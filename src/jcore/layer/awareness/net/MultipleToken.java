package layer.awareness.net;

public class MultipleToken extends Token{

	public MultipleToken(String placeName) {
		super(placeName);
	}
	
	public MultipleToken( Token init ){
		super(init.getPlaceName());
	}

}
