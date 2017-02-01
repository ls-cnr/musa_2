package pmr.graph;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;

public class EvolutionEdge implements Edge {
	
	private OPNode source;
	private WorldNode destination;
	private EvolutionScenario scenario; 
	
	public EvolutionEdge(OPNode source, WorldNode destination, EvolutionScenario scenario){
		super();
		this.source = source;
		this.destination = destination;
		this.scenario = scenario;
	}
	
	public EvolutionScenario getScenario(){
		return this.scenario;
	}
	
	public OPNode getSource(){
		return this.source;
	}

	public WorldNode getDestination() {
		return this.destination;
	}
	
	public void setSource(OPNode source){
		this.source = source;
	}

	public void setDestination(WorldNode destination) {
		this.destination = destination;
	}
	
	public void setScenario(EvolutionScenario scenario) {
		this.scenario = scenario;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof NormalEdge){
			NormalEdge temp = (NormalEdge)obj;
			
			if(temp.getSource() == null && this.source != null)	return false;
			else if(temp.getSource() != null && this.source == null)	return false;
	
			if(temp.getDestination() == null && this.destination != null)	return false;
			else if(temp.getDestination() != null && this.destination == null)	return false;
			
			if(this.source == null && temp.getSource() == null)
				if(this.destination == null && temp.getDestination() == null )	return true;
			
			if(this.source.equals(temp.getSource()) == true)
				if(this.destination.equals(temp.getDestination()) == true)		return true;
			
			return false;
		}
		else return false;
	}
	
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.scenario.hashCode();
	}
}
