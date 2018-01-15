package logics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;
import net.sf.tweety.lp.asp.util.AnswerSetList;


public class AnswerSetExample {

//	public static void main(String[] args) {
//			test1();
//			System.out.println("expected for test1: true, false");
//			
//			test2();
//			System.out.println("expected for test2: true, false");
//
//			test3();
//			System.out.println("expected for test3: true");
//
//			test4();
//			System.out.println("expected for test4: true,true");
//}
	
	 @Test
	public  void test1() {
//		System.out.println("---test1---");
//		System.out.println("Description: this test uses an answer set program to check a couple of conditions");
		DLV solver = new DLV(dlvPath());
		Program penguin;
		
		Constant tweety = new Constant("tweety");
		Constant polly = new Constant("polly");
		Constant sid = new Constant("sid");
		Constant olga = new Constant("olga");
		
		/* penguin(tweety), parrot(polly),	sparrow(sid), broken_wing(sid), ostrich(olga) */
		DLPAtom tweety_penguin = new DLPAtom("penguin",tweety);
		DLPAtom polly_parrot = new DLPAtom("parrot",polly);
		DLPAtom sid_sparrow = new DLPAtom("sparrow",sid);
		DLPAtom sid_brokenwing = new DLPAtom("broken_wing",sid);
		DLPAtom olga_ostrich = new DLPAtom("ostrich",olga);
		
		/* bird(X) :- penguin(X), bird(X) :- parrot(X), bird(X) :- sparrow(X), bird(X) :- ostrich(X) */
		Rule r1 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r2 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("parrot",new Variable("X"))  );
		Rule r3 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("sparrow",new Variable("X"))  );
		Rule r4 = new Rule( new DLPAtom("bird",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );

		/* cannot_fly(X) :- penguin(X), cannot_fly(X) :- ostrich(X), cannot_fly(X) :- broken_wing(X) */
		Rule r5 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("penguin",new Variable("X"))  );
		Rule r6 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("ostrich",new Variable("X"))  );
		Rule r7 = new Rule( new DLPAtom("cannot_fly",new Variable("X")), new DLPAtom("broken_wing",new Variable("X"))  );
				
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
			Rule r8 = ASPParser.parseRule("can_fly(X) :- bird(X), not cannot_fly(X).");
			penguin.add(r8);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		System.out.println(penguin.toString());
