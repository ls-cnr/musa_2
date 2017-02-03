package layer.awareness.net;

import java.util.ArrayList;

public class MultipleToken extends Token{
	
	private ArrayList<Token> tokensDependent;

	public MultipleToken(String placeName) {
		super(placeName);
	}
	
	public MultipleToken( String placeName, MultipleToken depend, int branch ){
		super(placeName, depend, branch);
	}

	public boolean hasDependents() {
		if( tokensDependent == null )
			return false;
		else
			return true;
	}
	
	public ArrayList<Token> getTokensDependent() {
		return tokensDependent;
	}
	
	public void addToken( Token token ) {
		checkTokensDependent();
		tokensDependent.add(token);
	}
	
	private void checkTokensDependent() {
		if( tokensDependent == null )
			tokensDependent = new ArrayList<>();
	}
	
	public int getNumberOfBranch() {
		int max = tokensDependent.get(0).getBranch();
		for( Token token : tokensDependent )
			if( token.getBranch() > max )
				max = token.getBranch();
		return max;
	}
}
