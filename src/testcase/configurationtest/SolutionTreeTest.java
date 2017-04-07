package configurationtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import b2b_cloud_scenario.B2BCloudSetup;
import configuration.SolutionTree;
import configuration.Tree;
import pmr.SolutionGraph;
import pmr.graph.EvolutionEdge;
import pmr.graph.NormalEdge;
import pmr.graph.OPNode;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.ProblemExploration;

/**
 * SolutionTree test class.
 * 
 * @author Mirko Avantaggiato
 *
 */
public class SolutionTreeTest {
	@Test
	public void testB2BCloud() {
		B2BCloudSetup b2bcs = new B2BCloudSetup();
		SolutionGraph sg = new SolutionGraph();
		ProblemExploration pe = new ProblemExploration(b2bcs.getGoalModel(), b2bcs.getCapabilities(),
				b2bcs.getDomain());
		// b2bcs.getGoalModel().printModel();
		sg.getWTS().setInitialState(b2bcs.getNodewStart().getWorldState());
		pe.addToVisit(b2bcs.getNodewStart(), b2bcs.getStartTokens(), 9);
		for (int i = 0; i < 40; i++) {
			pe.expandNode();
			// for (ENode t : pe.getHighestExpansion().getDestination()) {
			// System.out.println(t.getScore());
			// }
			ExpansionNode en = pe.getHighestExpansion();
			if (en != null) {
				sg.addNode(en);
				pe.removeExpandedNode(en);
			}
		}
		WTS wts = sg.getWTS();
		HashMap<String, WorldNode> success_nodes = sg.getExitNodeMap();
		WorldNode w0 = b2bcs.getNodewStart();
		w0 = wts.getWTS().get(w0.getWorldState().toString());

		SolutionTree st = new SolutionTree(wts, success_nodes);
		HashMap<String, WorldNode> visited = new HashMap<>();

		st.preliminaryVisit(w0, visited);
		
		/*<test1>*/
		for (String s : st.getFailureNodes().keySet())
			st.getSuccessNodes().put(s, st.getFailureNodes().get(s));
		st.getFailureNodes().clear();
		/*</1>*/
		
		st.WTS_toPathList(w0);
		// printAllPathsGraphvizHashCode(st);
		st.pathList_toTree();
		// st.printTree();

		System.out.println("digraph{");

		printWTSHashCodeGraphvizAsSubGraph(wts);
		st.printTreeGraphvizAsSubGraph();
		ArrayList<Tree<String>> solutions = st.tree_toSolutionSet(st.getRoot());
		if (solutions != null) {
			int i = 1;
			for (Tree<String> t : solutions) {
				System.out.println("subgraph cluster_" + i + "{");
				SolutionTree.printTreeGraphviz(t);
				System.out.println("labelloc=\"t\";\nlabel=\"SOLUTION " + i++ + "\"\n}");
			}
		}
		System.out.println("}");
	}

	/**
	 * This method prints (dot language) all paths found.
	 * 
	 * @param st
	 */
	public void printAllPathsGraphviz(SolutionTree st) {
		int i = 1;
		for (ArrayList<String> path : st.getAllPaths()) {
			System.out.println("Path " + i++);
			System.out.println("digraph{");
			StringBuilder sb = new StringBuilder();
			for (String node : path)
				sb.append("\"" + node + "\"" + "->");
			String s = sb.toString().substring(0, sb.toString().length() - 2) + "}";
			System.out.println(s);
		}
	}

	/**
	 * This method prints (dot language) all paths found + their hashcodes.
	 * 
	 * @param st
	 */
	public void printAllPathsGraphvizHashCode(SolutionTree st) {
		int i = 1;
		for (ArrayList<String> path : st.getAllPaths()) {
			System.out.println("Path " + i++);
			System.out.println("digraph{");
			StringBuilder sb = new StringBuilder();
			for (String node : path)
				if (node.contains("$") == false)
					sb.append("\"" + node + node.hashCode() + "\"" + "->");
				else
					sb.append("\"" + node + "\"" + "->");
			String tmp = sb.toString().substring(0, sb.toString().length() - 2) + "}";
			System.out.println(tmp);
		}
	}

