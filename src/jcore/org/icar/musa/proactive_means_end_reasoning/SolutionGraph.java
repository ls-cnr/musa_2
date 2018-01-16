package org.icar.musa.proactive_means_end_reasoning;

import java.util.HashMap;
import java.util.Iterator;

import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.proactive_means_end_reasoning.wts.EvolutionEdge;
import org.icar.musa.proactive_means_end_reasoning.wts.NormalEdge;
import org.icar.musa.proactive_means_end_reasoning.wts.OPNode;
import org.icar.musa.proactive_means_end_reasoning.wts.WTS;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;

/**
 * The SolutionGraph Artifact. It contains the graph of solutions, the score map for each World node, the map of solution WorldNodes
 * and the map of tokens for each WorldNode.
 */
public class SolutionGraph {
	
	/** The wts. */
	private WTS wts;
	
	/** The token map. */
	private HashMap<String, TokenConf> tokenMap;
	
	/** The score map. */
	private HashMap<String, Integer> scoreMapping;
	
	/** The exit node map. */
	private HashMap<String, WorldNode> exitNodeMap;
	
	/**
	 * Instantiates a new solution graph.
	 */
	public SolutionGraph(){
		this.wts = new WTS();
		this.tokenMap = new HashMap<>();
		this.scoreMapping = new HashMap<>();
		this.exitNodeMap = new HashMap<>();
	}
	
	/**
	 * Gets the graph.
	 *
	 * @return the graph
	 */
	public HashMap<String, WorldNode> getWTSHashmap(){
		return this.wts.getWTS();
	}
	
	public WTS getWTS() {
		return wts;
	}
	
	/**
	 * Gets the token map.
	 *
	 * @return the token map
	 */
	public HashMap<String, TokenConf> getTokenMap(){
		return this.tokenMap;
	}
	
	/**
	 * Gets the score mapping.
	 *
	 * @return the score mapping
	 */
	public HashMap<String, Integer> getScoreMapping(){
		return this.scoreMapping;
	}
	
	/**
	 * Gets the exit node map.
	 *
	 * @return the exit node map
	 */
	public HashMap<String, WorldNode> getExitNodeMap(){
		return this.exitNodeMap;
	}
	
	/**
	 * Adds the node.
	 *
	 * @param node
	 *            the node
	 */
	public void addNode(GraphExpansion node){
		this.wts.addExpansionNode(node);
		this.updateTokenMap(node);
		this.updateExitNodeMap(node);
	}
	
	/**
	 * Removes the node.
	 *
	 * @param node
	 *            the node
	 */
	public void removeNode(WorldNode node){
		this.wts.removeNode(node);
		this.scoreMapping.remove(node);
		this.exitNodeMap.remove(node);
	}
	
	/**
	 * Update the token map.
	 *
	 * @param node
	 *            the node
	 */
	public void updateTokenMap(GraphExpansion node){
		this.tokenMap.put(node.getSource().getWorldState().toString(),node.getSource().getTokens());
		Iterator<ExtendedNode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ExtendedNode temp = i.next();
			this.tokenMap.put(temp.getWorldState().toString(),temp.getTokens());
		}
	}
	
	/**
	 * Update exit node list.
	 *
	 * @param node
	 *            the node
	 */
	public void updateExitNodeMap(GraphExpansion node){
		if(node.getSource().isExitNode() == true)	
			this.exitNodeMap.put(node.getSource().getWorldState().toString(), wts.getWTS().get(node.getSource().getWorldState().toString()));
		Iterator<ExtendedNode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ExtendedNode temp = i.next();
			if(temp.isExitNode() == true)	this.exitNodeMap.put(temp.getWorldState().toString(), wts.getWTS().get(temp.getWorldState().toString()));
		}
	}
	
	public void updateScoreMapping(GraphExpansion node){
		Iterator i = node.getDestination().iterator();
		
		this.scoreMapping.put(node.getSource().getWorldState().toString(), node.getSource().getScore());
		
		NormalExpansion nodeToAdd;
		MultipleExpansion listToAdd;
		while(i.hasNext()){
			GraphExpansion temp =(GraphExpansion) i.next();
			if(temp instanceof NormalExpansion){
				nodeToAdd =(NormalExpansion) temp;
				this.scoreMapping.put(nodeToAdd.getDestination().get(0).getWorldState().toString(), nodeToAdd.getDestination().get(0).getScore());
			}
			else{
				listToAdd = (MultipleExpansion) temp;
				Iterator<ExtendedNode> j = node.getDestination().iterator();
				while (j.hasNext()){
					ExtendedNode Etemp = j.next();
					this.scoreMapping.put(Etemp.getWorldState().toString(), Etemp.getScore());
				}
			}
		}
	}

	
	
	/**
	 * Gets the solution paths.
	 *
	 * @return the solution paths.
	 */
//	public ArrayList<ArrayList<WorldNode>> getSolutions(){
//		return this.wts.getSolutions(new WorldNode(null), this.exitNodeMap);
//	}
	
	
	public void printForGraphviz(){
		//this.wts.printForGraphviz();
		System.out.println("digraph G {");

		// set the initial state as green
		System.out.println("\""+wts.getInitialState().getWorldState().toString()+"\"[color=green]");
		for (String node : exitNodeMap.keySet()) {
			System.out.println("\""+node+"\"[color=red]");
		}

		
		Iterator<String> i = wts.getGraphNodeIterator();
		while(i.hasNext()){
			String temp = (String) i.next();
			
			WorldNode w = wts.getGraph().get(temp);
			
			
			// draw all outgoing arcs from node to neighbours
			for( NormalEdge e : w.getOutcomingEdgeList()){
				System.out.println("\""+w.getWorldState().toString()+"\" -> \""+e.getDestination().getWorldState().toString()+"\"[label=\""+ e.getCapability()+ "\"]");
			}
			
			for( OPNode opNode : w.getOPNodeList()){
				for( EvolutionEdge ee : opNode.getOutcomingEdge()){
					//ee.getDestination().
					System.out.println("\""+w.getWorldState().toString() + "\" -> \"" + ee.getDestination().getWorldState().toString()+"\" [label=\""+ ee.getScenario()+ "\"][style=bold][color=red]");
				}
			}

		}
		System.out.println("}");
	}

}
