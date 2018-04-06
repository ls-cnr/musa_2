package org.icar.musa.pmr.problem_exploration;

import org.icar.musa.runtime_entity.AssumptionSet;
import org.icar.musa.runtime_entity.QualityAsset;
import org.icar.musa.runtime_entity.Requirements;

public class ProblemSpecification {
	private AssumptionSet assumptions;
	private Requirements goal_specification;
	private QualityAsset quality_asset;
	
	private TerminationEnum termination_type;
	private long ms_maxtime=0;
	private int num_iterations=0;
	
	public ProblemSpecification(AssumptionSet assumptions, Requirements goal_specification,
			QualityAsset quality_asset) {
		super();
		this.assumptions = assumptions;
		this.goal_specification = goal_specification;
		this.quality_asset = quality_asset;
		
		termination_type=TerminationEnum.FULL_GOAL_ACHIEVEMENT;
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

	public TerminationEnum getTermination_type() {
		return termination_type;
	}

	
	public void setTermination_type(TerminationEnum termination_type) {
		this.termination_type = termination_type;
	}

	public long getMs_maxtime() {
		return ms_maxtime;
	}

	public void setMs_maxtime(long ms_maxtime) {
		this.ms_maxtime = ms_maxtime;
	}

	public int getNum_iterations() {
		return num_iterations;
	}

	public void setNum_iterations(int num_iterations) {
		this.num_iterations = num_iterations;
	}

	
	
	
	
}
