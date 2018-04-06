// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;

import org.icar.musa.applications.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.pmr.problem_exploration.ProblemExploration;
import org.icar.musa.pmr.problem_exploration.ProblemSpecification;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.utils.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.utils.agent_communication.translator.JasonExtNode;
import org.icar.musa.utils.exception.ProblemDefinitionException;
import org.icar.musa.utils.exception.TranslateError;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import jason.asSyntax.Term;


//@ARTIFACT_INFO(
//		  outports = {
//		    @OUTPORT(name = "local_cap_repo")
//		  }
//	) 
public class ProblemExplorationArtifact extends Artifact {
	private String my_agent_name ="unspecified";
	
	private ProblemExploration pe;
	
	void init(String agent_name,String cap_set) {
		// a regime questi dati vanno presi dal artefatto Specification
		SPSReconfigurationEasy problem_domain = new SPSReconfigurationEasy();	
		ArrayList<AbstractCapability> capabilities=null;
		if (cap_set.equals("all")) {
			capabilities = problem_domain.getCapabilitySet();
		}
		if (cap_set.equals("set1")) {
			capabilities = problem_domain.getSubCapabilitySet1();
		}
		if (cap_set.equals("set2")) {
			capabilities = problem_domain.getSubCapabilitySet2();
		}
		if (cap_set.equals("set3")) {
			capabilities = problem_domain.getSubCapabilitySet3();
		}
		LTLGoal model = (LTLGoal) problem_domain.getRequirements();
		AssumptionSet assumptions=problem_domain.getDomainAssumptions();
		
		ProblemSpecification ps = new ProblemSpecification(assumptions,model,null);		
		try {
			pe = new ProblemExploration( ps, capabilities, agent_name);
		} catch (ProblemDefinitionException e) {
			failed("I goal devono essere specificati in LTL");
		}
		//this.debugSetInitialNode();

	}
	
	@OPERATION
	public void addToVisit(String term_string) {		
		StateNode node;
		try {
			node = (StateNode) JasonExtNode.term_string_to_object(term_string);
			if (!node.isExitNode()) {
				pe.add_new_node(node);
			}
		} catch (TranslateError t) {
			return;
		}
	}
	
	@OPERATION
	void expand_local_graph() {
		pe.generate_expansion();
	}
	
	@OPERATION
	void getMostPromisingExpansion(OpFeedbackParam<Term> expansion) {
		WTSExpansion exp = pe.getHighestExpansion();
		expansion.set( JasonExpansionNode.object_to_term(exp) );
	}
	
	@OPERATION
	void removeWinnerNode(String node){
		WTSExpansion exp;
		try {
			exp = JasonExpansionNode.term_string_to_object(node);
			this.pe.pickExpansion(exp);
		} catch (TranslateError e) {
			e.printStackTrace();
		}
		
	}
	
	@OPERATION
	void log_current_state() {
		pe.log_current_state();
	}
	
}

