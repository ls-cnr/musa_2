package example;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import layer.awareness.DomainEntail;
import layer.semantic.AssumptionSet;
import layer.semantic.StateOfWorld;
import net.sf.tweety.commons.ParserException;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.logics.fol.FolBeliefSet;
import net.sf.tweety.logics.fol.parser.FolParser;
import net.sf.tweety.logics.fol.semantics.HerbrandInterpretation;
import net.sf.tweety.logics.fol.syntax.AssociativeFOLFormula;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.fol.syntax.FolSignature;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNot;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

public class AnswerSetExample {

	public static void main(String[] args) {
		
			test3();
	}
	
	public static void test1() {
		Program penguin;
		
		Constant tweety = new Constant("tweety");
		Constant polly = new Constant("polly");
		Constant sid = new Constant("sid");
		Constant olga = new Constant("olga");
		
		/*
		 * 
			penguin(tweety).
			parrot(polly).
			sparrow(sid).
			broken_wing(sid).
			ostrich(olga).
		 */
		DLPAtom tweety_penguin = new DLPAtom("penguin",tweety);
		DLPAtom polly_parrot = new DLPAtom("parrot",polly);
		DLPAtom sid_sparrow = new DLPAtom("sparrow",sid);
		DLPAtom sid_brokenwing = new DLPAtom("broken_wing",sid);
		DLPAtom olga_ostrich = new DLPAtom("ostrich",olga);
		
		/*
			bird(X) :- penguin(X).
			bird(X) :- parrot(X).
			bird(X) :- sparrow(X).
			bird(X) :- ostrich(X).
		 */
		
		Rule r1 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r2 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("parrot",new Variable("X"))  );
		Rule r3 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("sparrow",new Variable("X"))  );
		Rule r4 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );

		/*
			cannot_fly(X) :- penguin(X).
			cannot_fly(X) :- ostrich(X).
			cannot_fly(X) :- broken_wing(X). 
		 */
		Rule r5 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r6 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );
		Rule r7 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("broken_wing",new Variable("X"))  );
		
		/* can_fly(X) :- bird(X), not cannot_fly(X). */
		LinkedList r8_body = new LinkedList<DLPAtom>();
		r8_body.add(new DLPAtom("bird",new Variable("X")));
		r8_body.add(new DLPNot( new DLPAtom("cannot_fly",new Variable("X")) ) );
		//Rule r8 = new Rule( 	new DLPAtom("can_fly",new Variable("X")),  r8_body  );
		
		Rule r8;
		
		penguin = new Program();
		penguin.addFact(new DLPHead(tweety_penguin));
		penguin.addFact(new DLPHead(polly_parrot));
		penguin.addFact(new DLPHead(sid_sparrow));
		penguin.addFact(new DLPHead(sid_brokenwing));
		penguin.addFact(new DLPHead(olga_ostrich));
		
		penguin.add(r1);
		penguin.add(r2);
		penguin.add(r3);
		penguin.add(r4);
		penguin.add(r5);
		penguin.add(r6);
		penguin.add(r7);

		try {
			r8 = ASPParser.parseRule("can_fly(X) :- bird(X), not cannot_fly(X).");
			penguin.add(r8);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(penguin.toString());
		System.out.println("****************");
		
		DLV solver = new DLV("./tools/dlv.i386-apple-darwin.bin");
		AnswerSetList response = null;
		try {
			response = solver.computeModels(penguin, 10);
		} catch (SolverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (response !=null) {
			System.out.println(response.toString());
			
			/* can_fly(polly) ? */
			DLPAtom question1 = new DLPAtom("can_fly",polly);
			boolean resp1 = response.holdsAll(question1);
			System.out.println("can_fly(polly) ? "+resp1);

			/* can_fly(tweety) ? */
			DLPAtom question2 = new DLPAtom("can_fly",tweety);
			boolean resp2 = response.holdsAll(question2);
			System.out.println("can_fly(tweety) ? "+resp2);
		}
		
	}
	
	public static void test2() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("penguin(tweety).");
			w.addFact_asString("parrot(polly).");
			w.addFact_asString("sparrow(sid).");
			w.addFact_asString("broken_wing(sid).");
			w.addFact_asString("ostrich(olga).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		AssumptionSet domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("bird(X) :- penguin(X).");
			domain.addAssumption_asString("bird(X) :- parrot(X).");
			domain.addAssumption_asString("bird(X) :- sparrow(X).");
			domain.addAssumption_asString("bird(X) :- ostrich(X).");
			domain.addAssumption_asString("cannot_fly(X) :- penguin(X).");
			domain.addAssumption_asString("cannot_fly(X) :- ostrich(X).");
			domain.addAssumption_asString("cannot_fly(X) :- broken_wing(X).");
			domain.addAssumption_asString("can_fly(X) :- bird(X), not cannot_fly(X).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		DomainEntail env = DomainEntail.getInstance();
		//env.setWorld(w);
		//env.setAssumptions(domain);
		
		
	}
	
	public static void test3() {
		Program penguin;
		
		Constant tweety = new Constant("tweety");
		Constant polly = new Constant("polly");
		Constant sid = new Constant("sid");
		Constant olga = new Constant("olga");
		DLPAtom tweety_penguin = new DLPAtom("penguin",tweety);
		DLPAtom polly_parrot = new DLPAtom("parrot",polly);
		DLPAtom sid_sparrow = new DLPAtom("sparrow",sid);
		DLPAtom sid_brokenwing = new DLPAtom("broken_wing",sid);
		DLPAtom olga_ostrich = new DLPAtom("ostrich",olga);
		
		Rule r1 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r2 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("parrot",new Variable("X"))  );
		Rule r3 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("sparrow",new Variable("X"))  );
		Rule r4 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );
		Rule r5 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r6 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );
		Rule r7 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("broken_wing",new Variable("X"))  );
		LinkedList r8_body = new LinkedList<DLPAtom>();
		r8_body.add(new DLPAtom("bird",new Variable("X")));
		r8_body.add(new DLPNot( new DLPAtom("cannot_fly",new Variable("X")) ) );
		
		Rule r8=null;
		
		Rule question1 = null;
		//Rule question2 = null;
		
		try {
			r8 = ASPParser.parseRule("can_fly(X) :- bird(X), not cannot_fly(X).");
			question1 = ASPParser.parseRule(" :- not can_fly(tweety),not can_fly(polly).");
			//question2 = ASPParser.parseRule(" :- can_fly(polly).");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		penguin = new Program();
		penguin.addFact(new DLPHead(tweety_penguin));
		penguin.addFact(new DLPHead(polly_parrot));
		penguin.addFact(new DLPHead(sid_sparrow));
		penguin.addFact(new DLPHead(sid_brokenwing));
		penguin.addFact(new DLPHead(olga_ostrich));
		
		penguin.add(r1);
		penguin.add(r2);
		penguin.add(r3);
		penguin.add(r4);
		penguin.add(r5);
		penguin.add(r6);
		penguin.add(r7);
		penguin.add(r8);

		penguin.add(question1);
		//penguin.add(question2);


		System.out.println(penguin.toString());
		System.out.println("****************");
		
		DLV solver = new DLV("./tools/dlv.i386-apple-darwin.bin");
		AnswerSetList response = null;
		try {
			response = solver.computeModels(penguin, 10);
		} catch (SolverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (response !=null) {
			System.out.println(response.toString());
			
			AspFolTranslator tx = new AspFolTranslator();
			HerbrandInterpretation interpr = new HerbrandInterpretation();
			
			AnswerSet as = response.get(0);
			Iterator<DLPLiteral> it = as.iterator();
			while (it.hasNext()) {
				FolFormula f = tx.toFOL(it.next());
				interpr.add((FOLAtom) f);
			}

			System.out.println("H: " + interpr);
			
			
			FOLAtom atom1 = new FOLAtom(new Predicate("can_fly",1), new  Variable("X"));
			FOLAtom atom2= new FOLAtom(new Predicate("penguin",1), new  Variable("X"));
			FolFormula conj = new Conjunction(atom1,atom2);
			FolFormula q1 = new ExistsQuantifiedFormula(conj, new  Variable("X"));
			boolean b1 = interpr.satisfies(q1);
			
			System.out.println("testing: " + q1+" --> "+b1);
			
			
//			/* can_fly(polly) ? */
//			DLPAtom question1 = new DLPAtom("can_fly",polly);
//			boolean resp1 = response.holdsAll(question1);
//			System.out.println("can_fly(polly) ? "+resp1);
//
//			/* can_fly(tweety) ? */
//			DLPAtom question2 = new DLPAtom("can_fly",tweety);
//			boolean resp2 = response.holdsAll(question2);
//			System.out.println("can_fly(tweety) ? "+resp2);
		}
		
	}

}
