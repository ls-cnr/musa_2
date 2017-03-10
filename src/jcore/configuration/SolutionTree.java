package configuration;

import java.util.ArrayList;
import java.util.HashMap;

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
@SuppressWarnings("unused")
public class SolutionTree {

	private TreeNode root;

	/* input */
	private WTS wts;
	private HashMap<WorldNode, WorldNode> success_nodes;

	/* temp */
	private HashMap<WorldNode, WorldNode> failure_nodes;
	private HashMap<OPNode, OPNode> xor_nodes;

	private ArrayList<ArrayList<TreeNode>> pathList;

	/**
	 * This method produces the lists of xor_nodes and failure_nodes
	 * 
	 * @param w0
	 *            initial state
	 * @param success_nodes
	 *            success states
	 * @param failure_nodes
	 *            leaf node that aren't success nodes, found during the visit;
	 * @param visited
	 *            visited node in this DFS-like visit
	 * @param xor_nodes
	 *            xor_nodes found during the visit
	 */
	public void preliminaryVisit(WorldNode w0, HashMap<WorldNode, WorldNode> success_nodes,
			HashMap<WorldNode, WorldNode> failure_nodes, HashMap<WorldNode, WorldNode> visited,
			HashMap<WorldNode, ArrayList<OPNode>> xor_nodes) {

		/*
		 * If w0 has no outgoing edge and it's not contained in success_nodes,
		 * it means it is a failure node, so it is put in the appropriate map.
		 */
		if (w0.getOutcomingEdgeList().isEmpty() && !success_nodes.containsKey(w0)) {
			failure_nodes.put(w0, w0);
			return;
		}
		visited.put(w0, w0);
		xor_nodes.put(w0, w0.getOPNodeList());

		for (NormalEdge normalEdge : w0.getOutcomingEdgeList()) {
			if (!visited.containsKey(normalEdge)) {
				preliminaryVisit(normalEdge.getDestination(), success_nodes, failure_nodes, visited, xor_nodes);
			}
		}
		visited.remove(w0);
	}

	/**
	 * @param wts
	 *            the world transition system to work on
	 * @param success_nodes
	 *            success_nodes of the WTS
	 */
	public SolutionTree(WTS wts, HashMap<WorldNode, WorldNode> success_nodes) {
		super();
		this.wts = wts;
		this.success_nodes = success_nodes;
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
	 * This method produces the tree the real algorithm will work on
	 * and exctract the actual solution(s).
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
}
