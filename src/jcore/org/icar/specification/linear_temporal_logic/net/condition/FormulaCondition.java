package org.icar.specification.linear_temporal_logic.net.condition;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;

/**
 * This Class is used for a Formula type Condition. It stores the net reference to look up for checking if the condition is Accepted or Error.
 */
public class FormulaCondition extends TransitionCondition {

	/** The cond. */
	private PNStateEnum cond;
	
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
	public void setStateCondition(PNStateEnum s) {
		cond = s;
	}

	/**
	 * Gets the cond.
	 *
	 * @return the cond
	 */
	public PNStateEnum getCond() {
		return cond;
	}
}
