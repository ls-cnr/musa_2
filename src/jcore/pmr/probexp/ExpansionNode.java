package pmr.probexp;

import java.util.ArrayList;
import java.util.Comparator;

import layer.awareness.AbstractCapability;
import pmr.graph.Node;
import pmr.graph.WorldNode;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpansionNode.
 */
public abstract class ExpansionNode implements Node{
	
	/** The capability. */
	protected AbstractCapability capability;
	
	/** The destination. */
	protected ArrayList<ENode> destination;
	
	/** The source. */
	protected ENode source;
	
	/** The score. */
	protected int score;
	
	/**
	 * Instantiates a new expansion node.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param capability
	 *            the capability
	 */
	public ExpansionNode(ENode source, ArrayList<ENode> destination, AbstractCapability capability){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Gets the capability.
	 *
	 * @return the capability
	 */
	public AbstractCapability getCapability() {
		return capability;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public ENode getSource() {
		return source;
	}	
	
	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public ArrayList<ENode> getDestination(){
		return this.destination;
	}
	
	/**
	 * Sets the destination.
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(ArrayList<ENode> destination){
		this.destination = destination;
	}
	
	/**
	 * Adds the destination.
	 *
	 * @param destination
	 *            the destination
	 */
	public void addDestination(ENode destination){
		this.destination.add(destination);
	}
	
	/**
	 * Sets the capability.
	 *
	 * @param capability
	 *            the new capability
	 */
	public void setCapability(AbstractCapability capability) {
		this.capability = capability;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(ENode source) {
		this.source = source;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score
	 *            the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the score comparator.
	 *
	 * @return the score comparator
	 */
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
