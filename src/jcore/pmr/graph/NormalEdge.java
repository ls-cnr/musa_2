package pmr.graph;

import layer.awareness.AbstractCapability;

public class NormalEdge implements Edge {
	
	private WorldNode source;
	private WorldNode destination;
	private AbstractCapability capability;

	public NormalEdge(WorldNode source, WorldNode destination, AbstractCapability capability){
		super();
		this.source = source;
		this.destination = destination;
		this.capability = capability;
	}
	
	public WorldNode getSource(){
		return this.source;
	}

	public WorldNode getDestination() {
		return this.destination;
	}
	
	public AbstractCapability getCapability() {
		return this.capability;
	}
	
	public void setSource(WorldNode source){
		this.source = source;
	}

	public void setDestination(WorldNode destination) {
		this.destination = destination;
	}
	
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof NormalEdge){
			NormalEdge temp = (NormalEdge)obj;
			
			if(temp.getSource() == null && this.source != null)					return false;
			else if(temp.getSource() != null && this.source == null)			return false;
	
			if(temp.getDestination() == null && this.destination != null)		return false;
			else if(temp.getDestination() != null && this.destination == null)	return false;
			
			if(this.source == null && temp.getSource() == null)
				if(this.destination == null && temp.getDestination() == null )	return true;
			
			if(this.source.equals(temp.getSource()) == false)					return false;
			
			return true;
			
		}
		else return false;
	}
	
	@Override
	public int hashCode(){
		return this.source.hashCode() + this.destination.hashCode() + this.capability.hashCode();
	}
	}

