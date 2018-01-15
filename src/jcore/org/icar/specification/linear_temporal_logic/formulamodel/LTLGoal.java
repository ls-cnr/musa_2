package org.icar.specification.linear_temporal_logic.formulamodel;

import java.util.HashMap;
import java.util.Stack;

import org.icar.musa.core.Requirements;
/**
 * This class represents a binary tree structure, used to maintain a hierarchy between all sub-formulas that derive from the main
 * LTL formula. Every node contains a LTL operator that depends on his sons, the operands. Leaves just contain Atomic Propositions.
 */
public class LTLGoal implements Requirements {
    
	/** The root. */
	private FormulaBTNode root;
	
	/** The stack that come from the parser, used for construction */
	private Stack<String> constructionStack;
	
	/** A dictionary used to keep track of the First Order Logic Proposition in the formula */
	private HashMap<String, Stack<String>> FOLFormulasDict;

	/**
	 * Instantiates a new formula BT.
	 *
	 * @param stack
	 *            the construction stack
	 * @param dict
	 *            the FOL Formulas dict
	 */
	public LTLGoal(Stack<String> stack, HashMap<String, Stack<String>> dict){
    	this.constructionStack = stack;
    	this.FOLFormulasDict = dict;
		this.root = construct();

		this.root = normalize(this.root);
	}
    
    /**
	 * Gets the root.
	 *
	 * @return the root
	 */
    public FormulaBTNode getRoot(){
    	return root;
    }
    
    /**
	 * Gets the FOL formulas dict.
	 *
	 * @return the FOL formulas dict
	 */
    public HashMap<String, Stack<String>> getFOLFormulasDict() {
    	return FOLFormulasDict;
    }
    
    /**
	 * The recursive method that constructs a binary tree that holds LTL operators and Atomic Propositions, 
	 * from a stack.
	 *  
	 * @return the formula BT node
	 */
    private FormulaBTNode construct(){
		if(!constructionStack.isEmpty()){
			String tmp = constructionStack.pop();
			FormulaBTNode root = new FormulaBTNode(tmp);
			if( tmp.equals("AND") || tmp.equals("OR") || tmp.equals("IMP") || tmp.equals("BIC") || tmp.equals("U") || tmp.equals("R") ){
				root.addRight(construct());
				root.addLeft(construct());
			}
			else if (tmp.equals("NOT") || tmp.equals("F") || tmp.equals("X") || tmp.equals("G")){
				 root.addLeft(construct());
			}
			return root;
		}
		else
			return null;
	 }
    
    /**
	 * After the construction the tree must be normalized, that is removing all the nodes containing
	 * the NOT operator and adjusting their sub-trees.
	 *
	 * @param root
	 *            the root
	 * @return the formula BT node
	 */
    private FormulaBTNode normalize(FormulaBTNode root) {
    	if( !root.getVal().equals("NOT") ){
    		if(root.hasLeft()) root.addLeft(normalize(root.getLeft()));
    		if(root.hasRight()) root.addRight(normalize(root.getRight()));
    	}
    	else
    		root = negateFormula(root.getLeft());
    	return root;
    }
    
    /**
	 * The recursive method that negates a sub-tree starting from a node
	 *
	 * @param root
	 *            the root
	 * @return the formula BT node
	 */
    private FormulaBTNode negateFormula( FormulaBTNode root ) {
    	if ( root.getVal().equals("NOT") ){
			root = normalize(root.getLeft());
    	}
		else if (root.getVal().equals("F")){
			root.setVal("G");
			root.addLeft( negateFormula(root.getLeft()) );
		}
		else if (root.getVal().equals("G")){
			root.setVal("F");
			root.addLeft(negateFormula(root.getLeft()));
		}
		else if (root.getVal().equals("X"))
			root.addLeft(negateFormula(root.getLeft()));
		else if (root.getVal().equals("U")){
			root.setVal("R");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("R")){
			root.setVal("U");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("AND")){
			root.setVal("OR");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("OR")){
			root.setVal("AND");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("IMP")){ // !(a->b) = a && !b
			root.setVal("AND");
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("BIC")){ 	// !(a<->b) = (a && !b) || (!a && b)
			root.setVal("OR");					//TO TEST 
			FormulaBTNode leftCopy = new FormulaBTNode(root.getLeft());
			FormulaBTNode rightCopy = new FormulaBTNode(root.getRight());
			FormulaBTNode newLeft = new FormulaBTNode("AND");
			newLeft.addLeft(root.getLeft());
			newLeft.addRight(negateFormula(rightCopy));
			root.addLeft(newLeft);
			FormulaBTNode newRight = new FormulaBTNode("AND");
			newRight.addLeft(negateFormula(leftCopy));
			newRight.addRight(root.getRight());
			root.addRight(newRight);			
		}
		else if (root.getVal().equals("TRUE"))
			root.setVal("FALSE");
		else if (root.getVal().equals("FALSE"))
			root.setVal("TRUE");
		else
			if( root.getVal().startsWith("!") ){
				root.setVal( root.getVal().substring(1) );
			}
			else
				root.setVal("!"+root.getVal());

		return root;
    }
    
    /**
	 * Prints the tree in a preorder visit
	 *
	 * @param root
	 *            the root
	 */
    public void print(FormulaBTNode root) {
    	System.out.print(root.getVal()+"-> ");
    	if( root.hasLeft() ) System.out.print(root.getLeft().getVal() + ", ");
    	if( root.hasRight() ) System.out.print(root.getRight().getVal());
    	System.out.println("");
    	if(root.hasLeft()) print(root.getLeft());
    	if(root.hasRight()) print(root.getRight());
    }
}
