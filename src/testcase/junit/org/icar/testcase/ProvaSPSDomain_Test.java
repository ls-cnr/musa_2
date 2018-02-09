package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.junit.Test;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class ProvaSPSDomain_Test {

	@Test
	public void test() {
		AssumptionSet domain;
		domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("component(X) :- generator(X).");
			domain.addAssumption_asString("component(X) :- motor(X).");
			domain.addAssumption_asString("component(X) :- load(X).");
			domain.addAssumption_asString("component(X) :- fire_system(X).");
			domain.addAssumption_asString("event(X) :- fire(X).");
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("generator(aux).");
			domain.addAssumption_asString("motor(motor_1).");
			domain.addAssumption_asString("motor(motor_2).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("fire_system(a_fire_sys).");
			domain.addAssumption_asString("fire(a_fire).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("node(n3).");
			domain.addAssumption_asString("node(n4).");
			domain.addAssumption_asString("node(n5).");
			domain.addAssumption_asString("node(n6).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			domain.addAssumption_asString("switch(i5).");
			domain.addAssumption_asString("switch(i6).");
			domain.addAssumption_asString("switch(i7).");
			domain.addAssumption_asString("switch(i8).");
			domain.addAssumption_asString("switch(i9).");
			domain.addAssumption_asString("switch(i10).");
			domain.addAssumption_asString("switch(i11).");
			domain.addAssumption_asString("switch(i12).");
			domain.addAssumption_asString("switch(i13).");
			domain.addAssumption_asString("switch(i14).");
			domain.addAssumption_asString("switch(i15).");
			domain.addAssumption_asString("switch(i16).");
			domain.addAssumption_asString("switch(i17).");
			domain.addAssumption_asString("on(l1) :- up(n2), closed(i8).");
			domain.addAssumption_asString("on(l1) :- up(n5), closed(i7).");
			domain.addAssumption_asString("on(l2) :- up(n3), closed(i14), closed(i13).");
			domain.addAssumption_asString("on(l2) :- up(n6), closed(i11), closed(i12).");
			domain.addAssumption_asString("on(motor_1) :- up(n3), closed(i15).");
			domain.addAssumption_asString("on(motor_2) :- up(n6), closed(i10).");
			domain.addAssumption_asString("up(n2) :- up(n1), closed(i17).");
			domain.addAssumption_asString("up(n3) :- up(n2), closed(i16).");
			domain.addAssumption_asString("up(n5) :- up(n4), closed(i6).");
			domain.addAssumption_asString("un(n6) :- up(n5), closed(i9).");
			domain.addAssumption_asString("up(n1) :- on(main), closed(i1).");
			domain.addAssumption_asString("up(n1) :- up(n4), closed(i4), closed(i3), closed(i2).");
			domain.addAssumption_asString("up(n4) :- up(1), closed(i2), closed(i3), closed(i4).");
			domain.addAssumption_asString("up(n4) :- on(aux), closed(i5).");
			
			domain.addAssumption_asString("off(l1) :- not on(l1).");
			
			domain.addAssumption_asString("off(X) :- component(X), not on(X).");
			
			domain.addAssumption_asString("off(l2) :- not on(l2).");
			domain.addAssumption_asString("off(motor_1) :- not on(motor_1).");
			domain.addAssumption_asString("off(motor_2) :- not on(motor_2).");
			domain.addAssumption_asString("down(n1) :- not up(n1).");
			domain.addAssumption_asString("down(n2) :- not up(n2).");
			domain.addAssumption_asString("down(n3) :- not up(n3).");
			domain.addAssumption_asString("down(n4) :- not up(n4).");
			domain.addAssumption_asString("down(n5) :- not up(n5).");
			domain.addAssumption_asString("down(n6) :- not up(n6).");
			domain.addAssumption_asString("off(main) :- fault(main).");
			domain.addAssumption_asString("off(aux) :- fault(aux).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		/************************************************************************************/
		StateOfWorld wStart = new StateOfWorld(); 
		try {
			  wStart.addFact_asString("on(main).");
			  wStart.addFact_asString("off(aux).");
			  wStart.addFact_asString("off(the_fire_sys).");
			  wStart.addFact_asString("closed(i1).");
			  wStart.addFact_asString("closed(i2).");
			  wStart.addFact_asString("closed(i3).");
			  wStart.addFact_asString("closed(i4).");
			  wStart.addFact_asString("open(i5).");
			  wStart.addFact_asString("closed(i6).");
			  wStart.addFact_asString("open(i7).");
			  wStart.addFact_asString("open(i8).");
			  wStart.addFact_asString("closed(i9).");
			  wStart.addFact_asString("closed(i10).");
			  wStart.addFact_asString("open(i11).");
			  wStart.addFact_asString("open(i12).");
			  wStart.addFact_asString("closed(i13).");
			  wStart.addFact_asString("closed(i14).");
			  wStart.addFact_asString("open(i15).");
			  wStart.addFact_asString("closed(i16).");
			  wStart.addFact_asString("closed(i17).");
			} catch (ParseException e) {
			  e.printStackTrace();
			} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			  e.printStackTrace();
			}

		/************************************************************************************/
		System.out.println( EntailOperator.getInstance().entailsCondition(wStart, domain, new FOLCondition(new DLPAtom("off", new Constant("l2")))));
		
		
	}

}
