package pmr.graph;

import java.util.ArrayList;
import java.util.HashSet;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.EvolutionScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class OPNode.
 */
public abstract class OPNode implements Node {
	
	/** The outcoming list. */
	private ArrayList <EvolutionEdge> outcomingList;
	
	/** The incoming edge. */
	//Contiene soltanto un arco in entrata
	private OPEdge incomingEdge;
	
	/** The hash code. */
	private int hashCode;
	
	/** The capability. */
	private AbstractCapability capability;
	
	/**
	 * Instantiates a new OP node.
	 *
	 * @param capability
	 *            the capability
	 */
	public OPNode(AbstractCapability capability){
		super();
		this.outcomingList = new ArrayList <EvolutionEdge>();
		this.incomingEdge = null;
		this.hashCode = this.hashCode();
		this.capability = capability;
	}
	
	/**
	 * Sets the incoming edge.
	 *
	 * @param edge
	 *            the new incoming edge
	 */
	//Setta l'arco in entrata
	public void setIncomingEdge(OPEdge edge){
		this.incomingEdge = edge;
	}
	
	 /**
	 * Gets the outcoming edge.
	 *
	 * @return the outcoming edge
	 */
 	//prende l'arco in uscita;
	public ArrayList<EvolutionEdge> getOutcomingEdge(){
		return this.outcomingList;
	}
	
	 /**
	 * Gets the incoming edge.
	 *
	 * @return the incoming edge
	 */
 	//prende l'arco in entrata;
	public OPEdge getIncomingEdge(){
		return this.incomingEdge;
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
	 * Adds the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	//Aggiunge un arco alla lista degli outcomingEdge usando l'arco stesso
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
	public boolean removeOutcomingEdge(EvolutionEdge edge){
			return this.outcomingList.remove(edge);
	}
	
	//Se due OPNode hanno lo stesso source e la stessa capability allora sono uguali, dato che ha senso confrontarli solo all'interno.
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	//Di uno stesso WorldNode
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
	//Ho modifcato equals, di conseguenza modifico l'hashCode() di StateOfWorld per mantenere la regola delgli oggetti true per equals = Stesso hashcode
	@Override
	public int hashCode(){
		if(this.incomingEdge != null && this.incomingEdge.getSource().getWorldState() != null)	{
			return this.incomingEdge.getSource().getWorldState().hashCode();
		}
		else
			return 0;
	}
}

