package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.utils.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.utils.agent_communication.translator.JasonExtNode;
import org.icar.musa.utils.agent_communication.translator.JasonStateOfWorld;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.utils.exception.TranslateError;
import org.junit.Test;

import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;

public class Communication_test {

	@Test
	public void test_W_to_term() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("available(doc).");
			w.addFact_asString("received(file).");
			w.addFact_asString("sent(file,user).");
			
			Term t = JasonStateOfWorld.object_to_term(w);
			
			StateOfWorld world = JasonStateOfWorld.term_to_object(t);
			
			assertTrue(w.equals(world));
		} catch (ParseException | NotAllowedInAStateOfWorld e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_term_to_W() {
		Structure w = Structure.parse("w([received(file),sent(file,user),available(doc)])");
		StateOfWorld world;
		try {
			world = JasonStateOfWorld.term_to_object(w);
			Term t = JasonStateOfWorld.object_to_term(world);

			assertTrue(w.equals(t));
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void test_WTSNode_to_term() {
		StateOfWorld regAndCloud2 = new StateOfWorld();
		try {
			regAndCloud2.addFact_asString("order(an_order).");
			regAndCloud2.addFact_asString("available(an_order).");
			regAndCloud2.addFact_asString("user(a_user).");
			regAndCloud2.addFact_asString("logged(a_user).");
			regAndCloud2.addFact_asString("registered(a_user).");
			regAndCloud2.addFact_asString("has_cloud_space(a_user).");
			
			StateNode temp = new StateNode(regAndCloud2);
			
			Term term = JasonExtNode.object_to_term(temp);
			
			StateNode temp2 = (StateNode) JasonExtNode.term_to_object(term);
			
			assertTrue(temp.equals(temp2));
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void test_Single_ExpansionNode_to_term() throws ParseException, NotAllowedInAStateOfWorld, TranslateError {
		StateOfWorld root = new StateOfWorld();
		root.addFact_asString("order(an_order).");
		root.addFact_asString("available(an_order).");
		root.addFact_asString("user(a_user).");
		root.addFact_asString("logged(a_user).");
		root.addFact_asString("registered(a_user).");
		root.addFact_asString("has_cloud_space(a_user).");
		StateNode node = new StateNode(root);
		WTSExpansion exp = new WTSExpansion("login", node);
		
		StateOfWorld m1 = new StateOfWorld();
		m1.addFact_asString("order(an_order).");
		m1.addFact_asString("available(an_order).");
		StateNode dest1 = new StateNode(m1);
		exp.addVertex(dest1);
		
		CapabilityEdge main_edge = new CapabilityEdge();
		main_edge.setCapabilityName("login");
		main_edge.setAgentProvider("myAgent");
		exp.addEdge(node, dest1,main_edge);
				
		exp.setMulti_expansion(false);
		exp.setScore(5.7);
		exp.setContain_exit(false);
		exp.setContain_forbidden(false);
		
		Term t = JasonExpansionNode.object_to_term(exp);
		WTSExpansion exp2 = JasonExpansionNode.term_to_object(t);
		
		System.out.println(exp.equals(exp2));
	}

	
	@Test
	public void test_Multi_ExpansionNode_to_term() throws ParseException, NotAllowedInAStateOfWorld, TranslateError {
		StateOfWorld root = new StateOfWorld();
		root.addFact_asString("order(an_order).");
		root.addFact_asString("available(an_order).");
		root.addFact_asString("user(a_user).");
		root.addFact_asString("logged(a_user).");
		root.addFact_asString("registered(a_user).");
		root.addFact_asString("has_cloud_space(a_user).");
		StateNode node = new StateNode(root);
		WTSExpansion exp = new WTSExpansion("login", node);
		
		XorNode xor = new XorNode();
		CapabilityEdge main_edge = new CapabilityEdge();
		main_edge.setCapabilityName("login");
		main_edge.setAgentProvider("myAgent");
		exp.addVertex(xor);
		exp.addEdge(node, xor,main_edge);
	
		StateOfWorld m1 = new StateOfWorld();
		m1.addFact_asString("order(an_order).");
		m1.addFact_asString("available(an_order).");
		StateNode dest1 = new StateNode(m1);
		exp.addVertex(dest1);
		ScenarioEdge secondary_edge = new ScenarioEdge("s1");
		exp.addEdge(xor, dest1,secondary_edge);

		StateOfWorld m2 = new StateOfWorld();
		m2.addFact_asString("user(a_user).");
		m2.addFact_asString("logged(a_user).");
		m2.addFact_asString("registered(a_user).");
		m2.addFact_asString("has_cloud_space(a_user).");
		StateNode dest2 = new StateNode(m2);
		exp.addVertex(dest2);
		ScenarioEdge secondary_edge2 = new ScenarioEdge("s2");
		exp.addEdge(xor, dest2,secondary_edge2);
				
		exp.setMulti_expansion(true);
		exp.setScore(5.7);
		exp.setContain_exit(false);
		exp.setContain_forbidden(false);
		
		Term t = JasonExpansionNode.object_to_term(exp);
		WTSExpansion exp2 = JasonExpansionNode.term_to_object(t);
		
		System.out.println(exp.equals(exp2));
	}

}
