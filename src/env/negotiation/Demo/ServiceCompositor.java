/** Service Compositor demo environment
 *
 * Warning, this Artifact only aims at showcasing
 * required fuctionalities. It is NOT intended for
 * productive use, because it relies on JavaOctave,
 * which allows a fast implementation, but it's
 * heavy on memory requirements.
 *
 * Requirements:
 *
 * 1. GNU octave installed
 * 2. https://github.com/Swissbite/javaoctave
 *    (clone the repository and build the library with Gradle)
 * 3. Apache commons-logging library
 */

package negotiation.Demo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.OctaveDouble;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.ListTerm;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.asSyntax.parser.ParseException;
import negotiation.ServiceCompositorInterface;

public class ServiceCompositor extends Artifact implements ServiceCompositorInterface {

	// use dot instead of comma for decimal separators
	public static DecimalFormat df = new DecimalFormat("#.##",
	new DecimalFormatSymbols(Locale.US));

	// internal elements
	private String[] QoS;
	private double[] C;
	private int turn;
	private ArrayList<String> AS;
	private int lastCondition = 0;
	private OctaveEngine octave;

	private static class OctaveException extends Exception {
		private static final long serialVersionUID = 3680006476213781709L;
		public OctaveException(String msg) {
			super(msg);
		}
	}

	public void init(Object[] QoS, Object[] C, int deadline) {
		this.QoS = Arrays.copyOf(QoS, QoS.length, String[].class);
		this.C = new double[C.length];
		for (int i = 0; i < C.length; i++)
			this.C[i] = Double.parseDouble(C[i].toString());
		this.turn = 1;

		octave = new OctaveEngineFactory().getScriptEngine();

		// add octave scripts from project root relative path
		octave.eval("addpath('./src/env/negotiation/Demo/octave/scripts')");
		// just to be safe
		octave.eval("clear; close all");
		// remaining issues vector over turns
		octave.eval(String.format("R = zeros(%d, %d);", deadline, QoS.length));
	}

	protected void dispose() {
		octave.eval("crash_dumps_octave_core(0)");
		octave.close();
		super.dispose();
	}

