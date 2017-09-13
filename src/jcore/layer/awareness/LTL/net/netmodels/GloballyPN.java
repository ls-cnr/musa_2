package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.*;
import petrinet.logic.*;

public class GloballyPN extends FormulaPN {
	
	public GloballyPN( TransitionCondition op1 ) {
		super();
		this.firstOp = op1;
		TransitionCondition firstOpCopy;
		if( firstOp instanceof SimpleCondition )
			firstOpCopy = new SimpleCondition(firstOp.getTerm());
		else
			firstOpCopy = new FormulaCondition(firstOp.getTerm());
		this.secondOp = null;
		
		start = pn.place("Start");
		association.put(start, "A");
		
		Transition t1 = pn.transition(firstOp.getTerm());
		firstOp.setStateCondition("A");
		labels.put(t1, firstOp);
		
		pn.arc("a1", start, t1);
		pn.arc("a2", t1, start);
		
		Transition t2 = pn.transition("!"+firstOp.getTerm());
		firstOpCopy.setStateCondition("E");
		labels.put(t1, firstOpCopy);
		
		pn.arc("a3", start, t2);
		association.put(pn.arc("a2", t2, pn.place("Error")).getPlace(), "E");
	}

}
