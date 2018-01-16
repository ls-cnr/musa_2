package org.icar.ltlpetrinet.hierarchical_model;

import java.util.HashSet;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.musa.core.Condition;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.fol_reasoner.EntailOperator;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AssumptionSet;

public class PredicateNode extends HierarchyNode {
	private Condition cond;
	
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
		
	}

	protected boolean retrieveTransitionDependency(StateOfWorld w, AssumptionSet assumptions, boolean normal) {
		boolean normal_test = false;
		Condition cond = this.getCondition();
		
		EntailOperator entail = EntailOperator.getInstance();
		normal_test =  entail.entailsCondition(w, assumptions, (FOLCondition) cond);

		if (normal)
			return normal_test;
		else
			return !normal_test;
	}

	@Override
	public PNStateEnum getNetState() {
		return null;
	}

	@Override
	public int calculate_partial_satisfaction() {
		return 0;
	}
}
