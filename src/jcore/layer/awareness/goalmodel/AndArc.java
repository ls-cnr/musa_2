package layer.awareness.goalmodel;

import layer.awareness.Goal;

/**
 * The Class AndArc, used to represent the And Arc in the model.
 */
public class AndArc extends RefinementArc {
	
	/**
	 * Instantiates a new AndArc.
	 *
	 * @param node the node to point at
	 */
	public AndArc( Goal node ) {
		super(node);
	}
	
}
