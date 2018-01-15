package org.icar.ltlpetrinet.annotated_pn;

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
