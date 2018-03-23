package org.icar.musa.domain_app.spsreconfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.Condition;
import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.context.evolution.AddStatement;
import org.icar.musa.core.context.evolution.EvolutionScenario;
import org.icar.musa.core.context.evolution.RemoveStatement;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.CapabilityEvolutionScenario;
import org.icar.musa.core.runtime_entity.QualityAsset;
import org.icar.musa.domain_app.Scenario;
import org.icar.musa.persistence.entity.DomainAssumption;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.model.GoalModel;
import org.icar.specification.LTLgoal.model.LTLGoal;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPSReconfigurationFailure2 implements Scenario {
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
			domain.addAssumption_asString("generator(mg1).");
			domain.addAssumption_asString("generator(mg2).");
			domain.addAssumption_asString("generator(aux1).");
			domain.addAssumption_asString("generator(aux2).");
			
			for (int i=1; i<=24; i++) 
				domain.addAssumption_asString("load(l"+i+").");
			
			for (int i=1; i<=25; i++) 
				domain.addAssumption_asString("switch(sw"+i+").");			
			
			/* electrical topology */
			domain.addAssumption_asString( "up(n1) :- up(n2), not f(b1_2)."  );
			domain.addAssumption_asString( "up(n1) :- up(n10), not f(b1_10)."  );
			domain.addAssumption_asString( "up(n2) :- up(n1), not f(b1_2)."  );
			domain.addAssumption_asString( "up(n2) :- up(n15), closed(sw4)."  );
			domain.addAssumption_asString( "up(n2) :- up(n3), not f(b2_3)."  );
			domain.addAssumption_asString( "up(n3) :- up(n2), not f(b2_3)."  );
			domain.addAssumption_asString( "up(n3) :- up(n17), not f(b3_17)."  );
			domain.addAssumption_asString( "up(n3) :- up(n4), not f(b3_4)."  );
			domain.addAssumption_asString( "up(n4) :- up(n3), not f(b3_4)."  );
			domain.addAssumption_asString( "up(n4) :- up(n22), closed(sw9)."  );
			domain.addAssumption_asString( "up(n4) :- up(n5), not f(b4_5)."  );
			domain.addAssumption_asString( "up(n5) :- up(n4), not f(b4_5)."  );
			domain.addAssumption_asString( "up(n5) :- up(n26), not f(b5_26)."  );
			domain.addAssumption_asString( "up(n5) :- up(n6), not f(b5_6)."  );
			domain.addAssumption_asString( "up(n6) :- up(n5), not f(b5_6)."  );
			domain.addAssumption_asString( "up(n6) :- up(n31), closed(sw15)."  );
			domain.addAssumption_asString( "up(n6) :- up(n7), not f(b6_7)."  );
			domain.addAssumption_asString( "up(n7) :- up(n6), not f(b6_7)."  );
			domain.addAssumption_asString( "up(n7) :- up(n33), not f(b7_33)."  );
			domain.addAssumption_asString( "up(n7) :- up(n8), not f(b7_8)."  );
			domain.addAssumption_asString( "up(n8) :- up(n7), not f(b7_8)."  );
			domain.addAssumption_asString( "up(n8) :- up(n38)."  );
			domain.addAssumption_asString( "up(n8) :- up(n9), not f(b8_9)."  );
			domain.addAssumption_asString( "up(n9) :- up(n8), not f(b8_9)."  );
			domain.addAssumption_asString( "up(n9) :- up(n42), not f(b9_42)."  );
			domain.addAssumption_asString( "up(n10) :- up(n1), not f(b1_10)."  );
			domain.addAssumption_asString( "up(n10) :- up(n11), closed(sw1)."  );
			domain.addAssumption_asString( "on(l1) :- up(n10)."  );			
			domain.addAssumption_asString( "up(n11) :- up(n10), closed(sw1)."  );
			domain.addAssumption_asString( "up(n11) :- up(n12), not f(b11_12)."  );
			domain.addAssumption_asString( "on(l2) :- up(n11)."  );
			domain.addAssumption_asString( "up(n12) :- up(n11), not f(b11_12)."  );
			domain.addAssumption_asString( "up(n12) :- up(n13), closed(sw2)."  );
			domain.addAssumption_asString( "on(l3) :- up(n12), closed(sw3)."  );
			domain.addAssumption_asString( "up(n13) :- up(n12), closed(sw2)."  );
			domain.addAssumption_asString( "up(n13) :- up(n14), not f(b13_14)."  );
			domain.addAssumption_asString( "on(l4) :- up(n13)."  );
			domain.addAssumption_asString( "up(n14) :- up(n13), not f(b13_14)."  );
			domain.addAssumption_asString( "up(n14) :- up(n16), not f(b14_16)."  );
			domain.addAssumption_asString( "up(n15) :- up(n2), closed(sw4)."  );
			domain.addAssumption_asString( "up(n15) :- on(aux1)."  );
			domain.addAssumption_asString( "up(n15) :- up(n16), closed(sw8)."  );
			domain.addAssumption_asString( "up(n16) :- up(n14), not f(b14_16)."  );
			domain.addAssumption_asString( "up(n16) :- up(n15), closed(sw8)."  );
			domain.addAssumption_asString( "up(n16) :- up(n21), not f(b16_21)."  );
			domain.addAssumption_asString( "up(n17) :- up(n3), not f(b3_17)."  );
			domain.addAssumption_asString( "up(n17) :- up(n18), closed(sw5)."  );
			domain.addAssumption_asString( "on(l5) :- up(n17)."  );
			domain.addAssumption_asString( "up(n18) :- up(n17), closed(sw5)."  );
			domain.addAssumption_asString( "up(n18) :- up(n19), not f(b18_19)."  );
			domain.addAssumption_asString( "on(l6) :- up(n18)."  );
			domain.addAssumption_asString( "up(n19) :- up(n18), not f(b18_19)."  );
			domain.addAssumption_asString( "up(n19) :- up(n20), closed(sw6)."  );
			domain.addAssumption_asString( "on(l7) :- up(n19), closed(sw7)."  );
			domain.addAssumption_asString( "up(n20) :- up(n19), closed(sw6)."  );
			domain.addAssumption_asString( "up(n20) :- up(n21), not f(b20_21)."  );
			domain.addAssumption_asString( "on(l8) :- up(n20)."  );
			domain.addAssumption_asString( "up(n21) :- up(n16), not f(b16_21)."  );
			domain.addAssumption_asString( "up(n21) :- up(n20)."  );
			domain.addAssumption_asString( "up(n21) :- up(n25), not f(b21_25)."  );
			domain.addAssumption_asString( "up(n22) :- up(n4), closed(sw9)."  );			
			domain.addAssumption_asString( "up(n22) :- up(n23), not f(b22_23)."  );
			domain.addAssumption_asString( "on(l9) :- up(n22)."  );
			domain.addAssumption_asString( "up(n23) :- up(n22), not f(b22_23)."  );
			domain.addAssumption_asString( "up(n23) :- up(n24), closed(sw10)."  );
			domain.addAssumption_asString( "on(l10) :- up(n23), closed(sw11)."  );
			domain.addAssumption_asString( "up(n24) :- up(n23), closed(sw10)."  );
			domain.addAssumption_asString( "up(n24) :- on(mg1)."  );
			domain.addAssumption_asString( "up(n24) :- up(n25)."  );
			domain.addAssumption_asString( "up(n25) :- up(n21), not f(b21_25)."  );
			domain.addAssumption_asString( "up(n25) :- up(n24)."  );
			domain.addAssumption_asString( "up(n25) :- up(n30), not f(b25_30)."  );
			domain.addAssumption_asString( "up(n26) :- up(n5), not f(b5_26)."  );
			domain.addAssumption_asString( "up(n26) :- up(n27), closed(sw12)."  );
			domain.addAssumption_asString( "on(l11) :- up(n26)."  );
			domain.addAssumption_asString( "up(n27) :- up(n26), closed(sw12)."  );
			domain.addAssumption_asString( "up(n27) :- up(n28), not f(b27_28)."  );
			domain.addAssumption_asString( "on(l12) :- up(n27)."  );
			domain.addAssumption_asString( "up(n28) :- up(n27), not f(b27_28)."  );
			domain.addAssumption_asString( "up(n28) :- up(n29), closed(sw13)."  );
			domain.addAssumption_asString( "on(l13) :- up(n28), closed(sw14)."  );
			domain.addAssumption_asString( "up(n29) :- up(n28), closed(sw13)."  );
			domain.addAssumption_asString( "up(n29) :- up(n30), not f(b29_30)."  );
			domain.addAssumption_asString( "on(l14) :- up(n29)."  );		
			domain.addAssumption_asString( "up(n30) :- up(n29), not f(b29_30)."  );
			domain.addAssumption_asString( "up(n30) :- up(n25), not f(b25_30)."  );
			domain.addAssumption_asString( "up(n30) :- up(n32), not f(b30_32)."  );
			domain.addAssumption_asString( "up(n31) :- up(n6), closed(sw15)."  );
			domain.addAssumption_asString( "up(n31) :- on(aux2)."  );
			domain.addAssumption_asString( "up(n31) :- up(n32), closed(sw16)."  );
			domain.addAssumption_asString( "up(n32) :- up(n30), not f(b30_32)."  );
			domain.addAssumption_asString( "up(n32) :- up(n31), closed(sw16)."  );
			domain.addAssumption_asString( "up(n32) :- up(n37), not f(b32_37)."  );
			domain.addAssumption_asString( "up(n33) :- up(n7), not f(b7_33)."  );
			domain.addAssumption_asString( "up(n33) :- up(n34), closed(sw17)."  );
			domain.addAssumption_asString( "on(l15) :- up(n33)."  );
			domain.addAssumption_asString( "up(n34) :- up(n33), closed(sw17)."  );
			domain.addAssumption_asString( "up(n34) :- up(n35), not f(b34_35)."  );
			domain.addAssumption_asString( "on(l16) :- up(n34)."  );
			domain.addAssumption_asString( "up(n35) :- up(n34), not f(b34_35)."  );
			domain.addAssumption_asString( "up(n35) :- up(n36), closed(sw18)."  );
			domain.addAssumption_asString( "on(l17) :- up(n35), closed(sw19)."  );
			domain.addAssumption_asString( "up(n36) :- up(n35), closed(sw18)."  );
			domain.addAssumption_asString( "up(n36) :- up(n37), not f(b36_37)."  );
			domain.addAssumption_asString( "on(l18) :- up(n36)."  );	
			domain.addAssumption_asString( "up(n37) :- up(n36), not f(b36_37)."  );
			domain.addAssumption_asString( "up(n37) :- up(n32), not f(b32_37)."  );
			domain.addAssumption_asString( "up(n37) :- up(n41), not f(b37_41)."  );
			domain.addAssumption_asString( "up(n38) :- up(n8)."  );
			domain.addAssumption_asString( "up(n38) :- on(mg2)."  );
			domain.addAssumption_asString( "up(n38) :- up(n39)."  );
			domain.addAssumption_asString( "up(n39) :- up(n38)."  );
			domain.addAssumption_asString( "up(n39) :- up(n40), not f(b39_40)."  );
			domain.addAssumption_asString( "on(l19) :- up(n39)."  );
			domain.addAssumption_asString( "up(n40) :- up(n39), not f(b39_40)."  );
			domain.addAssumption_asString( "up(n40) :- up(n41), closed(sw21)."  );
			domain.addAssumption_asString( "on(l20) :- up(n40), closed(sw22)."  );
			domain.addAssumption_asString( "up(n41) :- up(n37), not f(b37_41)."  );
			domain.addAssumption_asString( "up(n41) :- up(n40), closed(sw21)."  );
			domain.addAssumption_asString( "up(n41) :- up(n46), not f(b41_46)."  );
			domain.addAssumption_asString( "up(n42) :- up(n9), not f(b9_42)."  );
			domain.addAssumption_asString( "up(n42) :- up(n43), closed(sw23)."  );
			domain.addAssumption_asString( "on(l21) :- up(n42)."  );
			domain.addAssumption_asString( "up(n43) :- up(n42), closed(sw23)."  );
			domain.addAssumption_asString( "up(n43) :- up(n44), not f(b43_44)."  );
			domain.addAssumption_asString( "on(l22) :- up(n43)."  );
			domain.addAssumption_asString( "up(n44) :- up(n43), not f(b43_44)."  );
			domain.addAssumption_asString( "up(n44) :- up(n45), closed(sw25)."  );
			domain.addAssumption_asString( "on(l23) :- up(n44), closed(sw24)."  );
			domain.addAssumption_asString( "up(n45) :- up(n44), closed(sw25)."  );
			domain.addAssumption_asString( "up(n45) :- up(n46), not f(b45_46)."  );
			domain.addAssumption_asString( "on(l24) :- up(n45)."  );
			domain.addAssumption_asString( "up(n46) :- up(n45), not f(b45_46)."  );
			domain.addAssumption_asString( "up(n46) :- up(n41), not f(b41_46)."  );

		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		return null;
//		String p1 = "F( (((on(l2) and on(l6)) and (on(l9) and on(l12))) and ((on(l16) and on(l19)) and on(l22))) )" ;
//		String g1 =  "goalmodel { goal g1 = "+p1+". }";
//		
//		System.out.println(g1);
//		
//		GoalModel model=null;
//		LTLGoal goal = null;
//		try {
//			model = LTLGoalModelBuilder.parse(g1);
//			goal = model.getGoals().iterator().next();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return goal;
	}
	
	@Override
	public QualityAsset getQualityAsset() {
		return new SPSloadmetrics(getDomainAssumptions());
	}


	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("off(mg1).");
			w.addFact_asString("on(mg2).");
			w.addFact_asString("off(aux1).");
			w.addFact_asString("off(aux2).");
			
			w.addFact_asString("closed(sw1).");
			w.addFact_asString("open(sw2).");
			w.addFact_asString("closed(sw3).");
			w.addFact_asString("closed(sw4).");
			w.addFact_asString("closed(sw5).");
			w.addFact_asString("open(sw6).");
			w.addFact_asString("closed(sw7).");
			w.addFact_asString("closed(sw8).");
			w.addFact_asString("closed(sw9).");
			w.addFact_asString("open(sw10).");
			w.addFact_asString("open(sw11).");
			w.addFact_asString("closed(sw12).");
			w.addFact_asString("open(sw13).");
			w.addFact_asString("closed(sw14).");
			w.addFact_asString("closed(sw15).");
			w.addFact_asString("closed(sw16).");
			w.addFact_asString("closed(sw17).");
			w.addFact_asString("open(sw18).");
			w.addFact_asString("closed(sw19).");
			w.addFact_asString("closed(sw20).");
			w.addFact_asString("open(sw21).");
			w.addFact_asString("open(sw22).");
			w.addFact_asString("closed(sw23).");
			w.addFact_asString("closed(sw24).");
			w.addFact_asString("open(sw25).");

			//guasti sempre aperti
