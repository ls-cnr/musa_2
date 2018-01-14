package org.icar.musa.proactive_means_end_reasoning;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class NormalExpansionNode.
 */
public class NormalExpansion extends GraphExpansion{
	
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
	
	public NormalExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability){
		super(source, destination, capability);
	}
	
	public NormalExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score){
		super(source, destination, capability, score);
	}
	
	public NormalExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, String agent){
		super(source, destination, capability, agent);
	}
	
	public NormalExpansion(ExtendedNode source, ArrayList<ExtendedNode> destination, String capability, int score, String agent){
		super(source, destination, capability, score, agent);
	}
	
}
