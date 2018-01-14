package org.icar.specification.linear_temporal_logic.net.condition;

import java.util.ArrayList;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;

public class CombinationCondition extends TransitionCondition {

	ArrayList<TransitionCondition> ops;
	
	public CombinationCondition(TransitionCondition firstOp, TransitionCondition secondOp) {
		super(firstOp.getTerm()+"-"+secondOp.getTerm());
		ops = new ArrayList<>();
		ops.add(firstOp);
		ops.add(secondOp);
	}

	@Override
	public void setStateCondition(PNStateEnum s) {
		//Empty
	}
	
	public ArrayList<TransitionCondition> getCond() {
		return ops;
	}

}
