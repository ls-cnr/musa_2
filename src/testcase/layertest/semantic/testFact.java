package layertest.semantic;

import communication.translator.ExtDLPHead;
import datalayer.world.StateOfWorld;
import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.lp.asp.parser.ParseException;

public class testFact {

	public static void main(String[] args) {
		StateOfWorld w3;
		w3 = new StateOfWorld();
		try {
			w3.addFact_asString("ciao(francesco).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		for (ExtDLPHead f : w3.getFacts()) {
			f.getTerms();
			for (Term<?> t: f.getTerms()) {
				System.out.println(">"+t.toString());
			}
		}

	}

}
