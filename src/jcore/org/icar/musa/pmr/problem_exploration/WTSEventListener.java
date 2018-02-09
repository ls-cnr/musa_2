package org.icar.musa.pmr.problem_exploration;

public interface WTSEventListener {
	
	public void notifyFirstNode(StateNode node);
	public void notifyEvolutionEdge(StateNode source,StateNode dest, CapabilityEdge edge);
	public void notifyChoiceEdge(StateNode source,XorNode dest, CapabilityEdge edge);
	public void notifyScenarioEdge(XorNode source,StateNode dest, ScenarioEdge edge);
	
}
