// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;

import org.icar.musa.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.agent_communication.translator.JasonExtNode;
import org.icar.musa.agent_communication.translator.TranslateError;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.domain_app.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.ProblemExploration;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.specification.LTLgoal.model.LTLGoal;

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
		ExtendedNode node;
		try {
			node = JasonExtNode.term_string_to_object(term_string);
			if (!node.isExitNode()) {
				pe.addToVisit(new WorldNode(node.getWorldState()), node.getTokens(), node.getScore());
			}
		} catch (TranslateError t) {
			return;
		}
	}
	
	@OPERATION
	void expand_local_graph() {
		pe.expandNode();
	}
	
	@OPERATION
	void getMostPromisingExpansion(OpFeedbackParam<Term> expansion) {
		GraphExpansion exp = pe.getHighestExpansion();
		expansion.set( JasonExpansionNode.object_to_term(exp) );
	}
	
	@OPERATION
	void removeWinnerNode(String node){
		GraphExpansion exp = null;
		try{
			exp = JasonExpansionNode.term_string_to_object(node);
			//System.out.println("rimuovo: "+node);
		}catch(TranslateError t){}
		
		this.pe.removeExpandedNode(exp);
	}
	
	@OPERATION
	void log_current_state() {
		//if (pe.isTerminated()==false)
			pe.log_current_state();
	}
	
}

