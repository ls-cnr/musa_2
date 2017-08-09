package layer.awareness.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import layer.awareness.goalmodel.GoalTreeModel;
import layer.semantic.Condition;
import petrinet.logic.*;

/**
 * The Class Net. Used as facade for the interaction with PetriNet.
 * @author Mirko Zichichi
 */
public class Net {

	/** The main structure */
	private Petrinet pn;

	/** A structure used to associate all the transition with a trigger condition or final state */
	private HashMap<Transition, Condition> labels;

	/** A map used to store the remaining hop in a transition ( used to follow the path to the final place once ) */
	private HashMap<Transition, Integer> hopMap;

	/** A structure used to relate an initialOrPlace with a finalOrPlace */
	private HashMap<Place, Place> initialOrPlaces;

	/** A structure used to relate a finalOrPlace with an initialOrPlace */
	private HashMap<Place, Place> finalOrPlaces;

	/** Simple value to keep the number of transitions */
	private int numTransitions;

	/**
	 * Instantiates a new net.
	 * This constructor create a new Petrinet through the class PetriNetConstruction, then it stores all the
	 * attributes coming from the construction.
	 *
	 * @param model
	 *            the GoalModel to start from
	 */
	public Net( GoalTreeModel model ) {
		PetriNetConstruction construction = new PetriNetConstruction(model);
		construction.construct();
		pn = construction.getPetrinet();
		labels = construction.getLabels();
		initialOrPlaces = construction.getInitialOrPlaces();
		finalOrPlaces = construction.getFinalOrPlaces();

		numTransitions = pn.getTransitions().size();
	}

	/**
	 * The starting method to calculate the hop value.
	 * Going through the list of all tokens, it collects all the Multiple token and their dependents,
	 * calculates the hop for normal tokens and then for the construct under all the Multiple tokens.
	 *
	 * @param tokens
	 *            the list of tokens to use to calculate the hop value
	 * @return the final hop value
	 */
	public int hop( ArrayList<Token> tokens ) {
		ArrayList<MultipleToken> temp = new ArrayList<>();
		hopMap = new HashMap<>();
		int hopValue = 0;

		for( Token token : tokens )
			if( token.isDependent() )
				token.getDependingToken().addToken(token); //Stores token in his Dependent token
			else
				if( token instanceof MultipleToken )
					temp.add((MultipleToken) token); //Collects all multiple tokens non Dependent
				else
					hopValue += hopToken(getPlace(token.getPlaceName()));

		for( MultipleToken mToken : temp) //Starts calculating hop value for all the MultipleToken
			hopValue += multipleHop(mToken);

		return hopValue;
	}

	/**
	 * This method calculates the hop for all the token depending from the Multiple token mToken.
	 *
	 * @param mToken
	 *            the MultipleToken that stores all the dependents to calculate the hop
	 * @return the hop value
	 */
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

	/**
	 * This is the main method to select a value from the array that stores values for all branches
	 * under the Multiple token.
	 *
	 * In this case it's used to select the maximum value.
	 *
	 * @param mHop
	 *            the array that stores all the hop values from all the branches
	 * @return the hop value selected
	 */
	private int getHopValueFromArray( ArrayList<Integer> mHop ) {
		int max = mHop.get(0);
		for( int i = 1; i < mHop.size(); i++ )
			if( mHop.get(i) > max )
				max = mHop.get(i);

		return max;
	}

	/**
	 * Them main method to calculate the Hop for a token.
	 * It starts from a place and calculates all the Transition until it reaches the final place.
	 * If a Transition encountered through the path has already been visited, the method stops
	 * and adds the remaining path if it's necessary ( for calculating an hop from a Conditional case).
	 *
	 * @param place
	 *            the place where to start
	 * @return the hop value
	 */
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

	/**
	 * Gets the transitions able to fire.
	 *
	 * @return the transitions able to fire
	 */
	public ArrayList<Transition> getTransitionsAbleToFire() {
		return (ArrayList<Transition>) pn.getTransitionsAbleToFire();
	}

	/**
	 * Gets the transition condition, Trigger condition or Final State.
	 *
	 * @param t
	 *            the transition
	 * @return the condition
	 */
	public Condition getTransitionLabel( Transition t ) {
		return labels.get(t);
	}

