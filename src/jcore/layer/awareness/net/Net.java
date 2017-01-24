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
	private int numTransitions;
	
	public Net( GoalModel model ) {
		PetriNetConstruction construction = new PetriNetConstruction(model);
		construction.construct();
		pn = construction.getPetrinet();
		labels = construction.getLabels();
		
		hops = new HashMap<>();
		hops.put(getLast(), 0);
		hop(getLast());
		
		numTransitions = pn.getTransitions().size();
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
	
	public ArrayList<Transition> getTransitionsAbleToFire() {
		return (ArrayList<Transition>) pn.getTransitionsAbleToFire();
	}
	
	public Condition getTransitionLabel( Transition t ) {
		return labels.get(t);
	}
	
	public int getNumTransitions() {
		return numTransitions;
	}
	
	public void putTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).addTokens(1); //equals test??
		}
	}
	
	public void removeTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).removeTokens(1); //equals test??
		}
	}
	
	public int getHop( Place place ) {
		return hops.get(place);
	}
	
	public Place getPlace( String name ) {
		return pn.getPlace(name);
	}
	
	public Place getFirst() {
		return pn.getPlaces().get(0);
	}
	
	public Place getLast() {
		return pn.getPlaces().get(pn.getPlaces().size() - 1);
	}
	
	private int max( int a, int b) {
		if( a >= b ) return a;
		else return b;
	}
	
}
