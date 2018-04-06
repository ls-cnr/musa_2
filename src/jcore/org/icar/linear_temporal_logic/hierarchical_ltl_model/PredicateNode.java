package org.icar.linear_temporal_logic.hierarchical_ltl_model;

import java.util.HashSet;
import java.util.Set;

import org.icar.linear_temporal_logic.annotated_petrinet.PNStateEnum;
import org.icar.linear_temporal_logic.ltl_supervisor.Token;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.context.fol_reasoner.EntailOperator;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.Condition;

public class PredicateNode extends HierarchyNode {
	private Condition cond;
	private boolean direct;
	
	public PredicateNode(String name, Condition condition) {
		super(name);
		this.cond = condition;
	}

	@Override
	public void init() {
	}

	public Condition getCondition() {
		return cond;
	}

	public void setCondition(Condition cond) {
		this.cond = cond;
	}

	@Override
	public Set<Token> getInitialTokenSet() {
		return new HashSet<Token>();
	}

	@Override
	public void updateNet(StateOfWorld w,AssumptionSet assumptions) {
		updateResistanceValue(w,assumptions);
	}
	@Override
	public void updateResistanceValue(StateOfWorld w, AssumptionSet assumptions) {
//		EntailOperator entail = EntailOperator.getInstance();
//		boolean normal_test =  entail.entailsCondition(w, assumptions, (FOLCondition) this.getCondition());
//		
	}

	protected boolean retrieveState_forFatherTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean direct_dependency) {
		boolean normal_test = false;
		Condition cond = this.getCondition();
		
		EntailOperator entail = EntailOperator.getInstance();
		normal_test =  entail.entailsCondition(w, assumptions, (FOLCondition) cond);
		
		direct = direct_dependency;
		
		setResistance(RMAX);
		if (normal_test==direct)
			setResistance(0);		
		
		if (direct_dependency)
			return normal_test;		
		else
			return !normal_test;		
	}

	@Override
	public PNStateEnum getNetState() {
		return null;
	}

	
//	@Override
//	public double calculate_partial_satisfaction() {
//		return 0;
//	}
//
	public String toString() {
		return "[ " + cond.toString() + " ] ";
	}

	public String toStringWithScore() {
		if (direct)
			return "[ "+cond.toString() +": r=" + getResistanceToFullAchievement() + " ] ";
		else
			return "[ "+cond.toString() +": r=" + getResistanceToFullAchievement() + "* ] ";
	}

//	@Override
//	public String toStringWithNet() {
//		return toString();
//	}
//	
//	public String toStringWithScore() {
//		return "[  ]";
//	}
//
//	@Override
//	public double calculate_partial_satisfaction_degree(boolean contribute_positively, StateOfWorld w, AssumptionSet assumptions) {
//		EntailOperator op = EntailOperator.getInstance();
//		boolean resp = op.entailsCondition(w, assumptions, (FOLCondition) cond);
//		if (resp == contribute_positively)
//				return 1;
//		return 0;
//	}
	
}
