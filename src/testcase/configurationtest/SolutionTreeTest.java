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
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.ProblemExploration;

public class SolutionTreeTest {

	@Test
	public void testB2BCloud() {
		B2BCloudSetup b2bcs = new B2BCloudSetup();
		SolutionGraph sg = new SolutionGraph();

		ProblemExploration pe = new ProblemExploration(b2bcs.getGoalModel(), b2bcs.getCapabilities(),
				b2bcs.getDomain());

		//b2bcs.getGoalModel().printModel();
		
		sg.getWTS().setInitialState(b2bcs.getNodewStart().getWorldState());
		pe.addToVisit(b2bcs.getNodewStart(), b2bcs.getStartTokens(), 9);

		for (int i = 0; i < 40; i++) {
			System.out.println("ciclo " + i);
			pe.expandNode();
//			for (ENode t : pe.getHighestExpansion().getDestination()) {
//				System.out.println(t.getScore());
//			}
			ExpansionNode en = pe.getHighestExpansion();		
			if (en != null) {
				sg.addNode(en);
				pe.removeExpandedNode(en);
			}
		}

		WTS wts = sg.getWTS();
		wts.printGraph();
		//printGraph(wts.getInitialState(),sg);
		
		System.out.println("Size Exit nodes "+sg.getExitNodeMap().size());
		
//		HashMap<WorldNode, WorldNode> success_nodes = sg.getExitNodeMap();
//		WorldNode w0 = b2bcs.getNodewStart();
//
//		SolutionTree st = new SolutionTree(wts, success_nodes);
//		HashMap<WorldNode, WorldNode> visited = new HashMap<>();
//		st.preliminaryVisit(w0, visited);
//
//		System.out.println("xor: " + st.getXor_nodes().keySet());
//		System.out.println("failure nodes: " + st.getFailure_nodes());
	}

	/* attenzione ai cicli nel grafo --> va in loop */
	private void printGraph(WorldNode w, SolutionGraph sg) {
		int score1 = 0;
		if (sg.getScoreMapping().get(w) != null)
		  score1= sg.getScoreMapping().get(w);

			for( NormalEdge e : w.getOutcomingEdgeList()){
				int score2 = 0;
				if (sg.getScoreMapping().get(e.getDestination()) != null) {
					score2 = sg.getScoreMapping().get(e.getDestination());
				}
				System.out.println("Node"+score1+" -> "+"Node"+score2+"[label=\""+ e.getCapability()+ "\"]");
				printGraph(e.getDestination(),sg);
			}
			
			for( OPNode opNode : w.getOPNodeList()){
				for( EvolutionEdge ee : opNode.getOutcomingEdge()){
					int score2 = 0;
					if (	sg.getScoreMapping().get(ee.getDestination()) != null) {
						score2 = 	sg.getScoreMapping().get(ee.getDestination());
					}
					System.out.println("Node"+score1 + " -> " +"Node"+ score2+" [label=\""+ ee.getScenario()+ "\"][style=bold][color=red]");
					printGraph(ee.getDestination(),sg);
				}
		}
	}
}
