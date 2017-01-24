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
		assertEquals(net.getHop(net.getFirst()), 9);
	}
	
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
		assertEquals(net.getHop(net.getFirst()), 16);
	}
	
}
