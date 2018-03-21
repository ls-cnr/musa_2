package org.icar.ltlpetrinet.annotated_pn;

import petrinet.logic.Transition;

public abstract class AnnotatedTransition extends Transition {
	private boolean negated = false;
	
	public final static boolean INVERSE = true;
	public final static boolean NORMAL = false;

	protected AnnotatedTransition(String name) {
		super(name);
	}
	
	protected AnnotatedTransition(String name, boolean inverse) {
		super(name);
		negated = inverse;
	}
	
	public boolean isNormal() {
		return negated==NORMAL;
	}

	public boolean isInverse() {
		return negated==INVERSE;
	}
}
