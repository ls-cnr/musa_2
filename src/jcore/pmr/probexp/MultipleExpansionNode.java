package pmr.probexp;

import java.util.ArrayList;
import java.util.HashMap;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;
import pmr.graph.WorldNode;

public class MultipleExpansionNode extends ExpansionNode{

	private double score;
	private HashMap<ENode, EvolutionScenario> scenarioList;
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, AbstractCapability capability){
		super(source, destination, capability);
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
	
	private void findScore(){
		double tempscore = 0;
		int i;
		for(i=0; i<this.getDestination().size(); i++){
			tempscore += this.getDestination().get(i).getScore();
		}
		this.score = tempscore / i;
	}
	
	public double getScore(){
		this.findScore();
		return this.score;
	}
}
