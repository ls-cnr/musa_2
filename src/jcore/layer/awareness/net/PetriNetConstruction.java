package layer.awareness.net;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

import layer.awareness.formulamodel.*;
import petrinet.logic.Petrinet;
import petrinet.logic.Place;

public class PetriNetConstruction {
	
	private FormulaBT tree;
	
	private HashSet<String> formulasSet;
	
	private HashMap<String, FormulaPN> petriNets;
	
	private int counter;
	
	public PetriNetConstruction( FormulaBT tree ) {
		this.tree = tree;
		formulasSet = new HashSet<>();
		petriNets = new HashMap<>();
		counter = 0;
		formulasSet.add("F"); formulasSet.add("G"); formulasSet.add("X"); 
		formulasSet.add("U");formulasSet.add("R");formulasSet.add("AND"); formulasSet.add("OR");formulasSet.add("IMP");formulasSet.add("BIC");
		
		construction(tree.getRoot(), "Formula" + counter++);
	}
	
	private void construction( FormulaBTNode root, String name ) {
		
		String[] tempA = new String[2];
		
		if( root.hasLeft() )
			if( formulasSet.contains(root.getLeft().getVal()) ){
				tempA[0] = "Formula" + counter++;
				construction(root.getLeft(), tempA[0]);
			}
			else
				tempA[0] = root.getLeft().getVal();
		else{
			petriNets.put(name, new SinglePlacePN(root.getVal()));
			return;
		}
		
		if( root.hasRight() )
			if( formulasSet.contains(root.getRight().getVal()) ){
				tempA[1] = "Formula" + counter++;
				construction(root.getRight(), tempA[1]);
			}
			else
				tempA[1] = root.getRight().getVal();	
		
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
			System.out.println("Errore " + root.getVal());			
	}
	
	public void printPNS() {
		for( String s : petriNets.keySet() )
			if( petriNets.get(s) instanceof UntilPN )
				System.out.println(s + " - " + petriNets.get(s) + " " + petriNets.get(s).operand + " " + ((UntilPN) petriNets.get(s)).operand2);
			else
				System.out.println(s + " - " + petriNets.get(s) + " " + petriNets.get(s).operand);
	}

}
