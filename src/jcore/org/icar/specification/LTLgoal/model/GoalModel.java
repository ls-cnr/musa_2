package org.icar.specification.LTLgoal.model;

import java.util.HashSet;
import java.util.Set;

import org.icar.musa.core.Requirements;
import org.icar.specification.LTLgoal.GoalModelEntity;

public class GoalModel implements GoalModelEntity, Requirements {
	private Set<LTLGoal> goals = new HashSet<LTLGoal>();

	public Set<LTLGoal> getGoals() {
		return goals;
	}
	
	
	
	

}
