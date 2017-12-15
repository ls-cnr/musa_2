package reasoner.probexp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import datalayer.awareness.AbstractCapability;
import datalayer.world.wts.Node;
import datalayer.world.wts.WorldNode;

/**
 * The Class ExpansionNode.
 */
public abstract class GraphExpansion implements Node{
	
	/** The capability. */
	protected String capability;
	
	/** The destination. */
	protected ArrayList<ExtendedNode> destination;
	
	/** The source. */
	protected ExtendedNode source;
	
	/** The score. */
	protected int score;
	
	protected String agent;
	
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
	public GraphExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
	}
	
	public GraphExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
		this.score = score;
	}
	
	public GraphExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, String agent){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
		this.score = score;
		this.agent = agent;
	}
	
	public GraphExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score, String agent){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
		this.score = score;
		this.agent = agent;
	}
	/**
	 * Gets the capability.
	 *
	 * @return the capability
	 */
	public String getCapability() {
		return capability;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public ExtendedNode getSource() {
		return source;
	}	
	
	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public ArrayList<ExtendedNode> getDestination(){
		return this.destination;
	}
	
	/**
	 * Sets the destination.
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(ArrayList<ExtendedNode> destination){
		this.destination = destination;
	}
	
	/**
	 * Adds the destination.
	 *
	 * @param destination
	 *            the destination
	 */
	public void addDestination(ExtendedNode destination){
		this.destination.add(destination);
	}
	
	/**
	 * Sets the capability.
	 *
	 * @param capability
	 *            the new capability
	 */
	public void setCapability(String capability) {
		this.capability = capability;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(ExtendedNode source) {
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
	
	
	public String getAgent(){
		return this.agent;
	}
	
	public void setAgent(String agent){
		this.agent = agent;
	}

	/**
	 * Gets the score comparator.
	 *
	 * @return the score comparator
	 */
	//Comparatore per tenere le liste aggiornate secondo lo score
	public static Comparator<GraphExpansion> getScoreComparator(){
		Comparator<GraphExpansion> comp = new Comparator<GraphExpansion>(){
			@Override
			public int compare(GraphExpansion e1, GraphExpansion e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!GraphExpansion.class.isAssignableFrom(obj.getClass()))
			return false;

		return this.hashCode() == obj.hashCode();
	}
	
//	@Override
//	  public boolean equals(Object obj){
//	      if (obj == null) {
//	          return false;
//	      }
//	      if (!GraphExpansion.class.isAssignableFrom(obj.getClass())) {
//	          return false;
//	      }
//	      GraphExpansion other = (GraphExpansion) obj;
//	      
//	      if(this.getSource().getWorldState() == null && other.getSource().getWorldState() == null){
//	        return true;
//	      }
//	      else if (this.getSource().getWorldState() != null && this.getSource().getWorldState().equals(other.getSource().getWorldState())) {
//	          Iterator<ExtendedNode> i = this.getDestination().iterator();
//	          
//	          while(i.hasNext()){
//	        	  ExtendedNode temp = (ExtendedNode) i.next();
//	        	  if (this.destination.contains(temp) == false)	return false;
//	          }
//	          return true;
//	      }
//	      else {
//	          return false;
//	      }
//	  }  
	
	  
	  /* (non-Javadoc)
	   * @see java.lang.Object#hashCode()
	   */
	  @Override
	  public int hashCode(){
		  
		  int hash = 0;
	      
		  String sum=getSource().getWorldState().toString()+getCapability();
		  for(int i = 0; i<this.destination.size(); i++){
	    	  	sum += this.destination.get(i).getWorldState().toSortedString();
	      }
		  
		  return sum.hashCode();
	  }  

}
