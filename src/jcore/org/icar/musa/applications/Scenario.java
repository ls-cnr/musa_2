package org.icar.musa.applications;

import java.util.ArrayList;

import org.icar.musa.context.StateOfWorld;
import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;

public interface Scenario {
	public String getName();
	public AssumptionSet getDomainAssumptions();
	public Requirements getRequirements();
	public ArrayList<AbstractCapability> getCapabilitySet();
	public StateOfWorld getInitialState();
	public QualityAsset getQualityAsset();
}
