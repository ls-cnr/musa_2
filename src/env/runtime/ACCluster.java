// CArtAgO artifact code for project musa_2_0

package runtime;

import cartago.*;

public class ACCluster extends Artifact {


	void init(int initialValue) {
	}

	@OPERATION
	void inc() {
		ObsProperty prop = getObsProperty("count");
		prop.updateValue(prop.intValue()+1);
		signal("tick");
	}
}

