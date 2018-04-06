package org.icar.musa.applications.monitoring_workflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.applications.Scenario;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.evolution.AddStatement;
import org.icar.musa.context.evolution.EvolutionScenario;
import org.icar.musa.context.evolution.RemoveAnyStatement;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.CapabilityEvolutionScenario;
import org.icar.musa.runtime_entity.Condition;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;
import org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class WakeUp implements Scenario {

	@Override
	public String getName() {
		return "monitoring the wake-up";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		AssumptionSet domain = new AssumptionSet();
		try {
			domain.addAssumption_asString("patient(john).");
			domain.addAssumption_asString("wearing(patient,bracelet).");
		} catch (ParseException | NotAllowedInAnAssumptionSet e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		String g1 =  "goalmodel { goal g1 = ( F (posture(standing) or posture(walking)) or F heartrate(problem) ).  }";
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
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		
		capabilities.add(generate_check_posture_capability());
		capabilities.add(generate_check_position_capability());
		capabilities.add(generate_check_heartrate_capability());	
		
		return capabilities;
	}

	/**
	 * capability per il check della posture del paziente
	 */
	private AbstractCapability generate_check_posture_capability() {
		/* check_posture */
		Condition pre = new FOLCondition(new DLPAtom("wearing", new Constant("patient"), new Constant("bracelet")));
		List<EvolutionScenario> evo_set = new LinkedList<>();
		
		CapabilityEvolutionScenario evo1 = new CapabilityEvolutionScenario("standing");
		evo1.addOperator(new RemoveAnyStatement("posture"));
		evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("posture", new Constant("standing"))) ) );
		evo_set.add(evo1);	
		
		CapabilityEvolutionScenario evo2 = new CapabilityEvolutionScenario("walking");
		evo2.addOperator(new RemoveAnyStatement("posture"));
		evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("posture", new Constant("walking"))) ) );
		evo_set.add(evo2);		

		CapabilityEvolutionScenario evo3 = new CapabilityEvolutionScenario("laying");
		evo3.addOperator(new RemoveAnyStatement("posture"));
		evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("posture", new Constant("laying"))) ) );
		evo_set.add(evo3);		
		
		CapabilityEvolutionScenario evo4 = new CapabilityEvolutionScenario("sitting");
		evo4.addOperator(new RemoveAnyStatement("posture"));
		evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("posture", new Constant("sitting"))) ) );
		evo_set.add(evo4);		

		return new AbstractCapability("check_posture", evo_set, pre, null);
	}

	/**
	 * capability per il check della posizione del paziente
	 */
	private AbstractCapability generate_check_position_capability() {
		/* check_posture */
		Condition pre = new FOLCondition(new DLPAtom("wearing", new Constant("patient"), new Constant("bracelet")));
		List<EvolutionScenario> evo_set = new LinkedList<>();

		CapabilityEvolutionScenario evo1 = new CapabilityEvolutionScenario("bedroom");
		evo1.addOperator(new RemoveAnyStatement("location"));
		evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("location", new Constant("bedroom"))) ) );
		evo_set.add(evo1);	
		
		CapabilityEvolutionScenario evo2 = new CapabilityEvolutionScenario("kitchen");
		evo2.addOperator(new RemoveAnyStatement("location"));
		evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("location", new Constant("kitchen"))) ) );
		evo_set.add(evo2);		

		CapabilityEvolutionScenario evo3 = new CapabilityEvolutionScenario("livingroom");
		evo3.addOperator(new RemoveAnyStatement("location"));
		evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("location", new Constant("livingroom"))) ) );
		evo_set.add(evo3);		
		
		return new AbstractCapability("check_location", evo_set, pre, null);
	}

	/**
	 * capability per il check della posizione del paziente
	 */
	private AbstractCapability generate_check_heartrate_capability() {
		/* check_posture */
		Condition pre = new FOLCondition(new DLPAtom("wearing", new Constant("patient"), new Constant("bracelet")));
		List<EvolutionScenario> evo_set = new LinkedList<>();
		CapabilityEvolutionScenario evo1 = new CapabilityEvolutionScenario("normal");
		evo1.addOperator(new RemoveAnyStatement("heartrate"));
		evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("heartrate", new Constant("normal"))) ) );
		evo_set.add(evo1);	
		
		CapabilityEvolutionScenario evo2 = new CapabilityEvolutionScenario("problem");
		evo2.addOperator(new RemoveAnyStatement("heartrate"));
		evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("heartrate", new Constant("problem"))) ) );
		evo_set.add(evo2);		
		
		return new AbstractCapability("check_heartrate", evo_set, pre, null);
	}

	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("autonomy(john,high).");
			//w.addFact_asString("personality(john,normal).");
			//w.addFact_asString("acceptance(john,high).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}

	@Override
	public QualityAsset getQualityAsset() {
		// TODO Auto-generated method stub
		return null;
	}

}
