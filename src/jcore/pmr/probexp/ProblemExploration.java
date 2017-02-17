package pmr.probexp;

import java.util.ArrayList;
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
 
/**
 * The Artifact ProblemExploration. It's used by an Agent to perform evolution for the various StateOfWorld passed, using his Capability.
 * It keeps a list of all the expanded nodes ready for bidding in auctions.
 * @author Alessandro Fontana
 * @author Mirko Zichichi
 */
public class ProblemExploration {

	/** The Assumption Set defined globally that the Agent has to maintain */
	private AssumptionSet assumptions;
	
	/** The Capabilities List that an Agent holds */
	private ArrayList<AbstractCapability> capabilities;
	
	/** The sorted List of ENode to visit */
	private ArrayList<ENode> toVisit;
	
	/** The List of visited ENode */
	private ArrayList<ENode> visited;
	
	/** The sorted List that contains all the ExpansionNodes generated by the Agent  */
	private ArrayList<ExpansionNode> expandedList;
	
	/** The Net generated by the GoalModel */
	private Net net;
	
	/**
	 * Instantiates a new ProblemExploration.
	 *
	 * @param model
	 *            the GoalModel to follow
	 * @param capabilities
	 *            the Capabilities List proper of the Agent
	 */
	public ProblemExploration( GoalModel model, ArrayList<AbstractCapability> capabilities, AssumptionSet assumptions) {
		this.capabilities = new ArrayList<>(capabilities);
		this.assumptions = assumptions;
		toVisit = new ArrayList<>();
		visited = new ArrayList<>();
		expandedList = new ArrayList<>();
		
		//Debug time
		long debugnewNetTimeStart = System.currentTimeMillis();
		
		net = new Net(model);
		
		//Debug time
		long debugnewNetTimeStop = System.currentTimeMillis();
		long debugnewNetTimeElapse = debugnewNetTimeStop - debugnewNetTimeStart;
		System.out.println("new Net, eseguito in: " + debugnewNetTimeElapse);
	}

	/**
	 * This Operation is used to add a new world node to the toVisit List.
	 *
	 * @param node
	 *            the WorldNode from SolutionGraph
	 * @param tokens
	 *            the Tokens List associated to that node 
	 * @param score
	 *            the Score associated to that node
	 */
	public void addToVisit( WorldNode node, ArrayList<Token> tokens, int score ) {
		toVisit.add( new ENode(node, tokens, score, false) );
	}
	
