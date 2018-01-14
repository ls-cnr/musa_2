package datalayer.world.configuration.distributed;

import java.util.ArrayList;
import java.util.Iterator;

public class SolutionSet implements Iterable<Solution> {
	private ArrayList<Solution> solutions;
	private Sequences seq;

	public SolutionSet(Sequences seq) {
		this.solutions = new ArrayList<>();
		this.seq = seq;
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

	public Sequences getSeq() {
		return seq;
	}

	public void setSeq(Sequences seq) {
		this.seq = seq;
	}

	public boolean containsSolution(Solution s) {
		return this.solutions.contains(s);
	}

}
