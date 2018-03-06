package org.icar.specification.ACLanguage.model;

import java.util.ArrayList;

import org.icar.specification.ACLanguage.CapabilityEntity;

public class CapabilityEvolution implements CapabilityEntity {
	private ArrayList<CapEvoScenario> scenarios;

	public CapabilityEvolution() {
		super();
		scenarios = new ArrayList<CapEvoScenario>();
	}

	public ArrayList<CapEvoScenario> getScenarios() {
		return scenarios;
	}

	@Override
	public String toString() {
		return "E [scenarios=" + scenarios + "]";
	}
	
	
	
}
