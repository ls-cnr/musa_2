package org.icar.musa.pmr.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.icar.musa.pmr.problem_exploration.WTSEdge;
import org.icar.musa.pmr.problem_exploration.WTSEventListener;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.QualityAsset;
import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

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

	public void log_solutions(AssumptionSet assumptions, QualityAsset asset) {
		System.out.println("Num solutions "+solutions.size());
		for (TreeBrick t : solutions) {
			System.out.println("-----");
			log_solution_with_capability(t,0,assumptions,asset);
			//t.log(0);
			//log_solution_with_capability(t,0);
		}
		if (solutions.size()==0)
			for (TreeBrick t : bricks) {
				t.log(0);
			}
	}

	public void log_partial_solutions(AssumptionSet assumptions, QualityAsset asset) {
		System.out.println("Num partial solutions "+bricks.size());
		List<PartialSolution> sorted = new LinkedList();
		
		for (TreeBrick t : bricks) {
			double t_score = t.calculate_partial_score();
			PartialSolution part_sol = new PartialSolution(t_score,t);
			sorted.add(part_sol);
		}
		sorted.sort(PartialSolution.getScoreComparator());
		
		Iterator<PartialSolution> it = sorted.iterator();
		int i=0;
		while (it.hasNext() & i<10) {
			i++;
			PartialSolution sol = it.next();
			System.out.println("------ sol "+i+" -------");
			log_solution_with_capability(sol.getPartial_solution(),0,assumptions,asset);
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
		
		if (t.getChilds().size()==0) {
			StateNode final_state = (StateNode) start;
			StateOfWorld w = final_state.getState();
			double score = final_state.getGoal_satisfaction_degree();
			System.out.println("final state: "+w.toString());
			System.out.println("score: "+score);
		}

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
	
	private void log_solution_with_capability(TreeBrick t, int tabs,AssumptionSet assumptions, QualityAsset asset) {
		WTSNode start = t.getNode();
		if ( start instanceof AWLoop) {
			System.out.println("loop");
			return;
		}
		if (t.getChilds().size()==0) {
			StateNode final_state = (StateNode) start;
			StateOfWorld w = final_state.getState();
			double score = final_state.getGoal_satisfaction_degree();
			System.out.println("final state: "+asset.getShortStateRepresentation(w));
			long long_score = asset.evaluate_state(w);
			System.out.println("l score: "+long_score);
			System.out.println("score: "+score);
			asset.log_state(assumptions,w);
		}

		for (TreeBrick sub : t.getChilds()) {
			WTSNode end = sub.getNode();
			WTSEdge edge = seq.get(new NodeCouple(start,end));
			if (edge instanceof CapabilityEdge) {
				CapabilityEdge cap = (CapabilityEdge) edge;
				System.out.println("cap: "+cap.getCapabilityName());
			}
			log_solution_with_capability(sub,tabs,assumptions,asset);
		}
	}
	
}
