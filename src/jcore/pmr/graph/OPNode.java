package pmr.graph;

import java.util.ArrayList;
import java.util.HashSet;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.EvolutionScenario;

/**
 * The Class OPNode.
 */
public abstract class OPNode implements Node {
	
	private ArrayList <EvolutionEdge> outcomingList;
	
	//Contiene soltanto un arco in entrata
	private NormalEdge	incomingEdge;
	private int hashCode;
	private AbstractCapability capability;
	
	public OPNode(AbstractCapability capability){
		super();
		this.outcomingList = new ArrayList <EvolutionEdge>();
		this.incomingEdge = null;
		this.hashCode = this.hashCode();
		this.capability = capability;
	}
	
	//Setta l'arco in entrata
	public void setIncomingEdge(NormalEdge edge){
		this.incomingEdge = edge;
	}
	
	 //prende l'arco in uscita;
	public ArrayList<EvolutionEdge> getOutcomingEdge(){
		return this.outcomingList;
	}
	
	 //prende l'arco in entrata;
	public NormalEdge getIncomingEdge(){
		return this.incomingEdge;
	}
	
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
	
	public boolean removeOutcomingEdge(EvolutionEdge edge){
			return this.outcomingList.remove(edge);
	}
	
	//Utilizzo l'equals ridefinito in StateOfWorld per confrontare i nodi.
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!OPNode.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    OPNode other = (OPNode) obj;
	    
	    if (this.incomingEdge.equals(other.getIncomingEdge()) == true) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	//Ho modifcato equals, di conseguenza modifico l'hashCode() di StateOfWorld per mantenere la regola delgli oggetti true per equals = Stesso hashcode
	@Override
	public int hashCode(){
		if(this.incomingEdge.getSource().getWorldState() != null)	{
			return this.incomingEdge.getSource().getWorldState().hashCode();
		}
		else
			return 0;
	}
}

