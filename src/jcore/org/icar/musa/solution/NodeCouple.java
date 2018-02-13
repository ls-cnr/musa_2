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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dst == null) ? 0 : dst.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeCouple other = (NodeCouple) obj;
		if (dst == null) {
			if (other.dst != null)
				return false;
		} else if (!dst.equals(other.dst))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		return true;
	}
	
	
	
	
}
