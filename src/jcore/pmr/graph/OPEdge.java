package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class OPEdge.
 */
public class OPEdge implements Edge {
		
	/** The source. */
	private WorldNode source;
	
	/** The destination. */
	private OPNode destination;
	
	/** The capability. */
	private AbstractCapability capability; 
	
	/**
	 * Instantiates a new OP edge.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param capability
	 *            the capability
	 */
	public OPEdge(WorldNode source, OPNode destination, AbstractCapability capability){
		super();
		this.source = source;
		this.destination = destination;
		this.capability = capability;
	}
	
	/**
	 * Gets the capability.
	 *
	 * @return the capability
	 */
	public AbstractCapability getCapability(){
		return this.capability;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public WorldNode getSource(){
		return this.source;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public OPNode getDestination() {
		return this.destination;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(WorldNode source){
		this.source = source;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(OPNode destination) {
		this.destination = destination;
	}
	
	/**
	 * Sets the scenario.
	 *
	 * @param capability
	 *            the new scenario
	 */
	public void setScenario(AbstractCapability capability) {
		this.capability = capability;
	}
	
	//L'OPNode è unico, ogni WorldNode ha i suoi OPNode, ma non può esistere un OPNode con lo stesso source e lo stesso desintation all'interno
	//Di un WorldNode
	
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
			
			if(this.source.equals(temp.getSource()) == false)				return false;
			if(this.destination.equals(temp.getDestination()) == false)		return false;
			return true;
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.capability.hashCode();
	}
}
