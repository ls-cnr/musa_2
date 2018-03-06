package org.icar.specification.ACLanguage.model;

import org.icar.specification.ACLanguage.CapabilityEntity;

import net.sf.tweety.logics.commons.syntax.interfaces.Term;

public class CapTerm implements CapabilityEntity {
	private Term<?> term;

	public CapTerm(Term<?> term) {
		super();
		this.term = term;
	}

	public Term<?> getTerm() {
		return term;
	}

	public void setTerm(Term<?> term) {
		this.term = term;
	}
	
	
}
