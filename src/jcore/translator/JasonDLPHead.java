package translator;

import java.util.Set;

import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class JasonDLPHead {

	public static Term object_to_term(ExtDLPHead object) {
		DLPLiteral l = object.get(0);
		Structure term = new Structure(l.getName(),l.getArguments().size());
		for (net.sf.tweety.logics.commons.syntax.interfaces.Term<?> arg : l.getArguments()) {
			term.addTerm(new Structure(arg.toString()));
		}
		return term;
	}
	
	
	public static ExtDLPHead term_to_object(Term term) throws TranslateError {
		ExtDLPHead head =null;
		
		if (!term.isStructure()) {
			throw new TranslateError();
		} else {
			Structure s = (Structure) term;
			
			DLPAtom atom = new DLPAtom(s.getFunctor());
			for (Term arg : s.getTerms()) {
				Constant dlp_arg = new Constant(arg.toString());
				atom.addArgument(dlp_arg);
			}
			
			head = new ExtDLPHead(atom);
		}
		
		return head;
	}

	public static void main(String[] args) {
		JasonDLPHead.test1();
		JasonDLPHead.test2();
		
		System.out.println("FINE");
	}
	
	public static void test1() {
		Constant tweety = new Constant("tweety");
		DLPAtom tweety_penguin = new DLPAtom("penguin",tweety);
		ExtDLPHead head = new ExtDLPHead(tweety_penguin);
		
		Term term = JasonDLPHead.object_to_term(head);
		System.out.println("term: "+term);
		
		System.out.println("FINE");
	}

	public static void test2() {
		Structure struct = Structure.parse("available(doc)");
		
		DLPHead term;
		try {
			term = JasonDLPHead.term_to_object(struct);
			System.out.println("term: "+term);
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("FINE");
	}
}
