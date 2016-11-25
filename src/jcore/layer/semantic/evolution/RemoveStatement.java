package layer.semantic.evolution;

import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.syntax.DLPHead;

public class RemoveStatement implements EvolutionOperator {
	private DLPHead new_statement;
	
	public RemoveStatement(DLPHead statement) {
		this.new_statement = statement;
	}
	public RemoveStatement(String statement) {
		/* TODO */;
	}
	

	public StateOfWorld apply_transformation(StateOfWorld w) {
		w.removeFact_safely_asASP(new_statement);
		return w;
	}

}
