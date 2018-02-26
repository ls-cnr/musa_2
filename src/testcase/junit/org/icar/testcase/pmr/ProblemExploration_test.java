package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.domain.monitoring_workflow.WakeUp;
import org.icar.musa.domain.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.pmr.problem_exploration.ProblemExploration;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.specification.LTLgoal.model.LTLGoal;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProblemExploration_test {
	private AssumptionSet assumptionsSPS;
	private Requirements requirementsSPS;
	private ArrayList<AbstractCapability> allCapSPS;
	private ProblemSpecification ps_SPS;
	private StateOfWorld initial_state_SPS;

	private AssumptionSet assumptionsWU;
	private Requirements requirementsWU;
	private ArrayList<AbstractCapability> allCapWU;
	private ProblemSpecification ps_WU;
	private StateOfWorld initial_state_WU;

	@Before
	public void initSPS() {
		SPSReconfigurationEasy s = new SPSReconfigurationEasy();
		assumptionsSPS = s.getDomainAssumptions();
		requirementsSPS = s.getRequirements();
		allCapSPS = s.getCapabilitySet();
		initial_state_SPS = s.getInitialState();
		
		ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, null);	
	}

	@Before
	public void initWakeUp() {
		WakeUp s = new WakeUp();
		assumptionsWU = s.getDomainAssumptions();
		requirementsWU = s.getRequirements();
		allCapWU = s.getCapabilitySet();
		initial_state_WU = s.getInitialState();
		
		ps_WU = new ProblemSpecification(assumptionsWU, requirementsWU, null);	
	}

	@Test
	public void test() throws ProblemDefinitionException {
		ProblemExploration pe = null;
		StateNode root = new StateNode(initial_state_SPS);

		pe = new ProblemExploration(ps_SPS, allCapSPS,"myagent");	
		pe.set_start_node(root);
		pe.generate_expansion();
		
		assertTrue(pe.test_expansions_numer()==5);
		//assertTrue(pe.getHighestExpansion().getScore()==6.0);
		StateNode test_node = (StateNode) pe.getHighestExpansion().getEvolutionNodes().get(0);
		assertTrue(test_node.getState().toString().equals("[closed(i1),closed(i3),on(main),open(i2),open(i4)]"));
		
		//pe.log_current_state();		
	}

	@Test
	public void test2() throws ProblemDefinitionException {
		ProblemExploration pe = null;
		WTSExpansion exp=null;
		StateNode root = new StateNode(initial_state_SPS);

		pe = new ProblemExploration(ps_SPS, allCapSPS,"myagent");	
		pe.set_start_node(root);
		pe.generate_expansion();
		
		exp = pe.getHighestExpansion();
		pe.pickExpansion(exp);

		//pe.log_current_state();
		
		exp = pe.getHighestExpansion();
		pe.pickExpansion(exp);
		for (WTSNode node : exp.getEvolutionNodes()) {
			pe.add_new_node((StateNode) node);
		}
		
		pe.generate_expansion();
		assertTrue(pe.test_expansions_numer()==8);
		//assertTrue(pe.getHighestExpansion().getScore()==6.0);

		//pe.log_current_state();
	}
	
	@Test
	public void test3() throws ProblemDefinitionException {
		ProblemExploration pe = null;
		WTSExpansion exp=null;
		StateNode root = new StateNode(initial_state_SPS);
		
		LTLGoal mygoal = (LTLGoal) ps_SPS.getGoal_specification();
		NetHierarchyBuilder builder = new NetHierarchyBuilder();
		NetHierarchy nets = builder.build(mygoal);
		WTS wts = new WTS(root, nets);

		pe = new ProblemExploration(ps_SPS, allCapSPS,"myagent");	
		pe.set_start_node(root);

		boolean terminated = false;
		
		while (terminated==false) {
			pe.generate_expansion();
			exp = pe.getHighestExpansion();
			pe.pickExpansion(exp);
			
			if (!exp.isContain_forbidden())
				wts.addExpansion(exp);
			
			for (WTSNode node : exp.getEvolutionNodes() ) {
				StateNode n = (StateNode) node;
				if (! n.isExitNode() & !n.isForbidden() ) {
					pe.add_new_node(n);
				}
			}
			
			terminated = pe.test_termination();
		}
		assertTrue(pe.getIterationCounter()==45);
		
		//wts.printForGraphviz();
	}
	
	@Test
	public void test4() throws ProblemDefinitionException {
		int max_iteration = 10;
		ProblemExploration pe = null;
		WTSExpansion exp=null;
		StateNode root = new StateNode(initial_state_WU);
		
		LTLGoal mygoal = (LTLGoal) ps_WU.getGoal_specification();
		NetHierarchyBuilder builder = new NetHierarchyBuilder();
		NetHierarchy nets = builder.build(mygoal);
		WTS wts = new WTS(root, nets);

		pe = new ProblemExploration(ps_WU, allCapWU,"myagent");	
		pe.set_start_node(root);

		boolean terminated = false;
		
		int it_counter = 0;
		while (terminated==false & it_counter<max_iteration) {
			pe.generate_expansion();
			//pe.log_current_state();
			
			exp = pe.getHighestExpansion();
			pe.pickExpansion(exp);
			
			//System.out.println("Pick "+exp.getCapability());
			//exp.printForGraphviz();
			
			if (!exp.isContain_forbidden()) {
				wts.addExpansion(exp);
				//wts.printForGraphviz();
				
				for (WTSNode node : exp.getEvolutionNodes() ) {
					StateNode n = (StateNode) node;
					if (! n.isExitNode() ) {
						pe.add_new_node(n);
					}
				}
			}
			
			terminated = pe.test_termination();
			it_counter++;
		}
		//assertTrue(pe.getIterationCounter()==45);
		
		//wts.printForGraphviz();
	}
	
}
