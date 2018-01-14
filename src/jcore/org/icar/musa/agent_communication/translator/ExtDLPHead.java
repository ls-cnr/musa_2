package communication.translator;

import net.sf.tweety.logics.commons.syntax.AssociativeFormulaSupport;
import net.sf.tweety.logics.commons.syntax.interfaces.AssociativeFormula;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class ExtDLPHead extends DLPHead{
	
	
	public ExtDLPHead(DLPLiteral head){
		super(head);
	}
	
	@Override
	public boolean equals(Object other) {
			AssociativeFormula<?> cast = (AssociativeFormula<?>)other;
			if(this.toString().equals(cast.toString()) == true)
				return true;
			else
				return false;
	}
	
	@Override
	public int hashCode() {
			return this.getSignature().hashCode();
	}
	
}
