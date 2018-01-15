package org.icar.ltlpetrinet.hierarchical_model;

import org.icar.ltlpetrinet.annotated_pn.AnnotatedPlace;
import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;

import petrinet.logic.Place;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public abstract class LogicNode extends HierarchyNode {
	private HierarchyNode left;
	private HierarchyNode right;

	public LogicNode(String name,HierarchyNode left, HierarchyNode right) {
		super(name);
		this.left = left;
		this.right = right;
		add_dependency("left", left);
		add_dependency("right", right);
	}

	public void init() {
		left.init();
		right.init();
	}

	public HierarchyNode getLeft() {
		return left;
	}

	public HierarchyNode getRight() {
		return right;
	}

	@Override
	public void updateNet(StateOfWorld w,AssumptionSet assumptions) {
		left.updateNet(w,assumptions);
		right.updateNet(w,assumptions);
	}
	
	protected boolean retrieveTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean normal) {
		boolean normal_test = false;
		
		boolean left_dep = left.retrieveTransitionDependency(w,assumptions,normal);
		boolean right_dep = right.retrieveTransitionDependency(w,assumptions,normal);
		normal_test = fireable_truth_table(left_dep,right_dep);
		
		if (normal)
			return normal_test;
		else
			return !normal_test;
	}

	protected abstract boolean fireable_truth_table(boolean op1, boolean op2);

	@Override
	public PNStateEnum getNetState() {
		PNStateEnum left_state = left.getNetState();
		PNStateEnum right_state = right.getNetState();
		
		return state_truth_table(left_state,right_state);
	}

	protected abstract PNStateEnum state_truth_table(PNStateEnum op1, PNStateEnum op2);

}
