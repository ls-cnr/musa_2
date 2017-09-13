package layer.awareness.LTL.net;

import java.util.HashMap;
import java.util.List;

import layer.awareness.LTL.formulamodel.FormulaBT;
import layer.awareness.LTL.net.netmodels.FormulaPN;
import petrinet.logic.Transition;

public class Nets {
	
	private HashMap<String, FormulaPN> nets;
	
	private final String startingName = "Formula0"; 
	
	public Nets( FormulaBT tree ) {
		this.nets = new PetriNetsConstruction(tree.getRoot()).getNets();
	}
	
	public void putTokens( TokensConfiguration tokens ) {
		for( String net : tokens.getNets() )
			for( String place : tokens.getTokens(net) )
				nets.get(net).putToken(place);
	}
	
	public void removeTokens() {
		for( String net : nets.keySet() )
			nets.get(net).removeAllTokens();
	}
	
	public List<Transition> getTransitionsAbleToFire( String net ) {
		return nets.get(net).getTransitionsAbleToFire();
	}
	
	public TransitionCondition getTransitionCondition( Transition t ) {
		TransitionCondition tmp = null;
		for( String f : nets.keySet() )
			if( (tmp = nets.get(f).getTransitionCondition(t)) != null )
				break;
		return tmp;
		
	}
	
	public int hop() {
		return 0;
	}
	
	public String initNet( String net ) {
		nets.get(net).putStartToken();
		return nets.get(net).getStartPlace().getName();
	}
	
	public String getNetState( String net ) {
		return nets.get(net).getNetState();
	}
	
	public String getStartingNet() {
		return startingName;
	}
	
	public FormulaPN getStartingPN() {
		return nets.get(startingName);
	}
	
	
}
