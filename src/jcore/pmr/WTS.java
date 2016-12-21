package pmr;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.tweety.logics.pl.semantics.PossibleWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import pmr.graph.Edge;
import pmr.graph.Node;

//Il solution graph, implementato come una mappa nodo-arco
public class WTS {
	private HashMap<Node, ArrayList<Edge>> graph;
	private PossibleWorld w;
	private DLPHead w2;
	
	/*implementazione secondo Ruggero Creo una funzione di Hash per i Facts, implemento una mappa <Integer, lista di stati del mondo>
	 dove Integer è l'hashcode del predicato, e la lista degli stati del mondo contiene gli stati che a loro volta contegono il predicato.
	 Inoltre ho un vettore che ad ogni cella, corrispondente al numero di predicati contenuti, indica quanti stati del mondo corrispondono.
	 
	private HashMap<Integer, ArrayList<Node> listPredicates;
	private ArrayList<ArrayList<Node>> NPredicates;
	
	public void addNode(Node node){
		if(NPredicates.get(node.getNFacts()) == null){
			this.graph.put(node, new ArrayList<Edge>()); //IndexOutOfBoundRange.
			NPredicates.add(node.getNFacts(), new ArrayList(Node)); //il primo è l'indice il secondo la lista dei nodi con 1 nodo.
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
	*/

	public WTS(){ 
		this.graph = new HashMap<Node, ArrayList<Edge>> ();
	}
	
	//Aggiungi nodo alla mappa
	public void addNode(Node node){
		if( this.graph.putIfAbsent(node, new ArrayList<Edge>()) != null);	//se provo ad aggiungere un nodo alla mappa
																			//ma il nodo è già presente, come lo segnalo?
		return;
	}
	
	
		
		
}
