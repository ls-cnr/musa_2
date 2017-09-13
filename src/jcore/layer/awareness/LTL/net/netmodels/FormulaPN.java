package layer.awareness.LTL.net.netmodels;

import java.util.HashMap;
import java.util.List;

import layer.awareness.LTL.net.TransitionCondition;
import petrinet.logic.*;

public abstract class FormulaPN {
	
	protected TransitionCondition firstOp;
	protected TransitionCondition secondOp;
	
	protected Petrinet pn;
	protected Place start;
	protected HashMap<Place, String> association;
	protected HashMap<Transition, TransitionCondition> labels;
	
	protected FormulaPN() {
		pn = new Petrinet("PN");
		association = new HashMap<>();
	}
	
	public List<Transition> getTransitionsAbleToFire() {
		return pn.getTransitionsAbleToFire();
	}
	
	public void putToken( String place ) {
		pn.getPlace(place).addTokens(1);
	}
	
	public void removeAllTokens() {
		for( Place p : pn.getPlaces())
			p.removeTokens(p.getTokens());
	}
	
	public TransitionCondition getTransitionCondition( Transition t ) {
		return labels.get(t);
	}
	
	public String getNetState() {
		Place p = getPlaceWithToken();
		if( isAccepted(p) ) return "A";
		else if( isWaiting(p) ) return "W";
		else if( isError(p) ) return "E";
		return null;
	}
	
	public Place getStartPlace() {
		return start;
	}
	
	public void putStartToken() {
		start.addTokens(1);
	}
	
	private Place getPlaceWithToken() {
		for( Place p : pn.getPlaces())
			if( p.hasAtLeastTokens(1) )
				return p;
		return null;
	}
	
	private boolean isAccepted( Place p ) {
		return association.get(p).equals("A");
	}
	
	private boolean isError( Place p ) {
		return association.get(p).equals("E");
	}
	
	private boolean isWaiting( Place p ) {
		return association.get(p).equals("W");
	}
	

}

