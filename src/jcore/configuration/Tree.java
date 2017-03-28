package configuration;

import java.util.ArrayList;

public class Tree<T> {
	private static int counter = 0;
	private final int ID;
	private T value;
	private int nodeType;
	private ArrayList<Tree<T>> children;

	public Tree(T value) {
		this.value = value;
		this.children = new ArrayList<>();
		/*
		 * 0: normal node. 1: xor node. 2: success node. 3: failure node. 4:
		 * loop node. 99: explict xor node
		 */
		this.setNodeType(0);
		this.ID = this.counter++;
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

	public void setNodeType(String nodeType) {
		switch (nodeType) {
		case "normal":
			this.nodeType = 0;
			break;
		case "xor":
			this.nodeType = 1;
			break;
		case "success":
			this.nodeType = 2;
			break;
		case "failure":
			this.nodeType = 3;
			break;
		case "loop":
			this.nodeType = 4;
			break;
		case "explicit_xor":
			this.nodeType = 99;
			break;

		}
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

}