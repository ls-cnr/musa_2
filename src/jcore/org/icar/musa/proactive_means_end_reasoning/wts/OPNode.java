package org.icar.musa.proactive_means_end_reasoning.wts;

import java.util.ArrayList;

/**
 * The Class OPNode. It implements the Node interface.
 */
public abstract class OPNode implements Node {
	
	/** The outcoming edges list. */
	private ArrayList <EvolutionEdge> outcomingList;
	
	/** The incoming edge. */
	private OPEdge incomingEdge;
	
	/** The hash code. */
	private int hashCode;
	
	/** The capability. */
	private String capability;
	
	/** The score. */
	private int score;
	
	private String agent;
	
	/**
	 * Instantiates a new OP node.
	 *
	 * @param capability
	 *            the capability
	 */
	public OPNode(String capability, int score){
		super();
		this.outcomingList = new ArrayList <EvolutionEdge>();
		this.incomingEdge = null;
		this.hashCode = this.hashCode();
		this.capability = capability;
		this.score = score;
	}
	
	public OPNode(String capability, int score, String agent){
		super();
		this.outcomingList = new ArrayList <EvolutionEdge>();
		this.incomingEdge = null;
		this.hashCode = this.hashCode();
		this.capability = capability;
		this.score = score;
		this.agent = agent;
	}
	
	/**
	 * Sets the incoming edge.
	 *
	 * @param edge
	 *            the new incoming edge
	 */
	public void setIncomingEdge(OPEdge edge){
		this.incomingEdge = edge;
	}
	
	 /**
	 * Gets the outcoming edges list.
	 *
	 * @return the outcoming edges list of this node
	 */
	public ArrayList<EvolutionEdge> getOutcomingEdge(){
		return this.outcomingList;
	}
	
	 /**
	 * Gets the incoming edge.
	 *
	 * @return the incoming edge of this node
	 */
	public OPEdge getIncomingEdge(){
		return this.incomingEdge;
	}
	
	/**
	 * Gets the capability.
	 *
	 * @return the capability of this OPNode
	 */
	public String getCapability(){
		return this.capability;
	}
	
	/**
	 * Adds the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	public boolean addOutcomingEdge(EvolutionEdge edge){
		if(this.outcomingList.contains(edge) == true){
				return false;
		}
		else{
			this.outcomingList.add(edge);
			return true;
		}
	
	}
	
	/**
	 * Removes the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	
	
	public void setAgent(String agent){
		this.agent = agent;
	}
	
	public String getAgent(){
		return this.agent;
	}
	
	public boolean removeOutcomingEdge(EvolutionEdge edge){
			return this.outcomingList.remove(edge);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!OPNode.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    OPNode other = (OPNode) obj;
	    
	    if (this.incomingEdge.equals(other.getIncomingEdge()) == true && this.capability.equals(other.getCapability()) == true) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		if(this.incomingEdge != null && this.incomingEdge.getSource().getWorldState() != null)	{
			return this.incomingEdge.getSource().getWorldState().hashCode();
		}
		else
			return 0;
	}
}

