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
		
	/*
	START
	N <- highestNodeToVisit        (remove)
	for each C in Capability
	       if N.StateOfW |= c.precondition == true then 
	             eNode <- apply_expand(C, N)
	             eNode2 <- apply_pn(eNode)
	             score(eNode2)
	             ExpandList <- Node2
	Visited <- N
	END
	*/	
	
	public void addToVisit( WorldNode node, ArrayList<String> tokens, double score ) {
		toVisit.add( new ENode(node, tokens, score) );
	}
	
	/**
	 * After the expansion a new ENode has been created, but it's empty. 
	 * This method fill up the remaining attributes using the Net.
	 * 
	 * @param enode the new eNode created from expansion
	 * @return
	 */
	private ENode applyNet( ENode starter, ENode enode ) {
		StateOfWorld state = enode.getWorldNode().getWorldState();
		ArrayList<String> tokens = starter.getTokens();
		
		net.putTokens(tokens);
		for( Transition t : net.getTransitionsAbleToFire() )
			if( DomainEntail.getInstance().entailsCondition(state, assumptions, net.getTransitionLabel(t)) )
				net.fire(t); 
		tokens = net.getTokens();
		net.removeTokens(tokens);
		
		enode.setTokens(tokens);
		//exit??
		return enode;
	}
	
	
}
