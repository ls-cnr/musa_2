package pmr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import layer.awareness.AbstractCapability;
import pmr.graph.Edge;
import pmr.graph.Node;
import pmr.graph.NormalEdge;

//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	HashMap<Node, List<Edge>> graph;
	
	//lista metodi
	//costruttore
	public WTS(){ 
		this.graph = new HashMap<Node, List<Edge>> ();
	}
	
	//Aggiungi nodo alla mappa
	public void addNode(Node node){
		if( this.graph.putIfAbsent(node, new LinkedList<Edge>()) != null);	//se provo ad aggiungere un nodo alla mappa
																			//ma il nodo è già presente, come lo segnalo?
		return;
	}
	
	public void addEdge(Node source, Node destination, AbstractCapability capability){
		Edge link = new NormalEdge(source, destination, capability);
		
		
	}
}
