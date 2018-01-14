package org.icar.specification.goalspec.goalmodel;

import org.icar.specification.goalspec.GS_Goal;

/**
 * The Class RefinementArc, used as generalization for And and Or arcs.
 * @author Mirko Zichichi
 */
public abstract class RefinementArc {
	
	/** The node where the arc points */
	public GS_Goal nextNode;
	
	/**
	 * Instantiates a new Arc.
	 *
	 * @param node the node to point at
	 */
	protected RefinementArc( GS_Goal node ) {
		nextNode = node;
	}
	
	/**
	 * Gets the next node.
	 *
	 * @return the next node
	 */
	public GS_Goal getNextNode() {
		return nextNode;
	}
}
