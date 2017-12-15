package domain;

import java.util.ArrayList;

import datalayer.awareness.AbstractCapability;
import datalayer.awareness.AssumptionSet;
import datalayer.awareness.Requirements;
import datalayer.world.StateOfWorld;

public interface Scenario {
	public String getName();
	public AssumptionSet getDomainAssumptions();
	public Requirements getRequirements();
	public ArrayList<AbstractCapability> getCapabilitySet();
	public StateOfWorld getInitialState();
}
