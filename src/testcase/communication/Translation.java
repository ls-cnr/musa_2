package communication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import communication.translator.JasonExtNode;
import datalayer.awareness.LTL.formulamodel.FormulaBTConstruction;
import datalayer.awareness.LTL.formulamodel.LTLGoal;
import datalayer.awareness.LTL.net.Nets;
import datalayer.awareness.LTL.net.TokensConfiguration;
import datalayer.world.StateOfWorld;
import jason.asSyntax.Term;
import net.sf.tweety.lp.asp.parser.ParseException;
import reasoner.probexp.ExtendedNode;


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
		} catch (exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		LTLGoal treeModel = FormulaBTConstruction.construct("G on(l1)");
		TokensConfiguration startingTokens = new TokensConfiguration(new Nets(treeModel));
		
		ExtendedNode enode = new ExtendedNode(w, startingTokens, 0, false, false);
		enode.setExit(false);
		Term term=JasonExtNode.object_to_term(enode);
		
		System.out.println(term);				
	}
}
