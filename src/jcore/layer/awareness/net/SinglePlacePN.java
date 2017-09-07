package layer.awareness.net;


public class SinglePlacePN extends FormulaPN {

	public SinglePlacePN( String operand ) {
		super();
		this.operand = operand;
		
		start = pn.place("Start");
		association.put(start, "A");
	}
	
}
