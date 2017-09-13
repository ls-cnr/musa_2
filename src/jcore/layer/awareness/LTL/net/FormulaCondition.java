package layer.awareness.LTL.net;

public class FormulaCondition extends TransitionCondition {

	private String cond;
	
	public FormulaCondition(String name) {
		super(name);
	}

	@Override
	public void setStateCondition(String s) {
		cond = s;
	}

	public String getCond() {
		return cond;
	}
}
