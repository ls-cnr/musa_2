package layertest.awareness.net;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import layer.awareness.*;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.*;

import java.util.ArrayList;

public class NetTest {

	private GoalModel model;
	private Net net;
	
	@Ignore
	@Test
	public void testParallelTemplate() {
		model = new GoalModel(new Goal("root", null, null));
		ArrayList<Goal> gs = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		model.addAndArcs(new Goal("root", null, null), gs);
		net = new Net(model);
	}
	
	@Ignore
	@Test
	public void testHops1() {
		model = new GoalModel(new Goal("root", null, null));
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
	public void testHops3() {
		model = new GoalModel(new Goal("root", null, null));
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
		first.add(new Token("p0"));
		assertEquals(net.hop(first), 12);
		
		ArrayList<Token> blu = new ArrayList<>();
		blu.add(new Token("p1"));
		blu.add(new Token("p6"));
		assertEquals(net.hop(blu), 4);
		
		ArrayList<Token> rosso = new ArrayList<>();
		rosso.add(new Token("p3"));
		rosso.add(new Token("p10"));
		rosso.add(new Token("p11"));
		assertEquals(net.hop(rosso), 5);
		
		ArrayList<Token> verde = new ArrayList<>();
		verde.add(new Token("p5"));
		verde.add(new Token("p3"));
		assertEquals(net.hop(verde), 8);
		
		ArrayList<Token> viola = new ArrayList<>();
		MultipleToken token = new MultipleToken("p5");
		viola.add( token );
		viola.add(new Token("p7", token, 0));
		viola.add(new Token("p10", token, 1));
		viola.add(new Token("p11", token, 1));
		assertEquals(net.hop(viola), 5);
		
		ArrayList<Token> giallo = new ArrayList<>();
		giallo.add(new Token("p1"));
		MultipleToken token2 = new MultipleToken("p5");
		giallo.add( token2 );
		giallo.add(new Token("p8", token2, 1));
		giallo.add(new Token("p11", token2, 1));
		assertEquals(net.hop(giallo), 9);
		
	}
	
	@Test
	public void testHops4() {
		model = new GoalModel(new Goal("root", null, null));
		ArrayList<Goal> rootAnd = new ArrayList<>();
		ArrayList<Goal> rootAndOr1 = new ArrayList<>();
		ArrayList<Goal> rootAndOr2 = new ArrayList<>();
		ArrayList<Goal> rootAndOr2Or = new ArrayList<>();
		ArrayList<Goal> rootAndOr2And = new ArrayList<>();
		rootAnd.add(new Goal("secondo", null, null));
		rootAnd.add(new Goal("terzo", null, null));
		rootAndOr1.add(new Goal("quarto", null, null));
		rootAndOr1.add(new Goal("quinto", null, null));
		rootAndOr2.add(new Goal("sesto", null, null));
		rootAndOr2.add(new Goal("settimo", null, null));
		rootAndOr2Or.add(new Goal("ottavo", null, null));
		rootAndOr2Or.add(new Goal("nono", null, null));
		rootAndOr2And.add(new Goal("decimo", null, null));
		rootAndOr2And.add(new Goal("undicesimo", null, null));
		
		model.addAndArcs(new Goal("root", null, null), rootAnd);
		model.addOrArcs(new Goal("secondo", null, null), rootAndOr1);
		model.addOrArcs(new Goal("terzo", null, null), rootAndOr2);
		model.addOrArcs(new Goal("sesto", null, null), rootAndOr2Or);
		model.addAndArcs(new Goal("settimo", null, null), rootAndOr2And);
		
		net = new Net(model);
		
		ArrayList<Token> first = new ArrayList<>();
		first.add( new Token("p0"));
		assertEquals(net.hop(first), 14);
		
		ArrayList<Token> blu = new ArrayList<>();
		blu.add(new Token("p1"));
		MultipleToken token = new MultipleToken("p8");
		blu.add( token );
		blu.add(new Token("p16", token, 1));
		blu.add(new Token("p17", token, 1));
		assertEquals(net.hop(blu), 9);
		
		ArrayList<Token> rosso = new ArrayList<>();
		MultipleToken token0 = new MultipleToken("p2");
		rosso.add( token0 );
		rosso.add(new Token("p4", token0, 0));
		MultipleToken token1 = new MultipleToken("p8");
		rosso.add( token1 );
		MultipleToken token2 = new MultipleToken("p10", token1, 0);
		rosso.add(token2);
		rosso.add(new Token("p12", token2, 0));
		rosso.add(new Token("p13", token2, 1));		
		rosso.add(new Token("p15", token1, 1));
		rosso.add(new Token("p18", token1, 1));
		assertEquals(net.hop(rosso), 7);
		
		ArrayList<Token> verde = new ArrayList<>();
		verde.add(new Token("p3"));
		verde.add(new Token("p8"));
		assertEquals(net.hop(verde), 9);
		
		ArrayList<Token> viola = new ArrayList<>();
		viola.add(new Token("p2"));
		MultipleToken tokenV = new MultipleToken("p8");
		viola.add( tokenV );
		viola.add(new Token("p11", tokenV, 0));
		viola.add(new Token("p14", tokenV, 1));
		viola.add(new Token("p19", tokenV, 1));
		assertEquals(net.hop(viola), 8);
		
		ArrayList<Token> giallo = new ArrayList<>();
		MultipleToken tokenG0 = new MultipleToken("p2");
		giallo.add( tokenG0 );
		giallo.add(new Token("p5", tokenG0, 1));
		MultipleToken tokenG1 = new MultipleToken("p8");
		giallo.add( tokenG1 );
		giallo.add(new Token("p10", tokenG1, 0));
		assertEquals(net.hop(giallo), 7);
		
		
	}
	
}
