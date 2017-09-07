package layer.awareness.net;

import java.util.HashMap;

import petrinet.logic.*;

public class GloballyPN extends FormulaPN {
	
	public GloballyPN( String operand ) {
		super();
		this.operand = operand;
		
		start = pn.place("Start");
		association.put(start, "A");
		
		Transition t1 = pn.transition(operand);
		pn.arc("a1", start, t1);
		pn.arc("a2", t1, start);
		
		Transition t2 = pn.transition("!"+operand);
		pn.arc("a3", start, t2);
		association.put(pn.arc("a2", t2, pn.place("Error")).getPlace(), "E");
	}

}
