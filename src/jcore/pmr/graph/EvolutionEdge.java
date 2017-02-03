package pmr.graph;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class EvolutionEdge.
 */
public class EvolutionEdge implements Edge {
	
	/** The source. */
	private OPNode source;
	
	/** The destination. */
	private WorldNode destination;
	
	/** The scenario. */
	private EvolutionScenario scenario; 
	
	/**
	 * Instantiates a new evolution edge.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param scenario
	 *            the scenario
	 */
	public EvolutionEdge(OPNode source, WorldNode destination, EvolutionScenario scenario){
		super();
		this.source = source;
		this.destination = destination;
		this.scenario = scenario;
	}
	
	/**
	 * Gets the scenario.
	 *
	 * @return the scenario
	 */
	public EvolutionScenario getScenario(){
		return this.scenario;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public OPNode getSource(){
		return this.source;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public WorldNode getDestination() {
		return this.destination;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(OPNode source){
		this.source = source;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(WorldNode destination) {
		this.destination = destination;
	}
	
	/**
	 * Sets the scenario.
	 *
	 * @param scenario
	 *            the new scenario
	 */
	public void setScenario(EvolutionScenario scenario) {
		this.scenario = scenario;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof NormalEdge){
			NormalEdge temp = (NormalEdge)obj;
			
			if(temp.getSource() == null && this.source != null)	return false;
			else if(temp.getSource() != null && this.source == null)	return false;
	
			if(temp.getDestination() == null && this.destination != null)	return false;
			else if(temp.getDestination() != null && this.destination == null)	return false;
			
			if(this.source == null && temp.getSource() == null)
				if(this.destination == null && temp.getDestination() == null )	return true;
			
			if(this.source.equals(temp.getSource()) == true)
				if(this.destination.equals(temp.getDestination()) == true)		return true;
			
			return false;
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.scenario.hashCode();
	}
}
