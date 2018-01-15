// Generated from LTL.g4 by ANTLR 4.7.1

	package datalayer.awareness.LTL.target;
	import java.util.*;
	/** Class auto-generated using ANTLR */

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LTLParser}.
 */
public interface LTLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LTLParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(LTLParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(LTLParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link LTLParser#folfor}.
	 * @param ctx the parse tree
	 */
	void enterFolfor(LTLParser.FolforContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#folfor}.
	 * @param ctx the parse tree
	 */
	void exitFolfor(LTLParser.FolforContext ctx);
	/**
	 * Enter a parse tree produced by {@link LTLParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(LTLParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(LTLParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LTLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LTLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LTLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LTLParser#unaOper}.
	 * @param ctx the parse tree
	 */
	void enterUnaOper(LTLParser.UnaOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#unaOper}.
	 * @param ctx the parse tree
	 */
	void exitUnaOper(LTLParser.UnaOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link LTLParser#binOper}.
	 * @param ctx the parse tree
	 */
	void enterBinOper(LTLParser.BinOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLParser#binOper}.
	 * @param ctx the parse tree
	 */
	void exitBinOper(LTLParser.BinOperContext ctx);
}