	/**
	 * The main operation, it's used to expand an ENode from the toVisit List.
	 * It entails if one of is Capability is compatible with the StateOfWorld contained in the ENode.
	 * If so, it calls a set of methods used to ultimate the expansion, in order: applyExpand, applyNet, score.
	 * Finally, whatever the case is, it adds the ENode to the visited List. 
	 */
	public void expandNode() {

		//Debug time
		long debuggetHighestTimeStart = System.currentTimeMillis();
		
		ENode enode = getHighestNodeToVisit();
		
		//Debug time
		long debuggetHighestTimeStop = System.currentTimeMillis();
		long debuggetHighestTimeElapse = debuggetHighestTimeStop - debuggetHighestTimeStart;
		System.out.println("getHighest, metodo eseguito in: " + debuggetHighestTimeElapse);
		
		for( AbstractCapability capability : capabilities ){
			
			//Debug time
			long debugDomainEntailTimeStart = System.currentTimeMillis();
			
			if(DomainEntail.getInstance().entailsCondition(enode.getWorldNode().getWorldState(), this.assumptions, capability.getPreCondition()) == true){
				//Starts the expansion
				//Debug time
				long debugDomainEntailTimeStop = System.currentTimeMillis();
				long debugDomainEntailElapse = debugDomainEntailTimeStop - debugDomainEntailTimeStart;
				System.out.println("DomainEntail, tempo trascorso per il confronto: " + debugDomainEntailElapse);
				
				//Debug time
				long debugapplyExpandTimeStart = System.currentTimeMillis();
				
				ExpansionNode expNode = applyExpand(enode, capability);
				
				//Debug time
				long debugapplyExpandTimeStop = System.currentTimeMillis();
				long debugapplyExpandTimeElapse = debugapplyExpandTimeStop - debugapplyExpandTimeStart;
				System.out.println("applyExpand, eseguito in: " + debugapplyExpandTimeElapse);
				
				//Applies the net to ultimate the expansion
				
				//Debug time
				long debugapplyNetTimeStart = System.currentTimeMillis();	
				int debugapplyNetCount = 0;
				
				for( ENode destination : expNode.getDestination() ){
					applyNet(expNode.getSource().getTokens(), destination, expNode);
					debugapplyNetCount++;
				}
				
				//Debug time
				long debugapplyNetTimeStop = System.currentTimeMillis();
				long debugapplyNetTimeElapse = debugapplyNetTimeStop - debugapplyNetTimeStart;
				System.out.println("applyNet, numero cicli:" +debugapplyNetCount+ ", eseguiti in: " + debugapplyNetTimeElapse);
				
				//Elaborates the Expansion score
				
				//Debug time
				long debugscoreTimeStart = System.currentTimeMillis();
				
				score(expNode);
				
				//Debug time
				long debugscoreTimeStop = System.currentTimeMillis();
				long debugscoreTimeElapse = debugscoreTimeStop - debugscoreTimeStart;
				System.out.println("score, metodo eseguito in: " + debugscoreTimeElapse);
				
				//Adds the Expansion to the List in order 
				expandedList.add(expNode);
			}
		}
		
		visited.add(enode);
	}	
	
	/**
	 * Operation used to get the highest ExpansionNode
	 *
	 * @return the highest ExpansionNode
	 */
	public ExpansionNode getHighestExpansion(){
		expandedList.sort(ExpansionNode.getScoreComparator());
		int index = this.expandedList.size() - 1;
		return this.expandedList.get(index);
	}
	
