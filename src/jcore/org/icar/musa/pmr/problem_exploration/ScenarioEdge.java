package org.icar.musa.pmr.problem_exploration;

public class ScenarioEdge extends WTSEdge {
	private String scenario;
	
	private String capability_name;
	private String agent_provider;
	
	public ScenarioEdge(String scenario) {
		super(0);
		
		this.scenario = scenario;
	}

	public String getCapability_name() {
		return capability_name;
	}

	public void setCapability_name(String capability_name) {
		this.capability_name = capability_name;
	}

	public String getAgent_provider() {
		return agent_provider;
	}

	public void setAgent_provider(String agent_provider) {
		this.agent_provider = agent_provider;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	
	
}
