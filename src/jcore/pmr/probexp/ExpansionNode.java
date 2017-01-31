package pmr.probexp;

import java.util.ArrayList;
import java.util.Comparator;

import layer.awareness.AbstractCapability;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public abstract class ExpansionNode implements Node{
	
	protected AbstractCapability capability;
	protected ArrayList<ENode> destination;
	protected ENode source;
	protected int score;
	
	public ExpansionNode(ENode source, ArrayList<ENode> destination, AbstractCapability capability){
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
	
	public ArrayList<ENode> getDestination(){
		return this.destination;
	}
	
	public void setDestination(ArrayList<ENode> destination){
		this.destination = destination;
	}
	
	public void addDestination(ENode destination){
		this.destination.add(destination);
	}
	
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}
	
	public void setSource(ENode source) {
		this.source = source;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	//Comparatore per tenere le liste aggiornate secondo lo score
	public static Comparator<ExpansionNode> getScoreComparator(){
		Comparator<ExpansionNode> comp = new Comparator<ExpansionNode>(){
			@Override
			public int compare(ExpansionNode e1, ExpansionNode e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}
	

}
