package layer.awareness.LTL.net.netmodels;

import layer.awareness.LTL.net.SimpleCondition;

public class SinglePlacePN extends FormulaPN {

	public SinglePlacePN( SimpleCondition op1 ) {
		super();
		this.firstOp = op1;
		this.secondOp = null;
		
		start = pn.place("Start");
		association.put(start, "A");
	}
	
}
