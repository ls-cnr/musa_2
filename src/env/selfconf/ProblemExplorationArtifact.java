// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.agent_communication.translator.JasonExtNode;
import org.icar.musa.agent_communication.translator.TranslateError;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.domain.evolution.AddStatement;
import org.icar.musa.core.domain.evolution.CapabilityEvolutionScenario;
import org.icar.musa.core.domain.evolution.EvolutionScenario;
import org.icar.musa.core.domain.evolution.RemoveStatement;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.ProblemExploration;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.specification.goalspec.GS_Goal;
import org.icar.specification.goalspec.goalmodel.GoalTreeModel;
import org.icar.specification.goalspec.net.Token;
import org.icar.specification.linear_temporal_logic.formulamodel.LTLGoal;

import cartago.*;
import domain.spsreconfiguration.SPSReconfigurationEasy;
import jason.asSyntax.Term;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;


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
	public void addToVisit( String term_string ) {
		ExtendedNode node;
		try{
			node = JasonExtNode.term_string_to_object(term_string);
		}catch(TranslateError t){return;}
		if (!node.isExitNode()) {
			pe.addToVisit(new WorldNode(node.getWorldState()), node.getTokens(), node.getScore() );
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

