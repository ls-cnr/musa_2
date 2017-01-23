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
	
	@Ignore
	@Test
	public void testBasicTemplate() {
		net = new Net(model);
		for( Arc a : net.getPetrinet().getArcs() ){
			if( a.getDirection() == 1 ) 
					System.out.print( "|" + a.getPlace().getName()  + "| -----" + a.getName() + "-----> ");
			if( a.getDirection() == 0 ) 
				System.out.print( "|" + a.getTransition().getName()  + "| -----" + a.getName() + "-----> ");
		}
		System.out.println("|" + net.getPetrinet().getPlaces().get(net.getPetrinet().getPlaces().size() - 1).getName() + "|");
	}
	
	@Test
	public void testParallelTemplate() {
		ArrayList<Goal> gs = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		model.addAndArcs(new Goal("root", null, null), gs);
		net = new Net(model);
	}
	
}
