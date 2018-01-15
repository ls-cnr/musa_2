package domain.spsreconfiguration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.Condition;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.domain.evolution.AddStatement;
import org.icar.musa.core.domain.evolution.CapabilityEvolutionScenario;
import org.icar.musa.core.domain.evolution.EvolutionScenario;
import org.icar.musa.core.domain.evolution.RemoveStatement;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.MultipleExpansion;
import org.icar.musa.proactive_means_end_reasoning.NormalExpansion;
import org.icar.musa.proactive_means_end_reasoning.ProblemExploration;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.specification.linear_temporal_logic.formulamodel.FormulaBTConstruction;
import org.icar.specification.linear_temporal_logic.formulamodel.LTLGoal;
import org.icar.specification.linear_temporal_logic.net.PNHierarchy;
import org.icar.specification.linear_temporal_logic.net.TokenConf;
import org.icar.specification.linear_temporal_logic.net.netmodels.GloballyPN;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class NECSSetup {

	private AssumptionSet domain;
	private ProblemExploration problem;	
	private TokenConf startingTokens;

	private AssumptionSet configureAssumptions() {
		AssumptionSet domain = new AssumptionSet();	 
		try {	
			domain.addAssumption_asString("component(X) :- generator(X).");
			domain.addAssumption_asString("component(X) :- motor(X).");
			domain.addAssumption_asString("component(X) :- load(X).");
			domain.addAssumption_asString("component(X) :- fire_system(X).");
			domain.addAssumption_asString("event(X) :- fire(X).");
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("generator(aux).");
			domain.addAssumption_asString("motor(motor_1).");
			domain.addAssumption_asString("motor(motor_2).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("fire_system(a_fire_sys).");
			domain.addAssumption_asString("fire(a_fire).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("node(n3).");
			domain.addAssumption_asString("node(n4).");
			domain.addAssumption_asString("node(n5).");
			domain.addAssumption_asString("node(n6).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			domain.addAssumption_asString("switch(i5).");
			domain.addAssumption_asString("switch(i6).");
			domain.addAssumption_asString("switch(i7).");
			domain.addAssumption_asString("switch(i8).");
			domain.addAssumption_asString("switch(i9).");
			domain.addAssumption_asString("switch(i10).");
			domain.addAssumption_asString("switch(i11).");
			domain.addAssumption_asString("switch(i12).");
			domain.addAssumption_asString("switch(i13).");
			domain.addAssumption_asString("switch(i14).");
			domain.addAssumption_asString("switch(i15).");
			domain.addAssumption_asString("switch(i16).");
			domain.addAssumption_asString("switch(i17).");
			domain.addAssumption_asString("on(l1) :- up(n2), closed(i8).");
			domain.addAssumption_asString("on(l1) :- up(n5), closed(i7).");
			domain.addAssumption_asString("on(l2) :- up(n3), closed(i14), closed(i13).");
			domain.addAssumption_asString("on(l2) :- up(n6), closed(i11), closed(i12).");
			domain.addAssumption_asString("on(motor_1) :- up(n3), closed(i15).");
			domain.addAssumption_asString("on(motor_2) :- up(n6), closed(i10).");
			domain.addAssumption_asString("up(n2) :- up(n1), closed(i17).");
			domain.addAssumption_asString("up(n3) :- up(n2), closed(i16).");
			domain.addAssumption_asString("up(n5) :- up(n4), closed(i6).");
			domain.addAssumption_asString("un(n6) :- up(n5), closed(i9).");
			domain.addAssumption_asString("up(n1) :- on(main), closed(i1).");
			domain.addAssumption_asString("up(n1) :- up(n4), closed(i4), closed(i3), closed(i2).");
			domain.addAssumption_asString("up(n4) :- on(aux), closed(i5).");
			domain.addAssumption_asString("up(n4) :- up(1), closed(i2), closed(i3), closed(i4).");
			domain.addAssumption_asString("off(l1) :- not on(l1).");
			domain.addAssumption_asString("off(l2) :- not on(l2).");
//			domain.addAssumption_asString("off(motor_1) :- not on(motor_1).");
//			domain.addAssumption_asString("off(motor_2) :- not on(motor_2).");
//			domain.addAssumption_asString("down(n1) :- not up(n1).");
//			domain.addAssumption_asString("down(n2) :- not up(n2).");
//			domain.addAssumption_asString("down(n3) :- not up(n3).");
//			domain.addAssumption_asString("down(n4) :- not up(n4).");
//			domain.addAssumption_asString("down(n5) :- not up(n5).");
//			domain.addAssumption_asString("down(n6) :- not up(n6).");
//			domain.addAssumption_asString("off(main) :- fault(main).");
//			domain.addAssumption_asString("off(aux) :- fault(aux).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		return domain;
	}
	
	private ArrayList<AbstractCapability> configureCapabilities() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		Constant main = new Constant("main");

		/*main_generator_on*/
		AbstractCapability main_on;
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", main));
		Set<EvolutionScenario> main_on_evo = new HashSet<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("mainOn");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", main)) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", main))));
		main_on_evo.add(main_on_evo1);		
		main_on = new AbstractCapability("switch_ON_main_generator_cap", main_on_evo, main_on_pre, null);
		capabilities.add(main_on);
		
		/*main_generator_off*/
		AbstractCapability main_off;
		Condition main_off_pre = new FOLCondition(new DLPAtom("on", main));
		Set<EvolutionScenario> main_off_evo = new HashSet<>();
		CapabilityEvolutionScenario main_off_evo1 = new CapabilityEvolutionScenario("mainOff");
		main_off_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", main)) ) );
		main_off_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", main))));
		main_off_evo.add(main_off_evo1);	
		main_off = new AbstractCapability("switch_OFF_main_generator_cap", main_off_evo, main_off_pre, null);
		capabilities.add(main_off);

		/*main_generator_fault*/
		AbstractCapability main_fault;
		Condition main_fault_pre = new FOLCondition(new DLPAtom("on", main));
		Set<EvolutionScenario> main_fault_evo = new HashSet<>();
		CapabilityEvolutionScenario main_fault_evo1 = new CapabilityEvolutionScenario("mainFault");
		main_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", main)) ) );
		main_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", main))));
		main_fault_evo.add(main_fault_evo1);
		main_fault = new AbstractCapability("simulate_FAILURE_main_generator_cap", main_fault_evo, main_fault_pre, null);
		capabilities.add(main_fault);

		/*aux_generator_on*/
		AbstractCapability aux_on;
		Constant aux = new Constant("aux");
		Condition aux_on_pre = new FOLCondition(new DLPAtom("off", aux));
		Set<EvolutionScenario> aux_on_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_on_evo1 = new CapabilityEvolutionScenario("auxOn");
		aux_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", aux)) ) );
		aux_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", aux))));
		aux_on_evo.add(aux_on_evo1);
		aux_on = new AbstractCapability("switch_ON_aux_generator_cap", aux_on_evo, aux_on_pre, null);
		capabilities.add(aux_on);

		/*aux_generator_off*/
		AbstractCapability aux_off;
		Condition aux_off_pre = new FOLCondition(new DLPAtom("on", aux));
		Set<EvolutionScenario> aux_off_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_off_evo1 = new CapabilityEvolutionScenario("auxOff");
		aux_off_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", aux)) ) );
		aux_off_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
		aux_off_evo.add(aux_off_evo1);
		aux_off = new AbstractCapability("switch_OFF_aux_generator_cap", aux_off_evo, aux_off_pre, null);
		capabilities.add(aux_off);

		/*aux_generator_fault*/
		AbstractCapability aux_fault;
		Condition aux_fault_pre = new FOLCondition(new DLPAtom("on", aux));
		Set<EvolutionScenario> aux_fault_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_fault_evo1 = new CapabilityEvolutionScenario("auxFault");
		aux_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", aux)) ) );
		aux_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
		aux_fault_evo.add(aux_fault_evo1);
		aux_fault = new AbstractCapability("simulate_FAILURE_aux_generator_cap", aux_fault_evo, aux_fault_pre, null);
		capabilities.add(aux_fault);

		/*catch_fire*/
		AbstractCapability catch_fire;
		Constant a_fire = new Constant("a_fire");
		Condition catch_fire_pre = new FOLCondition(new DLPAtom("fire", a_fire));
		Set<EvolutionScenario> catch_fire_evo = new HashSet<>();
		CapabilityEvolutionScenario catch_fire_evo1 = new CapabilityEvolutionScenario("catchFire");
		catch_fire_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("verified", a_fire)) ) );
		catch_fire_evo.add(catch_fire_evo1);
		catch_fire = new AbstractCapability("simulate_fire_cap", catch_fire_evo, catch_fire_pre, null);
		capabilities.add(catch_fire);

		/*the_fire_sys_on*/
		AbstractCapability the_fire_sys_on;
		Constant the_fire_sys = new Constant("the_fire_sys");
		Condition the_fire_sys_on_pre = new FOLCondition(new DLPAtom("off", the_fire_sys));
		Set<EvolutionScenario> the_fire_sys_on_evo = new HashSet<>();
		CapabilityEvolutionScenario the_fire_sys_on_evo1 = new CapabilityEvolutionScenario("the_fire_sysOn");
		the_fire_sys_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", the_fire_sys)) ) );
		the_fire_sys_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", the_fire_sys))));
		the_fire_sys_on_evo.add(the_fire_sys_on_evo1);		
		the_fire_sys_on = new AbstractCapability("switch_ON_the_fire_sys_cap", the_fire_sys_on_evo, the_fire_sys_on_pre, null);
		capabilities.add(the_fire_sys_on);
		
		/*switch*/
		capabilities.add(generate_open_capability_for_switcher("i1"));
		capabilities.add(generate_close_capability_for_switcher("i1"));
		capabilities.add(generate_open_capability_for_switcher("i2"));
		capabilities.add(generate_close_capability_for_switcher("i2"));
		capabilities.add(generate_open_capability_for_switcher("i3"));
		capabilities.add(generate_close_capability_for_switcher("i3"));
		capabilities.add(generate_open_capability_for_switcher("i4"));
		capabilities.add(generate_close_capability_for_switcher("i4"));		
		capabilities.add(generate_open_capability_for_switcher("i5"));
		capabilities.add(generate_close_capability_for_switcher("i5"));
		capabilities.add(generate_open_capability_for_switcher("i6"));
		capabilities.add(generate_close_capability_for_switcher("i6"));
		capabilities.add(generate_open_capability_for_switcher("i7"));
		capabilities.add(generate_close_capability_for_switcher("i7"));
		capabilities.add(generate_open_capability_for_switcher("i8"));
		capabilities.add(generate_close_capability_for_switcher("i8"));
		capabilities.add(generate_open_capability_for_switcher("i9"));
		capabilities.add(generate_close_capability_for_switcher("i9"));
		capabilities.add(generate_open_capability_for_switcher("i10"));
		capabilities.add(generate_close_capability_for_switcher("i10"));
		capabilities.add(generate_open_capability_for_switcher("i11"));
		capabilities.add(generate_close_capability_for_switcher("i11"));
		capabilities.add(generate_open_capability_for_switcher("i12"));
		capabilities.add(generate_close_capability_for_switcher("i12"));
		capabilities.add(generate_open_capability_for_switcher("i13"));
		capabilities.add(generate_close_capability_for_switcher("i13"));
		capabilities.add(generate_open_capability_for_switcher("i14"));
		capabilities.add(generate_close_capability_for_switcher("i14"));
		capabilities.add(generate_open_capability_for_switcher("i15"));
		capabilities.add(generate_close_capability_for_switcher("i15"));
		capabilities.add(generate_open_capability_for_switcher("i16"));
		capabilities.add(generate_close_capability_for_switcher("i16"));
		capabilities.add(generate_open_capability_for_switcher("i17"));
		capabilities.add(generate_close_capability_for_switcher("i17"));			
		
		return capabilities;
	}
	private AbstractCapability generate_close_capability_for_switcher(String switch_name) {
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("open", i_const));
		Set<EvolutionScenario> i_evo = new HashSet<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("i1Closed");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const))));
		i_evo.add(i_evo_scenario);
		return new AbstractCapability("close_switch_"+switch_name+"_cap", i_evo, i_pre, null);
	}

	private AbstractCapability generate_open_capability_for_switcher(String switch_name) {
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("closed", i_const));
		Set<EvolutionScenario> i_evo = new HashSet<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("i1Open");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const))));
		i_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name+"_cap", i_evo, i_pre, null);
	}
	
	private StateOfWorld generate_initial_state_scenario1() {
		StateOfWorld wStart = new StateOfWorld(); 
		try {
			  wStart.addFact_asString("on(main).");
			  wStart.addFact_asString("off(aux).");
			  wStart.addFact_asString("off(the_fire_sys).");
			  wStart.addFact_asString("closed(i1).");
			  wStart.addFact_asString("closed(i2).");
			  wStart.addFact_asString("closed(i3).");
			  wStart.addFact_asString("closed(i4).");
			  wStart.addFact_asString("open(i5).");
			  wStart.addFact_asString("closed(i6).");
			  wStart.addFact_asString("open(i7).");
			  wStart.addFact_asString("open(i8).");
			  wStart.addFact_asString("closed(i9).");
			  wStart.addFact_asString("closed(i10).");
			  wStart.addFact_asString("open(i11).");
			  wStart.addFact_asString("open(i12).");
			  wStart.addFact_asString("closed(i13).");
			  wStart.addFact_asString("closed(i14).");
			  wStart.addFact_asString("open(i15).");
			  wStart.addFact_asString("closed(i16).");
			  wStart.addFact_asString("closed(i17).");
			  wStart.addFact_asString("up(n6).");
			  //wStart.addFact_asString("closed(i15).");
			} catch (ParseException e) {
			  e.printStackTrace();
			} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			  e.printStackTrace();
		}
		return wStart;
	}

	private void modelConstruction( LTLGoal treeModel ) throws ProblemDefinitionException {
		ProblemSpecification ps = new ProblemSpecification(domain,treeModel,null);
		problem = new ProblemExploration(ps, new ArrayList<AbstractCapability>());
		startingTokens = new TokenConf(new PNHierarchy(treeModel));

//		System.out.println("\n- Formulas Tree");
//		treeModel.print(treeModel.getRoot());
//		System.out.println("-----------------------------------------------------------------");
	}
	
	
	
	@Before
	public void init() {
		domain = configureAssumptions();
	}
	
	@Ignore
	@Test
	public void test1() throws ProblemDefinitionException {
		System.out.println("-----Test1------");
		modelConstruction( FormulaBTConstruction.construct("G on(l1)"));
		
		StateOfWorld wStart = new StateOfWorld(); 
		try {
			  wStart.addFact_asString("off(main).");
			  wStart.addFact_asString("off(aux).");
			  wStart.addFact_asString("off(the_fire_sys).");
			  wStart.addFact_asString("closed(i1).");
			  wStart.addFact_asString("closed(i2).");
			  wStart.addFact_asString("open(i3).");
			  wStart.addFact_asString("open(i4).");
			  wStart.addFact_asString("open(i5).");
			  wStart.addFact_asString("open(i6).");
			  wStart.addFact_asString("open(i7).");
			  wStart.addFact_asString("closed(i8).");
			  wStart.addFact_asString("open(i9).");
			  wStart.addFact_asString("open(i10).");
			  wStart.addFact_asString("open(i11).");
			  wStart.addFact_asString("open(i12).");
			  wStart.addFact_asString("open(i13).");
			  wStart.addFact_asString("open(i14).");
			  wStart.addFact_asString("open(i15).");
			  wStart.addFact_asString("open(i16).");
			  wStart.addFact_asString("closed(i17).");
			} catch (ParseException e) {
			  e.printStackTrace();
			} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			  e.printStackTrace();
			}
		
		problem.addToVisit(new WorldNode(wStart), startingTokens, 10);

		ArrayList<AbstractCapability> capabilities = configureCapabilities();
		problem.setCapabilities(capabilities);
		
		int k = 0;
		while( !problem.toVisitIsEmpty() && k++ < 90)
			problem.expandNode();
		
		HashMap<ExtendedNode, Integer> swtmp = new HashMap<>();
		
		int tmp = 0;
		
		for( GraphExpansion nk : problem.getExpandedList() ){
			System.out.println("-----------------------------------------------------------------");
			System.out.println( nk.getCapability() );
			if( swtmp.containsKey(nk.getSource()) )
				System.out.println("Source W"+ swtmp.get(nk.getSource()) );
			else {
				swtmp.put(nk.getSource(), tmp);
				System.out.println("Source W"+ tmp++);
			}
			for( ExtendedNode e : nk.getDestination() ){
				if( swtmp.containsKey(e) )
					System.out.println("Name W"+ swtmp.get(e) );
				else {
					swtmp.put(e, tmp);
					System.out.println("Name W"+ tmp++);
				}
				System.out.println("Score: " + e.getScore());
				for( String net : e.getTokens().getNetsState().keySet() )
					System.out.println(net + " -> State:" + e.getTokens().getNetState(net));
				
				for( String net : e.getTokens().getConf().keySet() )
					for( String p : e.getTokens().getTokens(net) )
						System.out.println(net + " -> Place:" + p);
				
				System.out.println("");
			}
		}
	}

	
	@Test
	public void test2() throws ProblemDefinitionException {
		System.out.println("-----Test2------");
		
		// DOMANDA: USA LE DOMAIN ASSUMPTIONS?
		// PER OGNI NODO: motor_2 is ON o OFF?
		FOLCondition c1 = new FOLCondition(new DLPAtom("on",new Constant("motor_2") ));
		Condition c2 = new FOLCondition(new DLPAtom("on",new Constant("l2") ));
		
		// NOTA DI LUCA: se metto il goal "G(on(l2) U (on(motor_2)))" mi da line 1:23 no viable alternative at input 'on(motor_2))'
		modelConstruction( FormulaBTConstruction.construct("G(on(l2) U (!on(motor_2)))"));
		
		StateOfWorld wStart = generate_initial_state_scenario1();
		EntailOperator entail = EntailOperator.getInstance();
		entail.setVerbose(true);
		boolean b1 = entail.entailsCondition(wStart, domain, c1);
		//boolean b2 = entail.entailsCondition(wStart, domain, c2);
		
		
		System.out.println("motor_2:"+b1);
		//System.out.println("l2:"+b2);
				
//		problem.addToVisit(new WorldNode(wStart), startingTokens, 10);
//
//		ArrayList<AbstractCapability> capabilities = configureCapabilities();
//		problem.setCapabilities(capabilities);
//		
//		int k = 0;
//		while( !problem.toVisitIsEmpty() && k++ < 5) {
//			System.out.print("+");
//			problem.expandNode();
//		}
//		System.out.println("("+k+" iterazioni)("+problem.getAllStates().size()+" nodi)");
//		
//		
//		int r=0;
//		for (ENode e : problem.getAllStates()) {
//			if (r++<10) {
//				System.out.println("----------------node-(score: "+e.getScore()+")---------------------");
//				StateOfWorld W = e.getWorldState();
//				//System.out.println(W.toString());
//				b1 = DomainEntail.getInstance().entailsCondition(wStart, domain, c1);
//				b2 = DomainEntail.getInstance().entailsCondition(wStart, domain, c2);
//				System.out.println("motor_2:"+b1);
//				System.out.println("l2:"+b2);
//			}
//		}
		
		
//		HashMap<ENode, Integer> swtmp = new HashMap<>();
//		
//		int tmp = 0;
//		
//		for( ExpansionNode nk : problem.getExpandedList() ){
//			System.out.println("----------------expansion----------------------");
//			System.out.println( nk.getCapability() );
//			if( swtmp.containsKey(nk.getSource()) )
//				System.out.println("Source W"+ swtmp.get(nk.getSource()) );
//			else {
//				swtmp.put(nk.getSource(), tmp);
//				System.out.println("Source W"+ tmp++);
//			}
//			for( ENode e : nk.getDestination() ){
//				if( swtmp.containsKey(e) )
//					System.out.println("Name W"+ swtmp.get(e) );
//				else {
//					swtmp.put(e, tmp);
//					System.out.println("Name W"+ tmp++);
//				}
//				System.out.println("Score: " + e.getScore());
//				for( String net : e.getTokens().getNetsState().keySet() )
//					System.out.println(net + " -> State:" + e.getTokens().getNetState(net));
//				
//				for( String net : e.getTokens().getConf().keySet() )
//					for( String p : e.getTokens().getTokens(net) )
//						System.out.println(net + " -> Place:" + p);
//				
//				System.out.println("");
//			}
//		}
	}
	

}
