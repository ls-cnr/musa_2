package org.icar.ltlpetrinet.hierarchical_model;

import org.icar.ltlpetrinet.supervisor.TokenConf;

public class NetHierarchy {
	private HierarchyNode root;
	
	public NetHierarchy(HierarchyNode root) {
		super();
		this.root = root;
	}

	public String getName() {
		return root.getName();
	}
	
	public TokenConf getInitialTokenConfiguration() {
		TokenConf tokens = new TokenConf(getName(),root.getInitialTokenSet());
		return tokens;
	}

	public HierarchyNode getRoot() {
		return root;
	}
	
	public String toString() {
		return root.toString();
	}

	public String toStringWithScore() {
		return root.toStringWithScore();
	}

//	public String toStringWithNet() {
//		return root.toStringWithNet();
//	}

//	public String toStringWithScore(StateOfWorld w, AssumptionSet assumptions) {
//		return root.toStringWithScore(w,assumptions);
//	}

}
