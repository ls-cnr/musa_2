package configurationtest;

import java.util.HashMap;
import java.util.Iterator;
import org.junit.Test;

import b2b_cloud_scenario.B2BCloudSetup;
import configuration.centralized.Solution;
import configuration.centralized.SolutionSet;
import configuration.centralized.SolutionTree;
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

		/* ----- */

		SolutionTree st = new SolutionTree(wts, success_nodes);

		String tmp;
		WorldNode wn1, wn2;

		/*
		 * Preliminary visit to get some information
		 */
		st.preliminaryVisit(w0, new HashMap<String, WorldNode>());

		/* some tests */
		// 1: every final state is a success state

		for (String s : st.getFailureNodes().keySet())
			st.getSuccessNodes().put(s, st.getFailureNodes().get(s));
		st.getFailureNodes().clear();

		// 2: w0 has normalEdges instead of EEdges
		//
		// for (OPNode opNode : w0.getOPNodeList())
		// for (EvolutionEdge ee : opNode.getOutcomingEdge())
		// w0.addOutcomingEdge(new NormalEdge(w0, ee.getDestination(),
		// ee.getScenario()));
		// w0.getOPNodeList().clear();

		// 3: failure states make xor fail too

		// tmp =
		// "order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\nrefused(an_order)\n";
		// st.getFailureNodes().put(tmp, st.getSuccessNodes().get(tmp));
		// st.getSuccessNodes().remove(tmp);

		// 4: adding a loop edge
		//
		// tmp =
		// "order(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\naccepted(an_order)\ninvoice(the_invoice)\navailable(the_invoice)\nuploaded_on_cloud(the_invoice)\n";
		// wn1 = st.getWts().getWTS().get(tmp);
		// tmp =
		// "order(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\naccepted(an_order)\n";
		// wn2 = st.getWts().getWTS().get(tmp);
		//
		// wn1.addOutcomingEdge(new NormalEdge(wn1, wn2, "test4_edge"));
		//
		// 5: adding a loop edge
		//
		// tmp =
		// "order(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\naccepted(an_order)\ninvoice(the_invoice)\navailable(the_invoice)\n";
		// wn1 = st.getWts().getWTS().get(tmp);
		// tmp =
		// "order(an_order)\navailable(an_order)\nuser(a_user)\nuser_data(the_user_data)\n";
		// wn2 = st.getWts().getWTS().get(tmp);
		//
		// wn1.addOutcomingEdge(new NormalEdge(wn1, wn2, "test5_edge"));

		// 6: making a xor node normal
		// tmp =
		// "order(an_order)\navailable(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\n";
		// wn1 = st.getWts().getWTS().get(tmp);
		// for (OPNode opNode : wn1.getOPNodeList())
		// for (EvolutionEdge ee : opNode.getOutcomingEdge())
		// wn1.addOutcomingEdge(new NormalEdge(wn1, ee.getDestination(),
		// ee.getScenario()));
		// wn1.getOPNodeList().clear();

		// 7: adding an edge

		// tmp =
		// "order(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\naccepted(an_order)\ninvoice(the_invoice)\navailable(the_invoice)\n";
		// wn1 = st.getWts().getWTS().get(tmp);
		// tmp =
		// "order(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\naccepted(an_order)\ninvoice(the_invoice)\navailable(the_invoice)\n";
		// wn2 = st.getWts().getWTS().get(tmp);
		// wn1.addOutcomingEdge(new NormalEdge(wn1, wn2, "test6_edge"));

		// 8: adding an edge

		// tmp =
		// "order(an_order)\nuser(a_user)\nhas_cloud_space(a_user)\nlogged(a_user)\nregistered(a_user)\naccepted(an_order)\ninvoice(the_invoice)\navailable(the_invoice)\n";
		// wn1 = st.getWts().getWTS().get(tmp);
		// tmp =
		// "order(an_order)\navailable(an_order)\nuser(a_user)\nregistered(a_user)\nlogged(a_user)\n";
		// wn2 = st.getWts().getWTS().get(tmp);
		//
		// wn1.addOutcomingEdge(new NormalEdge(wn1, wn2, "test8_edge"));

		/* -------------------- */

		/**
		 * order(an_order) available(an_order) user(a_user) logged(a_user)
		 * registered(a_user)
		 */
		// 9: new loop

		tmp = "order(an_order)\navailable(an_order)\nuser(a_user)\nuser_data(the_user_data)\nlogged(a_user)\nunregistered(a_user)\ncomplete(the_user_data)\n";
		;
		wn1 = st.getWts().getWTS().get(tmp);
		tmp = "order(an_order)\navailable(an_order)\nuser(a_user)\nuser_data(the_user_data)\n";
		wn2 = st.getWts().getWTS().get(tmp);

		wn1.addOutcomingEdge(new NormalEdge(wn1, wn2, "test9_edge"));

		/* -------------------- */

		/*
		 * Path generation.
		 */
		st.WTS_toPathList(w0);

		/*
		 * Tree generation.
		 */
		st.pathList_toTree();

		/*
		 * Solution finding.
		 */
		st.tree_toSolutionSet();
		SolutionSet solutions = st.getSolutionSet();

		/* Printing */

		System.out.println("digraph{");
		printWTSHashCodeGraphvizAsSubGraph(wts);
		st.printTreeGraphvizAsSubGraph();
		if (solutions != null) {
			for (Solution t : solutions) {
				System.out.println("subgraph cluster_" + t.getID() + "{");
				SolutionTree.printTreeGraphviz(t.getRoot(), st.getTreeSafeNode());
				System.out.println("labelloc=\"t\";\nlabel=\"SOLUTION " + t.getID() + "\"\n" + "}");
			}

		}
		System.out.println("}");
		// st.printTree();

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
