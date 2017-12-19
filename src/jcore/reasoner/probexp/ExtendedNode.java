package reasoner.probexp;

import java.util.Comparator;

import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.TokensConfiguration;
import datalayer.world.StateOfWorld;
import datalayer.world.wts.Node;

/**
 * The Class ENode.
 * @author Alessandro Fontana
 * @author Mirko Zichichi
 */
public class ExtendedNode implements Node{
	
	/** The WorldNode coming from the WTS */
	private StateOfWorld node;
	
	/** The Tokens Configuration associated with the node to apply on the Nets*/
	private TokensConfiguration tokens;
	
	/** The Score associated to the node */
	private int score;
	
	/** Indicates if the node is an ExitNode */
	private boolean exit;

	/** Indicates if the node is an ErrorNode */
	private boolean error;
	
	/**
	 * Instantiates a new empty ENode with only the WorldState.
	 *
	 * @param node
	 *            the WorldNode
	 */
	public ExtendedNode( StateOfWorld node ) {
		this.node = node;
		this.exit = false;
	}
	
	/**
	 * Instantiates a new full ENode with all the attributes.
	 *
	 * @param node
	 *            the WorldNode
	 * @param tokens
	 *            the node's Tokens
	 * @param score
	 *            the node's Score
	 * @param exit
	 *            the Exit condition
	 * @param error
	 *            the Exit condition  
	 */
	public ExtendedNode( StateOfWorld node, TokensConfiguration tokens, int score, boolean exit, Boolean error ) {
		this.node = node;
		this.tokens = tokens;
		this.score = score;
		this.exit = exit;
		this.error = error;
	}
	
	/**
	 * Gets the world node.
	 *
	 * @return the WorldNode
	 */
	public StateOfWorld getWorldState() {
		return node;
	}
	
	/**
	 * Sets the Tokens Configuration.
	 *
	 * @param tokens
	 *            the new Tokens Configuration
	 */
	public void setTokens(TokensConfiguration tokens){
		this.tokens = tokens;
	}
	
	/**
	 * Sets the Node condition.
	 *
	 * @param string
	 *            Condition that states if the node is ExitNode or ErrorNode or other
	 */
	public void checkNodeType(PetriNetState state){
		if( state==PetriNetState.ACCEPTED )
			this.exit = true;
		else if( state==PetriNetState.ERROR )
			this.error = true;
	}
	
	/**
	 * Gets the Tokens List
	 *
	 * @return the Tokens List
	 */
	public TokensConfiguration getTokens() {
		return tokens;
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
	public void setScore(int score){
		this.score = score;
	}
	
	/**
	 * Checks if is exit node.
	 *
	 * @return true, if is exit node
	 */
	public boolean isExitNode(){
		return this.exit;
	}
	/**
	 * Checks if is error node.
	 *
	 * @return true, if is exit node
	 */
	public boolean isErrorNode(){
		return this.error;
	}
	
	@Override
	  public boolean equals(Object obj){
	      if (obj == null) {
	          return false;
	      }
	      if (!ExtendedNode.class.isAssignableFrom(obj.getClass())) {
	          return false;
	      }
	      ExtendedNode other = (ExtendedNode) obj;
	      
	      if(this.getWorldState() == null && other.getWorldState() == null){
	        return true;
	      }
	      else if (this.getWorldState() != null && this.getWorldState().equals(other.getWorldState())) {
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
	  public String toString(){
	      return this.getWorldState().toString();
	  }  
	
	  
	  /* (non-Javadoc)
	   * @see java.lang.Object#hashCode()
	   */
	  @Override
	  public int hashCode(){
	      return this.getWorldState().hashCode();
	  }  
	  
	  /**
	   * Gets the score comparator, used to keep lists updated using the score
	   *
	   * @return the score comparator
	   */
	  public static Comparator<ExtendedNode> getScoreComparator(){
		Comparator<ExtendedNode> comp = new Comparator<ExtendedNode>(){
			@Override
			public int compare(ExtendedNode e1, ExtendedNode e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}

	public void setExit(boolean b) {
		exit = b;
	}
	
}
