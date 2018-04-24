package org.icar.musa.applications.spsreconfiguration;

import java.io.IOException;
import java.util.ArrayList;

import org.icar.musa.applications.Scenario;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

import net.sf.tweety.lp.asp.parser.ParseException;

public class SPSReconfigcirc3_std_4guasti_0 implements Scenario {
	private SPSCircuit circuito;
	private int sub_scenario = 0;
	private int mission = 2;
	
	public SPSReconfigcirc3_std_4guasti_0() {
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
		circuito.add_switcher(10, 11, "swp1",true);
		circuito.add_connection(11, 12);
		circuito.add_switcher(12, 13, "sws1");
		circuito.add_connection(13, 14);
		circuito.add_switcher(2, 15, "swaux1p");
		circuito.add_switcher(15, 16, "swaux1s",true);
		circuito.add_connection(3, 17);
		circuito.add_switcher(17, 18, "swp2",true);
		circuito.add_connection(18, 19);
		circuito.add_switcher(19, 20, "sws2");
		circuito.add_connection(20, 21);
		circuito.add_switcher(4, 22, "swp3");
		circuito.add_switcher(22, 24, "sws3",true);
		circuito.add_connection(24, 25);
		circuito.add_connection(5, 26);
		circuito.add_switcher(26, 27, "swp4",true);
		circuito.add_connection(27, 28);
		circuito.add_switcher(28, 29, "sws4");
		circuito.add_connection(29, 30);
		circuito.add_switcher(6, 31, "swaux2p");
		circuito.add_switcher(31, 32, "swaux2s",true);	
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

		if (mission==0) {
			circuito.add_load(11, 2, 5, LoadDescriptor.VITAL, 1);
			circuito.add_load(18, 6, 5, LoadDescriptor.VITAL, 2);
			circuito.add_load(22, 9, 5, LoadDescriptor.VITAL, 3);
			circuito.add_load(27, 12, 5, LoadDescriptor.VITAL, 4);
			circuito.add_load(34, 16, 5, LoadDescriptor.VITAL, 4);
			circuito.add_load(39, 19, 5, LoadDescriptor.VITAL, 6);
			circuito.add_load(43, 22, 5, LoadDescriptor.VITAL, 7);
	
			circuito.add_load(12, 3, 10, LoadDescriptor.SEMIVITAL, 1);
			circuito.add_load(19, 7, 10, LoadDescriptor.SEMIVITAL, 2);
			circuito.add_load(28, 13, 10, LoadDescriptor.SEMIVITAL, 3);
			circuito.add_load(35, 17, 10, LoadDescriptor.SEMIVITAL, 4);
			circuito.add_load(44, 23, 10, LoadDescriptor.SEMIVITAL, 5);
			
			circuito.add_load(10, 1, 5, LoadDescriptor.NONVITAL, 1);
			circuito.add_load(13, 4, 5, LoadDescriptor.NONVITAL, 2);
			circuito.add_load(17, 5, 5, LoadDescriptor.NONVITAL, 3);
			circuito.add_load(20, 8, 5, LoadDescriptor.NONVITAL, 4);
			circuito.add_load(26, 11, 5, LoadDescriptor.NONVITAL, 5);
			circuito.add_load(29, 14, 5, LoadDescriptor.NONVITAL, 6);
			circuito.add_load(33, 15, 5, LoadDescriptor.NONVITAL, 7,true);
			circuito.add_load(36, 18, 5, LoadDescriptor.NONVITAL, 8,true);
			circuito.add_load(42, 21, 5, LoadDescriptor.NONVITAL, 9,true);
			circuito.add_load(45, 24, 5, LoadDescriptor.NONVITAL, 10,true);
		}
		
		if (mission==2) {
			circuito.add_load(45, 24, 5, LoadDescriptor.VITAL, 1);
			circuito.add_load(42, 21, 5, LoadDescriptor.VITAL, 2);
			circuito.add_load(39, 19, 5, LoadDescriptor.VITAL, 3);
			circuito.add_load(36, 18, 5, LoadDescriptor.VITAL, 4);
			circuito.add_load(33, 15, 5, LoadDescriptor.VITAL, 5);
			circuito.add_load(29, 14, 5, LoadDescriptor.VITAL, 6);
			circuito.add_load(26, 11, 5, LoadDescriptor.VITAL, 7);
			
			circuito.add_load(43, 22, 5, LoadDescriptor.SEMIVITAL, 8);
			circuito.add_load(34, 16, 5, LoadDescriptor.SEMIVITAL, 9);
			circuito.add_load(27, 12, 5, LoadDescriptor.SEMIVITAL, 10);
			circuito.add_load(19, 7, 10, LoadDescriptor.SEMIVITAL, 11);
			circuito.add_load(18, 6, 5, LoadDescriptor.SEMIVITAL, 12);
			circuito.add_load(12, 3, 10, LoadDescriptor.SEMIVITAL, 13);
			circuito.add_load(11, 2, 5, LoadDescriptor.SEMIVITAL, 14);
			circuito.add_load(22, 9, 5, LoadDescriptor.SEMIVITAL, 15);
		
			circuito.add_load(44, 23, 10, LoadDescriptor.NONVITAL, 16);
			circuito.add_load(35, 17, 10, LoadDescriptor.NONVITAL, 17);
			circuito.add_load(28, 13, 10, LoadDescriptor.NONVITAL, 18);
			circuito.add_load(20, 8, 5, LoadDescriptor.NONVITAL, 19,true);
			circuito.add_load(17, 5, 5, LoadDescriptor.NONVITAL, 20);
			circuito.add_load(13, 4, 5, LoadDescriptor.NONVITAL, 21,true);			
			circuito.add_load(10, 1, 5, LoadDescriptor.NONVITAL, 22,true);
		}
		
		if (sub_scenario==1)
			circuito.add_generator(24, "mg1", 60,true);
		else
			circuito.add_generator(24, "mg1", 60);
		circuito.add_generator(38, "mg2", 60);
		circuito.add_generator(31, "aux2", 20,true);
		circuito.add_generator(15, "aux1", 20,true);
		
		
		circuito.add_failure(3, 4);
		circuito.add_failure(4, 5);
		circuito.add_failure(16, 21);
		circuito.add_failure(32, 37);
	}

