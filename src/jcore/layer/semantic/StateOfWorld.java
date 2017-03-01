package layer.semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import layer.semantic.exception.*;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.Rule;
import translator.ExtDLPHead;

/**                                                                            
 * This class encapsulate a possible State Of World, i.e. a set of facts describing what the system knows about the environment.  
 * for goal-testing reasons, each fact is given in ASP, i.e. a subclass of MyDLPHead
 * @author icar-aose
 */

public class StateOfWorld {
	
	/** 
	 * Facts: Set of Facts.
	 * A fact is considered as a first order variable-free statement to which is possible to assign a truth value. 
	 * */
	private HashSet<ExtDLPHead> facts;
	
	/**
	 * Instantiates a new state of world.
	 */
	public StateOfWorld() {
		facts = new HashSet<ExtDLPHead>();
	}

	/**
	 * Instantiates a new state of world.
	 *
	 * @param facts the facts
	 */
	protected StateOfWorld(HashSet<ExtDLPHead> facts) {
		this.facts = facts;
	}

	/**
	 * Gets the facts.
	 *
	 * @return the facts stored on this state of world
	 */
	public HashSet<ExtDLPHead>getFacts(){
		return this.facts;
	}
	
	public ArrayList<ExtDLPHead> getFactsList(){
		ArrayList<ExtDLPHead> factlist = new ArrayList<ExtDLPHead>();
		Iterator<ExtDLPHead> it = this.facts.iterator();
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
			facts.add(new ExtDLPHead(r.getConclusion().get(0)));
		} else {
			throw new NotAllowedInAStateOfWorld();
		}
	}

	/**
	 * Adds the fact as ASP.
	 *
	 * @param fact_to_add the fact to add
	 */
	public void addFact_asASP(ExtDLPHead fact_to_add) {
		facts.add(fact_to_add);
	}

	/**
	 * Removes the fact safely as ASP.
	 *
	 * @param fact_to_remove the fact to remove
	 */
	public void removeFact_safely_asASP(ExtDLPHead fact_to_remove) {
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
	    if (this.facts.size() == other.getFactsNumber() && this.facts.equals(other.getFacts()) == true) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public String toString(){
		String res = new String(""); 
		Iterator i = this.facts.iterator();
		while(i.hasNext()){
			ExtDLPHead temp = (ExtDLPHead) i.next();
			res = res + temp.toString() + "\n";
		}

		//System.out.println(res);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public int hashCode(){
		return this.facts.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@SuppressWarnings("unchecked")
	public StateOfWorld clone() {
		return new StateOfWorld( (HashSet<ExtDLPHead>) this.facts.clone() );
	}

}
