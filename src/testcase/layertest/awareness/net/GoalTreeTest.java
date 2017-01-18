package layertest.awareness.net;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import layer.awareness.*;
import layer.awareness.goaltree.*;

import java.util.ArrayList;

public class GoalTreeTest {
	
	private GoalTree model;

	@Before	//Initializing the test
	public void init() {
		Goal g = new Goal("root", null, null);
		model = new GoalTree(g);
	}
	
	@Test //Testing root presence
	public void testRoot() {
		Goal h = new Goal("root", null, null);
		assertEquals(model.getRoot(), h);
	}
	
	@Test //Testing if it doesn't add any arc (the goal named root in the model is already in there, 
		  //so a new goal shouldn't be added and an arc to itself neither)
	public void testSameRoot() {
		ArrayList<Goal> a = new ArrayList<>();
		a.add(new Goal("root", null, null));	//New goal but already present in the model (Same name)
		try{
			model.addOrArcs(model.getRoot(), a);
		}
		catch( NodeNotFoundException ex ){}
		assertNull( model.getArcs(model.getRoot()));
	}
	
	@Test //Testing if it adds a new Arc
	public void testAddArc() {
		ArrayList<Goal> a = new ArrayList<>();
		Goal g = new Goal("second", null, null);
		a.add(g);
		try{
			model.addOrArcs(model.getRoot(), a);
		}
		catch( NodeNotFoundException ex ){}
		assertEquals( model.getArcs(model.getRoot()).get(0).getNextNode(), g);
	}
	
	@Test //Testing if it doesn't add the same Goal (equal by name) twice
	public void testSameGoals() {
		ArrayList<Goal> a = new ArrayList<>();
		Goal g = new Goal("second", null, null);	// These goals are considered
		Goal h = new Goal("second", null, null);	// equal
		a.add(g);
		a.add(h);
		try{
			model.addOrArcs(model.getRoot(), a);
		}
		catch( NodeNotFoundException ex ){}
		assertEquals( model.getArcs(model.getRoot()).size(), 1);
	}

}
