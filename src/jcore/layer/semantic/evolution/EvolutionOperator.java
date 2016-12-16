package layer.semantic.evolution;

import layer.semantic.StateOfWorld;

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
