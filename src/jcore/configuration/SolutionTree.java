package configuration;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<WorldNode, WorldNode> failure_nodes;
	private HashMap<OPNode, OPNode> xor_nodes;

	private ArrayList<ArrayList<TreeNode>> pathList;

	/**
	 * This method produces the lists of xor_nodes and failure_nodes
	 */

	public void preliminaryVisit() {
		/* TODO */
	}

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
	 * This method produces the tree on which the real algorithm will operate
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
