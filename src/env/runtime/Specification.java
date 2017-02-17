// CArtAgO artifact code for project musa_2_0

package runtime;

import java.util.LinkedList;
import java.util.List;

import cartago.*;
import layer.awareness.*;
import layer.awareness.goalmodel.*;
import layer.semantic.AssumptionSet;;

public class Specification extends Artifact {

	private String spec_id;
	private String domain_name;

	/* original data (maybe to-be-removed) */
	private List<Goal> goalset;
	private GoalModel goal_model;
	private AssumptionSet assumptions;
	
	/* data ready for messages */
	private String goalset_term;
	private String goal_model_term;
	private String assumptions_term;

	void init(String spec_id,String domain_name) {
		this.spec_id = spec_id;
		this.domain_name = domain_name;
		goalset = new LinkedList<Goal>();
		goal_model=null;
		assumptions = new AssumptionSet();
	}
	
	@OPERATION
	void load_from_DB() {
		//TODO
	}

	@LINK
	void get_goal_list_term() {
		//TODO
	}

	@LINK
	void get_goalmodel_term() {
		//TODO
	}

	@LINK
	void get_domain_assumption_term() {
		//TODO
	}

}

