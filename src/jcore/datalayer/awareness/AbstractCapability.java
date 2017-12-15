package datalayer.awareness;

import java.util.Set;

import org.inferred.freebuilder.FreeBuilder;

import datalayer.world.Condition;
import datalayer.world.evolution.AddStatement;
import datalayer.world.evolution.CapabilityEvoScenario;
import datalayer.world.evolution.EvolutionScenario;

/**
 * The Class AbstractCapability encapsulate a capability. A capability is an atomic and self-contained action the system may
 * intentionally use to address a given evolution of the state of the world. Each abstract capability can be implemented by several
 * concrete capabilities.
 * @author icar-aose
 */

//@FreeBuilder
public class AbstractCapability implements RunTimeEntity {
	
	/** The unique id that identify a capability. */
	private String unique_id;
	
	/** The evolution set are the differences between a state of the world and his next, generated after executing the capability . */
	private Set<EvolutionScenario> evolution_set;
	
	/** The pre is the pre-condition that needs to be true to execute the capability */
	private Condition pre;
	
	/** The post is a condition that the capability must satisfy to prove it has been successfully executed */
	private Condition post;
	
	/**
	 * Instantiates a new abstract capability.
	 *
	 * @param unique_id
	 *            the unique id
	 * @param evolution_set
	 *            the evolution set
	 * @param pre
	 *            the pre
	 * @param post
	 *            the post
	 */
	public AbstractCapability(String unique_id, Set<EvolutionScenario> evolution_set, Condition pre, Condition post) {
		super();
		this.unique_id = unique_id;
		this.evolution_set = evolution_set;
		this.pre = pre;
		this.post = post;
	}
	
	/**
	 * Gets the EvolutionScenario Set of this capability.
	 * 
	 * @return the evolution_set of this capability.
	 */
	public Set<EvolutionScenario> getScenarioSet(){
		return this.evolution_set;
	}
	
	/**
	 * Gets the EvolutionScenario Set of this capability.
	 * 
	 * @return the evolution_set of this capability.
	 */
	public String getId(){
		return this.unique_id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AbstractCapability){
			AbstractCapability temp = (AbstractCapability) obj;
			if(this.getId() == temp.getId())	return true;
			else								return false;
		}
		else return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		int i = 0;
		int sum = 0;
		String temp = this.getId();
		int len = temp.length();
		for(i = 0; i < len; i++){
			sum += (temp.charAt(i)*temp.length());
		}
		return sum;
	}
	
//	public void test_evo() {
//		EvolutionScenario s = new EvolutionScenario.Builder()
//				.setName("uno")
//				.addOperators(new AddStatement(null))
//				.build();
//	}

//	public String getName();
	public Condition getPreCondition(){
		return this.pre;
	}
	public Condition getPostCondition(){
		return this.post;
	}
//	
//	class Builder extends AbstractCapability_Builder {}
	
}
