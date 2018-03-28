package org.icar.musa.applications.spsreconfiguration;

public class NodeDescription {
	private int id;

	public NodeDescription(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return "n"+id;
	}
	
	
}
