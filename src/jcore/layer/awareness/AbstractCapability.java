package layer.awareness;

import java.util.Set;

import org.inferred.freebuilder.FreeBuilder;

import layer.semantic.Condition;
import layer.semantic.evolution.AddStatement;
import layer.semantic.evolution.EvolutionScenario;

@FreeBuilder
public interface AbstractCapability extends RunTimeEntity {
//	private String unique_id;
//	
//	private Set<EvolutionScenario> evolution_set;
//	private Condition pre;
//	private Condition post;
//	
//	public AbstractCapability(String unique_id, Set<EvolutionScenario> evolution_set, Condition pre, Condition post) {
//		super();
//		this.unique_id = unique_id;
//		this.evolution_set = evolution_set;
//		this.pre = pre;
//		this.post = post;
//	}
//	
//	public void test_evo() {
//		EvolutionScenario s = new EvolutionScenario.Builder()
//				.setName("uno")
//				.addOperators(new AddStatement(null))
//				.build();
//	}

	public String getName();
	public Set<EvolutionScenario> getEvolutions();
	public Condition getPreCondition();
	public Condition getPostCondition();
	
	class Builder extends AbstractCapability_Builder {}
	
}
