package org.icar.musa.solution;

import org.icar.musa.pmr.problem_exploration.WTSNode;

public class NodeCouple {
	private WTSNode src;
	private WTSNode dst;
	public NodeCouple(WTSNode src, WTSNode dst) {
		super();
		this.src = src;
		this.dst = dst;
	}
	public WTSNode getSrc() {
		return src;
	}
	public WTSNode getDst() {
		return dst;
	}
	
	
}
