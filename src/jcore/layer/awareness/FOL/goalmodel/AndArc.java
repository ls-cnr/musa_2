package layer.awareness.FOL.goalmodel;

import layer.awareness.Goal;

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
	public AndArc( Goal node ) {
		super(node);
	}
	
}
