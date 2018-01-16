package org.icar.specification.LTLgoal.model;

import org.icar.musa.core.Requirements;
import org.icar.specification.LTLgoal.GoalModelEntity;
import org.icar.specification.LTLgoal.LTLPriority;

public class LTLGoal implements GoalModelEntity, Requirements {
	private String name;
	private LTLFormula formula;
	private LTLPriority priority;
	
	public LTLGoal(String name, LTLFormula formula) {
		super();
		this.name = name;
		this.formula = formula;
		this.priority = null;
	}
	
	public LTLGoal(String name, LTLFormula formula, LTLPriority priority) {
		super();
		this.name = name;
		this.formula = formula;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LTLFormula getFormula() {
		return formula;
	}
	public void setFormula(LTLFormula formula) {
		this.formula = formula;
	}

	public LTLPriority getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return "LTLGoal [name=" + name + ", formula=" + formula + ", priority=" + priority + "]";
	}

	
	
}
