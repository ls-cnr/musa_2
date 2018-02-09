package org.icar.musa.solution;

import java.util.ArrayList;

import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.XorNode;

public class AWBuilder {
	private ArrayList<AWSequence> sequences;
	
	public AWBuilder() {
		super();
		sequences = new ArrayList<>();
	}

	public void addFirstNode(StateNode node) {
		sequences.clear();
		
		AWEvent event = new AWEvent(node, AWEvent.START);
		sequences.add(new AWSequence(event));
	}
	
	public void addEvolutionEdge(StateNode source,StateNode dest, CapabilityEdge edge) {
		for (AWSequence s : sequences) {
			
		}
	}
	
	public void addChoiceEdge(StateNode source,XorNode dest, CapabilityEdge edge) {
		
	}
	
	public void addScenarioEdge(XorNode source,StateNode dest, ScenarioEdge edge) {
		
	}

	
}
