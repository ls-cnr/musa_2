// Generated from capability.g4 by ANTLR 4.7.1

package org.icar.specification.ACLanguage;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link capabilityParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface capabilityVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link capabilityParser#capability}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapability(capabilityParser.CapabilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#cap_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCap_body(capabilityParser.Cap_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#precondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecondition(capabilityParser.PreconditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#postcondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostcondition(capabilityParser.PostconditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#evolution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvolution(capabilityParser.EvolutionContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#evo_action_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvo_action_list(capabilityParser.Evo_action_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(capabilityParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(capabilityParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalPredQuantifier}
	 * labeled alternative in {@link capabilityParser#quantified_predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalPredQuantifier(capabilityParser.UniversalPredQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistentialPredQuantifier}
	 * labeled alternative in {@link capabilityParser#quantified_predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialPredQuantifier(capabilityParser.ExistentialPredQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AlwaysTrue}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlwaysTrue(capabilityParser.AlwaysTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AlwaysFalse}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlwaysFalse(capabilityParser.AlwaysFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code APredicate}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAPredicate(capabilityParser.APredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegPredOperator}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegPredOperator(capabilityParser.NegPredOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FormulaPredWithBracket}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulaPredWithBracket(capabilityParser.FormulaPredWithBracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotOperator}
	 * labeled alternative in {@link capabilityParser#neg_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperator(capabilityParser.NotOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExclOperator}
	 * labeled alternative in {@link capabilityParser#neg_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExclOperator(capabilityParser.NotExclOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#bracketed_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed_predicate(capabilityParser.Bracketed_predicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndPredBinaryOperator}
	 * labeled alternative in {@link capabilityParser#binary_pred_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndPredBinaryOperator(capabilityParser.AndPredBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrPredBinaryOperator}
	 * labeled alternative in {@link capabilityParser#binary_pred_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrPredBinaryOperator(capabilityParser.OrPredBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfPredBinaryOperator}
	 * labeled alternative in {@link capabilityParser#binary_pred_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfPredBinaryOperator(capabilityParser.IfPredBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IffPredBinaryOperator}
	 * labeled alternative in {@link capabilityParser#binary_pred_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIffPredBinaryOperator(capabilityParser.IffPredBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(capabilityParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#termlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermlist(capabilityParser.TermlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(capabilityParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(capabilityParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#var_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_list(capabilityParser.Var_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(capabilityParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#numeral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeral(capabilityParser.NumeralContext ctx);
}