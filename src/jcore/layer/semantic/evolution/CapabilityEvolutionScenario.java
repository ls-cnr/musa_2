package layer.semantic.evolution;

import java.util.HashSet;
import java.util.Set;

public class CapabilityEvolutionScenario implements EvolutionScenario {

	private String name;
	private Set<EvolutionOperator> operators;
	
	public CapabilityEvolutionScenario( String name ) {
		this.name = name;
		this.operators = new HashSet<>();
	}
	
	public CapabilityEvolutionScenario( String name, Set<EvolutionOperator> operators ) {
		this.name = name;
		this.operators = new HashSet<EvolutionOperator>(operators);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void addOperator( EvolutionOperator operator ) {
		operators.add(operator);
	}

	@Override
	public Set<EvolutionOperator> getOperators() {
		return operators;
	}

}
