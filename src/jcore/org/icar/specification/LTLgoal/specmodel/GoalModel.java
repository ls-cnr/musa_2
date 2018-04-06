package org.icar.specification.LTLgoal.specmodel;

import java.util.HashSet;
import java.util.Set;

import org.icar.musa.runtime_entity.Requirements;
import org.icar.specification.LTLgoal.GoalModelEntity;

public class GoalModel implements GoalModelEntity, Requirements {
	private Set<LTLGoal> goals = new HashSet<LTLGoal>();

	public Set<LTLGoal> getGoals() {
		return goals;
	}
	
	
	
	

}
