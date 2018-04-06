package org.icar.musa.context.fol_reasoner;

import java.util.LinkedList;
import java.util.List;

import org.icar.musa.runtime_entity.Condition;

import net.sf.tweety.commons.Formula;
import net.sf.tweety.logics.commons.syntax.Constant;
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
	public FOLCondition(String pred) {
		/* assumption: string is in the form functor ( term1, term2, ) */
		pred = pred.replaceAll("\\s+","");
		//System.out.println("Converting: ["+pred+"]");
		boolean functor = false;
		String myFunctor = "";
		int pos = 0;
		List<Constant> terms = new LinkedList<Constant>();
		String myTerm = "";

		while (pos<pred.length()) {
			char c = pred.charAt(pos);
			if (functor==false) {
				
				if (c=='(') {
					functor=true;
				} else {
					myFunctor = myFunctor+c;
				}
				
			} else if (functor==true) {
				
				if (c==',') {
					terms.add(new Constant(myTerm));
					myTerm=new String("");
				} else if (c==')') {
					terms.add(new Constant(myTerm));
				} else {
					myTerm = myTerm+c;
				}
				
			}
			
			pos++;
		}
		
		formula =  tx.toFOL(new DLPAtom(myFunctor, terms));
		
	}

	public Formula getFOLFormula() {
		return formula;
	}
	
	public String toString() {
		return formula.toString();
	}
	
	public static void main(String[] args) {
		FOLCondition cond = new FOLCondition("test(fe,ga, yt)");
		System.out.println(cond.toString());
		
	}
	
}
