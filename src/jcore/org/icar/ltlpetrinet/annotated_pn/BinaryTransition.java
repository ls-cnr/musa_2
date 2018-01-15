package org.icar.ltlpetrinet.annotated_pn;

public class BinaryTransition extends AnnotatedTransition {
	private String first_dep;
	private String second_dep;

	public BinaryTransition(String name,String first_dep,String second_dep) {
		super(name);
		this.first_dep = first_dep;
		this.second_dep = second_dep;
	}

}
