package pmr.ProblemExploration;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;

public class MultipleExplorationNode extends ExplorationNode{

	
	public MultipleExplorationNode(ENode source, AbstractCapability capability){
		super(source, capability);
		this.destination = null;
	}
	
	public ArrayList<ENode> getDestination(){
		return this.destination;
	}
	
	public void setDestination(ArrayList<ENode> destination){
		this.destination = new ArrayList<ENode>(destination);
	}
	
	public void addDestination(ENode destination){
		this.destination.add(destination);
	}
}
