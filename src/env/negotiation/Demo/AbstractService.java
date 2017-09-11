/** Abstract Service demo environment
 *
 * Warning, this Artifact only aims at showcasing
 * required fuctionalities. It is NOT optimised for
 * more than 2 instances, because it relies on
 * JavaOctave, which allows a fast implementation,
 * but it's heavy on memory requirements.
 *
 * Therefore, if for some reason you are going to
 * use this artifact for 3 or more AS, you'll be
 * likely to experience out of memory exceptions.
 *
 * Requirements:
 *
 * 1. GNU octave installed
 * 2. https://github.com/Swissbite/javaoctave
 *    (clone the repository and build the library
 *    with Gradle)
 * 3. Apache commons-logging library
 */

package negotiation.Demo;

import negotiation.AbstractServiceInterface;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.OctaveDouble;

import cartago.Artifact;
import cartago.GUARD;
import cartago.OPERATION;
import cartago.ObsProperty;
import cartago.OpFeedbackParam;
import jacamo.infra.JaCaMoLauncher;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.ListTerm;
import jason.asSyntax.Literal;
import jason.asSyntax.parser.ParseException;
import jason.runtime.RuntimeServicesInfraTier;
import jason.runtime.Settings;

public class AbstractService extends Artifact implements AbstractServiceInterface {

	// use dot instead of comma for decimal separators
	public static final DecimalFormat df = new DecimalFormat("#.##",
	new DecimalFormatSymbols(Locale.US));

	// simplicity assumption: same number of SP for each AS (2 <= n <= 10 in this test)
	private static final int numberOfSP = ThreadLocalRandom.current().nextInt(2, 10 + 1);

	// internal elements
	private String ASname, serviceName;
	private ConcurrentHashMap<String, String> offers;
	private OctaveEngine octave;

	public void init(String ASname, String serviceName, String URI, int deadline) {
		// for the purpose of this test, URI and turns deadline won't be used

		this.serviceName = serviceName;
		this.ASname = ASname;

		offers = new ConcurrentHashMap<String, String>();
		octave = new OctaveEngineFactory().getScriptEngine();

		// add octave scripts from project root relative path
		octave.eval("addpath('./src/env/negotiation/Demo/octave/scripts')");
		// just to be safe
		octave.eval("clear; close all");

		this.defineObsProperty("state", "new");
		this.defineObsProperty("initialisedSP", 0);
	}

	protected void dispose() {
		offers.clear();
		octave.eval("crash_dumps_octave_core(0)");
		octave.close();
		super.dispose();
	}

	@GUARD
	public boolean initialised() {
		return this.getObsProperty("initialisedSP").intValue() == numberOfSP;
	}

	@OPERATION
	public void createProviders(String SCname) {
		/* we'll create a random number of stub SPs, each with a random Cobb-Douglas
		 * indifference plane curve */
	  if (!this.getObsProperty("state").stringValue().equals("new")) {
			log("SP set already created!");
			return;
		}

		// random number generator
		ThreadLocalRandom rng = ThreadLocalRandom.current();

	  RuntimeServicesInfraTier rs = JaCaMoLauncher.getJaCaMoRunner().getRuntimeServices();

		Settings s;
		String idSP = new String();
		String offerStr;
		double u = rng.nextDouble(0.6, 1); // assumption: same utility for all SPs

		try {
			for (int i = 1; i <= numberOfSP; i++) {
				idSP = this.serviceName + "_SP" + i;

				Double[] par = new Double[4]; // Cobb-Douglas curve parameters

				// u and these params are chosen to make the curve lie in [0,1] hypercube
				par[0] = rng.nextDouble(0.2, 1); // alpha
				par[1] = rng.nextDouble(0.2, 1); // beta
				par[2] = rng.nextDouble(5, 7.5); // gamma
				par[3] = u;

				octave.eval(String.format("SP{%d} = icurve(%s, %s, %s, %s);", i,
				df.format(par[0]), df.format(par[1]), df.format(par[2]), df.format(par[3])));
				octave.eval(String.format("ridx = randi(size(SP{%d}.x, 2));", i));
				octave.eval(String.format("offer = [SP{%d}.x(ridx) SP{%d}.y(ridx)];", i, i));

				offerStr = Arrays.toString(octave.get(OctaveDouble.class, "offer").getData());

				/* give this SP Cobb-Douglas parameters as initial belief base, and
				 * make it join AS workspace and its negotiation group */
				s = new Settings();
				s.addOption(Settings.INIT_BELS, String.format(bb, Arrays.toString(par),
				Integer.toString(i), this.serviceName, ASname, SCname, offerStr));
				rs.createAgent(idSP, "service_provider.asl", null, null, null, s, null);
				rs.startAgent(idSP);
		  }
		} catch (Exception e) {
				this.getObsProperty("state").updateValue("aborted");
				log("Error instantiating " + idSP);
				e.printStackTrace();
		}

		await("initialised");

		this.getObsProperty("state").updateValue("initialised");
		int sps = this.getObsProperty("initialisedSP").intValue();
		log("initialised with " + sps + " SPs");
	}

	@OPERATION
	public void getNumberOfSP(OpFeedbackParam<Integer> numberOfSP) {
		numberOfSP.set(AbstractService.numberOfSP);
	}

	@OPERATION
	public void getStandingOffers(OpFeedbackParam<Literal[]> offersList) {
		Literal[] aux = new Literal[offers.size()];
		int i = 0;

		for (String sp : offers.keySet()) {
			try {
				aux[i++] = ASSyntax.createLiteral("offer", ASSyntax.parseTerm(sp),
				ASSyntax.parseList(offers.get(sp)));
			} catch (ParseException e) {
				log("failing to parse standing offer from SP " + sp);
				e.printStackTrace();
		  }
		}

		offersList.set(aux);
		offers.clear();
	}

	@OPERATION
	public void providerInitialised() {
		ObsProperty p = this.getObsProperty("initialisedSP");
		p.updateValue(p.intValue() + 1);
	}

	@OPERATION
	public void sendOffer(String sp, String offer) {
		offers.put(sp, offer);
	}

	@OPERATION
	public void adjustOffer(String refPoint, String id, String paramsStr,
	OpFeedbackParam<ListTerm> newOffer) {
		// in case you need to extract paramsStr
		// paramsStr = paramsStr.replaceAll("[\\p{Z}\\s]+", "");
		// String[] par = paramsStr.substring(1, paramsStr.length() - 1).split(",");

		// we already saved indifference curves in createProviders operation
		octave.eval(String.format("offer = distance2curve([SP{%s}.x; SP{%s}.y]', %s);",
		id, id, refPoint));
		double[] offer = octave.get(OctaveDouble.class, "offer").getData();

		try {
			newOffer.set(ASSyntax.parseList(Arrays.toString(offer)));
		} catch (ParseException e) {
			log("failed to parse this offer: " + offer);
			e.printStackTrace();
		}
	}

}
