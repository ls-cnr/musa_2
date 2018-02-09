package org.icar.musa.pmr.problem_exploration;

public class CapabilityEdge extends WTSEdge {

	private String capability_name;
	private String agent_provider;
	
	private int cap_precedence;
	
	public CapabilityEdge() {
		super(0);
	}

	public String getCapabilityName() {
		return capability_name;
	}

	public void setCapabilityName(String capability_name) {
		this.capability_name = capability_name;
	}

	public String getAgentProvider() {
		return agent_provider;
	}

	public void setAgentProvider(String agent_provider) {
		this.agent_provider = agent_provider;
	}

	public int getCap_precedence() {
		return cap_precedence;
	}

	public void setCap_precedence(int cap_precedence) {
		this.cap_precedence = cap_precedence;
	}
	
	
	
}
