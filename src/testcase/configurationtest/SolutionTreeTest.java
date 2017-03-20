package configurationtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import b2b_cloud_scenario.B2BCloudSetup;
import configuration.SolutionTree;
import pmr.SolutionGraph;
import pmr.graph.EvolutionEdge;
import pmr.graph.NormalEdge;
import pmr.graph.OPNode;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.ProblemExploration;

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
			// System.out.println("ciclo: " + i);
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
		// printGraph(wts);

		HashMap<String, WorldNode> success_nodes = sg.getExitNodeMap();
		WorldNode w0 = b2bcs.getNodewStart();

		w0 = wts.getWTS().get(w0.getWorldState().toString());

		SolutionTree st = new SolutionTree(wts, success_nodes);
		HashMap<String, WorldNode> visited = new HashMap<>();
		st.preliminaryVisit(w0, visited);

		// System.out.println(st.getXorNodes().size() + " xor node(s) found
		// during preliminary visit: ");
		// for (String x : st.getXorNodes().keySet())
		// System.out.println(x);
		//
		// System.out.println(st.getFailureNodes().size() + " failure node(s)
		// found during preliminary visit: ");
		// for (String x : st.getFailureNodes().keySet())
		// System.out.println(x);

		st.WTS_toPathList(w0);
		int i = 1;
		for (ArrayList<String> path : st.getAllPath()) {
			System.out.println("Path " + i++);
			System.out.println("digraph\n{");
			StringBuilder sb = new StringBuilder();
			for (String node : path)
				sb.append("\"" + node + "\"" + "->");
			String tmp = sb.toString().substring(0, sb.toString().length()-2);
			sb = new StringBuilder(tmp);
			sb.append("}");
			System.out.println(sb.toString());
		}

	}

	public void printGraph(WTS wts) {
		Iterator<String> i = wts.getWTS().keySet().iterator();
		System.out.println("\n\nGRAPHVIZ\n\n");
		while (i.hasNext()) {
			String temp = (String) i.next();

			WorldNode w = wts.getWTS().get(temp);

			for (NormalEdge e : w.getOutcomingEdgeList()) {
				System.out.println("\"" + temp + "\"" + " -> " + "\"" + e.getDestination().getWorldState().toString()
						+ "\"" + "[label=\"" + e.getCapability() + "\"][color=green]");
			}

			for (OPNode opNode : w.getOPNodeList()) {
				for (EvolutionEdge ee : opNode.getOutcomingEdge()) {
					System.out
							.println("\"" + temp + "\"" + " -> " + "\"" + ee.getDestination().getWorldState().toString()
									+ "\"" + "[label=\"" + ee.getScenario() + "\"][style=bold][color=red]");
				}
			}
		}
	}
}
