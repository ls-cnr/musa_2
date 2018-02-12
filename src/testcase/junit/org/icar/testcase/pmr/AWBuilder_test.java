package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.solution.AWBuilder;
import org.icar.musa.solution.TreeBrick;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class AWBuilder_test {
	private StateNode initial_node;
	private StateNode second_node;
	private StateNode third_node;
	private XorNode first_xor;
	private XorNode second_xor;
	private StateNode first_childnode;
	private StateNode second_childnode;
	private StateNode not_included;
	
	@Before
	public void init() throws ParseException, NotAllowedInAStateOfWorld {
		StateOfWorld initial_state = new StateOfWorld();
		StateOfWorld second_state = new StateOfWorld();
		StateOfWorld third_state = new StateOfWorld();

		initial_state.addFact_asString("on(main).");
		initial_state.addFact_asString("open(i1).");
		
		second_state.addFact_asString("off(main).");
		second_state.addFact_asString("open(i1).");
		
		third_state.addFact_asString("off(main).");
		third_state.addFact_asString("closed(i1).");
		
		initial_node = new StateNode(initial_state);
		second_node = new StateNode(second_state);
		third_node = new StateNode(third_state);
		
		first_xor = new XorNode();
		second_xor = new XorNode();
		
		StateOfWorld first_childstate = new StateOfWorld();
		StateOfWorld second_childstate = new StateOfWorld();
		
		first_childstate.addFact_asString("on(main1).");
		first_childstate.addFact_asString("open(i11).");
		
		second_childstate.addFact_asString("off(main2).");
		second_childstate.addFact_asString("open(i12).");
		
		first_childnode = new StateNode(first_childstate);
		second_childnode = new StateNode(second_childstate);
		
		StateOfWorld not_state = new StateOfWorld();
		first_childstate.addFact_asString("necer(main1).");
		not_included = new StateNode(not_state);
	}

	@Ignore
	@Test
	public void test() {
		AWBuilder builder = new AWBuilder();
		
		builder.addFirstNode(initial_node);
		builder.addEvolutionEdge(initial_node, second_node, new CapabilityEdge());
		builder.addEvolutionEdge(second_node, third_node, new CapabilityEdge());
		
		assertTrue(builder.size()==1);
		
		builder.addChoiceEdge(third_node, first_xor, new CapabilityEdge() );
		builder.addScenarioEdge(first_xor, first_childnode, new ScenarioEdge("first"));
		builder.addScenarioEdge(first_xor, second_childnode, new ScenarioEdge("second"));
		
		assertTrue(builder.size()==1);
		
	}

	@Ignore
	@Test
	public void test2() {
		TreeBrick brick = new TreeBrick(initial_node);
		brick.appendSequence(initial_node, first_xor);
		brick.appendSequence(first_xor, first_childnode);
		brick.appendSequence(first_xor, second_childnode);
		brick.appendSequence(second_childnode, second_xor);
		brick.appendSequence(second_xor, second_node);
		brick.appendSequence(second_xor, third_node);
		
		assertTrue(!brick.appendSequence(initial_node, second_childnode));
		
		brick.log(0);
		
		TreeBrick brick2 = brick.clone_if_attach(initial_node, second_childnode);
		
		brick2.log(0);
	}

	@Test
	public void test3() {
		TreeBrick brick = new TreeBrick(initial_node);
		brick.appendSequence(initial_node, first_xor);
		brick.appendSequence(first_xor, first_childnode);
		brick.appendSequence(first_xor, second_childnode);
		brick.appendSequence(second_childnode, second_xor);
		brick.appendSequence(second_xor, second_node);
		brick.appendSequence(second_xor, third_node);
		
		brick.log(0);
				
		TreeBrick brick2 = brick.clone_if_attach(second_childnode, third_node);
		
		brick2.log(0);
		
		TreeBrick brick3 = brick.clone_if_attach(second_childnode, not_included);
		brick3.log(0);
		assertTrue(brick3 == null);
		
	}
}
