package org.icar.musa.solution;

import java.util.ArrayList;
import java.util.HashMap;

import org.icar.musa.pmr.problem_exploration.WTSEdge;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;


public class AWBuilder {
	private ArrayList<TreeBrick> bricks;
	private ArrayList<TreeBrick> solutions;
	private HashMap<NodeCouple,WTSEdge> seq;
	
	public AWBuilder() {
		super();
		bricks = new ArrayList<>();
		solutions = new ArrayList<>();
		
		seq = new HashMap<>();
	}
	
	public int size() {
		return bricks.size();
	}
	
	public ArrayList<TreeBrick> getBricks() {
		return bricks;
	}

	public void addFirstNode(StateNode node) {
		bricks.clear();
		
		bricks.add(new TreeBrick(node));
	}
	
	public void addEvolutionEdge(StateNode source,StateNode dest, CapabilityEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);
		
		if (dest.isExitNode()) {
			search_solutions();
		}
	}

	public void addChoiceEdge(StateNode source,XorNode dest, CapabilityEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);
	}
	
	public void addScenarioEdge(XorNode source,StateNode dest, ScenarioEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);

		if (dest.isExitNode()) {
			search_solutions();
		}
	}

	private void search_solutions() {
		// TODO Auto-generated method stub
		
	}

	private void tryExtendTrees(WTSNode source, WTSNode dest) {
		ArrayList<TreeBrick> new_bricks = new ArrayList<>();
		for (TreeBrick s : bricks) {
			boolean appended = s.appendSequence(source, dest,false);
			if (!appended) {
				TreeBrick clone = s.clone_if_attach(source, dest,false);
				if (clone != null) {
					new_bricks.add(clone);
				}
			}
		}
		bricks.addAll(new_bricks);
	}
	
	
}
