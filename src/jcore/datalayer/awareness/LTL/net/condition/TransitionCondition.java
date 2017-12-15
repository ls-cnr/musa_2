package datalayer.awareness.LTL.net.condition;

/**
 * A TransitionCondition it's a condition associated to a Transition in a PetriNet. This class is the common abstract class for every type
 * of condition.
 */
public abstract class TransitionCondition {

	/** The term. */
	private String term;
	
	/**
	 * Instantiates a new transition condition.
	 *
	 * @param term
	 *            the term
	 */
	protected TransitionCondition( String term ) {
		this.term = term;
	}
	
	/**
	 * Gets the term.
	 *
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}	
	
	/**
	 * Sets the term.
	 *
	 * @param s
	 *            the new term
	 */
	protected void setTerm( String s ) {
		term = s;
	}
	
	/**
	 * Sets the state that the condition must obtain to be satisfied.
	 *
	 * @param s
	 *            the new state condition
	 */
	public abstract void setStateCondition(String s);
	
}
