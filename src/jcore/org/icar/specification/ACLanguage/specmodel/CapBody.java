package org.icar.specification.ACLanguage.specmodel;

import org.icar.specification.ACLanguage.CapabilityEntity;

public class CapBody implements CapabilityEntity {
	private CapRelationFormula pre;
	private CapRelationFormula post;
	private CapabilityEvolution evo;
	
	public CapBody() {
		super();
		pre = null;
		post = null;
		evo = null;
	}

	public CapRelationFormula getPre() {
		return pre;
	}

	public void setPre(CapRelationFormula pre) {
		this.pre = pre;
	}

	public CapRelationFormula getPost() {
		return post;
	}

	public void setPost(CapRelationFormula post) {
		this.post = post;
	}

	public CapabilityEvolution getEvo() {
		return evo;
	}

	public void setEvo(CapabilityEvolution evo) {
		this.evo = evo;
	}

	
	
}
