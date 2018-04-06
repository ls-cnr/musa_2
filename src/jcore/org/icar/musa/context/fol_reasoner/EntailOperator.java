package org.icar.musa.context.fol_reasoner;

import java.util.Iterator;

import org.icar.musa.context.StateOfWorld;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;

import net.sf.tweety.logics.fol.semantics.HerbrandInterpretation;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

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
			response = solver.computeModels(test, 1);
		} catch (SolverException e) {
			e.printStackTrace();
		}

		if (verbose) {
			System.out.println("----models----");
			System.out.println(response.toString());
		}

		if (response != null) {
			if (response.size()>1) System.out.println("warning: many stable models");
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
	
	public boolean[] entailsCondition(StateOfWorld world,  AssumptionSet assumptions, FOLCondition[] conditions) {
		boolean[] results = new boolean[conditions.length];
		for (int i = 0; i<conditions.length; i++ )
			results[i]=false;
		
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
			response = solver.computeModels(test, 1);
		} catch (SolverException e) {
			e.printStackTrace();
		}

		if (verbose) {
			System.out.println("----models----");
			System.out.println(response.toString());
		}

		if (response != null) {
			if (response.size()>1) System.out.println("warning: many stable models");
			AnswerSet as = response.get(0);
			HerbrandInterpretation interpr = new HerbrandInterpretation();

			Iterator<DLPLiteral> it = as.iterator();
			while (it.hasNext()) {
				FolFormula f = tx.toFOL(it.next());
				interpr.add((FOLAtom) f);
			}
			
			for (int i = 0; i<conditions.length; i++ ) {
				FOLCondition cond = conditions[i];
				results[i] = interpr.satisfies(cond.getFOLFormula());
			}
		}
		
		return results;
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
