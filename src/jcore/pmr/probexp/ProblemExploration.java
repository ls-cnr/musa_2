package pmr.probexp;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;
import layer.awareness.DomainEntail;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.Net;
import layer.semantic.AssumptionSet;
import layer.semantic.StateOfWorld;
import petrinet.logic.Arc;
import petrinet.logic.Place;
import petrinet.logic.Transition;
import pmr.graph.WorldNode;

public class ProblemExploration {

	private AssumptionSet assumptions;
	private ArrayList<AbstractCapability> capabilities;
	private ArrayList<ENode> toVisit;
	private ArrayList<ENode> visited;
	private ArrayList<ExpansionNode> expandedList;
	private Net net;
	
	public ProblemExploration( GoalModel model /*, capabilities list*/ ) {
		toVisit = new ArrayList<>();
		net = new Net(model);
	}

	public void addToVisit( WorldNode node, ArrayList<String> tokens, int score ) {
		toVisit.add( new ENode(node, tokens, score, false) );
	}
		
	/*
	START
	eN <- highestNodeToVisit        (remove)
	for each C in Capability
	       if eN.StateOfW |= c.precondition == true then 
	             expansionNode <- apply_expand(C, eN)
	             apply_pn(expansionNode)
	             score(expansionNode)
	             ExpandList <- expansionNode
	Visited <- eN
	END
	*/	
	
	public void expandNode() {
		ENode enode = getHighestNodeToVisit();
		for( AbstractCapability capability : capabilities ){
			if( true /*enode.getWorldNode().getWorldState() != capability.precondition*/){
				ExpansionNode expNode;
				
				expNode = applyExpand(enode, capability);
				
				for( ENode destination : expNode.getDestination() )
					applyNet(expNode.getSource().getTokens(), destination);
				
				score(expNode);
				
				expandedList.add(expNode);
			}
			visited.add(enode);
		}
	}
	
	public ENode getHighestNodeToVisit(){
		return toVisit.remove(0);
	}
	
	private ExpansionNode applyExpand( ENode enode, AbstractCapability capability) {
		/*TODO*/
		return new NormalExpansionNode(enode, new ArrayList<ENode>(), capability);
	}
	
	/**
	 * After the expansion a new ENode has been created, but it's empty. 
	 * This method fills up the remaining attributes using the Net.
	 * 
	 * For every transition able to fire, it checks if the new state of world entails the condition labeled in 
	 *  the transition (trigger condition or final state).
	 * Then if the condition is true, it fires the token and elaborate the new token map.
	 * Finally it calls fillENode to fill up the enode.  
	 * 
	 * @param enode the new eNode created from expansion
	 * @param startingTokens the list of token to start with
	 */
	private void applyNet( ArrayList<String> startingTokens, ENode enode ) {
		StateOfWorld state = enode.getWorldNode().getWorldState();
		ArrayList<String> tokens = new ArrayList<>();
		
		net.putTokens(startingTokens);	//Prepares the net with tokens
		
		//checking and firing
		for( Transition t : net.getTransitionsAbleToFire() ) //lista aggiornata???
			if( DomainEntail.getInstance().entailsCondition(state, assumptions, net.getTransitionLabel(t)) ){
				t.fire();
				for(Arc arcOut : t.getOutgoing())		//Adding tokens from the fired transition outgoing places 
					tokens.add(arcOut.getPlace().getName());
			}
			else
				for(Arc arcIn : t.getIncoming())		//Adding tokens from the transition ingoing places
					tokens.add(arcIn.getPlace().getName());
		
		net.removeTokens(tokens); //Cleans the net from tokens
		
		fillENode(enode, tokens);//Fills up enode
	}
	
	/**
	 * This method is used in applyNet to fill up the remaining attributes in enode.
	 * 
	 * First part consists in setting the list of tokens.
	 * 
	 * Second part consists in calculating the Hops in the net and checking if it's an exit node.
	 * 
	 * In third part the SCORE is elaborated using a function that follows a color rule.
	 * The function sets a promising node at a value near 255 to represent WHITE, 0 for BLACK,  or
	 * in between to represent GRAY.
	 *
	 * @param enode
	 *            the enode to fill up
	 * @param tokens
	 *            the enode's tokens 
	 */
	private void fillENode( ENode enode, ArrayList<String> tokens ) {
				
		enode.setTokens(tokens);
		
		//Checking if it's an exit node
		for( String token : tokens )
			if( net.getPlace(token).equals(net.getLast()) ) enode.setExit(true);
		
		//Calculating Hops
		int nHop = net.hop(tokens);
		
		//Elaborating score
		int score = 0;
		if( enode.isExitNode() ) 
			score = 255;
		else 
			score = (int) 255 - (255 / net.getNumTransitions()) * nHop;
		enode.setScore(score);
	}
	
	/**
	 * The score function that elaborates the score for an ExpansionNode.
	 * If expNode has multiple destinations it sets the score to the minimum 
	 *  score obtained by its eNode destinations.   
	 *
	 * @param expNode
	 *            the expansionNode that needs a score
	 */
	private void score( ExpansionNode expNode ) {
		int score = 0;
		for( ENode destination : expNode.getDestination() ){
			score = max(score, destination.getScore());
		}
		expNode.setScore(score);
	}
	
	/**
	 * A standard method for finding the minimum value between two.
	 *
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @return the minimum between the two values
	 */
	private int max( int a, int b ){
		if( a >= b ) 
			return a;
		else 
			return b;
	}
	
}
