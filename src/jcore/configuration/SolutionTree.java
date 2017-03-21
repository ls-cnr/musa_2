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
	private HashMap<String, WorldNode> successNodes;

	/* temp */

	private HashMap<String, WorldNode> failureNodes;
	private HashMap<String, ArrayList<OPNode>> xorNodes;

	private ArrayList<ArrayList<String>> allPaths;
	private ArrayList<ArrayList<String>> loopPaths;

	/**
	 * @param wts
	 *            the world transition system to work on
	 * @param success_nodes
	 *            success_nodes of the WTS
	 */
	public SolutionTree(WTS wts, HashMap<String, WorldNode> success_nodes) {
		this.wts = wts;
		this.successNodes = success_nodes;

		this.xorNodes = new HashMap<>();
		this.failureNodes = new HashMap<>();

		/*
		 * TODO aggiungo manualmente 2 stati finali, dal momento che mi arriva
		 * in input una struttura vuota (!?)
		 */
		if (success_nodes.size() == 0) {
			System.out.println("Manually adding 2 success states");
			this.successNodes.put(
					"order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n",
					this.wts.getWTS().get(
							"order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n"));

			this.successNodes.put(
					"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n",
					this.wts.getWTS().get(
							"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n"));
		}

		this.allPaths = new ArrayList<>();
		this.loopPaths = new ArrayList<>();
	}

	/**
	 * This method produces the lists of xor nodes and failure nodes
	 * 
	 * @param w0
	 *            initial state
	 * @param visited
	 *            support variable
	 */

	public void preliminaryVisit(WorldNode w0, HashMap<String, WorldNode> visited) {

		/*
		 * If w0 has no outgoing edge, no OPNodeList and it's not contained in
		 * success_nodes, it means it is a failure node, so it is put in the
		 * appropriate map.
		 */

		visited.put(w0.getWorldState().toString(), w0);

		// System.out.println("VISITING \n" + w0.getWorldState().toString());
		// System.out.println("Outcoming edges size: " +
		// w0.getOutcomingEdgeList().size());
		// System.out.println("OPList size: " + w0.getOPNodeList().size());

		if (w0.getOutcomingEdgeList().size() == 0)
			if (w0.getOPNodeList().size() == 0)
				if (this.successNodes.containsKey(w0.getWorldState().toString()) == false)
					this.failureNodes.put(w0.getWorldState().toString(), w0);

		if (w0.getOPNodeList() != null && w0.getOPNodeList().isEmpty() == false)
			this.xorNodes.put(w0.getWorldState().toString(), w0.getOPNodeList());

		for (NormalEdge nE : w0.getOutcomingEdgeList())
			if (visited.containsKey(nE.getDestination().getWorldState().toString()) == false) {
				WorldNode to_visit = this.wts.getWTS().get(nE.getDestination().getWorldState().toString());
				preliminaryVisit(to_visit, visited);
			}

		for (OPNode opNode : w0.getOPNodeList()) {
			for (EvolutionEdge eE : opNode.getOutcomingEdge())
				if (visited.containsKey(eE.getDestination().getWorldState().toString()) == false) {
					WorldNode to_visit = this.wts.getWTS().get(eE.getDestination().getWorldState().toString());
					preliminaryVisit(to_visit, visited);
				}
		}

		visited.remove(w0.getWorldState().toString());
	}

	/**
	 * This method produces the list of all paths from W0 to each possibile
	 * success or failure state. This list will be used to build the tree and
	 * extract the solution(s).
	 */
	public void WTS_toPathList(WorldNode w, HashMap<String, WorldNode> visited, ArrayList<String> path) {
		String s = w.getWorldState().toString();
		visited.put(s, w);
		path.add(s);

		if (this.successNodes.containsKey(s) || this.failureNodes.containsKey(s))
			this.allPaths.add(new ArrayList<String>(path));
		else {
			for (NormalEdge x : w.getOutcomingEdgeList())
				if (visited.containsKey(x.getDestination().getWorldState().toString()) == false)
					WTS_toPathList(x.getDestination(), visited, path);
				else {
					path.add(x.getDestination().getWorldState().toString());
					this.allPaths.add(new ArrayList<String>(path));
					this.loopPaths.add(new ArrayList<String>(path));
					path.remove(x.getDestination().getWorldState().toString());
				}
			for (OPNode opNode : w.getOPNodeList())
				for (EvolutionEdge eE : opNode.getOutcomingEdge())
					if (visited.containsKey(eE.getDestination().getWorldState().toString()) == false)
						WTS_toPathList(eE.getDestination(), visited, path);
					else {
						path.add(eE.getDestination().getWorldState().toString());
						this.allPaths.add(new ArrayList<String>(path));
						this.loopPaths.add(new ArrayList<String>(path));
						path.remove(eE.getDestination().getWorldState().toString());
					}

		}

		path.remove(path.size() - 1);
		visited.remove(s);
	}

	public ArrayList<ArrayList<String>> getAllPaths() {
		return allPaths;
	}

	public void WTS_toPathList(WorldNode w0) {
		ArrayList<String> path = new ArrayList<>();
		HashMap<String, WorldNode> visited = new HashMap<>();

		WTS_toPathList(w0, visited, path);

	}

	/**
	 * This method produces the tree the real algorithm will work on and
	 * exctract the actual solution(s).
	 */
	public void pathList_toTree(ArrayList<String> path) {
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

	public HashMap<String, WorldNode> getSuccessNodes() {
		return successNodes;
	}

	public HashMap<String, WorldNode> getFailureNodes() {
		return failureNodes;
	}

	public HashMap<String, ArrayList<OPNode>> getXorNodes() {
		return xorNodes;
	}

}
