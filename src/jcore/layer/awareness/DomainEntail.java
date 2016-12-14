package layer.awareness;

import java.util.Iterator;

import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.syntax.Rule;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;
import net.sf.tweety.logics.fol.semantics.HerbrandInterpretation;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPElement;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;

// TODO: Auto-generated Javadoc

/**
 * The Class DomainEntail.
 * @author icar-aose
 * @version 1.0.0
 * @author Javadoc author Fontana Alessandro
 */

/* pattern Singleton */
public class DomainEntail {
	
	/** The instance. */
	private static DomainEntail instance=null;

	/** The path 2 dvl. */
	private final String path2dvl = "./ext/dlv.mingw.exe";
	
	/** The solver. */
	private  DLV solver;	
	
	/** The tx. */
	private AspFolTranslator tx;
	
	/**
	 * Instantiates a new domain entail.
	 */
	private DomainEntail() {
		solver = new DLV(path2dvl);
		tx = new AspFolTranslator();
	}
	
	/**
	 * Gets the single instance of DomainEntail.
	 *
	 * @return single instance of DomainEntail
	 */
	public static DomainEntail getInstance() {
		if (instance==null) {
			instance = new DomainEntail();
		}		
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
	public boolean entailsCondition(StateOfWorld world,  AssumptionSet assumptions, Condition condition) {
		Program test = assumptions.getASPClone();
		Iterator<DLPHead> fact_it = world.getFacts().iterator();
		while (fact_it.hasNext()) {
			test.addFact(fact_it.next());
		}
		
		AnswerSetList response = null;
		try {
			response = solver.computeModels(test, 10);
		} catch (SolverException e) {
			e.printStackTrace();
		}
		
		if (response != null) {
			AnswerSet as = response.get(0);
			HerbrandInterpretation interpr = new HerbrandInterpretation();

			Iterator<DLPLiteral> it = as.iterator();
			while (it.hasNext()) {
				FolFormula f = tx.toFOL(it.next());
				interpr.add((FOLAtom) f);
			}
			
			return interpr.satisfies(condition.getFOLFormula());

		}
		return false; // no stable model
	}
	
}
