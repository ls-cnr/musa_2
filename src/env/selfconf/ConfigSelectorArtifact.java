// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.HashMap;
import java.util.HashSet;

import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.pmr.solution.AWBuilder;
import org.icar.musa.runtime_entity.AbstractWorkflowNode;
import org.icar.musa.utils.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.utils.agent_communication.translator.JasonExtNode;
import org.icar.musa.utils.exception.TranslateError;

import cartago.*;

@ARTIFACT_INFO(outports = { @OUTPORT(name = "graph") })
public class ConfigSelectorArtifact extends Artifact {
	private AWBuilder abstract_wf_builder;
	
	HashMap<String, String> translation;
	
	private String spec_id_string;

	void init(String spec_id_string) {
		abstract_wf_builder = new AWBuilder();
		this.spec_id_string = spec_id_string;
	}

	@OPERATION
	void getSolution() {
	}
	
	@OPERATION
	void printSolutions() {
		System.out.println("SOLUTIONS");
		int i = 1;
		abstract_wf_builder.log_solutions();
	}

	@LINK @OPERATION
	void notifyFirstNode(String expNodeString) {
		try {
			StateNode node = (StateNode) JasonExtNode.term_string_to_object(expNodeString);
			abstract_wf_builder.notifyFirstNode(node);
		} catch (TranslateError e) {
			e.printStackTrace();
		}		
	}
		
	@LINK @OPERATION
	void notifyExpansion(String expNodeString) {
		WTSExpansion exp;
		try {
			exp = JasonExpansionNode.term_string_to_object(expNodeString);

			if (!exp.isMulti_expansion()) {
				StateNode src = exp.getRoot();
				StateNode dst = (StateNode) exp.getEvolutionNodes().get(0);
				CapabilityEdge edge = (CapabilityEdge) exp.getEdge(src, dst);
				abstract_wf_builder.notifyEvolutionEdge(src,dst,edge);
			} else {
				StateNode src = exp.getRoot();
				XorNode xonode = exp.getXorNode();
				xonode.setCases(exp.getEvolutionNodes().size());
				CapabilityEdge edge = (CapabilityEdge) exp.getEdge(src, xonode);
				abstract_wf_builder.notifyChoiceEdge(src,xonode,edge);

				for (WTSNode node :  exp.getEvolutionNodes()) {
					StateNode s = (StateNode) node;
					ScenarioEdge scen = (ScenarioEdge) exp.getEdge(xonode, node);
					abstract_wf_builder.notifyScenarioEdge(xonode, s,scen);
				}

			}
		} catch (TranslateError e) {
			e.printStackTrace();
		}

	}

	/* interface: MANAGE SOLUTION SEARCH */
	@OPERATION
	void runStep_SearchSolution() {
	}

	/* interface: SOLUTION */
	@OPERATION
	void getAbstractSolution() {
	}

	/* interface: BLACKBOARD */
	@OPERATION
	void markFailedService() {
	}
}
