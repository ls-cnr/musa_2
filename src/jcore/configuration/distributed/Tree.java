package configuration.distributed;

import java.util.ArrayList;

public class Tree<T> {
	static final int NORMAL_CODE = 0;
	static final int XOR_CODE = 1;
	static final int SUCCESS_CODE = 2;
	static final int FAILURE_CODE = 3;
	static final int LOOP_CODE = 4;
	static final int EXPLICIT_XOR_CODE = 5;

	private static int counter = 0;
	private final int ID;
	private T value;
	private int nodeType;
	private ArrayList<Tree<T>> children;

	public Tree(T value) {
		this.value = value;
		this.children = new ArrayList<>();
		this.setNodeType(NORMAL_CODE);
		this.ID = counter++;
	}

	public void addChild(Tree<T> child) {
		if (!this.children.contains(child))
			this.children.add(child);
	}

	public void removeChild(Tree<T> child) {
		if (this.children.contains(child))
			this.children.remove(child);
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public ArrayList<Tree<T>> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Tree<T>> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object that) {
		if (that != null) {
			@SuppressWarnings("unchecked")
			Tree<String> tmp = (Tree<String>) that;
			return this.getValue().toString().equals(tmp.getValue().toString());
		}
		return false;
	}

	public Tree<T> getChild(Tree<T> child) {
		for (int i = 0; i < this.getChildren().size(); i++)
			if (this.getChildren().get(i).equals(child))
				return this.getChildren().get(i);
		return null;
	}

	public int getID() {
		return this.ID;
	}

	public String getID_asString() {
		return (new Integer(this.ID)).toString();
	}

	public boolean isLeaf() {
		if (this.getChildren().size() == 0)
			return true;
		return false;
	}

	public boolean isInternal() {
		return !this.isLeaf();
	}

	public static int getCounter() {
		return counter;
	}

	public static Tree<String> getSameTree_newID(Tree<String> t) {
		Tree<String> n = new Tree<String>(t.getValue());
		n.setNodeType(t.getNodeType());
		if (t.isLeaf())
			return n;
		else {
			for (Tree<String> child : t.getChildren()) {
				n.addChild(getSameTree_newID(child));
			}
			return n;
		}
	}

	/**
	 * A more user-friendly translation.
	 * 
	 * @param type
	 *            int type
	 * @return string for that type
	 */
	public static String typeToString(int type) {

		if (type == Tree.NORMAL_CODE)
			return "normal";
		else if (type == Tree.SUCCESS_CODE)
			return "success";
		else if (type == Tree.XOR_CODE)
			return "xor";
		else if (type == Tree.FAILURE_CODE)
			return "failure";
		else if (type == Tree.LOOP_CODE)
			return "loop";
		else if (type == Tree.EXPLICIT_XOR_CODE)
			return "explicit_xor";
		else
			return "";
	}

}
