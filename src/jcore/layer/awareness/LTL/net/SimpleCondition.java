package layer.awareness.LTL.net;

import layer.semantic.Condition;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;

public class SimpleCondition extends TransitionCondition {

	private Condition cond;
	
	public SimpleCondition(String term) {
		super(term);
		//generare condition
//		FOLAtom THO_processed = new FOLAtom( new Predicate(name, 1) );
//		THO_processed.addArgument(doc);
//		Condition THO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(THO_processed, THO_order), doc));
	}

	@Override
	public void setStateCondition(String s) {
		String term = this.getTerm();
		if( s.equals("A") ) ; //accettare term
		else if( s.equals("E") ); //rifiutare term
	}
	
	public Condition getCondition() {
		return cond;
	}

}
