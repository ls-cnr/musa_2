package pmr.graph;

import java.util.HashSet;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.EvolutionScenario;

/**
 * The Class OPNode.
 */
public abstract class OPNode extends Node {
	
	private EvolutionScenario scenario;
	public OPNode(StateOfWorld worldState){
		super(worldState);
	}
}
