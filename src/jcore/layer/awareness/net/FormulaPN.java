package layer.awareness.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import petrinet.logic.*;

public abstract class FormulaPN {
	
	protected String operand;
	
	protected Petrinet pn;
	protected Place start;
	protected HashMap<Place, String> association;
	
	public FormulaPN() {
		pn = new Petrinet("PN");
		association = new HashMap<>();
	}
	
	protected Petrinet getPN() {
		return pn;
	}
	
	public void putStartToken() {
		start.addTokens(1);
	}
	
	public void removeAllTokens() {
		for( Place p : pn.getPlaces())
			p.removeTokens(p.getTokens());
	}
	
	public List<Place> getPlacesWithToken() {
		List<Place> list = new ArrayList<>();
		for( Place p : pn.getPlaces() )
			if( p.hasAtLeastTokens(1) ) 
				list.add(p);
		return list;
	}
	
	public boolean isAccepted( Place p ) {
		return association.get(p) == "A";
	}
	
	public boolean isError( Place p ) {
		return association.get(p) == "E";
	}
	
	public boolean isWaiting( Place p ) {
		return association.get(p) == "E";
	}

}

