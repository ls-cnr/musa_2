package org.icar.ltlpetrinet.hierarchical_model.template;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;

public class Pair {
	private PNStateEnum first;
	private PNStateEnum second;
	
	public Pair(PNStateEnum first, PNStateEnum second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	public PNStateEnum getFirst() {
		return first;
	}
	public void setFirst(PNStateEnum first) {
		this.first = first;
	}
	public PNStateEnum getSecond() {
		return second;
	}
	public void setSecond(PNStateEnum second) {
		this.second = second;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
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
		Pair other = (Pair) obj;
		if (first != other.first)
			return false;
		if (second != other.second)
			return false;
		return true;
	}
	
	
}
