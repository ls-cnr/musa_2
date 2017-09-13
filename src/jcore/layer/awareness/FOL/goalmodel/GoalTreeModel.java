package layer.awareness.FOL.goalmodel;

import java.util.ArrayList;

import layer.awareness.Goal;
import layer.awareness.RunTimeEntity;

/**
 * The Class GoalModel is used to visit a Goal Tree. 
 * @author Mirko Zichichi
 */
public class GoalTreeModel implements GoalModel{
	
	/** The Goal Tree. */
	private GoalTree tree;
	
	/**
	 * Instantiates a new goal model.
	 *
	 * @param tree the Goal Tree
	 */
	public GoalTreeModel( Goal rootGoal ) {
		tree = new GoalTree(rootGoal);
	}
	
	public void printModel() {
		tree.print(getRoot(), "");
	}
	/**
	 * Adds the AndArcs calling the function in GoalTree
	 *
	 * @param node
	 *            the node where arcs will be added
	 * @param outputs
	 *            the goals to get from the arcs
	 */
	public void addAndArcs( Goal node, ArrayList<Goal> outputs ) {
		try{
			tree.addRefinementArcs(node, outputs, true);
		}
		catch( NodeNotFoundException ex ){};//TODO Handle error
	}
	
	/**
	 * Adds the OrArcs calling the function in GoalTree
	 *
	 * @param node
	 *            the node where arcs will be added
	 * @param outputs
	 *            the goals to get from the arcs
	 */
	public void addOrArcs( Goal node, ArrayList<Goal> outputs ) {
		try{
			tree.addRefinementArcs(node, outputs, false);
		}
		catch( NodeNotFoundException ex ){};//TODO Handle error
	}
		
	/**
	 * Gets the goal from tree using another similar goal (only name is equal) 
	 *
	 * @param goal  
	 * @return the goal to find
	 */
	public Goal getGoalFromName( String goalName ) {
		return tree.getGoals().get( new Goal(goalName, null, null) );
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
