package layer.awareness;

import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.StateOfWorld;
import net.sf.tweety.logics.fol.semantics.HerbrandInterpretation;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSetList;
import translator.ExtDLPHead;

/**
 * The singleton class DomainEntail.
 *
 * @author icar-aose
 */
public class DomainEntail {

	/* The instance. */
	private static DomainEntail instance = null;

	/* The path to dvl. */
	private final String path2dvl;

	/* The solver. */
	private DLV solver;

	/* The tx. */
	private AspFolTranslator tx;

	/**
	 * Instantiates a new domain entail.
	 */
	private DomainEntail() {
		path2dvl = setPath();
		solver = new DLV(path2dvl);
		tx = new AspFolTranslator();
	}

	/**
	 * Gets the single instance of DomainEntail.
	 *
	 * @return single instance of DomainEntail
	 */
	public static DomainEntail getInstance() {
		if (instance == null)
			instance = new DomainEntail();
		return instance;
	}

	/**
	 * Entails condition.
	 *
	 * @param world
	 *            the world
	 * @param assumptions
	 *            the assumptions
	 * @param condition
	 *            the condition
	 * @return true, if successful
	 */
	public boolean entailsCondition(StateOfWorld world, AssumptionSet assumptions, Condition condition) {
		Program test = assumptions.getASPClone();
		AnswerSetList response = null;

		for (ExtDLPHead fact : world.getFacts())
			test.addFact(fact);

		try {
			response = solver.computeModels(test, 10);
		} catch (SolverException e) {
			e.printStackTrace();
		}

		if (response != null) {
			HerbrandInterpretation interpr = new HerbrandInterpretation();

			for (DLPLiteral lit : response.get(0))
				interpr.add((FOLAtom) tx.toFOL(lit));

			return interpr.satisfies(condition.getFOLFormula());
		}

		return false; // no stable model
	}

	/**
	 * This method returns the right path for DVL program considering the OS.
	 *
	 * @return path
	 */
	private String setPath() {
		try {
			String os = System.getenv("os.name");

			if (os.startsWith("Windows"))
				return "./ext/dlv.mingw.exe";
			else if (os.startsWith("Linux")) {
				if (System.getProperty("os.arch").endsWith("i386"))
					return "./ext/dlv.i386-linux-elf-static.bin";
				else
					return "./ext/dlv.x86-64-linux-elf-static.bin";
			} else if (os.startsWith("Mac"))
				return "./ext/dlv.i386-apple-darwin.bin";
			else
				throw new Exception("Operating System not supported by DLV");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}