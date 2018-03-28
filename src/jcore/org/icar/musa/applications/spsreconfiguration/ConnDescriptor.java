package org.icar.musa.applications.spsreconfiguration;

public class ConnDescriptor {
	private int source;
	private int dest;
	private String switcher;
	private boolean state = true;
	
	public ConnDescriptor(int source, int dest) {
		super();
		this.source = source;
		this.dest = dest;
		this.switcher = null;
	}

	public ConnDescriptor(int source, int dest, String switcher) {
		super();
		this.source = source;
		this.dest = dest;
		this.switcher = switcher;
	}

	public ConnDescriptor(int source, int dest, String switcher, boolean open) {
		super();
		this.source = source;
		this.dest = dest;
		this.switcher = switcher;
		if (open)
			state = false;
	}

	public int getSource() {
		return source;
	}

	public int getDest() {
		return dest;
	}

	public String getSourceName() {
		return "n"+source;
	}

	public String getDestName() {
		return "n"+dest;
	}

	public String getSwitcher() {
		return switcher;
	}

	public String getFailureName() {
		int min = Math.min(source, dest);
		int max = Math.max(source, dest);
		
		return "b"+min+"_"+max;
	}

	public boolean getState() {
		return state;
	}
	
	
	
}
