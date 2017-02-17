package translator;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import layer.awareness.net.Token;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;

public class JasonENode {
	public static Term object_to_term(ENode node) {
		Structure term = new Structure("enode",4);
		Term w = JasonStateOfWorld.object_to_term(node.getWorldState());
		ListTermImpl token_list = new ListTermImpl();
		if (node.getTokens() != null) {
			for (Token t : node.getTokens()) {
				Term token_term = JasonToken.object_to_term(t);
				token_list.add(token_term);
			}			
		}
		NumberTermImpl score = new NumberTermImpl(node.getScore());
		Structure exit = null;
		if (node.isExitNode()) {
			exit = new Structure("is_exit");
		} else {
			exit = new Structure("normal");
		}
		
		term.addTerm(w);
		term.addTerm(token_list);
		term.addTerm(score);
		term.addTerm(exit);
		
		return term;
	}
	
	
	public static ENode term_to_object(Term term) {
		
		
		return null;
	}
	
	public static ENode term_string_to_object(String term_string) {
		
		
		return null;
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
			
			ENode temp = new ENode(regAndCloud2);
			
			Term term = object_to_term(temp);
			System.out.println("term: "+term);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		

	}
	
}
