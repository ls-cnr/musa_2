package datalayer.world.configuration.distributed;

public class CapInfo {
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	private String src;
	private String dest;
	private String cap;
	private String agent;

	CapInfo(String a, String b, String c, String agent) {
		this.src = a;
		this.dest = b;
		this.cap = c;
		this.agent = agent;
	}

	@Override
	public boolean equals(Object obj) {
		CapInfo that = (CapInfo) obj;
		if (this.src.equals(that.src))
			if (this.dest.equals(that.dest))
				if (this.cap.equals(that.cap))
					if (this.agent.equals(that.agent))
					return true;
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + this.src + ", " + this.dest + ", " + this.cap + "," + this.agent + "]";
	}

}

