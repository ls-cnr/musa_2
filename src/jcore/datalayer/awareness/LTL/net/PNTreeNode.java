package datalayer.awareness.LTL.net;

public interface PNTreeNode {
	public PNTreeNode leftChildren();
	public PNTreeNode rightChildren();
	public boolean isLeaf();
}
