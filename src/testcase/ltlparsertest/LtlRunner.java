package ltlparsertest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import LTL.LTLLexer;
import LTL.LTLParser;
import layer.awareness.formulamodel.FormulaBT;
import layer.awareness.net.PetriNetConstruction;

public class LtlRunner {
	 
	public static void main( String[] args) throws Exception {
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream("(F ( a U (F c) )) U a");

		LTLLexer lexer = new LTLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		LTLParser parser = new LTLParser(tokens);
		ParseTree tree = parser.start(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		
		FormulaBT forTree = new FormulaBT(parser.getStack());
		new PetriNetConstruction(forTree).printPNS();
	 }	 
 }
