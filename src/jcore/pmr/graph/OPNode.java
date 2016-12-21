package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

/**
 * The Class OPNode.
 */
public abstract class OPNode extends Node {
	
	public OPNode(StateOfWorld worldState, Node parent, AbstractCapability capability){
		super(worldState, parent, capability);
	}
}
