package layer.awareness;

import layer.semantic.Condition;

public class Goal implements RunTimeEntity {
	private Condition trigger_condition;
	private Condition final_state;
	
	public Goal(Condition trigger_condition, Condition final_state) {
		super();
		this.trigger_condition = trigger_condition;
		this.final_state = final_state;
	}

	public Goal(String goalspec) {
		super();
		/* TODO */
	}

	public Condition getTrigger_condition() {
		return trigger_condition;
	}

	public Condition getFinal_state() {
		return final_state;
	}
	
	public String export_asGoalSPEC() {
		/* TODO */
		return "";
	}
	
}
