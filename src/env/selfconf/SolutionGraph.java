// CArtAgO artifact code for project musa_2_0

package selfconf;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import cartago.*;
import pmr.graph.EvolutionEdge;
import pmr.graph.StateOfWorldNode;

public class SolutionGraph extends Artifact {
	
	private DirectedGraph<StateOfWorldNode,EvolutionEdge> graph;
	
	void init() {
		// initialize graph
		graph = new DefaultDirectedGraph(EvolutionEdge.class);
	}
	

	/* interface: EXPAND */
	@LINK
	void addState() {
	}

	@LINK
	// this operation adds 'conjunct' evolutions (edges+arrival states)
	void addTransition() {
	}

	private void checkStateExists() {
	}

	
	
	/* interface: VISIT */
	@LINK
	void getStartState() {
	}

	@LINK
	void getState_ById() {
	}
	
	@LINK
	void getAllTransitions_FromState() {
	}
	
	@LINK
	void getAllTransitions_ToState() {
	}
	
	@LINK
	void pickConjointTransition_FromState_ByCapability() {
	}
	
	@LINK
	void pickConjointTransition_ToState_ByCapability() {
	}

}

