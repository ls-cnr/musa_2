package layer.semantic;

import java.util.HashSet;
import java.util.Iterator;

import layer.semantic.exception.*;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

public class AssumptionSet {
	//private HashSet<Rule> assumptions;
	private Program base;
	
	public AssumptionSet() {
		//assumptions = new HashSet<Rule>();
		base = new Program();		
	}
	
	public void addAssumption_asString(String assumption_to_add) throws ParseException, NotAllowedInAnAssumptionSet {
		Rule r = ASPParser.parseRule(assumption_to_add);
		add_assumption(r);
	}

	/*public HashSet<Rule> getRules() {
		return assumptions;
	}*/
	
	public Program getASPClone() {
		return base.clone();
	}

	private void add_assumption(Rule r) {
		//assumptions.add(r);
		base.add(r);
	}


}
