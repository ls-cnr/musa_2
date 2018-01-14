package org.icar.musa.core.domain.evolution;

import org.icar.musa.core.domain.StateOfWorld;

/**
 * The Interface EvolutionOperator.
 */

public interface EvolutionOperator {

	/**
	 * Apply transformation.
	 *
	 * @param w the w
	 * @return the state of world
	 */
	StateOfWorld apply_transformation(StateOfWorld w);

}
