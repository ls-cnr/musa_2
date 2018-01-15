package org.icar.specification.linear_temporal_logic.formulamodel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.icar.specification.linear_temporal_logic.target.LTLLexer;
import org.icar.specification.linear_temporal_logic.target.LTLParser;

/**
 * Class basically used to Parse a string and to generate a Formula Binary Tree. 
 */
public class FormulaBTConstruction {
	
	/**
	 * The main method that use the ANTLR parser that checks if the input formula is correct, 
	 * and returns a FormulaBT constructed using a Stack.
	 *
	 * @param in
	 *            the in
	 * @return the formula BT
	 */
	public static LTLGoal construct( String in ) {
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream(in);
	
		LTLLexer lexer = new LTLLexer(input);
	
		CommonTokenStream tokens = new CommonTokenStream(lexer);
	
		LTLParser parser = new LTLParser(tokens);
		ParseTree tree = parser.start(); // begin parsing at rule 'r'
		
		//TODO Handle parser exception
		
		return new LTLGoal(parser.getStack(), parser.getDict());
	}	 
}
