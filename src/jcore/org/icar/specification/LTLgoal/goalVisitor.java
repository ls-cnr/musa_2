// Generated from goal.g4 by ANTLR 4.7.1

package org.icar.specification.LTLgoal;
//package org.icar.specification.linear_temporal_logic.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link goalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface goalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link goalParser#goal_model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal_model(goalParser.Goal_modelContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#ltl_extended_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtl_extended_list(goalParser.Ltl_extended_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#single_goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_goal(goalParser.Single_goalContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#goalpriority}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoalpriority(goalParser.GoalpriorityContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#ltl_extended}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtl_extended(goalParser.Ltl_extendedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalQuantifier}
	 * labeled alternative in {@link goalParser#quantified_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalQuantifier(goalParser.UniversalQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistentialQuantifier}
	 * labeled alternative in {@link goalParser#quantified_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialQuantifier(goalParser.ExistentialQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AlwaysTrue}
	 * labeled alternative in {@link goalParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlwaysTrue(goalParser.AlwaysTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AlwaysFalse}
	 * labeled alternative in {@link goalParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlwaysFalse(goalParser.AlwaysFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code APredicate}
	 * labeled alternative in {@link goalParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAPredicate(goalParser.APredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperator}
	 * labeled alternative in {@link goalParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(goalParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FormulaWithBracket}
	 * labeled alternative in {@link goalParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulaWithBracket(goalParser.FormulaWithBracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#bracketed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed(goalParser.BracketedContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(goalParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#binary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_operator(goalParser.Binary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(goalParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#termlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermlist(goalParser.TermlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(goalParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(goalParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#smallatom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmallatom(goalParser.SmallatomContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#var_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_list(goalParser.Var_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(goalParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#numeral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeral(goalParser.NumeralContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(goalParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link goalParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(goalParser.CharacterContext ctx);
}