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
	private HashMap<ENode, String> scenarioList;
	
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
		this.scenarioList = new HashMap<ENode, String>();
		//if(destination != null)	this.findScore();
	}
	
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score){
		super(source, destination, capability, score);
		this.scenarioList = new HashMap<ENode, String>();
		//if(destination != null)	this.findScore();
	}
	
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, String agent){
		super(source, destination, capability, agent);
		this.scenarioList = new HashMap<ENode, String>();
		//if(destination != null)	this.findScore();
	}
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, HashMap<ENode, String> scenarioList){
		super(source, destination, capability);
		this.scenarioList = scenarioList;
	}
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, String agent, HashMap<ENode, String> scenarioList){
		super(source, destination, capability, agent);
		this.scenarioList = scenarioList;
	}
	
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score, HashMap<ENode, String> scenarioList){
		super(source, destination, capability, score);
		this.scenarioList = scenarioList;
	}
	
	public MultipleExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score, String agent, HashMap<ENode, String> scenarioList){
		super(source, destination, capability, score, agent);
		this.scenarioList = scenarioList;
	}
	
	/**
	 * Adds the scenario.
	 *
	 * @param node
	 *            the node
	 * @param scenario
	 *            the scenario
	 */
	public void addScenario(ENode node, String scenario){
		this.scenarioList.put(node, scenario);
	}
	
	/**
	 * Gets the scenario map.
	 *
	 * @return the scenario map
	 */
	public HashMap<ENode, String>getScenarioMap(){
		return this.scenarioList;
	}
	
	/**
	 * Gets the scenario.
	 *
	 * @param node
	 *            the node
	 * @return the scenario
	 */
	public String getScenario(ENode node){
		return this.scenarioList.get(node);
	}
	
}
