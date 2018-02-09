package org.icar.musa.core.context.evolution;

import java.util.List;
import java.util.Set;

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
