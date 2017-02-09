package layer.awareness.net;

/**
 * The Class Token, used to manipulate tokens in the Petrinet.
 * @author Mirko Zichichi
 */
public class Token {
	
	/** The place name. */
	private String placeName;
	
	/** The dependent token. */
	private MultipleToken dependingToken;
	
	/** The branch in which the token is in. */
	private int branch;
		
	/**
	 * Instantiates a new token without depending.
	 *
	 * @param placeName
	 *            the place name
	 */
	public Token( String placeName ) {
		this.placeName = placeName;
		this.dependingToken = null;
	}
	
	/**
	 * Instantiates a new token with a depending token and his branch.
	 *
	 * @param placeName
	 *            the place name
	 * @param dependingToken
	 *            the depending token
	 * @param branch
	 *            the branch
	 */
	public Token( String placeName, MultipleToken dependingToken, int branch) {
		this.placeName = placeName;
		this.dependingToken = dependingToken;
		this.branch = branch;
	}
	
	/**
	 * Gets the place name.
	 *
	 * @return the place name
	 */
	public String getPlaceName() {
		return placeName;
	}
	
	/**
	 * Gets the token branch.
	 *
	 * @return the branch
	 */
	public int getBranch() {
		return branch;
	}
	
	/**
	 * Checks if is dependent from a MultipleToken.
	 *
	 * @return true, if is dependent
	 */
	public boolean isDependent() {
		if( dependingToken == null )
			return false;
		else
			return true;
	}
	
	/**
	 * Gets the depending token.
	 *
	 * @return the depending token
	 */
	public MultipleToken getDependingToken() {
		return dependingToken;
	}
}
