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
	
	public EvolutionScenario getScenario(){
		return this.scenario;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof EvolutionEdge){
			EvolutionEdge temp = (EvolutionEdge)obj;
			
			/*faccio i controlli includendo la possibilità dei null evitando che crashi*/
			if(temp.getSource() == null && this.source != null)	return false;
			else if(temp.getSource() != null && this.source == null)	return false;

			if(temp.getDestination() == null && this.destination != null)	return false;
			else if(temp.getDestination() != null && this.destination == null)	return false;
			
			if(temp.getScenario() == null && this.scenario != null)	return false;
			else if(temp.getScenario() != null && this.scenario == null)	return false;
			
			if(this.source == null && temp.getSource() == null)
				if(this.destination == null && temp.getDestination() == null)	
					if(temp.getScenario() == null && this.scenario == null)		return true;
			
			if(this.source.equals(temp.getSource()) == false)				return false;
			if(this.destination.equals(temp.getDestination()) == false)		return false;
			return true;
		}
		else return false;
	}
	
	
	@Override
	public int hashCode(){
		if(this == null)	return 0;
		return this.source.hashCode() + this.destination.hashCode() + this.capability.hashCode() + this.scenario.hashCode();
	}
}
