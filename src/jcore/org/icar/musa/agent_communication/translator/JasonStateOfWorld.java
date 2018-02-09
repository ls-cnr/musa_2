package org.icar.musa.agent_communication.translator;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.exception.NotAllowedInAStateOfWorld;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPHead;

public class JasonStateOfWorld {

	public static Term object_to_term(StateOfWorld node) {
		Structure term = new Structure("w",1);
		ListTermImpl facts = new ListTermImpl();
		if (node != null) {
			for (ExtDLPHead fact : node.getFactsList()) {
				Term item = JasonDLPHead.object_to_term(fact);
				facts.add(item);
			}		
		}
		term.addTerm(facts);
		return term;
	}
	
	
	public static StateOfWorld term_to_object(Term term) throws TranslateError {
		StateOfWorld world = null;
		if (term.isStructure()) {			
			Structure s = (Structure) term;		
			if (s.getFunctor().equals("w")) {
				ListTermImpl facts = (ListTermImpl) s.getTerm(0);
				world = new StateOfWorld();
				for (Term t : facts ) {
					ExtDLPHead h = JasonDLPHead.term_to_object(t);
					world.addFact_asASP(h);
				}				
			}
		}
		return world;
	}

	public static StateOfWorld term_string_to_object(String term_string) throws TranslateError {
		Structure term = Structure.parse(term_string);
		return term_to_object(term);
	}
		
	public static void main(String[] args) {
		JasonStateOfWorld.test1();
		JasonStateOfWorld.test2();
	}
	

	public static void test1() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("available(doc).");
			w.addFact_asString("received(file).");
			w.addFact_asString("sent(file,user).");
			
			Term t = JasonStateOfWorld.object_to_term(w);
			System.out.println("result: "+t.toString());
		} catch (ParseException | NotAllowedInAStateOfWorld e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("FINE");
	}

	public static void test2() {
		Structure w = Structure.parse("w([received(file),sent(file,user),available(doc)])");
		StateOfWorld world;
		try {
			world = JasonStateOfWorld.term_to_object(w);
			Term t = JasonStateOfWorld.object_to_term(world);
			System.out.println("result: "+t.toString());
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("FINE");
	}
}
