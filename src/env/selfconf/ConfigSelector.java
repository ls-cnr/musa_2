// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.HashMap;
import java.util.HashSet;

import cartago.*;
import configuration.distributed.Sequences;
import configuration.distributed.Solution;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import translator.JasonExpansionNode;
import translator.TranslateError;

@ARTIFACT_INFO(outports = { @OUTPORT(name = "graph") })
public class ConfigSelector extends Artifact {
	private Sequences sc;
	HashMap<String, String> translation;
	@SuppressWarnings("unused")
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

	@OPERATION
	void notifyENode(String expNodeString) {
		ExpansionNode expNode;
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
				this.sc.processEdge(src, "X" + src.hashCode(), expNode.getCapability());
				HashSet<String> tmp = new HashSet<>();
				for (ENode d : expNode.getDestination()) {
					String dest = d.getWorldState().toString();
					if (dest.equals(""))
						dest = "w0";
					this.sc.processEdge("X" + src.hashCode(), dest, "");
					if (d.isExitNode())
						tmp.add(d.getWorldState().toString());
				}
				for (String s : tmp) {
					this.sc.processSolution(s);
				}
				tmp.clear();
			} else if (expNode.getDestination().size() == 1) {
				/*
				 * ExpansionNode semplice. Aggiungo dest e se è soluzione ne
				 * tengo conto.
				 */
				String dest = expNode.getDestination().get(0).getWorldState().toString();
				if (dest.equals(""))
					dest = "w0";
				this.sc.processEdge(src, dest, expNode.getCapability());
				if (expNode.getDestination().get(0).isExitNode())
					this.sc.processSolution(dest);
			}
		} catch (TranslateError e) {
			// TODO Auto-generated catch block
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
