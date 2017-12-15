package reasoner.probexp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import datalayer.awareness.AbstractCapability;
import datalayer.world.evolution.EvolutionOperator;
import datalayer.world.evolution.EvolutionScenario;
import datalayer.world.wts.WorldNode;

// TODO: Auto-generated Javadoc
/**
 * The Class MultipleExpansionNode.
 */
public class MultipleExpansion extends GraphExpansion{

	/** The scenario list. */
	private HashMap<ExtendedNode, String> scenarioList;
	
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
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability){
		super(source, destination, capability);
		this.scenarioList = new HashMap<ExtendedNode, String>();
		//if(destination != null)	this.findScore();
	}
	
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score){
		super(source, destination, capability, score);
		this.scenarioList = new HashMap<ExtendedNode, String>();
		//if(destination != null)	this.findScore();
	}
	
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, String agent){
		super(source, destination, capability, agent);
		this.scenarioList = new HashMap<ExtendedNode, String>();
		//if(destination != null)	this.findScore();
	}
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, HashMap<ExtendedNode, String> scenarioList){
		super(source, destination, capability);
		this.scenarioList = scenarioList;
	}
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, String agent, HashMap<ExtendedNode, String> scenarioList){
		super(source, destination, capability, agent);
		this.scenarioList = scenarioList;
	}
	
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score, HashMap<ExtendedNode, String> scenarioList){
		super(source, destination, capability, score);
		this.scenarioList = scenarioList;
	}
	
	public MultipleExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score, String agent, HashMap<ExtendedNode, String> scenarioList){
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
	public void addScenario(ExtendedNode node, String scenario){
		this.scenarioList.put(node, scenario);
	}
	
	/**
	 * Gets the scenario map.
	 *
	 * @return the scenario map
	 */
	public HashMap<ExtendedNode, String>getScenarioMap(){
		return this.scenarioList;
	}
	
	/**
	 * Gets the scenario.
	 *
	 * @param node
	 *            the node
	 * @return the scenario
	 */
	public String getScenario(ExtendedNode node){
		return this.scenarioList.get(node);
	}
	
}
