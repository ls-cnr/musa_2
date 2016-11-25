package layer.semantic.evolution;

import java.util.Set;

import org.inferred.freebuilder.FreeBuilder;

@FreeBuilder
public interface EvolutionScenario {
	String getName();
	Set<EvolutionOperator> getOperators();
	
	class Builder extends EvolutionScenario_Builder { }

}
