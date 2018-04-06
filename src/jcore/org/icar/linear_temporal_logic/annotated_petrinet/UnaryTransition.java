package org.icar.linear_temporal_logic.annotated_petrinet;

public class UnaryTransition extends AnnotatedTransition {
	private String dependency;
	
	public UnaryTransition(String name, String dependency) {
		super(name);
		this.dependency = dependency;
	}

	public UnaryTransition(String name, String dependency,boolean normal) {
		super(name,normal);
		this.dependency = dependency;
	}

	public String getDependency() {
		return dependency;
	}

}
