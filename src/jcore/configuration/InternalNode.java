package configuration;

import java.util.ArrayList;

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
	private ArrayList<TreeNode> children;

	public void addChild(TreeNode n) {
		this.children.add(n);
	}

	public void removeChild(TreeNode n) {
		this.children.remove(n);
	}

	public ArrayList<TreeNode> getChildren() {
		return this.children;
	}

	public boolean isXor() {
		return this.isXor;
	}

	public boolean isNormal() {
		return this.isNormal;
	}

}