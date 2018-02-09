package org.icar.musa.pmr.problem_exploration;

import java.util.ArrayList;
import java.util.Comparator;

import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;

public class WTSExpansion extends WTS {	
	private static final long serialVersionUID = 1341666082643667454L;

	private String capability;
	private double score;
	
	private boolean multi_expansion;
	private boolean contain_forbidden;
	private boolean contain_exit;
	
	public WTSExpansion(String capability, StateNode root, NetHierarchy pnmodel) {
		super(root, pnmodel);
		this.capability = capability;
		multi_expansion=false;
		contain_forbidden=false;
		contain_exit=false;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isContain_forbidden() {
		return contain_forbidden;
	}

	public void setContain_forbidden(boolean contain_forbidden) {
		this.contain_forbidden = contain_forbidden;
	}

	public boolean isMulti_expansion() {
		return multi_expansion;
	}

	public void setMulti_expansion(boolean multi_expansion) {
		this.multi_expansion = multi_expansion;
	}

	public boolean isContain_exit() {
		return contain_exit;
	}

	public void setContain_exit(boolean contain_exit) {
		this.contain_exit = contain_exit;
	}
	
	public String getCapability() {
		return capability;
	}

	public ArrayList<WTSNode> getEvolutionNodes() {
		ArrayList<WTSNode> final_nodes = null;
		
		if (!multi_expansion) {
			final_nodes = getOutgoingNodes(getRoot());
		} else {
			final_nodes = new ArrayList<>();

			ArrayList<WTSNode> xor_nodes = getOutgoingNodes(getRoot());
			for (WTSNode xornode : xor_nodes) {
				ArrayList<WTSNode> evo_nodes = getOutgoingNodes(xornode);
				final_nodes.addAll(evo_nodes);
			}
		}
		
		return final_nodes;
	}
	
	public XorNode getXorNode() {
		XorNode xornode = null;
		if (multi_expansion) {
			ArrayList<WTSNode> final_nodes = getOutgoingNodes(getRoot());
			xornode = (XorNode) final_nodes.get(0);
		}
		return xornode;
	}

	public static Comparator<? super WTSExpansion> getScoreComparator() {
		Comparator<WTSExpansion> comp = new Comparator<WTSExpansion>(){
			@Override
			public int compare(WTSExpansion e1, WTSExpansion e2){
				double op = e1.score - e2.score;
				if (op>0) return 1;
				if (op<0) return -1;
				return 0;		
			}
		};
		return comp;
	}
	public static Comparator<? super WTSExpansion> getInverseScoreComparator() {
		Comparator<WTSExpansion> comp = new Comparator<WTSExpansion>(){
			@Override
			public int compare(WTSExpansion e1, WTSExpansion e2){
				double op = e2.score - e1.score;
				if (op>0) return 1;
				if (op<0) return -1;
				return 0;		
			}
		};
		return comp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getRoot() == null) ? 0 : getRoot().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		WTSExpansion other = (WTSExpansion) obj;
		if (getRoot() == null) {
			if (other.getRoot() != null)
				return false;
		} else if (!getRoot().equals(other.getRoot()))
			return false;
		return true;
	}
	
	

}
