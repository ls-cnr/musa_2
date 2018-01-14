package datalayer.world.evolution;

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
	Set<EvolutionOperator> getOperators();
	
}
