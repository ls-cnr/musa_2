package org.icar.ltlpetrinet.hierarchical_model.template;

import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.AnnotatedPlace;
import org.icar.ltlpetrinet.annotated_pn.AnnotatedTransition;
import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.annotated_pn.UnaryTransition;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;
import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public class FinallyPN extends PNNode {
	private HierarchyNode dependency;

	public FinallyPN(String name, HierarchyNode subnode) {
		super(name);

		dependency = subnode;
		add_dependency(dependency.getName(), dependency);
	}

	@Override
	protected void build_pn() {
		AnnotatedPlace start = new AnnotatedPlace("start", PNStateEnum.WAIT_BUT_ERROR);
		AnnotatedPlace end = new AnnotatedPlace("end", PNStateEnum.ACCEPTED);
		
		AnnotatedTransition t1 = new UnaryTransition("start_to_end", dependency.getName(),AnnotatedTransition.NORMAL);

		pn.add(start);
		pn.add(end);
		pn.add(t1);
		pn.arc("a1", start, t1);
		pn.arc("a2", t1,end);
	}

	@Override
	public Set<Token> getInitialTokenSet() {
		Set<Token> tokens = dependency.getInitialTokenSet();
		tokens.add(new Token("start",getName()));
		return tokens;
	}
	
	@Override
	public void updateResistance(StateOfWorld w, AssumptionSet assumptions) {
		dependency.updateResistance(w, assumptions);
		
		PNStateEnum state = getNetState();
		if (state==PNStateEnum.WAIT_BUT_ERROR)
			setResistance(dependency.getResistance());
		else if (state==PNStateEnum.ACCEPTED)
			setResistance(0);
	}


//	@Override
//	protected void update_hops_to_accept_place() {
//		PNStateEnum state = getNetState();
//		if (state==PNStateEnum.WAIT_BUT_ERROR) 
//			setScore(1);
//		if (state==PNStateEnum.ACCEPTED) 
//			setScore(0);
//		
//	}
//
	public String toString() {
		return "[ F " + dependency.toString() + " ] ";
	}
	
	public String toStringWithScore() {
		return "[ F (r="+getResistance()+") " + dependency.toStringWithScore() + " ] ";
	}

//
//	public String toStringWithNet() {
//		return "[ F("+getName()+") " + dependency.toStringWithNet() + " ] ";
//	}

//	public String toStringWithScore(StateOfWorld w, AssumptionSet assumptions) {
//		return "[ F("+calculate_partial_satisfaction()+") " + dependency.toStringWithScore(w,assumptions) + " ] ";
//	}
//
//	@Override
//	public double calculate_partial_satisfaction_degree(boolean contribute_positively, StateOfWorld w, AssumptionSet assumptions) {
//		double sub_degree = dependency.calculate_partial_satisfaction_degree(true, w, assumptions);
//
//		if (contribute_positively) {
//			if (sub_degree == 1)
//				return 1;
//			else if (sub_degree==0)
//				return 0;
//			else 
//				return 0.5;
//		}
//		
//		if (!contribute_positively) {
//			if (sub_degree == 1)
//				return 0;
//			else if (sub_degree==0)
//				return 1;
//			else 
//				return 0.5;
//		}
//		
//		return 0;
//	}

}
