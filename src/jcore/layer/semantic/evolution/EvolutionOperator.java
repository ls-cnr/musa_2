package layer.semantic.evolution;

import layer.semantic.StateOfWorld;

public interface EvolutionOperator {

	StateOfWorld apply_transformation(StateOfWorld w);

}
