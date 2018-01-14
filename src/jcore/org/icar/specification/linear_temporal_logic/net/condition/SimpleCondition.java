package org.icar.specification.linear_temporal_logic.net.condition;

import java.util.ArrayList;

import org.icar.musa.core.Condition;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.specification.linear_temporal_logic.net.PNStateEnum;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.logics.fol.syntax.RelationalFormula;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;

/**
 * This Class is used for a Simple Condition, that is a First Order Logic Proposition to check if it's true or false in the system domain.
 */
public class SimpleCondition extends TransitionCondition {

	/** The condition that must be entailed in the system domain */
	private Condition cond;
	
	/** The default predicate */
	private DLPLiteral defaultPred;
	
	/** The negated predicate */
	private DLPLiteral negPred;
	
	/** The state condition that indicates if the formula must be accepted or error */
	private PNStateEnum stateCondition;
	
	/** The negation condition of predicate */
	private boolean neg;
	
	/** The predicate */
	private String pred;
	
	/** The args. */
	private ArrayList<String> args;
	
	/**
	 * Instantiates a new simple condition.
	 *
	 * @param term
	 *            the term
	 * @param pred
	 *            the pred
	 * @param args
	 *            the args
	 * @param neg
	 *            the neg
	 */
	public SimpleCondition(String term, String pred, ArrayList<String> args, boolean neg) {
		super(term);
		stateCondition = PNStateEnum.ACCEPTED;
		this.pred = pred;
		this.args = args;
		this.neg = neg;
		
//		FOLAtom atom = new FOLAtom( new Predicate(pred, args.size()) );
//		HashSet<Variable> argSet = new HashSet<>();
//		for( String s : args ){
//			Variable tmp = new Variable(s);
//			atom.addArgument(tmp);
//			argSet.add(tmp);
//		} 
//		
//		//cond = new Condition(atom); 
//		cond = new Condition( new ExistsQuantifiedFormula(atom, argSet) );
		
		ArrayList<Constant> cs = new ArrayList<>();
		for( String s : args )
			cs.add(new Constant(s));
		
		if(neg){
			defaultPred = new DLPNeg(new DLPAtom(pred, cs));
			negPred = new DLPAtom(pred, cs);
		}
		else{
			defaultPred = new DLPAtom(pred, cs);
			negPred = new DLPNeg(new DLPAtom(pred, cs));
		}
		
		cond = new FOLCondition(defaultPred);
	}
	
	/**
	 * Instantiates a copy of a simple condition.
	 *
	 * @param copy
	 *            the copy
	 */
	public SimpleCondition( SimpleCondition copy ) {
		this(copy.getTerm(), copy.pred, copy.args, copy.neg);
	}

	/* (non-Javadoc)
	 * @see layer.awareness.LTL.net.condition.TransitionCondition#setStateCondition(java.lang.String)
	 */
	@Override
	public void setStateCondition(PNStateEnum s) {
		if( s == PNStateEnum.ERROR ){
			//cond = new Condition( new Negation((ExistsQuantifiedFormula) cond.getFOLFormula()) );
			//cond = new Condition(new Negation( (RelationalFormula) cond.getFOLFormula()));
			cond = new FOLCondition(negPred);
			stateCondition = s;
			if( !getTerm().startsWith("!") )
				setTerm("!" + getTerm());
		}
		else if( s == PNStateEnum.ACCEPTED ){
			//cond = new Condition( new Negation((ExistsQuantifiedFormula) cond.getFOLFormula()) );
			//cond = new Condition(new Negation( (RelationalFormula) cond.getFOLFormula()));
			cond = new FOLCondition(defaultPred);
			stateCondition = s;
			if( getTerm().startsWith("!") )
				setTerm(getTerm().substring(1));
		}
	}
	
	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public Condition getCondition() {
		return cond;
	}
	
	/**
	 * Gets the state condition.
	 *
	 * @return the state condition
	 */
	public PNStateEnum getStateCondition() {
		return stateCondition;
	}

}
