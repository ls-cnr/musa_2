package org.icar.musa.applications.spsreconfiguration;

import java.util.LinkedList;
import java.util.List;

import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.evolution.AddStatement;
import org.icar.musa.context.evolution.EvolutionScenario;
import org.icar.musa.context.evolution.RemoveStatement;
import org.icar.musa.context.fol_reasoner.EntailOperator;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.CapabilityEvolutionScenario;
import org.icar.musa.runtime_entity.Condition;
import org.icar.musa.utils.agent_communication.translator.ExtDLPHead;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class SPSCircuit {
	List<LoadDescriptor> loads;
	List<GeneratorDescriptor> generators;
	List<ConnDescriptor> connections;
	List<FailureDescription> failures;
	
	public SPSCircuit() {
		super();
		loads = new LinkedList<LoadDescriptor>();
		generators = new LinkedList<GeneratorDescriptor>();
		connections = new LinkedList<ConnDescriptor>();
		failures = new LinkedList<FailureDescription>();
	}
	
	public void log_graph() {
		System.out.println("digraph G {");

		for (ConnDescriptor conn : connections) {
			System.out.print("\""+conn.getSource()+"\"");
			System.out.print("->");
			System.out.print("\""+conn.getDest()+"\"");
			if (conn.getSwitcher()!=null)
				System.out.println("[label=\""+ conn.getSwitcher()+ "\"]");
			else
				System.out.println("[label=\""+ conn.getFailureName()+ "\"]");	
		}
		
		for (LoadDescriptor load : loads) {
			System.out.print("\""+load.getName()+"\"");
			if (load.getType()==LoadDescriptor.VITAL)
				System.out.println("[shape=invtriangle,style=bold,color=black];");
			else if (load.getType()==LoadDescriptor.SEMIVITAL)
				System.out.println("[shape=invtriangle,style=bold,color=blue];");
			else
				System.out.println("[shape=invtriangle,style=bold,color=yellow];");
			
			System.out.print("\""+load.getSourceId()+"\"");
			System.out.print("->");
			System.out.print("\""+load.getName()+"\"");
			System.out.println("[label=\""+ load.getSwitcher()+ "\"]");
		}
		
		for (GeneratorDescriptor gen : generators) {
			System.out.print("\""+gen.getName()+"\"");
			System.out.println("[shape=box,style=bold,color=black];");
			
			System.out.print("\""+gen.getSourceId()+"\"");
			System.out.print("->");
			System.out.print("\""+gen.getName()+"\"");
		}
		
		System.out.println("}");		
	}

	public void log_current_state_graph(AssumptionSet assumptions,StateOfWorld w) {
		
		FOLCondition[] load_conditions = getLoadConditions();
		FOLCondition[] gen_conditions = getGenConditions();
		FOLCondition[] node_conditions = getNodeConditions();
		
		EntailOperator entail = EntailOperator.getInstance();
		boolean[] noderesults = entail.entailsCondition(w, assumptions, node_conditions);
		boolean[] results = entail.entailsCondition(w, assumptions, load_conditions);
		boolean[] genresults = entail.entailsCondition(w, assumptions, gen_conditions);
		
		System.out.println("digraph G {");
		
		for (int i=0; i<node_conditions.length; i++) {
			System.out.print("\""+(i+1)+"\"");
			if (noderesults[i]==true)
				System.out.println("[color=black]");
			if (noderesults[i]==false) 
				System.out.println("[color=red]");
		}

		for (ConnDescriptor conn : connections) {
			System.out.print("\""+conn.getSource()+"\"");
			System.out.print("->");
			System.out.print("\""+conn.getDest()+"\"");
			if (conn.getSwitcher()!=null)
				System.out.println("[label=\""+ conn.getSwitcher()+ "\"]");
			else
				System.out.println("[label=\""+ conn.getFailureName()+ "\"]");	
		}
		
		int i_load=0;
		for (LoadDescriptor load : loads) {
			
			System.out.print("\""+load.getName()+"\"");
//			if (load.getType()==LoadDescriptor.VITAL)
//				System.out.println("[shape=invtriangle,style=bold,color=black];");
//			else if (load.getType()==LoadDescriptor.SEMIVITAL)
//				System.out.println("[shape=invtriangle,style=bold,color=blue];");
//			else
//				System.out.println("[shape=invtriangle,style=bold,color=yellow];");
			if (results[i_load]==true) 
				System.out.println("[shape=invtriangle,color=black];");
			else 
				System.out.println("[shape=invtriangle,color=red];");
			
			System.out.print("\""+load.getSourceId()+"\"");
			System.out.print("->");
			System.out.print("\""+load.getName()+"\"");
			System.out.println("[label=\""+ load.getSwitcher()+ "\"]");
			
			i_load++;
		}
		
		int i_gen=0;
		for (GeneratorDescriptor gen : generators) {
			
			System.out.print("\""+gen.getName()+"\"");
			if (genresults[i_gen]==true)
				System.out.println("[shape=box,style=bold,color=black];");
			else 
				System.out.println("[shape=box,style=bold,color=red];");
			
			System.out.print("\""+gen.getSourceId()+"\"");
			System.out.print("->");
			System.out.print("\""+gen.getName()+"\"");
			
			i_gen++;
		}
		
		System.out.println("}");		
	}

	public void add_connection(int node1, int node2) {
		connections.add(new ConnDescriptor(node1,node2));
	}
	
	public void add_switcher(int node1, int node2, String sw_name) {
		connections.add(new ConnDescriptor(node1,node2,sw_name));
	}

	public void add_switcher(int node1, int node2, String sw_name, boolean open) {
		connections.add(new ConnDescriptor(node1,node2,sw_name,open));
	}

	public void add_load(int node, int load, int pow, int type, int priority) {
		loads.add( new LoadDescriptor(node,load,pow,type,priority) );
	}

	public void add_generator(int node, String name, int pow) {
		generators.add( new GeneratorDescriptor(node,name,pow));
	}

	public void add_generator(int node, String name, int pow, boolean off) {
		generators.add( new GeneratorDescriptor(node,name,pow,off));
	}
	
	public void add_failure(int n1, int n2) {
		failures.add(new FailureDescription(n1,n2));
	}
	
	public AssumptionSet getAssumptions() throws ParseException, NotAllowedInAnAssumptionSet {
		AssumptionSet assumptions = new AssumptionSet();
		
		assumptions.addAssumption_asString("off(X) :- load(X), not on(X).");
		assumptions.addAssumption_asString("off(X) :- generator(X), not on(X).");
		assumptions.addAssumption_asString("down(X) :- node(X), not up(X).");
		assumptions.addAssumption_asString("open(X) :- switch(X), not closed(X).");
		
		for (ConnDescriptor c : connections) {
			if (c.getSwitcher()==null) {
				assumptions.addAssumption_asString("up("+c.getSourceName()+") :- up("+c.getDestName()+"), not f("+c.getFailureName()+").");
				assumptions.addAssumption_asString("up("+c.getDestName()+") :- up("+c.getSourceName()+"), not f("+c.getFailureName()+").");
			} else {
				assumptions.addAssumption_asString("switcher("+c.getSwitcher()+").");
				assumptions.addAssumption_asString("up("+c.getSourceName()+") :- up("+c.getDestName()+"), closed("+c.getSwitcher()+").");
				assumptions.addAssumption_asString("up("+c.getDestName()+") :- up("+c.getSourceName()+"), closed("+c.getSwitcher()+").");
			}
		}
		for (GeneratorDescriptor g : generators) {
			assumptions.addAssumption_asString("generator("+g.getName()+").");
			assumptions.addAssumption_asString("up("+g.getSourceName()+") :- on("+g.getName()+").");
		}
		for (LoadDescriptor l : loads) {
			assumptions.addAssumption_asString("load("+l.getName()+").");
			assumptions.addAssumption_asString("switcher("+l.getSwitcher()+").");
			assumptions.addAssumption_asString("on("+l.getName()+") :- up("+l.getSourceName()+"), closed("+l.getSwitcher()+").");
		}
		
		return assumptions;
	}

	public void log_assumptions() {
		for (ConnDescriptor c : connections) {
			if (c.getSwitcher()==null) {
				System.out.println("up("+c.getSourceName()+") :- up("+c.getDestName()+"), f("+c.getFailureName()+").");
				System.out.println("up("+c.getDestName()+") :- up("+c.getSourceName()+"), f("+c.getFailureName()+").");
			} else {
				System.out.println("switcher("+c.getSwitcher()+").");
				System.out.println("up("+c.getSourceName()+") :- up("+c.getDestName()+"), closed("+c.getSwitcher()+").");	
				System.out.println("up("+c.getDestName()+") :- up("+c.getSourceName()+"), closed("+c.getSwitcher()+").");	
			}
		}
		for (GeneratorDescriptor g : generators) {
			System.out.println("generator("+g.getSourceName()+").");
			System.out.println("up("+g.getSourceName()+") :- on("+g.getName()+").");
		}
		for (LoadDescriptor l : loads) {
			System.out.println("load("+l.getName()+").");
			System.out.println("switcher("+l.getSwitcher()+").");
			System.out.println("on("+l.getName()+") :- up("+l.getSourceName()+"), closed("+l.getSwitcher()+").");
		}
	}
	
	public StateOfWorld getInitialState() throws ParseException, NotAllowedInAStateOfWorld {
		StateOfWorld w = new StateOfWorld();
		
		
		for (ConnDescriptor c : connections) {
			if (c.getSwitcher()!=null) {
				if (c.getState()==true)
					w.addFact_asString("closed("+c.getSwitcher()+").");
				else
					w.addFact_asString("open("+c.getSwitcher()+").");
			}
		}
		for (LoadDescriptor l : loads) {
			w.addFact_asString("closed("+l.getSwitcher()+").");
		}
		for (GeneratorDescriptor g : generators) {
			if (g.getState()==true)
				w.addFact_asString("on("+g.getName()+").");
			else
				w.addFact_asString("off("+g.getName()+").");
		}
		for (FailureDescription f : failures) {
			w.addFact_asString("f("+f.getFailureName()+").");
		}
		
		return w;
	}
	
	public void log_initial_state() {
		for (ConnDescriptor c : connections) {
			if (c.getSwitcher()!=null) {
				if (c.getState()==true)
					System.out.println("closed("+c.getSwitcher()+").");
				else
					System.out.println("open("+c.getSwitcher()+").");
			}
		}
		for (LoadDescriptor l : loads) {
			System.out.println("closed("+l.getSwitcher()+").");
		}
		for (GeneratorDescriptor g : generators) {
			if (g.getState()==true)
				System.out.println("on("+g.getName()+").");
			else
				System.out.println("off("+g.getName()+").");
		}
	}

	public FOLCondition[] getNodeConditions() {
		FOLCondition[] node_conditions = new FOLCondition[46];
		for (int i=0; i<46; i++) {
			node_conditions[i] = new FOLCondition(new DLPAtom("up",new Constant ("n"+(i+1))));
		}
		return node_conditions;
	}

	
	public FOLCondition[] getLoadConditions() {
		FOLCondition[] load_conditions = new FOLCondition[loads.size()];
		int i=0;
		for (LoadDescriptor l : loads) {
			load_conditions[i] = new FOLCondition(new DLPAtom("on",new Constant (l.getName())));
			i++;
		}
		return load_conditions;
	}
	public void log_load_conditions() {
		for (LoadDescriptor l : loads) {
			System.out.println("on("+l.getName()+").");
		}
	}
	public FOLCondition[] getGenConditions() {
		FOLCondition[] gen_conditions = new FOLCondition[generators.size()];
		int i=0;
		for (GeneratorDescriptor l : generators) {
			gen_conditions[i] = new FOLCondition(new DLPAtom("on",new Constant (l.getName())));
			i++;
		}
		return gen_conditions;
	}
	public void log_gen_conditions() {
		for (GeneratorDescriptor g : generators) {
			System.out.println("on("+g.getName()+").");
		}
	}
	public int[] getGenPower() {
		int[] gen_pow = new int[generators.size()];
		int i=0;
		for (GeneratorDescriptor l : generators) {
			gen_pow[i] = l.getPow();
			i++;
		}
		return gen_pow;
	}
	public int[] getLoadPower() {
		int[] load_pow = new int[loads.size()];
		int i=0;
		for (LoadDescriptor l : loads) {
			load_pow[i] = l.getPow();
			i++;
		}
		return load_pow;
	}
	
	public void log_current_state(StateOfWorld w) throws ParseException, NotAllowedInAnAssumptionSet {
		
		AssumptionSet assumptions = getAssumptions();
//		System.out.println("---assumptions----");
//		System.out.println(assumptions.toString());
//		System.out.println("---state----");
//		for (ExtDLPHead fact : w.getFactsList())
//			System.out.println(fact.toString()+".");

		FOLCondition[] load_conditions = getLoadConditions();
//		System.out.println("---load conditions----");
//		for (int i=0; i<load_conditions.length; i++) {
//			System.out.println(load_conditions[i].toString());
//		}
		FOLCondition[] gen_conditions = getGenConditions();
		
//		FOLCondition[] node_conditions = getNodeConditions();
		
		EntailOperator entail = EntailOperator.getInstance();
//		boolean[] noderesults = entail.entailsCondition(w, assumptions, node_conditions);
		boolean[] results = entail.entailsCondition(w, assumptions, load_conditions);
		boolean[] genresults = entail.entailsCondition(w, assumptions, gen_conditions);

//		for (int i=0; i<node_conditions.length; i++) {
//			if (noderesults[i]==false) System.out.println("node "+(i+1)+" is down");
//		}

		int i_gen=0;
		for (GeneratorDescriptor l : generators) {
			if (genresults[i_gen]==false) System.out.println("gen "+l.getName()+" is off");
			//gen_conditions[i] = new FOLCondition(new DLPAtom("on",new Constant (l.getName())));
			i_gen++;
		}
		
//		for (int i=0; i<genresults.length; i++) {
//			if (genresults[i]==false) System.out.println("gen "+(i+1)+" is off");
//		}
		
		int i=0;
		for (LoadDescriptor l : loads) {
			//load_conditions[i] = new FOLCondition(new DLPAtom("on",new Constant (l.getName())));
			if (results[i]==false) System.out.println("load "+l.getName()+" is off");
			i++;
		}
		
//		for (int i=0; i<results.length; i++) {
//			if (results[i]==false) System.out.println("load "+(i+1)+" is off");
//		}
	}
	
	public AbstractCapability generate_switch_on_generator(String name) {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant(name)));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant(name))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant(name)))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_"+name+"_generator_cap", main_on_evo, main_on_pre, null);
	}

	public AbstractCapability generate_switch_off_generator(String name) {
		/* switch_OFF_main_generator_cap
		 * PRE: on(main)
		 * SCENARIO mainOff: remove {on(main)}, add {off(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("on", new Constant(name)));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_off");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("off", new Constant(name))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("on", new Constant(name)))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_OFF_"+name+"_generator_cap", main_on_evo, main_on_pre, null);
	}
	
	public AbstractCapability generate_switch_on_aux2() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw15"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw15")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw15_generator_cap", main_on_evo, main_on_pre, null);
	}

	public AbstractCapability generate_switch_on_aux2_alt() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw16"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw16")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw16_generator_cap", main_on_evo, main_on_pre, null);
	}

	public AbstractCapability generate_switch_on_aux2_alt2() {
		/* switch_ON_main_generator_cap
		 * PRE: off(main)
		 * SCENARIO mainOn: remove {off(main)}, add {on(main)}
		*/
		Condition main_on_pre = new FOLCondition(new DLPAtom("off", new Constant("aux2")));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario main_on_evo1 = new CapabilityEvolutionScenario("gen_on");
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("on", new Constant("aux2"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw15"))) ) );
		main_on_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", new Constant("sw16"))) ) );
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("off", new Constant("aux2")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw15")))));
		main_on_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", new Constant("sw16")))));
		main_on_evo.add(main_on_evo1);		
		return new AbstractCapability("switch_ON_aux2_and_sw15_16_generator_cap", main_on_evo, main_on_pre, null);
	}
	
	public AbstractCapability generate_close_capability_for_switcher(String switch_name) {
		/* close_switch_<name>_cap
		 * PRE: open(<name>)
		 * SCENARIO iClosed: remove {open(<name>)}, add {closed(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("open", i_const));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iClosed");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("close_switch_"+switch_name+"_cap", main_on_evo, i_pre, null);
	}

	public AbstractCapability generate_open_capability_for_switcher(String switch_name) {
		/* open_switch_<name>_cap
		 * PRE: closed(<name>)
		 * SCENARIO iOpen: remove {closed(<name>)}, add {open(<name>)}
		*/
		Constant i_const = new Constant(switch_name);
		Condition i_pre = new FOLCondition(new DLPAtom("closed", i_const));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iOpen");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name+"_cap", main_on_evo, i_pre, null);
	}

	public AbstractCapability generate_capability_for_open_close(String switch_name1,String switch_name2) {
		Constant i_const1 = new Constant(switch_name1);
		Constant i_const2 = new Constant(switch_name2);
		
		Condition i_pre = new FOLCondition(new DLPAtom("closed", i_const1));
		List<EvolutionScenario> main_on_evo = new LinkedList<>();
		CapabilityEvolutionScenario i_evo_scenario = new CapabilityEvolutionScenario("iOpen");
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("open", i_const1)) ) );
		i_evo_scenario.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("closed", i_const2)) ) );
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("closed", i_const1))));
		i_evo_scenario.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("open", i_const2))));
		main_on_evo.add(i_evo_scenario);
		return new AbstractCapability("open_switch_"+switch_name1+"_close_switch_"+switch_name2+"_cap", main_on_evo, i_pre, null);
	}
	
}
