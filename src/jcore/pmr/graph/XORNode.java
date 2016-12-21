package pmr.graph;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

public class XORNode extends OPNode{
	public XORNode(StateOfWorld worldState, Node parent, AbstractCapability capability){
		super(worldState, parent, capability);
	}
}
