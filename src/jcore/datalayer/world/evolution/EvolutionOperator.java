package datalayer.world.evolution;

import datalayer.world.StateOfWorld;

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
