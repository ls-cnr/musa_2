package layertest.awareness;

import net.sf.tweety.lp.asp.parser.ParseException;

import java.io.IOException;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datalayer.awareness.AssumptionSet;
import datalayer.world.*;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import reasoner.EntailOperator;


// TODO: Auto-generated Javadoc
/**
 * The Class DomainEntailTest.
 */
public class DomainEntailTest1 {
	
	/** The w. */
	private StateOfWorld w;
	
	/** The domain. */
	private AssumptionSet domain;
	
	/** The cond q 1. */
	private Condition cond_q1;
	
	/** The cond q 2. */
	private Condition cond_q2;
	
	/** The cond q 3. */
	private Condition cond_q3;
	
	/** The env. */
	EntailOperator env;
	

	
	/**
	 * Initializing a StateOfWorld, an AssumptionSet and several Condition, to test the entailsCondition method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp()throws Exception{
		this.w = new StateOfWorld();
		try {
			w.addFact_asString("penguin(tweety).");
			w.addFact_asString("parrot(polly).");
			w.addFact_asString("sparrow(sid).");
			w.addFact_asString("broken_wing(sid).");
			w.addFact_asString("ostrich(olga).");
		} 
		catch (ParseException e) {
			e.printStackTrace();
		} 
		catch (exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
	
		this.domain = new AssumptionSet();	 
		try {
			domain.addAssumption_asString("bird(X) :- penguin(X).");
			domain.addAssumption_asString("bird(X) :- parrot(X).");
			domain.addAssumption_asString("bird(X) :- sparrow(X).");
			domain.addAssumption_asString("bird(X) :- ostrich(X).");
			domain.addAssumption_asString("cannot_fly(X) :- penguin(X).");
			domain.addAssumption_asString("cannot_fly(X) :- ostrich(X).");
			domain.addAssumption_asString("cannot_fly(X) :- broken_wing(X).");
			domain.addAssumption_asString("can_fly(X) :- bird(X), not cannot_fly(X).");
		} 
		catch (ParseException e) {
			e.printStackTrace();
		} 
		catch (exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		this.env = EntailOperator.getInstance();
		
		Constant polly = new Constant ("polly");
		Constant tweety = new Constant ("tweety");
		Constant sid = new Constant ("sid");
		
		DLPAtom q1 = new DLPAtom("can_fly",polly);
		DLPAtom q2 = new DLPAtom("can_fly",tweety);
		DLPAtom q3 = new DLPAtom("can_fly", sid);
		
		this.cond_q1 = new Condition(q1);
		this.cond_q2 = new Condition(q2);
		this.cond_q3 = new Condition(q3);
	}
	
	
	/**
	 * Test entails condition polly.
	 */
	@Test
	public void testEntailsCondition_polly() {
		boolean b1 = env.entailsCondition(w, domain, cond_q1);
		assertEquals(true, b1);
	}
	
	/**
	 * Test entails condition tweety.
	 */
	@Test
	public void testEntailsCondition_tweety() {
		boolean b1 = env.entailsCondition(w, domain, cond_q2);
		assertEquals(false,b1);		
	}
	
	/**
	 * Test entails condition sid.
	 */
	@Test
	public void testEntailsCondition_sid() {
		boolean b1 = env.entailsCondition(w, domain, cond_q3);
		assertEquals(false,b1);		
	}
}
