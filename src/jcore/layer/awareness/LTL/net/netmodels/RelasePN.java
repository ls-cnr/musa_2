package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.condition.*;
import petrinet.logic.Transition;

/**
 * The Class RelasePN, used to create a PetriNet that models a RELASE formula.
 */
public class RelasePN extends FormulaPN {

	/**
	 * Instantiates a new relase PN.
	 *
	 * @param op1
	 *            the op 1
	 * @param op2
	 *            the op 2
	 */
	public RelasePN( TransitionCondition op1, TransitionCondition op2 ) {
		super("RelasePN");
		this.firstOp = op1;
		this.secondOp = op2;
		TransitionCondition secondOpCopy;
		if( secondOp instanceof SimpleCondition )
			secondOpCopy = new SimpleCondition((SimpleCondition) secondOp);
		else
			secondOpCopy = new FormulaCondition(secondOp.getTerm());
		
		start = pn.place("Start");
		placeState.put(start, "A");
		
		Transition t1 = pn.transition(firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOp.setStateCondition("A");
		secondOpCopy.setStateCondition("A");
		transitionLabel.put(t1, new CombinationCondition(firstOp, secondOpCopy));
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept")).getPlace(), "A");

		Transition t2 = pn.transition("RERR-" + secondOp.getTerm());
		secondOpCopy.setStateCondition("E");
		transitionLabel.put(t2, secondOpCopy);
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Error")).getPlace(), "E");
	}
}
