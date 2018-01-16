package org.icar.specification.LTLgoal.model;

import org.icar.specification.LTLgoal.GoalModelEntity;

public class LTLPredicate extends LTLFormula implements GoalModelEntity {
	private String predicate;

	public LTLPredicate(String predicate) {
		super();
		this.predicate = predicate;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	@Override
	public String toString() {
		return "P [predicate=" + predicate + "]";
	}
	
	
	
}
