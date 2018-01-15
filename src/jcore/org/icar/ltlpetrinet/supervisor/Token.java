package org.icar.ltlpetrinet.supervisor;

public class Token {
	private String myPlace;
	private String myNet;
	
	public Token(String myPlace, String myNet) {
		super();
		this.myPlace = myPlace;
		this.myNet = myNet;
	}

	public String getMyPlace() {
		return myPlace;
	}

	public void setMyPlace(String myPlace) {
		this.myPlace = myPlace;
	}

	public String getMyNet() {
		return myNet;
	}

	public void setMyNet(String myNet) {
		this.myNet = myNet;
	}

	@Override
	public String toString() {
		return "token [" + myPlace + " in " +myNet + "]";
	}
	
	
}
