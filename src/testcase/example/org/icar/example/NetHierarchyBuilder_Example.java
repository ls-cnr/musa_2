package org.icar.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.model.GoalModel;

public class NetHierarchyBuilder_Example {
	public static void main(String[] args) throws IOException {
		File file = new File("./src/testcase/example/goal/goal.txt");
		FileInputStream fis = new FileInputStream(file);

		GoalModel model = LTLGoalModelBuilder.parse(fis);
		
		NetHierarchyBuilder builder = new NetHierarchyBuilder();
		NetHierarchy net = builder.build(model.getGoals().iterator().next());
		System.out.println(net);
	}

}
