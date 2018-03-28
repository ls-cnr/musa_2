package org.icar.musa.applications.spsreconfiguration;

import java.util.LinkedList;
import java.util.List;

import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AssumptionSet;
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
}
