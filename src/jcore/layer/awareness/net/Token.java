package layer.awareness.net;

public class Token {
	
	private String placeName;
	private MultipleToken dependentToken;
		
	public Token( String placeName ) {
		this.placeName = placeName;
		this.dependentToken = null;
	}
	
	public Token( String placeName, MultipleToken dependentToken ) {
		this.placeName = placeName;
		this.dependentToken = dependentToken;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public boolean isDependent() {
		if( dependentToken == null )
			return false;
		else
			return true;
	}
	
	public MultipleToken getDependentToken() {
		return dependentToken;
	}
}
