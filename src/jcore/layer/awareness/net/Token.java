package layer.awareness.net;

public class Token {
	
	private String placeName;
	private MultipleToken dependentToken;
	private int branch;
		
	public Token( String placeName ) {
		this.placeName = placeName;
		this.dependentToken = null;
	}
	
	public Token( String placeName, MultipleToken dependentToken, int branch) {
		this.placeName = placeName;
		this.dependentToken = dependentToken;
		this.branch = branch;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public int getBranch() {
		return branch;
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
