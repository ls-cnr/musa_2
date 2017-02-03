package pmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.net.Token;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.NormalExpansionNode;

// TODO: Auto-generated Javadoc
/**
 * The Class SolutionGraph.
 */
public class SolutionGraph {
	
	/** The wts. */
	private WTS wts;
	
	/** The token map. */
	private HashMap<WorldNode, ArrayList<Token>> tokenMap;
	
	/** The score mapping. */
	private HashMap<WorldNode, Double> scoreMapping;
	
	/** The exit node map. */
	private HashMap<WorldNode, WorldNode> exitNodeMap;
	
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
	 * Gets the wts.
	 *
	 * @return the wts
	 */
	public HashMap<WorldNode, WorldNode> getWTS(){
		return this.wts.getWTS();
	}
	
	/**
	 * Gets the token map.
	 *
	 * @return the token map
	 */
	public HashMap<WorldNode, ArrayList<Token>> getTokenMap(){
		return this.tokenMap;
	}
	
	/**
	 * Gets the score mapping.
	 *
	 * @return the score mapping
	 */
	public HashMap<WorldNode, Double> getScoreMapping(){
		return this.scoreMapping;
	}
	
	/**
	 * Gets the exit node map.
	 *
	 * @return the exit node map
	 */
	public HashMap<WorldNode, WorldNode> getExitNodeMap(){
		return this.exitNodeMap;
	}
	
	/**
	 * Adds the node.
	 *
	 * @param node
	 *            the node
	 */
	public void addNode(ExpansionNode node){
		this.wts.addNode(node);
		this.updateTokenMap(node);
		this.updateExitNodeList(node);
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
	 * Update token map.
	 *
	 * @param node
	 *            the node
	 */
	public void updateTokenMap(ExpansionNode node){
		this.tokenMap.put(node.getSource().getWorldNode(), node.getSource().getTokens());
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			this.tokenMap.put(temp.getWorldNode(), temp.getTokens());
		}
	}
	
	/**
	 * Update exit node list.
	 *
	 * @param node
	 *            the node
	 */
	public void updateExitNodeList(ExpansionNode node){
		if(node.getSource().isExitNode() == true)	this.exitNodeMap.put(node.getSource().getWorldNode(), node.getSource().getWorldNode());
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			if(temp.isExitNode() == true)	this.exitNodeMap.put(temp.getWorldNode(), temp.getWorldNode());
		}
	}
	
	/**
	 * Gets the solutions.
	 *
	 * @return the solutions
	 */
	public ArrayList<ArrayList<WorldNode>> getSolutions(){
		return this.wts.getSolutions(new WorldNode(null), this.exitNodeMap);
	}
}
