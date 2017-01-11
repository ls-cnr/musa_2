package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.EvolutionScenario;

public class XORNode extends OPNode{
	public XORNode(StateOfWorld worldState, Node parent, AbstractCapability capability, EvolutionScenario scenario){
		super(worldState, parent, capability, scenario);
	}
}
