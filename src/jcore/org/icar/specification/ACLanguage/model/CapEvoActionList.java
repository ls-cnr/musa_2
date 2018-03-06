package org.icar.specification.ACLanguage.model;

import java.util.ArrayList;

import org.icar.specification.ACLanguage.CapabilityEntity;

public class CapEvoActionList implements CapabilityEntity {
	private ArrayList<CapEvoAction> actions;

	public CapEvoActionList() {
		super();
		actions = new ArrayList<CapEvoAction>();
	}

	public ArrayList<CapEvoAction> getActions() {
		return actions;
	}

	@Override
	public String toString() {
		return "CapEvoActionList [actions=" + actions + "]";
	}
	
	
	
}
