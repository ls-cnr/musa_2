package org.icar.musa.applications.spsreconfiguration;

public class LoadDescriptor {
	public static final int VITAL=0;
	public static final int SEMIVITAL=1;
	public static final int NONVITAL=2;
	
	private int source;
	private int id;
	private int pow;
	private int type;
	private int priority;
	
	public LoadDescriptor(int source, int id, int pow, int type, int priority) {
		super();
		this.source = source;
		
		this.id = id;
		this.pow = pow;
		this.type = type;
		this.priority = priority;
	}

	public String getName() {
		return "l"+id;
	}

	public String getSourceName() {
		return "n"+source;
	}

	public int getPow() {
		return pow;
	}

	public void setPow(int pow) {
		this.pow = pow;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getSwitcher() {
		return "sw_"+id;
	}
	
	
	
}
