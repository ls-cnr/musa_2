package domain.spsreconfiguration;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datalayer.awareness.AbstractCapability;
import datalayer.awareness.AssumptionSet;
import datalayer.awareness.ProblemSpecification;
import datalayer.awareness.Requirements;
import datalayer.awareness.LTL.formulamodel.LTLGoal;
import datalayer.awareness.LTL.net.PNHierarchy;
import datalayer.awareness.LTL.net.TokensConfiguration;
import datalayer.world.StateOfWorld;
import datalayer.world.wts.WorldNode;
import exception.ProblemDefinitionException;
import jason.stdlib.perceive;
import reasoner.SolutionGraph;
import reasoner.probexp.ExtendedNode;
import reasoner.probexp.GraphExpansion;
import reasoner.probexp.ProblemExploration;

public class SPSREasyTest {

	private AssumptionSet assumptions;
	private Requirements requirements;
	private ArrayList<AbstractCapability> allCap;
	private ArrayList<AbstractCapability> capSet1;
	private ArrayList<AbstractCapability> capSet2;
	private ArrayList<AbstractCapability> capSet3;
	private ProblemSpecification ps;
	private StateOfWorld first;
	
	
	@Before
	public void init() {
		SPSReconfigurationEasy s = new SPSReconfigurationEasy();
		assumptions = s.getDomainAssumptions();
		requirements = s.getRequirements();
		allCap = s.getCapabilitySet();
		capSet1 = s.getSubCapabilitySet1();
		capSet2 = s.getSubCapabilitySet2();
		capSet3 = s.getSubCapabilitySet3();
		first = s.getInitialState();
		
		ps = new ProblemSpecification(assumptions, requirements, null);
		
	}
	
	@Test
	public void test() {
		SolutionGraph graph = new SolutionGraph();
		ProblemExploration pe = null;
		try {
			pe = new ProblemExploration(ps, allCap);
		} catch (ProblemDefinitionException e) {
			System.out.println("I goal devono essere specificati in LTL");
		}
		pe.addToVisit(new WorldNode(first), new TokensConfiguration(new PNHierarchy((LTLGoal) requirements)), 10);
		
		int k = 0;
		// simula il ciclo di espansione di un WTS
		while( !pe.terminated() && k++ < 100) {
			//System.out.println("ciclo "+k);
			pe.expandNode();
			pe.log_current_state();
			
			
			GraphExpansion exp = pe.getHighestExpansion();
			pe.removeExpandedNode(exp);
			
			//System.out.println("expand "+exp.getCapability());
			
			//update graph
			graph.addNode(exp);
			
			//update problem exploration with new nodes
			for(ExtendedNode node : exp.getDestination()){
				//System.out.println("new "+node.getWorldState());
				pe.addToVisit(new WorldNode(node.getWorldState()), node.getTokens(), node.getScore() );
			}
		}
		
		graph.printForGraphviz();
	}

}
