package org.icar.musa.core.runtime_entity;

import org.icar.musa.core.Requirements;

public class ProblemSpecification {
	private AssumptionSet assumptions;
	private Requirements goal_specification;
	private QualityAsset quality_asset;
	
	public ProblemSpecification(AssumptionSet assumptions, Requirements goal_specification,
			QualityAsset quality_asset) {
		super();
		this.assumptions = assumptions;
		this.goal_specification = goal_specification;
		this.quality_asset = quality_asset;
	}

	public AssumptionSet getAssumptions() {
		return assumptions;
	}

	public Requirements getGoal_specification() {
		return goal_specification;
	}

	public QualityAsset getQuality_asset() {
		return quality_asset;
	}
	
	
}
