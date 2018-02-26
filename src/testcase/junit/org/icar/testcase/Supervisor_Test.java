package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchyBuilder;
import org.icar.ltlpetrinet.supervisor.NetSupervisor;
import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.domain.spsreconfiguration.SPSReconfigurationFull;
import org.icar.specification.LTLgoal.model.LTLGoal;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class Supervisor_Test {

	@Test
	public void sps_full_goal_test() {
		SPSReconfigurationFull s = new SPSReconfigurationFull();
		Requirements requirementsSPS = s.getRequirements();
		
		NetHierarchyBuilder builder = new NetHierarchyBuilder();
		NetHierarchy nets = builder.build((LTLGoal) requirementsSPS );
		
		System.out.println(nets.toString() );
		//System.out.println(nets.toStringWithNet() );
		
		TokenConf conf = nets.getInitialTokenConfiguration();
		System.out.println(conf.toString());
		
		NetSupervisor supervisor = new NetSupervisor(nets, conf);
		supervisor.prepareTokens();
		
		StateOfWorld w0 = new StateOfWorld();
		try {
			w0.addFact_asString("on(main1).");
			w0.addFact_asString("on(main2).");
			w0.addFact_asString("off(aux1).");
			w0.addFact_asString("off(aux2).");
			w0.addFact_asString("closed(i1).");
			w0.addFact_asString("open(i2).");
			w0.addFact_asString("open(i3).");
			w0.addFact_asString("closed(i4).");
			w0.addFact_asString("open(i5).");
			w0.addFact_asString("open(i6).");
			w0.addFact_asString("open(i7).");
			w0.addFact_asString("open(i8).");
			w0.addFact_asString("open(i9).");
			w0.addFact_asString("open(i10).");
			w0.addFact_asString("open(i11).");
			w0.addFact_asString("open(i12).");
			w0.addFact_asString("open(i13).");
			w0.addFact_asString("open(i14).");
			w0.addFact_asString("open(i15).");
			w0.addFact_asString("open(i16).");
			w0.addFact_asString("open(i17).");
			w0.addFact_asString("open(i18).");
			w0.addFact_asString("open(i19).");
			w0.addFact_asString("open(i20).");
			w0.addFact_asString("open(i21).");
			w0.addFact_asString("open(i22).");
			w0.addFact_asString("open(i23).");
			w0.addFact_asString("open(i24).");
			w0.addFact_asString("open(i25).");
			w0.addFact_asString("open(i26).");
			w0.addFact_asString("open(i27).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		supervisor.update(w0, s.getDomainAssumptions());
		
		System.out.println(supervisor.toStringWithScore());
		System.out.println(supervisor.calculate_partial_satisfaction());	
		
		StateOfWorld w1 = new StateOfWorld();
		try {
			w1.addFact_asString("on(main1).");
			w1.addFact_asString("on(main2).");
			w1.addFact_asString("on(aux1).");
			w1.addFact_asString("off(aux2).");
			w1.addFact_asString("closed(i1).");
			w1.addFact_asString("open(i2).");
			w1.addFact_asString("open(i3).");
			w1.addFact_asString("closed(i4).");
			w1.addFact_asString("open(i5).");
			w1.addFact_asString("open(i6).");
			w1.addFact_asString("open(i7).");
			w1.addFact_asString("open(i8).");
			w1.addFact_asString("open(i9).");
			w1.addFact_asString("open(i10).");
			w1.addFact_asString("open(i11).");
			w1.addFact_asString("open(i12).");
			w1.addFact_asString("open(i13).");
			w1.addFact_asString("open(i14).");
			w1.addFact_asString("open(i15).");
			w1.addFact_asString("open(i16).");
			w1.addFact_asString("open(i17).");
			w1.addFact_asString("open(i18).");
			w1.addFact_asString("open(i19).");
			w1.addFact_asString("open(i20).");
			w1.addFact_asString("open(i21).");
			w1.addFact_asString("open(i22).");
			w1.addFact_asString("open(i23).");
			w1.addFact_asString("open(i24).");
			w1.addFact_asString("open(i25).");
			w1.addFact_asString("open(i26).");
			w1.addFact_asString("open(i27).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		supervisor.update(w1, s.getDomainAssumptions());
		
		System.out.println(supervisor.toStringWithScore());
		System.out.println(supervisor.calculate_partial_satisfaction());	
	}

}
