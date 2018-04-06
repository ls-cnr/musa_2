package org.icar.musa.applications.spsreconfiguration;

import java.io.IOException;
import java.util.ArrayList;

import org.icar.musa.applications.Scenario;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;
import org.icar.specification.ACLanguage.CapabilityBuilder;
import org.icar.specification.ACLanguage.specmodel.Capability;
import org.icar.specification.LTLgoal.LTLGoalModelBuilder;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;

import net.sf.tweety.lp.asp.parser.ParseException;

public class SPSReconfigurationEasy implements Scenario {

	@Override
	public String getName() {
		return "Circuito Logico Articolo AAMAS";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
		AssumptionSet domain = new AssumptionSet();	 
		try {
			/* list of electrical elements */
			domain.addAssumption_asString("generator(main).");
			domain.addAssumption_asString("load(l1).");
			domain.addAssumption_asString("load(l2).");
			domain.addAssumption_asString("node(n1).");
			domain.addAssumption_asString("node(n2).");
			domain.addAssumption_asString("node(n3).");
			domain.addAssumption_asString("node(n4).");
			domain.addAssumption_asString("switch(i1).");
			domain.addAssumption_asString("switch(i2).");
			domain.addAssumption_asString("switch(i3).");
			domain.addAssumption_asString("switch(i4).");
			
			/* generic rules on on-off, up-down and open-closed */
			domain.addAssumption_asString("off(X) :- load(X), not on(X).");
			domain.addAssumption_asString("off(X) :- generator(X), not on(X).");
			domain.addAssumption_asString("down(X) :- node(X), not up(X).");
			domain.addAssumption_asString("open(X) :- switch(X), not closed(X).");
			
			/* electrical topology */
			domain.addAssumption_asString("up(n3) :- up(n1), closed(i1).");
			domain.addAssumption_asString("up(n4) :- up(n2), closed(i2).");
			domain.addAssumption_asString("up(n1) :- on(main).");
			domain.addAssumption_asString("up(n2) :- on(main).");
			domain.addAssumption_asString("on(l1) :- up(n3).");
			domain.addAssumption_asString("on(l1) :- up(n4).");
			domain.addAssumption_asString("on(l2) :- up(n3), closed(i3).");
			domain.addAssumption_asString("on(l2) :- up(n4), closed(i4).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		String g1 =  "goalmodel { goal g1 = (G on(main) and F on(l2)). }";	//"G(on(l2) U (!on(l1)))"; //
//		String g1 =  "F on(l2)";	//"G(on(l2) U (!on(l1)))"; //
//		String g1 =  "G (F on(l2) ) ";	//"G(on(l2) U (!on(l1)))"; //
//		String g1 =  "G on(main) && G (F on(l2) ) ";	//"G(on(l2) U (!on(l1)))"; //
		
		GoalModel model=null;
		LTLGoal goal = null;
		try {
			model = LTLGoalModelBuilder.parse(g1);
			goal = model.getGoals().iterator().next();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return goal;
	}

	@Override
	public StateOfWorld getInitialState() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("on(main).");
			w.addFact_asString("open(i1).");
			w.addFact_asString("open(i2).");
			w.addFact_asString("closed(i3).");
			w.addFact_asString("open(i4).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.utils.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		return w;
	}
	
	public ArrayList<AbstractCapability> getSubCapabilitySet1() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_start_generator_capability("main"));
		capabilities.add(generate_stop_generator_capability("main"));
		return capabilities;		
	}
	public ArrayList<AbstractCapability> getSubCapabilitySet2() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_open_capability_for_switcher("i1"));
		capabilities.add(generate_close_capability_for_switcher("i1"));
		capabilities.add(generate_open_capability_for_switcher("i2"));
		capabilities.add(generate_close_capability_for_switcher("i2"));
		return capabilities;		
	}
	
	public ArrayList<AbstractCapability> getSubCapabilitySet3() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(generate_open_capability_for_switcher("i3"));
		capabilities.add(generate_close_capability_for_switcher("i3"));
		capabilities.add(generate_open_capability_for_switcher("i4"));
		capabilities.add(generate_close_capability_for_switcher("i4"));
		return capabilities;
	}


	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
	
		/*generator*/
		capabilities.add(generate_start_generator_capability("main"));
		capabilities.add(generate_stop_generator_capability("main"));
		
		/*switch*/
		capabilities.add(generate_open_capability_for_switcher("i1"));
		capabilities.add(generate_close_capability_for_switcher("i1"));
		capabilities.add(generate_open_capability_for_switcher("i2"));
		capabilities.add(generate_close_capability_for_switcher("i2"));
		capabilities.add(generate_open_capability_for_switcher("i3"));
		capabilities.add(generate_close_capability_for_switcher("i3"));
		capabilities.add(generate_open_capability_for_switcher("i4"));
		capabilities.add(generate_close_capability_for_switcher("i4"));		
		
		return capabilities;
	}
	
	private AbstractCapability generate_start_generator_capability(String name) {
		AbstractCapability ac = null;
		
		String capname = "switch_ON_"+name+"_generator_cap";
		String pre = "off("+name+")";
		String post = "on("+name+")";
		String evo1 = "scenario "+name+"_on ( add on("+name+"), remove off("+name+") )";
		String c = "capability "+ capname +" { pre: " + pre +" post: " + post + " " + evo1 +" }";
		
		try {
			Capability cap = CapabilityBuilder.parse(c);
			ac = CapabilityBuilder.convertToAbstract(cap);
		} catch (IOException e) {
			System.out.println(c);
			e.printStackTrace();
		}
		return ac;
	}
	
	private AbstractCapability generate_stop_generator_capability(String name) {
		AbstractCapability ac = null;
		
		String capname = "switch_OFF_"+name+"_generator_cap";
		String pre = "on("+name+")";
		String post = "off("+name+")";
		String evo1 = "scenario "+name+"_on ( add off("+name+"), remove on("+name+") )";
		String c = "capability "+ capname +" { pre: " + pre +" post: " + post + " " + evo1 +" }";
		
		try {
			Capability cap = CapabilityBuilder.parse(c);
			ac = CapabilityBuilder.convertToAbstract(cap);
		} catch (IOException e) {
			System.out.println(c);
			e.printStackTrace();
		}
		return ac;
	}
	
	private AbstractCapability generate_close_capability_for_switcher(String switch_name) {
		/* close_switch_<name>_cap
		 * PRE: open(<name>)
		 * SCENARIO iClosed: remove {open(<name>)}, add {closed(<name>)}
		*/
		AbstractCapability ac = null;
		
		String capname = "set_CLOSED_"+switch_name+"_switcher_cap";
		String pre = "open("+switch_name+")";
		String post = "closed("+switch_name+")";
		String evo1 = "scenario "+switch_name+"_closed ( add closed("+switch_name+"), remove open("+switch_name+") )";
		String c = "capability "+ capname +" { pre: " + pre +" post: " + post + " " + evo1 +" }";
		
		try {
			Capability cap = CapabilityBuilder.parse(c);
			ac = CapabilityBuilder.convertToAbstract(cap);
		} catch (IOException e) {
			System.out.println(c);
			e.printStackTrace();
		}
		return ac;
	}

	private AbstractCapability generate_open_capability_for_switcher(String switch_name) {
		/* open_switch_<name>_cap
		 * PRE: closed(<name>)
		 * SCENARIO iOpen: remove {closed(<name>)}, add {open(<name>)}
		*/
		AbstractCapability ac = null;
		
		String capname = "set_OPEN_"+switch_name+"_switcher_cap";
		String pre = "closed("+switch_name+")";
		String post = "open("+switch_name+")";
		String evo1 = "scenario "+switch_name+"_open ( add open("+switch_name+"), remove closed("+switch_name+") )";
		String c = "capability "+ capname +" { pre: " + pre +" post: " + post + " " + evo1 +" }";
		
		try {
			Capability cap = CapabilityBuilder.parse(c);
			ac = CapabilityBuilder.convertToAbstract(cap);
		} catch (IOException e) {
			System.out.println(c);
			e.printStackTrace();
		}
		return ac;
	}

	@Override
	public QualityAsset getQualityAsset() {
		// TODO Auto-generated method stub
		return null;
	}

}
