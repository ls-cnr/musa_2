package pmr.ProblemExploration;

import layer.awareness.AbstractCapability;

public class NormalExplorationNode extends ExplorationNode{
		
	NormalExplorationNode(AbstractCapability capability, ENode source, ENode destination){
		super(source, capability);
		this.destination = destination;
	}


	
}
