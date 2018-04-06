package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.icar.linear_temporal_logic.hierarchical_ltl_model.NetHierarchy;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.PredicateNode;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.AndOperator;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.FinallyPN;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.GloballyPN;
import org.icar.linear_temporal_logic.ltl_supervisor.NetSupervisor;
import org.icar.linear_temporal_logic.ltl_supervisor.TokenConf;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.junit.Before;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class WTS_test {

	private NetHierarchy net_hierarchy;
	private NetSupervisor supervisor;
	private StateOfWorld initial_state;
	private StateOfWorld second_state;
	private StateOfWorld third_state;
	
	@Before
	public void init_petrinet() {
		PredicateNode pn1 = new PredicateNode("pn1", new FOLCondition("lit(a)"));
		GloballyPN glob = new GloballyPN("glob1", pn1);
		
		PredicateNode pn2 = new PredicateNode("pn2", new FOLCondition("lit(b)"));
		FinallyPN fin = new FinallyPN("fin1", pn2);
		
		AndOperator root = new AndOperator("root", glob, fin);
		root.init();
		
		net_hierarchy = new NetHierarchy(root);
		TokenConf tokens = net_hierarchy.getInitialTokenConfiguration();
		
		supervisor = new NetSupervisor(net_hierarchy, tokens);
	}

	@Before
	public void init_states() {
		initial_state = new StateOfWorld();
		try {
			initial_state.addFact_asString("on(main).");
			initial_state.addFact_asString("open(i1).");
			initial_state.addFact_asString("open(i2).");
			initial_state.addFact_asString("closed(i3).");
			initial_state.addFact_asString("open(i4).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		second_state = new StateOfWorld();
		try {
			second_state.addFact_asString("on(main).");
			second_state.addFact_asString("open(i1).");
			second_state.addFact_asString("open(i2).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		third_state = new StateOfWorld();
		try {
			third_state.addFact_asString("on(main).");
			third_state.addFact_asString("open(i1).");
			third_state.addFact_asString("closed(i2).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
}
	
	@Test
	public void create_WTS_by_hand() {
		StateNode node = new StateNode(initial_state);
		WTS wts = new WTS(node);
		assertTrue(wts.vertexSet().size()==1);
		
		WTSNode again_initial_node = new StateNode(initial_state);
		WTSNode test_again_node = wts.safelyAddGenericNode(again_initial_node);
		assertTrue(wts.vertexSet().size()==1);
		assertTrue(again_initial_node.equals(test_again_node));
		
		StateNode second_node = new StateNode(second_state);
		WTSNode test_second_node = wts.safelyAddGenericNode(second_node);
		CapabilityEdge edge1 = new CapabilityEdge();
		edge1.setCapabilityName("cap1");
		edge1.setAgentProvider("agent1");
		wts.addEdge(node, second_node, edge1);
		assertTrue(wts.vertexSet().size()==2);
		assertTrue(wts.edgeSet().size()==1);
		assertTrue(second_node.equals(test_second_node));
		assertTrue(wts.outgoingEdgesOf(node).size()==1);
		assertTrue(wts.incomingEdgesOf(second_node).size()==1);
	}
	
	@Test
	public void wts_expansion_comparator() {
		StateNode node = new StateNode(initial_state);
		StateNode second_node = new StateNode(second_state);
		
		WTSExpansion exp = new WTSExpansion("cap1", node);
		exp.safelyAddGenericNode(second_node);
		CapabilityEdge edge1 = new CapabilityEdge();
		edge1.setCapabilityName("cap1");
		edge1.setAgentProvider("agent1");
		exp.addEdge(node, second_node, edge1);
		exp.setScore(5);		
		assertTrue(exp.getEvolutionNodes().size()==1);
		
		WTSExpansion exp2 = new WTSExpansion("cap1", node);
		exp2.safelyAddGenericNode(second_node);
		CapabilityEdge edge2 = new CapabilityEdge();
		edge2.setCapabilityName("cap1");
		edge2.setAgentProvider("agent1");
		exp2.addEdge(node, second_node, edge2);
		exp2.setScore(5);	
		assertTrue(exp.equals(exp2));
		
		exp2.setScore(6);
		Comparator comp = WTSExpansion.getScoreComparator();
		assertTrue(comp.compare(exp2, exp)>0);
	}
	
	@Test
	public void wts_update_via_expansion() {
		StateNode node = new StateNode(initial_state);
		WTS wts = new WTS(node);

		StateNode first_node = new StateNode(initial_state);
		StateNode second_node = new StateNode(second_state);
		
		WTSExpansion exp = new WTSExpansion("cap1", first_node);
		exp.safelyAddGenericNode(second_node);
		CapabilityEdge edge1 = new CapabilityEdge();
		edge1.setCapabilityName("cap1");
		edge1.setAgentProvider("agent1");
		exp.addEdge(node, second_node, edge1);
		
		wts.addExpansion(exp);
		assertTrue(wts.vertexSet().size()==2);
		assertTrue(wts.edgeSet().size()==1);
		assertTrue(wts.outgoingEdgesOf(node).size()==1);
	}
	
	@Test
	public void wts_update_via_multi_expansion() {
		StateNode node = new StateNode(initial_state);
		WTS wts = new WTS(node);

		StateNode first_node = new StateNode(initial_state);
		XorNode xornode = new XorNode();
		StateNode second_node = new StateNode(second_state);
		StateNode third_node = new StateNode(third_state);
		
		
		WTSExpansion exp = new WTSExpansion("cap1", first_node);
		exp.setMulti_expansion(true);

		exp.safelyAddGenericNode(xornode);
		CapabilityEdge edge1 = new CapabilityEdge();
		edge1.setCapabilityName("cap1");
		edge1.setAgentProvider("agent1");
		exp.addEdge(first_node, xornode, edge1);
		
		exp.safelyAddGenericNode(second_node);
		ScenarioEdge sc1_edge = new ScenarioEdge("scen1");
		exp.addEdge(xornode, second_node, sc1_edge);

		exp.safelyAddGenericNode(third_node);
		ScenarioEdge sc2_edge = new ScenarioEdge("scen1");
		exp.addEdge(xornode, third_node, sc2_edge);

		wts.addExpansion(exp);
		assertTrue(wts.vertexSet().size()==4);
		assertTrue(wts.edgeSet().size()==3);
		assertTrue(wts.outgoingEdgesOf(node).size()==1);
		assertTrue(wts.outgoingEdgesOf(xornode).size()==2);
	}
	

}
