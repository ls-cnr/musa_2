package org.icar.musa.solution_extractor.centralized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.icar.musa.proactive_means_end_reasoning.wts.EvolutionEdge;
import org.icar.musa.proactive_means_end_reasoning.wts.NormalEdge;
import org.icar.musa.proactive_means_end_reasoning.wts.OPNode;
import org.icar.musa.proactive_means_end_reasoning.wts.WTS;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;

/**
 * This class describe a Solution Tree. It takes as input the WTS and extracts
 * all solutions, factoring in all xor nodes and loop nodes related problems.
 * 
 * @author Mirko Avantaggiato
 *
 */
public class SolutionTree {
	private Tree<String> root;

	private WTS wts;
	private HashMap<String, WorldNode> successNodes;

	private HashMap<String, WorldNode> failureNodes;
	private HashMap<String, ArrayList<OPNode>> xorNodes;

	private ArrayList<ArrayList<String>> allPaths;
	private ArrayList<ArrayList<String>> loopPaths;

	private HashSet<String> treeSafeNodes;

	private SolutionSet solutionSet;

	/**
	 * @param wts
	 *            the world transition system to work on
	 * @param success_nodes
	 *            success_nodes of the WTS
	 */
	public SolutionTree(WTS wts, HashMap<String, WorldNode> success_nodes) {
		this.wts = wts;
		this.successNodes = success_nodes;
		/*
		 * TODO aggiungo manualmente 2 stati di successo.
		 */
		if (success_nodes.size() == 0) {
			this.successNodes.put(
					"order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n",
					this.wts.getWTS().get(
							"order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n"));

			this.successNodes.put(
					"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n",
					this.wts.getWTS().get(
							"invoice(the_invoice)\norder(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\nmailed_perm_link(the_invoice, a_user)\nstorehouse_manager(a_storehouse_manager)\ndelivery_order(the_delivery_order)\nsent(the_delivery_order, a_storehouse_manager)\n"));
		}
		/*
		 * ------------------------------
		 */
		this.xorNodes = new HashMap<>();
		this.failureNodes = new HashMap<>();
		/*
		 * ------------------------------
		 */
		this.allPaths = new ArrayList<>();
		this.loopPaths = new ArrayList<>();
		/*
		 * ------------------------------
		 */
		this.treeSafeNodes = new HashSet<>();
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
	 * 
	 * This method produces the list of all paths from W0 to each possibile
	 * success or failure state. This list will be used to build the tree and
	 * extract the solution(s).
	 *
	 * @param w
	 *            initial node
	 * @param visited
	 *            visited so far
	 * @param path
	 *            path built
	 */
	private void WTS_toPathList(WorldNode w, HashSet<String> visited, ArrayList<String> path) {
		String s = w.getWorldState().toString();

		visited.add(s);
		path.add(s);

		if (this.successNodes.containsKey(s) || this.failureNodes.containsKey(s))
			this.allPaths.add(new ArrayList<String>(path));

		else {
			for (int i = 0; i < w.getOutcomingEdgeList().size(); i++) {
				String toVisit = w.getOutcomingEdgeList().get(i).getDestination().getWorldState().toString();
				if (visited.contains(toVisit) == false)
					WTS_toPathList(w.getOutcomingEdgeList().get(i).getDestination(), visited, path);

				else {
					String loopNode = toVisit;
					path.add(loopNode);
					this.allPaths.add(new ArrayList<String>(path));
					this.loopPaths.add(new ArrayList<String>(path));
					path.remove(path.size() - 1);
				}
			}

			for (int i = 0; i < w.getOPNodeList().size(); i++) {
				path.add(i + "$" + w.getWorldState().toString().hashCode());
				for (EvolutionEdge eE : w.getOPNodeList().get(i).getOutcomingEdge()) {
					String toVisit = eE.getDestination().getWorldState().toString();
					if (visited.contains(toVisit) == false)
						WTS_toPathList(eE.getDestination(), visited, path);

					else {
						String loopNode = toVisit;
						path.add(loopNode);
						this.allPaths.add(new ArrayList<String>(path));
						this.loopPaths.add(new ArrayList<String>(path));
						path.remove(path.size() - 1);
					}
				}
				path.remove(path.lastIndexOf(i + "$" + w.getWorldState().toString().hashCode()));
			}
		}

		path.remove(path.size() - 1);
		visited.remove(s);
	}

	/**
	 * Support method.
	 * 
	 * @param w0
	 *            initial node
	 */
	public void WTS_toPathList(WorldNode w0) {
		ArrayList<String> path = new ArrayList<>();
		HashSet<String> visited = new HashSet<>();
		WTS_toPathList(w0, visited, path);
	}

	/**
	 * This method produces the tree the real algorithm will work on and extract
	 * the actual solution(s).
	 */
	public void pathList_toTree() {
		this.root = new Tree<String>(this.allPaths.get(0).get(0));
		setNodeType(this.root);

		for (ArrayList<String> path : this.allPaths) {
			Tree<String> currentNode = this.root;
			Iterator<String> i = path.iterator();
			if (i.hasNext())
				i.next();

			while (i.hasNext()) {
				Tree<String> newBorn = new Tree<String>((String) i.next());
				setNodeType(newBorn);

				if (!currentNode.getChildren().contains(newBorn))
					currentNode.getChildren().add(newBorn);
				currentNode = currentNode.getChild(newBorn);
			}

			if (this.loopPaths.contains(path))
				currentNode.setNodeType(Tree.LOOP_CODE);

		}
	}

	/**
	 * This method decides which category a TreeNode belongs to.
	 * 
	 * @param node
	 */
	private void setNodeType(Tree<String> node) {
		if (this.successNodes.containsKey(node.getValue()) == true)
			node.setNodeType(Tree.SUCCESS_CODE);
		else if (this.failureNodes.containsKey(node.getValue()) == true)
			node.setNodeType(Tree.FAILURE_CODE);
		else if (this.xorNodes.containsKey(node.getValue()) == true)
			node.setNodeType(Tree.XOR_CODE);
		else if (node.getValue().contains("$")) {
			node.setNodeType(Tree.EXPLICIT_XOR_CODE);
		} else
			node.setNodeType(Tree.NORMAL_CODE);
	}

	/**
	 * A more user-friendly translation.
	 * 
	 * @param type
	 *            int type
	 * @return string for that type
	 */
	public static String typeToString(int type) {

		if (type == Tree.NORMAL_CODE)
			return "normal";
		else if (type == Tree.SUCCESS_CODE)
			return "success";
		else if (type == Tree.XOR_CODE)
			return "xor";
		else if (type == Tree.FAILURE_CODE)
			return "failure";
		else if (type == Tree.LOOP_CODE)
			return "loop";
		else if (type == Tree.EXPLICIT_XOR_CODE)
			return "explicit_xor";
		else
			return "";
	}

	/**
	 * This method prints the tre produced in pathList_toTree();
	 * 
	 * @param node
	 *            current node
	 * @param depth
	 *            to print proper number of "\t"
	 */
	public static void printTree(Tree<String> node, int depth) {
		if (node != null) {
			String tab = String.join("", Collections.nCopies(depth, "\t"));
			System.out.println(tab + node.getValue().hashCode() + "(" + typeToString(node.getNodeType()) + ")" + "_"
					+ node.getID());
			for (Tree<String> x : node.getChildren())
				printTree(x, depth + 1);
		}

	}

	/**
	 * Support method.
	 */
	public void printTree() {
		printTree(this.getRoot(), 0);
	}

	/**
	 * This method prints the tree (dot language) adding at the end of each node
	 * 
	 * @param node
	 *            tree node
	 * @param treeSafeNodes
	 *            used to decide if a node is safe
	 */
	public static void printTreeGraphviz(Tree<String> node, HashSet<String> treeSafeNodes) {
		if (node != null) {
			String V = "\"";
			String src = "", dest = "";
			for (Tree<String> x : node.getChildren()) {
				boolean flag = node.getValue().contains("$");
				if (flag)
					src = node.getValue() + "\n" + typeToString(node.getNodeType()) + "\n" + node.getID() + "\nsafe: "
							+ treeSafeNodes.contains(node.getValue());
				else
					src = node.getValue() + "\n" + node.getValue().hashCode() + "\n" + typeToString(node.getNodeType())
							+ "\n" + node.getID() + "\nsafe: " + treeSafeNodes.contains(node.getValue());

				if (x.getValue().contains("$"))
					dest = x.getValue() + "\n" + typeToString(x.getNodeType()) + "\n" + x.getID() + "\nsafe: "
							+ treeSafeNodes.contains(x.getValue());
				else
					dest = x.getValue() + "\n" + x.getValue().hashCode() + "\n" + typeToString(x.getNodeType()) + "\n"
							+ x.getID() + "\nsafe: " + treeSafeNodes.contains(x.getValue());

				if (flag)
					System.out.println(
							V + src + V + "[color=red]\n" + V + src + V + "->" + V + dest + V + "[color=red]\n");
				else
					System.out.println(V + src + V + "->" + V + dest + V);
				printTreeGraphviz(x, treeSafeNodes);
			}
		}

	}

	/**
	 * Support method.
	 */
	public void printTreeGraphviz() {
		System.out.println("digraph{");
		printTreeGraphviz(this.root, this.treeSafeNodes);
		System.out.println("labelloc=\"t\";\nlabel=\"TREE\"\n}");
	}

	/**
	 * Support method. It uses graphviz graph clusters.
	 */
	public void printTreeGraphvizAsSubGraph() {
		System.out.println("subgraph cluster_TREE{");
		printTreeGraphviz(this.root, this.treeSafeNodes);
		System.out.println("labelloc=\"t\";\nlabel=\"TREE\"\n}");
	}

	/**
	 * This method produces the output solution(s), bottom-up approach.
	 * 
	 * 
	 * @param currentNode
	 * @return
	 */
	private ArrayList<Tree<String>> tree_toSolutionSet(Tree<String> currentNode) {
		ArrayList<Tree<String>> solutions = new ArrayList<>();

		if (currentNode.isLeaf()) {
			Tree<String> lastNode = new Tree<String>(currentNode.getValue());
			lastNode.setNodeType(currentNode.getNodeType());

			if (currentNode.getNodeType() == Tree.SUCCESS_CODE || currentNode.getNodeType() == Tree.LOOP_CODE) {
				solutions.add(lastNode);
				return solutions;
			} else
				return null;
		}

		else {
			if (currentNode.getNodeType() == Tree.XOR_CODE || currentNode.getNodeType() == Tree.NORMAL_CODE) {
				for (Tree<String> child : currentNode.getChildren()) {
					ArrayList<Tree<String>> subSolutions = tree_toSolutionSet(child);
					if (subSolutions != null)
						for (Tree<String> solutionTree : subSolutions) {

							Tree<String> subTree = new Tree<String>(currentNode.getValue());
							subTree.setNodeType(currentNode.getNodeType());

							subTree.getChildren().add(solutionTree);
							solutions.add(subTree);

						}
				}
				if (solutions.isEmpty())
					return null;
				else
					return solutions;

			} else if (currentNode.getNodeType() == Tree.EXPLICIT_XOR_CODE) {
				HashSet<ArrayList<Tree<String>>> set = new HashSet<>();
				for (Tree<String> child : currentNode.getChildren()) {
					ArrayList<Tree<String>> subSolutions = tree_toSolutionSet(child);
					if (subSolutions != null)
						set.add(subSolutions);
					else
						return null;
				}

				HashSet<ArrayList<String>> set_copy = new HashSet<>();
				for (ArrayList<Tree<String>> al : set) {
					ArrayList<String> toAdd = new ArrayList<>();
					for (Tree<String> ts : al)
						toAdd.add(ts.getID_asString());
					set_copy.add(toAdd);
				}

				Iterator<ArrayList<String>> set_copyIterator = set_copy.iterator();
				if (set_copyIterator.hasNext()) {
					ArrayList<String> first = (ArrayList<String>) set_copyIterator.next();
					while (set_copyIterator.hasNext()) {
						ArrayList<String> first_copy = new ArrayList<>(first);
						ArrayList<String> second = (ArrayList<String>) set_copyIterator.next();
						first = SolutionTree.combineTwoArrayList(first_copy, second);
					}
					for (String examinedString : first) {

						Tree<String> subTree = new Tree<String>(currentNode.getValue());
						subTree.setNodeType(currentNode.getNodeType());

						String[] S = examinedString.split("@");
						ArrayList<String> stringsToParse = new ArrayList<>();
						for (int i = 0; i < S.length; i++)
							stringsToParse.add(S[i]);

						for (String s : stringsToParse) {
							Tree<String> toAddAsChild = new Tree<String>("");
							for (ArrayList<Tree<String>> array : set)
								for (Tree<String> tree : array)
									if (tree.getID_asString().equals(s)) {
										toAddAsChild.setValue(tree.getValue());
										toAddAsChild.setNodeType(tree.getNodeType());
										toAddAsChild.setChildren(tree.getChildren());
										subTree.getChildren().add(Tree.getSameTree_newID(toAddAsChild));
									}
						}
						solutions.add(subTree);
					}
				}
				return solutions;
			}
			return solutions;
		}
	}

	/**
	 * This method combines 2 ArrayLists<String>. <br>
	 * Example: A = [a, b, c] B = [d, e] <br>
	 * 
	 * <b>res = [ad, ae, bd, be, cd, ce]</b>
	 * 
	 */
	private static ArrayList<String> combineTwoArrayList(ArrayList<String> A, ArrayList<String> B) {
		ArrayList<String> res = new ArrayList<>();
		for (String a : A)
			for (String b : B)
				res.add(a + "@" + b);
		return res;
	}

	public void tree_toSolutionSet() {
		ArrayList<Tree<String>> solutions = tree_toSolutionSet(this.getRoot());
		SolutionSet solutionSet = new SolutionSet(this);
		if (solutions != null) {
			for (Tree<String> s : solutions)
				solutionSet.addSolution(new Solution(s, solutionSet));
			Iterator<Solution> i = solutionSet.iterator();
			while (i.hasNext()) {
				Solution s = i.next();
				this.makeTreeSafe(s.getRoot());
				/*
				 * removing a solutions if it has got a loop which does not
				 * arrive to a safe Tree node or if it is a simple path ending
				 * with a loop (like A->B->C->L(A))
				 */
				if (s.checkLoop() == false || (s.isSimplePath() == true && s.containsLoop() == true))
					i.remove();

			}
		}
		this.setSolutionSet(solutionSet);
	}

	/**
	 * A node is safe if it is a success node or if at least one of its children
	 * is safe. Bottom-up approach.
	 * 
	 * @param t
	 */
	private void makeTreeSafe(Tree<String> t) {
		if (t != null) {
			if (t.getNodeType() == Tree.SUCCESS_CODE)
				this.treeSafeNodes.add(t.getValue());
			else {
				if (t.getNodeType() == Tree.XOR_CODE)
					t.setNodeType(Tree.NORMAL_CODE);
				for (Tree<String> child : t.getChildren()) {
					this.makeTreeSafe(child);
					if (this.treeSafeNodes.contains(child.getValue()))
						this.treeSafeNodes.add(t.getValue());
				}
			}
		}
	}

	public Tree<String> getRoot() {
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

	public HashSet<String> getTreeSafeNode() {
		return treeSafeNodes;
	}

	public ArrayList<ArrayList<String>> getAllPaths() {
		return this.allPaths;
	}

	public SolutionSet getSolutionSet() {
		return solutionSet;
	}

	public void setSolutionSet(SolutionSet solutionSet) {
		this.solutionSet = solutionSet;
	}

}
