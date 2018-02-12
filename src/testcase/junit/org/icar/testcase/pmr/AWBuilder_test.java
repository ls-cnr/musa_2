package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.solution.AWBuilder;
import org.icar.musa.solution.TreeBrick;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class AWBuilder_test {
	private StateNode nodeA;
	private StateNode nodeB;
	private StateNode nodeC;
	private XorNode first_xor;
	private XorNode second_xor;
	private StateNode nodeD;
	private StateNode nodeE;
	private StateNode not_included;
	
	@Before
	public void init() throws ParseException, NotAllowedInAStateOfWorld {
		StateOfWorld a = new StateOfWorld();
		StateOfWorld b = new StateOfWorld();
		StateOfWorld c = new StateOfWorld();

		a.addFact_asString("p(a).");
		
		b.addFact_asString("p(b).");
		
		c.addFact_asString("p(c).");
		
		nodeA = new StateNode(a);
		nodeB = new StateNode(b);
		nodeC = new StateNode(c);
		
		first_xor = new XorNode();
		second_xor = new XorNode();
		
		StateOfWorld d = new StateOfWorld();
		StateOfWorld e = new StateOfWorld();
		
		d.addFact_asString("p(d).");
		
		e.addFact_asString("p(e).");
		e.addFact_asString("open(i12).");
		
		nodeD = new StateNode(d);
		nodeE = new StateNode(e);
		
		StateOfWorld not_state = new StateOfWorld();
		d.addFact_asString("never(p).");
		not_included = new StateNode(not_state);
	}

	@Test
	public void test_sequenza() {
		AWBuilder builder = new AWBuilder();
		
		builder.addFirstNode(nodeA);
		builder.addEvolutionEdge(nodeA, nodeB, new CapabilityEdge());
		builder.addEvolutionEdge(nodeB, nodeC, new CapabilityEdge());
		
		assertTrue(builder.size()==1);
		
		builder.addChoiceEdge(nodeC, first_xor, new CapabilityEdge() );
		builder.addScenarioEdge(first_xor, nodeD, new ScenarioEdge("first"));
		builder.addScenarioEdge(first_xor, nodeE, new ScenarioEdge("second"));
		
		assertTrue(builder.size()==1);
	}

	@Test
	public void test_albero() {
		TreeBrick brick = new TreeBrick(nodeA);
		brick.appendSequence(nodeA, first_xor);
		brick.appendSequence(first_xor, nodeB);
		brick.appendSequence(first_xor, nodeC);
		brick.appendSequence(nodeC, second_xor);
		brick.appendSequence(second_xor, nodeD);
		brick.appendSequence(second_xor, nodeE);
		brick.appendSequence(nodeB, nodeE);		
		//brick.log(0);
		
		TreeBrick brick2 = brick.clone_if_attach(nodeC, nodeE);
		//brick2.log(0);
	}
}
