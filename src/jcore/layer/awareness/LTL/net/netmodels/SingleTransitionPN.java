package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.condition.*;
import petrinet.logic.Transition;

// TODO: Auto-generated Javadoc
/**
 * The Class SingleTransitionPN, used to create a PetriNet that models a a case in which the formula it's just an Atomic Proposition.
 */
public class SingleTransitionPN extends FormulaPN {

	/**
	 * Instantiates a new single transition PN.
	 *
	 * @param op1
	 *            the op 1
	 */
	public SingleTransitionPN( SimpleCondition op1 ) {
		super("SingleTransitionPN");
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition((SimpleCondition) firstOp);
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = null;
		
		start = pn.place("Start");
		placeState.put(start, "W");
		
		Transition t1 = pn.transition(firstOp.getTerm());
		firstOp.setStateCondition("A");
		transitionLabel.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept")).getPlace(), "A");
		
		Transition t2 = pn.transition("!"+firstOp.getTerm());
		firstOpCopy.setStateCondition("E");
		transitionLabel.put(t2, firstOpCopy);
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a6", t2, pn.place("Error")).getPlace(), "E");
	}
	
}
