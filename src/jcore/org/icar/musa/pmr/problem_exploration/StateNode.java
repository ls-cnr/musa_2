package org.icar.musa.pmr.problem_exploration;

import java.util.Comparator;

import org.icar.ltlpetrinet.supervisor.TokenConf;
import org.icar.musa.core.context.StateOfWorld;

public class StateNode extends WTSNode {
	private StateOfWorld state;
	
	private boolean isStartNode;
	private boolean isExitNode;
	private boolean isLoopable;
	private boolean isForbidden;
		
	private TokenConf tokens;
	private double goal_satisfaction_degree;
	
	
	public StateNode(StateOfWorld state) {
		super(state.hashCode());
		this.state = state;
		
		isStartNode=false;
		isExitNode=false;
		isLoopable=false;
		isForbidden=false;
	}

	public StateOfWorld getState() {
		return state;
	}


	public void setState(StateOfWorld state) {
		this.state = state;
	}


	public boolean isStartNode() {
		return isStartNode;
	}


	public void setStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}


	public boolean isExitNode() {
		return isExitNode;
	}


	public void setExitNode(boolean isExitNode) {
		this.isExitNode = isExitNode;
	}


	public boolean isLoopable() {
		return isLoopable;
	}


	public void setLoopable(boolean isLoopable) {
		this.isLoopable = isLoopable;
	}

	public boolean isForbidden() {
		return isForbidden;
	}

	public void setForbidden(boolean isForbidden) {
		this.isForbidden = isForbidden;
	}

	public TokenConf getTokens() {
		return tokens;
	}


	public void setTokens(TokenConf tokens) {
		this.tokens = tokens;
	}


	public double getGoal_satisfaction_degree() {
		return goal_satisfaction_degree;
	}


	public void setGoal_satisfaction_degree(double goal_satisfaction_degree) {
		this.goal_satisfaction_degree = goal_satisfaction_degree;
	}
	
	
	

	@Override
	public String toString() {
		return "StateNode [state=" + state + "]";
	}

	/* class comparator */
	public static Comparator<? super StateNode> getScoreComparator() {
		Comparator<StateNode> comp = new Comparator<StateNode>(){
			@Override
			public int compare(StateNode e1, StateNode e2){
				double op = e1.goal_satisfaction_degree - e2.goal_satisfaction_degree;
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
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		StateNode other = (StateNode) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	

}
