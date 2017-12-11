package translator;

import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import layer.awareness.LTL.net.TokensConfiguration;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.probexp.ENode;

public class JasonENode {
	
	public static Term object_to_term(ENode node) {
		Structure term = new Structure("enode",4);
		Term w = JasonStateOfWorld.object_to_term(node.getWorldState());
		Term tokens = JasonTokensConfiguration.object_to_term(node.getTokens());
		NumberTermImpl score = new NumberTermImpl(node.getScore());
		StringTermImpl exit = null;
		if (node.isExitNode()) {
			exit = new StringTermImpl("is_exit");
		} else if( node.isErrorNode()) {
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
	
	
	public static ENode term_to_object(Term term) throws TranslateError {
		ENode res = null;
		StateOfWorld w = null;
		Boolean isExit = false;
		Boolean isError = false;
		int score;
		TokensConfiguration tokens = null;
		
		if (!term.isStructure()) {
			throw new TranslateError();
		} else {
			
			Structure s = (Structure) term;
			
			try{
				w = JasonStateOfWorld.term_to_object(s.getTerm(0));
			}catch(TranslateError e1){ throw new TranslateError();}
			
			try{
				tokens = JasonTokensConfiguration.term_to_object(s.getTerm(1));
			}catch(TranslateError e1){ throw new TranslateError();}
			
			
			NumberTermImpl number =(NumberTermImpl) s.getTerm(2);
			score = (int)number.solve();
			
			StringTermImpl exitCheck = (StringTermImpl) s.getTerm(3);
			String check = exitCheck.getString();
			if(check.equals(new String("is_exit")) == true)	isExit = true;
			else if(check.equals(new String("is_error")) == true)	isError = true;
		}
		
		res = new ENode(w, tokens, score, isExit, isError);
		return res;
	}
	
	public static ENode term_string_to_object(String term_string) throws TranslateError{
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
