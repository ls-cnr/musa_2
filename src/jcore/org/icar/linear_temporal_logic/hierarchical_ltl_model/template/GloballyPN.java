package org.icar.linear_temporal_logic.hierarchical_ltl_model.template;

import java.util.Set;

import org.icar.linear_temporal_logic.annotated_petrinet.AnnotatedPlace;
import org.icar.linear_temporal_logic.annotated_petrinet.AnnotatedTransition;
import org.icar.linear_temporal_logic.annotated_petrinet.PNStateEnum;
import org.icar.linear_temporal_logic.annotated_petrinet.UnaryTransition;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.HierarchyNode;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.PNNode;
import org.icar.linear_temporal_logic.ltl_supervisor.Token;
import org.icar.musa.context.StateOfWorld;
import org.icar.musa.runtime_entity.AssumptionSet;

public class GloballyPN extends PNNode {
	private HierarchyNode dependency;

	public GloballyPN(String name, HierarchyNode subnode) {
		super(name);
		dependency = subnode;
		add_dependency(dependency.getName(), dependency);
	}

	@Override
	protected void build_pn() {
		AnnotatedPlace start = new AnnotatedPlace("start", PNStateEnum.WAIT_BUT_ACCEPTED);
		AnnotatedPlace end = new AnnotatedPlace("end", PNStateEnum.ERROR);
		
		AnnotatedTransition t1 = new UnaryTransition("start_to_end", dependency.getName(),AnnotatedTransition.INVERSE);
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
	public void updateResistanceValue(StateOfWorld w, AssumptionSet assumptions) {
		dependency.updateResistanceValue(w, assumptions);

		PNStateEnum state = getNetState();
		if (state==PNStateEnum.WAIT_BUT_ACCEPTED)
			setResistance(0);
		else if (state==PNStateEnum.ERROR)
			setResistance(RINF);
	}


	public String toString() {
		return "[ G " + dependency.toString() + " ] ";
	}

	public String toStringWithScore() {
		return "[ G (r="+getResistanceToFullAchievement()+") " + dependency.toStringWithScore() + " ] ";
	}

}
