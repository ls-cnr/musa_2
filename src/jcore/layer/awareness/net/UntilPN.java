package layer.awareness.net;

import petrinet.logic.Transition;

public class UntilPN extends FormulaPN {
	
	protected String operand2;

	public UntilPN( String operand1, String operand2 ) {
		super();
		this.operand = operand1;
		this.operand2 = operand2;
		
		start = pn.place("Start");
		association.put(start, "W");
		
		Transition t1 = pn.transition(operand);
		pn.arc("a1", start, t1);
		pn.arc("a2", t1, start);
		
		Transition t2 = pn.transition(this.operand2);
		pn.arc("a3", start, t2);
		association.put(pn.arc("a2", t2, pn.place("Accept")).getPlace(), "A");

		Transition t3 = pn.transition("UERR-" + operand + "-" + this.operand2);
		pn.arc("a4", start, t3);
		association.put(pn.arc("a2", t3, pn.place("Error")).getPlace(), "E");
	}
	
}
