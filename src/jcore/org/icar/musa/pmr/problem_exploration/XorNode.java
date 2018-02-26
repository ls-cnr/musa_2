package org.icar.musa.pmr.problem_exploration;

public class XorNode extends WTSNode {
	private int cases;

	public XorNode() {
		super(0);
		cases = 0;
	}
	
	public void setCases(int cases) {
		this.cases = cases;
	}
	
	public int getCases() {
		return cases;
	}

	@Override
	public String toString() {
		return "X";
	}

	
}
