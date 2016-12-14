package example;

import layer.awareness.AbstractCapability;
import layer.semantic.Condition;
import layer.semantic.evolution.AddStatement;
import layer.semantic.evolution.EvolutionScenario;

public class FreeBuilderExample {

	public static void main(String[] args) {
		
		EvolutionScenario s = new EvolutionScenario.Builder()
				.setName("uno")
				.removeOperators(new AddStatement("is_ready"))
				.addOperators(new AddStatement("done"))
				.build();
	
		AbstractCapability cap1 = new AbstractCapability.Builder()
				.setName("classifier")
				.setPreCondition(new Condition("is_ready"))
				.setPostCondition(new Condition("done"))
				.addEvolutions(s)
				.build();
	
		cap1.getName();
	}
}
