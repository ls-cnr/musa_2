package layer.semantic.evolution;

import layer.semantic.StateOfWorld;
import translator.ExtDLPHead;

/**
 * The Class AddStatement.
 */

public class AddStatement implements  EvolutionOperator {
	
	/** The new statement. */
	private ExtDLPHead new_statement;

	/**
	 * Instantiates a new adds the statement.
	 *
	 * @param statement the statement
	 */
	public AddStatement(ExtDLPHead statement) {
		this.new_statement = statement;
	}
	
	/**
	 * Instantiates a new adds the statement.
	 *
	 * @param statement the statement
	 */
	public AddStatement(String statement) {
		/* TODO */
	}

	/* (non-Javadoc)
	 * @see layer.semantic.evolution.EvolutionOperator#apply_transformation(layer.semantic.StateOfWorld)
	 */
	public StateOfWorld apply_transformation(StateOfWorld w) {
		w.addFact_asASP(new_statement);
		return w;
	}

}
