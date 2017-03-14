// CArtAgO artifact code for project musa_2_0

package directory;

import java.util.ArrayList;
import java.util.List;

import cartago.*;

public class AgentDirectory extends Artifact {
	private List<String> agentsName;

	void init() {
		this.defineObsProperty("artifact_name", "null");
		agentsName = new ArrayList<String>();

		System.out.println("artefatto Directory Service creato");
	}
	
	@OPERATION
	void register(String agentName) {
		String name= new String(agentName);
		agentsName.add(name);
		System.out.println("ho registrato "+agentName);
	}

	@OPERATION
	void unregister() {
	}

	@OPERATION
	void broadcast_announce_WTS_creation(String spec_id, cartago.WorkspaceId workspace, String artifactName) {
		signal("announcement_WTS_creation",spec_id,workspace,artifactName);
	}
	
}

