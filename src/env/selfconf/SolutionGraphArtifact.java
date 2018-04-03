// CArtAgO artifact code for project musa_2_0

package selfconf;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.utils.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.utils.agent_communication.translator.JasonExtNode;
import org.icar.musa.utils.agent_communication.translator.JasonStateOfWorld;
import org.icar.musa.utils.exception.TranslateError;
import org.icar.specification.LTLgoal.model.LTLGoal;

import cartago.*;
import jason.asSyntax.Term;

public class SolutionGraphArtifact extends Artifact {
	
	private WTS graph;
	
	void init() {
		// initialize graph
		graph = null;
		
		System.out.println("creato artefatto solution graph");
	}
	

	@LINK
	void set_initial_state(String node_string) {
		System.out.println("Stato Iniziale: "+node_string);
		
		try {
			StateOfWorld w = JasonStateOfWorld.term_string_to_object(node_string);
			StateNode root = new StateNode(w);
			graph = new WTS(root);
		} catch (TranslateError e1) {
			e1.printStackTrace();
		}
	}

	@LINK
	void get_node_number(OpFeedbackParam<Integer> num) {
		int n = graph.vertexSet().size(); //getWTSHashmap().size();
		num.set(new Integer(n));
	}
	
	/* interface: EXPAND */
	@LINK
	void expand(String expansion_node, String spec_id_string) {
		WTSExpansion exp;
		try{
		exp = JasonExpansionNode.term_string_to_object(expansion_node);
		}catch(TranslateError t){return;}
		graph.addExpansion(exp);
		//System.out.println("Ho aggiunto un nodo al grafo. Il grafo coniente ora " + this.graph.getWTSHashmap().size() + " nodi.");
	}

	/* interface: VISIT */
	@OPERATION
	void getStartState() {
		//
	}

	@OPERATION
	void getState_ById() {
		//
	}
	
	@OPERATION
	void getAllTransitions_FromState() {
	}
	
	@OPERATION
	void getAllTransitions_ToState() {
	}
	
	@OPERATION
	void pickConjointTransition_FromState_ByCapability() {
	}
	
	@OPERATION
	void pickConjointTransition_ToState_ByCapability() {
	}
	
	@LINK
	void printGraph(){
		this.graph.printForGraphviz();
	}

}

