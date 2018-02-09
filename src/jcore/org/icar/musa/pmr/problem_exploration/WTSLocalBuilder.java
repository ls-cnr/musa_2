package org.icar.musa.pmr.problem_exploration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.ProblemSpecification;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.specification.LTLgoal.model.LTLGoal;

public class WTSLocalBuilder {
	private static int max_iteration = 20;
	
	private ProblemSpecification ps;	
	private ArrayList<AbstractCapability> allCap;
	
	private List<WTSEventListener> listeners;
	
	public WTSLocalBuilder(ProblemSpecification ps, ArrayList<AbstractCapability> capabilities) {
		super();
		this.ps = ps;		
		this.allCap = capabilities;
		
		listeners = new LinkedList<WTSEventListener>();
	}
	
	public WTS build_solution_space(StateOfWorld initial_state) throws ProblemDefinitionException {
		StateNode root = new StateNode(initial_state);
		NetHierarchyBuilder builder = new NetHierarchyBuilder();
		NetHierarchy nets = builder.build((LTLGoal) ps.getGoal_specification() );
		
		WTS wts = new WTS(root, nets);
		notifyFirstNode(root);
		
		ProblemExploration pe = new ProblemExploration(ps, allCap,"myagent");	
		pe.set_start_node(root);
		
		boolean terminated = false;
		
		int it_counter = 0;
		while (terminated==false & it_counter<max_iteration) {
			pe.generate_expansion();
			
			WTSExpansion exp = pe.getHighestExpansion();
			pe.pickExpansion(exp);
			
			if (!exp.isContain_forbidden()) {
				wts.addExpansion(exp);
				notifyExpansion(exp);
				
				for (WTSNode node : exp.getEvolutionNodes() ) {
					StateNode n = (StateNode) node;
					if (! n.isExitNode() ) {
						pe.add_new_node(n);
					}
				}
			}
			
			terminated = pe.test_termination();
			it_counter++;
		}	
		
		return wts;
	}

	public void register(WTSEventLogger logger) {
		listeners.add(logger);
	}
 
	
	private void notifyExpansion(WTSExpansion exp) {
		if (!exp.isMulti_expansion()) {
			StateNode src = exp.getRoot();
			StateNode dst = (StateNode) exp.getEvolutionNodes().get(0);
			CapabilityEdge edge = (CapabilityEdge) exp.getEdge(src, dst);
			notifyEvolutionEdge(src,dst,edge);
		} else {
			StateNode src = exp.getRoot();
			XorNode xonode = exp.getXorNode();
			CapabilityEdge edge = (CapabilityEdge) exp.getEdge(src, xonode);
			notifyChoiceEdge(src,xonode,edge);
			
			for (WTSNode node :  exp.getEvolutionNodes()) {
				StateNode s = (StateNode) node;
				ScenarioEdge scen = (ScenarioEdge) exp.getEdge(xonode, node);
				notifyScenarioEdge(xonode, s,scen);
			}
			
		}
		
	}

	private void notifyFirstNode(StateNode node) {
		for (WTSEventListener listener : listeners) {
			listener.notifyFirstNode(node);
		}
	}
	private void notifyEvolutionEdge(StateNode source,StateNode dest, CapabilityEdge edge) {
		for (WTSEventListener listener : listeners) {
			listener.notifyEvolutionEdge(source, dest, edge);
		}
	}
	private void notifyChoiceEdge(StateNode source,XorNode dest, CapabilityEdge edge) {
		for (WTSEventListener listener : listeners) {
			listener.notifyChoiceEdge(source, dest, edge);
		}
	}
	private void notifyScenarioEdge(XorNode source,StateNode dest, ScenarioEdge edge) {
		for (WTSEventListener listener : listeners) {
			listener.notifyScenarioEdge(source, dest, edge);
		}
	}
	
}
