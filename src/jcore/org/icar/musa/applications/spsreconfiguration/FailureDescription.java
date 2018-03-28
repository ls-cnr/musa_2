package org.icar.musa.applications.spsreconfiguration;

public class FailureDescription {
	private int source;
	private int dest;
	
	public FailureDescription(int source, int dest) {
		super();
		this.source = source;
		this.dest = dest;
	}
	
	public String getFailureName() {
		int min = Math.min(source, dest);
		int max = Math.max(source, dest);
		
		return "b"+min+"_"+max;
	}

}
