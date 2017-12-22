package domain.spsreconfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import communication.translator.ExtDLPHead;
import datalayer.awareness.AbstractCapability;
import datalayer.awareness.AssumptionSet;
import datalayer.awareness.Requirements;
import datalayer.awareness.LTL.formulamodel.FormulaBTConstruction;
import datalayer.world.Condition;
import datalayer.world.StateOfWorld;
import datalayer.world.evolution.AddStatement;
import datalayer.world.evolution.CapabilityEvolutionScenario;
import datalayer.world.evolution.EvolutionScenario;
import datalayer.world.evolution.RemoveStatement;
import domain.Scenario;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPSReconfigurationEasy implements Scenario {

	@Override
	public String getName() {
		return "Circuito Logico Articolo AAMAS";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		AssumptionSet domain = new AssumptionSet();	 
		try {
			/* list of electrical elements */
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("node(n3).");
			domain.addAssumption_asString("node(n4).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			
			/* generic rules on on-off, up-down and open-closed */
			domain.addAssumption_asString("off(X) :- load(X), not on(X).");
			domain.addAssumption_asString("off(X) :- generator(X), not on(X).");
			domain.addAssumption_asString("down(X) :- node(X), not up(X).");
			domain.addAssumption_asString("open(X) :- switch(X), not closed(X).");
			
			/* electrical topology */
			domain.addAssumption_asString("up(n3) :- up(n1), closed(i1).");
			domain.addAssumption_asString("up(n4) :- up(n2), closed(i2).");
			domain.addAssumption_asString("up(n1) :- on(main).");
			domain.addAssumption_asString("up(n2) :- on(main).");
			domain.addAssumption_asString("on(l1) :- up(n3).");
			domain.addAssumption_asString("on(l1) :- up(n4).");
			domain.addAssumption_asString("on(l2) :- up(n3), closed(i3).");
			domain.addAssumption_asString("on(l2) :- up(n4), closed(i4).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
//		String g1 =  "G on(main) && F on(l2) ";	//"G(on(l2) U (!on(l1)))"; //
		String g1 =  "F on(l2)";	//"G(on(l2) U (!on(l1)))"; //
//		String g1 =  "G (F on(l2) ) ";	//"G(on(l2) U (!on(l1)))"; //
//		String g1 =  "G on(main) && G (F on(l2) ) ";	//"G(on(l2) U (!on(l1)))"; //
		
		return FormulaBTConstruction.construct(g1);
	}

	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("on(main).");
			w.addFact_asString("open(i1).");
			w.addFact_asString("open(i2).");
			w.addFact_asString("closed(i3).");
			w.addFact_asString("open(i4).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}
	
	public ArrayList<AbstractCapability> getSubCapabilitySet1() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_switch_on_main_generator());
		capabilities.add(generate_switch_off_main_generator());
		return capabilities;		
	}
	public ArrayList<AbstractCapability> getSubCapabilitySet2() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_open_capability_for_switcher("i1"));
		capabilities.add(generate_close_capability_for_switcher("i1"));
		capabilities.add(generate_open_capability_for_switcher("i2"));
		capabilities.add(generate_close_capability_for_switcher("i2"));
		return capabilities;		
	}
	
	public ArrayList<AbstractCapability> getSubCapabilitySet3() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_open_capability_for_switcher("i3"));
		capabilities.add(generate_close_capability_for_switcher("i3"));
		capabilities.add(generate_open_capability_for_switcher("i4"));
		capabilities.add(generate_close_capability_for_switcher("i4"));
		return capabilities;
	}


	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generator*/
		capabilities.add(generate_switch_on_main_generator());
		capabilities.add(generate_switch_off_main_generator());
		
		/*switch*/
		capabilities.add(generate_open_capability_for_switcher("i1"));
		capabilities.add(generate_close_capability_for_switcher("i1"));
		capabilities.add(generate_open_capability_for_switcher("i2"));
		capabilities.add(generate_close_capability_for_switcher("i2"));
		capabilities.add(generate_open_capability_for_switcher("i3"));
		capabilities.add(generate_close_capability_for_switcher("i3"));
		capabilities.add(generate_open_capability_for_switcher("i4"));
		capabilities.add(generate_close_capability_for_switcher("i4"));		
		
		return capabilities;
	}
	

	private AbstractCapability generate_switch_on_main_generator() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new Condition(new DLPAtom("off", new Constant("main")));
		Set<EvolutionScenario> main_on_evo = new HashSet<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("mainOn");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("main"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("main")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_main_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_switch_off_main_generator() {
		/* switch_OFF_main_generator_cap
		 * PRE: on(main)
		 * SCENARIO mainOff: remove {on(main)}, add {off(main)}
		*/
		Condition main_on_pre = new Condition(new DLPAtom("on", new Constant("main")));
		Set<EvolutionScenario> main_on_evo = new HashSet<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("mainOff");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", new Constant("main"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", new Constant("main")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_OFF_main_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_close_capability_for_switcher(String switch_name) {
		/* close_switch_<name>_cap
		 * PRE: open(<name>)
		 * SCENARIO iClosed: remove {open(<name>)}, add {closed(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new Condition(new DLPAtom("open", i_const));
		Set<EvolutionScenario> i_evo = new HashSet<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iClosed");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const))));
		i_evo.add(i_evo_scenario);
		return new AbstractCapability("close_switch_"+switch_name+"_cap", i_evo, i_pre, null);
	}

	private AbstractCapability generate_open_capability_for_switcher(String switch_name) {
		/* open_switch_<name>_cap
		 * PRE: closed(<name>)
		 * SCENARIO iOpen: remove {closed(<name>)}, add {open(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new Condition(new DLPAtom("closed", i_const));
		Set<EvolutionScenario> i_evo = new HashSet<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iOpen");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const))));
		i_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name+"_cap", i_evo, i_pre, null);
	}

//	ALTRE CAPABILITY RIMOSSE	
//	/*main_generator_fault*/
//	AbstractCapability main_fault;
//	Condition main_fault_pre = new Condition(new DLPAtom("on", main));
//	Set<EvolutionScenario> main_fault_evo = new HashSet<>();
//	CapabilityEvolutionScenario main_fault_evo1 = new CapabilityEvolutionScenario("mainFault");
//	main_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", main)) ) );
//	main_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", main))));
//	main_fault_evo.add(main_fault_evo1);
//	main_fault = new AbstractCapability("simulate_FAILURE_main_generator_cap", main_fault_evo, main_fault_pre, null);
//	capabilities.add(main_fault);
//
//	/*aux_generator_on*/
//	AbstractCapability aux_on;
//	Constant aux = new Constant("aux");
//	Condition aux_on_pre = new Condition(new DLPAtom("off", aux));
//	Set<EvolutionScenario> aux_on_evo = new HashSet<>();
//	CapabilityEvolutionScenario aux_on_evo1 = new CapabilityEvolutionScenario("auxOn");
//	aux_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", aux)) ) );
//	aux_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", aux))));
//	aux_on_evo.add(aux_on_evo1);
//	aux_on = new AbstractCapability("switch_ON_aux_generator_cap", aux_on_evo, aux_on_pre, null);
//	capabilities.add(aux_on);
//
//	/*aux_generator_off*/
//	AbstractCapability aux_off;
//	Condition aux_off_pre = new Condition(new DLPAtom("on", aux));
//	Set<EvolutionScenario> aux_off_evo = new HashSet<>();
//	CapabilityEvolutionScenario aux_off_evo1 = new CapabilityEvolutionScenario("auxOff");
//	aux_off_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", aux)) ) );
//	aux_off_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
//	aux_off_evo.add(aux_off_evo1);
//	aux_off = new AbstractCapability("switch_OFF_aux_generator_cap", aux_off_evo, aux_off_pre, null);
//	capabilities.add(aux_off);
//
//	/*aux_generator_fault*/
//	AbstractCapability aux_fault;
//	Condition aux_fault_pre = new Condition(new DLPAtom("on", aux));
//	Set<EvolutionScenario> aux_fault_evo = new HashSet<>();
//	CapabilityEvolutionScenario aux_fault_evo1 = new CapabilityEvolutionScenario("auxFault");
//	aux_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", aux)) ) );
//	aux_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
//	aux_fault_evo.add(aux_fault_evo1);
//	aux_fault = new AbstractCapability("simulate_FAILURE_aux_generator_cap", aux_fault_evo, aux_fault_pre, null);
//	capabilities.add(aux_fault);
//
//	/*catch_fire*/
//	AbstractCapability catch_fire;
//	Constant a_fire = new Constant("a_fire");
//	Condition catch_fire_pre = new Condition(new DLPAtom("fire", a_fire));
//	Set<EvolutionScenario> catch_fire_evo = new HashSet<>();
//	CapabilityEvolutionScenario catch_fire_evo1 = new CapabilityEvolutionScenario("catchFire");
//	catch_fire_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("verified", a_fire)) ) );
//	catch_fire_evo.add(catch_fire_evo1);
//	catch_fire = new AbstractCapability("simulate_fire_cap", catch_fire_evo, catch_fire_pre, null);
//	capabilities.add(catch_fire);
//
//	/*the_fire_sys_on*/
//	AbstractCapability the_fire_sys_on;
//	Constant the_fire_sys = new Constant("the_fire_sys");
//	Condition the_fire_sys_on_pre = new Condition(new DLPAtom("off", the_fire_sys));
//	Set<EvolutionScenario> the_fire_sys_on_evo = new HashSet<>();
//	CapabilityEvolutionScenario the_fire_sys_on_evo1 = new CapabilityEvolutionScenario("the_fire_sysOn");
//	the_fire_sys_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", the_fire_sys)) ) );
//	the_fire_sys_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", the_fire_sys))));
//	the_fire_sys_on_evo.add(the_fire_sys_on_evo1);		
//	the_fire_sys_on = new AbstractCapability("switch_ON_the_fire_sys_cap", the_fire_sys_on_evo, the_fire_sys_on_pre, null);
//	capabilities.add(the_fire_sys_on);

}
