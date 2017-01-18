package pmr.graph;

import layer.awareness.AbstractCapability;

public abstract class Edge {
	protected Node source;
	protected Node destination;
	protected AbstractCapability capability;
	
	public Edge(Node source, Node destination, AbstractCapability capability){
		this.source = source;
		this.destination = destination;
		this.capability = capability;
	}
	
	public Node getSource(){
		return this.source;
	}

	public Node getDestination() {
		return destination;
	}
	
	public AbstractCapability getCapability() {
		return capability;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Edge){
			Edge temp = (Edge)obj;
			
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
		if(this == null)	return 0;
		return this.source.hashCode() + this.destination.hashCode() + this.capability.hashCode();
	}
}
