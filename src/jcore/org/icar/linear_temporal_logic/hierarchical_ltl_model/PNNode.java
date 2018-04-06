package org.icar.linear_temporal_logic.hierarchical_ltl_model;

import java.util.HashSet;
import java.util.Set;

import org.icar.linear_temporal_logic.annotated_petrinet.AnnotatedPlace;
import org.icar.linear_temporal_logic.annotated_petrinet.AnnotatedTransition;
import org.icar.linear_temporal_logic.annotated_petrinet.PNStateEnum;
import org.icar.linear_temporal_logic.annotated_petrinet.UnaryTransition;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.runtime_entity.AssumptionSet;

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
					
					boolean fire = sub.retrieveState_forFatherTransitionDependency(w,assumptions,t.isNormal());
					if (fire) {
						ut.fire();
					}
				}
			}
		}
	}

	protected boolean retrieveState_forFatherTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean normal) {
		boolean normal_test = false;
		normal_test = ( getNetState() == PNStateEnum.ACCEPTED );
		
		if (normal)
			return normal_test;
		else
			return !normal_test;
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
	
}
