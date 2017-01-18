package layertest.awareness.net;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import layer.awareness.*;
import layer.awareness.goaltree.*;
import petrinet.logic.*;

import java.util.ArrayList;

public class NetTest {

	private GoalTree tree;
	private GoalModel model;
	private Net net;
	
	
	
	@Before
	public void init() {
		tree = new GoalTree(new Goal("root", null, null));
		model = new GoalModel(tree);
		net = new Net(model);
	}
	
	@Ignore
	@Test
	public void testBasicTemplate() {
		net.construct();
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
		try{
			ArrayList<Goal> gs = new ArrayList<>();
			gs.add(new Goal("secondo", null, null));
			gs.add(new Goal("terzo", null, null));
			tree.addAndArcs(new Goal("root", null, null), gs);
			net.construct();
		}
		catch( NodeNotFoundException ex){ System.out.println("Errore"); }
	}
	
}
