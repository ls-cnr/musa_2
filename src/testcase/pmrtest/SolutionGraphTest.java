package pmrtest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.MultipleExpansion;
import org.icar.musa.proactive_means_end_reasoning.NormalExpansion;
import org.icar.musa.proactive_means_end_reasoning.SolutionGraph;
import org.icar.musa.proactive_means_end_reasoning.wts.WTS;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.musa.proactive_means_end_reasoning.wts.XORNode;
import org.junit.Before;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class SolutionGraphTest {

	private SolutionGraph graph;
	
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
	private WorldNode n5;
	private WorldNode n6;
	
	private ExtendedNode e1;
	private ExtendedNode e2;
	private ExtendedNode e3;
	private ExtendedNode e4;
	private ExtendedNode e5;
	private ExtendedNode e6;
	
	private GraphExpansion ex0;
	private GraphExpansion ex1;
	private GraphExpansion ex2;
	private GraphExpansion ex3;
	private GraphExpansion ex4;
	private GraphExpansion ex5;
	private GraphExpansion ex6;
	private GraphExpansion ex7;
	
	private GraphExpansion ex1Repeat;
	
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
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w2 = new StateOfWorld();
	try {
		w2.addFact_asString("penguin(tweety).");
		w2.addFact_asString("penguin(tweety).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w3 = new StateOfWorld();
	try {
		w3.addFact_asString("penguin(tweety).");
		w3.addFact_asString("parrot(polly).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w4 = new StateOfWorld();
	try {
		w4.addFact_asString("sparrow(sid).");
		w4.addFact_asString("broken_wing(sid).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w5 = new StateOfWorld();
	try {
		w5.addFact_asString("Eagle(berry).");
		w5.addFact_asString("can_fly(berry).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w6 = new StateOfWorld();
	try {
		w6.addFact_asString("Eagle(sid).");
		w6.addFact_asString("broken_wing(sid).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	this.w7 = new StateOfWorld();
	try {
		w7.addFact_asString("sparrow(claire).");
		w7.addFact_asString("can_fly(claire).");
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (org.icar.musa.exception.NotAllowedInAStateOfWorld e) {
		e.printStackTrace();
	}
	

	this.n1 = new WorldNode(w1);
	this.n2 = new WorldNode(w2);
	this.n3 = new WorldNode(w3);
	this.n4 = new WorldNode(w4);
	this.n5 = new WorldNode(w5);
	this.n6 = new WorldNode(w6);
	
	this.e1 = new ExtendedNode(w1);
	this.e2 = new ExtendedNode(w2);
	this.e3 = new ExtendedNode(w3);
	this.e4 = new ExtendedNode(w4);
	this.e5 = new ExtendedNode(w5);
	this.e6 = new ExtendedNode(w6);
	
	ArrayList<ExtendedNode> ENodeList345 = new ArrayList<ExtendedNode>();
	ENodeList345.add(e3);
	ENodeList345.add(e4);
	ENodeList345.add(e5);
	
	ArrayList<ExtendedNode> ENodeList1 = new ArrayList<ExtendedNode>();
	ENodeList1.add(e1);
	
	ArrayList<ExtendedNode> ENodeList2 = new ArrayList<ExtendedNode>();
	ENodeList2.add(e2);
	
	ArrayList<ExtendedNode> ENodeList3 = new ArrayList<ExtendedNode>();
	ENodeList3.add(e3);
	
	ArrayList<ExtendedNode> ENodeList4 = new ArrayList<ExtendedNode>();
	ENodeList4.add(e4);
	
	ArrayList<ExtendedNode> ENodeList5 = new ArrayList<ExtendedNode>();
	ENodeList5.add(e5);
	
	ArrayList<ExtendedNode> ENodeList6 = new ArrayList<ExtendedNode>();
	ENodeList6.add(e6);

	this.ex0 = new NormalExpansion(new ExtendedNode(null), ENodeList1, cap1.getId());
	this.ex1 = new MultipleExpansion(e1, ENodeList345, cap1.getId());
	this.ex2 = new NormalExpansion(e2, ENodeList3, cap2.getId());
	this.ex3 = new NormalExpansion(e3, ENodeList6, cap3.getId());
	this.ex4 = new NormalExpansion(e4, ENodeList5, cap1.getId());
	this.ex5 = new MultipleExpansion(e5, ENodeList345, cap1.getId());
	this.ex6 = new NormalExpansion(e6, ENodeList3, cap1.getId());
	this.ex7 = new NormalExpansion(e6, ENodeList4, cap2.getId());
	
	this.graph = new SolutionGraph();
}

/* w1 ha lo stesso DLPHead Set di w2
 * n1 ha lo stesso StateOfWorld di n2
 * e1 ha lo stesso StateOfWorld di e2
 * ex1 ha lo stesso StateOfWorld di ex2
 * e5 ha null come StateOfWorld
 * x1 ha la stessa capability di x4
 * ex1 ha la stessa capability di ex4
 * ex1 contiene e5 che ha null come StateOfWorld
 * ex3 ha la stessa destNodeList di ex4
 * ex4 � di tipo MultipleExpansionNode
 * ex5 � di tipo MultipleExpansionNode ed ha e5 come source che ha null come StateOfWorld
 */

//Il nodo iniziale viene sempre riconosciuto tramite un new WorldNode(null)
	@Test
	public void addNodeTest(){
		assertEquals(true, this.graph.getWTSHashmap().containsKey(new WorldNode(ex0.getSource().getWorldState())));
		assertEquals(null, this.graph.getWTSHashmap().get(new WorldNode(null)).getWorldState());
		assertEquals(true, this.graph.getWTSHashmap().get(new WorldNode(null)).equals(new WorldNode(null)));

		this.graph.addNode(ex0);
		
		assertEquals(2,this.graph.getWTSHashmap().size());
		assertEquals(1, this.graph.getWTSHashmap().get(new WorldNode(null)).getOutcomingEdgeList().size());
	}
	
	@Test
	public void addNodeTest2(){
		this.graph.addNode(ex1);
		this.graph.addNode(ex2);
		assertEquals(true, this.graph.getWTSHashmap().containsKey(new WorldNode(ex2.getDestination().get(0).getWorldState())));
	}	
	
	//Dovrei aver aggiunto tutti gli stateOfWorld diversi: w1,w3,w4,w5,wnull = 5
	@Test
	public void addNodeTest3(){
		this.e3.setExit(true);
		this.graph.addNode(ex1);
		this.graph.addNode(ex2);
		this.graph.addNode(ex3);
		this.graph.addNode(ex4);
		this.graph.addNode(ex5);
		this.graph.addNode(ex6);
		this.graph.addNode(ex1);
		this.graph.addNode(ex2);
		this.graph.addNode(ex3);
		this.graph.addNode(ex4);
		this.graph.addNode(ex5);
		this.graph.addNode(ex6);
		assertEquals(5, this.graph.getWTSHashmap().size());
	}
	
	//WorldNode con OPNode all'interno : n4,n5,null
	@Test
	public void addNodeTest4(){
		this.e3.setExit(true);
		this.graph.addNode(ex1);
		this.graph.addNode(ex2);
		this.graph.addNode(ex3);
		this.graph.addNode(ex4);
		this.graph.addNode(ex5);
		this.graph.addNode(ex6);
		int a = 0;
		a = this.graph.getWTSHashmap().get(n4).getOPNodeList().size() + this.graph.getWTSHashmap().get(n5).getOPNodeList().size();
		assertEquals(0, a);
	}
	
	@Test
	public void exitNodeTest(){
		this.e2.setExit(true);
		this.graph.addNode(ex2);
		assertEquals(true, this.graph.getExitNodeMap().containsKey(new WorldNode(e2.getWorldState())));
	}
	
	@Test
	public void exitNodeTest2(){
		this.e2.setExit(true);
		this.graph.addNode(ex2);
		assertEquals(false, this.graph.getExitNodeMap().containsKey(new WorldNode(e3.getWorldState())));
	}
	
	@Test
	public void exitNodeTest3(){
		this.e3.setExit(true);
		this.graph.addNode(ex3);
		assertEquals(true, this.graph.getExitNodeMap().containsKey(new WorldNode(e3.getWorldState())));
	}
	
	
	//WNull -> n1, n1 -> n2 ma n1 = n2 quindi n1 -> n1, n2=n1 -> n3
	// n1 outlist = 2 archi, n1 inlist = 2 archi.
	//n1 = n2
	@Test
	public void edgeTest1(){
		this.graph.addNode(ex0);
		this.graph.addNode(ex1);
		this.graph.addNode(ex2);
		assertEquals(1, this.graph.getWTSHashmap().get(new WorldNode(null)).getOutcomingEdgeList().size());
		assertEquals(2, this.graph.getWTSHashmap().get(n1).getOutcomingEdgeList().size());
		assertEquals(2, this.graph.getWTSHashmap().get(n1).getIncomingEdgeList().size());
		
	}
	
	//WNull -> n1 -> n2 ma n1=n2 quindi n1 -> n1, n2=n1 -> n3, n3 -> n4, n4 nodo uscita
	//Percorso1: Wnull->n1->n1->n3->n4
	//Percorso2: Wnull->n1 (n1 checked)
//	@Test
//	public void visitTest1(){
//		this.e4.setExit(true);
//		this.graph.addNode(ex0);
//		this.graph.addNode(ex1);
//		this.graph.addNode(ex2);
//		this.graph.addNode(ex3);
//		ArrayList<ArrayList<WorldNode>> solution = this.graph.getSolutions();
//		assertEquals(1, solution.size());
//		assertEquals(4, solution.get(0).size());
//	}

	//Wnull->n1 -> n3, n3 -> n4_n5_n6, n4 -> n7, n5 -> n6, n7 -> n4_n5
//	@Test
//	public void visitTest2(){
//		this.e5.setExit(true);
//		this.graph.addNode(ex0);
//		this.graph.addNode(ex1);
//		this.graph.addNode(ex4);
//		this.graph.addNode(ex3);
//		this.graph.addNode(ex6);
//		this.graph.addNode(ex7);
//		assertEquals(6, this.graph.getWTSHashmap().size());
//		ArrayList<ArrayList<WorldNode>> solution = this.graph.getSolutions();
//		assertEquals(3, solution.size());
//	}
}
