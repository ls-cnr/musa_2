package org.icar.musa.solution;

import java.util.ArrayList;
import java.util.HashMap;

import org.icar.musa.pmr.problem_exploration.WTSEdge;
import org.icar.musa.pmr.problem_exploration.WTSEventListener;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;


public class AWBuilder implements WTSEventListener {
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

	@Override
	public void notifyFirstNode(StateNode node) {
		addFirstNode(node);
	}

	@Override
	public void notifyEvolutionEdge(StateNode source, StateNode dest, CapabilityEdge edge) {
		addEvolutionEdge(source,dest,edge);
	}

	@Override
	public void notifyChoiceEdge(StateNode source, XorNode dest, CapabilityEdge edge) {
		addChoiceEdge(source, dest, edge);
	}

	@Override
	public void notifyScenarioEdge(XorNode source, StateNode dest, ScenarioEdge edge) {
		addScenarioEdge(source, dest, edge);
	}

	
	
	private void addFirstNode(StateNode node) {
		bricks.clear();
		
		bricks.add(new TreeBrick(node));
	}
	
	private void addEvolutionEdge(StateNode source,StateNode dest, CapabilityEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);
		
		if (dest.isExitNode()) {
			search_solutions();
		}
	}

	private void addChoiceEdge(StateNode source,XorNode dest, CapabilityEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);
	}
	
	private void addScenarioEdge(XorNode source,StateNode dest, ScenarioEdge edge) {
		seq.put(new NodeCouple(source, dest), edge);
		tryExtendTrees(source, dest);

		if (dest.isExitNode()) {
			search_solutions();
		}
	}

	private void search_solutions() {
		for (TreeBrick tb : bricks) {
			if (!tb.isSolution()) {
				tb.update_metadata();
				if (tb.leadsToExit()) {
					tb.setSolution(true);
					
					if (!solutions.contains(tb));
						solutions.add(tb);
				}
			}
		}
	}

	private void tryExtendTrees(WTSNode source, WTSNode dest) {
		ArrayList<TreeBrick> new_bricks = new ArrayList<>();
		for (TreeBrick s : bricks) {
			boolean appended = false;
			if (!s.isSolution())
				appended = s.appendSequence(source, dest);
			
			if (!appended) {
				TreeBrick clone = s.clone_if_attach(source, dest,false);
				if (clone != null) {
					new_bricks.add(clone);
				}
			}
		}
		bricks.addAll(new_bricks);
	}

	public ArrayList<TreeBrick> getSolutions() {
		return solutions;
	}

	public void log_solutions() {
		System.out.println("Num solutions "+solutions.size());
		for (TreeBrick t : solutions) {
			System.out.println("-----");
			log_solution_with_capability(t,0);
		}
	}

	private void log_solution_with_capability(TreeBrick t, int tabs) {
		WTSNode start = t.getNode();
		for (TreeBrick sub : t.getChilds()) {
			WTSNode end = sub.getNode();
			WTSEdge edge = seq.get(new NodeCouple(start,end));
			if (edge instanceof CapabilityEdge) {
				CapabilityEdge cap = (CapabilityEdge) edge;
				System.out.println("cap: "+cap.getCapabilityName());
			}
			log_solution_with_capability(sub,tabs);
		}
	}
	
	
}
