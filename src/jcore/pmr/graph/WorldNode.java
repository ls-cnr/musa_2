package pmr.graph;

import java.util.ArrayList;
import java.util.HashSet;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;

// TODO: Auto-generated Javadoc
/**
 * The Class WorldNode.
 */
public class WorldNode implements Node {

	/** The world state. */
	//stato del mondo associato al nodo
	private StateOfWorld worldState;
	
	/** The Nfacts. */
	//numero di fatti contenuti nello stato del mondo associato
	private int Nfacts;
	
	/** The outcoming edge list. */
	//lista degli archi in uscita dal nodo
	private ArrayList<NormalEdge> outcomingEdgeList;
	
	/** The incoming edge list. */
	//lista degli archi in entrata nel nodo
	private ArrayList<Edge> incomingEdgeList;
	
	/** The OP list. */
	//lista dei nodi fittizzi collegati al nodo
	private ArrayList<OPNode>	OPList;
	
	/** The hash code. */
	//hashCode per velocizzare i calcoli
	private int hashCode;
	
	/**
	 * Instantiates a new world node.
	 *
	 * @param worldState
	 *            the world state
	 */
	public WorldNode(StateOfWorld worldState){
		super();
		this.worldState = worldState;
		if(worldState == null){
			this.Nfacts = 0;
		}
		else{
			this.Nfacts = worldState.getFactsNumber();
		}
			this.outcomingEdgeList = new ArrayList<NormalEdge>();
			this.incomingEdgeList = new ArrayList<Edge>();
			this.OPList = new ArrayList<OPNode>();
			this.hashCode = this.hashCode_once();
	}
	
	
	/**
	 * Adds the incoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	//Aggiunge un arco alla lista degli incomingEdge usando l'arco stesso, ritorna falso se la lista contiene già l'arco e non lo aggiunge
	public boolean addIncomingEdge(Edge edge){
		NormalEdge nortemp = null;
		EvolutionEdge evotemp = null;
		if(edge instanceof NormalEdge){
			nortemp = (NormalEdge) edge;
			if(this.incomingEdgeList.contains(nortemp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(nortemp);
				return true;
			}
		}
		
		else{
			evotemp = (EvolutionEdge) edge;
			if(this.incomingEdgeList.contains(evotemp) == true){
				return false;
			}
			else{
				this.incomingEdgeList.add(evotemp);
				return true;
			}
		}
	}
	
	/**
	 * Adds the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	//Aggiunge un arco alla lista degli outcomingEdge usando l'arco stesso
	public boolean addOutcomingEdge(NormalEdge edge){
		if(this.outcomingEdgeList.contains(edge) == true){
				return false;
		}
		else{
			this.outcomingEdgeList.add(edge);
			return true;
		}
	}
	
	/**
	 * Sets the outcoming edge list.
	 *
	 * @param edges
	 *            the new outcoming edge list
	 */
	//Aggiunge una lista di archi in uscita all'oggetto.
	public void setOutcomingEdgeList(ArrayList<NormalEdge> edges){
		 this.outcomingEdgeList = edges;
	}
	
	/**
	 * Sets the incoming edge list.
	 *
	 * @param edges
	 *            the new incoming edge list
	 */
	//Aggiunge una lista di archi in entrata all'oggetto
	public void setIncomingEdgeList(ArrayList<Edge> edges){
		this.incomingEdgeList = edges;
	}
	
	/**
	 * Gets the n facts.
	 *
	 * @return the n facts
	 */
	//Restituisce il numero di fatti contenuti nello stato del mondo associato al nodo
	public int getNFacts(){
		return this.Nfacts;
	}
	
	/**
	 * Gets the world state.
	 *
	 * @return the world state
	 */
	//Restituisce lo stato del mondo associato al nodo
	public StateOfWorld getWorldState(){
		return this.worldState; 
	}
	
	/**
	 * Gets the outcoming edge list.
	 *
	 * @return the outcoming edge list
	 */
	//Restituisce la lista degli archi in uscita contenuti nel nodo
	public ArrayList<NormalEdge> getOutcomingEdgeList(){
		return this.outcomingEdgeList;
	}
	
	/**
	 * Gets the incoming edge list.
	 *
	 * @return the incoming edge list
	 */
	//Restituisce la lista degli archi in entrata contenuti nel nodo
	public ArrayList<Edge> getIncomingEdgeList(){
		return this.incomingEdgeList;
	}
	
	/**
	 * Gets the facts list.
	 *
	 * @return the facts list
	 */
	//Restituisce la lista dei fatti, lista contenuta nello state of world
	public ArrayList<DLPHead> getFactsList(){
		return this.worldState.getFactsList();
	}
	
	/**
	 * Gets the OP node list.
	 *
	 * @return the OP node list
	 */
	//Restituisce la lista degli OPNode
	public ArrayList<OPNode> getOPNodeList(){
		return this.OPList;
	}
	
	/**
	 * Removes the incoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	//Rimuove un arco dalla lista degli archi in entrata
	public boolean removeIncomingEdge(Edge edge){
		return this.incomingEdgeList.remove(edge);
	}
	
	/**
	 * Removes the outcoming edge.
	 *
	 * @param edge
	 *            the edge
	 * @return true, if successful
	 */
	//Rimuove un arco dalla lista degli archi in uscita
	public boolean removeOutcomingEdge(Edge edge){
		return this.outcomingEdgeList.remove(edge);
	}
	
	/**
	 * Adds the OP node.
	 *
	 * @param node
	 *            the node
	 * @return true, if successful
	 */
	//Aggiunge un nodo finto alla lista dei nodi finti
	public boolean addOPNode(OPNode node){
		if(this.OPList.contains(node) == true){
			return false;
		}
		else{
			this.OPList.add(node);
			return true;
		}
	}
	
	/**
	 * Removes the OP node.
	 *
	 * @param node
	 *            the node
	 * @return the OP node
	 */
	//Rimuove un nodo finto dalla lista dei nodi finti
	public OPNode removeOPNode(OPNode node){
		return this.OPList.remove(this.OPList.indexOf(node));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	//Utilizzo l'equals ridefinito in StateOfWorld per confrontare i nodi.
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!WorldNode.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    WorldNode other = (WorldNode) obj;
	    
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
	
	/**
	 * Hash code once.
	 *
	 * @return the int
	 */
	//Ho modifcato equals, di conseguenza modifico l'hashCode() di StateOfWorld per mantenere la regola delgli oggetti true per equals = Stesso hashcode
	private int hashCode_once(){
		if(this.worldState != null)	{
			return this.worldState.hashCode();
		}
		else
			return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//Richiamo il valore della funzione hashcode eseguita una volta alla creazione, lo stateofworld non cambia dopo l'inizializzazione.
	@Override
	public int hashCode(){
			return this.hashCode;
	}
}	
