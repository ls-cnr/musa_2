package org.icar.musa.pmr.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.icar.musa.pmr.problem_exploration.WTSEdge;
import org.icar.musa.pmr.problem_exploration.WTSEventListener;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;

public class AWBuilder implements WTSEventListener {
	private HashSet<TreeBrick> bricks;
	private HashSet<TreeBrick> solutions;
	private HashMap<NodeCouple,WTSEdge> seq;
	
	public AWBuilder() {
		super();
		bricks = new HashSet<TreeBrick>();
		solutions = new HashSet<TreeBrick>();
		
		seq = new HashMap<NodeCouple,WTSEdge>();
	}
	
	public int size() {
		return bricks.size();
	}
	
	public HashSet<TreeBrick> getBricks() {
		return bricks;
	}

	@Override
	public void notifyFirstNode(StateNode node) {
		addFirstNode(node);
	}

	@Override
	public void notifyEvolutionEdge(StateNode source, StateNode dest, CapabilityEdge edge) {
		addEvolutionEdge(source,dest,edge);
		//internal_report();
	}

	@Override
	public void notifyChoiceEdge(StateNode source, XorNode dest, CapabilityEdge edge) {
		addChoiceEdge(source, dest, edge);
		//internal_report();
	}

	@Override
	public void notifyScenarioEdge(XorNode source, StateNode dest, ScenarioEdge edge) {
		addScenarioEdge(source, dest, edge);
		//internal_report();
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
		ArrayList<TreeBrick> new_original_bricks = new ArrayList<>();
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
		for (TreeBrick n : new_bricks) {
			boolean original = true;
			for (TreeBrick s : bricks) {
				if (n.equals(s)) {
					original = false;
				}
			}
			if (original)
				new_original_bricks.add(n);
		}
		
		bricks.addAll(new_original_bricks);
	}

	public HashSet<TreeBrick> getSolutions() {
		return solutions;
	}

	public void log_solutions() {
		System.out.println("Num solutions "+solutions.size());
		for (TreeBrick t : solutions) {
			System.out.println("-----");
			log_solution_with_capability(t,0);
			//t.log(0);
			//log_solution_with_capability(t,0);
		}
		if (solutions.size()==0)
			for (TreeBrick t : bricks) {
				t.log(0);
			}
	}
	
	private void internal_report() {
		System.out.println("n. bricks: "+bricks.size());
		for (TreeBrick tb : bricks) {
			tb.print_as_inline(); System.out.println("");
		}
		System.out.println("n. solutions: "+solutions.size());
		for (TreeBrick s : solutions) {
			s.print_as_inline(); System.out.println("");
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
