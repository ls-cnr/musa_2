package layer.awareness.LTL.net;

public abstract class TransitionCondition {

	private String term;
	
	protected TransitionCondition( String term ) {
		this.term = term;
	}
	
	public String getTerm() {
		return term;
	}	
	
	public abstract void setStateCondition(String s);
	
}
