package layertest.awareness.net;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import layer.awareness.*;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.MultipleToken;
import layer.awareness.net.Net;
import layer.awareness.net.Token;
import net.sf.tweety.agents.MultiAgentSystem;
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
		ArrayList<Token> t1 = new ArrayList<>();
		t1.add(new Token(net.getFirst().getName()));
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
		
		ArrayList<Token> first = new ArrayList<>();
		first.add(new Token(net.getFirst().getName()));
		assertEquals(net.hop(first), 12);
		
		ArrayList<Token> blu = new ArrayList<>();
		blu.add(new Token("p1"));
		blu.add(new Token("p6"));
		assertEquals(net.hop(blu), 4);
		
		ArrayList<Token> rosso = new ArrayList<>();
		rosso.add(new Token("p10"));
		rosso.add(new Token("p11"));
		rosso.add(new Token("p3"));
		
		MultipleToken token = new MultipleToken("p5");
		rosso.add( token );
		rosso.add(new Token("p7", token));
		assertEquals(net.hop(rosso), 5);
		
		ArrayList<Token> verde = new ArrayList<>();
		verde.add(new Token("p5"));
		verde.add(new Token("p3"));
		assertEquals(net.hop(verde), 8);
	}
	
}
