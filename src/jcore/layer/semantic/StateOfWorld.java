package layer.semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import layer.semantic.exception.*;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.Rule;

/**                                                                            
 * This class encapsulate a possible State Of World, i.e. a set of facts describing what the system knows about the environment.  
 * for goal-testing reasons, each fact is given in ASP, i.e. a subclass of DLPHead
 * @author icar-aose
 * @version 1.0.0
 */

public class StateOfWorld {
	
	/** 
	 * Facts: Set of Facts.
	 * A fact is considered as a first order variable-free statement to which is possible to assign a truth value. 
	 * */
	private HashSet<DLPHead> facts;
	
	/**
	 * Instantiates a new state of world.
	 */
	public StateOfWorld() {
		facts = new HashSet<DLPHead>();
	}

	/**
	 * Instantiates a new state of world.
	 *
	 * @param facts the facts
	 */
	protected StateOfWorld(HashSet<DLPHead> facts) {
		this.facts = facts;
	}

	/**
	 * Gets the facts.
	 *
	 * @return the facts
	 */
	public HashSet<DLPHead>getFacts(){
		return this.facts;
	}
	
	public ArrayList<DLPHead> getFactsList(){
		ArrayList<DLPHead> factlist = new ArrayList<DLPHead>();
		Iterator<DLPHead> it = this.facts.iterator();
		while(it.hasNext() == true){
			factlist.add(it.next());
		}
		return factlist;
	}

	
	//Numero di fatti contenuti nell'oggetto.
	public int getFactsNumber(){
		return this.facts.size();
	}
	
	/**
	 * Adds the fact as string.
	 *
	 * @param fact_to_add the fact to add
	 * @throws ParseException the parse exception
	 * @throws NotAllowedInAStateOfWorld the not allowed in A state of world
	 */
	public void addFact_asString(String fact_to_add) throws ParseException, NotAllowedInAStateOfWorld {
		Rule r = ASPParser.parseRule(fact_to_add);
		if (r.isFact()) {
			facts.add(r.getConclusion());
		} else {
			throw new NotAllowedInAStateOfWorld();
		}
	}

	/**
	 * Adds the fact as ASP.
	 *
	 * @param fact_to_add the fact to add
	 */
	public void addFact_asASP(DLPHead fact_to_add) {
		facts.add(fact_to_add);
	}

	/**
	 * Removes the fact safely as ASP.
	 *
	 * @param fact_to_remove the fact to remove
	 */
	// TO TEST --> verificare che il metodo 'HashSet.remove' funzioni con due istanze di DLPHead che abbiano lo stesso valore
	// HashSet non permette di aggiungere due istanze con lo stesso valore
	public void removeFact_safely_asASP(DLPHead fact_to_remove) {
		facts.remove(fact_to_remove);
	}
	
	/*Ridefinisco l'equala tra oggetti StateOfWorld, confrontando la lista di fatti in essi contenuta. Se i fatti differiscono
	 * Gli StateOfWorld sono diversi, altrimenti lo StateOfWorld è lo stesso.
	 * HashSet implementa l'equals facendo il confronto tra la somma degli hashCode() degli elementi contenuti. DLPHead implementa
	 * una hashCode() da loro ridefinita in maniera opportuna*/
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!StateOfWorld.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    StateOfWorld other = (StateOfWorld) obj;
	    if (this.facts.equals(other.getFactsList()) && this.facts.size() == other.getFactsNumber()) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	/*Ridefinisco hashCode() per la classe StateOfWorld, rifacendomi all'hashCode() implementato in Set che come spiegato sopra funziona.
	 * Inoltre questo valore (che anche se molto raramente, potrebbe risultare essere uguale per set di diversa dimensione) lo moltiplico
	 * per l'effettivo numero di fatti contenuti, eliminando questa possibilità di collisione*/
	@Override
	public int hashCode(){
		return this.facts.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@SuppressWarnings("unchecked")
	public StateOfWorld clone() {
		return new StateOfWorld( (HashSet<DLPHead>) this.facts.clone() );
	}
	
	// @Override hashCode(), solo se si può creare una funzione che rende gli stati del mondo univoci.

}
