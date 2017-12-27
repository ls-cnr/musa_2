package datalayer.awareness.LTL.net.netmodels;

import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.condition.*;
import petrinet.logic.*;

/**
 * The Class GloballyPN, used to create a PetriNet that models a GLOBALLY formula.
 */
public class GloballyPN extends FormulaPN {
	
	/**
	 * Instantiates a new globally PN.
	 *
	 * @param op1
	 *            the op 1
	 */
	public GloballyPN( TransitionCondition op1, String fatherName) {
		super("GloballyPN");
		this.firstOp = op1;
		this.secondOp = new EndCondition(fatherName);
		
		start = pn.place("Start");
		placeState.put(start, PetriNetState.WAIT_BUT_ACCEPTED);
		
		Transition t1 = pn.transition("!"+firstOp.getTerm());
		firstOp.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Error")).getPlace(), PetriNetState.ERROR);
		
		Transition t2 = pn.transition("END-" + fatherName);
		transitionLabel.put(t2, secondOp);
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Accept")).getPlace(), PetriNetState.ACCEPTED);
	}

}
