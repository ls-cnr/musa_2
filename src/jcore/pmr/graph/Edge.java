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
}
