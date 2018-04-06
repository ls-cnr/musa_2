package org.icar.specification.ACLanguage.specmodel;

import java.util.LinkedList;
import java.util.List;

import org.icar.specification.ACLanguage.CapabilityEntity;

import net.sf.tweety.logics.commons.syntax.interfaces.Term;


public class CapConditionTerms implements CapabilityEntity {
	private List<Term<?>> terms;

	public CapConditionTerms() {
		super();
		terms = new LinkedList<Term<?>>();
	}

	public List<Term<?>> getTerms() {
		return terms;
	}
	
	
	
	
}
