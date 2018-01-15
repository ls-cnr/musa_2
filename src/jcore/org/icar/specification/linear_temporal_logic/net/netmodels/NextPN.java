package org.icar.specification.linear_temporal_logic.net.netmodels;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;
import org.icar.specification.linear_temporal_logic.net.condition.*;

import petrinet.logic.Place;
import petrinet.logic.Transition;

/**
 * The Class NextPN, used to create a PetriNet that models a NEXT formula.
 */
public class NextPN extends FormulaPN {

	/**
	 * Instantiates a new next PN.
	 *
	 * @param op1
	 *            the op 1
	 */
	public NextPN( TransitionCondition op1 ) {
		super("NextPN");
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition((SimpleCondition) firstOp);
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = null;
		
		start = pn.place("Start");
		placeState.put(start, PNStateEnum.WAIT_BUT_ACCEPTED);
		
		Transition t1 = pn.transition("Empty");
		transitionLabel.put(t1, new TrueCondition());
		
		pn.arc("a1", start, t1);
		Place wait = pn.arc("a2", t1, pn.place("Wait")).getPlace();
		placeState.put(wait, PNStateEnum.WAIT_BUT_ACCEPTED);
		
		Transition t2 = pn.transition(firstOp.getTerm());
		firstOp.setStateCondition(PNStateEnum.ACCEPTED);
		transitionLabel.put(t2, firstOp);
		
		pn.arc("a3", wait, t2);
		placeState.put(pn.arc("a4", t2, pn.place("Accept")).getPlace(), PNStateEnum.ACCEPTED);
		
		Transition t3 = pn.transition("!"+firstOp.getTerm());
		firstOpCopy.setStateCondition(PNStateEnum.ERROR);
		transitionLabel.put(t3, firstOpCopy);
		
		pn.arc("a5", wait, t3);
		placeState.put(pn.arc("a6", t3, pn.place("Error")).getPlace(), PNStateEnum.ERROR);
	}
	
}
