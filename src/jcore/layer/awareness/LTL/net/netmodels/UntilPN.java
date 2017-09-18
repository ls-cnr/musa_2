package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.condition.*;
import petrinet.logic.Transition;

/**
 * The Class UntilPN, used to create a PetriNet that models an UNTIL formula.
 */
public class UntilPN extends FormulaPN {

	/**
	 * Instantiates a new until PN.
	 *
	 * @param op1
	 *            the op 1
	 * @param op2
	 *            the op 2
	 */
	public UntilPN( TransitionCondition op1, TransitionCondition op2 ) {
		super("UntilPN");
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition((SimpleCondition) firstOp);
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = op2;
		TransitionCondition secondOpCopy;
		TransitionCondition secondOpCopyCopy;
		if( secondOp instanceof SimpleCondition ){
			secondOpCopy = new SimpleCondition((SimpleCondition) secondOp);
			secondOpCopyCopy = new SimpleCondition((SimpleCondition) secondOp);
		}
		else{
			secondOpCopy = new FormulaCondition(secondOp.getTerm()); 
			secondOpCopyCopy = new FormulaCondition(secondOp.getTerm()); 
		}
		
		start = pn.place("Start");
		placeState.put(start, "W");
		
		Transition t1 = pn.transition(secondOp.getTerm());
		secondOp.setStateCondition("A");
		transitionLabel.put(t1, secondOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept")).getPlace(), "A");

		Transition t2 = pn.transition("UERR1-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOp.setStateCondition("E");
		secondOpCopy.setStateCondition("E");
		transitionLabel.put(t2, new CombinationCondition(firstOp, secondOpCopy));
				
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Error1")).getPlace(), "E");
		
		Transition t3 = pn.transition("UERR2-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy.setStateCondition("A");
		secondOpCopyCopy.setStateCondition("A");
		transitionLabel.put(t2, new CombinationCondition(firstOpCopy, secondOpCopyCopy));
				
		pn.arc("a5", start, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Error2")).getPlace(), "E");
		
	}
	
}
