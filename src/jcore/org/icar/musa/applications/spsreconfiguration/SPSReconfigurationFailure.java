package org.icar.musa.applications.spsreconfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.applications.Scenario;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.evolution.AddStatement;
import org.icar.musa.context.evolution.EvolutionScenario;
import org.icar.musa.context.evolution.RemoveStatement;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.CapabilityEvolutionScenario;
import org.icar.musa.runtime_entity.Condition;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPSReconfigurationFailure implements Scenario {
	@Override
	public String getName() {
		return "Circuito Logico Esteso con Failures";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		AssumptionSet domain = new AssumptionSet();	 
		try {
			
			/* generic rules on on-off, up-down and open-closed */
			domain.addAssumption_asString("off(X) :- load(X), not on(X).");
			domain.addAssumption_asString("off(X) :- generator(X), not on(X).");
			domain.addAssumption_asString("down(X) :- node(X), not up(X).");
			domain.addAssumption_asString("open(X) :- switch(X), not closed(X).");

			/* list of electrical elements */
			domain.addAssumption_asString("generator(main1).");
			domain.addAssumption_asString("generator(main2).");
			domain.addAssumption_asString("generator(aux1).");
			domain.addAssumption_asString("generator(aux2).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("load(l3).");
			domain.addAssumption_asString("load(l4).");
			domain.addAssumption_asString("load(l5).");
			domain.addAssumption_asString("load(l6).");
			domain.addAssumption_asString("load(l7).");
			domain.addAssumption_asString("load(l8).");
			domain.addAssumption_asString("load(l9).");
			domain.addAssumption_asString("load(l10).");
			domain.addAssumption_asString("load(l11).");
			domain.addAssumption_asString("load(l12).");
			domain.addAssumption_asString("load(l13).");
			domain.addAssumption_asString("load(l14).");
			domain.addAssumption_asString("load(l15).");
			domain.addAssumption_asString("load(l16).");
			domain.addAssumption_asString("load(l17).");
			domain.addAssumption_asString("load(l18).");
			domain.addAssumption_asString("load(l19).");
			domain.addAssumption_asString("load(l20).");
			domain.addAssumption_asString("load(l21).");
			domain.addAssumption_asString("load(l22).");
			domain.addAssumption_asString("load(l23).");
			domain.addAssumption_asString("load(l24).");
			domain.addAssumption_asString("node(n13).");
			domain.addAssumption_asString("node(n24).");
			domain.addAssumption_asString("node(n5).");
			domain.addAssumption_asString("node(n9).");
			domain.addAssumption_asString("node(n35).");
			domain.addAssumption_asString("node(n46).");
			domain.addAssumption_asString("node(n11).");
			domain.addAssumption_asString("node(n57).");
			domain.addAssumption_asString("node(n68).");
			domain.addAssumption_asString("node(n19).");
			domain.addAssumption_asString("node(n79).");
			domain.addAssumption_asString("node(n15).");
			domain.addAssumption_asString("node(n810).");
			domain.addAssumption_asString("node(n17).");
			
			domain.addAssumption_asString("node(n45).");
			
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			domain.addAssumption_asString("switch(i5).");
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
			domain.addAssumption_asString("switch(i18).");
			domain.addAssumption_asString("switch(i19).");
			domain.addAssumption_asString("switch(i20).");
			domain.addAssumption_asString("switch(i21).");
			domain.addAssumption_asString("switch(i22).");
			domain.addAssumption_asString("switch(i23).");
			domain.addAssumption_asString("switch(i24).");
			domain.addAssumption_asString("switch(i25).");
			domain.addAssumption_asString("switch(i26).");
			domain.addAssumption_asString("switch(i27).");
			
			//questi ultimi tre switch sono i tre guasti sul bus superiore
			domain.addAssumption_asString("switch(i28).");
			domain.addAssumption_asString("switch(i29).");
			domain.addAssumption_asString("switch(i30).");

			/* electrical topology */
			domain.addAssumption_asString("up(n13) :- closed(i4), on(aux1).");
			domain.addAssumption_asString("up(n13) :- closed(i1), up(n5).");
			domain.addAssumption_asString("up(n13) :- up(n45), closed(i28).");
			
			domain.addAssumption_asString("up(n45) :- up(n13), closed(i28).");
			domain.addAssumption_asString("up(n45) :- up(n35), closed(i29).");
			domain.addAssumption_asString("up(n45) :- up(n7), closed(i5).");

			domain.addAssumption_asString("up(n24) :- closed(i8), on(aux1).");
			domain.addAssumption_asString("up(n24) :- closed(i2), up(n5).");
			domain.addAssumption_asString("up(n24) :- up(n46).");
			domain.addAssumption_asString("on(l1) :- up(n13).");
			domain.addAssumption_asString("on(l1) :- closed(i1), up(n5).");
			domain.addAssumption_asString("up(n5) :- closed(i2), up(n24).");
			domain.addAssumption_asString("up(n5) :- closed(i1), up(n13).");
			domain.addAssumption_asString("on(l2) :- up(n5).");
			domain.addAssumption_asString("on(l3) :- closed(i3), up(n5).");
			domain.addAssumption_asString("on(l4) :- up(n24).");
			domain.addAssumption_asString("on(l4) :- closed(i2), up(n5).");
			domain.addAssumption_asString("on(l5) :- up(n13).");
			domain.addAssumption_asString("on(l5) :- up(n35).");
			domain.addAssumption_asString("on(l5) :- closed(i5), up(n7).");
			domain.addAssumption_asString("up(n7) :- closed(i6), up(n24).");
			domain.addAssumption_asString("up(n7) :- closed(i5), up(n13).");
			domain.addAssumption_asString("on(l6) :- up(n7).");
			domain.addAssumption_asString("on(l7) :- closed(i7), up(n7).");
			domain.addAssumption_asString("on(l8) :- up(n24).");
			domain.addAssumption_asString("on(l8) :- closed(i6), up(n7).");
			domain.addAssumption_asString("on(l8) :- up(n46).");
			domain.addAssumption_asString("up(n46) :- closed(i26), on(main1).");
			domain.addAssumption_asString("up(n46) :- up(n68).");
			domain.addAssumption_asString("up(n46) :- up(n24).");
			
			domain.addAssumption_asString("up(n35) :- up(n45), closed(i29).");
			domain.addAssumption_asString("up(n35) :- up(n57), closed(i30).");
			domain.addAssumption_asString("up(n35) :- closed(i9), up(n9).");
			
			domain.addAssumption_asString("on(l9) :- closed(i9), up(n35).");
			domain.addAssumption_asString("on(l9) :- up(n9).");
			domain.addAssumption_asString("on(l10) :- closed(i11), up(n9).");
			domain.addAssumption_asString("up(n9) :- closed(i9), up(n35).");
			domain.addAssumption_asString("up(n9) :- closed(i10), on(main1).");
			domain.addAssumption_asString("on(l11) :- up(n35).");
			domain.addAssumption_asString("on(l11) :- up(n57).");
			domain.addAssumption_asString("on(l11) :- closed(i12), up(n11).");
			domain.addAssumption_asString("on(l12) :- up(n11).");
			domain.addAssumption_asString("on(l12) :- closed(i12), up(n35).");
			domain.addAssumption_asString("on(l12) :- closed(i12), up(n57).");
			domain.addAssumption_asString("on(l13) :- closed(i14), up(n11).");
			domain.addAssumption_asString("on(l14) :- closed(i13), up(n11).");
			domain.addAssumption_asString("on(l14) :- up(n46).");
			domain.addAssumption_asString("on(l14) :- up(n68).");
			domain.addAssumption_asString("up(n11) :- closed(i12), up(n35).");
			domain.addAssumption_asString("up(n11) :- closed(i12), up(n57).");
			domain.addAssumption_asString("up(n11) :- closed(i13), up(n46).");
			domain.addAssumption_asString("up(n11) :- closed(i13), up(n68).");
			
			domain.addAssumption_asString("up(n57) :- closed(i15), on(aux2).");
			domain.addAssumption_asString("up(n57) :- up(n35), closed(i30).");
			domain.addAssumption_asString("up(n57) :- up(n79).");
			
			domain.addAssumption_asString("up(n68) :- closed(i16), on(aux2).");
			domain.addAssumption_asString("up(n68) :- up(n46).");
			domain.addAssumption_asString("up(n68) :- up(n810).");
			domain.addAssumption_asString("on(l15) :- up(n57).");
			domain.addAssumption_asString("on(l15) :- up(n79).");
			domain.addAssumption_asString("on(l15) :- closed(i17), up(n19).");
			domain.addAssumption_asString("on(l16) :- up(n19).");
			domain.addAssumption_asString("on(l16) :- closed(i17), up(n57).");
			domain.addAssumption_asString("on(l16) :- closed(i17), up(n79).");
			domain.addAssumption_asString("on(l17) :- closed(i19), up(n19).");
			domain.addAssumption_asString("on(l18) :- up(n68).");
			domain.addAssumption_asString("on(l18) :- up(n810).");
			domain.addAssumption_asString("on(l18) :- closed(i18), up(n19).");
			domain.addAssumption_asString("up(n79) :- closed(i27), on(main2).");
			domain.addAssumption_asString("up(n79) :- up(n57).");
			domain.addAssumption_asString("up(n79) :- closed(i20), up(n15).");
			domain.addAssumption_asString("on(l19) :- up(n15).");
			domain.addAssumption_asString("on(l19) :- closed(i20), up(n79).");
			domain.addAssumption_asString("on(l20) :- closed(i22), up(n15).");
			domain.addAssumption_asString("up(n15) :- closed(i20), up(n79).");
			domain.addAssumption_asString("up(n15) :- closed(i21), up(n810).");
			domain.addAssumption_asString("up(n810) :- closed(i21), up(n15).");
			domain.addAssumption_asString("up(n810) :- up(n68).");
			domain.addAssumption_asString("on(l21) :- up(n79).");
			domain.addAssumption_asString("on(l21) :- closed(i23), up(n17).");
			domain.addAssumption_asString("on(l22) :- up(n17).");
			domain.addAssumption_asString("on(l23) :- closed(i25), up(n17).");
			domain.addAssumption_asString("up(n17) :- closed(i23), up(n79).");
			domain.addAssumption_asString("up(n17) :- closed(i24), up(n810).");
			domain.addAssumption_asString("on(l24) :- up(n810).");
			domain.addAssumption_asString("on(l24) :- closed(i24), up(n17).");

		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		
		String p1 = "F((((on(l1) and on(l4)) and (on(l5) and on(l8))) and ((on(l11) and on(l14)) and (on(l15) and on(l18)))) and ( on(l21) and on(l24) ))" ;
		String g1 =  "goalmodel { goal g1 = "+p1+". }";
		
		System.out.println(g1);
		
		GoalModel model=null;
		LTLGoal goal = null;
		try {
			model = LTLGoalModelBuilder.parse(g1);
			goal = model.getGoals().iterator().next();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return goal;
	}

	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("on(main1).");
			w.addFact_asString("on(main2).");
			w.addFact_asString("off(aux1).");
			w.addFact_asString("off(aux2).");
			w.addFact_asString("closed(i1).");
			w.addFact_asString("closed(i2).");
			w.addFact_asString("closed(i3).");
			w.addFact_asString("open(i4).");
			w.addFact_asString("closed(i5).");
			w.addFact_asString("closed(i6).");
			w.addFact_asString("closed(i7).");
			w.addFact_asString("open(i8).");
			w.addFact_asString("closed(i9).");
			w.addFact_asString("closed(i10).");
			w.addFact_asString("closed(i11).");
			w.addFact_asString("closed(i12).");
			w.addFact_asString("closed(i13).");
			w.addFact_asString("closed(i14).");
			w.addFact_asString("open(i15).");
			w.addFact_asString("open(i16).");
			w.addFact_asString("closed(i17).");
			w.addFact_asString("closed(i18).");
			w.addFact_asString("closed(i19).");
			w.addFact_asString("closed(i20).");
			w.addFact_asString("open(i21).");
			w.addFact_asString("closed(i22).");
			w.addFact_asString("closed(i23).");
			w.addFact_asString("closed(i24).");
			w.addFact_asString("closed(i25).");
			w.addFact_asString("open(i26).");
			w.addFact_asString("closed(i27).");

			//guasti sempre aperti
			w.addFact_asString("open(i28).");
			w.addFact_asString("open(i29).");
			w.addFact_asString("open(i30).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}
	
	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generators*/
		capabilities.add(generate_switch_on_generator("main1"));
		capabilities.add(generate_switch_off_generator("main1"));
		capabilities.add(generate_switch_on_generator("main2"));
		capabilities.add(generate_switch_off_generator("main2"));
		capabilities.add(generate_switch_on_generator("aux1"));
		capabilities.add(generate_switch_off_generator("aux1"));
		capabilities.add(generate_switch_on_generator("aux2"));
		capabilities.add(generate_switch_off_generator("aux2"));
		
		/*switchers*/
		for (int i=0; i<27; i++) {
			capabilities.add(generate_open_capability_for_switcher("i"+(i+1)));
			capabilities.add(generate_close_capability_for_switcher("i"+(i+1)));			
		}	
		
		return capabilities;
	}
	

	private AbstractCapability generate_switch_on_generator(String name) {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant(name)));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant(name))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant(name)))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_"+name+"_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_switch_off_generator(String name) {
		/* switch_OFF_main_generator_cap
		 * PRE: on(main)
		 * SCENARIO mainOff: remove {on(main)}, add {off(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("on", new Constant(name)));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_off");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", new Constant(name))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", new Constant(name)))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_OFF_"+name+"_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_close_capability_for_switcher(String switch_name) {
		/* close_switch_<name>_cap
		 * PRE: open(<name>)
		 * SCENARIO iClosed: remove {open(<name>)}, add {closed(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("open", i_const));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iClosed");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("close_switch_"+switch_name+"_cap", main_on_evo, i_pre, null);
	}

	private AbstractCapability generate_open_capability_for_switcher(String switch_name) {
		/* open_switch_<name>_cap
		 * PRE: closed(<name>)
		 * SCENARIO iOpen: remove {closed(<name>)}, add {open(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("closed", i_const));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iOpen");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name+"_cap", main_on_evo, i_pre, null);
	}

	@Override
	public QualityAsset getQualityAsset() {
		// TODO Auto-generated method stub
		return null;
	}

}
