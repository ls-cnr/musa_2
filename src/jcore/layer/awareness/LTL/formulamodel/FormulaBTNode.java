package layer.awareness.LTL.formulamodel;

public class FormulaBTNode {
	
	private String val;
	private FormulaBTNode left, right;
	
	public FormulaBTNode(String val) {
		this.val = val;
	}
	
	public void addLeft(FormulaBTNode child) {
        left = child;
    }
	
	public void addRight(FormulaBTNode child) {
        right = child;
	}
	
	public String getVal() {
		return val;
	}
	
	public void setVal(String val) {
		this.val = val;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public FormulaBTNode getLeft() {
		return left;
	}
	
	public boolean hasRight() {
		return right != null;
	}
	
	public FormulaBTNode getRight() {
		return right;
	}
}