	@Override
	public String getName() {
		return "circ3-std-4guasti-0";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		return circuito.getAssumptions();
	}

	@Override
	public Requirements getRequirements() {
//		return null;
		String p1 = "F( (((on(l11) and on(l14)) and (on(l15) and on(l18))) and ((on(l19) and on(l21)) and on(l24))) )" ;
//		String p1 = "F( (((on(l2) and on(l6)) and (on(l9) and on(l12))) and ((on(l16) and on(l19)) and on(l22))) )" ;
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
		return circuito.getInitialState();
	}
	
	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generators*/
		if (sub_scenario == 0) {
			capabilities.add(circuito.generate_switch_on_generator("mg1"));
			capabilities.add(circuito.generate_switch_off_generator("mg1"));
		}
		capabilities.add(circuito.generate_switch_on_generator("mg2"));
		capabilities.add(circuito.generate_switch_off_generator("mg2"));
		capabilities.add(circuito.generate_switch_on_generator("aux1"));
		capabilities.add(circuito.generate_switch_off_generator("aux1"));
		capabilities.add(circuito.generate_switch_on_generator("aux2"));
		capabilities.add(circuito.generate_switch_off_generator("aux2"));

		for (int i=1; i<= 24; i++) {
			capabilities.add(circuito.generate_open_capability_for_switcher("sw_"+i));
			capabilities.add(circuito.generate_close_capability_for_switcher("sw_"+i));
		}

		for (int i=1; i<= 7; i++) {
			capabilities.add(circuito.generate_capability_for_open_close("swp"+i,"sws"+i));
		}

		capabilities.add(circuito.generate_capability_for_open_close("swaux1p","swaux1s"));
		capabilities.add(circuito.generate_capability_for_open_close("swaux2p","swaux2s"));

		return capabilities;
	}
	

	// test
	public FOLCondition[] getLoadConditions() {
		return circuito.getLoadConditions();
	}
	
	public class SPSloadmetrics extends QualityAsset {
		private SPSCircuit circuito;
				
		public SPSloadmetrics(SPSCircuit circuito) {
			super();
			this.circuito = circuito;
			init_metrics();
		}
		
		private void init_metrics() {

		}
		
		public String getShortStateRepresentation(StateOfWorld w) {
			return circuito.getShortStateRepresentation(w);
		}
		

		@Override
		public long evaluate_state(StateOfWorld w) {
			return circuito.evaluate_state(w);
		}

		@Override
		public long max_score() {
			return 1;
		}

		@Override
		public double getThreshold_metrics() {
			return 0;
		}

		@Override
		public void log_state(AssumptionSet assumptions, StateOfWorld w) {
			circuito.log_state(w);
		}
		
	}


	public void log_graph() {
		circuito.log_graph();
	}

	public void log_current_state_graph(AssumptionSet assumptions, StateOfWorld w) {
		circuito.log_current_state_graph(assumptions, w);
	}

}
