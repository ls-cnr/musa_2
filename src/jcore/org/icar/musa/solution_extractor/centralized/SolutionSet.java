package datalayer.world.configuration.centralized;

import java.util.ArrayList;
import java.util.Iterator;

public class SolutionSet implements Iterable<Solution> {
	private ArrayList<Solution> solutions;

	/* Which SolutionTree do I belong to? */
	private SolutionTree solutionTree;

	public SolutionSet(SolutionTree st) {
		this.solutions = new ArrayList<>();
		this.setSolutionTree(st);
	}

	@Override
	public Iterator<Solution> iterator() {
		return this.solutions.iterator();
	}

	public void addSolution(Solution s) {
		this.solutions.add(s);

	}

	public ArrayList<Solution> getSolutions() {
		return this.solutions;
	}

	public int getSize() {
		return this.solutions.size();
	}

	public boolean isEmpty() {
		return this.solutions.size() == 0;
	}

	public void remove(Solution s) {
		this.solutions.remove(s);
	}

	public SolutionTree getSolutionTree() {
		return solutionTree;
	}

	public void setSolutionTree(SolutionTree solutionTree) {
		this.solutionTree = solutionTree;
	}

}
