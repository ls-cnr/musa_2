package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.*;
import petrinet.logic.Transition;

public class FinallyPN extends FormulaPN {

	public FinallyPN( TransitionCondition op1 ) {
		super();
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition(firstOp.getTerm());
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = null;
		
		start = pn.place("Start");
		association.put(start, "W");
		
		Transition t1 = pn.transition("!"+firstOp.getTerm());
		firstOp.setStateCondition("E");
		labels.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		pn.arc("a2", t1, start);
		
		Transition t2 = pn.transition(firstOp.getTerm());
		firstOpCopy.setStateCondition("A");
		labels.put(t2, firstOpCopy);
		
		pn.arc("a3", start, t2);
		association.put(pn.arc("a2", t2, pn.place("Accept")).getPlace(), "A");
	}	
}
