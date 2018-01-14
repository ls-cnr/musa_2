package datalayer.awareness.LTL.net.condition;

import java.util.ArrayList;

import datalayer.awareness.LTL.net.PetriNetState;

public class CombinationCondition extends TransitionCondition {

	ArrayList<TransitionCondition> ops;
	
	public CombinationCondition(TransitionCondition firstOp, TransitionCondition secondOp) {
		super(firstOp.getTerm()+"-"+secondOp.getTerm());
		ops = new ArrayList<>();
		ops.add(firstOp);
		ops.add(secondOp);
	}

	@Override
	public void setStateCondition(PetriNetState s) {
		//Empty
	}
	
	public ArrayList<TransitionCondition> getCond() {
		return ops;
	}

}
