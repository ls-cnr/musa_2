package ltlparsertest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import layer.awareness.LTL.formulamodel.FormulaBT;
import layer.awareness.LTL.target.LTLLexer;
import layer.awareness.LTL.target.LTLParser;

public class LtlRunner {
	 
	public static void main( String[] args) throws Exception {
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream("!(f R (b U (! (! (!(!(!a)))))))");

		LTLLexer lexer = new LTLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		LTLParser parser = new LTLParser(tokens);
		ParseTree tree = parser.start(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		
		FormulaBT forTree = new FormulaBT(parser.getStack());
	 }	 
 }
