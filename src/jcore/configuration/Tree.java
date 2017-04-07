package configuration;

import java.util.ArrayList;

public class Tree<T> {
	private static final int NORMAL_CODE = 0;
	private static final int XOR_CODE = 1;
	private static final int SUCCESS_CODE = 2;
	private static final int FAILURE_CODE = 3;
	private static final int LOOP_CODE = 4;
	private static final int EXPLICIT_XOR_CODE = 5;

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
		return this.value.toString() + "(" + this.nodeType + ")";
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

	public boolean isLeaf() {
		if (this.getNodeType() == SUCCESS_CODE || this.getNodeType() == LOOP_CODE || this.getNodeType() == FAILURE_CODE)
			return true;
		return false;
	}

	public boolean isInternal() {
		return !this.isLeaf();
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Tree.counter = counter;
	}

	public static int getNormalCode() {
		return NORMAL_CODE;
	}

	public static int getXorCode() {
		return XOR_CODE;
	}

	public static int getSuccessCode() {
		return SUCCESS_CODE;
	}

	public static int getFailureCode() {
		return FAILURE_CODE;
	}

	public static int getLoopCode() {
		return LOOP_CODE;
	}

	public static int getExplicitXorCode() {
		return EXPLICIT_XOR_CODE;
	}

}