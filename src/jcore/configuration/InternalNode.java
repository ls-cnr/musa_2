package configuration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class describes an internal node in the solution tree. This node can be
 * either a xor node, or a normal node.
 * 
 * @author Mirko Avantaggiato
 *
 */
public class InternalNode extends TreeNode {
	private boolean isXor;
	private boolean isNormal;
	private HashMap<String, TreeNode> children;

	public InternalNode(boolean isXor, boolean isNormal) {
		this.isXor = isXor;
		this.isNormal = isNormal;
		this.children = new HashMap<>();
	}

	public void addChild(TreeNode n) {
		this.children.put(n.getFacts(), n);
	}

	public void removeChild(String n) {
		this.children.remove(n);
	}

	public HashMap<String, TreeNode> getChildren() {
		return this.children;
	}

	public boolean isXor() {
		return this.isXor;
	}

	public boolean isNormal() {
		return this.isNormal;
	}

}