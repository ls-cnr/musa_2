package org.icar.musa.context.evolution;

import org.icar.musa.context.StateOfWorld;
import org.icar.specification.ACLanguage.specmodel.CapEvoAction;
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
