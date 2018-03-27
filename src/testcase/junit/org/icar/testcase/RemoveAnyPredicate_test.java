package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.context.evolution.AddStatement;
import org.icar.musa.core.context.evolution.RemoveAnyStatement;
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class RemoveAnyPredicate_test {

	@Test
	public void test() {
		StateOfWorld w;
		w = new StateOfWorld();
		try {
			w.addFact_asString("location(bedroom).");
			w.addFact_asString("posture(sitting).");
			w.addFact_asString("posture(laying).");
			
			RemoveAnyStatement evo1 = new RemoveAnyStatement("posture");
			AddStatement evo2 = new AddStatement(new ExtDLPHead(new DLPAtom("posture", new Constant("walking"))));
			
			w = evo1.apply_transformation(w);
			w = evo2.apply_transformation(w);
			
			assertTrue(w.getFacts().contains( new ExtDLPHead(new DLPAtom("posture", new Constant("walking"))) ));
			assertTrue(! w.getFacts().contains( new ExtDLPHead(new DLPAtom("posture", new Constant("sitting"))) ));
			assertTrue(! w.getFacts().contains( new ExtDLPHead(new DLPAtom("posture", new Constant("laying"))) ));

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
	}

}
