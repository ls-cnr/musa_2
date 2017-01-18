package layer.awareness;

import java.util.ArrayList;

import layer.awareness.goaltree.*;

/**
 * The Class GoalModel is used to visit a Goal Tree. 
 */
public class GoalModel implements RunTimeEntity{
	
	/** The Goal Tree. */
	private GoalTree tree;
	
	/**
	 * Instantiates a new goal model.
	 *
	 * @param tree the Goal Tree
	 */
	public GoalModel( GoalTree treeModel ) {
		tree = treeModel;
	}
	
	/**
	 * Gets the goal from tree using another similar goal (only name is equal) 
	 *
	 * @param goal  
	 * @return the goal to find
	 */
	public Goal getGoalFromGoal( Goal goal ) {
		return tree.getGoals().get( goal );
	}
	
	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public Goal getRoot() {
		return tree.getRoot();
	}
	
	/**
	 * Gets the arcs.
	 *
	 * @param node the node
	 * @return the arcs
	 */
	public ArrayList<RefinementArc> getArcs( Goal node ){
		return tree.getArcs(node);
	}
}
