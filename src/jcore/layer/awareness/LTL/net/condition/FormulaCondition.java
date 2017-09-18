package layer.awareness.LTL.net.condition;

/**
 * This Class is used for a Formula type Condition. It stores the net reference to look up for checking if the condition is Accepted or Error.
 */
public class FormulaCondition extends TransitionCondition {

	/** The cond. */
	private String cond;
	
	/**
	 * Instantiates a new formula condition.
	 *
	 * @param name
	 *            the name
	 */
	public FormulaCondition(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see layer.awareness.LTL.net.condition.TransitionCondition#setStateCondition(java.lang.String)
	 */
	@Override
	public void setStateCondition(String s) {
		cond = s;
	}

	/**
	 * Gets the cond.
	 *
	 * @return the cond
	 */
	public String getCond() {
		return cond;
	}
}
