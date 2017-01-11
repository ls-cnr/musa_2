package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;

public class EvolutionEdge extends Edge {

	private EvolutionScenario scenario; //in abstractcapability abbiamo un set di scenari che differenziano lo stato del mondo da un altro
										//Nel caso dello XORNode, lo scenario che etichetta l'arco, è uno? o tutti?
	
	public EvolutionEdge(Node source, Node destination, AbstractCapability capability, EvolutionScenario scenario){
		super(source, destination, capability);
		this.scenario = scenario;
	}
}
