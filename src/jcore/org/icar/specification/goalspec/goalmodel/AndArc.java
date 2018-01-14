package org.icar.specification.goalspec.goalmodel;

import org.icar.specification.goalspec.GS_Goal;

/**
 * The Class AndArc, used to represent the And Arc in the model.
 * @author Mirko Zichichi
 */
public class AndArc extends RefinementArc {
	
	/**
	 * Instantiates a new AndArc.
	 *
	 * @param node the node to point at
	 */
	public AndArc( GS_Goal node ) {
		super(node);
	}
	
}
