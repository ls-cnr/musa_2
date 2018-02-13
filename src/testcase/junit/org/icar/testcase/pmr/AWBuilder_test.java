package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.domain.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSLocalBuilder;
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
	private StateNode nodeF;
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
		StateOfWorld f = new StateOfWorld();
		
		d.addFact_asString("p(d).");
		
		e.addFact_asString("p(e).");
		
		f.addFact_asString("p(f).");
		
		nodeD = new StateNode(d);
		nodeE = new StateNode(e);
		nodeF = new StateNode(f);
		
		StateOfWorld not_state = new StateOfWorld();
		d.addFact_asString("never(p).");
		not_included = new StateNode(not_state);
	}

	@Test
	public void test_builder() {
		AWBuilder builder = new AWBuilder();
		
		builder.notifyFirstNode(nodeA);
		builder.notifyEvolutionEdge(nodeA, nodeB, new CapabilityEdge());
		builder.notifyEvolutionEdge(nodeB, nodeC, new CapabilityEdge());
		
		assertTrue(builder.size()==1);
		
		builder.notifyChoiceEdge(nodeC, first_xor, new CapabilityEdge() );
		builder.notifyScenarioEdge(first_xor, nodeD, new ScenarioEdge("first"));
		builder.notifyScenarioEdge(first_xor, nodeE, new ScenarioEdge("second"));
		
		assertTrue(builder.size()==1);
	}

	@Test
	public void test_builder_with_loop() {
		AWBuilder builder = new AWBuilder();
		
		builder.notifyFirstNode(nodeA);
		builder.notifyEvolutionEdge(nodeA, nodeB, new CapabilityEdge());
		builder.notifyEvolutionEdge(nodeB, nodeC, new CapabilityEdge());
		builder.notifyEvolutionEdge(nodeC, nodeA, new CapabilityEdge());
		
		//builder.getBricks().get(0).log(0);
	}

	@Test
	public void test_clone_albero() {
		TreeBrick brick = new TreeBrick(nodeA);
		brick.appendSequence(nodeA, first_xor);
		brick.appendSequence(first_xor, nodeB);
		brick.appendSequence(first_xor, nodeC);
		brick.appendSequence(nodeC, second_xor);
		brick.appendSequence(second_xor, nodeD);
		brick.appendSequence(second_xor, nodeE);
		brick.appendSequence(nodeB, nodeE);		
		//brick.log(0);
		
		TreeBrick brick2 = brick.clone_if_attach(nodeC, nodeE,false);
		//brick2.log(0);
	}

	@Test
	public void test_loop_in_albero() {
		TreeBrick brick = new TreeBrick(nodeA);
		brick.appendSequence(nodeA, nodeB);
		brick.appendSequence(nodeB, first_xor);
		brick.appendSequence(first_xor, nodeC);
		brick.appendSequence(first_xor, nodeD);
		brick.appendSequence(nodeD, nodeA);
		//brick.log(0);
		
		TreeBrick brick2 = brick.clone_if_attach(nodeB, nodeE,false);
		//brick2.log(0);
	}

	@Test
	public void test_flags_in_albero() {
		TreeBrick brick = new TreeBrick(nodeA);
		brick.appendSequence(nodeA, nodeB);
		brick.appendSequence(nodeB, first_xor);
		nodeC.setExitNode(true);
		brick.appendSequence(first_xor, nodeC);
		brick.appendSequence(first_xor, nodeD);
		brick.appendSequence(nodeD, second_xor);
		brick.appendSequence(second_xor, nodeB);
		brick.appendSequence(second_xor, nodeE);
		nodeF.setExitNode(true);
		brick.appendSequence(nodeE, nodeF);
		
		brick.update_metadata();
		assertTrue( brick.leadsToExit() );
		//brick.log(0);		
	}
	
	@Test
	public void explore_SPS() throws ProblemDefinitionException {
		SPSReconfigurationEasy s = new SPSReconfigurationEasy();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, null);
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		AWBuilder aw_builder = new AWBuilder();
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		aw_builder.log_solutions();
//		for (TreeBrick tb : aw_builder.getSolutions() ) {
//			System.out.println("-----");
//			tb.log(0);
//		}
	}
}
