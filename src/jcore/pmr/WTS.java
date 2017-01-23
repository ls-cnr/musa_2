package pmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;
import net.sf.tweety.logics.pl.semantics.PossibleWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import pmr.graph.Edge;
import pmr.graph.EvolutionEdge;
import pmr.graph.Node;
import pmr.graph.NormalEdge;
import pmr.graph.OPEdge;
import pmr.graph.OPNode;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpantionNode;
import pmr.probexp.MultipleExpantionNode;
import pmr.probexp.NormalExpantionNode;

//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	
	private HashMap<WorldNode, WorldNode> graph;

	public WTS(){ 
		this.graph = new HashMap<WorldNode, WorldNode> ();
		WorldNode tempnode = new WorldNode(null);
		ArrayList<NormalEdge> templist = new ArrayList<NormalEdge>();
		tempnode.setOutcomingEdgeList(templist);
		this.graph.put(tempnode, tempnode);
	}
	
	
	//Aggiungi nodo alla mappa, restituisce true se lo aggiunge, false se lo trova e non lo aggiunge
	public void addNode(ExpantionNode newnode){	
		if(newnode instanceof NormalExpantionNode){
			//Aggiorno il nodo presente sia nel grafo che nel newnode, aggiungo al grafo il nodo destinazione presente in newnode 
			//aggiungendo un arco in entrata dal nodo source e aggiorno gli archi in uscita dal nodo source aggiungendo
			//quello diretto verso il nodo destination.
			NormalExpantionNode tempnode = (NormalExpantionNode) newnode;
			this.addSafeNode(tempnode.getDestination().get(0).getWorldNode());
			WorldNode destination2 = this.graph.get(tempnode.getDestination().get(0).getWorldNode());
			
			//Metodo che aggiunge l'arco in entrata ed in uscita
			this.addEdge(tempnode.getSource().getWorldNode(), destination2, tempnode.getCapability());			}
		else{
			//Aggiorno il nodo presente nel grafo, aggiungendogli un OPNode all'interno
			MultipleExpantionNode exptempnode = (MultipleExpantionNode) newnode;
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
	
	//Aggiunta semplice al grafo tramite WorldNode
	public boolean addSafeNode(WorldNode source){
		if(this.graph.containsKey(source) == false){
			this.graph.put(source, source);
			return true;
		}
		else
			return false;
	}
	
	//Aggiunge un arco ad un Worldnodo sorgente, modificando la lista degli archi uscenti del nodo stesso e la lista degli archi entranti del nodo destinazione
	//arco di tipo NormalEdge
	public void addEdge(WorldNode sourcenode, WorldNode destnode, AbstractCapability capability){
			sourcenode.addOutcomingEdge(new NormalEdge(sourcenode, destnode, capability));
			destnode.addIncomingEdge(new NormalEdge(sourcenode, destnode, capability));
	}
	
	
	public HashMap<WorldNode, WorldNode> getWTS(){
		return this.graph;
	}
	
	//Rimuove un nodo dal grafo
	public void removeNode(WorldNode node){
		this.graph.remove(node);
	}
	
	//Rimuove un arco da un Worldnodo del grafo, modifica lista in uscita del nodo sorgente e lista in entrata del nodo destinazione
	public void removeEdge(WorldNode sourcenode, WorldNode destnode){
		if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				WorldNode tempnode = (WorldNode) destnode;
				this.graph.get(sourcenode).removeOutcomingEdge(new NormalEdge(sourcenode, tempnode, null));
				this.graph.get(tempnode).removeIncomingEdge(new NormalEdge(sourcenode, tempnode, null));
		}
	}
	
	//Rimuove un arco da un WorldNode del grafo verso un OPNode, rimuovendo l'OPNode dalla lista nel WorldNode, restituisce l'OPNode
	public OPNode removeEdge(WorldNode sourcenode, OPNode destnode){
		if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				return this.graph.get(sourcenode).removeOPNode(destnode);
		}
		return null;
	}
		
	public ArrayList<WorldNode> WTSVisit(WorldNode start){
		HashMap <WorldNode,Integer> checkedNode = new HashMap <WorldNode, Integer>();
		ArrayList<WorldNode> pathNode = new ArrayList <WorldNode>();
		
		if(start == null)
			return null;
		
		for(NormalEdge edge : this.graph.get(start).getOutcomingEdgeList()){
			if(checkedNode.containsKey(edge.getDestination()) == false){
				pathNode.add((WorldNode)edge.getDestination());
				pathNode.addAll(WTSVisit(edge.getDestination()));
			}
		}
		return pathNode;
	}
	
	
		
}
