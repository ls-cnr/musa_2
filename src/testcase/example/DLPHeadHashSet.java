package example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;

public class DLPHeadHashSet {

	private StateOfWorld set1;
	private StateOfWorld set2;


	@Before
	public void setUp(){
		this.set1 = new StateOfWorld();
		this.set2 = new StateOfWorld();
		try {
			set1.addFact_asString("penguin(tweety).");
			set1.addFact_asString("parrot(polly).");
			set1.addFact_asString("sparrow(sid).");
			set1.addFact_asString("broken_wing(sid).");
			set1.addFact_asString("ostrich(olga).");

			set2.addFact_asString("broken_wing(sid).");
			set2.addFact_asString("penguin(tweety).");
			set2.addFact_asString("parrot(polly).");
			set2.addFact_asString("ostrich(olga).");
			set2.addFact_asString("sparrow(sid).");


		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		assertEquals(true, this.set1.hashCode() == this.set2.hashCode());
	}

}
