// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.HashMap;
import java.util.HashSet;

import org.icar.musa.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.agent_communication.translator.TranslateError;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.solution_extractor.distributed.Sequences;
import org.icar.musa.solution_extractor.distributed.Solution;

import cartago.*;

@ARTIFACT_INFO(outports = { @OUTPORT(name = "graph") })
public class ConfigSelectorArtifact extends Artifact {
	private Sequences sc;
	
	HashMap<String, String> translation;
	
	private String spec_id_string;

	void init(String spec_id_string) {
		this.sc = new Sequences();
		this.spec_id_string = spec_id_string;
	}

	@OPERATION
	void getSolution() {
		System.out.println("SOLUTION");
		for (Solution x : this.sc.getSolutionsSoFar())
			Sequences.printTree(x.getRoot(), 0);
		// System.out.println("SUCCESS STATES");
		// for (String x : this.sc.getSuccessNodes())
		// System.out.println(x);
		// System.out.println("SEQUENCES");
		// for (ArrayList<String> s : this.sc.getSeqs())
		// System.out.println(s);
		// System.out.println("CAPABILITIES USED");
		// for (Sequences.Triple t : this.sc.getCapabilities())
		// System.out.println(t.toString().replaceAll("\n", ""));
	}

	@LINK @OPERATION
	void notifyENode(String expNodeString) {
		GraphExpansion expNode;
		try {
			expNode = JasonExpansionNode.term_string_to_object(expNodeString);
			String src = expNode.getSource().getWorldState().toString();
			if (src.equals(""))
				src = "w0";
			if (expNode.getDestination().size() > 1) {
				/*
				 * Nodo XOR. "Estraggo" il nodo XOR dal nodo vero e proprio e lo
				 * chiamo "X" + hashCode() dei fatti del mondo dello stato del
				 * mondo di provenienza (devono avere nomi univoci).
				 * 
				 * Aggiungo ogni destinazione. X -> 1, X -> 2, ecc. . Nel caso
				 * in cui qualcuna di queste destinazioni sia un nodo di
				 * successo, viene processato successivamente.
				 */
				this.sc.processEdge(src, "X" + src.hashCode(), expNode.getCapability(), expNode.getAgent());
				HashSet<String> tmp = new HashSet<>();
				for (ExtendedNode d : expNode.getDestination()) {
					String dest = d.getWorldState().toString();
					if (dest.equals(""))
						dest = "w0";
					this.sc.processEdge("X" + src.hashCode(), dest, "", expNode.getAgent());
					if (d.isExitNode())
						tmp.add(d.getWorldState().toString());
				}
				for (String s : tmp) {
					this.sc.processSolution(s);
				}
				tmp.clear();
			} else if (expNode.getDestination().size() == 1) {
				/*
				 * ExpansionNode semplice. Aggiungo dest e se � soluzione ne
				 * tengo conto.
				 */
				String dest = expNode.getDestination().get(0).getWorldState().toString();
				if (dest.equals(""))
					dest = "w0";
				this.sc.processEdge(src, dest, expNode.getCapability(), expNode.getAgent());
				if (expNode.getDestination().get(0).isExitNode())
					this.sc.processSolution(dest);
			}
		} catch (TranslateError e) {
			e.printStackTrace();
		}
	}

	/* interface: MANAGE SOLUTION SEARCH */
	@OPERATION
	void runStep_SearchSolution() {
	}

	/* interface: SOLUTION */
	@OPERATION
	void getAbstractSolution() {
	}

	/* interface: BLACKBOARD */
	@OPERATION
	void markFailedService() {
	}
}
