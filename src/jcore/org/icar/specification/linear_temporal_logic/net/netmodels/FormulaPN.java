package datalayer.awareness.LTL.net.netmodels;

import java.util.HashMap;
import java.util.List;

import datalayer.awareness.LTL.net.PNTreeNode;
import datalayer.awareness.LTL.net.PetriNetState;
import datalayer.awareness.LTL.net.condition.TransitionCondition;
import petrinet.logic.*;

/**
 * The Class FormulaPN, used as common abstract class for all the PetriNets that represent a basic formula(a formula that contains 
 * only a LTL operator). This class, other than the PetriNet, contains and manages the informations on Places State and Transition Conditions. 
 */
public abstract class FormulaPN implements PNTreeNode {
	
	/** The first operand */
	protected TransitionCondition firstOp;
	
	/** The second operand */
	protected TransitionCondition secondOp;
	
	/** The PetriNet */
	protected Petrinet pn;
	
	/** The starting Place */
	protected Place start;
	
	/** The association between Place and State */
	protected HashMap<Place, PetriNetState> placeState;
	
	/** The conditions labels associated to Transitions */
	protected HashMap<Transition, TransitionCondition> transitionLabel;
	
	protected PNTreeNode left;
	protected PNTreeNode right;
	
	/**
	 * Instantiates a new formula PN.
	 *
	 * @param name
	 *            the name
	 */
	protected FormulaPN( String name ) {
		pn = new Petrinet(name);
		placeState = new HashMap<>();
		transitionLabel = new HashMap<>();
		
		left=null;
		right=null;
	}
	
	public void setLeftChildren(PNTreeNode node) {
		left = node;
	}
	
	public void setRightChildren(PNTreeNode node) {
		right = node;
	}

	public PNTreeNode leftChildren() {
		return left;
	}
	public PNTreeNode rightChildren() {
		return right;
	}
	public boolean isLeaf() {
		return (left==null & right==null);
	}

	
	/**
	 * Gets the transitions able to fire.
	 *
	 * @return the transitions able to fire
	 */
	public List<Transition> getTransitionsAbleToFire() {
		return pn.getTransitionsAbleToFire();
	}
	
	/**
	 * Put a token.
	 *
	 * @param place
	 *            the place
	 */
	public void putToken( String place ) {
		pn.getPlace(place).addTokens(1);
	}
	
	/**
	 * Removes the all tokens.
	 */
	public void removeAllTokens() {
		for( Place p : pn.getPlaces())
			p.removeTokens(p.getTokens());
	}
	
	/**
	 * Gets a transition condition.
	 *
	 * @param t
	 *            the transition
	 * @return the transition condition
	 */
	public TransitionCondition getTransitionCondition( Transition t ) {
		return transitionLabel.get(t);
	}
	
	/**
	 * Gets the state in which a net is actually into
	 *
	 * @return the net state
	 */
	public PetriNetState getNetState() {		 
		Place p = getPlaceWithToken();
		return placeState.get(p);
//		if( p != null )
//			if( placeState.get(p).equals("A") ) return "A";
//			else if( placeState.get(p).equals("W") ) return "W";
//			else if( placeState.get(p).equals("E") ) return "E";
//		return null;
	}
	
	/**
	 * Gets the start place.
	 *
	 * @return the start place
	 */
	public Place getStartPlace() {
		return start;
	}
	
	/**
	 * Put start token.
	 */
	public void putStartToken() {
		start.addTokens(1);
	}
	
	/**
	 * Gets the place with the token.
	 *
	 * @return the place with token
	 */
	private Place getPlaceWithToken() {
		for( Place p : pn.getPlaces()){
			if( p.hasAtLeastTokens(1) )
				return p;
		}
		return null;
	}
	
	/**
	 * Gets the place state.
	 *
	 * @return the place state
	 */
	public HashMap<Place, PetriNetState> getPlaceState() {
		return placeState;
	}


}

