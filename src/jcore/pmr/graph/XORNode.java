package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.EvolutionScenario;

public class XORNode extends OPNode{
	public XORNode(String capability, int score){
		super(capability, score);
	}
	
	public XORNode(String capability, int score, String agent){
		super(capability, score, agent);
	}
}
