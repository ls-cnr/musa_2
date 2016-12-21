package pmr.graph;


import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

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
	
	public Node(StateOfWorld worldState, Node parent, AbstractCapability capability){
		this.worldState = worldState;
		//l'agente che vuole creare un nuovo nodo, sa da quale nodo è stata ricavata questa evoluzione, 
		this.incomingEdgeList = new ArrayList<Edge>();
		
		//se il genitore era un nodo normale, creo un NormalEdge con il genitore, se era instanza di OPNode, creo un EvolutionEdge
		if(parent instanceof OPNode)	this.incomingEdgeList.add(new EvolutionEdge(parent, this, capability));
		else							this.incomingEdgeList.add(new NormalEdge(parent, this, capability));
		
		this.outcomingEdgeList = new ArrayList<Edge>();
		this.Nfacts = worldState.getFactsNumber();
	}
	
	//costruttore del primo nodo del grafo (non ha genitori)
	public Node(StateOfWorld worldState){
		this.worldState = worldState;
		this.outcomingEdgeList = new ArrayList<Edge>();
		this.Nfacts = worldState.getFactsNumber();
	}

	//Aggiunge un arco alla lista degli incomingedge
	public void addEdge(Edge edge){
		this.outcomingEdgeList.add(edge);
	}
	
	//Aggiunge una lista di archi all'oggetto.
	public void setEdgeList(ArrayList<Edge> edges){
		this.outcomingEdgeList = edges;
	}
	
	//Restituisce il numero di fatti contenuti nello stato del mondo associato al nodo
	public int getNFacts(){
		return this.Nfacts;
	}
	
	public StateOfWorld getWorldState(){
		return this.worldState; 
	}
	
	public ArrayList<Edge> getOutcomingEdgeList(){
		return this.outcomingEdgeList;
	}
	
	public ArrayList<Edge> getIncomingEdgeList(){
		return this.incomingEdgeList;
	}
}
