package datalayer.awareness.LTL.net.condition;

import datalayer.awareness.LTL.net.PetriNetState;

/**
 * This Class is used for a Formula type Condition. It stores the net reference to look up for checking if the condition is Accepted or Error.
 */
public class FormulaCondition extends TransitionCondition {

	/** The cond. */
	private PetriNetState cond;
	
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
	public void setStateCondition(PetriNetState s) {
		cond = s;
	}

	/**
	 * Gets the cond.
	 *
	 * @return the cond
	 */
	public PetriNetState getCond() {
		return cond;
	}
}