	//Funzione che crea gli stati del mondo successivi, applicando un'evoluzione agli scenari associati alla capability passata
	/**
	 * Apply expand.
	 *
	 * @param enode
	 *            the enode
	 * @param capability
	 *            the capability
	 * @return the expansion node
	 */
	//Se la capability contiene un solo elemento sar� un evoluzione semplice gestita da un NormalExpansionNode.
	private ExpansionNode applyExpand( ENode enode, AbstractCapability capability) {
		if(capability.getScenarioSet().size() == 1){
			//Creo un oggetto di tipo WorldEvolution che dato un AssumptionSet ed un StateOfWorld ci da le evoluzioni.
			WorldEvolution evo = new WorldEvolution(this.assumptions, enode.getWorldNode().getWorldState());
			//Uso un iteratore perch� il set non mi fa accedere ai singoli elementi. In questo caso l'elemento � uno solo
			//Ed � l'ultimo della lista delle evoluzioni, dato che in ogni caso WorldEvolution salva lo StateOfWorld source.
			
			//Debug time
			long debugWorldEvolutionTimeStart = System.currentTimeMillis();
			int debugWorldEvolutionCount = 0;
			
			Iterator i = capability.getScenarioSet().iterator();
			if(i.hasNext()){
				EvolutionScenario temp =(EvolutionScenario) i.next();
				evo.addEvolution(temp.getOperators());
				debugWorldEvolutionCount++;
			}
			
			//Debug time
			long debugWorldEvolutionTimeStop = System.currentTimeMillis();
			long debugWorldEvolutionTimeElapse = debugWorldEvolutionTimeStop - debugWorldEvolutionTimeStart;
			System.out.println("WordEvolution(NormalExpansionNode), numero cicli: "+debugWorldEvolutionCount+", eseguiti in: " + debugWorldEvolutionTimeElapse);
			
			ArrayList<ENode> newEnodeList = new ArrayList<ENode>();
			ENode newEnode = new ENode(new WorldNode(evo.getEvolution().getLast()));
			newEnodeList.add(newEnode);
			this.toVisit.add(newEnode);
			ExpansionNode result = new NormalExpansionNode(enode, newEnodeList, capability);
			return result;
		}
		else{
			//Se la capability ha pi� scenari, devo creare una WorldEvolution per scenario. Ogni WorldEvolution produrr�
			//Uno StateOfWorld, che verr� inglobato in un nodo che a sua volta finir� nella lista delle destinazioni
			//Del MultipleExpansioNode. Inoltre si aggiunge alla mappa dei nodi-scenari associati, la coppia nodo-scenario.
			MultipleExpansionNode expNode = new MultipleExpansionNode(enode, new ArrayList<ENode>(), capability);
			
			//Debug time
			long debugWorldEvolutionTimeStart2 = System.currentTimeMillis();
			
			Iterator i = capability.getScenarioSet().iterator();
			while(i.hasNext()){
				WorldEvolution evo = new WorldEvolution(this.assumptions, enode.getWorldNode().getWorldState());
				EvolutionScenario temp = (EvolutionScenario) i.next();
				evo.addEvolution(temp.getOperators());
				ENode newEnode = new ENode(new WorldNode(evo.getEvolution().getLast()));
				this.toVisit.add(newEnode);
				expNode.addDestination(newEnode);
				expNode.addScenario(newEnode, temp);
			}
			
			//Debug time
			long debugWorldEvolutionTimeStop2 = System.currentTimeMillis();
			long debugWorldEvolutionTimeElapse2 = debugWorldEvolutionTimeStop2 - debugWorldEvolutionTimeStart2;
			System.out.println("WordEvolution(MultipleExpantionNode), ciclo eseguito in: " + debugWorldEvolutionTimeElapse2);
			ExpansionNode result = expNode;
			return result;
		}
	}
	
	/**
	 * After the expansion a new ENode has been created, but it's empty. This
	 * method fills up the remaining attributes using the Net.
	 * 
	 * For every transition able to fire, it checks if the new state of world
	 * entails the condition labeled in the transition (trigger condition or
	 * final state). Then if the condition is true, it fires the token and
	 * elaborate the new token map. Finally it calls fillENode to fill up the
	 * ENode.
	 * 
	 * It handles the creation of a new Token List considering the OR "special"
	 * condition. Every Conditional Case has an InitialOrPlace and a FinalOrPlace.
	 * The InitialOrPlace has a special MultipleToken that allows to start 
	 * multiple parallel path for that case. In every path, called Branch, every
	 * new Token is created dependent from the MultipleToken and with his branch
	 * stored. When the first Token gets to the FinalOrPlace, it wins the "race"
	 * and all the remaining Tokens in every parallel path are destroyed.
	 *
	 * @param startingTokens
	 *            the list of token to start with
	 * @param enode
	 *            the new eNode created from expansion
	 */
	
