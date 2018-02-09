package org.icar.musa.proactive_means_end_reasoning.wts;

import java.util.ArrayList;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.context.StateOfWorld;

/**
 * The Class WorldNode. It's the standard node of the graph of the solutions. It implements the Node Interface
 */
public class WorldNode implements Node {
	
	private static int counter = 0;
	
	private int id;

	/** The world state. */
	private StateOfWorld worldState;
	
	/** The Number of facts stored in the StateOfWorld. */
	private int Nfacts;
	
	/** The outcoming edges list. */
	private ArrayList<NormalEdge> outcomingEdgeList;
	
	/** The incoming edges list. */
	private ArrayList<Edge> incomingEdgeList;
	
	/** The OPNodes list. */
	private ArrayList<OPNode>	OPList;
	
	/** The hash code. It's calculated only one time inside the constructor 
	 * and stored inside the object to speed up the operations inside the HashMap */
	private int hashCode;
	
	/**
	 * Instantiates a new world node.
	 *
	 * @param worldState
	 *            the world state
	 */
	public WorldNode(StateOfWorld worldState){
		super();
		this.id = counter++;
		this.worldState = worldState;
		if(worldState == null){
			this.Nfacts = 0;
		}
		else{
			this.Nfacts = worldState.getFactsNumber();
		}
			this.outcomingEdgeList = new ArrayList<NormalEdge>();
			this.incomingEdgeList = new ArrayList<Edge>();
			this.OPList = new ArrayList<OPNode>();
			this.hashCode = this.hashCode_once();
	}
	
	
	public int getId() {
		return id;
	}

	/**
	 * Adds the incoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	public boolean addIncomingEdge(Edge edge){
		NormalEdge nortemp = null;
		EvolutionEdge evotemp = null;
		if(edge instanceof NormalEdge){
			nortemp = (NormalEdge) edge;
			if(this.incomingEdgeList.contains(nortemp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(nortemp);
				return true;
			}
		}
		
		else{
			evotemp = (EvolutionEdge) edge;
			if(this.incomingEdgeList.contains(evotemp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(evotemp);
				return true;
			}
		}
	}
	
	/**
	 * Adds the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	public boolean addOutcomingEdge(NormalEdge edge){
		if(this.outcomingEdgeList.contains(edge) == true){
				return false;
		}
		else{
			this.outcomingEdgeList.add(edge);
			return true;
		}
	}
	
	/**
	 * Sets the outcoming edges list.
	 *
	 * @param edges
	 *            the new outcoming edges list
	 */
	public void setOutcomingEdgeList(ArrayList<NormalEdge> edges){
		 this.outcomingEdgeList = edges;
	}
	
	/**
	 * Sets the incoming edges list.
	 *
	 * @param edges
	 *            the new incoming edges list
	 */
	public void setIncomingEdgeList(ArrayList<Edge> edges){
		this.incomingEdgeList = edges;
	}
	
	/**
	 * Gets the number of facts stored in the StateOfWorld.
	 *
	 * @return the number of facts
	 */
	public int getNFacts(){
		return this.Nfacts;
	}
	
	/**
	 * Gets the world state.
	 *
	 * @return the state of world of this node.
	 */
	public StateOfWorld getWorldState(){
		return this.worldState; 
	}
	
	/**
	 * Gets the outcoming edges list.
	 *
	 * @return the outcoming edges of this node.
	 */
	public ArrayList<NormalEdge> getOutcomingEdgeList(){
		return this.outcomingEdgeList;
	}
	
	/**
	 * Gets the incoming edges list.
	 *
	 * @return the incoming edges list of this node.
	 */
	public ArrayList<Edge> getIncomingEdgeList(){
		return this.incomingEdgeList;
	}
	
	/**
	 * Gets the facts list stored inside the StateOfWorld.
	 *
	 * @return the list of facts of the state of world of this node.
	 */
	public ArrayList<ExtDLPHead> getFactsList(){
		return this.worldState.getFactsList();
	}
	
	/**
	 * Gets the OPNode list.
	 *
	 * @return the OPNodes list of this node.
	 */
	public ArrayList<OPNode> getOPNodeList(){
		return this.OPList;
	}
	
	/**
	 * Removes the incoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	public boolean removeIncomingEdge(Edge edge){
		return this.incomingEdgeList.remove(edge);
	}
	
	/**
	 * Removes the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	public boolean removeOutcomingEdge(Edge edge){
		return this.outcomingEdgeList.remove(edge);
	}
	
	/**
	 * Adds the OPNode.
	 *
	 * @param node
	 *            the node
	 * @return true, if successful
	 */
	public boolean addOPNode(OPNode node){
		if(this.OPList.contains(node) == true){
			return false;
		}
		else{
			this.OPList.add(node);
			return true;
		}
	}
	
	/**
	 * Removes the OPNode.
	 *
	 * @param node
	 *            the node
	 * @return the OPNode removed if present, null otherwise.
	 */
	public OPNode removeOPNode(OPNode node){
		return this.OPList.remove(this.OPList.indexOf(node));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!WorldNode.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    WorldNode other = (WorldNode) obj;
	    
	    if(this.getWorldState() == null && other.getWorldState() == null){
	    	return true;
	    }
	    else if (this.getWorldState() != null && this.getWorldState().equals(other.getWorldState())) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	/**
	 * This method calculate the hashcode of the object. the value is stored inside the object.
	 *
	 * @return the hashcode, based on the StateOfWorld hashCode(). return 0 if worldstate is null.
	 */
	private int hashCode_once(){
		if(this.worldState != null)	{
			return this.worldState.hashCode();
		}
		else
			return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
			return this.hashCode;
	}
}	
