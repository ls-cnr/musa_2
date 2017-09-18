package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.condition.*;
import petrinet.logic.Transition;

/**
 * The Class FinallyPN, used to create a PetriNet that models a FINALLY formula.
 */
public class FinallyPN extends FormulaPN {

	/**
	 * Instantiates a new finally PN.
	 *
	 * @param op1
	 *            the op 1
	 */
	public FinallyPN( TransitionCondition op1 ) {
		super("FinallyPN");
		this.firstOp = op1;
		this.secondOp = null;
		
		start = pn.place("Start");
		placeState.put(start, "W");
		
		Transition t1 = pn.transition(firstOp.getTerm());
		firstOp.setStateCondition("A");
		transitionLabel.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept")).getPlace(), "A");
	}	
}
