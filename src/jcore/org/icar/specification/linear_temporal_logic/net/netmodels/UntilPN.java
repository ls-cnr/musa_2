package org.icar.specification.linear_temporal_logic.net.netmodels;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;
import org.icar.specification.linear_temporal_logic.net.condition.*;

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
	public UntilPN( TransitionCondition op1, TransitionCondition op2/*, String endFatherName*/) {
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
		//EndCondition thirdOp = new EndCondition(endFatherName);
		
		start = pn.place("Start");
		placeState.put(start, PNStateEnum.WAIT_BUT_ERROR);
		
		Transition t1 = pn.transition(secondOp.getTerm());
		secondOp.setStateCondition(PNStateEnum.ACCEPTED);
		transitionLabel.put(t1, secondOp);
		
		pn.arc("a1", start, t1);
		placeState.put(pn.arc("a2", t1, pn.place("Accept")).getPlace(), PNStateEnum.ACCEPTED);

		Transition t2 = pn.transition("UERR1-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOp.setStateCondition(PNStateEnum.ERROR);
		secondOpCopy.setStateCondition(PNStateEnum.ERROR);
		transitionLabel.put(t2, new CombinationCondition(firstOp, secondOpCopy));
				
		pn.arc("a3", start, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Error1")).getPlace(), PNStateEnum.ERROR);
		
		Transition t3 = pn.transition("UERR2-" + firstOp.getTerm() + "-" + secondOp.getTerm());
		firstOpCopy.setStateCondition(PNStateEnum.ACCEPTED);
		secondOpCopyCopy.setStateCondition(PNStateEnum.ACCEPTED);
		transitionLabel.put(t3, new CombinationCondition(firstOpCopy, secondOpCopyCopy));
				
		pn.arc("a5", start, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Error2")).getPlace(), PNStateEnum.ERROR);
		
		/*Transition t4 = pn.transition("END-" + endFatherName);
		transitionLabel.put(t4, thirdOp);
		
		pn.arc("a7", start, t4);
		placeState.put(pn.arc("a8", t4, pn.place("Error3")).getPlace(), PetriNetState.ERROR);*/
	}
	
}
