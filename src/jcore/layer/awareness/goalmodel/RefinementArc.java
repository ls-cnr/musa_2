package layer.awareness.goalmodel;

import layer.awareness.Goal;

/**
 * The Class RefinementArc, used as generalization for And and Or arcs.
 */
public abstract class RefinementArc {
	
	/** The node where the arc points */
	public Goal nextNode;
	
	/**
	 * Instantiates a new Arc.
	 *
	 * @param node the node to point at
	 */
	protected RefinementArc( Goal node ) {
		nextNode = node;
	}
	
	/**
	 * Gets the next node.
	 *
	 * @return the next node
	 */
	public Goal getNextNode() {
		return nextNode;
	}
}
