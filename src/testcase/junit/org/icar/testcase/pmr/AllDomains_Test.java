package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.musa.core.Requirements;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.domain_app.monitoring_workflow.WakeUp;
import org.icar.musa.domain_app.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.domain_app.spsreconfiguration.SPSReconfigurationFailure;
import org.icar.musa.domain_app.spsreconfiguration.SPSReconfigurationFailure2;
import org.icar.musa.domain_app.spsreconfiguration.SPSReconfigurationFull;
import org.icar.musa.exception.ProblemDefinitionException;
import org.icar.musa.pmr.problem_exploration.ProblemSpecification;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSEventLogger;
import org.icar.musa.pmr.problem_exploration.WTSLocalBuilder;
import org.icar.musa.pmr.solution.AWBuilder;
import org.junit.Ignore;
import org.junit.Test;

public class AllDomains_Test {

	@Ignore
	@Test
	public void explore_SPS() throws ProblemDefinitionException {
		SPSReconfigurationEasy s = new SPSReconfigurationEasy();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, null);
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		//WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder();
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		
		aw_builder.log_solutions();
	}

	@Ignore
	@Test
	public void explore_SPS_full() throws ProblemDefinitionException {
		SPSReconfigurationFull s = new SPSReconfigurationFull();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, null);
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		//WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder();
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		
		aw_builder.log_solutions();
	}

	@Test
	public void explore_SPS_full_with_failure() throws ProblemDefinitionException {
		SPSReconfigurationFailure2 s = new SPSReconfigurationFailure2();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, s.getQualityAsset());
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		//WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder();
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		
		aw_builder.log_solutions();
	}

	@Ignore
	@Test
	public void explore_WakeUp() throws ProblemDefinitionException {
		WakeUp s = new WakeUp();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, null);
		
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder();
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		WTS wts = wts_builder.build_solution_space(s.getInitialState());
		
		wts.printForGraphviz();
		//aw_builder.log_solutions();
	}
}