	/**
	 * This method prints the wts (dot language). green edges = normal edges.
	 * red, bold edges = evolution edges
	 * 
	 * @param wts
	 *            wts to print
	 */
	public void printWTS(WTS wts) {
		Iterator<String> i = wts.getWTS().keySet().iterator();
		while (i.hasNext()) {
			String temp = (String) i.next();
			WorldNode w = wts.getWTS().get(temp);

			for (NormalEdge e : w.getOutcomingEdgeList())
				System.out.println("\"" + temp + "\"" + " -> " + "\"" + e.getDestination().getWorldState().toString()
						+ "\"" + "[label=\"" + e.getCapability() + "\"][color=green]");

			for (OPNode opNode : w.getOPNodeList())
				for (EvolutionEdge ee : opNode.getOutcomingEdge())
					System.out
							.println("\"" + temp + "\"" + " -> " + "\"" + ee.getDestination().getWorldState().toString()
									+ "\"" + "[label=\"" + ee.getScenario() + "\"][style=bold][color=red]");

		}
	}

	/**
	 * This method prints the wts (dot language) adding at the end of WorldNode
	 * the hashcode corresponding to its facts. green edges = normal edges. red,
	 * bold edges = evolution edges
	 * 
	 * @param wts
	 *            wts to print
	 */
	public void printWTSHashCodeGraphviz(WTS wts) {
		System.out.println("digraph{");
		Iterator<String> i = wts.getWTS().keySet().iterator();
		while (i.hasNext()) {
			String temp = (String) i.next();
			WorldNode w = wts.getWTS().get(temp);
			for (NormalEdge e : w.getOutcomingEdgeList())
				System.out.println("\"" + temp + "\n" + temp.hashCode() + "\"" + " -> " + "\""
						+ e.getDestination().getWorldState().toString() + "\n"
						+ e.getDestination().getWorldState().toString().hashCode() + "\"" + "[label=\""
						+ e.getCapability() + "\"][color=green]");

			for (OPNode opNode : w.getOPNodeList())
				for (EvolutionEdge ee : opNode.getOutcomingEdge())
					System.out.println("\"" + temp + "\n" + temp.hashCode() + "\"" + " -> " + "\""
							+ ee.getDestination().getWorldState().toString() + "\n"
							+ ee.getDestination().getWorldState().toString().hashCode() + "\"" + "[label=\""
							+ ee.getScenario() + "\"][style=bold][color=red]");

		}
		System.out.println("labelloc=\"t\";\nlabel=\"WTS\"\n}");
	}

	public void printWTSHashCodeGraphvizAsSubGraph(WTS wts) {
		System.out.println("subgraph cluster_WTS{");
		Iterator<String> i = wts.getWTS().keySet().iterator();
		while (i.hasNext()) {
			String temp = (String) i.next();
			WorldNode w = wts.getWTS().get(temp);
			for (NormalEdge e : w.getOutcomingEdgeList())
				System.out.println("\"" + temp + "\n" + temp.hashCode() + "\"" + " -> " + "\""
						+ e.getDestination().getWorldState().toString() + "\n"
						+ e.getDestination().getWorldState().toString().hashCode() + "\"" + "[label=\""
						+ e.getCapability() + "\"][color=green]");

			for (OPNode opNode : w.getOPNodeList())
				for (EvolutionEdge ee : opNode.getOutcomingEdge())
					System.out.println("\"" + temp + "\n" + temp.hashCode() + "\"" + " -> " + "\""
							+ ee.getDestination().getWorldState().toString() + "\n"
							+ ee.getDestination().getWorldState().toString().hashCode() + "\"" + "[label=\""
							+ ee.getScenario() + "\"][style=bold][color=red]");

		}
		System.out.println("labelloc=\"t\";\nlabel=\"WTS\"\n}");
	}
}
