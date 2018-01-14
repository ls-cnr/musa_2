
package datalayer.world.configuration.distributed;

/**
 * This class describes one solution.
 * 
 * @author Mirko Avantaggiato
 *
 */
public class Solution {
	private static int counter = 0;
	private final int ID;
	private Tree<String> root;

	/* Which solutionSet am I in? */
	private SolutionSet solutionSet;

	public Solution(Tree<String> s, SolutionSet solutionSet) {
		this.ID = counter++;
		this.root = s;
		this.solutionSet = solutionSet;
	}

	public Tree<String> getRoot() {
		return this.root;
	}

	public int getID() {
		return this.ID;
	}

	public static int getCounter() {
		return counter;
	}

	public void setRoot(Tree<String> root) {
		this.root = root;
	}

	public boolean containsLoop() {
		return Solution.containsLoop(this.root);
	}

	private static boolean containsLoop(Tree<String> currentNode) {
		if (currentNode.isLeaf()) {
			if (currentNode.getNodeType() == Tree.LOOP_CODE)
				return true;
			else
				return false;
		} else
			for (Tree<String> child : currentNode.getChildren()) {
				boolean flag = Solution.containsLoop(child);
				if (flag == true)
					return true;
			}
		return false;
	}

	public boolean checkLoop() {
		return this.checkLoop(this.root, this.solutionSet);
	}

	private boolean checkLoop(Tree<String> currentNode, SolutionSet st) {
		if (currentNode.isLeaf()) {
			if (currentNode.getNodeType() == Tree.LOOP_CODE)
				if (this.solutionSet.getSeq().getTreeSafeNodes().contains(currentNode.getValue().split("\\*")[0]))
					return true;
				else
					return false;
			else
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

	public boolean isSimplePath() {
		return isSimplePath(this.getRoot());
	}

	private static boolean isSimplePath(Tree<String> node) {
		if (node.getChildren() != null)
			if (node.getChildren().size() == 1)
				return isSimplePath(node.getChildren().get(0));
			else if (node.getChildren().size() == 0)
				return true;
			else
				return false;
		return true;
	}

	public static long cumulativeHashCode(Tree<String> currentNode) {
		if (currentNode.isLeaf())
			return currentNode.getValue().hashCode();
		else {
			long sum = 7;
			for (Tree<String> child : currentNode.getChildren())
				sum *= Solution.cumulativeHashCode(child) % Long.MAX_VALUE;
			return sum;
		}
	}

	@Override
	public boolean equals(Object obj) {
		return Solution.cumulativeHashCode(this.getRoot()) == Solution.cumulativeHashCode(((Solution) obj).getRoot());
	}
}
