package pmrtest.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.graph.Edge;
import pmr.graph.EvolutionEdge;
import pmr.graph.Node;
import pmr.graph.NormalEdge;
import pmr.graph.OPNode;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;

public class EdgeTest {
	
	private StateOfWorld w1;
	private StateOfWorld w2;
	private StateOfWorld w3;
	private StateOfWorld w4;
	
	private WorldNode n1;
	private WorldNode n2;
	private WorldNode n3;
	private OPNode op1;
	
	private AbstractCapability cap1;
	private AbstractCapability cap2;
	private AbstractCapability cap3;

	private NormalEdge e1;
	private NormalEdge e2;
	private NormalEdge e3;
	
	private EvolutionEdge ev1;
	private EvolutionEdge ev2;
	
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
		this.n2 = new WorldNode(w3);
		this.n3 = new WorldNode(w1);
		this.op1 = new XORNode(cap1.getId(), 0);
		
		this.e1 = new NormalEdge(new WorldNode(null), this.n1, this.cap1.getId());
		this.e2 = new NormalEdge(new WorldNode(null), this.n1, this.cap1.getId());
		this.e3 = new NormalEdge(this.n1, this.n2, this.cap1.getId());
		
		this.ev1 = new EvolutionEdge(op1, n1, null);
	}
	
	@Test
	public void test_1() {
		assertEquals(true, this.e1.equals(this.e2));
	}

	@Test
	public void test_2() {
		assertEquals(false, this.e2.equals(this.e3));
	}
	
	@Test
	public void test_3() {
		assertEquals(true, new NormalEdge(new WorldNode(null), this.n1, this.cap1.getId()).equals(this.e2));
	}
}
