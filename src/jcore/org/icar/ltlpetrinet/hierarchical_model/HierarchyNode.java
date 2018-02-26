package org.icar.ltlpetrinet.hierarchical_model;

import java.util.HashMap;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public abstract class HierarchyNode {
	private String name;
	private HashMap<String, HierarchyNode> childs;
	
	public final static double RMAX = 100;
	public final static double RINF = 1000;
	
	private double resist;

	public HierarchyNode(String name) {
		super();
		this.name = name;
		childs = new HashMap<String, HierarchyNode>();
		
		resist = 0;
	}
	
	public abstract void init();
	
	public String getName() {
		return name;
	}
	
	public abstract Set<Token> getInitialTokenSet();

	protected void add_dependency(String name, HierarchyNode child) {
		childs.put(name,child);
	}
	
	public HashMap<String, HierarchyNode> getDependencies() {
		return childs;
	}

	public HierarchyNode getDependencyByName(String net_name) {
		return childs.get(net_name);
	}
	
	// update tokens, according to the new signal
	public abstract void updateNet(StateOfWorld w,AssumptionSet assumptions);
	
	// check is the current node is compatible for parent transition firing
	protected abstract boolean retrieveTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean normal);

	public abstract PNStateEnum getNetState();
	
	// calculate the 'resistance' towards the total satisfaction
	public abstract void updateResistance(StateOfWorld w,AssumptionSet assumptions);
	
	public double getResistance() {
		return resist;
	}
	
	public void setResistance(double r) {
		this.resist = r;
	}

	public abstract String toStringWithScore();
	
	

	//public abstract double calculate_partial_satisfaction();
	
	//public abstract String toStringWithNet();

//	public abstract double calculate_partial_satisfaction_degree(boolean contribute_positively, StateOfWorld w, AssumptionSet assumptions);
//	public abstract String toStringWithScore(StateOfWorld w, AssumptionSet assumptions);
	
	
}
