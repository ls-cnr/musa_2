package layer.awareness.net;

import java.util.ArrayList;
import java.util.HashMap;

import layer.awareness.goalmodel.GoalModel;
import layer.semantic.Condition;
import petrinet.logic.*;

public class Net {

	Petrinet pn;
	private HashMap<Transition, Condition> labels;
	private HashMap<Place, Integer> hops;
	
	public Net( GoalModel model ) {
		PetriNetConstruction construction = new PetriNetConstruction(model);
		construction.construct();
		pn = construction.getPetrinet();
		labels = construction.getLabels();
		
		hops = new HashMap<>();
		hops.put(construction.getLastPlace(), 0);
		hop( construction.getLastPlace() );
	}
	
	public void putTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).addTokens(1); //equals??
		}
	}
	
	public void removeTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).removeTokens(1); //equals??
		}
	}
	
	public void fire( Transition t ) {
		t.fire();
	}
	
	public ArrayList<String> getTokens() {
		ArrayList<String> newTokens = new ArrayList<>();
		for( Place p : pn.getPlaces() )
			if( p.getTokens() > 0 ) 
				newTokens.add(p.getName());
		return newTokens;
	}
	
	private void hop( Place place ) {
		for( Arc arcP : place.getIncoming() ){
			if( arcP.getTransition().hasMoreThanOneOutgoing() )
				//sum 
				;
			else{
				for( Arc arcT : arcP.getTransition().getIncoming() ){
					Place next = arcT.getPlace();
					hops.put(next, max(hops.get(next), hops.get(place) + 1));
					hop(next);
				}
			}
				
		}
	}
	
	private int max( int a, int b) {
		if( a >= b ) return a;
		else return b;
	}
	
	public ArrayList<Transition> getTransitionsAbleToFire() {
		return (ArrayList<Transition>) pn.getTransitionsAbleToFire();
	}
	
	public Condition getTransitionLabel( Transition t ) {
		return labels.get(t);
	}
	
	/*Temp*/
	public Petrinet getPetrinet() {
		return pn;
	}
}
