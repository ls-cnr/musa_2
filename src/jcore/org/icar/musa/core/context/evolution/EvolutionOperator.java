package org.icar.musa.core.context.evolution;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.specification.ACLanguage.model.CapEvoAction;
/**
 * The Interface EvolutionOperator.
 */

public interface EvolutionOperator extends CapEvoAction {

	/**
	 * Apply transformation.
	 *
	 * @param w the w
	 * @return the state of world
	 */
	StateOfWorld apply_transformation(StateOfWorld w);

}
