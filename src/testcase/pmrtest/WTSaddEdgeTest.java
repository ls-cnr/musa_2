package pmrtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;
import net.sf.tweety.lp.asp.parser.ParseException;
import pmr.WTS;
import pmr.graph.Edge;
import pmr.graph.Node;
import pmr.graph.NormalEdge;
import pmr.graph.WorldNode;
import pmr.graph.XORNode;

public class WTSaddEdgeTest {
		private WTS wts;
		
		private StateOfWorld w1;
		private StateOfWorld w2;
		private StateOfWorld w3;
		private StateOfWorld w4;
		private StateOfWorld w5;
		private StateOfWorld w6;
		private StateOfWorld w7;
		
		private WorldNode n1;
		private WorldNode n2;
		private WorldNode n3;
		private WorldNode n4;
		
		private XORNode x1;
		private XORNode x2;
		private XORNode x3;
		private XORNode x4;
		
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
		this.w5 = new StateOfWorld();
		try {
			w5.addFact_asString("Eagle(berry).");
			w5.addFact_asString("can_fly(berry).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w6 = new StateOfWorld();
		try {
			w6.addFact_asString("Eagle(sid).");
			w6.addFact_asString("broken_wing(sid).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}
		this.w7 = new StateOfWorld();
		try {
			w7.addFact_asString("sparrow(claire).");
			w7.addFact_asString("can_fly(claire).");
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
		this.n4 = new WorldNode(w2);

		this.x1 = new XORNode(w4);
		this.x2 = new XORNode(w5);
		this.x3 = new XORNode(w6);
		this.x4 = new XORNode(w6);
		this.wts = new WTS();
	}

	@Test
	public void testNormalEdge_2SameEdges() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		assertEquals(false, wts.addEdge(new WorldNode(null), this.n1, this.cap1));
	}
	
	@Test
	public void testNormalEdge_1_DifferentNodesDifferentEdges() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addNode(this.n2);
		assertEquals(true, wts.addEdge(this.n1, this.n2, this.cap1));
	}
	
	@Test
	public void testNormalEdge_2_IncomingListSize() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		assertEquals(1, this.n1.getIncomingEdgeList().size());
	}
	
	@Test
	public void testNormalEdge_3_SimpleEdgeTest() {
		wts.addNode(this.n1);
		Edge temp = new NormalEdge(new WorldNode(null), this.n1, this.cap1);
		assertEquals(true, temp.equals(new NormalEdge(new WorldNode(null), this.n1, this.cap1)));
		/*wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		assertEquals(true, this.n1.getIncomingEdgeList().contains(temp));
		assertEquals(2, this.n1.getIncomingEdgeList().size());*/
	}
	
	@Test
	public void testNormalEdge_4_ExternalEdgeInsideIncomingList() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		Edge temp = new NormalEdge(new WorldNode(null), this.n1, this.cap1);
		assertEquals(true, wts.getWTS().get(this.n1).getIncomingEdgeList().contains(temp));
	}
	
	@Test
	public void testNormalEdge_5_IncomingListSize_2() {
		wts.addNode(this.n1);
		wts.addNode(this.n2);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addEdge(this.n2, this.n1, this.cap1);
		wts.addEdge(this.n2, this.n1, this.cap1);
		wts.addEdge(this.n1, this.n2, this.cap2);
		assertEquals(2,this.n1.getIncomingEdgeList().size());
	}
	
	@Test
	public void testNormalEdge_6_2SameEdges_2() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addNode(this.n2);
		wts.addEdge(this.n2, this.n1, this.cap1);
		assertEquals(false, wts.addEdge(this.n2, this.n1, this.cap1));
	}
	
	@Test
	public void testNormalEdge_7_WTSSize() {
		wts.addNode(this.n1);
		assertEquals(2, wts.getWTS().size());
	}
	
	@Test
	public void testNormalEdge_8_OutcomingListSize() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addNode(this.n2);
		wts.addEdge(new WorldNode(null), this.n2, this.cap2);
		assertEquals(2, wts.getWTS().get(new WorldNode(null)).getOutcomingEdgeList().size());
	}
	
	@Test
	public void testNormalEdge_9_ContainsNode() {
		wts.addNode(this.n1);
		wts.addEdge(new WorldNode(null), this.n1, this.cap1);
		wts.addNode(this.n2);
		wts.addEdge(new WorldNode(null), this.n2, this.cap2);
		assertEquals(true, wts.getWTS().containsKey(new WorldNode(null)));
	}
	
	@Test
	// archi stessa Capability stesso evolution Scenario
	public void testEvolutionEdge_2SameEdges() {
		wts.addNode(this.x1);
		wts.addEdge(new WorldNode(null), this.x1, this.cap1, null);
		assertEquals(false, wts.addEdge(new WorldNode(null), this.x1, this.cap1, null));
	}
	
	@Test
	//Nodi diversi archi diversi
	public void testEvolutionEdge_1_DifferentNodesDifferentEdges() {
		wts.addNode(this.x1);
		wts.addEdge(new WorldNode(null), this.x1, this.cap1, null);
		wts.addNode(this.x2);
		wts.addEdge(new WorldNode(null), this.x2, this.cap2, null);
		assertEquals(true, wts.getWTS().containsKey(this.x2));
	}
	
	/* NON POSSO TESTARLO
	 * @Test
	//Archi con stessa capability ma diverso scenario
	public void testEvolutionEdge_2_DifferentNodesSameEdges() {
		wts.addNode(this.x1);
		wts.addEdge(new WorldNode(null), this.x1, this.cap1, null);
		wts.addEdge(new WorldNode(null), this.x1, this.cap1, null);
		assertEquals(false, wts.getWTS().containsKey(this.x2));
	}*/
}