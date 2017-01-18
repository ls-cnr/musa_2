package layertest.awareness.net;

import layer.awareness.*;
import layer.awareness.goaltree.*;
import petrinet.gui.*;

import java.util.ArrayList;

public class TestGui {

	public static void main(String[] args) throws NodeNotFoundException {
		GoalTree tree = new GoalTree(new Goal("root", null, null));
		GoalModel model = new GoalModel(tree);
		Net net = new Net(model);
		
		ArrayList<Goal> gs = new ArrayList<>();
		
		/*Test And
		gs.add(new Goal("secondo"));
		gs.add(new Goal("terzo"));
		tree.addAndArcs(new Goal("root"), gs);
		*/
		
		/*Test Or
		gs.add(new Goal("secondo"));
		gs.add(new Goal("terzo"));
		tree.addOrArcs(new Goal("root"), gs);
		*/
		
		/*Test T*/
		ArrayList<Goal> gsor = new ArrayList<>();
		gs.add(new Goal("secondo", null, null));
		gs.add(new Goal("terzo", null, null));
		gsor.add(new Goal("quarto", null, null));
		gsor.add(new Goal("quinto", null, null));
		
		tree.addAndArcs(new Goal("root", null, null), gs);
		tree.addOrArcs(new Goal("terzo", null, null), gsor);
		
		
		net.construct();
		net.getFirstPlace().addTokens(1);
		PetrinetGUI.displayPetrinet(net.getPetrinet());
	}
	
	
	
}
