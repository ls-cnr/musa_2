package org.icar.musa.pmr.solution;

import java.util.Comparator;

public class PartialSolution {
	private double score;
	private TreeBrick partial_solution;
	
	public PartialSolution(double score, TreeBrick partial_solution) {
		super();
		this.score = score;
		this.partial_solution = partial_solution;
	}

	public double getScore() {
		return score;
	}

	public TreeBrick getPartial_solution() {
		return partial_solution;
	}
	
	/* class comparator */
	public static Comparator<? super PartialSolution> getScoreComparator() {
		Comparator<PartialSolution> comp = new Comparator<PartialSolution>(){
			@Override
			public int compare(PartialSolution e1, PartialSolution e2){
				double op = e1.score - e2.score;
				if (op>0) return 1;
				if (op<0) return -1;
				return 0;		
			}
		};
		return comp;
	}

	
	
}
