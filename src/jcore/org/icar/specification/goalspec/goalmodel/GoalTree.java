package org.icar.specification.goalspec.goalmodel;


import java.util.ArrayList;
import java.util.HashMap;

import org.icar.specification.goalspec.GS_Goal;

/**
 * The Class GoalTree is used to create a Tree containing goals and arcs. Construction, starting from the root, 
 * consists in two parts: adding And or Or arcs to a node contained in the main structure and then putting the new nodes in.
 * A second structure is used to maintain access to goals.
 * @author Mirko Zichichi
 */
public class GoalTree {
	
	/**  The structure that contains the three. */
	private HashMap<GS_Goal, ArrayList<RefinementArc>> tree;
	
	/**  This structure contains goals allowing to visit each one (tree's map doesn't allow to get the key, so this one is needed). */
	private HashMap<GS_Goal, GS_Goal> goals;
	
	/**  The root, the starting node. */
	private GS_Goal root;
	
	
	public void print(GS_Goal node,String rientro) {
		//System.out.println(rientro+"Node : "+node.getName());
		if (tree.get(node) != null) {
			ArrayList<RefinementArc> succ = tree.get(node);
			for (RefinementArc ref : succ) {
				print(ref.getNextNode(),rientro+"   ");
			}
		}
	}
	
	/**
	 * Instantiates a new goal tree.
	 *
	 * @param rootGoal the goal that represent root
	 */
	public GoalTree( GS_Goal rootGoal ) {
		tree = new HashMap<>();
		root = rootGoal;
		goals = new HashMap<>();
		putNode(root);
	}
	
	/**
	 * Put the node in the model and in the goals' map.
	 *
	 * @param node the node
	 */
	private void putNode( GS_Goal node ) {
		tree.put(node, null);
		goals.put(node, node);
	}
	
	/**
	 * Adds arcs to the node. Used only from addAndArcs and addOrArcs because they control if the node is already contained.
	 * If the node already has some arcs, the Method won't replace them. (Another method?)  
	 *
	 * @param node the node
	 * @param arcs to add
	 */
	private void addArcs( GS_Goal node, ArrayList<RefinementArc> arcs ) {
		if(tree.get(node) == null)
			tree.put(node, arcs);
	}
	
	/**
	 * Method called to add AndArcs or OrArcs to a certain node (based on a boolean).
	 *
	 * @param node the key
	 * @param outputs the value, is an array list that contains all the goal linked to node
	 * @throws NodeNotFoundException thrown if the key node has no match in the HashMap
	 */
	void addRefinementArcs( GS_Goal node, ArrayList<GS_Goal> outputs, boolean and ) throws NodeNotFoundException {
		if( tree.containsKey(node) ){
			ArrayList<RefinementArc> arcs = new ArrayList<>();
			
			if(and)										//AndCase
				for( GS_Goal goal : outputs ){
					if( !tree.containsKey(goal) ){
						arcs.add(new AndArc(goal));
						putNode(goal);	
					}
				}
			else										//OrCase
				for( GS_Goal goal : outputs ){
					if( !tree.containsKey(goal) ){
						arcs.add(new OrArc(goal));
						putNode(goal);	
					}
				}
			if( !arcs.isEmpty() )
				addArcs(node, arcs);
		}
		else{
			throw new NodeNotFoundException();
		}
	}
	
	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	GS_Goal getRoot() {
		return root;
	}
	
	/**
	 * Gets arcs from goal.
	 *
	 * @param goal the key for getting arcs
	 * @return goal's arcs
	 */
	ArrayList<RefinementArc> getArcs( GS_Goal goal ) {
		return tree.get(goal);
	}
	
	/**
	 * Gets the tree.
	 *
	 * @return the tree
	 */
	HashMap<GS_Goal, ArrayList<RefinementArc>> getTree(){
		return tree;
	}
	
	/**
	 * Gets the goals.
	 *
	 * @return the goals
	 */
	HashMap<GS_Goal, GS_Goal> getGoals(){
		return goals;
	}
	
}
