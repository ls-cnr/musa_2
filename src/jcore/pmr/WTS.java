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
import pmr.ProblemExploration.ExplorationNode;
import pmr.ProblemExploration.MultipleExplorationNode;
import pmr.ProblemExploration.NormalExplorationNode;
import pmr.graph.Edge;
import pmr.graph.EvolutionEdge;
import pmr.graph.Node;
import pmr.graph.NormalEdge;
import pmr.graph.OPNode;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;

//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	
	private HashMap<WorldNode, WorldNode> graph;
	
/*	/*implementazione secondo Ruggero Creo una funzione di Hash per i Facts, implemento una mappa <Integer, lista di nodi>
	 dove Integer è l'hashcode del predicato, e la lista degli stati del mondo contiene gli stati che a loro volta contegono il predicato.
	 Inoltre ho un'altra mappa <Integer, lista di nodi> dove integer, questa volta corrispondente al numero di predicati contenuti,
	 è associato a quali stati del mondo corrispondono.
	 
	private HashMap<Integer, ArrayList<Node>> listPredicates; //Questo Integer è l'hashcode del predicato
	private HashMap<Integer, ArrayList<Node>> NPredicates;		//Questo Integer è un semplice int
	
	public void addNode(Node node){
		if(addFactsOnList(node) == true){	//Se ho aggiunto almeno un fatto, il nodo è nuovo. Altrimenti eseguo altre verifiche
			this.graph.put(node, new ArrayList<Edge>()); //IndexOutOfBoundRange.
			this.UpdateNpredicates(node);
			//NPredicates.put(node.getNFacts(), new ArrayList(Node)); //il primo è l'indice il secondo la lista dei nodi con 1 nodo.
			Iterator itr = node.getFacts.iterator();
			while(itr.hasNext()){
				Integer tempHashCode = new Integer(itr.next().getHash());	//prende l'intero relativo all'hash del primo fatto
				if(listPredicates.containsKey(tempHashCode)){
					ArrayList<Node> tempNodeListEdit = listPredicates.get(tempHashCode);
					tempNodeListEdit.add(node);
					listPredicates.replace(tempHashCode, tempNodeListEdit); 
				}
			}
		}
		//aggiorno la list predicates con tutti i nuovi predicati del nodo di sopra.
		else for(i=0; i<node.getNFacts(); i++){
				if(HashAssociato a tutti i predicati non è contenuto in listPredicates ferma e aggiorna tutto.
		else	stato del mondo presente, aggiorna gli archi del grafo.
			}
	}
	
		//Controllo se il singolo fatto è già stato inserito nella mappa
	private boolean isFactOnMap(DLPHead fact){
		if(this.listPredicates.containsKey(fact.hashCode()) == true){
			return true;
		}
		else{
			return false;
		}
	}
	/*addFactsOnList, aggiunge una nuova key alla mappa dei predicati se il predicato non è già contenuto, restituendo true
	 * se il predicato era già presente in mappa allora aggiunge alla lista dei nodi che lo contengono il nuovo nodo, restituendo false
	public boolean addFactsOnList(Node node){
		int flag = 0;
		ArrayList<Node> nodelist = new ArrayList<Node>();
		nodelist.add(node);
		for(DLPHead fact : node.getFactsList()){
			if(this.isFactOnMap(fact)){
				this.listPredicates.put(new Integer(fact.hashCode()), nodelist);
				flag = 1;
			}
			else{
				ArrayList<Node> temp = this.listPredicates.get(fact.hashCode());
				temp.add(node);
				this.listPredicates.replace(fact.hashCode(), temp);
			}
		}
		if(flag == 1)
			return true;
		else
			return false;
	}
	
	/*Controllo se la cella corrispondente al numero di fatti del nodo esiste, aggiungendo il nodo alla lista ad essa associata.
	 * Se la cella è vuota la creo.
	 
	public void UpdateNpredicates(Node node){
		if(this.NPredicates.containsKey(node.getNFacts())){
			ArrayList<Node> temp = this.NPredicates.get(node.getNFacts());
			temp.add(node);
			this.NPredicates.replace(node.getNFacts(), temp);
		}
		else{
			ArrayList<Node> temp = new ArrayList<Node>();
			temp.add(node);
			this.NPredicates.put(node.getNFacts(), temp);
		}
	}*/

	public WTS(){ 
		this.graph = new HashMap<WorldNode, WorldNode> ();
		WorldNode tempnode = new WorldNode(null);
		ArrayList<NormalEdge> templist = new ArrayList<NormalEdge>();
		tempnode.setOutcomingEdgeList(templist);
		this.graph.put(tempnode, tempnode);
	}
	
	//Aggiungi nodo alla mappa, restituisce true se lo aggiunge, false se lo trova e non lo aggiunge
	public boolean addNode(ExplorationNode newnode){
			if(newnode instanceof NormalExplorationNode){
				//Aggiorno il nodo presente sia nel grafo che nel newnode, aggiungo al grafo il nodo destinazione presente in newnode 
				//aggiungendo un arco in entrata dal nodo source e aggiorno gli archi in uscita dal nodo source aggiungendo
				//quello diretto verso il nodo destination.
				NormalExplorationNode tempnode = (NormalExplorationNode) newnode;
				this.addSafeNode(tempnode.getDestinationList().get(0));
				WorldNode destination2 = this.graph.get(tempnode.getDestinationList().get(0));
				//Metodo che aggiunge l'arco in entrata ed in uscita
				this.addEdge(tempnode.getSource().getNode(), destination2, tempnode.getCapability());			}
			else{
				//Aggiorno il nodo presente nel grafo, aggiungendogli un OPNode all'interno
				//Se il nodo contiene già quell'OPNode non compio nessuna operazione altrimenti lo aggiungo
				MultipleExplorationNode tempnode = (MultipleExplorationNode) newnode;
				WorldNode source2 = this.graph.get(tempnode.getSource().getNode());
				
				//Per ogni Enodo destinazione, provo ad aggiungere il WorldNode contenuto
				for(ENode temp : newnode.getDestinationList()){
					WorldNode node = temp.getNode();
					//aggiorno la lista degli incoming edge del nodo già esistente.
					//Prendendo l'OPNode come source, il nodo stesso come dest e lo scenario dalla mappa nodo-scenario nell'explnode
					//E la lista degli outcomingEdge dell'OPNode, a fine operazioni aggiorno l'OPNode nella lista Degli OPNode del nodo source
					this.addSafeNode(node);
					this.addEdge(tempnode.getOPNode(), this.graph.get(node), tempnode.getScenarioMap().get(node));
				}
				source2.addXORNode(tempnode.getOPNode());
			}
	}
	
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
	
	//Aggiunge un arco ad un OPnodo sorgente, modificando la lista degli archi uscenti del nodo stesso e la lista degli archi entranti del nodo destinazione
	//arco di tipo EvolutionEdge
	public void addEdge(OPNode sourcenode, WorldNode destnode, EvolutionScenario scenario){
			sourcenode.addOutcomingEdge(new EvolutionEdge(sourcenode, destnode, scenario));
			destnode.addIncomingEdge(new EvolutionEdge(sourcenode, destnode, scenario));
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
