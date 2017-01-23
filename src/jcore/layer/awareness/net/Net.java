package layer.awareness.net;

import java.util.ArrayList;
import java.util.HashMap;

import layer.awareness.goalmodel.GoalModel;
import layer.semantic.Condition;
import petrinet.logic.*;

public class Net {

	Petrinet pn;
	private HashMap<Transition, Condition> labels;
	
	public Net( GoalModel model ) {
		PetriNetConstruction construction = new PetriNetConstruction(model);
		construction.construct();
		pn = construction.getPetrinet();
		labels = construction.getLabels();
	}
	
	public void putTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).addTokens(1); //equals??
		}
	}
	
	public void removeTokens( ArrayList<String> tokens ) {
		for( String i : tokens ){
			pn.getPlace(i).removeTokens(1); //equals??
		}
	}
	
	public void fire( Transition t ) {
		t.fire();
	}
	
	public ArrayList<Transition> getTransitionsAbleToFire() {
		return (ArrayList<Transition>) pn.getTransitionsAbleToFire();
	}
	
	public Condition getTransitionLabel( Transition t ) {
		return labels.get(t);
	}
	
	/*Temp*/
	public Petrinet getPetrinet() {
		return pn;
	}
}
