package datalayer.awareness.LTL.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import datalayer.awareness.LTL.formulamodel.*;
import datalayer.awareness.LTL.net.condition.*;
import datalayer.awareness.LTL.net.netmodels.*;

/**
 * The Class PetriNetsConstruction, used do create a set of interconnected Nets through the Formula Binary Tree.
 */
public class PNHierarchyConstruction {
	
	/** The counter. */
	private static int counter;
	
	private static boolean orCond;
	
	/**
	 * The method that starts the construction.
	 *
	 * @param tree
	 *            the tree
	 * @param hopNets
	 *            a structure that holds the nets useful to elaborate hop 
	 * @return the hash map
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, FormulaPN> construct( LTLGoal tree, HashSet<String> hopNets ) {
		HashSet<String> formulasSet = new HashSet<>();
		
		HashMap<String, FormulaPN> petriNets = new HashMap<>();
				
		HashMap<String, Stack<String>> FOLFormulasDict = tree.getFOLFormulasDict();
//		HashMap<String, Stack<String>> tmpDict = new HashMap<>();
//		for( String s : FOLFormulasDict.keySet() )
//			tmpDict.put(s, (Stack<String>) FOLFormulasDict.get(s).clone());
//		FOLFormulasDict = tmpDict;
//		tmpDict = null;
		
		counter = 0;
		orCond = false;
		formulasSet.add("F"); formulasSet.add("G"); formulasSet.add("X"); formulasSet.add("U");formulasSet.add("R");
		formulasSet.add("AND"); formulasSet.add("OR");formulasSet.add("IMP");formulasSet.add("BIC");
		
		construction(tree.getRoot(), "Formula" + counter++, "Formula" + counter, formulasSet, petriNets, FOLFormulasDict, hopNets);
		counter = 0;
		
		return petriNets;
	}
	
	/**
	 * The main construction method. It recursively visit each tree node to construct a Net using the information saved 
	 * and associate a Net (or an Atomic Proposition) to it's dependent creating a TransitionCondition.
	 * It also search for the nodes fathers of a leaf that will be used for hop calculation. 
	 *
	 * @param root
	 *            the root
	 * @param name
	 *            the name
	 * @param formulasSet
	 *            the formulas set
	 * @param petriNets
	 *            the petri nets
	 * @param FOLFormulasDict
	 *            the FOL formulas dict
	 * @param hopNets
	 *            the hop nets
	 */
	private static void construction( FormulaBTNode root, String name, String fatherName, HashSet<String> formulasSet, HashMap<String, FormulaPN> petriNets, HashMap<String, Stack<String>> FOLFormulasDict, HashSet<String> hopNets ) {
		
		//String[] tempA = new String[2];
		TransitionCondition[] tempA = new TransitionCondition[2];
		
		if(root.getVal().equals("OR") || root.getVal().equals("IMP")){ //or hop special condition
			orCond = true;
			hopNets.add(name);
		}
		
		if( root.hasLeft() ){
			if( !root.getLeft().hasLeft() ) //Finds hopNets
				if( (root.hasRight() && !root.getRight().hasLeft()) || !root.hasRight() )
					if(!orCond)
						hopNets.add(name);
			if( formulasSet.contains(root.getLeft().getVal()) ){
				tempA[0] = new FormulaCondition("Formula" + counter++);
				construction(root.getLeft(), tempA[0].getTerm(), name, formulasSet, petriNets, FOLFormulasDict, hopNets);
			}
			else
				tempA[0] = initSimpleCondition(root.getLeft(), FOLFormulasDict);
		}
		else{
			petriNets.put(name, new SingleTransitionPN( initSimpleCondition(root, FOLFormulasDict) ));
			return;
		}
		
		if( root.hasRight() )
			if( formulasSet.contains(root.getRight().getVal()) ){
				tempA[1] = new FormulaCondition("Formula" + counter++);
				construction(root.getRight(), tempA[1].getTerm(), name, formulasSet, petriNets, FOLFormulasDict, hopNets);
			}
			else
				tempA[1] = initSimpleCondition(root.getRight(), FOLFormulasDict);
		
		orCond = false;
		
		if (root.getVal().equals("F"))
			petriNets.put(name, new FinallyPN(tempA[0], fatherName));
		else if (root.getVal().equals("G"))
			petriNets.put(name, new GloballyPN(tempA[0], fatherName));
		else if (root.getVal().equals("X")) 
			petriNets.put(name, new NextPN(tempA[0]));
		else if (root.getVal().equals("U"))
			petriNets.put(name, new UntilPN(tempA[0], tempA[1], fatherName));
		else if (root.getVal().equals("R"))
			petriNets.put(name, new RelasePN(tempA[0], tempA[1], fatherName));
		else if (root.getVal().equals("AND")) 
			petriNets.put(name, new AndPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("OR")) 
			petriNets.put(name, new OrPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("IMP")) 
			petriNets.put(name, new ImpPN(tempA[0], tempA[1]));
		else if (root.getVal().equals("BIC")) 
			petriNets.put(name, new BicPN(tempA[0], tempA[1]));
		else
			System.out.println("Errore " + root.getVal());//TODO exception handling			
	}
	
	/**
	 * Inits the simple condition.
	 *
	 * @param root
	 *            the root
	 * @param FOLFormulasDict
	 *            the FOL formulas dict
	 * @return the simple condition
	 */
	private static SimpleCondition initSimpleCondition(FormulaBTNode root, HashMap<String, Stack<String>> FOLFormulasDict ) {
		String tmpStr = root.getVal();
		boolean neg = false;
		if( tmpStr.startsWith("!") ){
			tmpStr = tmpStr.substring(1);
			neg = true;
		}
		@SuppressWarnings("unchecked")
		Stack<String> tmpStack = (Stack<String>) FOLFormulasDict.get(tmpStr).clone();
		String pred = tmpStack.pop();
		ArrayList<String> args = new ArrayList<>();
		while( !tmpStack.isEmpty() )
			args.add(tmpStack.pop());
		return new SimpleCondition(tmpStr, pred, args, neg);
	}

}
