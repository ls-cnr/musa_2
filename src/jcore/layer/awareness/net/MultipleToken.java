package layer.awareness.net;

public class MultipleToken extends Token{

	public MultipleToken(String placeName) {
		super(placeName);
	}
	
	public MultipleToken( String placeName, MultipleToken depend ){
		super(placeName, depend);
	}

}
