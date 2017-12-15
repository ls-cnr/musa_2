package datalayer.awareness.legacy.net;

import java.util.ArrayList;

/**
 * The Class MultipleToken, used to start parallel paths from an initialOrPlace.
 * @author Mirko Zichichi
 */
public class MultipleToken extends Token{
	
	/** The list of tokens that depend from this MultipleToken. */
	private ArrayList<Token> tokensDependent;

	/**
	 * Instantiates a new multiple token without depending.
	 *
	 * @param placeName
	 *            the place name
	 */
	public MultipleToken(String placeName) {
		super(placeName);
	}
	
	/**
	 * Instantiates a new multiple token with a depending token and his branch.
	 *
	 * @param placeName
	 *            the place name
	 * @param depend
	 *            the depending token
	 * @param branch
	 *            the branch
	 */
	public MultipleToken( String placeName, MultipleToken depend, int branch ){
		super(placeName, depend, branch);
	}

	/**
	 * Checks for dependents.
	 *
	 * @return true, if successful
	 */
	public boolean hasDependents() {
		if( tokensDependent == null )
			return false;
		else
			return true;
	}
	
	/**
	 * Gets the tokens that depend from this Multiple token.
	 *
	 * @return the tokens dependent
	 */
	public ArrayList<Token> getTokensDependent() {
		return tokensDependent;
	}
	
	/**
	 * Adds a new token to the dependent list.
	 *
	 * @param token
	 *            the token
	 */
	public void addToken( Token token ) {
		checkTokensDependent();
		tokensDependent.add(token);
	}
	
	/**
	 * Check if tokensDependent has been initialized.
	 */
	private void checkTokensDependent() {
		if( tokensDependent == null )
			tokensDependent = new ArrayList<>();
	}
	
	/**
	 * Gets the number of branch.
	 *
	 * @return the number of branch
	 */
	public int getNumberOfBranch() {
		int max = tokensDependent.get(0).getBranch();
		for( Token token : tokensDependent )
			if( token.getBranch() > max )
				max = token.getBranch();
		return max;
	}
}
