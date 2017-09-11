package pmr.probexp;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class NormalExpansionNode.
 */
public class NormalExpansionNode extends ExpansionNode{

	/**
	 * Instantiates a new normal expansion node.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @param capability
	 *            the capability
	 */

	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability){
		super(source, destination, capability);
	}

	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score){
		super(source, destination, capability, score);
	}

	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability, String agent){
		super(source, destination, capability, agent);
	}

	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability, int score, String agent){
		super(source, destination, capability, score, agent);
	}

}
