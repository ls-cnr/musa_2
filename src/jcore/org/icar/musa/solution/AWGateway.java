package org.icar.musa.solution;

import org.icar.musa.pmr.problem_exploration.WTSExpansion;

public class AWGateway extends AWItem {
	private WTSExpansion exp_ref;

	public AWGateway(String name, WTSExpansion exp_ref) {
		super(name);
		this.exp_ref = exp_ref;
	}
	
	

}
