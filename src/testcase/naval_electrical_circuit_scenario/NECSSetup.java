package naval_electrical_circuit_scenario;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.awareness.LTL.formulamodel.FormulaBT;
import layer.awareness.LTL.formulamodel.FormulaBTConstruction;
import layer.awareness.LTL.net.Nets;
import layer.awareness.LTL.net.TokensConfiguration;
import layer.awareness.LTL.net.netmodels.GloballyPN;
import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.AddStatement;
import layer.semantic.evolution.CapabilityEvolutionScenario;
import layer.semantic.evolution.EvolutionScenario;
import layer.semantic.evolution.RemoveStatement;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.MultipleExpansionNode;
import pmr.probexp.NormalExpansionNode;
import pmr.probexp.ProblemExploration;
import translator.ExtDLPHead;

public class NECSSetup {

	private AssumptionSet domain;
	private ProblemExploration problem;	
	private TokensConfiguration startingTokens;
	
	Constant main = new Constant("main");
	Constant aux = new Constant("aux");
	Constant a_fire = new Constant("a_fire");
	Constant the_fire_sys = new Constant("the_fire_sys");
	Constant i1 = new Constant("i1");
	Constant i2 = new Constant("i2");
	Constant i3 = new Constant("i3");
	Constant i4 = new Constant("i4");
	Constant i5 = new Constant("i5");
	Constant i6 = new Constant("i6");
	Constant i7 = new Constant("i7");
	Constant i8 = new Constant("i8");
	Constant i9 = new Constant("i9");
	Constant i10 = new Constant("i10");
	Constant i11 = new Constant("i11");
	Constant i12 = new Constant("i12");
	Constant i13 = new Constant("i13");
	Constant i14 = new Constant("i14");
	Constant i15 = new Constant("i15");
	Constant i16 = new Constant("i16");
	Constant i17 = new Constant("i17");
	
	AbstractCapability main_on;
	AbstractCapability main_off;
	AbstractCapability main_fault;
	AbstractCapability aux_on;
	AbstractCapability aux_off;
	AbstractCapability aux_fault;
	AbstractCapability catch_fire;
	AbstractCapability the_fire_sys_on;
	AbstractCapability i1_closed;
	AbstractCapability i1_open;
	AbstractCapability i2_closed;
	AbstractCapability i2_open;
	AbstractCapability i3_closed;
	AbstractCapability i3_open;
	AbstractCapability i4_closed;
	AbstractCapability i4_open;
	AbstractCapability i5_closed;
	AbstractCapability i5_open;
	AbstractCapability i6_closed;
	AbstractCapability i6_open;
	AbstractCapability i7_closed;
	AbstractCapability i7_open;
	AbstractCapability i8_closed;
	AbstractCapability i8_open;
	AbstractCapability i9_closed;
	AbstractCapability i9_open;
	AbstractCapability i10_closed;
	AbstractCapability i10_open;
	AbstractCapability i11_closed;
	AbstractCapability i11_open;
	AbstractCapability i12_closed;
	AbstractCapability i12_open;
	AbstractCapability i13_closed;
	AbstractCapability i13_open;
	AbstractCapability i14_closed;
	AbstractCapability i14_open;
	AbstractCapability i15_closed;
	AbstractCapability i15_open;
	AbstractCapability i16_closed;
	AbstractCapability i16_open;
	AbstractCapability i17_closed;
	AbstractCapability i17_open;
	
