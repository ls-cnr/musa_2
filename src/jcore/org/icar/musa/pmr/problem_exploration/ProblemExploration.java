package org.icar.musa.pmr.problem_exploration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.ltlpetrinet.supervisor.NetSupervisor;
import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.context.evolution.EvolutionOperator;
import org.icar.musa.core.context.evolution.EvolutionScenario;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.QualityAsset;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.specification.LTLgoal.model.LTLGoal;

public class ProblemExploration {
	private final double EXIT_REWARD_SCORE = 1;
	private final double FORBIDDEN_PAYOFF_SCORE = 1;
	
	private String my_agent_name = "unknown";
	
	private int iteration_counter=0;
	
	private static EntailOperator entail;

	private ProblemSpecification ps = null;
	
	/** The Assumption Set defined globally that the Agent has to maintain */
	private AssumptionSet assumptions;
	private QualityAsset asset;
	
	/** The Capabilities List that an Agent holds */
	private ArrayList<AbstractCapability> capabilities;
	
	/** The list of nodes to be visited */
	private ArrayList<StateNode> toVisit;
	
	/** The list of nodes already visited */
	private Set<StateNode> visited;
	
	/** The list of generated expansions */
	private ArrayList<WTSExpansion> generated_expansions;

	private NetSupervisor supervisor;

	public ProblemExploration( ProblemSpecification ps, ArrayList<AbstractCapability> capabilities, String agent_name) throws ProblemDefinitionException {
		initialize(ps,capabilities);
		this.ps = ps;
		my_agent_name = agent_name;
		entail = EntailOperator.getInstance();
	}

	private void initialize(ProblemSpecification ps, ArrayList<AbstractCapability> capabilities) throws ProblemDefinitionException {
		this.capabilities = new ArrayList<>(capabilities);
		this.assumptions = ps.getAssumptions();
		this.asset = ps.getQuality_asset();
		
		if (ps.getGoal_specification() == null) {
			// running without goals
			supervisor = null;
		} else {
		
			if (!(ps.getGoal_specification() instanceof LTLGoal)) {
				throw new ProblemDefinitionException();
			} else {
				LTLGoal mygoal = (LTLGoal) ps.getGoal_specification();
				NetHierarchyBuilder builder = new NetHierarchyBuilder();
				NetHierarchy nets = builder.build(mygoal);
				supervisor = new NetSupervisor(nets, nets.getInitialTokenConfiguration());
			}
			
		}
		
		iteration_counter=0;
	}

	public void set_start_node( StateNode node) {	
		toVisit = new ArrayList<>();
		visited = new HashSet<>();
		generated_expansions = new ArrayList<>();
		
		node.setStartNode(true);
		if (supervisor != null) {
			node.setTokens(supervisor.getNetModel().getInitialTokenConfiguration());
		}
		node.setGoal_satisfaction_degree(0);
		toVisit.add( node );
	}

	
	public void add_new_node( StateNode node) {	
		if( ! visited.contains(node)) {
			toVisit.add( node );
			
			/* keep the data sorted */
			toVisit.sort(StateNode.getScoreComparator());
		}
	}
	
	public int getIterationCounter() {
		return iteration_counter;
	}	
	
	/* THE CENTRAL METHOD */
	public void generate_expansion() {
		iteration_counter++;
		
		StateNode node = getMostPromisingNodeToVisit();
		visited.add(node);
		
		if (node != null) {
			for (AbstractCapability cap : capabilities) {
				boolean cap_applies = entail.entailsCondition(node.getState(), this.assumptions,(FOLCondition) cap.getPreCondition());
				if (cap_applies) {
					//System.out.println("cap: "+cap.getId());
					WTSExpansion exp = generate_cap_evolution(node, cap);
					if (exp!= null) {
						generated_expansions.add(exp);

					}
				}
			}
		}
	}
	
	public WTSExpansion getHighestExpansion() {
		WTSExpansion exp = null;
		if (! generated_expansions.isEmpty()) {
			/* keep the data sorted */
			generated_expansions.sort(WTSExpansion.getInverseScoreComparator());
			exp = generated_expansions.get(0);
		}
		return exp;
	}
	
	public void pickExpansion(WTSExpansion exp) {
		if (generated_expansions.contains(exp)) {
			generated_expansions.remove(exp);
		}
	}

	
	private StateNode getMostPromisingNodeToVisit() {
		StateNode node = null;
		if( !toVisit.isEmpty() )	{
			/* data is sorted, pickinf the first one */
			node = toVisit.remove(0);
		}
		return node;
	}

	private WTSExpansion generate_cap_evolution(StateNode node, AbstractCapability cap) {
		WTSExpansion exp = new WTSExpansion(cap.getId(),node);
		
		CapabilityEdge main_edge = new CapabilityEdge();
		main_edge.setCapabilityName(cap.getId());
		main_edge.setAgentProvider(my_agent_name);
		//main_edge.setCap_precedence(cap.getPrecedence());
		
		boolean multi_exp_mode = false;	
		XorNode multi_exp_xor_node = null;
		
		if (cap.getScenarioSet().size()>1) {
			exp.setMulti_expansion(true);
			multi_exp_mode = true;
			multi_exp_xor_node = new XorNode();

			exp.addVertex(multi_exp_xor_node);
			exp.addEdge(node, multi_exp_xor_node,main_edge);
		}
		
		for (EvolutionScenario scen : cap.getScenarioSet()) {			
			StateOfWorld dest_w = node.getState().clone();
			for (EvolutionOperator op : scen.getOperators()) {
				dest_w = op.apply_transformation(dest_w);
			}
			
			StateNode dest_node = new StateNode(dest_w);
			//if (!dest_node.equals(node)) {
				dest_node = update_node_metadata(node,dest_node);
				
				if (!multi_exp_mode) {
					exp.addVertex(dest_node);
					exp.addEdge(node, dest_node,main_edge);
				} else {
					exp.addVertex(dest_node);
					ScenarioEdge secondary_edge = new ScenarioEdge(scen.getName());
					exp.addEdge(multi_exp_xor_node, dest_node,secondary_edge);
				}
			//}
		
		}
		
		exp = update_expansion_metadata(exp);
		return exp;
	}

