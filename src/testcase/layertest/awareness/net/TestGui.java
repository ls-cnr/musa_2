package layertest.awareness.net;

import layer.awareness.*;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.Net;
import petrinet.gui.*;

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
		
		/*Test T*/
		ArrayList<Goal> gsor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		
		model.addAndArcs(new Goal("root", null, null), gs);
		model.addOrArcs(new Goal("terzo", null, null), gsor);
		
		

		Net net = new Net(model);
		net.getPetrinet().getPlaces().get(0).addTokens(1);
		PetrinetGUI.displayPetrinet(net.getPetrinet());
	}
	
	
	
}
