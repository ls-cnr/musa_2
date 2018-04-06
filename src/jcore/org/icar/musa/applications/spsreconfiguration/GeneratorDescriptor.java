package org.icar.musa.applications.spsreconfiguration;

public class GeneratorDescriptor {
	private int source;
	private String name;
	private int pow;
	private boolean state = true;
	
	public GeneratorDescriptor(int source, String name, int pow) {
		super();
		this.source = source;
		this.name = name;
		this.pow = pow;
	}
	public GeneratorDescriptor(int source, String name, int pow, boolean off) {
		super();
		this.source = source;
		this.name = name;
		this.pow = pow;
		if (off)
			state=false;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSourceId() {
		return source;
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

	public boolean getState() {
		return state;
	}
	
	
}
