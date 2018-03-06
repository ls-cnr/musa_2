package org.icar.musa.pmr.solution;

import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;

public class AWLoop extends WTSNode {
	private StateNode loop;
	
	public AWLoop(StateNode loop) {
		super(loop.hashCode());
		this.loop = loop;
	}

	public StateNode getLoop() {
		return loop;
	}
	
	

}
