package org.icar.testcase.pmr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllDomains_Test.class, AWBuilder_test.class, CapabilityEvolution.class, ProblemExploration_test.class,
		WTS_test.class })
public class AllPMRTests {

}
