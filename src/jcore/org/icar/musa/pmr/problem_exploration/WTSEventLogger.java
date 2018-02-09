package org.icar.musa.pmr.problem_exploration;

import java.util.HashMap;

public class WTSEventLogger implements WTSEventListener {
	private int xor_counter = 0;
	private HashMap<XorNode,String> map;
	
	public WTSEventLogger() {
		super();
		map = new HashMap<>();
	}

	@Override
	public void notifyFirstNode(StateNode node) {
		System.out.println("W0: "+node.getState());
	}

	@Override
	public void notifyEvolutionEdge(StateNode source, StateNode dest, CapabilityEdge edge) {
		System.out.println("S: "+printStateNode(source)+" -{"+edge.getCapabilityName()+"}-> D: "+printStateNode(dest));
	}

	@Override
	public void notifyChoiceEdge(StateNode source, XorNode dest, CapabilityEdge edge) {
		String dest_name = safelyAddXorNode(dest);
		System.out.println("S: "+printStateNode(source)+" -{"+edge.getCapabilityName()+"}-> D: "+dest_name);
	}

	private String safelyAddXorNode(XorNode dest) {
		String name = "X"+xor_counter;
		map.put(dest,name);
		
		xor_counter++;

		return name;
	}

	@Override
	public void notifyScenarioEdge(XorNode source, StateNode dest, ScenarioEdge edge) {
		String src_name = map.get(source);
		System.out.println("S: "+src_name+" -("+edge.getScenario()+")-> D: "+printStateNode(dest));
	}
	
	private String printStateNode(StateNode node) {
		String string = "";
		
		string+=node.getState();
		if (node.isExitNode())
			string += "(Exit)";
		
		return string;
	}

}
