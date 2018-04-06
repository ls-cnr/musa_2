package org.icar.testcase;

import static org.junit.Assert.*;

import java.io.IOException;

import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;
import org.junit.Test;

public class LTLGoalParser_test {

	@Test
	public void test() throws IOException {
		String goal = "goalmodel { goal gand = (G on(main) and F on(l2)). }";
		
		GoalModel model = LTLGoalModelBuilder.parse(goal);
		for (LTLGoal g : model.getGoals()) {
			System.out.println(g.toString());
		}
	}

	@Test
	public void test1() throws IOException {
		String goal = "goalmodel { goal g1 = F (posture(walking) or posture(standing) ).  }";
		
		GoalModel model = LTLGoalModelBuilder.parse(goal);
		for (LTLGoal g : model.getGoals()) {
			System.out.println(g.toString());
		}
	}

}
