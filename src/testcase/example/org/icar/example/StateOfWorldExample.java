package org.icar.example;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.context.StateOfWorld;

import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.lp.asp.parser.ParseException;

public class StateOfWorldExample {

	public static void main(String[] args) {
		StateOfWorld w3;
		w3 = new StateOfWorld();
		try {
			w3.addFact_asString("ciao(francesco).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		for (ExtDLPHead f : w3.getFacts()) {
			f.getTerms();
			for (Term<?> t: f.getTerms()) {
				System.out.println(">"+t.toString());
			}
		}

	}

}
