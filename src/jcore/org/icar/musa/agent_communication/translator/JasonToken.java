package communication.translator;

import java.util.ArrayList;

import datalayer.awareness.legacy.net.MultipleToken;
import datalayer.awareness.legacy.net.Token;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

public class JasonToken {

	public static Term object_to_term(Token token) {
		Structure term;
		if (token instanceof MultipleToken) {
			term = new Structure("mt",3);
		} else {
			term = new Structure("tk",3);
		}
		StringTermImpl token_name = new StringTermImpl(token.getPlaceName());
		
		Term dependee;
		if (token.getDependingToken() != null) {
			dependee = object_to_term(token.getDependingToken());
		} else {
			dependee = new Structure("no_dependee");
		}
		NumberTermImpl branch = new NumberTermImpl(token.getBranch());
		
		term.addTerm(token_name);
		term.addTerm(branch);
		term.addTerm(dependee);
		
		return term;
	}
	
	
	public static Token term_to_object(Term term) {
		Token token = null;
		if (term.isStructure()) {
			Structure ts = (Structure) term;
			if (ts.getFunctor().equals("tk")) {
				StringTermImpl token_name  = (StringTermImpl) ts.getTerm(0);
				NumberTermImpl branch = (NumberTermImpl) ts.getTerm(1);
				MultipleToken dependee = (MultipleToken) term_to_object(ts.getTerm(2));
				
				if (dependee == null) {
					token = new Token(token_name.getString());
				} else {
					token = new Token(token_name.getString(), dependee, (int) branch.solve());
				}
			} else if (ts.getFunctor().equals("mt")) {
				StringTermImpl token_name  = (StringTermImpl) ts.getTerm(0);
				NumberTermImpl branch = (NumberTermImpl) ts.getTerm(1);
				MultipleToken dependee = (MultipleToken) term_to_object(ts.getTerm(2));
				
				if (dependee == null) {
					token = new MultipleToken(token_name.getString());
				} else {
					token = new MultipleToken(token_name.getString(), dependee, (int) branch.solve());
				}
			} else {
				token = null;
			}
		}
		
		
		return token;
	}

	public static void main(String[] args) {
		test1();
		System.out.println(" ----- ");
		test2();
		System.out.println(" FINE ");
	}
	
	public static void test1() {
		ArrayList<Token> rosso = new ArrayList<>();
		MultipleToken token0 = new MultipleToken("p2");
		rosso.add( token0 );
		rosso.add(new Token("p4", token0, 0));
		MultipleToken token1 = new MultipleToken("p8");
		rosso.add( token1 );
		MultipleToken token2 = new MultipleToken("p10", token1, 0);
		rosso.add(token2);
		rosso.add(new Token("p12", token2, 0));
		rosso.add(new Token("p13", token2, 1));		
		rosso.add(new Token("p15", token1, 1));
		rosso.add(new Token("p18", token1, 1));
		
		for (Token t : rosso) {
			Term term = JasonToken.object_to_term(t);
			System.out.println("term: "+term.toString());
		}
		
	}


	public static void test2() {
		ArrayList<Token> rosso = new ArrayList<>();
		MultipleToken token0 = new MultipleToken("p2");
		rosso.add( token0 );
		rosso.add(new Token("p4", token0, 0));
		MultipleToken token1 = new MultipleToken("p8");
		rosso.add( token1 );
		MultipleToken token2 = new MultipleToken("p10", token1, 0);
		rosso.add(token2);
		rosso.add(new Token("p12", token2, 0));
		rosso.add(new Token("p13", token2, 1));		
		rosso.add(new Token("p15", token1, 1));
		rosso.add(new Token("p18", token1, 1));
		
		for (Token t : rosso) {
			Term term = JasonToken.object_to_term(t);			
			Token tx = JasonToken.term_to_object(term);
			Term out = JasonToken.object_to_term(tx);			
			
			System.out.println("term: "+out.toString());
		}

		
		
	}

}
