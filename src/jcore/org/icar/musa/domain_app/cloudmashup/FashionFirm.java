package org.icar.musa.domain_app.cloudmashup;

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
import org.icar.musa.core.context.evolution.RemoveAnyStatement;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.CapabilityEvolutionScenario;
import org.icar.musa.domain_app.Scenario;
import org.icar.musa.exception.NotAllowedInAnAssumptionSet;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.model.GoalModel;
import org.icar.specification.LTLgoal.model.LTLGoal;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class FashionFirm implements Scenario {

	@Override
	public String getName() {
		return "fashion firm";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		AssumptionSet domain = new AssumptionSet();
		try {
			domain.addAssumption_asString("role(X) :- user(X).");
			domain.addAssumption_asString("role(X) :- storehouse_manager(X).");
			domain.addAssumption_asString("document(X) :- order(X).");
			domain.addAssumption_asString("document(X) :- invoice(X).");
			domain.addAssumption_asString("document(X) :- user_data(X).");
			domain.addAssumption_asString("document(X) :- registration_form(X).");
			domain.addAssumption_asString("order(X) :- delivery_order(X).");
			domain.addAssumption_asString("processed(X) :- accepted(X) , order(X) ,  sent(Y,Z) , delivery_order(Y) , storehouse_manager(Z).");
			domain.addAssumption_asString("processed(X) :- refused(X) , order(X) , sent(failure_order,Y) , user(Y).");
			domain.addAssumption_asString("notified(X,Y) :- uploaded_on_cloud(X) , document(X) , has_cloud_space(Y) , user(Y).");
			domain.addAssumption_asString("notified(X,Y) :- mailed_perm_link(X,Y) , document(X) , user(Y).");
		} catch (ParseException | NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		String g1 =  "goalmodel { goal g1 = F processed(doc). }";	
		
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * capability per il check della posture del paziente
	 */
	private AbstractCapability check_user() {
		/* check_posture */
		// SERVE ASSOLUTAMENTE IL PARSER PER LE CAPABILITY....
		//Condition pre1 = new FOLCondition (new Negation( new Predicate("user", new Constant("a_user"))) ); // && not registered
		Condition pre2 = new FOLCondition(new DLPAtom("user", new Constant("a_user"))); // && not registered
		List<EvolutionScenario> evo_set = new LinkedList<>();
		
		CapabilityEvolutionScenario evo1 = new CapabilityEvolutionScenario("withcloud");
		evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registered", new Constant("a_user"))) ) );
		evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("has_cloud", new Constant("a_user"))) ) );
		evo_set.add(evo1);	
		
		CapabilityEvolutionScenario evo2 = new CapabilityEvolutionScenario("withoutcloud");
		evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registered", new Constant("a_user"))) ) );
		evo_set.add(evo2);		
		
		CapabilityEvolutionScenario evo3 = new CapabilityEvolutionScenario("uknown");
		evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uknown", new Constant("a_user"))) ) );
		evo_set.add(evo3);		

		return null;//new AbstractCapability("check_user", evo_set, pre, null);
	}

	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("order(doc).");
			w.addFact_asString("available(doc).");
			w.addFact_asString("user(a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}

}
