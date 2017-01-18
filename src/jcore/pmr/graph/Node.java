package pmr.graph;


import java.util.ArrayList;
import java.util.Iterator;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

import net.sf.tweety.lp.asp.syntax.DLPHead;

/**
 * The Interface Node.
 */
public abstract class Node {
	
	/** The incoming list. */
	
	//lista degli archi in uscita dal nodo
	protected ArrayList<Edge> outcomingEdgeList;
	
	//stato del mondo associato al nodo
	protected StateOfWorld worldState;
	
	//numero di fatti contenuti nello stato del mondo associato
	protected int Nfacts;
	
	//lista degli archi in entrata nel nodo
	protected ArrayList<Edge> incomingEdgeList;
	
	//Valore dell'hashcode per velocizzare le operazioni
	protected int hashCode;
	
	//Costruttore per i nodi
	public Node(StateOfWorld worldState){
		this.worldState = worldState;
		if(worldState == null){
			this.Nfacts = 0;
		}
		else	{
			this.Nfacts = worldState.getFactsNumber();
		}
		this.outcomingEdgeList = new ArrayList<Edge>();
		this.incomingEdgeList = new ArrayList<Edge>();
		this.hashCode = this.hashCode();
	}
	
	//Aggiunge un arco alla lista degli incomingEdge usando l'arco stesso, ritorna falso se la lista contiene già l'arco e non lo aggiunge
	public boolean addIncomingEdge(Edge edge){
		if(edge instanceof NormalEdge){
			NormalEdge temp = (NormalEdge) edge;
			if(this.incomingEdgeList.contains(temp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(temp);
				return true;
			}
		}
		else if(edge instanceof EvolutionEdge){
			EvolutionEdge temp = (EvolutionEdge) edge;
			if(this.incomingEdgeList.contains(temp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(temp);
				return true;
			}
		}
		else
			return false;
	}
	
	//Aggiunge un arco alla lista degli outcomingEdge usando l'arco stesso
	public boolean addOutcomingEdge(Edge edge){
		if(edge instanceof NormalEdge){
			NormalEdge temp = (NormalEdge) edge;
			if(this.outcomingEdgeList.contains(temp) == true){
				return false;
			}
			else{
				this.outcomingEdgeList.add(temp);
				return true;
			}
		}
		else if(edge instanceof EvolutionEdge){
			EvolutionEdge temp = (EvolutionEdge) edge;
			if(this.outcomingEdgeList.contains(temp) == true){
				return false;
			}
			else{
				this.outcomingEdgeList.add(temp);
				return true;
			}
		}
		else
			return false;
	}
	
	//Aggiunge una lista di archi in uscita all'oggetto.
	public void setOutcomingEdgeList(ArrayList<Edge> edges){
		 this.outcomingEdgeList = edges;
	}
	
	//Aggiunge una lista di archi in entrata all'oggetto
	public void setIncomingEdgeList(ArrayList<Edge> edges){
		this.incomingEdgeList = edges;
	}
	
	//Restituisce il numero di fatti contenuti nello stato del mondo associato al nodo
	public int getNFacts(){
		return this.Nfacts;
	}
	
	//Restituisce lo stato del mondo associato al nodo
	public StateOfWorld getWorldState(){
		return this.worldState; 
	}
	
	//Restituisce la lista degli archi in uscita contenuti nel nodo
	public ArrayList<Edge> getOutcomingEdgeList(){
		return this.outcomingEdgeList;
	}
	
	//Restituisce la lista degli archi in entrata contenuti nel nodo
	public ArrayList<Edge> getIncomingEdgeList(){
		return this.incomingEdgeList;
	}
	
	//Restituisce la lista dei fatti, lista contenuta nello state of world
	public ArrayList<DLPHead> getFactsList(){
		return this.worldState.getFactsList();
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
	
	//Ho modifcato equals, di conseguenza modifico l'hashCode() di StateOfWorld per mantenere la regola delgli oggetti true per equals = Stesso hashcode
	@Override
	public int hashCode(){
		if(this.getWorldState() != null)	{
			return this.getWorldState().hashCode();
		}
		else
			return 0;
	}
	
	//ritorna il valore di hashcode salvato nella variabile (più veloce che calcolarlo)
	public int getHashCode(){
		return this.hashCode;
	}
	
	
	//Rimuove un arco dalla lista degli archi in entrata
	public boolean removeIncomingEdge(Edge edge){
		return this.incomingEdgeList.remove(edge);
	}
	
	//Rimuove un arco dalla lista degli archi in uscita
	public boolean removeOutcomingEdge(Edge edge){
		return this.outcomingEdgeList.remove(edge);
	}
	
}