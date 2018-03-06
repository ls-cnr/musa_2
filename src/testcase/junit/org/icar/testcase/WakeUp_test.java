package org.icar.testcase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.domain_app.monitoring_workflow.WakeUp;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.ProblemExploration;
import org.icar.musa.proactive_means_end_reasoning.SolutionGraph;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.musa.solution_extractor.Solution;
import org.icar.musa.solution_extractor.distributed.Sequences;
import org.junit.Before;
import org.junit.Test;

public class WakeUp_test {

	private AssumptionSet assumptions;
	private Requirements requirements;
	private ArrayList<AbstractCapability> allCap;
	private ProblemSpecification ps;
	private StateOfWorld first;

	@Before
	public void init() {
		WakeUp s = new WakeUp();
		assumptions = s.getDomainAssumptions();
		requirements = s.getRequirements();
		allCap = s.getCapabilitySet();
		first = s.getInitialState();
		
		ps = new ProblemSpecification(assumptions, requirements, null);
		
	}

	@Test
	public void test() {
		SolutionGraph graph = new SolutionGraph();
		Sequences sc = new Sequences();
		ProblemExploration pe = null;
		try {
			pe = new ProblemExploration(ps, allCap);
		} catch (ProblemDefinitionException e) {
			System.out.println("I goal devono essere specificati in LTL");
		}
		
		TokenConf tokens = pe.getInitialTokenConfiguration();
		pe.addToVisit(new WorldNode(first), tokens, 1);
		
		int k = 0;
		// simula il ciclo di espansione di un WTS
		
		while( !pe.terminated() && k++ < 10) {
			pe.expandNode();
			GraphExpansion exp = pe.getHighestExpansion();
			pe.removeExpandedNode(exp);
			graph.addNode(exp);
			sc.processGraphExpansion(exp);
			
			//update problem exploration with new nodes
			for(ExtendedNode node : exp.getDestination()){
				if (!node.isExitNode())
					pe.addToVisit(new WorldNode(node.getWorldState()), node.getTokens(), node.getScore() );
			}
		}
		graph.printForGraphviz();
		int i = 1;
		for (Solution s : sc.getAbstractWorkflowSolutions() ) {
			System.out.println("Solution " + i++);
			s.print();
		}
	}

}