	/* calculate the overall score by looking at all the destination states */
	private WTSExpansion update_expansion_metadata(WTSExpansion exp) {
		ArrayList<WTSNode> final_nodes = exp.getEvolutionNodes();
		
		boolean contain_exit = false;
		boolean contain_forbidden = false;
		double score_sum = 0;
		
		for (WTSNode node : final_nodes) {
			StateNode state_node = (StateNode) node;
			score_sum+=state_node.getGoal_satisfaction_degree();
			if(state_node.isExitNode())
				contain_exit=true;
			if(state_node.isForbidden())
				contain_forbidden=true;
		}
		
		double average_score = score_sum/final_nodes.size();
		
		if (!contain_forbidden) {			
			double exit_reward = 0;
			if (contain_exit) {
				exit_reward = EXIT_REWARD_SCORE;
				exp.setContain_exit(true);
			}
			exp.setScore(average_score+exit_reward);
		} else {
			exp.setContain_forbidden(true);
			exp.setScore(average_score-FORBIDDEN_PAYOFF_SCORE);
		}
		
		return exp;
	}

	/* update tokens, goal_satisfaction_degree, exit state and forbidden state */ 
	private StateNode update_node_metadata(StateNode node, StateNode dest_node) {
		double score = 0;
			
		if (supervisor != null) {
			TokenConf start_tokens = node.getTokens();
			supervisor.setToken(start_tokens);
			supervisor.prepareTokens();
			supervisor.update(dest_node.getState(), assumptions);
			
			PNStateEnum updated_state = supervisor.getState();
			if( updated_state==PNStateEnum.ACCEPTED )
				dest_node.setExitNode(true);
			else if (updated_state==PNStateEnum.ERROR )
				dest_node.setForbidden(true);
			
			score = supervisor.calculate_partial_satisfaction(); 
			
			supervisor.cleanTokens();
			TokenConf updated_tokens = supervisor.getTokenConfiguration();
			dest_node.setTokens(updated_tokens);
		} 

		if (asset != null) {
			double metrics = asset.evaluate_state(dest_node.getState());
			score = metrics / (double) asset.max_score();
		}
		
		dest_node.setGoal_satisfaction_degree(score);
				
		return dest_node;
	}
	
	public void log_current_state() {
		System.out.println("========iteration ("+iteration_counter+")========");
		System.out.println("------------visited states---------------");
		for (StateNode s : visited) {
			if (s != null)
				if (s.getState() != null)
					System.out.println(asset.getShortStateRepresentation(s.getState()));
					//System.out.println(s.getState().toString());
				else
					System.out.println("node without state");
			else
				System.out.println("null node");
		}
//		System.out.println("------------to visite states---------------");
//		for (ExtendedNode n : toVisit) {
//			System.out.print(n.getWorldState().toString());
//			if (n.isExitNode())
//				System.out.print(" Exit ");
//			if (n.isErrorNode())
//				System.out.print(" Error ");
//			System.out.println("");
//		}
		System.out.println("--------------expanded---------------");
		for (WTSExpansion exp : generated_expansions) {
			
			if (!exp.isMulti_expansion()) {		
				for (WTSNode n : exp.getEvolutionNodes() ) {
					StateNode node = (StateNode) n;
					System.out.print("[...] ->"+exp.getCapability()+"\t-> ["+asset.getShortStateRepresentation(node.getState())+"]");
					//System.out.print(exp.getRoot().getState().toString()+"->"+exp.getCapability()+"\t->"+node.getState().toString());
					if (node.isExitNode())
						System.out.print(" is EXIT =>\t");
					if (node.isForbidden())
						System.out.print(" is FORB =>\t");
					
					System.out.println(" [score("+node.getGoal_satisfaction_degree()+") -"+node.getTokens() +"]");
				}
			} else {
				System.out.println(exp.getRoot().getState().toString()+"->");
				for (WTSNode n : exp.getEvolutionNodes() ) {
					StateNode node = (StateNode) n;
					System.out.print("\t\t[...] ->"+exp.getCapability()+"\t-> [...]");
					//System.out.print("\t\t->"+exp.getCapability()+"\t->"+node.getState().toString());
					if (node.isExitNode())
						System.out.print(" is EXIT =>\t");
					if (node.isForbidden())
						System.out.print(" is FORB =>\t");
					
					System.out.println(" [score("+node.getGoal_satisfaction_degree()+") -"+node.getTokens() +"]");
				}
				
			}
		}
	}

	/* methods for testing */
	public int test_expansions_numer() {
		return generated_expansions.size();
	}

	public boolean test_termination() {
		return toVisit.isEmpty() & generated_expansions.isEmpty();
	}
	
	public  WTSExpansion test_generate_cap_evolution(StateNode node, AbstractCapability cap) {
		return generate_cap_evolution(node,cap);
	}

}