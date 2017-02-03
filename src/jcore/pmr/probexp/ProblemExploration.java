package pmr.probexp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import layer.awareness.AbstractCapability;
import layer.awareness.DomainEntail;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.*;
import layer.semantic.AssumptionSet;
import layer.semantic.StateOfWorld;
import layer.semantic.WorldEvolution;
import layer.semantic.evolution.EvolutionScenario;
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

	public void addToVisit( WorldNode node, ArrayList<Token> tokens, int score ) {
		toVisit.add( new ENode(node, tokens, score, false) );
		toVisit.sort(ENode.getScoreComparator());
	}
		
	
	public void setCapabilities(ArrayList<AbstractCapability> capabilities){
		this.capabilities = capabilities;
	}
	
	public void addCapability(AbstractCapability capability){
		this.capabilities.add(capability);
	}
	
	public ExpansionNode getHighestExpansion(){
		int index = this.expandedList.size() - 1;
		return this.expandedList.get(index);
	}
	
	public void removeExpandedList(){
		this.expandedList.removeAll(this.expandedList);
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
			if(DomainEntail.getInstance().entailsCondition(enode.getWorldNode().getWorldState(), this.assumptions, capability.getPreCondition()) == true){
				ExpansionNode expNode = applyExpand(enode, capability);
			
				
				for( ENode destination : expNode.getDestination() )
					applyNet(expNode.getSource().getTokens(), destination);
				
				score(expNode);
				
				expandedList.add(expNode);
				expandedList.sort(ExpansionNode.getScoreComparator());
			}
			visited.add(enode);
		}
	}
	
	public ENode getHighestNodeToVisit(){
		int index = toVisit.size() - 1;
		return toVisit.remove(index);
	}
	
	
	//Funzione che crea gli stati del mondo successivi, applicando un'evoluzione agli scenari associati alla capability passata
	//Se la capability contiene un solo elemento sarà un evoluzione semplice gestita da un NormalExpansionNode.
	private ExpansionNode applyExpand( ENode enode, AbstractCapability capability) {
		if(capability.getScenarioSet().size() == 1){
			//Creo un oggetto di tipo WorldEvolution che dato un AssumptionSet ed un StateOfWorld ci da le evoluzioni.
			WorldEvolution evo = new WorldEvolution(this.assumptions, enode.getWorldNode().getWorldState());
			//Uso un iteratore perché il set non mi fa accedere ai singoli elementi. In questo caso l'elemento è uno solo
			//Ed è l'ultimo della lista delle evoluzioni, dato che in ogni caso WorldEvolution salva lo StateOfWorld source.
			Iterator i = capability.getScenarioSet().iterator();
			if(i.hasNext()){
				EvolutionScenario temp =(EvolutionScenario) i.next();
				evo.addEvolution(temp.getOperators());
			}
			ArrayList<ENode> newEnodeList = new ArrayList<ENode>();
			ENode newEnode = new ENode(new WorldNode(evo.getEvolution().getLast()));
			newEnodeList.add(newEnode);
			ExpansionNode result = new NormalExpansionNode(enode, newEnodeList, capability);
			return result;
		}
		else{
			//Se la capability ha più scenari, devo creare una WorldEvolution per scenario. Ogni WorldEvolution produrrà
			//Uno StateOfWorld, che verrà inglobato in un nodo che a sua volta finirà nella lista delle destinazioni
			//Del MultipleExpansioNode. Inoltre si aggiunge alla mappa dei nodi-scenari associati, la coppia nodo-scenario.
			MultipleExpansionNode expNode = new MultipleExpansionNode(enode, new ArrayList<ENode>(), capability);
			WorldEvolution evo = new WorldEvolution(this.assumptions, enode.getWorldNode().getWorldState());
			Iterator i = capability.getScenarioSet().iterator();
			while(i.hasNext()){
				EvolutionScenario temp = (EvolutionScenario) i.next();
				evo.addEvolution(temp.getOperators());
				ENode newEnode = new ENode(new WorldNode(evo.getEvolution().getLast()));
				expNode.addDestination(newEnode);
				expNode.addScenario(newEnode, temp);
			}
			ExpansionNode result = expNode;
			return result;
		}
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
	private void applyNet( ArrayList<Token> startingTokens, ENode enode ) {
		StateOfWorld state = enode.getWorldNode().getWorldState();
		ArrayList<Token> tokens = new ArrayList<>();
		
		net.putTokens(startingTokens);	//Prepares the net with tokens
		
		ArrayList<Transition> transitionsATF= net.getTransitionsAbleToFire();
		
		//checking and firing
		for( int i = 0, count = 0; i < transitionsATF.size(); i++ ){
			Transition t = transitionsATF.get(i);
			if( DomainEntail.getInstance().entailsCondition(state, assumptions, net.getTransitionLabel(t)) ){
				Place place = net.getFirstInPlaceFromTransition(t);
				
				if( net.isInitialOrPlace(place) ){
					
					MultipleToken mulTok = null;
					if( !net.checkMultipleToken(place) ){ 
						mulTok = new MultipleToken(place.getName());
						tokens.add(mulTok);
					}
					if( mulTok == null )
						for( Token mulTokOld : tokens )
							if( mulTokOld.getPlaceName() == place.getName() )
								mulTok = (MultipleToken) mulTokOld;
					
					for(Arc arcOut : t.getOutgoing()){		//Adding tokens from the fired transition outgoing places 
						Place finalPlace = arcOut.getPlace();
						tokens.add( new Token(finalPlace.getName(), mulTok, count) ); //It's sure that it isn't a finalOrPlace
					}
					count++;
				}
				else
					for( Arc arcOut : t.getOutgoing() ){
						Place finalPlace = arcOut.getPlace();
						if( net.isFinalOrPlace(finalPlace) ) {
							tokens.add( new Token(finalPlace.getName()) );
							net.removeOrTokens(finalPlace, tokens);
							for( int j = i + 1; j < transitionsATF.size(); j++ )
								if( !transitionsATF.get(j).canFire() )
									transitionsATF.remove(j);
						}
						else
							for( Token initToken : startingTokens )
								if( initToken.getPlaceName() == place.getName() )
									tokens.add( new Token(finalPlace.getName(), initToken.getDependentToken(), initToken.getBranch()) );
					}
				
				t.fire();
			}
			else
				for(Arc arcIn : t.getIncoming()){		//Adding tokens from the transition incoming places
					Place p = arcIn.getPlace();
					for( Token initToken : startingTokens )
						if( initToken.getPlaceName() == p.getName() ){
							if( initToken instanceof MultipleToken )
								tokens.add(new MultipleToken(initToken.getPlaceName(), initToken.getDependentToken(), initToken.getBranch()));
							else 
								tokens.add( new Token(initToken.getPlaceName(), initToken.getDependentToken(), initToken.getBranch()) );
							break;
						}	
				}
		}
		
		net.removeTokens(tokens); //Cleans the net from tokens
		enode.setTokens(tokens);
		//fillENode(enode, tokens);//Fills up enode
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
	private void fillENode( ENode enode, ArrayList<Token> tokens ) {
				
		enode.setTokens(tokens);
		
		//Checking if it's an exit node
		for( Token token : tokens )
			if( net.getPlace(token.getPlaceName()).equals(net.getLast()) ) enode.setExit(true);
		
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
	
	public void toVisitSort(){
		Collections.sort(this.toVisit, ENode.getScoreComparator());
	}
	
	public void expansionListSort(){
		Collections.sort(this.expandedList, ExpansionNode.getScoreComparator());
	}
}
