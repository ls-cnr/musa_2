package org.icar.musa.solution_extractor.distributed;

import java.util.ArrayList;
import java.util.Iterator;

public class InternalStringSolutionSet implements Iterable<InternalStringSolution> {
	private ArrayList<InternalStringSolution> solutions;
	private Sequences seq;

	public InternalStringSolutionSet(Sequences seq) {
		this.solutions = new ArrayList<>();
		this.seq = seq;
	}

	@Override
	public Iterator<InternalStringSolution> iterator() {
		return this.solutions.iterator();
	}

	public void addSolution(InternalStringSolution s) {
		this.solutions.add(s);

	}

	public ArrayList<InternalStringSolution> getSolutions() {
		return this.solutions;
	}

	public int getSize() {
		return this.solutions.size();
	}

	public boolean isEmpty() {
		return this.solutions.size() == 0;
	}

	public void remove(InternalStringSolution s) {
		this.solutions.remove(s);
	}

	public Sequences getSeq() {
		return seq;
	}

	public void setSeq(Sequences seq) {
		this.seq = seq;
	}

	public boolean containsSolution(InternalStringSolution s) {
		return this.solutions.contains(s);
	}

}
