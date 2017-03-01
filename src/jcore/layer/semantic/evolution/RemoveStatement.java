package layer.semantic.evolution;

import layer.semantic.StateOfWorld;
import translator.ExtDLPHead;

/**
 * The Class RemoveStatement.
 */

public class RemoveStatement implements EvolutionOperator {
	
	/** The new statement. */
	private ExtDLPHead new_statement;
	
	/**
	 * Instantiates a new removes the statement.
	 *
	 * @param statement the statement
	 */
	public RemoveStatement(ExtDLPHead statement) {
		this.new_statement = statement;
	}
	
	/**
	 * Instantiates a new removes the statement.
	 *
	 * @param statement the statement
	 */
	public RemoveStatement(String statement) {
		/* TODO */;
	}
	

	/* (non-Javadoc)
	 * @see layer.semantic.evolution.EvolutionOperator#apply_transformation(layer.semantic.StateOfWorld)
	 */
	public StateOfWorld apply_transformation(StateOfWorld w) {
		w.removeFact_safely_asASP(new_statement);
		return w;
	}

}
