package configurationtest;

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
		pe.addToVisit(b2bcs.getNodewStart(), b2bcs.getStartTokens(), 9);

		for (int i = 0; i < 40; i++) {
			// System.out.println("ciclo: " + i);
			pe.expandNode();
			ExpansionNode en = pe.getHighestExpansion();
			if (en != null) {
				sg.addNode(en);
				pe.removeExpandedNode(en);
			}
		}

		WTS wts = sg.getWTS();

		// Iterator<WorldNode> i = wts.getWTS().keySet().iterator();
		// printGraph(i.next());

		HashMap<WorldNode, WorldNode> success_nodes = sg.getExitNodeMap();
		WorldNode w0 = b2bcs.getNodewStart();

		SolutionTree st = new SolutionTree(wts, success_nodes);

		HashMap<String, WorldNode> visited = new HashMap<>();
		visited.put(w0.getWorldState().toString(), w0);// da togliere
		st.preliminaryVisit(wts.getWTS().get(w0), visited);

		System.out.println(">xorNodes found: " + st.getXorNodesString().size());
		for (String x : st.getXorNodesString().keySet())
			System.out.println(x);

		System.out.println(">failureNodes found: " + st.getFailureNodesString().size());
		for (String x : st.getFailureNodesString().keySet())
			System.out.println(x);

		System.out.println(">successNodes found: " + st.getSuccessNodesString().size());
		for (String x : st.getSuccessNodesString().keySet())
			System.out.println(x);

	}

	public void printGraph(WorldNode w0) {
		if (w0 == null)
			return;

		if (w0.getOPNodeList().isEmpty() == false)
			System.out.println("\"" + w0.getWorldState() + "\"[style=bold][color=yellow]");
		for (NormalEdge e : w0.getOutcomingEdgeList()) {
			System.out.println("\"" + w0.getWorldState() + "\"" + " -> " + "\"" + e.getDestination().getWorldState()
					+ "\" [label=\"" + e.getCapability() + "\"][style=bold][color=green]");
			printGraph(e.getDestination());
		}

		for (OPNode opNode : w0.getOPNodeList()) {
			for (EvolutionEdge ee : opNode.getOutcomingEdge()) {
				System.out
						.println("\"" + w0.getWorldState() + "\"" + " -> " + "\"" + ee.getDestination().getWorldState()
								+ "\" [label=\"" + ee.getScenario() + "\"][style=bold][color=red]");
				printGraph(ee.getDestination());
			}
		}
	}

}
