package org.icar.musa.solution;

import org.icar.musa.pmr.problem_exploration.StateNode;

public class AWEvent extends AWItem {
	public static final int START = 0;
	public static final int END = 1;
	
	private StateNode node_ref;
	private int type;

	public AWEvent(StateNode node_ref, int type) {
		super(type_to_string(type));
		this.node_ref = node_ref;
	}

	private static String type_to_string(int type) {
		if (type==START)
			return "start";
		
		return "end";
	}
	
	
	
}
