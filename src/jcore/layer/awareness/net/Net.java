package layer.awareness.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import layer.awareness.goalmodel.GoalModel;
import layer.semantic.Condition;
import petrinet.logic.*;

public class Net {

	private Petrinet pn;
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
		int val = hops.get(place) + 1;
		List<Arc> placeIncoming;
		
		if(  (placeIncoming = place.getIncoming())  != null )
			for( Arc arcP : placeIncoming ){
				Transition transition = arcP.getTransition();
				
				if( transition.hasMoreThanOneOutgoing() ){
					val = 0;
					for( Arc arcTOut : transition.getOutgoing() ){
						Integer i = hops.get( arcTOut.getPlace() );
						if( i == null ) i = 0;
						val += i;
					}
					val += 1;
				}
				
				for( Arc arcTIn : transition.getIncoming() ){
					Place next = arcTIn.getPlace();
					Integer prev;
					if( ( prev = hops.get(next)) == null ) prev = 0;
					hops.put(next, max(prev, val) );
					hop(next);
				}
			}
	}
	
	private int max( int a, int b) {
		if( a >= b ) return a;
		else return b;
	}
	
	public int getHop( Place place ) {
		return hops.get(place);
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
	
	/*Temp for testing*/
	public Place getFirst() {
		return pn.getPlaces().get(0);
	}
}
