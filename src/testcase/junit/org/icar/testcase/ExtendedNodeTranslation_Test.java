package org.icar.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.agent_communication.translator.JasonExtNode;
import org.icar.musa.agent_communication.translator.TranslateError;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.model.GoalModel;
import org.icar.specification.LTLgoal.model.LTLGoal;
import org.junit.Test;

import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;


public class ExtendedNodeTranslation_Test {

	@Test
	public void test_ExtendedNode_to_term() throws IOException, TranslateError {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("order(an_order).");
			w.addFact_asString("available(an_order).");
			w.addFact_asString("user(a_user).");
			w.addFact_asString("user_data(the_user_data).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		GoalModel goalModel = LTLGoalModelBuilder.parse("goalmodel { goal g1 = G on(l1). }");
		LTLGoal goal = goalModel.getGoals().iterator().next();
		NetHierarchy net = NetHierarchyBuilder.build(goal);
		TokenConf startingTokens = net.getInitialTokenConfiguration();
		//NetSupervisor s = new NetSupervisor(net, net.getInitialTokenConfiguration());
		
		ExtendedNode enode = new ExtendedNode(w, startingTokens, 0, false, false);
		enode.setExit(false);
		Term term=JasonExtNode.object_to_term(enode);
		
		ExtendedNode enode2 = JasonExtNode.term_to_object(term);
		assertTrue(enode.equals(enode2));
	}
	
	@Test
	public void test_term_to_extended_node() throws TranslateError {
		String s = "enode(w([order(an_order),available(an_order),user(a_user),user_data(the_user_data)]),tokensconfig(\"g1\",[c(\"g1\",\"start\")]),0,\"normal\")";
		ExtendedNode enode = JasonExtNode.term_string_to_object(s);
		Term term=JasonExtNode.object_to_term(enode);
		assertTrue(s.equals(term.toString()));
	}
}