	private void applyNet( ArrayList<Token> startingTokens, ENode enode , /*debug*/ ExpansionNode mast) {
		/*debug*/
		MultipleExpansionNode nk = (MultipleExpansionNode) mast;
		//System.out.println(nk.getScenario(enode).getName());
		/*****/
		StateOfWorld state = enode.getWorldNode().getWorldState();
		ArrayList<Token> tokens = new ArrayList<>(startingTokens);
		//Prepares the net with tokens
		net.putTokens(startingTokens);
		ArrayList<Transition> transitionsATF= net.getTransitionsAbleToFire();
		
		//Checking compatibility with StateOfWorld for every Transition and Firing
		for( int i = 0, branchCount = 0; i < transitionsATF.size(); i++ ){
			Transition t = transitionsATF.get(i);
			
			if( DomainEntail.getInstance().entailsCondition(state, assumptions, net.getTransitionLabel(t)) ){
				Place place = net.getFirstInPlaceFromTransition(t);
				
				//Starting a "special" OR condition 
				if( net.isInitialOrPlace(place) ){
					//Checking MultipleToken presence and Getting the reference
					MultipleToken mulTok = null;
					for( Token mulTokOld : tokens )
						if( mulTokOld.getPlaceName().equals(place.getName()) ){
							if( mulTokOld instanceof MultipleToken ){
								mulTok = (MultipleToken) mulTokOld;
								net.checkMultipleToken(place);
							}
							else{
								mulTok = new MultipleToken(place.getName());
								tokens.remove(mulTokOld);
								tokens.add(mulTok);
							}
						}
					
					//Adding Tokens for the fired Transition outgoing Places, setting DependentToken and Branch for each one
					for(Arc arcOut : t.getOutgoing()){		
						Place finalPlace = arcOut.getPlace();
						tokens.add( new Token(finalPlace.getName(), mulTok, branchCount) ); //It's sure that it isn't a finalOrPlace
					}
					branchCount++;
				}
				//Normal case
				else{
					Token old = null;
					//Removing Tokens from the fired Transition incoming Places
					for( Arc arcIn : t.getIncoming() ){
						Place initialPlace = arcIn.getPlace();
						for( int k = 0; k < tokens.size(); k++ ){
							Token tok = tokens.get(k);
							if( tok.getPlaceName().equals(initialPlace.getName()) )
								if( !( tok instanceof MultipleToken ) ){
									old = tok;
									tokens.remove(k);
								}
						}									
					}
					//Adding Tokens for the fired Transition outgoing Places
					for( Arc arcOut : t.getOutgoing() ){
						Place finalPlace = arcOut.getPlace();
						//If finalOrPlace removes Tokens in special OR condition and their associated Transitions 
						if( net.isFinalOrPlace(finalPlace) ) {
							tokens.add( new Token(finalPlace.getName()) );
							net.removeOrTokens(finalPlace, tokens);
							for( int j = i + 1; j < transitionsATF.size(); j++ ) //Removes transitions that cannot fire anymore
								if( !transitionsATF.get(j).canFire() )           //(those in the special OR case)
									transitionsATF.remove(j);
						}
						else
							if( old != null )
								tokens.add( new Token(finalPlace.getName(), old.getDependingToken(), old.getBranch()) );
							else
								tokens.add(new Token(finalPlace.getName()));
					}
				}
				
				t.fire();
			}
		}
		
		//Cleans the net from tokens
		net.removeTokens(tokens);
		
		//Fills up ENode
		fillENode(enode, tokens);
	}
	
	/**
	 * This method is used in applyNet to fill up the remaining attributes in ENode.
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
	 *            the ENode to fill up
	 * @param tokens
	 *            the ENode's tokens 
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
	 * Method used to get the highest node to visit.
	 *
	 * @return the highest node to visit
	 */
	private ENode getHighestNodeToVisit(){
		int index = toVisit.size() - 1;
		System.out.println(index);
		return toVisit.remove(index);
	}
	
	public boolean toVisitIsEmpty() {
		return toVisit.isEmpty();
	}
	
	/**
	 * Removes the expanded list.
	 */
	public void removeExpandedList(){
		this.expandedList.removeAll(this.expandedList);
	}
	
	/**
	 * This operation is used to set new Capabilities
	 *
	 * @param capabilities
	 *            the new Capabilities List
	 */
	public void setCapabilities(ArrayList<AbstractCapability> capabilities){
		this.capabilities = capabilities;
	}
	
	/**
	 * This operation is used to add a new Capability
	 *
	 * @param capability
	 *            the new Capability
	 */
	public void addCapability(AbstractCapability capability){
		this.capabilities.add(capability);
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

	/* metodo utile al testing */
	public ArrayList<ExpansionNode> getExpandedList(){
		return this.expandedList;
	}
	public ArrayList<ENode> getToVisit(){
		return this.toVisit;
	}
}
