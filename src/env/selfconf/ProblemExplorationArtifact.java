// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartago.*;
import communication.translator.ExtDLPHead;
import communication.translator.JasonExtNode;
import communication.translator.JasonExpansionNode;
import communication.translator.TranslateError;
import datalayer.awareness.AbstractCapability;
import datalayer.awareness.AssumptionSet;
import datalayer.awareness.ProblemSpecification;
import datalayer.awareness.LTL.formulamodel.LTLGoal;
import datalayer.awareness.legacy.GS_Goal;
import datalayer.awareness.legacy.goalmodel.GoalTreeModel;
import datalayer.awareness.legacy.net.Token;
import datalayer.world.Condition;
import datalayer.world.StateOfWorld;
import datalayer.world.evolution.AddStatement;
import datalayer.world.evolution.CapabilityEvolutionScenario;
import datalayer.world.evolution.EvolutionScenario;
import datalayer.world.evolution.RemoveStatement;
import datalayer.world.wts.WorldNode;
import domain.spsreconfiguration.SPSReconfigurationEasy;
import exception.ProblemDefinitionException;
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
import reasoner.probexp.ExtendedNode;
import reasoner.probexp.GraphExpansion;
import reasoner.probexp.ProblemExploration;


//@ARTIFACT_INFO(
//		  outports = {
//		    @OUTPORT(name = "local_cap_repo")
//		  }
//	) 
public class ProblemExplorationArtifact extends Artifact {
	
	private ProblemExploration pe;
	
	void init(String cap_set) {
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
			pe = new ProblemExploration( ps, capabilities);
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

