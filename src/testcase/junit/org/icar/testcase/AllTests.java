package org.icar.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnswerSetTest.class, DLPHeadHashSet_Test.class, DomainEntailParamTest.class, DomainEntailTest1.class,
		DomainEntailTest2.class, EdgeTest.class, ExpansionTranslatorTest.class, ExtendedNodeTranslation_Test.class,
		GoalModelTest.class, NetTest.class, ProvaSPSDomain_Test.class, SequencesTest.class, SolutionGraphTest.class,
		SPSREasyTest.class, StateOfWorldTest.class, WTSTest.class })
public class AllTests {

}
