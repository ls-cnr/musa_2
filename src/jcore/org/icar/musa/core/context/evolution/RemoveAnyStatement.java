package org.icar.musa.core.context.evolution;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.context.StateOfWorld;

import net.sf.tweety.lp.asp.syntax.DLPPredicate;

public class RemoveAnyStatement implements  EvolutionOperator {
	private String predicate;

	public RemoveAnyStatement(String string) {
		predicate = string;
	}

	@Override
	public StateOfWorld apply_transformation(StateOfWorld w) {
		List<ExtDLPHead> to_remove = new LinkedList<ExtDLPHead>(); 
		Iterator<ExtDLPHead> it = w.getFacts().iterator();
		while(it.hasNext() == true){
			ExtDLPHead fact = it.next();
			DLPPredicate literal = fact.getPredicates().iterator().next();
			if (literal.getName().equals(predicate) ) {
				to_remove.add(fact);
			}
		}
		for (ExtDLPHead item : to_remove) {
			w.removeFact_safely_asASP(item);
		}
		return w;
	}

}
