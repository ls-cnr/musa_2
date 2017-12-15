package datalayer.awareness.LTL.net.condition;

import java.util.ArrayList;

public class CombinationCondition extends TransitionCondition {

	ArrayList<TransitionCondition> ops;
	
	public CombinationCondition(TransitionCondition firstOp, TransitionCondition secondOp) {
		super(firstOp.getTerm()+"-"+secondOp.getTerm());
		ops = new ArrayList<>();
		ops.add(firstOp);
		ops.add(secondOp);
	}

	@Override
	public void setStateCondition(String s) {
		//Empty
	}
	
	public ArrayList<TransitionCondition> getCond() {
		return ops;
	}

}
