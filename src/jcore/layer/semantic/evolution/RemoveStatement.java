package layer.semantic.evolution;

import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;

/**
 * The Class RemoveStatement.
 */

public class RemoveStatement implements EvolutionOperator {
	
	/** The new statement. */
	private DLPHead new_statement;
	
	/**
	 * Instantiates a new removes the statement.
	 *
	 * @param statement the statement
	 */
	public RemoveStatement(DLPHead statement) {
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