//			w.addFact_asString("f(b3_4).");
//			w.addFact_asString("f(b4_5).");
//			w.addFact_asString("f(b16_21).");
//			w.addFact_asString("f(b32_37).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}
	
	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generators*/
		//capabilities.add(generate_switch_on_generator("mg1"));
		//capabilities.add(generate_switch_off_generator("mg1"));
		capabilities.add(generate_switch_on_generator("mg2"));
		capabilities.add(generate_switch_off_generator("mg2"));
		capabilities.add(generate_switch_on_generator("aux1"));
		capabilities.add(generate_switch_off_generator("aux1"));
		capabilities.add(generate_switch_on_generator("aux2"));
		capabilities.add(generate_switch_off_generator("aux2"));
		
		capabilities.add(generate_open_capability_for_switcher("sw3"));
		capabilities.add(generate_open_capability_for_switcher("sw7"));
		capabilities.add(generate_open_capability_for_switcher("sw14"));
		capabilities.add(generate_open_capability_for_switcher("sw19"));
		capabilities.add(generate_open_capability_for_switcher("sw24"));

		capabilities.add(generate_close_capability_for_switcher("sw3"));
		capabilities.add(generate_close_capability_for_switcher("sw7"));
		capabilities.add(generate_close_capability_for_switcher("sw14"));
		capabilities.add(generate_close_capability_for_switcher("sw19"));
		capabilities.add(generate_close_capability_for_switcher("sw24"));

		capabilities.add(generate_capability_for_open_close("sw1","sw2"));
		capabilities.add(generate_capability_for_open_close("sw2","sw1"));
		capabilities.add(generate_capability_for_open_close("sw5","sw6"));
		capabilities.add(generate_capability_for_open_close("sw6","sw5"));
		capabilities.add(generate_capability_for_open_close("sw9","sw10"));
		capabilities.add(generate_capability_for_open_close("sw10","sw9"));
		capabilities.add(generate_capability_for_open_close("sw12","sw13"));
		capabilities.add(generate_capability_for_open_close("sw13","sw12"));
		capabilities.add(generate_capability_for_open_close("sw17","sw18"));
		capabilities.add(generate_capability_for_open_close("sw18","sw17"));
		capabilities.add(generate_capability_for_open_close("sw20","sw21"));
		capabilities.add(generate_capability_for_open_close("sw21","sw20"));
		capabilities.add(generate_capability_for_open_close("sw23","sw25"));
		capabilities.add(generate_capability_for_open_close("sw25","sw23"));
		
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
	
	private AbstractCapability generate_switch_on_aux2() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw15"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw15")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw15_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_switch_on_aux2_alt() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw16"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw16")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw16_generator_cap", main_on_evo, main_on_pre, null);
	}

	private AbstractCapability generate_switch_on_aux2_alt2() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw15"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw16"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw15")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw16")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw15_16_generator_cap", main_on_evo, main_on_pre, null);
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

	private AbstractCapability generate_capability_for_open_close(String switch_name1,String switch_name2) {
		Constant i_const1 = new Constant(switch_name1);
		Constant i_const2 = new Constant(switch_name2);
		
		Condition i_pre = new FOLCondition(new DLPAtom("closed", i_const1));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iOpen");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const1)) ) );
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const2)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const1))));
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const2))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name1+"close_switch_"+switch_name2+"_cap", main_on_evo, i_pre, null);
	}
	
	
	public class SPSloadmetrics extends QualityAsset {
		private AssumptionSet myAssumptions;
		
		private FOLCondition[] load_conditions=null;
		private FOLCondition[] gen_conditions=null;
		private int[] load_pow=null;
		private int[] gen_pow=null;
		
		private long max_score = -1;
		private double threshold = -1;
		
		public SPSloadmetrics(AssumptionSet myAssumptions) {
			super();
			this.myAssumptions = myAssumptions;
			
			init_metrics();
		}
		
		private void init_metrics() {
			load_conditions = new FOLCondition[24];
			
			load_conditions[0] = new FOLCondition(new DLPAtom("on",new Constant ("l2")));
			load_conditions[1] = new FOLCondition(new DLPAtom("on",new Constant ("l6")));
			load_conditions[2] = new FOLCondition(new DLPAtom("on",new Constant ("l9")));
			load_conditions[3] = new FOLCondition(new DLPAtom("on",new Constant ("l12")));
			load_conditions[4] = new FOLCondition(new DLPAtom("on",new Constant ("l16")));
			load_conditions[5] = new FOLCondition(new DLPAtom("on",new Constant ("l19")));
			load_conditions[6] = new FOLCondition(new DLPAtom("on",new Constant ("l22")));
			
			load_conditions[7] = new FOLCondition(new DLPAtom("on",new Constant ("l3")));
			load_conditions[8] = new FOLCondition(new DLPAtom("on",new Constant ("l7")));
			load_conditions[9] = new FOLCondition(new DLPAtom("on",new Constant ("l10")));	
			load_conditions[10] = new FOLCondition(new DLPAtom("on",new Constant ("l13")));
			load_conditions[11] = new FOLCondition(new DLPAtom("on",new Constant ("l17")));
			load_conditions[12] = new FOLCondition(new DLPAtom("on",new Constant ("l20")));
			load_conditions[13] = new FOLCondition(new DLPAtom("on",new Constant ("l23")));
			
			load_conditions[14] = new FOLCondition(new DLPAtom("on",new Constant ("l1")));
			load_conditions[15] = new FOLCondition(new DLPAtom("on",new Constant ("l4")));
			load_conditions[16] = new FOLCondition(new DLPAtom("on",new Constant ("l5")));		
			load_conditions[17] = new FOLCondition(new DLPAtom("on",new Constant ("l8")));
			load_conditions[18] = new FOLCondition(new DLPAtom("on",new Constant ("l11")));
			load_conditions[19] = new FOLCondition(new DLPAtom("on",new Constant ("l14")));
			load_conditions[20] = new FOLCondition(new DLPAtom("on",new Constant ("l15")));
			load_conditions[21] = new FOLCondition(new DLPAtom("on",new Constant ("l18")));
			load_conditions[22] = new FOLCondition(new DLPAtom("on",new Constant ("l21")));
			load_conditions[23] = new FOLCondition(new DLPAtom("on",new Constant ("l24")));
			
			gen_conditions = new FOLCondition[4];
			gen_conditions[0] = new FOLCondition(new DLPAtom("on",new Constant ("mg1")));
			gen_conditions[1] = new FOLCondition(new DLPAtom("on",new Constant ("mg2")));
			gen_conditions[2] = new FOLCondition(new DLPAtom("on",new Constant ("aux1")));
			gen_conditions[3] = new FOLCondition(new DLPAtom("on",new Constant ("aux2")));

			load_pow = new int[24];
			for (int i=0; i<7; i++)
				load_pow[i] = 5;
			for (int i=7; i<14; i++)
				load_pow[i] = 5;
			for (int i=14; i<24; i++)
				load_pow[i] = 5;
					
			gen_pow = new int[4];
			gen_pow[0] = 60;
			gen_pow[1] = 60;
			gen_pow[2] = 20;
			gen_pow[3] = 20;
			
		}
		
		public String getShortStateRepresentation(StateOfWorld w) {
			String value = "";
			EntailOperator entail = EntailOperator.getInstance();
			boolean[] results = entail.entailsCondition(w, myAssumptions, load_conditions);

			for (int i=0; i<24; i++) {
				if (results[i])
					value +="1";
				else
					value +="0";
			}
			return value;
		}
		

		@Override
		public long evaluate_state(StateOfWorld w) {
			long score = 0;
						
			EntailOperator entail = EntailOperator.getInstance();
			boolean[] results = entail.entailsCondition(w, myAssumptions, load_conditions);
			
			String value = "";
			for (int i=0; i<24; i++) {
				if (results[i])
					value +="1";
				else
					value +="0";
			}
			
			int score_before = Integer.parseInt(value, 2);

			boolean[] genresults = entail.entailsCondition(w, myAssumptions, gen_conditions);
			int pot_erogata = 0;
			for (int i=0; i<4; i++)
				if (genresults[i])
					pot_erogata+=gen_pow[i];
			
			//System.out.println("POT MAX: "+pot_erogata);

			String value_after = "";
			for (int i=0; i<24; i++) {
				//System.out.print(results[i]+" , ");
				if (results[i]) {
					if (load_pow[i]<pot_erogata) {
						value_after +="1";
						pot_erogata = pot_erogata-load_pow[i];
					} else {
						value_after +="0";
					}
				} else {
					value_after +="0";
				}
			}
			
			int pot_in_eccesso = pot_erogata;
			
			score = Integer.parseInt(value_after, 2);
			
			return score;
		}

		@Override
		public long max_score() {
			return 1;
//			if (max_score>0)
//				return max_score;
//			
//			String value = "";
//			for (int i=0; i<24; i++) {
//					value +="1";
//			}
//			
//			max_score = Integer.parseInt(value, 2);
//			return max_score;
		}

		@Override
		public double getThreshold_metrics() {
			if (threshold>0) 
				return threshold;
			
			String value = "";
			for (int i=0; i<17; i++) {
					value +="1";
			}
			for (int i=17; i<24; i++) {
				value +="0";
			}
			double th = Integer.parseInt(value, 2);
			threshold = th/(double) max_score;
			
			return threshold;
		}

		@Override
		public void log_state(AssumptionSet assumptions, StateOfWorld w) {
			
//				for (int i=0; i<46; i++) {
//					FOLCondition cond = new FOLCondition(new DLPAtom("up",new Constant ("n"+(i+1))));
//					EntailOperator test1 = EntailOperator.getInstance();
//					boolean b1= test1.entailsCondition(w, assumptions, cond);
//					if (b1==false)
//						System.out.println("Node"+(i+1)+" is down");
//				}
			
			for (int i=0; i<24; i++) {
				FOLCondition cond = new FOLCondition(new DLPAtom("on",new Constant ("l"+(i+1))));
				EntailOperator test1 = EntailOperator.getInstance();
				boolean b1= test1.entailsCondition(w, assumptions, cond);
				if (b1==false)
					System.out.println("Load"+(i+1)+" is down");
			}
			
			EntailOperator entail = EntailOperator.getInstance();
			boolean[] results = entail.entailsCondition(w, myAssumptions, load_conditions);
			
			String value = "";
			for (int i=0; i<24; i++) {
				if (results[i])
					value +="1";
				else
					value +="0";
			}
			
			int score_before = Integer.parseInt(value, 2);
			System.out.println("score without power balance: "+score_before);
		}
		
	}

}
