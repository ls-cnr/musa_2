package pmr.probexp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionOperator;
import layer.semantic.evolution.EvolutionScenario;
import pmr.graph.WorldNode;

// TODO: Auto-generated Javadoc
/**
 * The Class MultipleExpansionNode.
 */
public class MultipleExpansionNode extends ExpansionNode{

	/** The scenario list. */
	private HashMap<ENode, EvolutionScenario> scenarioList;
	
	/**
	 * Instantiates a new multiple expansion node.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param capability
	 *            the capability
	 */
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability){
		super(source, destination, capability);
		this.scenarioList = new HashMap<ENode, EvolutionScenario>();
		//if(destination != null)	this.findScore();
	}
	
	/**
	 * Adds the scenario.
	 *
	 * @param node
	 *            the node
	 * @param scenario
	 *            the scenario
	 */
	public void addScenario(ENode node, EvolutionScenario scenario){
		this.scenarioList.put(node, scenario);
	}
	
	/**
	 * Gets the scenario map.
	 *
	 * @return the scenario map
	 */
	public HashMap<ENode, EvolutionScenario>getScenarioMap(){
		return this.scenarioList;
	}
	
	/**
	 * Gets the scenario.
	 *
	 * @param node
	 *            the node
	 * @return the scenario
	 */
	public EvolutionScenario getScenario(ENode node){
		return this.scenarioList.get(node);
	}
	
}
