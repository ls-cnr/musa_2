package datalayer.awareness.LTL.net.netmodels;

import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.condition.CombinationCondition;
import datalayer.awareness.LTL.net.condition.FormulaCondition;
import datalayer.awareness.LTL.net.condition.SimpleCondition;
import datalayer.awareness.LTL.net.condition.TransitionCondition;
import petrinet.logic.Transition;
/**
 * The Class AndPN, used to create a PetriNet that models an AND formula.
 */
public class AndPN extends FormulaPN {
	
	/**
	 * Instantiates a new and PN.
	 *
	 * @param op1
	 *            the op 1
	 * @param op2
	 *            the op 2
	 */
	public AndPN( TransitionCondition op1, TransitionCondition op2 ) {
		super("AndPN");
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition((SimpleCondition) firstOp);
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = op2;
		TransitionCondition secondOpCopy;
		if( secondOp instanceof SimpleCondition )
			secondOpCopy = new SimpleCondition((SimpleCondition) secondOp);
		else
			secondOpCopy = new FormulaCondition(secondOp.getTerm()); 
		
		start = pn.place("Start");
		placeState.put(start, PetriNetState.WAIT_BUT);
		
		Transition t1 = pn.transition("!"+firstOp.getTerm());
		firstOp.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Error1")).getPlace(), PetriNetState.ERROR);

		Transition t2 = pn.transition(firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy.setStateCondition(PetriNetState.ACCEPTED);
		secondOpCopy.setStateCondition(PetriNetState.ACCEPTED);
		transitionLabel.put(t2, new CombinationCondition(firstOpCopy, secondOpCopy));
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Accept")).getPlace(), PetriNetState.ACCEPTED);
		
		Transition t3 = pn.transition("!"+secondOp.getTerm());
		secondOp.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t3, secondOp);
		
		pn.arc("a5", start, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Error2")).getPlace(), PetriNetState.ERROR);
		
	}

}
