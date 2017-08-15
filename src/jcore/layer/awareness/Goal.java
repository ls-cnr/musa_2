package layer.awareness;

import layer.semantic.Condition;

/**
 * The Class Goal encapsulate the Goals. A goal is a desired change in the state
 * of the world an actor wants to achieve. A goal must have a trigger_condition
 * that describe when the goal should be pursued and a final_state that describe
 * the desired state of the world.
 *
 * @author icar-aose
 * @version 1.0.0
 */

public class Goal implements RunTimeEntity {

	/**
	 * The goal name. It identifies the Goal, so a Goal is equal if the name is
	 * equal
	 */
	private String name;

	/**
	 * The trigger condition. A goal can be pursued only if this condition is true
	 */
	private Condition trigger_condition;

	/**
	 * The final state describes how the state of the world should be after that the
	 * goal was achieved.
	 */
	private Condition final_state;

	/**
	 * Instantiates a new goal.
	 *
	 * @param trigger_condition
	 *            the trigger condition
	 * @param final_state
	 *            the final state
	 */
	public Goal(String name, Condition trigger_condition, Condition final_state) {
		super();
		this.name = name;
		this.trigger_condition = trigger_condition;
		this.final_state = final_state;
	}

	/**
	 * Instantiates a new goal.
	 *
	 * @param goalspec
	 *            the goalspec
	 */
	public Goal(String goalspec) {
		super();
		/* TODO */
	}

	/**
	 * Gets the trigger condition.
	 *
	 * @return the trigger condition
	 */
	public Condition getTrigger_condition() {
		return trigger_condition;
	}

	/**
	 * Gets the final state.
	 *
	 * @return the final state
	 */
	public Condition getFinal_state() {
		return final_state;
	}

	/**
	 * Export as goal SPEC.
	 *
	 * @return the string
	 */
	public String export_asGoalSPEC() {
		/* TODO */
		return "";
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Goal))
			return false;
		Goal g = (Goal) o;
		if (this.name.equals(g.getName()))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		int h = 0;
		for (int i = 0; i < name.length(); i++)
			h = (127 * h + name.charAt(i)) % 101;
		return h;
	}

}
