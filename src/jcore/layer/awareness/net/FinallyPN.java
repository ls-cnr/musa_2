package layer.awareness.net;

import petrinet.logic.Transition;

public class FinallyPN extends FormulaPN {

	public FinallyPN( String operand ) {
		super();
		this.operand = operand;
		
		start = pn.place("Start");
		association.put(start, "W");
		
		Transition t1 = pn.transition("!"+operand);
		pn.arc("a1", start, t1);
		pn.arc("a2", t1, start);
		
		Transition t2 = pn.transition(operand);
		pn.arc("a3", start, t2);
		association.put(pn.arc("a2", t2, pn.place("Accept")).getPlace(), "A");
	}
	
}
