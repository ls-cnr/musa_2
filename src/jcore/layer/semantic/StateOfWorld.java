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
	 * @return the facts stored on this state of world
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

	
	/** Gets the number of facts stored in this state of world
	 * 
	 * @return the number of facts.
	 */
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
	public void removeFact_safely_asASP(DLPHead fact_to_remove) {
		facts.remove(fact_to_remove);
	}
	
	/**	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/* perform an equals between the StateOfWorld stored inside the nodes.*/
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!StateOfWorld.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    StateOfWorld other = (StateOfWorld) obj;
	    if (this.facts.equals(other.getFacts()) && this.facts.size() == other.getFactsNumber()) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
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

}
