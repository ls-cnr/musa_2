package datalayer.awareness.LTL.net.netmodels;

import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.condition.*;
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
	public RelasePN( TransitionCondition op1, TransitionCondition op2/*, String endFatherName */) {
		super("RelasePN");
		this.firstOp = op1;
		this.secondOp = op2;
		//EndCondition thirdOp = new EndCondition(endFatherName);
		TransitionCondition secondOpCopy;
		if( secondOp instanceof SimpleCondition )
			secondOpCopy = new SimpleCondition((SimpleCondition) secondOp);
		else
			secondOpCopy = new FormulaCondition(secondOp.getTerm());
		
		start = pn.place("Start");
		placeState.put(start, PetriNetState.WAIT_BUT_ACCEPTED);
		
		Transition t1 = pn.transition(firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOp.setStateCondition(PetriNetState.ACCEPTED);
		secondOpCopy.setStateCondition(PetriNetState.ACCEPTED);
		transitionLabel.put(t1, new CombinationCondition(firstOp, secondOpCopy));
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept1")).getPlace(), PetriNetState.ACCEPTED);

		Transition t2 = pn.transition("RERR-" + secondOp.getTerm());
		secondOpCopy.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t2, secondOpCopy);
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Error")).getPlace(), PetriNetState.ERROR);

		/*Transition t3 = pn.transition("END-" + endFatherName);
		transitionLabel.put(t3, thirdOp);
		
		pn.arc("a5", start, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Accept2")).getPlace(), PetriNetState.ACCEPTED);*/
	}
}
