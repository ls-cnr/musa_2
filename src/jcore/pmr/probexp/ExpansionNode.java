package pmr.probexp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import pmr.graph.Node;

/**
 * The Class ExpansionNode.
 */
public abstract class ExpansionNode implements Node{

	/** The capability. */
	protected String capability;

	/** The destination. */
	protected ArrayList<ENode> destination;

	/** The source. */
	protected ENode source;

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
	public ExpansionNode(ENode source, ArrayList<ENode> destination, String capability){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
	}

	public ExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
		this.score = score;
	}

	public ExpansionNode(ENode source, ArrayList<ENode> destination, String capability, String agent){
		this.capability = capability;
		this.source = source;
		this.destination = destination;
		this.agent = agent;
	}

	public ExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score, String agent){
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
	public void setCapability(String capability) {
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
	public static Comparator<ExpansionNode> getScoreComparator(){
		Comparator<ExpansionNode> comp = new Comparator<ExpansionNode>(){
			@Override
			public int compare(ExpansionNode e1, ExpansionNode e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}


	@Override
	  public boolean equals(Object obj){
	      if (obj == null) {
	          return false;
	      }
	      if (!ExpansionNode.class.isAssignableFrom(obj.getClass())) {
	          return false;
	      }
	      ExpansionNode other = (ExpansionNode) obj;

	      if(this.getSource().getWorldState() == null && other.getSource().getWorldState() == null){
	        return true;
	      }
	      else if (this.getSource().getWorldState() != null && this.getSource().getWorldState().equals(other.getSource().getWorldState())) {
	          Iterator<ENode> i = this.getDestination().iterator();

	          while(i.hasNext()){
	        	  ENode temp = (ENode) i.next();
	        	  if (this.destination.contains(temp) == false)	return false;
	          }
	          return true;
	      }
	      else {
	          return false;
	      }
	  }


	  /* (non-Javadoc)
	   * @see java.lang.Object#hashCode()
	   */
	  @Override
	  public int hashCode(){
		  int hash = 0;
	      for(int i = 0; i<this.destination.size(); i++){
	    	  hash += this.destination.get(i).hashCode();
	      }

		  return this.getSource().getWorldState().hashCode() + hash;
	  }

}