	@Before
	public void init() {
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
			domain.addAssumption_asString("up(n4) :- on(aux), closed(i5).");
			domain.addAssumption_asString("up(n4) :- up(1), closed(i2), closed(i3), closed(i4).");
			domain.addAssumption_asString("off(l1) :- not on(l1).");
			domain.addAssumption_asString("off(l2) :- not on(l2).");
//			domain.addAssumption_asString("off(motor_1) :- not on(motor_1).");
//			domain.addAssumption_asString("off(motor_2) :- not on(motor_2).");
//			domain.addAssumption_asString("down(n1) :- not up(n1).");
//			domain.addAssumption_asString("down(n2) :- not up(n2).");
//			domain.addAssumption_asString("down(n3) :- not up(n3).");
//			domain.addAssumption_asString("down(n4) :- not up(n4).");
//			domain.addAssumption_asString("down(n5) :- not up(n5).");
//			domain.addAssumption_asString("down(n6) :- not up(n6).");
//			domain.addAssumption_asString("off(main) :- fault(main).");
//			domain.addAssumption_asString("off(aux) :- fault(aux).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		/*main_generator_on*/
		Condition main_on_pre = new Condition(new DLPAtom("off", main));

		Set<EvolutionScenario> main_on_evo = new HashSet<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("mainOn");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", main)) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", main))));
		main_on_evo.add(main_on_evo1);
		
		this.main_on = new AbstractCapability("main_generator_on", main_on_evo, main_on_pre, null);

		/*main_generator_off*/
		Condition main_off_pre = new Condition(new DLPAtom("on", main));

		Set<EvolutionScenario> main_off_evo = new HashSet<>();
		CapabilityEvolutionScenario main_off_evo1 = new CapabilityEvolutionScenario("mainOff");
		main_off_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", main)) ) );
		main_off_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", main))));
		main_off_evo.add(main_off_evo1);
		
		this.main_off = new AbstractCapability("main_generator_off", main_off_evo, main_off_pre, null);

		/*main_generator_fault*/
		Condition main_fault_pre = new Condition(new DLPAtom("on", main));

		Set<EvolutionScenario> main_fault_evo = new HashSet<>();
		CapabilityEvolutionScenario main_fault_evo1 = new CapabilityEvolutionScenario("mainFault");
		main_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", main)) ) );
		main_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", main))));
		main_fault_evo.add(main_fault_evo1);
		
		this.main_fault = new AbstractCapability("main_generator_fault", main_fault_evo, main_fault_pre, null);

		/*aux_generator_on*/
		Condition aux_on_pre = new Condition(new DLPAtom("off", aux));

		Set<EvolutionScenario> aux_on_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_on_evo1 = new CapabilityEvolutionScenario("auxOn");
		aux_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", aux)) ) );
		aux_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", aux))));
		aux_on_evo.add(aux_on_evo1);
		
		this.aux_on = new AbstractCapability("aux_generator_on", aux_on_evo, aux_on_pre, null);

		/*aux_generator_off*/
		Condition aux_off_pre = new Condition(new DLPAtom("on", aux));

		Set<EvolutionScenario> aux_off_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_off_evo1 = new CapabilityEvolutionScenario("auxOff");
		aux_off_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", aux)) ) );
		aux_off_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
		aux_off_evo.add(aux_off_evo1);
		
		this.aux_off = new AbstractCapability("aux_generator_off", aux_off_evo, aux_off_pre, null);

		/*aux_generator_fault*/
		Condition aux_fault_pre = new Condition(new DLPAtom("on", aux));

		Set<EvolutionScenario> aux_fault_evo = new HashSet<>();
		CapabilityEvolutionScenario aux_fault_evo1 = new CapabilityEvolutionScenario("auxFault");
		aux_fault_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("fault", aux)) ) );
		aux_fault_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", aux))));
		aux_fault_evo.add(aux_fault_evo1);
		
		this.aux_fault = new AbstractCapability("aux_generator_fault", aux_fault_evo, aux_fault_pre, null);

		/*catch_fire*/
		Condition catch_fire_pre = new Condition(new DLPAtom("fire", a_fire));

		Set<EvolutionScenario> catch_fire_evo = new HashSet<>();
		CapabilityEvolutionScenario catch_fire_evo1 = new CapabilityEvolutionScenario("catchFire");
		catch_fire_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("verified", a_fire)) ) );
		catch_fire_evo.add(catch_fire_evo1);
		
		this.catch_fire = new AbstractCapability("catch_fire", catch_fire_evo, catch_fire_pre, null);

		/*the_fire_sys_on*/
		Condition the_fire_sys_on_pre = new Condition(new DLPAtom("off", the_fire_sys));

		Set<EvolutionScenario> the_fire_sys_on_evo = new HashSet<>();
		CapabilityEvolutionScenario the_fire_sys_on_evo1 = new CapabilityEvolutionScenario("the_fire_sysOn");
		the_fire_sys_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", the_fire_sys)) ) );
		the_fire_sys_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", the_fire_sys))));
		the_fire_sys_on_evo.add(the_fire_sys_on_evo1);
		
		this.the_fire_sys_on = new AbstractCapability("the_fire_sys_on", the_fire_sys_on_evo, the_fire_sys_on_pre, null);

		/*switch_i1_closed*/
		Condition i1_closed_pre = new Condition(new DLPAtom("open", i1));

		Set<EvolutionScenario> i1_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i1_closed_evo1 = new CapabilityEvolutionScenario("i1Closed");
		i1_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i1)) ) );
		i1_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i1))));
		i1_closed_evo.add(i1_closed_evo1);
		
		this.i1_closed = new AbstractCapability("switch_i1_closed", i1_closed_evo, i1_closed_pre, null);

		/*switch_i1_open*/
		Condition i1_open_pre = new Condition(new DLPAtom("closed", i1));

		Set<EvolutionScenario> i1_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i1_open_evo1 = new CapabilityEvolutionScenario("i1Open");
		i1_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i1)) ) );
		i1_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i1))));
		i1_open_evo.add(i1_open_evo1);
		
		this.i1_open = new AbstractCapability("switch_i1_open", i1_open_evo, i1_open_pre, null);

		/*switch_i2_closed*/
		Condition i2_closed_pre = new Condition(new DLPAtom("open", i2));

		Set<EvolutionScenario> i2_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i2_closed_evo1 = new CapabilityEvolutionScenario("i2Closed");
		i2_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i2)) ) );
		i2_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i2))));
		i2_closed_evo.add(i2_closed_evo1);
		
		this.i2_closed = new AbstractCapability("switch_i2_closed", i2_closed_evo, i2_closed_pre, null);

		/*switch_i2_open*/
		Condition i2_open_pre = new Condition(new DLPAtom("closed", i2));

		Set<EvolutionScenario> i2_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i2_open_evo1 = new CapabilityEvolutionScenario("i2Open");
		i2_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i2)) ) );
		i2_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i2))));
		i2_open_evo.add(i2_open_evo1);
		
		this.i2_open = new AbstractCapability("switch_i2_open", i2_open_evo, i2_open_pre, null);
		/*switch_i3_closed*/
		Condition i3_closed_pre = new Condition(new DLPAtom("open", i3));

		Set<EvolutionScenario> i3_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i3_closed_evo1 = new CapabilityEvolutionScenario("i3Closed");
		i3_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i3)) ) );
		i3_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i3))));
		i3_closed_evo.add(i3_closed_evo1);
		
		this.i3_closed = new AbstractCapability("switch_i3_closed", i3_closed_evo, i3_closed_pre, null);

		/*switch_i3_open*/
		Condition i3_open_pre = new Condition(new DLPAtom("closed", i3));

		Set<EvolutionScenario> i3_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i3_open_evo1 = new CapabilityEvolutionScenario("i3Open");
		i3_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i3)) ) );
		i3_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i3))));
		i3_open_evo.add(i3_open_evo1);
		
		this.i3_open = new AbstractCapability("switch_i3_open", i3_open_evo, i3_open_pre, null);

		/*switch_i4_closed*/
		Condition i4_closed_pre = new Condition(new DLPAtom("open", i4));

		Set<EvolutionScenario> i4_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i4_closed_evo1 = new CapabilityEvolutionScenario("i4Closed");
		i4_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i4)) ) );
		i4_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i4))));
		i4_closed_evo.add(i4_closed_evo1);
		
		this.i4_closed = new AbstractCapability("switch_i4_closed", i4_closed_evo, i4_closed_pre, null);

		/*switch_i4_open*/
		Condition i4_open_pre = new Condition(new DLPAtom("closed", i4));

		Set<EvolutionScenario> i4_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i4_open_evo1 = new CapabilityEvolutionScenario("i4Open");
		i4_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i4)) ) );
		i4_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i4))));
		i4_open_evo.add(i4_open_evo1);
		
		this.i4_open = new AbstractCapability("switch_i4_open", i4_open_evo, i4_open_pre, null);

		/*switch_i1_closed*/
		Condition i5_closed_pre = new Condition(new DLPAtom("open", i5));

		Set<EvolutionScenario> i5_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i5_closed_evo1 = new CapabilityEvolutionScenario("i5Closed");
		i5_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i5)) ) );
		i5_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i5))));
		i5_closed_evo.add(i5_closed_evo1);
		
		this.i5_closed = new AbstractCapability("switch_i5_closed", i5_closed_evo, i5_closed_pre, null);

		/*switch_i5_open*/
		Condition i5_open_pre = new Condition(new DLPAtom("closed", i5));

		Set<EvolutionScenario> i5_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i5_open_evo1 = new CapabilityEvolutionScenario("i5Open");
		i5_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i5)) ) );
		i5_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i5))));
		i5_open_evo.add(i5_open_evo1);
		
		this.i5_open = new AbstractCapability("switch_i5_open", i5_open_evo, i5_open_pre, null);

		/*switch_i6_closed*/
		Condition i6_closed_pre = new Condition(new DLPAtom("open", i6));

		Set<EvolutionScenario> i6_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i6_closed_evo1 = new CapabilityEvolutionScenario("i6Closed");
		i6_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i6)) ) );
		i6_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i6))));
		i6_closed_evo.add(i6_closed_evo1);
		
		this.i6_closed = new AbstractCapability("switch_i6_closed", i6_closed_evo, i6_closed_pre, null);

		/*switch_i6_open*/
		Condition i6_open_pre = new Condition(new DLPAtom("closed", i6));

		Set<EvolutionScenario> i6_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i6_open_evo1 = new CapabilityEvolutionScenario("i6Open");
		i6_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i6)) ) );
		i6_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i6))));
		i6_open_evo.add(i6_open_evo1);
		
		this.i6_open = new AbstractCapability("switch_i6_open", i6_open_evo, i6_open_pre, null);

		/*switch_i7_closed*/
		Condition i7_closed_pre = new Condition(new DLPAtom("open", i7));

		Set<EvolutionScenario> i7_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i7_closed_evo1 = new CapabilityEvolutionScenario("i7Closed");
		i7_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i7)) ) );
		i7_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i7))));
		i7_closed_evo.add(i7_closed_evo1);
		
		this.i7_closed = new AbstractCapability("switch_i7_closed", i7_closed_evo, i7_closed_pre, null);

		/*switch_i7_open*/
		Condition i7_open_pre = new Condition(new DLPAtom("closed", i7));

		Set<EvolutionScenario> i7_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i7_open_evo1 = new CapabilityEvolutionScenario("i7Open");
		i7_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i7)) ) );
		i7_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i7))));
		i7_open_evo.add(i7_open_evo1);
		
		this.i7_open = new AbstractCapability("switch_i7_open", i7_open_evo, i7_open_pre, null);

		/*switch_i8_closed*/
		Condition i8_closed_pre = new Condition(new DLPAtom("open", i8));

		Set<EvolutionScenario> i8_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i8_closed_evo1 = new CapabilityEvolutionScenario("i8Closed");
		i8_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i8)) ) );
		i8_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i8))));
		i8_closed_evo.add(i8_closed_evo1);
		
		this.i8_closed = new AbstractCapability("switch_i8_closed", i8_closed_evo, i8_closed_pre, null);

		/*switch_i8_open*/
		Condition i8_open_pre = new Condition(new DLPAtom("closed", i8));

		Set<EvolutionScenario> i8_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i8_open_evo1 = new CapabilityEvolutionScenario("i8Open");
		i8_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i8)) ) );
		i8_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i8))));
		i8_open_evo.add(i8_open_evo1);
		
		this.i8_open = new AbstractCapability("switch_i8_open", i8_open_evo, i8_open_pre, null);

		/*switch_i9_closed*/
		Condition i9_closed_pre = new Condition(new DLPAtom("open", i9));

		Set<EvolutionScenario> i9_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i9_closed_evo1 = new CapabilityEvolutionScenario("i9Closed");
		i9_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i9)) ) );
		i9_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i9))));
		i9_closed_evo.add(i9_closed_evo1);
		
		this.i9_closed = new AbstractCapability("switch_i9_closed", i9_closed_evo, i9_closed_pre, null);

		/*switch_i9_open*/
		Condition i9_open_pre = new Condition(new DLPAtom("closed", i9));

		Set<EvolutionScenario> i9_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i9_open_evo1 = new CapabilityEvolutionScenario("i9Open");
		i9_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i9)) ) );
		i9_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i9))));
		i9_open_evo.add(i9_open_evo1);
		
		this.i9_open = new AbstractCapability("switch_i9_open", i9_open_evo, i9_open_pre, null);

		/*switch_i10_closed*/
		Condition i10_closed_pre = new Condition(new DLPAtom("open", i10));

		Set<EvolutionScenario> i10_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i10_closed_evo1 = new CapabilityEvolutionScenario("i10Closed");
		i10_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i10)) ) );
		i10_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i10))));
		i10_closed_evo.add(i10_closed_evo1);
		
		this.i10_closed = new AbstractCapability("switch_i10_closed", i10_closed_evo, i10_closed_pre, null);

		/*switch_i10_open*/
		Condition i10_open_pre = new Condition(new DLPAtom("closed", i10));

		Set<EvolutionScenario> i10_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i10_open_evo1 = new CapabilityEvolutionScenario("i10Open");
		i10_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i10)) ) );
		i10_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i10))));
		i10_open_evo.add(i10_open_evo1);
		
		this.i10_open = new AbstractCapability("switch_i10_open", i10_open_evo, i10_open_pre, null);

		/*switch_i11_closed*/
		Condition i11_closed_pre = new Condition(new DLPAtom("open", i11));

		Set<EvolutionScenario> i11_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i11_closed_evo1 = new CapabilityEvolutionScenario("i11Closed");
		i11_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i11)) ) );
		i11_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i11))));
		i11_closed_evo.add(i11_closed_evo1);
		
		this.i11_closed = new AbstractCapability("switch_i11_closed", i11_closed_evo, i11_closed_pre, null);

		/*switch_i11_open*/
		Condition i11_open_pre = new Condition(new DLPAtom("closed", i11));

		Set<EvolutionScenario> i11_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i11_open_evo1 = new CapabilityEvolutionScenario("i11Open");
		i11_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i11)) ) );
		i11_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i11))));
		i11_open_evo.add(i11_open_evo1);
		
		this.i11_open = new AbstractCapability("switch_i11_open", i11_open_evo, i11_open_pre, null);

		/*switch_i12_closed*/
		Condition i12_closed_pre = new Condition(new DLPAtom("open", i12));

		Set<EvolutionScenario> i12_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i12_closed_evo1 = new CapabilityEvolutionScenario("i12Closed");
		i12_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i12)) ) );
		i12_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i12))));
		i12_closed_evo.add(i12_closed_evo1);
		
		this.i12_closed = new AbstractCapability("switch_i12_closed", i12_closed_evo, i12_closed_pre, null);

		/*switch_i12_open*/
		Condition i12_open_pre = new Condition(new DLPAtom("closed", i12));

		Set<EvolutionScenario> i12_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i12_open_evo1 = new CapabilityEvolutionScenario("i12Open");
		i12_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i12)) ) );
		i12_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i12))));
		i12_open_evo.add(i12_open_evo1);
		
		this.i12_open = new AbstractCapability("switch_i12_open", i12_open_evo, i12_open_pre, null);

		/*switch_i13_closed*/
		Condition i13_closed_pre = new Condition(new DLPAtom("open", i13));

		Set<EvolutionScenario> i13_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i13_closed_evo1 = new CapabilityEvolutionScenario("i13Closed");
		i13_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i13)) ) );
		i13_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i13))));
		i13_closed_evo.add(i13_closed_evo1);
		
		this.i13_closed = new AbstractCapability("switch_i13_closed", i13_closed_evo, i13_closed_pre, null);

		/*switch_i13_open*/
		Condition i13_open_pre = new Condition(new DLPAtom("closed", i13));

		Set<EvolutionScenario> i13_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i13_open_evo1 = new CapabilityEvolutionScenario("i13Open");
		i13_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i13)) ) );
		i13_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i13))));
		i13_open_evo.add(i13_open_evo1);
		
		this.i13_open = new AbstractCapability("switch_i13_open", i13_open_evo, i13_open_pre, null);

		/*switch_i14_closed*/
		Condition i14_closed_pre = new Condition(new DLPAtom("open", i14));

		Set<EvolutionScenario> i14_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i14_closed_evo1 = new CapabilityEvolutionScenario("i14Closed");
		i14_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i14)) ) );
		i14_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i14))));
		i14_closed_evo.add(i14_closed_evo1);
		
		this.i14_closed = new AbstractCapability("switch_i14_closed", i14_closed_evo, i14_closed_pre, null);

		/*switch_i14_open*/
		Condition i14_open_pre = new Condition(new DLPAtom("closed", i14));

		Set<EvolutionScenario> i14_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i14_open_evo1 = new CapabilityEvolutionScenario("i14Open");
		i14_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i14)) ) );
		i14_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i14))));
		i14_open_evo.add(i14_open_evo1);
		
		this.i14_open = new AbstractCapability("switch_i14_open", i14_open_evo, i14_open_pre, null);

		/*switch_i15_closed*/
		Condition i15_closed_pre = new Condition(new DLPAtom("open", i15));

		Set<EvolutionScenario> i15_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i15_closed_evo1 = new CapabilityEvolutionScenario("i15Closed");
		i15_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i15)) ) );
		i15_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i15))));
		i15_closed_evo.add(i15_closed_evo1);
		
		this.i15_closed = new AbstractCapability("switch_i15_closed", i15_closed_evo, i15_closed_pre, null);

		/*switch_i15_open*/
		Condition i15_open_pre = new Condition(new DLPAtom("closed", i15));

		Set<EvolutionScenario> i15_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i15_open_evo1 = new CapabilityEvolutionScenario("i15Open");
		i15_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i15)) ) );
		i15_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i15))));
		i15_open_evo.add(i15_open_evo1);
		
		this.i15_open = new AbstractCapability("switch_i15_open", i15_open_evo, i15_open_pre, null);

		/*switch_i16_closed*/
		Condition i16_closed_pre = new Condition(new DLPAtom("open", i16));

		Set<EvolutionScenario> i16_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i16_closed_evo1 = new CapabilityEvolutionScenario("i16Closed");
		i16_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i16)) ) );
		i16_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i16))));
		i16_closed_evo.add(i16_closed_evo1);
		
		this.i16_closed = new AbstractCapability("switch_i16_closed", i16_closed_evo, i16_closed_pre, null);

		/*switch_i16_open*/
		Condition i16_open_pre = new Condition(new DLPAtom("closed", i16));

		Set<EvolutionScenario> i16_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i16_open_evo1 = new CapabilityEvolutionScenario("i16Open");
		i16_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i16)) ) );
		i16_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i16))));
		i16_open_evo.add(i16_open_evo1);
		
		this.i16_open = new AbstractCapability("switch_i16_open", i16_open_evo, i16_open_pre, null);

		/*switch_i17_closed*/
		Condition i17_closed_pre = new Condition(new DLPAtom("open", i17));

		Set<EvolutionScenario> i17_closed_evo = new HashSet<>();
		CapabilityEvolutionScenario i17_closed_evo1 = new CapabilityEvolutionScenario("i17Closed");
		i17_closed_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i17)) ) );
		i17_closed_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i17))));
		i17_closed_evo.add(i17_closed_evo1);
		
		this.i17_closed = new AbstractCapability("switch_i17_closed", i17_closed_evo, i17_closed_pre, null);

		/*switch_i17_open*/
		Condition i17_open_pre = new Condition(new DLPAtom("closed", i17));

		Set<EvolutionScenario> i17_open_evo = new HashSet<>();
		CapabilityEvolutionScenario i17_open_evo1 = new CapabilityEvolutionScenario("i17Open");
		i17_open_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i17)) ) );
		i17_open_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i17))));
		i17_open_evo.add(i17_open_evo1);
		
		this.i17_open = new AbstractCapability("switch_i17_open", i17_open_evo, i17_open_pre, null);
	}
	
	private void modelConstruction( FormulaBT treeModel ) {
		problem = new ProblemExploration(treeModel, new ArrayList<AbstractCapability>(), domain);

		startingTokens = new TokensConfiguration(new Nets(treeModel));

		System.out.println("\n- Formulas Tree");
		treeModel.print(treeModel.getRoot());
		System.out.println("-----------------------------------------------------------------");
	}
	
