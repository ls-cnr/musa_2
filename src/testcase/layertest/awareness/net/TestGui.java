package layertest.awareness.net;

import layer.awareness.*;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.Net;
import petrinet.gui.*;
import petrinet.logic.Petrinet;

import java.util.ArrayList;

public class TestGui {

	public static void main(String[] args) {
		GoalModel model = new GoalModel(new Goal("root", null, null));		
		ArrayList<Goal> gs = new ArrayList<>();
		
		/*Test And
		gs.add(new Goal("secondo"));
		gs.add(new Goal("terzo"));
		model.addAndArcs(new Goal("root"), gs);
		*/
		
		/*Test Or
		gs.add(new Goal("secondo"));
		gs.add(new Goal("terzo"));
		model.addOrArcs(new Goal("root"), gs);
		*/
		
		/*Test T
		ArrayList<Goal> gsor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		
		model.addAndArcs(new Goal("root", null, null), gs);
		model.addOrArcs(new Goal("terzo", null, null), gsor);
		*/
		
		/*Test 4*/
		//model = new GoalModel(new Goal("root", null, null));
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
		

		/*Add this method to the Net class to start the test.
		 * 
		public Petrinet getPetrinet() {
			return pn;
		}
		*/
		
		/*
		Net net = new Net(model);
		net.getPetrinet().getPlaces().get(0).addTokens(1);
		PetrinetGUI.displayPetrinet(net.getPetrinet());
		*/
	}
	
	
	
}
