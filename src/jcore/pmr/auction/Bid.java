package pmr.auction;

public class Bid {
	private String agent;
	private double value;
	
	public Bid(String agent, double value) {
		super();
		this.agent = agent;
		this.value = value;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
