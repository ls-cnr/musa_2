package configuration;

public class Solution {
	private Tree<String> root;
	private final int ID;
	private static int counter = 0;

	/* A quale SolutionTree si riferisce questa soluzione riferisco? */
	private SolutionTree solutionTree;

	public Solution(Tree<String> s, SolutionTree solutionTree) {
		this.root = s;
		this.ID = counter++;
		this.solutionTree = solutionTree;
	}

	public Tree<String> getRoot() {
		return root;
	}

	public int getID() {
		return ID;
	}

	public static int getCounter() {
		return counter;
	}

	public void setRoot(Tree<String> root) {
		this.root = root;
	}

	public void add(Tree<String> t) {
		this.root.addChild(t);
	}

	public boolean containsLoop() {
		return Solution.containsLoop(this.root);
	}

	private static boolean containsLoop(Tree<String> currentNode) {
		if (currentNode.isLeaf()) {
			if (currentNode.getNodeType() == Tree.LOOP_CODE) {
				return true;
			} else
				return false;
		} else {
			for (Tree<String> child : currentNode.getChildren()) {
				boolean flag = Solution.containsLoop(child);
				if (flag == true)
					return flag;
			}

		}
		return false;
	}

	public boolean checkLoop() {
		return this.checkLoop(this.root, this.solutionTree);
	}

	private boolean checkLoop(Tree<String> currentNode, SolutionTree st) {
		if (currentNode.isLeaf()) {
			if (currentNode.getNodeType() == Tree.LOOP_CODE) {
				if (this.solutionTree.getTreeSafeNode().contains(currentNode.getValue()))
					return true;
				else
					return false;
			} else
				return true;
		}

		else {
			for (Tree<String> child : currentNode.getChildren()) {
				if (this.checkLoop(child, st) == false)
					return false;
			}
			return true;
		}
	}

	public int howManyLoops() {
		return Solution.howManyLoops(this.root);
	}

	private static int howManyLoops(Tree<String> currentNode) {
		if (currentNode.isLeaf())
			if (currentNode.getNodeType() == Tree.LOOP_CODE)
				return 1;
			else
				return 0;
		else {
			int sum = 0;
			for (Tree<String> child : currentNode.getChildren())
				sum += Solution.howManyLoops(child);
			return sum;
		}
	}

}
