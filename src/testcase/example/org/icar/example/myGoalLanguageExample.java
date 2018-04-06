package org.icar.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

public class myGoalLanguageExample {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("./goal/goal.txt");
		FileInputStream fis = new FileInputStream(file);

		GoalModel model = LTLGoalModelBuilder.parse(fis);
		for (LTLGoal g : model.getGoals()) {
			System.out.println(g.toString());
		}
	}
}
