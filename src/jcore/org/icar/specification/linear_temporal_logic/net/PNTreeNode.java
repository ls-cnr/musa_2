package org.icar.specification.linear_temporal_logic.net;

public interface PNTreeNode {
	public PNTreeNode leftChildren();
	public PNTreeNode rightChildren();
	public boolean isLeaf();
}
