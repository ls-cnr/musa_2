package org.icar.musa.core.fol_reasoner;

import org.icar.musa.core.Condition;

import net.sf.tweety.commons.Formula;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;

/**                                                                            
 * This class encapsulate a Condition, i.e. a logic formula to be used in Goals and Capabilities.  
 * for opportunity reasons the formula is encoded in First Order Logic, i.e. it is a subclass of FolFormula
 * however the class also accepts ASP atoms, i.e. formulas in the form of DLPAtom
 * @author icar-aose
 * @version 1.0.0
 */

public class FOLCondition implements Condition {
	private FolFormula formula;
	
	private static AspFolTranslator tx = new AspFolTranslator();
	
	public FOLCondition(FolFormula folFormula) {
		formula = folFormula;
	}
	public FOLCondition(DLPLiteral source) {
		formula = tx.toFOL(source);
	}
	public FOLCondition(String folFormula) {
		/* TODO */
	}

	public Formula getFOLFormula() {
		return formula;
	}
	
	public String toString() {
		return formula.toString();
	}
	
}
