package datalayer.awareness.LTL.net;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import datalayer.awareness.LTL.formulamodel.*;
import datalayer.awareness.LTL.net.condition.TransitionCondition;
import datalayer.awareness.LTL.net.netmodels.FormulaPN;
import petrinet.logic.Transition;

/**
 * The Class Nets holds every Net generated from the main LTL formula. It's used to manage the Petri Nets dynamic behavior, controlling 
 * tokens firing, transitions conditions and places state. It's also used for hop elaboration. 
 */
public class PNHierarchy {
	
	/** The dictionary that associates an id to a Net. */
	private HashMap<String, FormulaPN> nets;
	
	/** The nets used for hop elaboration */
	private HashSet<String> hopNets;
	
	/** The starting formula name. */
	private final String startingName = "Formula0"; 
		
	/**
	 * Instantiates a new nets.
	 *
	 * @param tree
	 *            the tree
	 */
	public PNHierarchy( LTLGoal tree ) {
		hopNets = new HashSet<>();
		this.nets = PNHierarchyConstruction.construct(tree, hopNets);
	}
	
	/**
	 * It puts tokens in all nets given a configuration.
	 *
	 * @param tokens
	 *            the tokens
	 */
	public void putTokens( TokensConfiguration tokens ) {
		for( String net : tokens.getNets() )
			for( String place : tokens.getTokens(net) )
				nets.get(net).putToken(place);
	}
	
	/**
	 * Removes all the tokens from the nets.
	 */
	public void removeTokens() {
		for( String net : nets.keySet() )
			nets.get(net).removeAllTokens();
	}
	
	/**
	 * Gets the transitions able to fire of a net.
	 *
	 * @param net
	 *            the net
	 * @return the transitions able to fire
	 */
	public List<Transition> getTransitionsAbleToFire( String net ) {
		return nets.get(net).getTransitionsAbleToFire();
	}
	
	/**
	 * Gets the transition condition given the net.
	 *
	 * @param net
	 *            the net
	 * @param t
	 *            the t
	 * @return the transition condition
	 */
	public TransitionCondition getTransitionCondition( String net, Transition t ) {
		return nets.get(net).getTransitionCondition(t);
		
	}
	
	/**
	 * Elaborates an hop value considering the state of each hop net. The hop nets are the ones that are father of a leaf.
	 * The hop is calculated given the number of accepted hop nets on the total number of hop nets.
	 *
	 * @return the value
	 */
	public double hop() {
		PetriNetState tmp = nets.get(startingName).getNetState();
		if( tmp==PetriNetState.ACCEPTED ) // .equals("A") )
			return 0;
		else if( tmp==PetriNetState.ERROR ) // tmp.equals("E") )
			return 1;
		else{
			double count = 0;
			
			for( String s : hopNets ){
				if( getNetState(s) != null ) {
					if( getNetState(s) == PetriNetState.WAIT_BUT_ACCEPTED ) // .equals("W") )
						count = count + 0.6;
					if( getNetState(s) == PetriNetState.WAIT_BUT_ERROR ) // .equals("W") )
						count = count + 0.2;
				} else {
					count = count + 0.4;
				}
			}
			return (double) count / hopNets.size();
		}
	}
	
	/**
	 * Inits a net putting a token in the start place.
	 *
	 * @param net
	 *            the net
	 * @return the string
	 */
	public String initNet( String net ) {
		nets.get(net).putStartToken();
		return nets.get(net).getStartPlace().getName();
	}
	
	/**
	 * Gets the net state.
	 *
	 * @param net
	 *            the net
	 * @return the net state
	 */
	public PetriNetState getNetState( String net ) {
		return nets.get(net).getNetState();
	}
	
	/**
	 * Gets the starting net.
	 *
	 * @return the starting net
	 */
	public String getStartingNet() {
		return startingName;
	}
	
	/**
	 * Gets the starting PN.
	 *
	 * @return the starting PN
	 */
	public FormulaPN getStartingPN() {
		return nets.get(startingName);
	}
	
	
	// For testing
	public HashMap<String, FormulaPN> getNets() {
		return nets;
	}
	
	
}