//	@Test
//	public void test0() {
//		modelConstruction( FormulaBTConstruction.construct("( (G on(l1)) && (G(on(l2) U off(motor_2))) ) && ( (G(off(main) <-> on(aus))) && (G(verified(a_fire) -> ((X off(l2)) && (F on(the_fire_sys))))) )"));
//				
//		addAllCapabilities();
//		while( !problem.toVisitIsEmpty())
//			problem.expandNode();
//	}
	
	@Ignore
	@Test
	public void test1() {
		modelConstruction( FormulaBTConstruction.construct("G on(l1)"));
		
		StateOfWorld wStart = new StateOfWorld(); 
		try {
			  wStart.addFact_asString("off(main).");
			  wStart.addFact_asString("off(aux).");
			  wStart.addFact_asString("off(the_fire_sys).");
			  wStart.addFact_asString("closed(i1).");
			  wStart.addFact_asString("closed(i2).");
			  wStart.addFact_asString("open(i3).");
			  wStart.addFact_asString("open(i4).");
			  wStart.addFact_asString("open(i5).");
			  wStart.addFact_asString("open(i6).");
			  wStart.addFact_asString("open(i7).");
			  wStart.addFact_asString("closed(i8).");
			  wStart.addFact_asString("open(i9).");
			  wStart.addFact_asString("open(i10).");
			  wStart.addFact_asString("open(i11).");
			  wStart.addFact_asString("open(i12).");
			  wStart.addFact_asString("open(i13).");
			  wStart.addFact_asString("open(i14).");
			  wStart.addFact_asString("open(i15).");
			  wStart.addFact_asString("open(i16).");
			  wStart.addFact_asString("closed(i17).");
			} catch (ParseException e) {
			  e.printStackTrace();
			} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			  e.printStackTrace();
			}
		
		problem.addToVisit(new WorldNode(wStart), startingTokens, 10);

		problem.addCapability(i2_closed);
		problem.addCapability(i2_open);
		problem.addCapability(i3_closed);
		problem.addCapability(i3_open);
		problem.addCapability(main_on);
		problem.addCapability(main_off);
		
		int k = 0;
		while( !problem.toVisitIsEmpty() && k++ < 90)
			problem.expandNode();
		
		HashMap<ENode, Integer> swtmp = new HashMap<>();
		
		int tmp = 0;
		
		for( ExpansionNode nk : problem.getExpandedList() ){
			System.out.println("-----------------------------------------------------------------");
			System.out.println( nk.getCapability() );
			if( swtmp.containsKey(nk.getSource()) )
				System.out.println("Source W"+ swtmp.get(nk.getSource()) );
			else {
				swtmp.put(nk.getSource(), tmp);
				System.out.println("Source W"+ tmp++);
			}
			for( ENode e : nk.getDestination() ){
				if( swtmp.containsKey(e) )
					System.out.println("Name W"+ swtmp.get(e) );
				else {
					swtmp.put(e, tmp);
					System.out.println("Name W"+ tmp++);
				}
				System.out.println("Score: " + e.getScore());
				for( String net : e.getTokens().getNetsState().keySet() )
					System.out.println(net + " -> State:" + e.getTokens().getNetState(net));
				
				for( String net : e.getTokens().getConf().keySet() )
					for( String p : e.getTokens().getTokens(net) )
						System.out.println(net + " -> Place:" + p);
				
				System.out.println("");
			}
		}
	}

	
	@Test
	public void test2() {
		modelConstruction( FormulaBTConstruction.construct("G(on(l2) U (! on(motor_2)))"));
		
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
			} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			  e.printStackTrace();
			}
		
		problem.addToVisit(new WorldNode(wStart), startingTokens, 10);

		problem.addCapability(i10_open);
		
		int k = 0;
		while( !problem.toVisitIsEmpty() && k++ < 90)
			problem.expandNode();
		
		HashMap<ENode, Integer> swtmp = new HashMap<>();
		
		int tmp = 0;
		
		for( ExpansionNode nk : problem.getExpandedList() ){
			System.out.println("-----------------------------------------------------------------");
			System.out.println( nk.getCapability() );
			if( swtmp.containsKey(nk.getSource()) )
				System.out.println("Source W"+ swtmp.get(nk.getSource()) );
			else {
				swtmp.put(nk.getSource(), tmp);
				System.out.println("Source W"+ tmp++);
			}
			for( ENode e : nk.getDestination() ){
				if( swtmp.containsKey(e) )
					System.out.println("Name W"+ swtmp.get(e) );
				else {
					swtmp.put(e, tmp);
					System.out.println("Name W"+ tmp++);
				}
				System.out.println("Score: " + e.getScore());
				for( String net : e.getTokens().getNetsState().keySet() )
					System.out.println(net + " -> State:" + e.getTokens().getNetState(net));
				
				for( String net : e.getTokens().getConf().keySet() )
					for( String p : e.getTokens().getTokens(net) )
						System.out.println(net + " -> Place:" + p);
				
				System.out.println("");
			}
		}
	}
	
	

	private void addAllCapabilities(){
		problem.addCapability(main_on);
		problem.addCapability(main_off);
		problem.addCapability(main_fault);
		problem.addCapability(aux_on);
		problem.addCapability(aux_off);
		problem.addCapability(aux_fault);
		problem.addCapability(catch_fire);
		problem.addCapability(the_fire_sys_on);
		problem.addCapability(i1_closed);
		problem.addCapability(i1_open);
		problem.addCapability(i2_closed);
		problem.addCapability(i2_open);
		problem.addCapability(i3_closed);
		problem.addCapability(i3_open);
		problem.addCapability(i4_closed);
		problem.addCapability(i4_open);
		problem.addCapability(i5_closed);
		problem.addCapability(i5_open);
		problem.addCapability(i6_closed);
		problem.addCapability(i6_open);
		problem.addCapability(i7_closed);
		problem.addCapability(i7_open);
		problem.addCapability(i8_closed);
		problem.addCapability(i8_open);
		problem.addCapability(i9_closed);
		problem.addCapability(i9_open);
		problem.addCapability(i10_closed);
		problem.addCapability(i10_open);
		problem.addCapability(i11_closed);
		problem.addCapability(i11_open);
		problem.addCapability(i12_closed);
		problem.addCapability(i12_open);
		problem.addCapability(i13_closed);
		problem.addCapability(i13_open);
		problem.addCapability(i14_closed);
		problem.addCapability(i14_open);
		problem.addCapability(i15_closed);
		problem.addCapability(i15_open);
		problem.addCapability(i16_closed);
		problem.addCapability(i16_open);
		problem.addCapability(i17_closed);
		problem.addCapability(i17_open);
	}

}
