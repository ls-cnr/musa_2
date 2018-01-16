package org.icar.ltlpetrinet.hierarchical_model.template;

import java.util.HashMap;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.LogicNode;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;
import org.icar.ltlpetrinet.supervisor.Token;

public class OrOperator extends LogicNode {
	private HashMap<Pair,PNStateEnum> state_truth;

	public OrOperator(String name, HierarchyNode left, HierarchyNode right) {
		super(name,left,right);
		
		init_state_truth();
	}

	private void init_state_truth() {
		state_truth = new HashMap<Pair,PNStateEnum>();
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.ERROR), PNStateEnum.ACCEPTED);
		
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.WAIT_BUT_ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.WAIT_BUT_ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.ERROR), PNStateEnum.WAIT_BUT_ACCEPTED);

		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.WAIT_BUT_ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.ERROR), PNStateEnum.WAIT_BUT_ERROR);

		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.WAIT_BUT_ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.ERROR), PNStateEnum.ERROR);
}

	@Override
	public Set<Token> getInitialTokenSet() {
		Set<Token> left_tokens = getLeft().getInitialTokenSet();
		Set<Token> right_tokens = getRight().getInitialTokenSet();
		left_tokens.addAll(right_tokens);
		return left_tokens;
	}

	@Override
	protected boolean fireable_truth_table(boolean op1, boolean op2) {
		return op1 | op2;
	}

	@Override
	protected PNStateEnum state_truth_table(PNStateEnum op1, PNStateEnum op2) {
		return state_truth.get(new Pair(op1, op2));
	}

	@Override
	public int calculate_partial_satisfaction() {
		int leftscore = getLeft().calculate_partial_satisfaction();
		int rightscore = getRight().calculate_partial_satisfaction();
		return Math.max(leftscore, rightscore);
	}

}
