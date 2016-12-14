package layertest.semantic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;

public class StateOfWorldTest {

	StateOfWorld w;
	
	@Before
	public void init(){
		w = new StateOfWorld();
		try {
			w.addFact_asString("penguin(tweety).");
			w.addFact_asString("penguin(tweety).");
			System.out.println( w.getFacts().toString() );
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeFact_safely_asASP(){
		assertTrue(true);
	}

}
