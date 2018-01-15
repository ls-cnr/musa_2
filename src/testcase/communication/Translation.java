package communication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.icar.musa.agent_communication.translator.JasonExtNode;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.specification.linear_temporal_logic.formulamodel.FormulaBTConstruction;
import org.icar.specification.linear_temporal_logic.formulamodel.LTLGoal;
import org.icar.specification.linear_temporal_logic.net.PNHierarchy;
import org.icar.specification.linear_temporal_logic.net.TokenConf;
import org.junit.Test;

import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;


public class Translation {

	@Test
	public void test_ExtendedNode_to_term() {
		StateOfWorld w = new StateOfWorld();
		try {
			w.addFact_asString("order(an_order).");
			w.addFact_asString("available(an_order).");
			w.addFact_asString("user(a_user).");
			w.addFact_asString("user_data(the_user_data).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		LTLGoal treeModel = FormulaBTConstruction.construct("G on(l1)");
		TokenConf startingTokens = new TokenConf(new PNHierarchy(treeModel));
		
		ExtendedNode enode = new ExtendedNode(w, startingTokens, 0, false, false);
		enode.setExit(false);
		Term term=JasonExtNode.object_to_term(enode);
		
		System.out.println(term);				
	}
}
