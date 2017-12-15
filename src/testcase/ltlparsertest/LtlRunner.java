package ltlparsertest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import datalayer.awareness.LTL.formulamodel.FormulaBTConstruction;
import datalayer.awareness.LTL.formulamodel.LTLGoal;
import datalayer.awareness.LTL.target.LTLLexer;
import datalayer.awareness.LTL.target.LTLParser;

public class LtlRunner {
	 
	public static void main( String[] args) throws Exception {
		test2();
	 }	
	
	private static void test1() {
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream("(!( G clean_work(house_work) ) ) && ( F add(document,user_ticket) )");

		LTLLexer lexer = new LTLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		LTLParser parser = new LTLParser(tokens);
		ParseTree tree = parser.start(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		
		LTLGoal forTree = new LTLGoal(parser.getStack(), parser.getDict());
	}

	private static void test2() {
		
		LTLGoal goal = FormulaBTConstruction.construct("received(doc,usr) && (order(doc)&&user(usr)) -> F(processed(doc)&&order(doc))"); //) -> F(processed(Doc)&order(Doc))
		goal.print(goal.getRoot());
	}

}
