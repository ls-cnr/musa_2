package org.icar.testcase.pmr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.musa.applications.Scenario;
import org.icar.musa.applications.monitoring_workflow.WakeUp;
import org.icar.musa.applications.spsreconfiguration.SPSReconfigcirc3_std_4guasti_0;
import org.icar.musa.applications.spsreconfiguration.SPSReconfigurationEasy;
import org.icar.musa.applications.spsreconfiguration.SPSReconfigurationFailure2;
import org.icar.musa.applications.spsreconfiguration.SPSReconfigurationFull;
import org.icar.musa.pmr.problem_exploration.ProblemSpecification;
import org.icar.musa.pmr.problem_exploration.WTS;
import org.icar.musa.pmr.problem_exploration.WTSEventLogger;
import org.icar.musa.pmr.problem_exploration.WTSLocalBuilder;
import org.icar.musa.pmr.solution.AWBuilder;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.Requirements;
import org.icar.musa.utils.exception.ProblemDefinitionException;
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

	@Ignore
	@Test
	public void explore_SPS_full_with_failure() throws ProblemDefinitionException {
		SPSReconfigurationFailure2 s = new SPSReconfigurationFailure2();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, s.getQualityAsset());
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		//WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder(s.getQualityAsset(),assumptionsSPS);
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		
		aw_builder.log_solutions(assumptionsSPS, s.getQualityAsset());
		aw_builder.log_partial_solutions(assumptionsSPS, s.getQualityAsset());
	}

	@Test
	public void explore_SPS_circuito3() throws ProblemDefinitionException {
		Scenario s = new SPSReconfigcirc3_std_4guasti_0();
		AssumptionSet assumptionsSPS = s.getDomainAssumptions();
		Requirements requirementsSPS = s.getRequirements();
		ArrayList<AbstractCapability> allCapSPS = s.getCapabilitySet();
		ProblemSpecification ps_SPS = new ProblemSpecification(assumptionsSPS, requirementsSPS, s.getQualityAsset());
		
//		System.out.println("---assumptions---");
//		System.out.println(assumptionsSPS.toString());
//		System.out.println("---state---");
//		System.out.println(s.getInitialState().toExtendedString());
		
		
		WTSLocalBuilder wts_builder = new WTSLocalBuilder(ps_SPS, allCapSPS);
		//WTSEventLogger logger = new WTSEventLogger();
		AWBuilder aw_builder = new AWBuilder(s.getQualityAsset(),assumptionsSPS);
		//wts_builder.register(logger);
		wts_builder.register(aw_builder);
		
		wts_builder.build_solution_space(s.getInitialState());
		
		aw_builder.log_solutions(assumptionsSPS, s.getQualityAsset());
		aw_builder.log_partial_solutions(assumptionsSPS, s.getQualityAsset());
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
