package org.icar.ltlpetrinet.hierarchical_model.template;

import java.util.HashMap;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.LogicNode;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;
import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public class AndOperator extends LogicNode {
	private HashMap<Pair,PNStateEnum> state_truth;

	public AndOperator(String name, HierarchyNode left, HierarchyNode right) {
		super(name,left,right);
		
		init_state_truth();
	}

	private void init_state_truth() {
		state_truth = new HashMap<Pair,PNStateEnum>();
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.ERROR);
		state_truth.put(new Pair(PNStateEnum.ACCEPTED,PNStateEnum.ERROR), PNStateEnum.ERROR);
		
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.ACCEPTED), PNStateEnum.ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.WAIT_BUT_ACCEPTED);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ACCEPTED,PNStateEnum.ERROR), PNStateEnum.ERROR);

		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.ACCEPTED), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.WAIT_BUT_ERROR);
		state_truth.put(new Pair(PNStateEnum.WAIT_BUT_ERROR,PNStateEnum.ERROR), PNStateEnum.ERROR);

		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.ACCEPTED), PNStateEnum.ERROR);
		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.WAIT_BUT_ACCEPTED), PNStateEnum.ERROR);
		state_truth.put(new Pair(PNStateEnum.ERROR,PNStateEnum.WAIT_BUT_ERROR), PNStateEnum.ERROR);
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
		return op1 & op2;
	}

	@Override
	protected PNStateEnum state_truth_table(PNStateEnum op1, PNStateEnum op2) {
		return state_truth.get(new Pair(op1, op2));
	}

	@Override
	public void updateResistance(StateOfWorld w, AssumptionSet assumptions) {
		getLeft().updateResistance(w, assumptions);
		getRight().updateResistance(w, assumptions);
		
		setResistance(getLeft().getResistance()+getRight().getResistance());
	}

	
//	@Override
//	public double calculate_partial_satisfaction() {
//		double leftscore = getLeft().calculate_partial_satisfaction();
//		double rightscore = getRight().calculate_partial_satisfaction();
//		return  (leftscore+rightscore)/2;
//		//return Math.min(leftscore, rightscore);
//	}

	public String toString() {
		return "[ AND " + getLeft().toString() + "," + getRight().toString() + " ] ";
	}
	
	public String toStringWithScore() {
		return "[ AND (r="+getResistance()+") " + getLeft().toStringWithScore() + "," + getRight().toStringWithScore() + " ] ";
	}


//	public String toStringWithNet() {
//		return "[ AND("+getName()+") " + getLeft().toStringWithNet() + "," + getRight().toStringWithNet() + " ] ";
//	}
//
//	@Override
//	public int count_slots() {
//		int left_slots=0;
//		int right_slots=0;
//		
//		if (getLeft() instanceof LogicNode) {
//			LogicNode left_node = (LogicNode) getLeft();
//			left_slots = left_node.count_slots();
//		} else {
//			return -1;
//		}
//		 
//		if (getRight() instanceof LogicNode) {
//			LogicNode right_node = (LogicNode) getRight();
//			right_slots = right_node.count_slots();
//		} else {
//			return -1;
//		}
//		
//		return left_slots+right_slots;
//	}


//	public String toStringWithScore(StateOfWorld w, AssumptionSet assumptions) {
//		return "[ AND("+calculate_partial_satisfaction()+") " + getLeft().toStringWithScore(w,assumptions) + "," + getRight().toStringWithScore(w,assumptions) + " ] ";
//	}
//
//	@Override
//	public double calculate_partial_satisfaction_degree(boolean contribute_positively, StateOfWorld w, AssumptionSet assumptions) {
//		double left_degree = getLeft().calculate_partial_satisfaction_degree(true, w, assumptions);
//		double right_degree = getRight().calculate_partial_satisfaction_degree(true, w, assumptions);
//		if (contribute_positively) {
//			if (left_degree+right_degree==2)
//				return 1;
//			else if (left_degree+right_degree==0)
//				return 0;
//			else 
//				return 0.5;
//		}
//		
//		if (!contribute_positively) {
//			if (left_degree+right_degree==2)
//				return 0;
//			else if (left_degree+right_degree==0)
//				return 1;
//			else 
//				return 0.5;
//		}
//		
//		return 0;
//	}

	
}
