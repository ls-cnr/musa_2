package layer.awareness.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import layer.awareness.goalmodel.GoalModel;
import layer.semantic.Condition;
import petrinet.logic.*;

public class Net {

	private Petrinet pn;
	private HashMap<Transition, Condition> labels;
	private HashMap<Transition, Integer> hopMap;
	private HashMap<Place, Place> initialOrPlaces;
	private HashMap<Place, Place> finalOrPlaces;
	
	private int numTransitions;
	
	public Net( GoalModel model ) {
		PetriNetConstruction construction = new PetriNetConstruction(model);
		construction.construct();
		pn = construction.getPetrinet();
		labels = construction.getLabels();
		initialOrPlaces = construction.getInitialOrPlaces();
		finalOrPlaces = construction.getFinalOrPlaces(); 
		
		numTransitions = pn.getTransitions().size();
	}
	
	public int hop( ArrayList<Token> tokens ) {
		ArrayList<MultipleToken> temp = new ArrayList<>();
		hopMap = new HashMap<>();
		int hopValue = 0;
		
		for( Token token : tokens )
			if( token.isDependent() )
				token.getDependentToken().addToken(token);
			else
				if( token instanceof MultipleToken )
					temp.add((MultipleToken) token);
				else
					hopValue += hopToken(getPlace(token.getPlaceName()));
		
		for( MultipleToken mToken : temp)
			hopValue += multipleHop(mToken);
		
		return hopValue;
	}
	
	private int multipleHop( MultipleToken mToken ) {
		if( mToken.hasDependents() ){
			ArrayList<Integer> mHop = new ArrayList<>(Collections.nCopies(mToken.getNumberOfBranch() + 1, 0));
			for( Token token : mToken.getTokensDependent() ){
				int i = token.getBranch();
				if( token instanceof MultipleToken)
					mHop.set( i, mHop.get(i) + multipleHop((MultipleToken) token) );
				else
					mHop.set( i, mHop.get(i) + hopToken(getPlace(token.getPlaceName())) );
			}
			return getHopValueFromArray(mHop);
		}
		else
			return hopToken(getPlace(mToken.getPlaceName()));
	}
	
	private int getHopValueFromArray( ArrayList<Integer> mHop ) {
		int max = mHop.get(0);
		for( int i = 1; i < mHop.size(); i++ )
			if( mHop.get(i) > max )
				max = mHop.get(i);
			
		return max;
	} 
	
	private int hopToken( Place place ) {
		if( place.equals(getLast()) )
			return 0;
		else{
			int general = 0;
			
			for( Arc arcP : place.getOutgoing() ){
				Transition transition = arcP.getTransition();
				int count = 0;
				if( !hopMap.containsKey(transition) ){
					count++;
					for( Arc arcT : transition.getOutgoing() )
						count += hopToken(arcT.getPlace());
					if( isFinalOrPlace(place) )
						hopMap.put(transition, count);
					else
						hopMap.put(transition, 0);
				}
				else{
					count = hopMap.get(transition);
				}
				general = max(count, general);
			}
			
			return general;
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
	
	public void putTokens( ArrayList<Token> tokens ) {
		for( Token token : tokens ){
			pn.getPlace(token.getPlaceName()).addTokens(1); //equals test??
		}
	}
	
	public boolean checkMultipleToken( Place place ){
		if( !place.hasAtLeastTokens(1) ){
			place.addTokens(1);
			return true;
		}
		return false;
	}
	
	public void removeOrTokens( Place finalOrPlace, ArrayList<Token> tokens ) {
		Place initialOrPlace = finalOrPlaces.get(finalOrPlace);
		remove(finalOrPlace, initialOrPlace, tokens);
	}
	
	private void remove( Place initial, Place place, ArrayList<Token> tokens ) {
		if( place.getName() == initial.getName() )
			for( int i = 0; i < tokens.size(); i++ )
				if( tokens.get(i).getPlaceName() == place.getName() )
					tokens.remove(i);
		else{
			if( place.hasAtLeastTokens(1) ){ 
				place.removeTokens(1);
				for( int j = 0; j < tokens.size(); j++ )
					if( tokens.get(j).getPlaceName() == place.getName() )
						tokens.remove(j);
			}
			
			for( Arc incomingArcP : place.getIncoming() ){
				Transition t = incomingArcP.getTransition();
				for( Arc incomingArcT : t.getIncoming() ){
					remove(initial, incomingArcT.getPlace(), tokens);
				}
			}
			
		}
	}
	
	
	
	public void removeTokens( ArrayList<Token> tokens ) {
		for( Token token : tokens ){
			pn.getPlace(token.getPlaceName()).removeTokens(1); //equals test??
		}
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
	
	public Place getFirstInPlaceFromTransition( Transition transition ) {
		return transition.getIncoming().get(0).getPlace();
	}
	
	private int max( int a, int b) {
		if( a >= b ) return a;
		else return b;
	}
	
	public boolean isFinalOrPlace( Place place ) {
		return finalOrPlaces.containsKey(place);
	}
	
	public boolean isInitialOrPlace( Place place ) {
		return initialOrPlaces.containsKey(place);
	}
	
	public Petrinet getPetrinet() {
		return pn;
	}
}
