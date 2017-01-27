package layertest.awareness.net;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import layer.awareness.*;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.Net;
import petrinet.logic.*;

import java.util.ArrayList;

public class NetTest {

	private GoalModel model;
	private Net net;
	
	
	
	@Before
	public void init() {
		model = new GoalModel(new Goal("root", null, null));
	}
	
	@Test
	public void testParallelTemplate() {
		ArrayList<Goal> gs = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		model.addAndArcs(new Goal("root", null, null), gs);
		net = new Net(model);
	}
	
	@Ignore
	@Test
	public void testHops1() {
		ArrayList<Goal> gs = new ArrayList<>();
		ArrayList<Goal> gsor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		
		model.addAndArcs(new Goal("root", null, null), gs);
		model.addOrArcs(new Goal("terzo", null, null), gsor);
		
		
		net = new Net(model);
		ArrayList<String> t1 = new ArrayList<>();
		t1.add(net.getFirst().getName());
		assertEquals(net.hop(t1), 8);
	}
	
	@Ignore
	@Test
	public void testHops2() {
		ArrayList<Goal> gs = new ArrayList<>();
		ArrayList<Goal> gsor = new ArrayList<>();
		ArrayList<Goal> gsandor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		gsandor.add(new Goal("sesto", null, null));
		gsandor.add(new Goal("settimo", null, null));
		
		model.addAndArcs(new Goal("root", null, null), gs);
		model.addOrArcs(new Goal("terzo", null, null), gsor);
		model.addAndArcs(new Goal("quinto", null, null), gsandor);
		
		net = new Net(model);
		//assertEquals(net.getHop(net.getFirst()), 16);
	}
	
	@Test
	public void testHops3() {
		ArrayList<Goal> gs = new ArrayList<>();
		ArrayList<Goal> gsor = new ArrayList<>();
		ArrayList<Goal> gsandor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		gsandor.add(new Goal("sesto", null, null));
		gsandor.add(new Goal("settimo", null, null));
		
		model.addAndArcs(new Goal("root", null, null), gs);
		model.addOrArcs(new Goal("terzo", null, null), gsor);
		model.addAndArcs(new Goal("quinto", null, null), gsandor);
		
		net = new Net(model);
		
		ArrayList<String> first = new ArrayList<>();
		first.add(net.getFirst().getName());
		assertEquals(net.hop(first), 12);
		
		ArrayList<String> blu = new ArrayList<>();
		blu.add("p1");
		blu.add("p6");
		assertEquals(net.hop(blu), 4);
		
		ArrayList<String> rosso = new ArrayList<>();
		rosso.add("p10");
		rosso.add("p11");
		rosso.add("p3");
		assertEquals(net.hop(rosso), 5);
		
		ArrayList<String> verde = new ArrayList<>();
		verde.add("p5");
		verde.add("p3");
		assertEquals(net.hop(verde), 8);
	}
	
}
