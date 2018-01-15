package org.icar.specification.goalspec.goalmodel;

import org.icar.specification.goalspec.GS_Goal;

/**
 * The Class OrArc, used to represent the And Arc in the model.
 * @author Mirko Zichichi
 */
public class OrArc extends RefinementArc {
	
	/**
	 * Instantiates a new OrArc.
	 *
	 * @param node the node to point at
	 */
	public OrArc( GS_Goal node ) {
		super(node);
	}
	
}
