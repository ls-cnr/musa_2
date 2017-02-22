package pmr.probexp;

import java.util.ArrayList;

import layer.awareness.AbstractCapability;

// TODO: Auto-generated Javadoc
/**
 * The Class NormalExpansionNode.
 */
public class NormalExpansionNode extends ExpansionNode{
		
	private String Scenario;
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
	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability, String scenario){
		super(source, destination, capability);
		this.Scenario = scenario;
	}
	
	public NormalExpansionNode(ENode source, ArrayList<ENode> destination, String capability){
		super(source, destination, capability);
	}
	
	public void setScenario(String scenario){
		this.Scenario = scenario;
	}
	
	public String getScenario(){
		return this.Scenario;
	}
	
}
