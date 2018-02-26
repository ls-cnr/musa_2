package org.icar.ltlpetrinet.hierarchical_model.template;

import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.AnnotatedPlace;
import org.icar.ltlpetrinet.annotated_pn.AnnotatedTransition;
import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.annotated_pn.UnaryTransition;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;
import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public class UntilPN extends PNNode {
	private HierarchyNode left;
	private HierarchyNode right;
	private HierarchyNode sub;

	public UntilPN(String name,HierarchyNode subnode_left,HierarchyNode subnode_right) {
		super(name);

		left = subnode_left;
		right = subnode_right;
		add_dependency("left", left);
		add_dependency("right", right);
		
		sub = new AndOperator("sub1", left, right);
		add_dependency("and", sub);
	}

	@Override
	protected void build_pn() {
		AnnotatedPlace start = new AnnotatedPlace("start", PNStateEnum.WAIT_BUT_ERROR);
		AnnotatedPlace end1 = new AnnotatedPlace("end1", PNStateEnum.ACCEPTED);
		AnnotatedPlace end2 = new AnnotatedPlace("end2", PNStateEnum.ERROR);
		AnnotatedPlace end3 = new AnnotatedPlace("end3", PNStateEnum.ERROR);
		
		AnnotatedTransition t1 = new UnaryTransition("start_to_end1", "right",AnnotatedTransition.NORMAL);
		
		AnnotatedTransition t2 = new UnaryTransition("start_to_end2", "and",AnnotatedTransition.INVERSE);
		AnnotatedTransition t3 = new UnaryTransition("start_to_end3", "and",AnnotatedTransition.NORMAL);

		pn.add(start);
		pn.add(end1);
		pn.add(end2);
		pn.add(end3);
		pn.add(t1);
		pn.add(t2);
		pn.add(t3);
		pn.arc("a1", start, t1);
		pn.arc("a2", start, t2);
		pn.arc("a3", start, t3);
		pn.arc("a4", t1,end1);
		pn.arc("a5", t2,end2);
		pn.arc("a6", t3,end3);
	}

	@Override
	public Set<Token> getInitialTokenSet() {
		Set<Token> left_tokens = left.getInitialTokenSet();
		Set<Token> right_tokens = right.getInitialTokenSet();
		left_tokens.addAll(right_tokens);
		left_tokens.add(new Token("start",getName()));
		return left_tokens;
	}
	
	@Override
	public void updateResistance(StateOfWorld w, AssumptionSet assumptions) {
		left.updateResistance(w, assumptions);
		right.updateResistance(w, assumptions);

		PNStateEnum state = getNetState();
		if (state==PNStateEnum.WAIT_BUT_ERROR)
			setResistance(left.getResistance()+right.getResistance());
		else if (state==PNStateEnum.ACCEPTED)
			setResistance(0);
		else if (state==PNStateEnum.ERROR)
			setResistance(RINF);
	}


//	@Override
//	protected void update_hops_to_accept_place() {
//		PNStateEnum state = getNetState();
//		if (state==PNStateEnum.ERROR) 
//			setScore(-1);
//		if (state==PNStateEnum.WAIT_BUT_ERROR) 
//			setScore(1);
//		if (state==PNStateEnum.ACCEPTED) 
//			setScore(0);
//		
//	}
//
	public String toString() {
		return "[ U " + left.toString() + "," + right.toString() + " ] ";
	}

	public String toStringWithScore() {
		return "[ U (r="+getResistance()+") " + left.toStringWithScore() + "," + right.toStringWithScore() + " ] ";
	}

	//
//	public String toStringWithNet() {
//		return "[ U("+getName()+") " + left.toStringWithNet() + "," + right.toStringWithNet() + " ] ";
//	}

//	public String toStringWithScore(StateOfWorld w, AssumptionSet assumptions) {
//		return "[ U("+calculate_partial_satisfaction()+") " + left.toStringWithScore(w,assumptions) + "," + right.toStringWithScore(w,assumptions) + " ] ";
//	}
//
//	@Override
//	public double calculate_partial_satisfaction_degree(boolean contribute_positively, StateOfWorld w, AssumptionSet assumptions) {
//		double left_degree = left.calculate_partial_satisfaction_degree(true, w, assumptions);
//		double right_degree = left.calculate_partial_satisfaction_degree(true, w, assumptions);
//		
//		if (contribute_positively) {
//			if (left_degree+right_degree==0 | left_degree+right_degree==2)
//				return 0;
//			else if (left_degree==1 | right_degree==1)
//				return 1;
//			else 
//				return 0.5;
//		}
//		
//		if (!contribute_positively) {
//			if (left_degree+right_degree==0 | left_degree+right_degree==2)
//				return 1;
//			else if (left_degree==1 | right_degree==1)
//				return 0;
//			else 
//				return 0.5;
//		}
//		
//		return 0;
//	}

}
