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
import pmr.graph.OPNode;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;

//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	
	private HashMap<Node, Node> graph;
	
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
		this.graph = new HashMap<Node, Node> ();
		Node tempnode = new WorldNode(null);
		ArrayList<Edge> templist = new ArrayList<Edge>();
		tempnode.setOutcomingEdgeList(templist);
		this.graph.put(tempnode, tempnode);
	}
	
	//Aggiungi nodo alla mappa, restituisce true se lo aggiunge, false se lo trova e non lo aggiunge
	public boolean addNode(Node newnode){
		if(newnode instanceof XORNode){
			if(this.graph.containsKey(newnode) == false){
				this.graph.put((XORNode)newnode, (XORNode) newnode);
				return true;
			}
			else
				return false;
		}
		else if(newnode instanceof WorldNode){
				if(this.graph.containsKey(newnode) == false){
					this.graph.put((WorldNode)newnode, (WorldNode)newnode);
					return true;
				}
				else
					return false;
		}
		else{
			return false;
		}
	}
	
	//Aggiunge un arco ad un nodo sorgente, modificando la lista degli archi uscenti del nodo stesso e la lista degli archi entranti del nodo destinazione
	//arco di tipo NormalEdge
	public boolean addEdge(Node sourcenode, Node destnode, AbstractCapability capability){
		if(sourcenode instanceof WorldNode){
			//Se i nodi sono presenti nel grafo
			if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				//prendo i riferimenti a quei nodi
				Node tempnodesource = this.graph.get(sourcenode);
				Node tempnodedest = this.graph.get(destnode);
				
				//se aggiungendo l'arco, mi accorgo che è già presente nella lista, fermo tutto e ritorno falso
				if(tempnodesource.addOutcomingEdge(new NormalEdge(tempnodesource, tempnodedest, capability)) == false)
					return false;
				//Mi occupo di incominglist aggiornandolo
				if(tempnodedest.addIncomingEdge(new NormalEdge(tempnodesource, tempnodedest, capability)) == false)
					return false;
				
				//Rimpiazzo i due nodi con i loro nuovi valori
				this.graph.put(this.graph.get(sourcenode), tempnodesource);
				this.graph.put(this.graph.get(destnode), tempnodedest);
				return true;
			}
			else return false;
		}
		else{
			//errore
			return false;
		}
	}
	
	//Aggiunge un arco ad un nodo sorgente, modificando la lista degli archi uscenti del nodo stesso e la lista degli archi entranti del nodo destinazione
	//arco di tipo EvolutionEdge
	public boolean addEdge(Node sourcenode, Node destnode, AbstractCapability capability, EvolutionScenario scenario){
		if(sourcenode instanceof OPNode){
			//Se i nodi sono presenti nel grafo
			if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
				//se aggiungendo l'arco, mi accorgo che è già presente nella lista, fermo tutto e ritorno falso
				if(sourcenode.addOutcomingEdge(new EvolutionEdge(sourcenode, destnode, capability, scenario)) == false)
					return false;
				this.graph.replace(sourcenode, sourcenode);
				//Mi occupo di incominglist aggiornandolo
				destnode.addIncomingEdge(new EvolutionEdge(sourcenode, destnode, capability, scenario));
				this.graph.replace(destnode, destnode);
				return true;
			}
			else return false;
		}
		else{
			//errore
			return false;
		}
	}
	
	public HashMap<Node, Node> getWTS(){
		return this.graph;
	}
	
	//Rimuove un nodo dal grafo
	public void removeNode(Node node){
		this.graph.remove(node);
	}
	
	//Rimuove un arco da un nodo del grafo, modifica lista in uscita del nodo sorgente e lista in entrata del nodo destinazione
	public void removeEdge(Node sourcenode, Node destnode){
		if(this.graph.containsKey(sourcenode) == true && this.graph.containsKey(destnode) == true){
			this.graph.get(sourcenode).removeOutcomingEdge(new NormalEdge(sourcenode, destnode, null));
			this.graph.replace(sourcenode, sourcenode);
			
			this.graph.get(destnode).removeIncomingEdge(new NormalEdge(sourcenode, destnode, null));
			this.graph.replace(destnode, destnode);
		}
	}
		
	public ArrayList<Node> WTSVisit(Node start){
		HashMap <Node,Integer> checkedNode = new HashMap <Node, Integer>();
		ArrayList<Node> pathNode = new ArrayList <Node>();
		
		if(start == null)
			return null;
		
		for(Edge edge : this.graph.get(start).getOutcomingEdgeList()){
			if(edge instanceof NormalEdge){
				NormalEdge temp = (NormalEdge) edge;
				if(checkedNode.containsKey(temp.getDestination()) == false){
					pathNode.add((WorldNode)temp.getDestination());
					pathNode.addAll(WTSVisit(temp.getDestination()));
				}
			}
			else{
				EvolutionEdge temp = (EvolutionEdge) edge;
				if(checkedNode.containsKey(temp.getDestination()) == false){
					pathNode.add((XORNode)temp.getDestination());
					pathNode.addAll(WTSVisit(temp.getDestination()));
				}
			}
		}
		return pathNode;
	}
	
	
		
}
