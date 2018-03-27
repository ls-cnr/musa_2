package org.icar.musa.applications;

import java.util.ArrayList;

import org.icar.musa.core.Requirements;
import org.icar.musa.core.context.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import org.icar.musa.core.runtime_entity.QualityAsset;

public interface Scenario {
	public String getName();
	public AssumptionSet getDomainAssumptions();
	public Requirements getRequirements();
	public ArrayList<AbstractCapability> getCapabilitySet();
	public StateOfWorld getInitialState();
	public QualityAsset getQualityAsset();
}
