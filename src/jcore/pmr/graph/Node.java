package pmr.graph;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

import net.sf.tweety.lp.asp.syntax.DLPHead;

/**
 * The Interface Node.
 */
public abstract class Node {
	
	/** The incoming list. */
	
	//lista degli archi in uscita dal nodo
	protected Set<Edge> outcomingEdgeList;
	
	//stato del mondo associato al nodo
	protected StateOfWorld worldState;
	
	//numero di fatti contenuti nello stato del mondo associato
	protected int Nfacts;
	
	//lista degli archi in entrata nel nodo
	protected Set<Edge> incomingEdgeList;
	
	//Costruttore per i nodi
	public Node(StateOfWorld worldState){
		this.worldState = worldState;
		if(worldState == null){
			this.Nfacts = 0;
		}
		else	{
			this.Nfacts = worldState.getFactsNumber();
		}
		this.outcomingEdgeList = new HashSet<Edge>();
		this.incomingEdgeList = new HashSet<Edge>();
	}
	
	//Aggiunge un arco alla lista degli incomingedge
	public void addEdge(Edge edge){
		this.outcomingEdgeList.add(edge);
	}
	
	//Aggiunge una lista di archi in uscita all'oggetto.
	public void setOutcomingEdgeList(Set<Edge> edges){
		this.outcomingEdgeList = edges;
	}
	
	//Aggiunge una lista di archi in entrata all'oggetto
	public void setIncomingEdgeList(Set<Edge> edges){
		this.incomingEdgeList = edges;
	}
	
	//Restituisce il numero di fatti contenuti nello stato del mondo associato al nodo
	public int getNFacts(){
		return this.Nfacts;
	}
	
	public StateOfWorld getWorldState(){
		return this.worldState; 
	}
	
	public Set<Edge> getOutcomingEdgeList(){
		return this.outcomingEdgeList;
	}
	
	public Set<Edge> getIncomingEdgeList(){
		return this.incomingEdgeList;
	}
	
	public ArrayList<DLPHead> getFactsList(){
		ArrayList<DLPHead> factlist = new ArrayList<DLPHead>();
		Iterator<DLPHead> it = this.worldState.getFacts().iterator();
		while(it.hasNext() == true){
			factlist.add(it.next());
		}
		return factlist;
		}
	
	//Utilizzo l'equals ridefinito in StateOfWorld per confrontare i nodi.
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!Node.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    Node other = (Node) obj;
	    
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
	
	//Di conseguenza uso l'hashCode() di StateOfWorld per mantenere la regola delgli oggetti true per equals = Stesso hashcode
	@Override
	public int hashCode(){
		if(this.getWorldState() != null)	{
			return this.getWorldState().hashCode();
		}
		else
			return 0;
	}
}
