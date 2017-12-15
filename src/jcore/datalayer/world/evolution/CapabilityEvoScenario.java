package datalayer.world.evolution;

import java.util.HashSet;
import java.util.Set;

import communication.translator.ExtDLPHead;
import exception.NotAllowedInAStateOfWorld;
import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.Rule;

public class CapabilityEvoScenario implements EvolutionScenario {
	
	String name;
	Set<EvolutionOperator> operators;
	
	public CapabilityEvoScenario(String name, Set<EvolutionOperator> operators){
		this.name = name;
		this.operators = new HashSet<EvolutionOperator>(operators);
	}
	
	public void addOperator_asString(String fact_to_add) throws ParseException, NotAllowedInAStateOfWorld {
		Rule r = ASPParser.parseRule(fact_to_add);
		if (r.isFact()) {
			operators.add(new AddStatement((ExtDLPHead)r.getConclusion()));
		} else {
			throw new NotAllowedInAStateOfWorld();
		}
	}
	
	public void removeOperator_asString(String fact_to_add) throws ParseException, NotAllowedInAStateOfWorld {
		Rule r = ASPParser.parseRule(fact_to_add);
		if (r.isFact()) {
			operators.add(new RemoveStatement((ExtDLPHead)r.getConclusion()));
		} else {
			throw new NotAllowedInAStateOfWorld();
		}
	}
	
	public String getName(){
		return this.name;
	}
	public Set<EvolutionOperator> getOperators(){
		return this.operators;
	}

}
