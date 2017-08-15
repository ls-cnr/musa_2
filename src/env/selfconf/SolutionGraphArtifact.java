package selfconf;

import cartago.Artifact;
import cartago.LINK;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import layer.semantic.StateOfWorld;
import pmr.SolutionGraph;
import pmr.probexp.ExpansionNode;
import translator.JasonExpansionNode;
import translator.JasonStateOfWorld;
import translator.TranslateError;

public class SolutionGraphArtifact extends Artifact {

	private SolutionGraph graph = new SolutionGraph();

	@LINK
	void set_initial_state(String node_string) {
		try {
			@SuppressWarnings("unused")
			StateOfWorld w = JasonStateOfWorld.term_string_to_object(node_string);
			// TODO add the initial state to the graph
		} catch (TranslateError e1) {
			e1.printStackTrace();
		}
	}

	@LINK
	void get_node_number(OpFeedbackParam<Integer> num) {
		int n = graph.getWTSHashmap().size();
		num.set(new Integer(n));
	}

	/* interface: EXPAND */
	@LINK
	void expand(String expansion_node, String spec_id_string) {
		ExpansionNode exp;
		try {
			exp = JasonExpansionNode.term_string_to_object(expansion_node);
		} catch (TranslateError t) {
			return;
		}
		graph.addNode(exp);
		System.out.println("Added a node. The graph now contains " + this.graph.getWTSHashmap().size() + "nodes");
	}

	/* interface: VISIT */
	@OPERATION
	void getStartState() {
		// TODO
	}

	@OPERATION
	void getState_ById() {
		// TODO
	}

	@OPERATION
	void getAllTransitions_FromState() {
		// TODO
	}

	@OPERATION
	void getAllTransitions_ToState() {
		// TODO
	}

	@OPERATION
	void pickConjointTransition_FromState_ByCapability() {
		// TODO
	}

	@OPERATION
	void pickConjointTransition_ToState_ByCapability() {
		// TODO
	}

	@LINK
	void printGraph() {
		this.graph.printGraph();
	}

}