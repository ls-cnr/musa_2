package layer.semantic.evolution;

import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;

public class AddStatement implements  EvolutionOperator {
	private DLPHead new_statement;

	public AddStatement(DLPHead statement) {
		this.new_statement = statement;
	}
	public AddStatement(String statement) {
		/* TODO */
	}

	public StateOfWorld apply_transformation(StateOfWorld w) {
		w.addFact_asASP(new_statement);
		return w;
	}

}
