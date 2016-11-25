// CArtAgO artifact code for project musa_2_0

package selfconf;

import cartago.*;

@ARTIFACT_INFO(
		  outports = {
		    @OUTPORT(name = "graph"),
		    @OUTPORT(name = "directory")
		  }
	) 
public class GraphAccessManager extends Artifact {

	void init() {
	}
	
	/* interface: MANAGE ACCESS */
	@OPERATION
	void runStep_Auction() {
	}

	@OPERATION
	void flush() {
	}

	/* interface: GRANT ACCESS */
	@OPERATION
	void cache_changes() {
	}
	
	@OPERATION
	void bid() {
	}
	
	
}

