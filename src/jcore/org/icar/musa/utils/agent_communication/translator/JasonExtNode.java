package org.icar.musa.utils.agent_communication.translator;

import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.utils.exception.TranslateError;

import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;

public class JasonExtNode {
	
	public static Term object_to_term(WTSNode node) {
		if (node instanceof StateNode)
			return statenode_to_term((StateNode)node);
		if (node instanceof XorNode)
			return xornode_to_term((XorNode)node);
		
		return null;
	}
	
	
	private static Term xornode_to_term(XorNode node) {
		return new Structure("xor",0);
	}


	private static Term statenode_to_term(StateNode node) {
		Structure term = new Structure("node",4);
		Term w = JasonStateOfWorld.object_to_term(node.getState());
		Term tokens;
		if (node.getTokens()==null) {
			tokens = new Structure("start_tokens",0);
		} else {
			tokens = JasonTokensConfiguration.object_to_term(node.getTokens());
		}
		NumberTermImpl score = new NumberTermImpl(node.getGoal_satisfaction_degree());
		StringTermImpl exit = null;
		if (node.isExitNode()) {
			exit = new StringTermImpl("is_exit");
		} else if( node.isForbidden()) {
			exit = new StringTermImpl("is_error");
		} else {
			exit = new StringTermImpl("normal");
		}
		
		term.addTerm(w);
		term.addTerm(tokens);
		term.addTerm(score);
		term.addTerm(exit);
		
		return term;
	}


	public static WTSNode term_to_object(Term term) throws TranslateError {
		
		if (!term.isStructure()) {
			throw new TranslateError();
		} 
		
		Structure s = (Structure) term;
		if (s.getFunctor().equals("xor"))
			return new XorNode();
			
		if (s.getFunctor().equals("node")) {
			StateOfWorld w = JasonStateOfWorld.term_to_object(s.getTerm(0));
			TokenConf tokens = JasonTokensConfiguration.term_to_object(s.getTerm(1));
			NumberTermImpl score =(NumberTermImpl) s.getTerm(2);
			boolean isExit=false;
			boolean isError=false;
			StringTermImpl exitCheck = (StringTermImpl) s.getTerm(3);
			String check = exitCheck.getString();
			if(check.equals(new String("is_exit")) == true)	isExit = true;
			else if(check.equals(new String("is_error")) == true)	isError = true;
			StateNode state_node = new StateNode(w);
			state_node.setGoal_satisfaction_degree(score.solve());
			state_node.setExitNode(isExit);
			state_node.setForbidden(isError);
			return state_node;
		}
		
		return null;
	}
	
	public static WTSNode term_string_to_object(String term_string) throws TranslateError{
		Structure t = Structure.parse(term_string);
		return term_to_object(t);
	}

	public static void main(String[] args) {
		StateOfWorld regAndCloud2 = new StateOfWorld();
		try {
			regAndCloud2.addFact_asString("order(an_order).");
			regAndCloud2.addFact_asString("available(an_order).");
			regAndCloud2.addFact_asString("user(a_user).");
			regAndCloud2.addFact_asString("logged(a_user).");
			regAndCloud2.addFact_asString("registered(a_user).");
			regAndCloud2.addFact_asString("has_cloud_space(a_user).");
			
			StateNode temp = new StateNode(regAndCloud2);
			
			Term term = object_to_term(temp);
			System.out.println("term: "+term);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		

	}
	
}
