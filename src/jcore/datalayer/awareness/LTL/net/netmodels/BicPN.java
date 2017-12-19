package datalayer.awareness.LTL.net.netmodels;

import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.condition.CombinationCondition;
import datalayer.awareness.LTL.net.condition.FormulaCondition;
import datalayer.awareness.LTL.net.condition.SimpleCondition;
import datalayer.awareness.LTL.net.condition.TransitionCondition;
import petrinet.logic.Transition;

/**
 * The Class BicPN, used to create a PetriNet that models a BICONNECTED formula.
 */
public class BicPN extends FormulaPN {

	/**
	 * Instantiates a new bic PN.
	 *
	 * @param op1
	 *            the op 1
	 * @param op2
	 *            the op 2
	 */
	public BicPN( TransitionCondition op1, TransitionCondition op2 ) {
		super("BicPN");
		this.firstOp = op1;
		TransitionCondition firstOpCopy1;
		TransitionCondition firstOpCopy2;
		TransitionCondition firstOpCopy3;
		if( firstOp instanceof SimpleCondition ){
			firstOpCopy1 = new SimpleCondition((SimpleCondition) firstOp);
			firstOpCopy2 = new SimpleCondition((SimpleCondition) firstOp);
			firstOpCopy3 = new SimpleCondition((SimpleCondition) firstOp);
		}
		else{
			firstOpCopy1 = new FormulaCondition(firstOp.getTerm());
			firstOpCopy2 = new FormulaCondition(firstOp.getTerm());
			firstOpCopy3 = new FormulaCondition(firstOp.getTerm());
		}
		this.secondOp = op2;
		TransitionCondition secondOpCopy1;
		TransitionCondition secondOpCopy2;
		TransitionCondition secondOpCopy3;
		if( secondOp instanceof SimpleCondition ){
			secondOpCopy1 = new SimpleCondition((SimpleCondition) secondOp);
			secondOpCopy2 = new SimpleCondition((SimpleCondition) secondOp);
			secondOpCopy3 = new SimpleCondition((SimpleCondition) secondOp);
		}
		else{
			secondOpCopy1 = new FormulaCondition(secondOp.getTerm()); 
			secondOpCopy2 = new FormulaCondition(secondOp.getTerm()); 
			secondOpCopy3 = new FormulaCondition(secondOp.getTerm()); 
		}
		
		start = pn.place("Start");
		placeState.put(start, PetriNetState.WAIT_BUT_ACCEPTED);
		
		Transition t1 = pn.transition("BICACC1-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOp.setStateCondition(PetriNetState.ACCEPTED);
		secondOp.setStateCondition(PetriNetState.ACCEPTED);
		transitionLabel.put(t1, new CombinationCondition(firstOp, secondOp));
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept1")).getPlace(), PetriNetState.ACCEPTED);

		Transition t2 = pn.transition("BICERR1-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy1.setStateCondition(PetriNetState.ERROR);
		secondOpCopy1.setStateCondition(PetriNetState.ACCEPTED);
		transitionLabel.put(t2, new CombinationCondition(firstOpCopy1, secondOpCopy1));
		
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Error1")).getPlace(), PetriNetState.ERROR);
		
		Transition t3 = pn.transition("BICERR2-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy2.setStateCondition(PetriNetState.ACCEPTED);
		secondOpCopy2.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t3, new CombinationCondition(firstOpCopy2, secondOpCopy2));
		
		pn.arc("a5", start, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Error2")).getPlace(), PetriNetState.ERROR);

		Transition t4 = pn.transition("BICACC1-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy3.setStateCondition(PetriNetState.ERROR);
		secondOpCopy3.setStateCondition(PetriNetState.ERROR);
		transitionLabel.put(t4, new CombinationCondition(firstOpCopy3, secondOpCopy3));
		
		pn.arc("a7", start, t4);
		placeState.put(pn.arc("a8", t4, pn.place("Accept2")).getPlace(), PetriNetState.ACCEPTED);
	}
	
}
