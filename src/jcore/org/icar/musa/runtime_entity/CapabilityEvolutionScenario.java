package org.icar.musa.runtime_entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.icar.musa.context.evolution.EvolutionOperator;
import org.icar.musa.context.evolution.EvolutionScenario;

public class CapabilityEvolutionScenario implements EvolutionScenario {

	private String name;
	private List<EvolutionOperator> operators;
	
	public CapabilityEvolutionScenario( String name ) {
		this.name = name;
		this.operators = new LinkedList<>();
	}
	
	public CapabilityEvolutionScenario( String name, Set<EvolutionOperator> operators ) {
		this.name = name;
		this.operators = new LinkedList<EvolutionOperator>(operators);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void addOperator( EvolutionOperator operator ) {
		operators.add(operator);
	}

	@Override
	public List<EvolutionOperator> getOperators() {
		return operators;
	}

	@Override
	public String toString() {
		return "CapabilityEvolutionScenario [name=" + name + ", operators=" + operators + "]";
	}
	
	

}
