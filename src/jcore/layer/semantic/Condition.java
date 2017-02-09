package layer.semantic;

import net.sf.tweety.commons.Formula;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

/**                                                                            
 * This class encapsulate a Condition, i.e. a logic formula to be used in Goals and Capabilities.  
 * for opportunity reasons the formula is encoded in First Order Logic, i.e. it is a subclass of FolFormula
 * however the class also accepts ASP atoms, i.e. formulas in the form of DLPAtom
 * @author icar-aose
 * @version 1.0.0
 */

public class Condition {
	private FolFormula formula;
	
	private static AspFolTranslator tx = new AspFolTranslator();
	
	public Condition(FolFormula folFormula) {
		formula = folFormula;
	}
	public Condition(DLPAtom atom) {
		formula = tx.toFOL(atom);
	}
	public Condition(String folFormula) {
		/* TODO */
	}

	public Formula getFOLFormula() {
		return formula;
	}
	
	public String toString() {
		return formula.toString();
	}
	
}
