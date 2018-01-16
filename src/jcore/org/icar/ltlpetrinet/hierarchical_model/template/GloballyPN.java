package org.icar.ltlpetrinet.hierarchical_model.template;

import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.AnnotatedPlace;
import org.icar.ltlpetrinet.annotated_pn.AnnotatedTransition;
import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.annotated_pn.UnaryTransition;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;
import org.icar.ltlpetrinet.supervisor.Token;

public class GloballyPN extends PNNode {
	private HierarchyNode dependency;

	public GloballyPN(String name, HierarchyNode subnode) {
		super(name);
		dependency = subnode;
		add_dependency("one"+dependency.getName(), dependency);
	}

	@Override
	protected void build_pn() {
		AnnotatedPlace start = new AnnotatedPlace("start", PNStateEnum.WAIT_BUT_ACCEPTED);
		AnnotatedPlace end = new AnnotatedPlace("end", PNStateEnum.ERROR);
		
		AnnotatedTransition t1 = new UnaryTransition("start_to_end", "one"+dependency.getName(),AnnotatedTransition.INVERSE);
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


}
