package pmr.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.AbstractCapability;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.MultipleExpansionNode;
import pmr.probexp.NormalExpansionNode;

/**
 * The Class WTS.
 */
//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	
	/** The graph. */
	private HashMap<WorldNode, WorldNode> graph;

	/**
	 * Instantiates a new wts.
	 */
	public WTS(){ 
		this.graph = new HashMap<WorldNode, WorldNode> ();
		this.graph.put(new WorldNode(null), new WorldNode(null));
	}
	
	
	/**
	 * Adds the node.
	 *
	 * @param newnode
	 *            the newnode
	 */
	//Aggiungi nodo alla mappa, restituisce true se lo aggiunge, false se lo trova e non lo aggiunge
	public void addNode(ExpansionNode newnode){	
		if(newnode instanceof NormalExpansionNode){
			//Aggiorno il nodo presente sia nel grafo che nel newnode, aggiungo al grafo il nodo destinazione presente in newnode 
			//aggiungendo un arco in entrata dal nodo source e aggiorno gli archi in uscita dal nodo source aggiungendo
			//quello diretto verso il nodo destination.
			NormalExpansionNode tempnode = (NormalExpansionNode) newnode;
			this.addSafeNode(tempnode.getSource().getWorldNode());
			this.addSafeNode(tempnode.getDestination().get(0).getWorldNode());
			WorldNode destination2 = this.graph.get(tempnode.getDestination().get(0).getWorldNode());
			
			//Metodo che aggiunge l'arco in entrata ed in uscita
			this.addEdge(this.graph.get(tempnode.getSource().getWorldNode()), destination2, tempnode.getCapability());			}
		else{
			//Aggiorno il nodo presente nel grafo, aggiungendogli un OPNode all'interno
			MultipleExpansionNode exptempnode = (MultipleExpansionNode) newnode;
			this.addSafeNode(exptempnode.getSource().getWorldNode());
			WorldNode source2 = this.graph.get(exptempnode.getSource().getWorldNode());
			
			//Creo un OPNode E setto l'arco entrante
			OPNode faketempnode = new XORNode(exptempnode.getCapability());
			faketempnode.setIncomingEdge(new OPEdge(source2, faketempnode, exptempnode.getCapability()));
			
			//Se il nodo contiene già quell'OPNode non compio nessuna operazione altrimenti lo aggiungo
			if(source2.getOPNodeList().contains(faketempnode))	return;
			
			//Per ogni Enodo destinazione, aggiungo al grafo il WorldNode contenuto
			for(ENode etemp : newnode.getDestination()){
				WorldNode wnode = etemp.getWorldNode();
				this.addSafeNode(wnode);
				//Aggiorno la lista degli archi uscenti da faketempnode, aggiungendo un arco per ogni ENode destination
				faketempnode.addOutcomingEdge(new EvolutionEdge(faketempnode, wnode, exptempnode.getScenario(etemp)));
			}
			//Aggiungo l'OPNode al WorldNode source nel grafo
			source2.addOPNode(faketempnode);
		}
	}
	
	/**
	 * Adds the safe node.
	 *
	 * @param source
	 *            the source
	 * @return true, if successful
	 */
	//Aggiunta semplice al grafo tramite WorldNode
	public boolean addSafeNode(WorldNode source){
		if(this.graph.containsKey(source) == false){
			WorldNode temp = new WorldNode(source.getWorldState());
			temp.setIncomingEdgeList(source.getIncomingEdgeList());
			temp.setOutcomingEdgeList(source.getOutcomingEdgeList());
			this.graph.put(temp, temp);
			return true;
		}
		else
			return false;
	}
	
	//Aggiunge un arco ad un Worldnodo sorgente, modificando la lista degli archi uscenti del nodo stesso e la lista degli archi entranti del nodo destinazione
	/**
	 * Adds the edge.
	 *
	 * @param sourcenode
	 *            the sourcenode
	 * @param destnode
	 *            the destnode
	 * @param capability
	 *            the capability
	 */
	//arco di tipo NormalEdge
	public void addEdge(WorldNode sourcenode, WorldNode destnode, AbstractCapability capability){
			sourcenode.addOutcomingEdge(new NormalEdge(sourcenode, destnode, capability));
			destnode.addIncomingEdge(new NormalEdge(sourcenode, destnode, capability));
	}
	
	//Ritorna l'arco da modificare se è presente, null altrimenti.
	/**
	 * Edits the edge.
	 *
	 * @param node
	 *            the node
	 * @param capability
	 *            the capability
	 * @return the edge
	 */
	//Basta confrontare le capability tra gli archi visto che ci troviamo all'interno di un nodo.
	public Edge editEdge(WorldNode node, AbstractCapability capability){
		Iterator <NormalEdge> i = this.graph.get(node).getOutcomingEdgeList().iterator();
		while(i.hasNext() == true){
			NormalEdge tempedge = i.next();
			if (tempedge.getCapability().equals(capability) == true)
				return tempedge;
		}
		Iterator <OPNode> j = this.graph.get(node).getOPNodeList().iterator();
		while(j.hasNext() == true){
			OPNode tempnode = j.next();
			if (tempnode.getCapability().equals(capability) == true)
				return tempnode.getIncomingEdge();
		}
		return null;
	}
	
	/**
	 * Gets the wts.
	 *
	 * @return the wts
	 */
	public HashMap<WorldNode, WorldNode> getWTS(){
		return this.graph;
	}
	
	/**
	 * Removes the node.
	 *
	 * @param node
	 *            the node
	 */
	//Rimuove un nodo dal grafo
	public void removeNode(WorldNode node){
		this.graph.remove(node);
	}
	
	/**
	 * Removes the edge.
	 *
	 * @param sourcenode
	 *            the sourcenode
	 * @param destnode
	 *            the destnode
	 */
	//Rimuove un arco da un Worldnodo del grafo, modifica lista in uscita del nodo sorgente e lista in entrata del nodo destinazione
	public void removeEdge(WorldNode sourcenode, WorldNode destnode){
		if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				WorldNode tempnode = (WorldNode) destnode;
				this.graph.get(sourcenode).removeOutcomingEdge(new NormalEdge(sourcenode, tempnode, null));
				this.graph.get(tempnode).removeIncomingEdge(new NormalEdge(sourcenode, tempnode, null));
		}
	}
	
	/**
	 * Removes the edge.
	 *
	 * @param sourcenode
	 *            the sourcenode
	 * @param destnode
	 *            the destnode
	 * @return the OP node
	 */
	//Rimuove un arco da un WorldNode del grafo verso un OPNode, rimuovendo l'OPNode dalla lista nel WorldNode, restituisce l'OPNode
	public OPNode removeEdge(WorldNode sourcenode, OPNode destnode){
		if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				return this.graph.get(sourcenode).removeOPNode(destnode);
		}
		return null;
	}
		
	
	/**
	 * Gets the solutions.
	 *
	 * @param start
	 *            the start
	 * @param exitNodeMap
	 *            the exit node map
	 * @return the solutions
	 */
	public ArrayList<ArrayList<WorldNode>> getSolutions(WorldNode start, HashMap<WorldNode, WorldNode> exitNodeMap){
		ArrayList<ArrayList<WorldNode>> result = new ArrayList<>();
		ArrayList<WorldNode> pathNode = new ArrayList <WorldNode>();
		HashMap <WorldNode,Integer> checkedNode = new HashMap <WorldNode, Integer>();
		WTSVisit(start, exitNodeMap, result, pathNode, checkedNode);
		return result;
	}
	
	
	/*Semplice visita. Percorre tutti i WorldNode ed una volta esauriti i NormalEdge inizia a scorrere, se presente
	*La lista degli OPNode ripetendo il processo.
	*/
	
	/**
	 * WTS visit.
	 *
	 * @param start
	 *            the start
	 * @param exitNodeMap
	 *            the exit node map
	 * @param result
	 *            the result
	 * @param pathNode
	 *            the path node
	 * @param checkedNode
	 *            the checked node
	 */
	private void WTSVisit(WorldNode start, HashMap<WorldNode, WorldNode> exitNodeMap, ArrayList<ArrayList<WorldNode>> result, 
			ArrayList<WorldNode> pathNode, HashMap <WorldNode,Integer> checkedNode){
		
		if(exitNodeMap.containsKey(start) == true){
			pathNode.add(start);
			ArrayList<WorldNode> temp = new ArrayList<>(pathNode);
			result.add(temp);
			pathNode.remove(start);
			return;
		}
		
		for(NormalEdge edge : this.graph.get(start).getOutcomingEdgeList()){
			if(checkedNode.containsKey(start) == false){
				pathNode.add(start);
				checkedNode.put(start, 1);
				WTSVisit(edge.getDestination(), exitNodeMap, result, pathNode, checkedNode);
				pathNode.remove(start);
			}
		}
		
		for(OPNode node : this.graph.get(start).getOPNodeList()){
			for(int j=0; j<this.graph.get(start).getOPNodeList().size(); j++){
				if(checkedNode.containsKey(node.getOutcomingEdge().get(j).getDestination()) == false){
					pathNode.add(node.getOutcomingEdge().get(j).getDestination());
					checkedNode.put(start, 1);
					WTSVisit(node.getOutcomingEdge().get(j).getDestination(), exitNodeMap, result, pathNode, checkedNode);
					pathNode.remove(node.getOutcomingEdge().get(j).getDestination());
				}
			}
		}
		checkedNode.remove(start);
	}
	
	
		
}
