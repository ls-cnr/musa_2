package pmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.net.Token;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.MultipleExpansionNode;
import pmr.probexp.NormalExpansionNode;

/**
 * The SolutionGraph Artifact. It contains the graph of solutions, the score map
 * for each World node, the map of solution WorldNodes and the map of tokens for
 * each WorldNode.
 */
public class SolutionGraph {

	/** The wts. */
	private WTS wts;

	/** The token map. */
	private HashMap<String, ArrayList<Token>> tokenMap;

	/** The score map. */
	private HashMap<String, Integer> scoreMapping;

	/** The exit node map. */
	private HashMap<String, WorldNode> exitNodeMap;

	/**
	 * Instantiates a new solution graph.
	 */
	public SolutionGraph() {
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
	public HashMap<String, WorldNode> getWTSHashmap() {
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
	public HashMap<String, ArrayList<Token>> getTokenMap() {
		return this.tokenMap;
	}

	/**
	 * Gets the score mapping.
	 *
	 * @return the score mapping
	 */
	public HashMap<String, Integer> getScoreMapping() {
		return this.scoreMapping;
	}

	/**
	 * Gets the exit node map.
	 *
	 * @return the exit node map
	 */
	public HashMap<String, WorldNode> getExitNodeMap() {
		return this.exitNodeMap;
	}

	/**
	 * Adds the node.
	 *
	 * @param node
	 *            the node
	 */
	public void addNode(ExpansionNode node) {
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
	public void removeNode(WorldNode node) {
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
	public void updateTokenMap(ExpansionNode node) {
		this.tokenMap.put(node.getSource().getWorldState().toString(), node.getSource().getTokens());
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()) {
			ENode temp = i.next();
			this.tokenMap.put(temp.getWorldState().toString(), temp.getTokens());
		}
	}

	/**
	 * Update exit node list.
	 *
	 * @param node
	 *            the node
	 */
	public void updateExitNodeMap(ExpansionNode node) {
		if (node.getSource().isExitNode() == true)
			this.exitNodeMap.put(node.getSource().getWorldState().toString(),
					wts.getWTS().get(node.getSource().getWorldState().toString()));
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()) {
			ENode temp = i.next();
			if (temp.isExitNode() == true)
				this.exitNodeMap.put(temp.getWorldState().toString(),
						wts.getWTS().get(temp.getWorldState().toString()));
		}
	}

	public void updateScoreMapping(ExpansionNode node) {
		Iterator i = node.getDestination().iterator();

		this.scoreMapping.put(node.getSource().getWorldState().toString(), node.getSource().getScore());

		NormalExpansionNode nodeToAdd;
		@SuppressWarnings("unused")
		MultipleExpansionNode listToAdd;
		while (i.hasNext()) {
			ExpansionNode temp = (ExpansionNode) i.next();
			if (temp instanceof NormalExpansionNode) {
				nodeToAdd = (NormalExpansionNode) temp;
				this.scoreMapping.put(nodeToAdd.getDestination().get(0).getWorldState().toString(),
						nodeToAdd.getDestination().get(0).getScore());
			} else {
				listToAdd = (MultipleExpansionNode) temp;
				Iterator<ENode> j = node.getDestination().iterator();
				while (j.hasNext()) {
					ENode Etemp = j.next();
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
	// public ArrayList<ArrayList<WorldNode>> getSolutions(){
	// return this.wts.getSolutions(new WorldNode(null), this.exitNodeMap);
	// }

	public void printGraph() {
		this.wts.printGraph();
	}
}
