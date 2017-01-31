package pmr.probexp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionOperator;
import layer.semantic.evolution.EvolutionScenario;
import pmr.graph.WorldNode;

public class MultipleExpansionNode extends ExpansionNode{

	private HashMap<ENode, EvolutionScenario> scenarioList;
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, AbstractCapability capability){
		super(source, destination, capability);
		this.scenarioList = new HashMap<ENode, EvolutionScenario>();
		//if(destination != null)	this.findScore();
	}
	
	public void addScenario(ENode node, EvolutionScenario scenario){
		this.scenarioList.put(node, scenario);
	}
	
	public HashMap<ENode, EvolutionScenario>getScenarioMap(){
		return this.scenarioList;
	}
	
	public EvolutionScenario getScenario(ENode node){
		return this.scenarioList.get(node);
	}
	
}
