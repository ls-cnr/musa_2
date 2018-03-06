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
	 * Visit a parse tree produced by the {@code MyCapability}
	 * labeled alternative in {@link capabilityParser#capability}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyCapability(capabilityParser.MyCapabilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyCapBody}
	 * labeled alternative in {@link capabilityParser#cap_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyCapBody(capabilityParser.MyCapBodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyPrecondition}
	 * labeled alternative in {@link capabilityParser#precondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyPrecondition(capabilityParser.MyPreconditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyPostcondition}
	 * labeled alternative in {@link capabilityParser#postcondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyPostcondition(capabilityParser.MyPostconditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyEvoScenario}
	 * labeled alternative in {@link capabilityParser#evolution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyEvoScenario(capabilityParser.MyEvoScenarioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MySingleAction}
	 * labeled alternative in {@link capabilityParser#evo_action_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMySingleAction(capabilityParser.MySingleActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyMultiAction}
	 * labeled alternative in {@link capabilityParser#evo_action_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyMultiAction(capabilityParser.MyMultiActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyAddAction}
	 * labeled alternative in {@link capabilityParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyAddAction(capabilityParser.MyAddActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyRemoveAction}
	 * labeled alternative in {@link capabilityParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyRemoveAction(capabilityParser.MyRemoveActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyClearAction}
	 * labeled alternative in {@link capabilityParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyClearAction(capabilityParser.MyClearActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyPredicateFormula}
	 * labeled alternative in {@link capabilityParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyPredicateFormula(capabilityParser.MyPredicateFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyQuantPredicateFormula}
	 * labeled alternative in {@link capabilityParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyQuantPredicateFormula(capabilityParser.MyQuantPredicateFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyForAllPredicate}
	 * labeled alternative in {@link capabilityParser#quantified_predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyForAllPredicate(capabilityParser.MyForAllPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyExistsPredicate}
	 * labeled alternative in {@link capabilityParser#quantified_predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyExistsPredicate(capabilityParser.MyExistsPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyNegPredicate}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyNegPredicate(capabilityParser.MyNegPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyCompPredicate}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyCompPredicate(capabilityParser.MyCompPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyAtomPredicate}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyAtomPredicate(capabilityParser.MyAtomPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyDisjunctionPredicateFormula}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyDisjunctionPredicateFormula(capabilityParser.MyDisjunctionPredicateFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyConjunctionPredicateFormula}
	 * labeled alternative in {@link capabilityParser#predicate_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyConjunctionPredicateFormula(capabilityParser.MyConjunctionPredicateFormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyNotPred1}
	 * labeled alternative in {@link capabilityParser#neg_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyNotPred1(capabilityParser.MyNotPred1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code MyNotPred2}
	 * labeled alternative in {@link capabilityParser#neg_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyNotPred2(capabilityParser.MyNotPred2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code MyBracketedPredicate}
	 * labeled alternative in {@link capabilityParser#bracketed_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyBracketedPredicate(capabilityParser.MyBracketedPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyAtom}
	 * labeled alternative in {@link capabilityParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyAtom(capabilityParser.MyAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyStructure}
	 * labeled alternative in {@link capabilityParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyStructure(capabilityParser.MyStructureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyEmptyTerm}
	 * labeled alternative in {@link capabilityParser#termlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyEmptyTerm(capabilityParser.MyEmptyTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyMultiTerm}
	 * labeled alternative in {@link capabilityParser#termlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyMultiTerm(capabilityParser.MyMultiTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MySimpleTerm}
	 * labeled alternative in {@link capabilityParser#termlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMySimpleTerm(capabilityParser.MySimpleTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyTrueFalseTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyTrueFalseTerm(capabilityParser.MyTrueFalseTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyConstantTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyConstantTerm(capabilityParser.MyConstantTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyNumericTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyNumericTerm(capabilityParser.MyNumericTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyVariableTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyVariableTerm(capabilityParser.MyVariableTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyFunctionTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyFunctionTerm(capabilityParser.MyFunctionTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyStringTerm}
	 * labeled alternative in {@link capabilityParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyStringTerm(capabilityParser.MyStringTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyTrueConstant}
	 * labeled alternative in {@link capabilityParser#true_or_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyTrueConstant(capabilityParser.MyTrueConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyFalseConstant}
	 * labeled alternative in {@link capabilityParser#true_or_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyFalseConstant(capabilityParser.MyFalseConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyAtomConstantTerm}
	 * labeled alternative in {@link capabilityParser#constant_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyAtomConstantTerm(capabilityParser.MyAtomConstantTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyAtomFunctionalTerm}
	 * labeled alternative in {@link capabilityParser#functional_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyAtomFunctionalTerm(capabilityParser.MyAtomFunctionalTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyStructureFunctionalTerm}
	 * labeled alternative in {@link capabilityParser#functional_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyStructureFunctionalTerm(capabilityParser.MyStructureFunctionalTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#variable_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_term(capabilityParser.Variable_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#numeral_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeral_term(capabilityParser.Numeral_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#string_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_term(capabilityParser.String_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link capabilityParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(capabilityParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MySingleVariable}
	 * labeled alternative in {@link capabilityParser#var_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMySingleVariable(capabilityParser.MySingleVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyMultiVariable}
	 * labeled alternative in {@link capabilityParser#var_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyMultiVariable(capabilityParser.MyMultiVariableContext ctx);
}