package org.icar.specification.ACLanguage.specmodel;

import org.icar.specification.ACLanguage.CapabilityEntity;

public class CapEvoScenario implements CapabilityEntity {
	private String name;
	private CapEvoActionList actions;
	
	public CapEvoScenario(String name) {
		super();
		this.name = name;
		actions = new CapEvoActionList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CapEvoActionList getActions() {
		return actions;
	}

	public void setActions(CapEvoActionList actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "S [name=" + name + ", actions=" + actions + "]";
	}
	
	
	
}
