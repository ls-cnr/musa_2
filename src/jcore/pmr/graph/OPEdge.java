package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.evolution.EvolutionScenario;

public class OPEdge {
		
	private WorldNode source;
	private OPNode destination;
	private AbstractCapability capability; 
	
	public OPEdge(WorldNode source, OPNode destination, AbstractCapability capability){
		super();
		this.source = source;
		this.destination = destination;
		this.capability = capability;
	}
	
	public AbstractCapability getCapability(){
		return this.capability;
	}
	
	public WorldNode getSource(){
		return this.source;
	}

	public OPNode getDestination() {
		return this.destination;
	}
	
	public void setSource(WorldNode source){
		this.source = source;
	}

	public void setDestination(OPNode destination) {
		this.destination = destination;
	}
	
	public void setScenario(AbstractCapability capability) {
		this.capability = capability;
	}
	
	//L'OPNode è unico, ogni WorldNode ha i suoi OPNode, ma non può esistere un OPNode con lo stesso source e lo stesso desintation all'interno
	//Di un WorldNode
	
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
			
			if(this.source.equals(temp.getSource()) == false)				return false;
			if(this.destination.equals(temp.getDestination()) == false)		return false;
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.scenario.hashCode();
	}
}
