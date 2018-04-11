package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.applications.spsreconfiguration.SPSReconfigcirc3_std_4guasti_0;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.QualityAsset;
import org.junit.Test;

public class Circuito3_test {

	@Test
	public void test() {
		SPSReconfigcirc3_std_4guasti_0 scenario = new SPSReconfigcirc3_std_4guasti_0();
		AssumptionSet assumptions = scenario.getDomainAssumptions();
		StateOfWorld w = scenario.getInitialState();
		QualityAsset asset = scenario.getQualityAsset();
		
//		System.out.println("---assumptions---");
//		System.out.println(assumptions.toString());
//		System.out.println("---state---");
//		System.out.println(w.toString());
		
		
//		System.out.println("---deductions---");
//		asset.log_state(assumptions,w);
//		
//		System.out.println("---state---");
//		scenario.log_current_state_graph(assumptions,w);
		
		FOLCondition[] con = scenario.getLoadConditions();
		for (FOLCondition c : con) {
			System.out.println(c.toString());
		}
		
	}

}
