package layer.awareness.LTL.net;

import java.util.ArrayList;

import layer.awareness.LTL.net.TransitionCondition;

public class CombinationCondition extends TransitionCondition {

	ArrayList<TransitionCondition> ops;
	
	public CombinationCondition(TransitionCondition firstOp, TransitionCondition secondOp) {
		super(firstOp.getTerm()+"-"+secondOp.getTerm());
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
