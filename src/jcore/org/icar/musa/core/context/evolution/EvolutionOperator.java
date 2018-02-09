package org.icar.musa.core.context.evolution;

import org.icar.musa.core.context.StateOfWorld;

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