	/**
	 * Gets the number of transitions.
	 *
	 * @return the number of transitions
	 */
	public int getNumTransitions() {
		return numTransitions;
	}

	/**
	 * Put all the tokens into the PetriNet.
	 *
	 * @param tokens
	 *            the list of tokens
	 */
	public void putTokens( ArrayList<Token> tokens ) {
		for( Token token : tokens ){
			pn.getPlace(token.getPlaceName()).addTokens(1);
		}
	}

	/**
	 * Checks if an initialOrPlace is already Multiple token.
	 * If so it adds a token to let the following transition to fire.
	 *
	 * @param place
	 *            the place to check
	 * @return true, if successful
	 */
	public boolean checkMultipleToken( Place place ){
		if( !place.hasAtLeastTokens(1) ){
			place.addTokens(1);
			return true;
		}
		return false;
	}

	/**
	 * Method used to remove all the tokens in the parallel OR case when a token gets to
	 * the finalOrPlace.
	 *
	 * @param finalOrPlace
	 *            the final or place
	 * @param tokens
	 *            the tokens
	 */
	public void removeOrTokens( Place finalOrPlace, ArrayList<Token> tokens ) {
		Place initialOrPlace = finalOrPlaces.get(finalOrPlace);
		remove(initialOrPlace, finalOrPlace, tokens);
	}

	/**
	 * Removes, from a Petrinet, all the tokens backward from a place to an initial place.
	 *
	 * @param initial
	 *            the initial place
	 * @param place
	 *            the place to start with
	 * @param tokens
	 *            the list of tokens
	 */
	private void remove( Place initial, Place place, ArrayList<Token> tokens ) {
		if( place.getName().equals( initial.getName() ) ){
			if( place.hasAtLeastTokens(1) ){
				place.removeTokens(1);
			}
			for( int i = 0; i < tokens.size(); i++ )
				if( tokens.get(i).getPlaceName().equals(place.getName()) )
					tokens.remove(i);
		}
		else{
			if( place.hasAtLeastTokens(1) ){
				place.removeTokens(1);
				for( int j = 0; j < tokens.size(); j++ )
					if( tokens.get(j).getPlaceName().equals(place.getName()) )
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



	/**
	 * Removes the tokens in the list from the Petrinet.
	 *
	 * @param tokens
	 *            the list of tokens
	 */
	public void removeTokens( ArrayList<Token> tokens ) {
		for( Token token : tokens ){
			pn.getPlace(token.getPlaceName()).removeTokens(1);
		}
	}

	/**
	 * Gets a place from his name.
	 *
	 * @param name
	 *            the place's name
	 * @return the place
	 */
	public Place getPlace( String name ) {
		return pn.getPlace(name);
	}

	/**
	 * Gets the first place in the PetriNet.
	 *
	 * @return the first place
	 */
	public Place getFirst() {
		return pn.getPlaces().get(0);
	}

	/**
	 * Gets the last place in the PetriNet.
	 *
	 * @return the last place
	 */
	public Place getLast() {
		return pn.getPlaces().get(pn.getPlaces().size() - 1);
	}

	/**
	 * Gets the first incoming place from a transition.
	 *
	 * @param transition
	 *            the transition
	 * @return the first incoming place from transition
	 */
	public Place getFirstInPlaceFromTransition( Transition transition ) {
		return transition.getIncoming().get(0).getPlace();
	}

	/**
	 * Max.
	 *
	 * @param a
	 *            first value
	 * @param b
	 *            second value
	 * @return the max
	 */
	private int max( int a, int b) {
		if( a >= b ) return a;
		else return b;
	}

	/**
	 * Checks if is final or place.
	 *
	 * @param place
	 *            the place
	 * @return true, if is final or place
	 */
	public boolean isFinalOrPlace( Place place ) {
		return finalOrPlaces.containsKey(place);
	}

	/**
	 * Checks if is initial or place.
	 *
	 * @param place
	 *            the place
	 * @return true, if is initial or place
	 */
	public boolean isInitialOrPlace( Place place ) {
		return initialOrPlaces.containsKey(place);
	}
}
