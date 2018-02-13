package org.icar.musa.solution;

import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;

public class WTSLoop extends WTSNode {
	private StateNode loop;
	
	public WTSLoop(StateNode loop) {
		super(loop.hashCode());
		this.loop = loop;
	}

	public StateNode getLoop() {
		return loop;
	}
	
	

}
