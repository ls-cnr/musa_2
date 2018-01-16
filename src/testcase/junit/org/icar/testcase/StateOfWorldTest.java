package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.domain.StateOfWorld;
import org.junit.Before;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.lp.asp.parser.ParseException;

public class StateOfWorldTest {

	StateOfWorld w;
	StateOfWorld w1;
	StateOfWorld w2;
	StateOfWorld w3;
	
	@Before
	public void init(){
		w = new StateOfWorld();
		try {
			w.addFact_asString("penguin(tweety).");
			w.addFact_asString("penguin(tweety).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
	
	w1 = new StateOfWorld();
	try {
		w1.addFact_asString("penguin(tweety).");
		w1.addFact_asString("penguin(tweety).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	
	w2 = new StateOfWorld();
	try {
		w2.addFact_asString("bird(tweety).");
		w2.addFact_asString("bird(tweety).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	
	w3 = new StateOfWorld();
	try {
		w3.addFact_asString("ciao(francesco).");

	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
}
	
	@Test
	public void equals1(){
		assertEquals(true, w.equals(w1));
	}
	
	@Test
	public void equals2(){
		assertEquals(false, w1.equals(w2));
	}
	
	@Test
	public void equals3(){
		assertEquals(false, w.equals(w2));
	}
	
	@Test
	public void equals4(){
		assertEquals(false, w.equals(w3));
	}
}
