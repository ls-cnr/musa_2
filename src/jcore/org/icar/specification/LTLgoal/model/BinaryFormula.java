package org.icar.specification.LTLgoal.model;

import org.icar.specification.LTLgoal.GoalModelEntity;

public class BinaryFormula extends LTLFormula implements GoalModelEntity {
	public static final int UNTIL=0;
	public static final int RELEASE=1;
	public static final int AND=2;
	public static final int OR=3;
	public static final int IF=4;
	public static final int IFF=5;
	
	private int type;
	private LTLFormula left;
	private LTLFormula right;
	
	public BinaryFormula(int type, LTLFormula left, LTLFormula right) {
		super();
		this.type = type;
		this.left = left;
		this.right = right;
	}
	
	public boolean isUntil() {
		return type==UNTIL;
	}
	
	public boolean isRelease() {
		return type==RELEASE;
	}

	public boolean isAnd() {
		return type==AND;
	}
	
	public boolean isOr() {
		return type==OR;
	}

	public boolean isIf() {
		return type==IF;
	}

	public boolean isIff() {
		return type==IFF;
	}

	public int getType() {
		return type;
	}

	public LTLFormula getLeft() {
		return left;
	}

	public LTLFormula getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "[" + type() + ", left=" + left + ", right=" + right + "]";
	}
	
	private String type() {
		if (type==UNTIL) return "U";
		if (type==RELEASE) return "R";
		if (type==AND) return "and";
		if (type==OR) return "or";
		if (type==IF) return "->";
		if (type==IFF) return "<->";
		return "undef";
	}

}
