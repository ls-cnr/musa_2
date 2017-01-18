package layer.awareness.goaltree;

import layer.awareness.Goal;

/**
 * The Class OrArc, used to represent the And Arc in the model.
 */
public class OrArc extends RefinementArc {
	
	/**
	 * Instantiates a new OrArc.
	 *
	 * @param node the node to point at
	 */
	public OrArc( Goal node ) {
		super(node);
	}
	
}
