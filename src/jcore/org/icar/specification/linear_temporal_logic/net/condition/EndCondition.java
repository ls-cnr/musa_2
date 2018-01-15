package org.icar.specification.linear_temporal_logic.net.condition;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;

@Deprecated
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
	public void setStateCondition(PNStateEnum s) {
	}

}
