package pmrtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.WTS;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public class WTSaddNodeTest {
	private StateOfWorld w1;
	private StateOfWorld w2;
	private StateOfWorld w3;
	private StateOfWorld w4;
	
	private Node n1;
	private Node n2;
	private Node n3;
	private Node n4;
	
	private AbstractCapability cap1;
	private AbstractCapability cap2;
	private AbstractCapability cap3;
	
	@Before
	public void setUp(){
		this.cap1 = new AbstractCapability("uno",null,null,null);
		this.cap2 = new AbstractCapability("due",null,null,null);
		this.cap3 = new AbstractCapability("tre",null,null,null);
		
		this.w1 = new StateOfWorld();
		try {
			w1.addFact_asString("penguin(tweety).");
			w1.addFact_asString("penguin(tweety).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w2 = new StateOfWorld();
		try {
			w2.addFact_asString("penguin(tweety).");
			w2.addFact_asString("penguin(tweety).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w3 = new StateOfWorld();
		try {
			w3.addFact_asString("penguin(tweety).");
			w3.addFact_asString("parrot(polly).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w4 = new StateOfWorld();
		try {
			w4.addFact_asString("sparrow(sid).");
			w4.addFact_asString("broken_wing(sid).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		
		//Radice(n1) due rami figli: (n2) (n3), figlio(n2) : un ramo figlio(n4)
		//n4 contiene uno stato del mondo uguale a n3, quindi l'ipotetico figlio di n2 è in realtà n3, quindi
		//dovrebbe nascere un nuovo arco da (n2) a (n3)
		this.n1 = new WorldNode(w4);
		this.n2 = new WorldNode(w3, this.n1, this.cap1);
		this.n3 = new WorldNode(w1, this.n1, this.cap2);
		this.n4 = new WorldNode(w2, this.n2, this.cap3);
	}

	@Test
	public void testAddNode() {
		WTS temp = new WTS();
		temp.addNode(this.n1);
		temp.addNode(this.n2);
		temp.addNode(this.n3);
		assertEquals(false,temp.addNode(this.n4));
	}

}
