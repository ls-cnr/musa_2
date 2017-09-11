package directory;

import java.util.ArrayList;
import java.util.List;

import cartago.Artifact;
import cartago.OPERATION;

public class AgentDirectory extends Artifact {

	private List<String> agentsName;

	void init() {
		this.defineObsProperty("artifact_name", "null");
		agentsName = new ArrayList<String>();
	}

	@OPERATION
	void register(String agentName) {
		String name = new String(agentName);
		agentsName.add(name);
		//System.out.println("I registered "+agentName);
	}

	@OPERATION
	void unregister() {
		// TODO
	}

	@OPERATION
	void broadcast_announce_WTS_creation(String spec_id, String workspace, String artifactName) {
		signal("announcement_WTS_creation", spec_id, workspace, artifactName);
	}

}
