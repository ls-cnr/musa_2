package layer.semantic;

import java.util.HashSet;

import layer.semantic.exception.*;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.Rule;

/**********************************************************/
/* STATE OF THE WORLD                                                                              
 * This class encapsulate a possible State Of World, i.e. a set of facts describing what the system knows about the environment.  
 * for goal-testing reasons, each fact is given in ASP, i.e. a subclass of DLPHead
 */                                
/**********************************************************/

public class StateOfWorld {
	private HashSet<DLPHead> facts;
	
	public StateOfWorld() {
		facts = new HashSet<DLPHead>();
	}

	protected StateOfWorld(HashSet<DLPHead> facts) {
		this.facts = facts;
	}

	public HashSet<DLPHead> getFacts() {
		return facts;
	}

	public void addFact_asString(String fact_to_add) throws ParseException, NotAllowedInAStateOfWorld {
		Rule r = ASPParser.parseRule(fact_to_add);
		if (r.isFact()) {
			facts.add(r.getConclusion());
		} else {
			throw new NotAllowedInAStateOfWorld();
		}
	}

	public void addFact_asASP(DLPHead fact_to_add) {
		facts.add(fact_to_add);
	}

	// TO TEST --> verificare che il metodo 'HashSet.remove' funzioni con due istanze di DLPHead che abbiano lo stesso valore
	public void removeFact_safely_asASP(DLPHead fact_to_remove) {
		facts.remove(fact_to_remove);
	}
	
	@SuppressWarnings("unchecked")
	public StateOfWorld clone() {
		return new StateOfWorld( (HashSet<DLPHead>) this.facts.clone() );
	}


}
