package org.icar.musa.core.fol_reasoner;

import java.util.Iterator;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

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

/**
 * The Class DomainEntail.
 * @author icar-aose
 */

/* pattern Singleton */
public class EntailOperator {
	
	/** The instance. */
	private static EntailOperator instance=null;

	/** The path 2 dvl. */
	private final String path2dvl;
	
	/** The solver. */
	private  DLV solver;	
	
	/** The tx. */
	private AspFolTranslator tx;
	
	private boolean verbose = false;
	
	/**
	 * Instantiates a new domain entail.
	 */
	private EntailOperator() {
		path2dvl = setPath();
		solver = new DLV(path2dvl);
		tx = new AspFolTranslator();
	}
	
	/**
	 * Gets the single instance of DomainEntail.
	 *
	 * @return single instance of DomainEntail
	 */
	public static EntailOperator getInstance() {
		if (instance==null) {
			instance = new EntailOperator();
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
	public boolean entailsCondition(StateOfWorld world,  AssumptionSet assumptions, FOLCondition condition) {
		Program test = assumptions.getASPClone();
		Iterator<ExtDLPHead> fact_it = world.getFacts().iterator();
		while (fact_it.hasNext()) {
			test.addFact(fact_it.next());
		}
		
		if (verbose) {
			System.out.println("----program----");
			System.out.println(test.toString());
		}
		
		AnswerSetList response = null;
		try {
			response = solver.computeModels(test, 10);
		} catch (SolverException e) {
			e.printStackTrace();
		}

		if (verbose) {
			System.out.println("----models----");
			System.out.println(response.toString());
		}

		if (response != null) {
			AnswerSet as = response.get(0);
			HerbrandInterpretation interpr = new HerbrandInterpretation();

			Iterator<DLPLiteral> it = as.iterator();
			while (it.hasNext()) {
				FolFormula f = tx.toFOL(it.next());
				interpr.add((FOLAtom) f);
			}
			
			boolean reply = interpr.satisfies(condition.getFOLFormula());

			if (verbose) {
				System.out.println("----reply----");
				System.out.print(condition.getFOLFormula().toString());
				System.out.println(" => "+reply);
			}
			
			return reply;
		}
		return false; // no stable model
	}
	
	/**
	 * This method returns the right path for DVL program considering the OS. 
	 * @return path
	 */
	private String setPath(){
		if( System.getProperty("os.name").startsWith("Windows") )
			return "./ext/dlv.mingw.exe";
		else //TODO considering other OS, such as Mac OS or Linux based OS
			return "./ext/dlv.i386-apple-darwin.bin";
	}
	
	public void setVerbose(boolean flag) {
		verbose = flag;
	}
	
}
