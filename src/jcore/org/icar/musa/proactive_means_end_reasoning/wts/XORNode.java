package datalayer.world.wts;

import datalayer.awareness.AbstractCapability;
import datalayer.world.StateOfWorld;
import datalayer.world.evolution.EvolutionScenario;

public class XORNode extends OPNode{
	public XORNode(String capability, int score){
		super(capability, score);
	}
	
	public XORNode(String capability, int score, String agent){
		super(capability, score, agent);
	}
}