//		System.out.println("****************");
		
		AnswerSetList response = null;
		try {
			response = solver.computeModels(penguin, 10);
		} catch (SolverException e) {
		}
		
		if (response !=null) {
//			System.out.println(response.toString());
			
			/* can_fly(polly) ? */
			DLPAtom question1 = new DLPAtom("can_fly",polly);
			boolean resp1 = response.holdsAll(question1);
			assertTrue(resp1);
			//System.out.println("can_fly(polly) ? --> "+resp1);

			/* can_fly(tweety) ? */
			DLPAtom question2 = new DLPAtom("can_fly",tweety);
			boolean resp2 = response.holdsAll(question2);
			assertFalse(resp2);
			//System.out.println("can_fly(tweety) ? --> "+resp2);
		}
		
	}

	 @Test
	public  void test2() {
//		System.out.println("---test2---");
//		System.out.println("Description: this test uses the DomainEntail class to check a couple of conditions");
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("penguin(tweety).");
			w.addFact_asString("parrot(polly).");
			w.addFact_asString("sparrow(sid).");
			w.addFact_asString("broken_wing(sid).");
			w.addFact_asString("ostrich(olga).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
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
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		FOLCondition c1 = new FOLCondition( new DLPAtom("can_fly",new Constant("polly")) );
		FOLCondition c2 = new FOLCondition( new DLPAtom("can_fly",new Constant("tweety")) );
		
		EntailOperator env = EntailOperator.getInstance();
		env.setVerbose(false);
		boolean b1 = env.entailsCondition(w, domain, c1);
		boolean b2 = env.entailsCondition(w, domain, c2);
		
		assertTrue(b1);
		assertFalse(b2);
//		System.out.println("can_fly(polly) ? --> "+b1);
//		System.out.println("can_fly(tweety) ? --> "+b2);
		
	}
	
	 @Test
	public  void test3() {
//		System.out.println("---test3---");
//		System.out.println("Description: this test uses the DomainEntail for the SPS reconfiguration scenario");
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("on(main).");
			w.addFact_asString("closed(i1).");
			w.addFact_asString("open(i2).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		AssumptionSet domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("off(X) :- load(X), not on(X).");
			domain.addAssumption_asString("off(X) :- generator(X), not on(X).");
			domain.addAssumption_asString("down(X) :- node(X), not up(X).");
			domain.addAssumption_asString("open(X) :- switch(X), not closed(X).");
			domain.addAssumption_asString("on(l1) :- up(n1), closed(i1).");
			domain.addAssumption_asString("on(l1) :- up(n2), closed(i2).");
			domain.addAssumption_asString("up(n1) :- on(main).");
			domain.addAssumption_asString("up(n2) :- on(main).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		FOLCondition c1 = new FOLCondition( new DLPAtom("on",new Constant("l1")) );
		//Condition c2 = new Condition( new DLPAtom("can_fly",new Constant("tweety")) );
		
		EntailOperator env = EntailOperator.getInstance();
		env.setVerbose(false);
		boolean b1 = env.entailsCondition(w, domain, c1);
		//boolean b2 = env.entailsCondition(w, domain, c2);
		
		assertTrue(b1);
//		System.out.println("on(l1) ? --> "+b1);
		//System.out.println("can_fly(tweety) ? --> "+b2);
		
	}

	 @Test
	public  void test4() {
//		System.out.println("---test4---");
//		System.out.println("Description: this test uses the DomainEntail for the SPS reconfiguration scenario 2");
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("on(main).");
			w.addFact_asString("closed(i1).");
			w.addFact_asString("open(i2).");
			w.addFact_asString("open(i3).");
			w.addFact_asString("closed(i1).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		AssumptionSet domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("node(n3).");
			domain.addAssumption_asString("node(n4).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			
			domain.addAssumption_asString("off(X) :- load(X), not on(X).");
			domain.addAssumption_asString("off(X) :- generator(X), not on(X).");
			domain.addAssumption_asString("down(X) :- node(X), not up(X).");
			domain.addAssumption_asString("open(X) :- switch(X), not closed(X).");
			
			domain.addAssumption_asString("up(n3) :- up(n1), closed(i1).");
			domain.addAssumption_asString("up(n4) :- up(n2), closed(i2).");
			domain.addAssumption_asString("up(n1) :- on(main).");
			domain.addAssumption_asString("up(n2) :- on(main).");
			domain.addAssumption_asString("on(l1) :- up(n3).");
			domain.addAssumption_asString("on(l1) :- up(n4).");
			domain.addAssumption_asString("on(l2) :- up(n3), closed(i3).");
			domain.addAssumption_asString("on(l2) :- up(n4), closed(i4).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		FOLCondition c1 = new FOLCondition( new DLPAtom("on",new Constant("l1")) );
		FOLCondition c2 = new FOLCondition( new DLPAtom("off",new Constant("l2")) );
		
		EntailOperator env = EntailOperator.getInstance();
		env.setVerbose(false);
		boolean b1 = env.entailsCondition(w, domain, c1);
		boolean b2 = env.entailsCondition(w, domain, c2);
		
		assertTrue(b1);
		assertTrue(b2);
		
//		System.out.println("on(l1) ? --> "+b1);
//		System.out.println("off(l2) ? --> "+b2);
		
	}

	private static String dlvPath(){
			if( System.getProperty("os.name").startsWith("Windows") )
				return "./ext/dlv.mingw.exe";
			else //TODO considering other OS, such as Mac OS or Linux based OS
				return "./ext/dlv.i386-apple-darwin.bin";
	}

}
