package org.icar.example;

import org.icar.musa.domain_app.monitoring_workflow.WakeUp;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.pmr.problem_exploration.ProblemSpecification;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSEventLogger;
import org.icar.musa.pmr.problem_exploration.WTSLocalBuilder;

public class WTSBuilderExample {

	public static void main(String[] args) {
		WakeUp s = new WakeUp();
		
		ProblemSpecification ps = new ProblemSpecification(s.getDomainAssumptions(), s.getRequirements(),null);
		WTSLocalBuilder builder = new WTSLocalBuilder(ps, s.getCapabilitySet());
		
		WTSEventLogger logger = new WTSEventLogger();
		builder.register(logger);

		try {
			WTS wts = builder.build_solution_space(s.getInitialState());
			wts.printForGraphviz();
		} catch (ProblemDefinitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
