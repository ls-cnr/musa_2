package org.icar.specification.linear_temporal_logic.formulamodel;

/**
 * The Class FormulaBTNode, used to represent a node in a Formula Binary Tree.
 */
public class FormulaBTNode {
	
	/** The val. */
	private String val;
	
	/** The right. */
	private FormulaBTNode left, right;
	
	/**
	 * Instantiates a new formula BT node.
	 *
	 * @param val
	 *            the val
	 */
	public FormulaBTNode( String val ) {
		this.val = val;
	}
	
	/**
	 * Instantiates a new formula BT node.
	 *
	 * @param copy
	 *            the copy
	 */
	public FormulaBTNode( FormulaBTNode copy ){
		this(copy.getVal());
		if( copy.hasLeft() )
			addLeft(new FormulaBTNode(copy.getLeft()));
		if( copy.hasRight() )
			addRight(new FormulaBTNode(copy.getRight()));
	}
	
	/**
	 * Adds the left.
	 *
	 * @param child
	 *            the child
	 */
	public void addLeft( FormulaBTNode child ) {
        left = child;
    }
	
	/**
	 * Adds the right.
	 *
	 * @param child
	 *            the child
	 */
	public void addRight( FormulaBTNode child ) {
        right = child;
	}
	
	/**
	 * Gets the val.
	 *
	 * @return the val
	 */
	public String getVal() {
		return val;
	}
	
	/**
	 * Sets the val.
	 *
	 * @param val
	 *            the new val
	 */
	public void setVal(String val) {
		this.val = val;
	}
	
	/**
	 * Checks for left.
	 *
	 * @return true, if successful
	 */
	public boolean hasLeft() {
		return left != null;
	}
	
	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public FormulaBTNode getLeft() {
		return left;
	}
	
	/**
	 * Checks for right.
	 *
	 * @return true, if successful
	 */
	public boolean hasRight() {
		return right != null;
	}
	
	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public FormulaBTNode getRight() {
		return right;
	}
}
