package org.icar.ltlpetrinet.hierarchical_model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.AnnotatedPlace;
import org.icar.ltlpetrinet.annotated_pn.AnnotatedTransition;
import org.icar.ltlpetrinet.annotated_pn.BinaryTransition;
import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.annotated_pn.UnaryTransition;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

import petrinet.logic.Petrinet;
import petrinet.logic.Place;
import petrinet.logic.Transition;

public abstract class PNNode extends HierarchyNode {
	protected Petrinet pn;
	
	public PNNode(String name) {
		super(name);
		this.pn = new Petrinet(name);
	}
	
	public void init() {
		for (HierarchyNode node : this.getDependencies().values() ) {
			node.init();
		}
		build_pn();
	}
	
	protected abstract void build_pn();

	public Petrinet getPN() {
		return pn;
	}
	
	@Override
	public void updateNet(StateOfWorld w, AssumptionSet assumptions) {
		Set<AnnotatedTransition> fireable = identify_fireable_transitions_in_a_petrinet();
		for (AnnotatedTransition t : fireable) {
			if (t instanceof UnaryTransition) {
				UnaryTransition ut = (UnaryTransition) t;
				String net_name = ut.getDependency();
				HierarchyNode sub = this.getDependencyByName(net_name);
				if (sub!=null) {
					sub.updateNet(w,assumptions);
					
					boolean fire = sub.retrieveTransitionDependency(w,assumptions,t.isNormal());
					if (fire) {
						ut.fire();
					}
				}
	
			}
		}
	}
	
	private Set<AnnotatedTransition> identify_fireable_transitions_in_a_petrinet() {
		Set<AnnotatedTransition> transitions = new HashSet<AnnotatedTransition>();
		for (Transition t : pn.getTransitionsAbleToFire()) {
			if (t instanceof AnnotatedTransition) {
				transitions.add((AnnotatedTransition) t);
			}
		}
		return transitions;
	}

	protected boolean retrieveTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean normal) {
		boolean normal_test = false;
		normal_test = ( getNetState() == PNStateEnum.ACCEPTED);
		
		if (normal)
			return normal_test;
		else
			return !normal_test;
	}

	@Override
	public PNStateEnum getNetState() {
		PNStateEnum pessimistic = PNStateEnum.ERROR;
		
		for (Place p : pn.getPlaces()) {
			if (p.getTokens()>0) {
				if (p instanceof AnnotatedPlace) {
					AnnotatedPlace ap = (AnnotatedPlace) p;
					pessimistic = ap.getState();
				}
			}
		}	
		return pessimistic;
	}

	@Override
	public int calculate_partial_satisfaction() {
		int subscore = 0;
		
//		for (HierarchyNode sub : getDependencies().values()) {
//			subscore = subscore+sub.calculate_partial_satisfaction();
//		}
//		subscore = subscore/2;
		
		PNStateEnum state = getNetState();
		if (state==PNStateEnum.ACCEPTED) 
			return 10+subscore;
		if (state==PNStateEnum.WAIT_BUT_ACCEPTED) 
			return 5+subscore;
		if (state==PNStateEnum.WAIT_BUT_ERROR) 
			return 1+subscore;
		return 0;
	}

}
