package org.icar.ltlpetrinet.hierarchical_model;

import java.util.HashMap;

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
	
}
