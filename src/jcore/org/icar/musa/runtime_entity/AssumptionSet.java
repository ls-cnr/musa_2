package org.icar.musa.runtime_entity;

import org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet;

import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

/**                                                                            
 * The Class AssumptionSet
 * @author icar-aose
 * @version 1.0.0
 */

public class AssumptionSet {
	
	/** The base. */
	//private HashSet<Rule> assumptions;
	private Program base;
	
	/**
	 * Instantiates a new assumption set.
	 */
	public AssumptionSet() {
		//assumptions = new HashSet<Rule>();
		base = new Program();		
	}
	
	/**
	 * Adds the assumption as string.
	 *
	 * @param assumption_to_add the assumption to add
	 * @throws ParseException the parse exception
	 * @throws NotAllowedInAnAssumptionSet the not allowed in an assumption set
	 */
	public void addAssumption_asString(String assumption_to_add) throws ParseException, NotAllowedInAnAssumptionSet {
		Rule r = ASPParser.parseRule(assumption_to_add);
		add_assumption(r);
	}

	/*public HashSet<Rule> getRules() {
		return assumptions;
	}*/
	
	/**
	 * Gets the ASP clone.
	 *
	 * @return the ASP clone
	 */
	public Program getASPClone() {
		return base.clone();
	}

	/**
	 * Adds the assumption.
	 *
	 * @param r the r
	 */
	private void add_assumption(Rule r) {
		//assumptions.add(r);
		base.add(r);
	}

	@Override
	public String toString() {
		return base.toString();
	}
	
	


}
