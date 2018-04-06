package org.icar.specification.ACLanguage.specmodel;

import org.icar.specification.ACLanguage.CapabilityEntity;

public class Capability implements CapabilityEntity {
	private String name;
	private CapRelationFormula pre;
	private CapRelationFormula post;
	private CapabilityEvolution evo;
	
	public Capability(String name, CapRelationFormula pre, CapRelationFormula post, CapabilityEvolution evo) {
		super();
		this.name = name;
		this.pre = pre;
		this.post = post;
		this.evo = evo;
	}
	
	public Capability(String name) {
		super();
		this.name = name;
		this.pre = null;
		this.post = null;
		this.evo = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Capability [name=" + name + ", pre=" + pre + ", post=" + post + ", evo=" + evo + "]";
	}
	
	
	
}
