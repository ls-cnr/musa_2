package org.icar.musa.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.exception.*;

import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.Rule;

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
	
	private String hash=null;
	
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
		hash=null;
	}

	/**
	 * Adds the fact as ASP.
	 *
	 * @param fact_to_add the fact to add
	 */
	public void addFact_asASP(ExtDLPHead fact_to_add) {
		facts.add(fact_to_add);
		hash=null;
	}

	/**
	 * Removes the fact safely as ASP.
	 *
	 * @param fact_to_remove the fact to remove
	 */
	public void removeFact_safely_asASP(ExtDLPHead fact_to_remove) {
		facts.remove(fact_to_remove);
		hash=null;
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
	    if (this.facts.size() == other.getFactsNumber()) {
	    		if (this.toSortedString().equals(other.toSortedString())) {
	    			return true;
	    		}
	    } 
	    	
	    return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public String toString(){
		return toSortedString();
		
//		String res = new String("["); 
//		Iterator i = this.facts.iterator();
//		int ind=0;
//		while(i.hasNext()){
//			ExtDLPHead temp = (ExtDLPHead) i.next();
//			if (ind==0) {
//				res = res + temp.toString();
//			} else {
//				res = res + "," + temp.toString();
//			}
//			ind++;
//		}
//		res = res + "]";
//		//System.out.println(res);
//		return res;
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

	private String toSortedString() {
//		if (hash==null) {
//			hash=toSortedString_SPSreconfig();
//		}
//		
//		if (true) return hash;
		
		String res = new String("[");
		
		List<String> al = new ArrayList<String>();
		for (ExtDLPHead f : facts) {
			al.add( f.toString() );
		}

		Collections.sort(al);
				
		Iterator<String> i = al.iterator();
		int ind=0;
		while(i.hasNext()){
			String temp = (String) i.next();
			if (ind==0) {
				res = res + temp;
			} else {
				res = res + "," + temp;
			}
			ind++;
		}
		res = res + "]";
		return res;
	}
	
	/* metodo aggiunto solo per lo scenario SPSReconfigurationEasy (da rimuovere in futuro) */
	public String toSortedString_SPSreconfig() {
		String res = new String("[");
		
		String main="";
		String i1="";
		String i2="";
		String i3="";
		String i4="";
				
		for (ExtDLPHead f : facts) {
			Iterator it = f.getTerms().iterator();
			String arg = it.next().toString();
			if (arg.equals("main")) {
				main = f.toString();
			}
			if (arg.equals("i1")) {
				i1 = f.toString();
			}
			if (arg.equals("i2")) {
				i2 = f.toString();
			}
			if (arg.equals("i3")) {
				i3 = f.toString();
			}
			if (arg.equals("i4")) {
				i4 = f.toString();
			}			
		}
		res="["+main+","+i1+","+i2+","+i3+","+i4+"]";
		return res;
	}


}
