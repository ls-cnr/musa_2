package layer.awareness.LTL.net;

import java.util.HashMap;
import java.util.HashSet;

import layer.awareness.LTL.formulamodel.*;
import layer.awareness.LTL.net.netmodels.*;

public class PetriNetsConstruction {
	
	private HashSet<String> formulasSet;
	
	private HashMap<String, FormulaPN> petriNets;
	
	private int counter;
	
	public PetriNetsConstruction( FormulaBTNode root ) {
		formulasSet = new HashSet<>();
		petriNets = new HashMap<>();
		counter = 0;
		formulasSet.add("F"); formulasSet.add("G"); formulasSet.add("X"); 
		formulasSet.add("U");formulasSet.add("R");formulasSet.add("AND"); formulasSet.add("OR");formulasSet.add("IMP");formulasSet.add("BIC");
		
		construction(root, "Formula" + counter++);
	}
	
	private void construction( FormulaBTNode root, String name ) {
		
		//String[] tempA = new String[2];
		TransitionCondition[] tempA = new TransitionCondition[2];
		
		if( root.hasLeft() )
			if( formulasSet.contains(root.getLeft().getVal()) ){
				tempA[0] = new FormulaCondition("Formula" + counter++);
				construction(root.getLeft(), tempA[0].getTerm());
			}
			else
				tempA[0] = new SimpleCondition(root.getLeft().getVal());
		else{
			petriNets.put(name, new SinglePlacePN(new SimpleCondition(root.getLeft().getVal())));
			return;
		}
		
		if( root.hasRight() )
			if( formulasSet.contains(root.getRight().getVal()) ){
				tempA[1] = new FormulaCondition("Formula" + counter++);
				construction(root.getRight(), tempA[1].getTerm());
			}
			else
				tempA[1] = new SimpleCondition(root.getLeft().getVal());
		
		if (root.getVal().equals("F"))
			petriNets.put(name, new FinallyPN(tempA[0]));
		else if (root.getVal().equals("G"))
			petriNets.put(name, new GloballyPN(tempA[0]));
		else if (root.getVal().equals("X")) ;
			//TODO petriNets.put(name, new FinallyPN(tempA[0]));
		else if (root.getVal().equals("U"))
			petriNets.put(name, new UntilPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("R"))
			petriNets.put(name, new RelasePN(tempA[0], tempA[1]));
		else if (root.getVal().equals("AND")) ;
			//TODO petriNets.put(name, new UntilPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("OR")) ;
			//TODO petriNets.put(name, new UntilPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("IMP")) ;
			//TODO petriNets.put(name, new UntilPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("BIC")) ;
			//TODO petriNets.put(name, new UntilPN(tempA[0], tempA[1]));
		else
			System.out.println("Errore " + root.getVal());//TODO exception handling			
	}
	
	public HashMap<String, FormulaPN> getNets() {
		return petriNets;
	}

}
