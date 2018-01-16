package org.icar.specification.LTLgoal;

public class LTLPriority implements GoalModelEntity  {
	private int priority;

	public LTLPriority(int priority) {
		super();
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "[" + priority + "]";
	}
	
	
}
