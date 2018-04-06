package org.icar.musa.context.evolution;

import java.util.List;

/**
 * The Interface EvolutionScenario.
 */

public interface EvolutionScenario {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the operators.
	 *
	 * @return the operators
	 */
	List<EvolutionOperator> getOperators();
	
}
