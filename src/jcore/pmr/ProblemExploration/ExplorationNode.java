package pmr.ProblemExploration;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public abstract class ExplorationNode implements Node{
	
	private AbstractCapability capability;
	private ArrayList<ENode> destination;
	private ENode source;
	private int score;
	
	public ExplorationNode(ENode source, AbstractCapability capability, ArrayList<ENode> destination){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
	}
	
	public AbstractCapability getCapability() {
		return capability;
	}
	
	public ENode getSource() {
		return source;
	}
	
	public int getScore() {
		return score;
	}	
	
	public ENode getDestination(){
		return this.destination;
	}
	
	public void setDestination(ENode destination){
		this.destination = destination;
	}
	
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}
	
	public void setSource(ENode source) {
		this.source = source;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	

}
