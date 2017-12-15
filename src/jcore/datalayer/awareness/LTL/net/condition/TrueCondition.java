package datalayer.awareness.LTL.net.condition;

/**
 * An always true condition (used for NextPN).
 */
public class TrueCondition extends TransitionCondition {

	/**
	 * Instantiates a new true condition.
	 */
	public TrueCondition() {
		super("true");
	}

	/* (non-Javadoc)
	 * @see layer.awareness.LTL.net.condition.TransitionCondition#setStateCondition(java.lang.String)
	 */
	@Override
	public void setStateCondition(String s) {
	}

}
