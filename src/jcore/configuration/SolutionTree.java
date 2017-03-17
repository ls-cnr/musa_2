package configuration;

import java.util.ArrayList;
import java.util.HashMap;

import pmr.graph.EvolutionEdge;
import pmr.graph.NormalEdge;
import pmr.graph.OPNode;
import pmr.graph.WTS;
import pmr.graph.WorldNode;

/**
 * This class describe a Solution Tree. It takes as input the WTS and extracts
 * all solutions, factoring in all xor nodes and loop nodes related problems.
 * 
 * @author Mirko Avantaggiato
 *
 */
public class SolutionTree {

	private TreeNode root;

	/* input */
	private WTS wts;
	private HashMap<WorldNode, WorldNode> success_nodes;

	/* temp */

	private HashMap<String, WorldNode> successNodesString;
	private HashMap<String, WorldNode> failureNodesString;
	private HashMap<String, ArrayList<OPNode>> xorNodesString;
	private HashMap<String, WorldNode> wtsString;

	/**
	 * @param wts
	 *            the world transition system to work on
	 * @param success_nodes
	 *            success_nodes of the WTS
	 */
	public SolutionTree(WTS wts, HashMap<WorldNode, WorldNode> success_nodes) {
		this.wts = wts;
		this.success_nodes = success_nodes;

		this.successNodesString = new HashMap<>();
		this.xorNodesString = new HashMap<>();
		this.failureNodesString = new HashMap<>();
		this.wtsString = new HashMap<>();

		/* Copio il WTS in una struttura <Stringa, WorldNode> */
		for (WorldNode w : this.wts.getWTS().keySet())
			this.wtsString.put(w.getWorldState().toString(), this.wts.getWTS().get(w));

		/*
		 * Copio il gli stati di successo in una struttura <Stringa, WorldNode>
		 */
		if (success_nodes.size() == 0)
			System.out.println("WARNING: empty success_nodes in input. Nothing to add.");
		for (WorldNode w : success_nodes.keySet())
			this.successNodesString.put(w.getWorldState().toString(), this.wts.getWTS().get(w));

		/*
		 * TODO aggiungo manualmente 2 stati finali, dal momento che mi arriva
		 * in input una struttura vuota (!?)
		 */
		System.out.println("Manually adding 2 success states");
		this.successNodesString.put(
				"order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n", this.wtsString
						.get("order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n"));

		this.successNodesString.put(
				"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n",
				this.wtsString.get(
						"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n"));
	}

	/**
	 * This method produces the lists of xor_nodes and failure_nodes
	 * 
	 * @param w0
	 *            initial state
	 * @param visited
	 *            support variable
	 */

	public void preliminaryVisit(WorldNode w0, HashMap<String, WorldNode> visited) {

		/*
		 * If w0 has no outgoing edge and it's not contained in success_nodes,
		 * it means it is a failure node, so it is put in the appropriate map.
		 */

		visited.put(w0.getWorldState().toString(), w0);
		if (w0.getOutcomingEdgeList().size() == 0)
			if (w0.getOPNodeList().size() == 0)
				if (this.successNodesString.containsKey(w0.getWorldState().toString()) == false)
					this.failureNodesString.put(w0.getWorldState().toString(), w0);

		if (w0.getOPNodeList().isEmpty() == false)
			this.xorNodesString.put(w0.getWorldState().toString(), w0.getOPNodeList());

		for (NormalEdge nE : w0.getOutcomingEdgeList())
			if (!visited.containsKey(nE.getDestination().getWorldState().toString())) {
				visited.put(nE.getDestination().getWorldState().toString(), nE.getDestination());
				WorldNode to_visit = wtsString.get(nE.getDestination().getWorldState().toString());
				preliminaryVisit(to_visit, visited);
				visited.remove(nE.getDestination().getWorldState().toString());
			}

		for (OPNode opNode : w0.getOPNodeList()) {
			for (EvolutionEdge eE : opNode.getOutcomingEdge())
				if (!visited.containsKey(eE.getDestination().getWorldState().toString())) {
					visited.put(eE.getDestination().getWorldState().toString(), eE.getDestination());
					WorldNode to_visit = wtsString.get(eE.getDestination().getWorldState().toString());
					preliminaryVisit(to_visit, visited);
					visited.remove(eE.getDestination());
				}
		}

		visited.remove(w0.getWorldState().toString());
	}

	/**
	 * This method produces the list of all paths from W0 to each possibile
	 * success or failure state. This list will be used to extract the
	 * solution(s).
	 */
	public void WTS_toPathList() {
		/* TODO */
	}

	/**
	 * This method produces the tree the real algorithm will work on and
	 * exctract the actual solution(s).
	 */
	public void pathList_toTree() {
		/* TODO */
	}

	/**
	 * This method produces the output solution(s).
	 */
	public void tree_toSolutionSet() {
		/* TODO */
	}

	public TreeNode getRoot() {
		return root;
	}

	public WTS getWts() {
		return wts;
	}

	public HashMap<WorldNode, WorldNode> getSuccess_nodes() {
		return success_nodes;
	}

	public HashMap<String, WorldNode> getSuccessNodesString() {
		return successNodesString;
	}

	public HashMap<String, WorldNode> getFailureNodesString() {
		return failureNodesString;
	}

	public HashMap<String, ArrayList<OPNode>> getXorNodesString() {
		return xorNodesString;
	}

	public HashMap<String, WorldNode> getWtsString() {
		return wtsString;
	}

}
