// CArtAgO artifact code for project musa_2_0

package selfconf;

import cartago.*;

@ARTIFACT_INFO(
		  outports = {
		    @OUTPORT(name = "graph")
		  }
	) 
public class ConfigSelector extends Artifact {

	void init() {
	}
	
	/* interface: MANAGE SOLUTION SEARCH */
	@OPERATION
	void runStep_SearchSolution() {
	}
	
	
	/* interface: SOLUTION */
	@OPERATION
	void getAbstractSolution() {
	}

	/* interface: BLACKBOARD */
	@OPERATION
	void markFailedService() {
	}
	
	

}

