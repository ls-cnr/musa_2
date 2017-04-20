package configuration;

import java.util.ArrayList;
import java.util.Iterator;

public class SolutionSet implements Iterable<Solution> {
	private ArrayList<Solution> solutions;

	public SolutionSet() {
		this.solutions = new ArrayList<>();
	}

	@Override
	public Iterator<Solution> iterator() {
		return this.solutions.iterator();
	}

	public void addSolution(Solution s) {
		this.solutions.add(s);

	}

	public ArrayList<Solution> getSolutions() {
		return solutions;
	}

	public int getSize() {
		return this.solutions.size();
	}

	public void add(Solution s) {
		this.solutions.add(s);
	}

	public boolean isEmpty() {
		return this.solutions.size() == 0;
	}

	public void remove(Solution s) {
		this.solutions.remove(s);
	}

}
