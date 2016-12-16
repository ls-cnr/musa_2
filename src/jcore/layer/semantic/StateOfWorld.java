package layer.semantic;

import java.util.HashSet;

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
	public HashSet<DLPHead> getFacts() {
		return facts;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@SuppressWarnings("unchecked")
	public StateOfWorld clone() {
		return new StateOfWorld( (HashSet<DLPHead>) this.facts.clone() );
	}


}
