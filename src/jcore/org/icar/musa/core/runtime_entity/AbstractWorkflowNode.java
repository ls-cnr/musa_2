package org.icar.musa.core.runtime_entity;

public class AbstractWorkflowNode {
	private String abstract_cap_name;
	private String agent;
	private String src;
	private String dest;

	public String getAbstract_cap_name() {
		return abstract_cap_name;
	}

	public void setAbstract_cap_name(String abstract_cap_name) {
		this.abstract_cap_name = abstract_cap_name;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abstract_cap_name == null) ? 0 : abstract_cap_name.hashCode());
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractWorkflowNode other = (AbstractWorkflowNode) obj;
		if (abstract_cap_name == null) {
			if (other.abstract_cap_name != null)
				return false;
		} else if (!abstract_cap_name.equals(other.abstract_cap_name))
			return false;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		return true;
	}
	
	public String toString() {
		return abstract_cap_name+"/"+agent;
	}

}
