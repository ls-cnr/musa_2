package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.applications.spsreconfiguration.SPSReconfigurationFailure2;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.EntailOperator;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPS_tester {

	@Test
	public void test() throws ParseException, NotAllowedInAStateOfWorld {
		SPSReconfigurationFailure2 domain = new SPSReconfigurationFailure2();
		
		AssumptionSet assumptions = domain.getDomainAssumptions();
		StateOfWorld w = domain.getInitialState();
		System.out.println(w.toString());
		
//		w.addFact_asString("f(b2_3).");
//		w.addFact_asString("f(b3_4).");
//		w.addFact_asString("f(b4_5).");
//		w.addFact_asString("f(b21_25).");
		for (int i=0; i<46; i++) {
			FOLCondition cond = new FOLCondition(new DLPAtom("up",new Constant ("n"+(i+1))));
			EntailOperator test1 = EntailOperator.getInstance();
			boolean b1= test1.entailsCondition(w, assumptions, cond);
			if (b1==false)
				System.out.println("Node"+(i+1)+" is down");
		}

		
		boolean[] results = new boolean[24];
		for (int i=0; i<24; i++) {
			FOLCondition cond = new FOLCondition(new DLPAtom("on",new Constant ("l"+(i+1))));
			EntailOperator test1 = EntailOperator.getInstance();
			boolean b1= test1.entailsCondition(w, assumptions, cond);
			results[i]=b1;
			if (b1==false)
				System.out.println("Load"+(i+1)+" is down");
		}
		
		long score = domain.getQualityAsset().evaluate_state(w);
		
		
		
		
	}

}
