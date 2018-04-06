package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.linear_temporal_logic.annotated_petrinet.PNStateEnum;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.NetHierarchy;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.PredicateNode;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.AndOperator;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.FinallyPN;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.GloballyPN;
import org.icar.linear_temporal_logic.ltl_supervisor.NetSupervisor;
import org.icar.linear_temporal_logic.ltl_supervisor.TokenConf;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.junit.Before;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class LTL_Globally_Finally_Test {
	private NetSupervisor supervisor;
	
	@Before
	public void init() {

		PredicateNode pn1 = new PredicateNode("pn1", new FOLCondition("lit(a)"));
		GloballyPN glob = new GloballyPN("glob1", pn1);
		
		PredicateNode pn2 = new PredicateNode("pn2", new FOLCondition("lit(b)"));
		FinallyPN fin = new FinallyPN("fin1", pn2);
		
		AndOperator root = new AndOperator("root", glob, fin);
		root.init();
		
		NetHierarchy h = new NetHierarchy(root);
		TokenConf tokens = h.getInitialTokenConfiguration();
		
		supervisor = new NetSupervisor(h, tokens);
	}

	@Test
	public void test1() {
		StateOfWorld w1 = new StateOfWorld();
		StateOfWorld w2 = new StateOfWorld();
		StateOfWorld w3 = new StateOfWorld();
		try {
			w1.addFact_asString("lit(a).");
			w2.addFact_asString("lit(a).");w2.addFact_asString("lit(c).");
			w3.addFact_asString("lit(a).");w3.addFact_asString("lit(b).");
		} catch (ParseException e) {
			e.printStackTrace();
		}  catch (NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		AssumptionSet assumptions = new AssumptionSet();
		
		supervisor.prepareTokens();
		supervisor.update(w1,assumptions);
		assertEquals(supervisor.getState(),PNStateEnum.WAIT_BUT_ERROR);

		supervisor.update(w2,assumptions);
		assertEquals(supervisor.getState(),PNStateEnum.WAIT_BUT_ERROR);

		supervisor.update(w3,assumptions);
		assertEquals(supervisor.getState(),PNStateEnum.ACCEPTED);

		supervisor.cleanTokens();

	}

	@Test
	public void test2() {
		StateOfWorld w1 = new StateOfWorld();
		StateOfWorld w2 = new StateOfWorld();
		try {
			w1.addFact_asString("lit(a).");
			w2.addFact_asString("lit(b).");w2.addFact_asString("lit(c).");
		} catch (ParseException e) {
			e.printStackTrace();
		}  catch (NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		AssumptionSet assumptions = new AssumptionSet();

		
		supervisor.prepareTokens();
		supervisor.update(w1,assumptions);
		assertEquals(supervisor.getState(),PNStateEnum.WAIT_BUT_ERROR);
		
//		System.out.println(supervisor.getState());
		supervisor.update(w2,assumptions);
		assertEquals(supervisor.getState(),PNStateEnum.ERROR);

		supervisor.cleanTokens();
		//TokenConf tokens3 = supervisor.getTokenConfiguration();
		//System.out.println(tokens3.toString());
	}

}
