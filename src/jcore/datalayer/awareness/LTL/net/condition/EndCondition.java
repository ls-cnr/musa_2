package datalayer.awareness.LTL.net.condition;

import datalayer.awareness.LTL.net.PetriNetState;

public class EndCondition extends TransitionCondition {

	private String father;
	
	public EndCondition(String father) {
		super("end");
		this.father = father;
	}
	
	public String getFather() {
		return father;
	}

	@Override
	public void setStateCondition(PetriNetState s) {
	}

}