	@OPERATION
	public void currentStatus(String offers,
	OpFeedbackParam<Literal[]> bestOffersPackage,
  OpFeedbackParam<ListTerm> remainingIssues,
	OpFeedbackParam<Integer> agreement,
  OpFeedbackParam<Integer> paretoOptimalAgreement,
	OpFeedbackParam<Integer> stoppingCondition) {

		/* internal maps for storing AS-SP pair for each retrieved offer
		 * (because they arrive asynchronously) */
		ArrayList<ArrayList<String>> ASvalues = new ArrayList<ArrayList<String>>();
		// like above, but global because it's used in calculateRefPoint too
		AS = new ArrayList<String>();

		// vectors parsed from Octave
		double[] bestOfferIndex = null, bestOfferPkg1d = null;

		try {
			// construct offers matrix
			ListTerm offersSet = ASSyntax.parseList(offers);
			int m = QoS.length;
			int n = offersSet.size();
			int k = ((ListTerm) ((Structure) offersSet.get(0)).getTerm(1)).size();
			// simplicity assumption: same number of SPs for each AS
			octave.eval(String.format("x = zeros(%d, %d, %d);", k, m, n));
			int i = 1, j; // in Matlab/Octave, indexes start from 1
			for (Term t : offersSet) {
				Structure s = (Structure) t;
				ListTerm offersAS = (ListTerm) s.getTerm(1);
				j = 1;
				ArrayList<String> spnames = new ArrayList<String>();
				for (Term t2 : offersAS) {
					Structure s2 = (Structure) t2;
					octave.eval(String.format("x(%d, :, %d) = %s;", j++, i,
					s2.getTerm(1).toString()));
					spnames.add(s2.getTerm(0).toString());
				}
				String asname = s.getTerm(0).toString();
				ASvalues.add(spnames);
				AS.add(asname);
				i++;
			}

			// define best offers package
			octave.eval("b = seloffer(x);"); // indexes
			octave.eval("best_offer = squeeze(max(x(b,:,:)))';"); // package
			// Octave makes b a double for safety
			bestOfferIndex = octave.get(OctaveDouble.class, "b").getData();
			bestOfferPkg1d = octave.get(OctaveDouble.class, "best_offer").getData();

			if (bestOfferIndex.length == 0 || bestOfferPkg1d.length == 0)
				throw new OctaveException("JavaOctave: error parsing best offer package");

			ListTerm[] baux = new ListTerm[bestOfferPkg1d.length / m];
			for (i = 1; i <= bestOfferPkg1d.length / m; i++)
				baux[i - 1] = ASSyntax.parseList(Arrays.toString(Arrays.copyOfRange(bestOfferPkg1d, i * m - m, i * m)));

			Term[] taux = new Literal[baux.length];
			i = 0;
			System.out.print("[sc_env] best offers package is [");
			for (ArrayList<String> spnames : ASvalues) {
				if (i > 0)
					System.out.print(", ");
				if (i < bestOfferIndex.length) {
					// Matlab/Octave index minus 1
					String s = spnames.get(((int) bestOfferIndex[i]) - 1);
					taux[i++] = ASSyntax.parseTerm(s);
					System.out.print(s);
				}
			}
			System.out.print("] = [(");
			for (i = 0; i < bestOfferPkg1d.length; i++) {
				System.out.print(df.format(bestOfferPkg1d[i]));
				if ((i + 1) % m == 0 && i + 1 < bestOfferPkg1d.length) {
					System.out.print("), (");
				} else if ((i + 1) % m == 0)
					System.out.print(")");
				else
					System.out.print(", ");
			}
			System.out.print("]");

			Literal[] bo = new Literal[taux.length];
			for (i = 0; i < taux.length; i++)
				bo[i] = ASSyntax.createLiteral("provider", ASSyntax.parseTerm(AS.get(i)), taux[i], baux[i]);
			bestOffersPackage.set(bo);

			// compute remaining issues vector
			octave.eval(String.format("C = %s;", Arrays.toString(C)));
			octave.eval(String.format("R(%d, :) = C - sum(best_offer); Rnow = R(%d, :);",
			this.turn, this.turn));
			double[] R = octave.get(OctaveDouble.class, "Rnow").getData();
			remainingIssues.set(ASSyntax.parseList(Arrays.toString(R)));
			System.out.print("\n[sc_env] R = [");
			for (i = 0; i < R.length; i++) {
				if (i > 0)
					System.out.print(", ");
				System.out.print(R[i]);
			}
			System.out.println("]");

			// verify agreement, Pareto-optimality and stopping criteria
			octave.eval(String.format("[status, stop] = checkagr(R, C, best_offer, %d); results = [status, stop];", this.turn));
			double[] results = octave.get(OctaveDouble.class, "results").getData(); // double like b

			String resultsStr = "[sc_env] ";
			switch ((int) results[0]) {
			case -1:
				resultsStr += "no agreement";
				lastCondition = 0;
				agreement.set(0);
				paretoOptimalAgreement.set(0);
				break;
			case 0:
				if (lastCondition == 0) {
					resultsStr += "no agreement due to Pareto-dominance violation";
					agreement.set(0);
					paretoOptimalAgreement.set(0);
					break;
				}
			case 1:
				resultsStr += "agreement";
				lastCondition = 1;
				agreement.set(1);
				paretoOptimalAgreement.set(0);
				break;
			case 2:
				resultsStr += "near Pareto-optimal agreement";
				lastCondition = 2;
				agreement.set(1);
				paretoOptimalAgreement.set(1);
				// assumption: near-PO is preferable, because faster than PO
				break;
			case 3:
				resultsStr += "Pareto-optimal agreement";
				lastCondition = 2;
				agreement.set(1);
				paretoOptimalAgreement.set(1);
				break;
			default:
				resultsStr += "status unknown";
				agreement.set(0);
				paretoOptimalAgreement.set(0);
				break;
			}

			if ((int) results[1] == 0) {
				stoppingCondition.set(0);
			} else {
				resultsStr += ", stopping condition met";
				stoppingCondition.set(1);
			}

			System.out.println(resultsStr);
			this.turn += this.turn + 1;
		} catch (ParseException e) {
			log("error while parsing offers");
			e.printStackTrace();
		} catch (OctaveException e) {
			if (bestOfferIndex.length == 0)
				log("error while parsing bestOfferIndex vector");
			if (bestOfferPkg1d.length == 0)
				log("error while parsing bestOfferPkg1d vector");
			e.printStackTrace();
		}

	}

	@OPERATION
	public void calculateRefPoint(String ASname, OpFeedbackParam<ListTerm> refPoint) {
		// C and best_offer have already been defined in Octave by previous operation
		octave.eval(String.format("rp = C - sum(best_offer) + best_offer(%d, :);",
		AS.indexOf(ASname) + 1));

		double[] rp = octave.get(OctaveDouble.class, "rp").getData();

		try {
			refPoint.set(ASSyntax.parseList(Arrays.toString(rp)));
		} catch (ParseException e) {
			log("error while parsing reference point for AS " + ASname);
			e.printStackTrace();
		}
	}

}
