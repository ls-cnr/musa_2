package layer.semantic.evolution;

import java.util.Set;

import org.inferred.freebuilder.FreeBuilder;

public interface EvolutionScenario {
	String getName();
	Set<EvolutionOperator> getOperators();
	
}
