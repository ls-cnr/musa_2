package org.icar.musa.applications.spsreconfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.applications.Scenario;
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
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet;
import org.icar.musa.utils.persistence.entity.DomainAssumption;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.model.GoalModel;
import org.icar.specification.LTLgoal.model.LTLGoal;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPSReconfigCircuito3 implements Scenario {
	private SPSCircuit circuito;
	
	public SPSReconfigCircuito3() {
		super();
		init_experiment();
	}

	private void init_experiment() {
		circuito = new SPSCircuit();
		circuito.add_connection(1, 2);
		circuito.add_connection(2, 3);
		circuito.add_connection(3, 4);
		circuito.add_connection(4, 5);
		circuito.add_connection(5, 6);
		circuito.add_connection(6, 7);
		circuito.add_connection(7, 8);
		circuito.add_connection(8, 9);
		
		circuito.add_connection(1, 10);
		circuito.add_switcher(10, 11, "swp1");
		circuito.add_connection(11, 12);
		circuito.add_switcher(12, 13, "sws1",true);
		circuito.add_connection(13, 14);
		circuito.add_switcher(2, 15, "swaux1p");
		circuito.add_switcher(15, 16, "swaux1s");
		circuito.add_connection(3, 17);
		circuito.add_switcher(17, 18, "swp2");
		circuito.add_connection(18, 19);
		circuito.add_switcher(19, 20, "sws2",true);
		circuito.add_connection(20, 21);
		circuito.add_switcher(4, 22, "swp3");
		circuito.add_switcher(22, 24, "sws3",true);
		circuito.add_connection(24, 25);
		circuito.add_connection(5, 26);
		circuito.add_switcher(26, 27, "swp4");
		circuito.add_connection(27, 28);
		circuito.add_switcher(28, 29, "sws4",true);
		circuito.add_connection(29, 30);
		circuito.add_switcher(6, 31, "swaux2p");
		circuito.add_switcher(31, 32, "swaux2s");	
		circuito.add_connection(7, 33);
		circuito.add_switcher(33, 34, "swp5");
		circuito.add_connection(34, 35);
		circuito.add_switcher(35, 36, "sws5",true);
		circuito.add_connection(36, 37);
		circuito.add_connection(8, 38);
		circuito.add_switcher(38, 39, "swp6");
		circuito.add_switcher(39, 41, "sws6",true);		
		circuito.add_connection(9, 42);
		circuito.add_switcher(42, 43, "swp7");
		circuito.add_connection(43, 44);
		circuito.add_switcher(44, 45, "sws7",true);
		circuito.add_connection(45, 46);
		circuito.add_connection(14, 16);
		circuito.add_connection(16, 21);
		circuito.add_connection(21, 25);
		circuito.add_connection(25, 30);
		circuito.add_connection(30, 32);
		circuito.add_connection(32, 37);
		circuito.add_connection(37, 41);
		circuito.add_connection(41, 46);

		circuito.add_load(11, 2, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(18, 6, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(22, 9, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(27, 12, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(34, 16, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(39, 19, 5, LoadDescriptor.VITAL, 1);
		circuito.add_load(43, 22, 5, LoadDescriptor.VITAL, 1);

		circuito.add_load(12, 3, 10, LoadDescriptor.SEMIVITAL, 1);
		circuito.add_load(19, 7, 10, LoadDescriptor.SEMIVITAL, 1);
		circuito.add_load(28, 13, 10, LoadDescriptor.SEMIVITAL, 1);
		circuito.add_load(35, 17, 10, LoadDescriptor.SEMIVITAL, 1);
		circuito.add_load(44, 23, 10, LoadDescriptor.SEMIVITAL, 1);
		
		circuito.add_load(10, 1, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(13, 4, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(17, 5, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(13, 8, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(26, 11, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(29, 14, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(33, 15, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(36, 18, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(42, 21, 5, LoadDescriptor.NONVITAL, 1);
		circuito.add_load(45, 24, 5, LoadDescriptor.NONVITAL, 1);
		
		circuito.add_generator(15, "aux1", 20,true);
		circuito.add_generator(24, "mg1", 60,true);
		circuito.add_generator(31, "aux2", 20,true);
		circuito.add_generator(38, "mg2", 60);
	}

	@Override
	public String getName() {
		return "Circuito Logico Esteso con Failures";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		try {
			return circuito.getAssumptions();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAllowedInAnAssumptionSet e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Requirements getRequirements() {
		//return null;
		String p1 = "F( (((on(l2) and on(l6)) and (on(l9) and on(l12))) and ((on(l16) and on(l19)) and on(l22))) )" ;
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
	public QualityAsset getQualityAsset() {
		return new SPSloadmetrics(circuito);
	}


	@Override
	public StateOfWorld getInitialState() {
		try {
			return circuito.getInitialState();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAllowedInAStateOfWorld e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generators*/
//		capabilities.add(generate_switch_on_generator("mg1"));
//		capabilities.add(generate_switch_off_generator("mg1"));
		capabilities.add(generate_switch_on_generator("mg2"));
		capabilities.add(generate_switch_off_generator("mg2"));
		capabilities.add(generate_switch_on_generator("aux1"));
		capabilities.add(generate_switch_off_generator("aux1"));
		capabilities.add(generate_switch_on_generator("aux2"));
		capabilities.add(generate_switch_off_generator("aux2"));

		for (int i=1; i<= 24; i++) {
			capabilities.add(generate_open_capability_for_switcher("sw_"+i));
			capabilities.add(generate_close_capability_for_switcher("sw_"+i));
		}

		for (int i=1; i<= 7; i++) {
			capabilities.add(generate_capability_for_open_close("swp"+i,"sws"+i));
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
		return new AbstractCapability("open_switch_"+switch_name1+"_close_switch_"+switch_name2+"_cap", main_on_evo, i_pre, null);
	}
	
	
	public class SPSloadmetrics extends QualityAsset {
		private SPSCircuit circuito;
		private AssumptionSet myAssumptions;
		
		private FOLCondition[] load_conditions=null;
		private FOLCondition[] gen_conditions=null;
		private int[] load_pow=null;
		private int[] gen_pow=null;
		
		private long max_score = -1;
		private double threshold = -1;
		
		public SPSloadmetrics(SPSCircuit circuito) {
			super();
			this.circuito = circuito;
			
			try {
				this.myAssumptions = circuito.getAssumptions();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotAllowedInAnAssumptionSet e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			init_metrics();
		}
		
		private void init_metrics() {
			load_conditions = circuito.getLoadConditions();			
			gen_conditions = circuito.getGenConditions();
			load_pow = circuito.getLoadPower();
			gen_pow = circuito.getGenPower();
		}
		
		public String getShortStateRepresentation(StateOfWorld w) {
			String value = "";
			EntailOperator entail = EntailOperator.getInstance();
			boolean[] results = entail.entailsCondition(w, myAssumptions, load_conditions);

			for (int i=0; i<22; i++) {
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
			for (int i=0; i<22; i++) {
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
			
			String shading = "";
			//System.out.println("POT MAX: "+pot_erogata);

			String value_after = "";
			for (int i=0; i<22; i++) {
				//System.out.print(results[i]+" , ");
				if (results[i]) {
					if (load_pow[i]<pot_erogata) {
						value_after +="1";
						pot_erogata = pot_erogata-load_pow[i];
						
						shading += "0";
					} else {
						value_after +="0";

						shading += "1";
					}
				} else {
					value_after +="0";
					
					shading += "0";
				}
			}
			
			int pot_in_eccesso = pot_erogata;
			int negativescore = Integer.parseInt(shading, 2);
			
			score = Integer.parseInt(value_after, 2)-negativescore;
			
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
			for (int i=17; i<22; i++) {
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
			EntailOperator entail = EntailOperator.getInstance();
			boolean[] genresults = entail.entailsCondition(w, myAssumptions, gen_conditions);
			boolean[] results = entail.entailsCondition(w, myAssumptions, load_conditions);
			
			int pot_erogata = 0;
			for (int i=0; i<4; i++)
				if (genresults[i])
					pot_erogata+=gen_pow[i];

			int pot_richiesta = 0;
			for (int i=0; i<22; i++) {
				//if (i!=9 & i!=19) {
//					FOLCondition cond = new FOLCondition(new DLPAtom("on",new Constant ("l"+(i+1))));
//					EntailOperator test1 = EntailOperator.getInstance();
//					boolean b1= test1.entailsCondition(w, assumptions, cond);
				if (results[i]==false)
					System.out.println("Load"+(i+1)+" is down");
				else
					pot_richiesta += load_pow[i];
					
				//}
			}
			
			String value = "";
			for (int i=0; i<22; i++) {
				if (results[i])
					value +="1";
				else
					value +="0";
			}
			
			int score_before = Integer.parseInt(value, 2);
			System.out.println("pow erogata: "+pot_erogata);
			System.out.println("pow assorbita: "+pot_richiesta);
			System.out.println("delta pow: "+(pot_erogata-pot_richiesta));
			System.out.println("score without power balance: "+score_before);
		}
		
	}

}
