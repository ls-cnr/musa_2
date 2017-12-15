package datalayer.awareness.LTL.net.netmodels;

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
	public GloballyPN( TransitionCondition op1 ) {
		super("GloballyPN");
		this.firstOp = op1;
		this.secondOp = null;
		
		start = pn.place("Start");
		placeState.put(start, "W");
		
		Transition t1 = pn.transition("!"+firstOp.getTerm());
		firstOp.setStateCondition("E");
		transitionLabel.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Error")).getPlace(), "E");
	}

}
