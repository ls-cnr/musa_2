package org.icar.specification.LTLgoal.specmodel;

import org.icar.specification.LTLgoal.GoalModelEntity;

public class UnaryFormula extends LTLFormula implements GoalModelEntity {
	public static final int GLOBALLY=0;
	public static final int FINALLY=1;
	public static final int NEXT=2;
	public static final int NOT=3;
	
	private int type;
	private LTLFormula subformula;
	
	public UnaryFormula(int type, LTLFormula subformula) {
		super();
		this.type = type;
		this.subformula = subformula;
	}

	public boolean isGlobally() {
		return type==GLOBALLY;
	}

	public boolean isFinally() {
		return type==FINALLY;
	}

	public boolean isNext() {
		return type==NEXT;
	}

	public boolean isNot() {
		return type==NOT;
	}

	public int getType() {
		return type;
	}

	public LTLFormula getSubformula() {
		return subformula;
	}

	@Override
	public String toString() {
		return "[" + type() + ", subformula=" + subformula + "]";
	}
	
	private String type() {
		if (type==GLOBALLY) return "G";
		if (type==FINALLY) return "F";
		if (type==NEXT) return "X";
		if (type==NOT) return "!";
		return "undef";
	}
	
	
	
}
