// Generated from goal.g4 by ANTLR 4.7.1

package org.icar.specification.LTLgoal;
//package org.icar.specification.linear_temporal_logic.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class goalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, GOALMODEL=2, GOAL=3, BEGINGOALMODEL=4, ENDGOALMODEL=5, BEGINGOAL=6, 
		ENDGOAL=7, FOREACH=8, EXISTS=9, SUCH=10, TRUE=11, FALSE=12, GLOBALLY=13, 
		NEXT=14, FINALLY=15, UNTIL=16, RELEASE=17, NOT=18, AND=19, OR=20, IF=21, 
		IFF=22, LB=23, RB=24, COMMA=25, LSB=26, RSB=27, LCLETTER=28, UCLETTER=29, 
		DIGIT=30, WS=31;
	public static final int
		RULE_goal_model = 0, RULE_ltl_extended_list = 1, RULE_single_goal = 2, 
		RULE_goalpriority = 3, RULE_ltl_extended = 4, RULE_quantified_formula = 5, 
		RULE_formula = 6, RULE_bracketed = 7, RULE_unary_operator = 8, RULE_binary_operator = 9, 
		RULE_predicate = 10, RULE_termlist = 11, RULE_term = 12, RULE_atom = 13, 
		RULE_smallatom = 14, RULE_var_list = 15, RULE_variable = 16, RULE_numeral = 17, 
		RULE_string = 18, RULE_character = 19;
	public static final String[] ruleNames = {
		"goal_model", "ltl_extended_list", "single_goal", "goalpriority", "ltl_extended", 
		"quantified_formula", "formula", "bracketed", "unary_operator", "binary_operator", 
		"predicate", "termlist", "term", "atom", "smallatom", "var_list", "variable", 
		"numeral", "string", "character"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'''", "'goalmodel'", "'goal'", "'{'", "'}'", "'='", "'.'", "'foreach'", 
		"'exist'", "':'", "'true'", "'false'", "'G'", "'X'", "'F'", "'U'", "'R'", 
		"'not'", "'and'", "'or'", "'->'", "'<->'", "'('", "')'", "','", "'['", 
		"']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "GOALMODEL", "GOAL", "BEGINGOALMODEL", "ENDGOALMODEL", "BEGINGOAL", 
		"ENDGOAL", "FOREACH", "EXISTS", "SUCH", "TRUE", "FALSE", "GLOBALLY", "NEXT", 
		"FINALLY", "UNTIL", "RELEASE", "NOT", "AND", "OR", "IF", "IFF", "LB", 
		"RB", "COMMA", "LSB", "RSB", "LCLETTER", "UCLETTER", "DIGIT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "goal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public goalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Goal_modelContext extends ParserRuleContext {
		public TerminalNode GOALMODEL() { return getToken(goalParser.GOALMODEL, 0); }
		public TerminalNode BEGINGOALMODEL() { return getToken(goalParser.BEGINGOALMODEL, 0); }
		public Ltl_extended_listContext ltl_extended_list() {
			return getRuleContext(Ltl_extended_listContext.class,0);
		}
		public TerminalNode ENDGOALMODEL() { return getToken(goalParser.ENDGOALMODEL, 0); }
		public Goal_modelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal_model; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitGoal_model(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Goal_modelContext goal_model() throws RecognitionException {
		Goal_modelContext _localctx = new Goal_modelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal_model);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(GOALMODEL);
			setState(41);
			match(BEGINGOALMODEL);
			setState(42);
			ltl_extended_list();
			setState(43);
			match(ENDGOALMODEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ltl_extended_listContext extends ParserRuleContext {
		public Single_goalContext single_goal() {
			return getRuleContext(Single_goalContext.class,0);
		}
		public Ltl_extended_listContext ltl_extended_list() {
			return getRuleContext(Ltl_extended_listContext.class,0);
		}
		public Ltl_extended_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ltl_extended_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitLtl_extended_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ltl_extended_listContext ltl_extended_list() throws RecognitionException {
		Ltl_extended_listContext _localctx = new Ltl_extended_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ltl_extended_list);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				single_goal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				single_goal();
				setState(48);
				ltl_extended_list();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_goalContext extends ParserRuleContext {
		public TerminalNode GOAL() { return getToken(goalParser.GOAL, 0); }
		public SmallatomContext smallatom() {
			return getRuleContext(SmallatomContext.class,0);
		}
		public TerminalNode BEGINGOAL() { return getToken(goalParser.BEGINGOAL, 0); }
		public Ltl_extendedContext ltl_extended() {
			return getRuleContext(Ltl_extendedContext.class,0);
		}
		public TerminalNode ENDGOAL() { return getToken(goalParser.ENDGOAL, 0); }
		public GoalpriorityContext goalpriority() {
			return getRuleContext(GoalpriorityContext.class,0);
		}
		public Single_goalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_goal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitSingle_goal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_goalContext single_goal() throws RecognitionException {
		Single_goalContext _localctx = new Single_goalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_single_goal);
		try {
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(GOAL);
				setState(53);
				smallatom(0);
				setState(54);
				match(BEGINGOAL);
				setState(55);
				ltl_extended();
				setState(56);
				match(ENDGOAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(GOAL);
				setState(59);
				smallatom(0);
				setState(60);
				match(BEGINGOAL);
				setState(61);
				ltl_extended();
				setState(62);
				goalpriority();
				setState(63);
				match(ENDGOAL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoalpriorityContext extends ParserRuleContext {
		public TerminalNode LSB() { return getToken(goalParser.LSB, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public TerminalNode RSB() { return getToken(goalParser.RSB, 0); }
		public GoalpriorityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalpriority; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitGoalpriority(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalpriorityContext goalpriority() throws RecognitionException {
		GoalpriorityContext _localctx = new GoalpriorityContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_goalpriority);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(LSB);
			setState(68);
			numeral(0);
			setState(69);
			match(RSB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ltl_extendedContext extends ParserRuleContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public Quantified_formulaContext quantified_formula() {
			return getRuleContext(Quantified_formulaContext.class,0);
		}
		public Ltl_extendedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ltl_extended; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitLtl_extended(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ltl_extendedContext ltl_extended() throws RecognitionException {
		Ltl_extendedContext _localctx = new Ltl_extendedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ltl_extended);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case TRUE:
			case FALSE:
			case GLOBALLY:
			case NEXT:
			case FINALLY:
			case NOT:
			case LB:
			case LCLETTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				formula();
				}
				break;
			case FOREACH:
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				quantified_formula();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quantified_formulaContext extends ParserRuleContext {
		public Quantified_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantified_formula; }
	 
		public Quantified_formulaContext() { }
		public void copyFrom(Quantified_formulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UniversalQuantifierContext extends Quantified_formulaContext {
		public TerminalNode FOREACH() { return getToken(goalParser.FOREACH, 0); }
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public TerminalNode SUCH() { return getToken(goalParser.SUCH, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public UniversalQuantifierContext(Quantified_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitUniversalQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExistentialQuantifierContext extends Quantified_formulaContext {
		public TerminalNode EXISTS() { return getToken(goalParser.EXISTS, 0); }
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public TerminalNode SUCH() { return getToken(goalParser.SUCH, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ExistentialQuantifierContext(Quantified_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitExistentialQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantified_formulaContext quantified_formula() throws RecognitionException {
		Quantified_formulaContext _localctx = new Quantified_formulaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quantified_formula);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOREACH:
				_localctx = new UniversalQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(FOREACH);
				setState(76);
				var_list();
				setState(77);
				match(SUCH);
				setState(78);
				formula();
				}
				break;
			case EXISTS:
				_localctx = new ExistentialQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(EXISTS);
				setState(81);
				var_list();
				setState(82);
				match(SUCH);
				setState(83);
				formula();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormulaWithBracketContext extends FormulaContext {
		public BracketedContext bracketed() {
			return getRuleContext(BracketedContext.class,0);
		}
		public FormulaWithBracketContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitFormulaWithBracket(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlwaysFalseContext extends FormulaContext {
		public TerminalNode FALSE() { return getToken(goalParser.FALSE, 0); }
		public AlwaysFalseContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitAlwaysFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class APredicateContext extends FormulaContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public APredicateContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitAPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorContext extends FormulaContext {
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public UnaryOperatorContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlwaysTrueContext extends FormulaContext {
		public TerminalNode TRUE() { return getToken(goalParser.TRUE, 0); }
		public AlwaysTrueContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitAlwaysTrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formula);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				_localctx = new AlwaysTrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new AlwaysFalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(FALSE);
				}
				break;
			case T__0:
			case LCLETTER:
				_localctx = new APredicateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				predicate();
				}
				break;
			case GLOBALLY:
			case NEXT:
			case FINALLY:
			case NOT:
				_localctx = new UnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				unary_operator();
				}
				break;
			case LB:
				_localctx = new FormulaWithBracketContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				bracketed();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BracketedContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(goalParser.LB, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode RB() { return getToken(goalParser.RB, 0); }
		public Binary_operatorContext binary_operator() {
			return getRuleContext(Binary_operatorContext.class,0);
		}
		public BracketedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracketed; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitBracketed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BracketedContext bracketed() throws RecognitionException {
		BracketedContext _localctx = new BracketedContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bracketed);
		try {
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(LB);
				setState(95);
				formula();
				setState(96);
				match(RB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(LB);
				setState(99);
				binary_operator();
				setState(100);
				match(RB);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(goalParser.NOT, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode FINALLY() { return getToken(goalParser.FINALLY, 0); }
		public TerminalNode NEXT() { return getToken(goalParser.NEXT, 0); }
		public TerminalNode GLOBALLY() { return getToken(goalParser.GLOBALLY, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitUnary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unary_operator);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(NOT);
				setState(105);
				formula();
				}
				break;
			case FINALLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				match(FINALLY);
				setState(107);
				formula();
				}
				break;
			case NEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(NEXT);
				setState(109);
				formula();
				}
				break;
			case GLOBALLY:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				match(GLOBALLY);
				setState(111);
				formula();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_operatorContext extends ParserRuleContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode AND() { return getToken(goalParser.AND, 0); }
		public TerminalNode OR() { return getToken(goalParser.OR, 0); }
		public TerminalNode IF() { return getToken(goalParser.IF, 0); }
		public TerminalNode IFF() { return getToken(goalParser.IFF, 0); }
		public TerminalNode UNTIL() { return getToken(goalParser.UNTIL, 0); }
		public TerminalNode RELEASE() { return getToken(goalParser.RELEASE, 0); }
		public Binary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitBinary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operatorContext binary_operator() throws RecognitionException {
		Binary_operatorContext _localctx = new Binary_operatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_binary_operator);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				formula();
				setState(115);
				match(AND);
				setState(116);
				formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				formula();
				setState(119);
				match(OR);
				setState(120);
				formula();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				formula();
				setState(123);
				match(IF);
				setState(124);
				formula();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				formula();
				setState(127);
				match(IFF);
				setState(128);
				formula();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				formula();
				setState(131);
				match(UNTIL);
				setState(132);
				formula();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(134);
				formula();
				setState(135);
				match(RELEASE);
				setState(136);
				formula();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_predicate);
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				atom();
				setState(142);
				match(LB);
				setState(143);
				termlist(0);
				setState(144);
				match(RB);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermlistContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public TermlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitTermlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermlistContext termlist() throws RecognitionException {
		return termlist(0);
	}

	private TermlistContext termlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermlistContext _localctx = new TermlistContext(_ctx, _parentState);
		TermlistContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_termlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				}
				break;
			case 2:
				{
				setState(149);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermlistContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_termlist);
					setState(152);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(153);
					match(COMMA);
					setState(154);
					term();
					}
					} 
				}
				setState(159);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term);
		try {
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				numeral(0);
				}
				break;
			case UCLETTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				variable(0);
				}
				break;
			case T__0:
			case LCLETTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				predicate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public SmallatomContext smallatom() {
			return getRuleContext(SmallatomContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_atom);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LCLETTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				smallatom(0);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(T__0);
				setState(167);
				string(0);
				setState(168);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SmallatomContext extends ParserRuleContext {
		public TerminalNode LCLETTER() { return getToken(goalParser.LCLETTER, 0); }
		public SmallatomContext smallatom() {
			return getRuleContext(SmallatomContext.class,0);
		}
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public SmallatomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smallatom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitSmallatom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SmallatomContext smallatom() throws RecognitionException {
		return smallatom(0);
	}

	private SmallatomContext smallatom(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SmallatomContext _localctx = new SmallatomContext(_ctx, _parentState);
		SmallatomContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_smallatom, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173);
			match(LCLETTER);
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SmallatomContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_smallatom);
					setState(175);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(176);
					character();
					}
					} 
				}
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Var_listContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(goalParser.COMMA, 0); }
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public Var_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitVar_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_listContext var_list() throws RecognitionException {
		Var_listContext _localctx = new Var_listContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_var_list);
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				variable(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				variable(0);
				setState(184);
				match(COMMA);
				setState(185);
				var_list();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode UCLETTER() { return getToken(goalParser.UCLETTER, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		return variable(0);
	}

	private VariableContext variable(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VariableContext _localctx = new VariableContext(_ctx, _parentState);
		VariableContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_variable, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(190);
			match(UCLETTER);
			}
			_ctx.stop = _input.LT(-1);
			setState(196);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VariableContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_variable);
					setState(192);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(193);
					character();
					}
					} 
				}
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NumeralContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(goalParser.DIGIT, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public NumeralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitNumeral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumeralContext numeral() throws RecognitionException {
		return numeral(0);
	}

	private NumeralContext numeral(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NumeralContext _localctx = new NumeralContext(_ctx, _parentState);
		NumeralContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_numeral, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(200);
			match(DIGIT);
			}
			_ctx.stop = _input.LT(-1);
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NumeralContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_numeral);
					setState(202);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(203);
					match(DIGIT);
					}
					} 
				}
				setState(208);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		return string(0);
	}

	private StringContext string(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StringContext _localctx = new StringContext(_ctx, _parentState);
		StringContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_string, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(210);
			character();
			}
			_ctx.stop = _input.LT(-1);
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StringContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_string);
					setState(212);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(213);
					character();
					}
					} 
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CharacterContext extends ParserRuleContext {
		public TerminalNode LCLETTER() { return getToken(goalParser.LCLETTER, 0); }
		public TerminalNode UCLETTER() { return getToken(goalParser.UCLETTER, 0); }
		public TerminalNode DIGIT() { return getToken(goalParser.DIGIT, 0); }
		public CharacterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitCharacter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharacterContext character() throws RecognitionException {
		CharacterContext _localctx = new CharacterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_character);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LCLETTER) | (1L << UCLETTER) | (1L << DIGIT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return termlist_sempred((TermlistContext)_localctx, predIndex);
		case 14:
			return smallatom_sempred((SmallatomContext)_localctx, predIndex);
		case 16:
			return variable_sempred((VariableContext)_localctx, predIndex);
		case 17:
			return numeral_sempred((NumeralContext)_localctx, predIndex);
		case 18:
			return string_sempred((StringContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean termlist_sempred(TermlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean smallatom_sempred(SmallatomContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean variable_sempred(VariableContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean numeral_sempred(NumeralContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean string_sempred(StringContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\5\3\65\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4D\n"+
		"\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6L\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7X\n\7\3\b\3\b\3\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\ti\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ns\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008d\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0095\n\f\3\r\3\r\5\r\u0099\n\r\3\r\3\r\3\r\7\r\u009e\n\r\f"+
		"\r\16\r\u00a1\13\r\3\16\3\16\3\16\5\16\u00a6\n\16\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00ad\n\17\3\20\3\20\3\20\3\20\3\20\7\20\u00b4\n\20\f\20\16"+
		"\20\u00b7\13\20\3\21\3\21\3\21\3\21\3\21\5\21\u00be\n\21\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u00c5\n\22\f\22\16\22\u00c8\13\22\3\23\3\23\3\23\3\23"+
		"\3\23\7\23\u00cf\n\23\f\23\16\23\u00d2\13\23\3\24\3\24\3\24\3\24\3\24"+
		"\7\24\u00d9\n\24\f\24\16\24\u00dc\13\24\3\25\3\25\3\25\2\7\30\36\"$&\26"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\3\3\2\36 \2\u00e8\2*\3"+
		"\2\2\2\4\64\3\2\2\2\6C\3\2\2\2\bE\3\2\2\2\nK\3\2\2\2\fW\3\2\2\2\16^\3"+
		"\2\2\2\20h\3\2\2\2\22r\3\2\2\2\24\u008c\3\2\2\2\26\u0094\3\2\2\2\30\u0098"+
		"\3\2\2\2\32\u00a5\3\2\2\2\34\u00ac\3\2\2\2\36\u00ae\3\2\2\2 \u00bd\3\2"+
		"\2\2\"\u00bf\3\2\2\2$\u00c9\3\2\2\2&\u00d3\3\2\2\2(\u00dd\3\2\2\2*+\7"+
		"\4\2\2+,\7\6\2\2,-\5\4\3\2-.\7\7\2\2.\3\3\2\2\2/\65\3\2\2\2\60\65\5\6"+
		"\4\2\61\62\5\6\4\2\62\63\5\4\3\2\63\65\3\2\2\2\64/\3\2\2\2\64\60\3\2\2"+
		"\2\64\61\3\2\2\2\65\5\3\2\2\2\66\67\7\5\2\2\678\5\36\20\289\7\b\2\29:"+
		"\5\n\6\2:;\7\t\2\2;D\3\2\2\2<=\7\5\2\2=>\5\36\20\2>?\7\b\2\2?@\5\n\6\2"+
		"@A\5\b\5\2AB\7\t\2\2BD\3\2\2\2C\66\3\2\2\2C<\3\2\2\2D\7\3\2\2\2EF\7\34"+
		"\2\2FG\5$\23\2GH\7\35\2\2H\t\3\2\2\2IL\5\16\b\2JL\5\f\7\2KI\3\2\2\2KJ"+
		"\3\2\2\2L\13\3\2\2\2MN\7\n\2\2NO\5 \21\2OP\7\f\2\2PQ\5\16\b\2QX\3\2\2"+
		"\2RS\7\13\2\2ST\5 \21\2TU\7\f\2\2UV\5\16\b\2VX\3\2\2\2WM\3\2\2\2WR\3\2"+
		"\2\2X\r\3\2\2\2Y_\7\r\2\2Z_\7\16\2\2[_\5\26\f\2\\_\5\22\n\2]_\5\20\t\2"+
		"^Y\3\2\2\2^Z\3\2\2\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_\17\3\2\2\2`a\7\31"+
		"\2\2ab\5\16\b\2bc\7\32\2\2ci\3\2\2\2de\7\31\2\2ef\5\24\13\2fg\7\32\2\2"+
		"gi\3\2\2\2h`\3\2\2\2hd\3\2\2\2i\21\3\2\2\2jk\7\24\2\2ks\5\16\b\2lm\7\21"+
		"\2\2ms\5\16\b\2no\7\20\2\2os\5\16\b\2pq\7\17\2\2qs\5\16\b\2rj\3\2\2\2"+
		"rl\3\2\2\2rn\3\2\2\2rp\3\2\2\2s\23\3\2\2\2tu\5\16\b\2uv\7\25\2\2vw\5\16"+
		"\b\2w\u008d\3\2\2\2xy\5\16\b\2yz\7\26\2\2z{\5\16\b\2{\u008d\3\2\2\2|}"+
		"\5\16\b\2}~\7\27\2\2~\177\5\16\b\2\177\u008d\3\2\2\2\u0080\u0081\5\16"+
		"\b\2\u0081\u0082\7\30\2\2\u0082\u0083\5\16\b\2\u0083\u008d\3\2\2\2\u0084"+
		"\u0085\5\16\b\2\u0085\u0086\7\22\2\2\u0086\u0087\5\16\b\2\u0087\u008d"+
		"\3\2\2\2\u0088\u0089\5\16\b\2\u0089\u008a\7\23\2\2\u008a\u008b\5\16\b"+
		"\2\u008b\u008d\3\2\2\2\u008ct\3\2\2\2\u008cx\3\2\2\2\u008c|\3\2\2\2\u008c"+
		"\u0080\3\2\2\2\u008c\u0084\3\2\2\2\u008c\u0088\3\2\2\2\u008d\25\3\2\2"+
		"\2\u008e\u0095\5\34\17\2\u008f\u0090\5\34\17\2\u0090\u0091\7\31\2\2\u0091"+
		"\u0092\5\30\r\2\u0092\u0093\7\32\2\2\u0093\u0095\3\2\2\2\u0094\u008e\3"+
		"\2\2\2\u0094\u008f\3\2\2\2\u0095\27\3\2\2\2\u0096\u0099\b\r\1\2\u0097"+
		"\u0099\5\32\16\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\u009f\3"+
		"\2\2\2\u009a\u009b\f\3\2\2\u009b\u009c\7\33\2\2\u009c\u009e\5\32\16\2"+
		"\u009d\u009a\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\31\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a6\5$\23\2\u00a3"+
		"\u00a6\5\"\22\2\u00a4\u00a6\5\26\f\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3\3"+
		"\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\33\3\2\2\2\u00a7\u00ad\5\36\20\2\u00a8"+
		"\u00a9\7\3\2\2\u00a9\u00aa\5&\24\2\u00aa\u00ab\7\3\2\2\u00ab\u00ad\3\2"+
		"\2\2\u00ac\u00a7\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ad\35\3\2\2\2\u00ae\u00af"+
		"\b\20\1\2\u00af\u00b0\7\36\2\2\u00b0\u00b5\3\2\2\2\u00b1\u00b2\f\3\2\2"+
		"\u00b2\u00b4\5(\25\2\u00b3\u00b1\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\37\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8"+
		"\u00be\5\"\22\2\u00b9\u00ba\5\"\22\2\u00ba\u00bb\7\33\2\2\u00bb\u00bc"+
		"\5 \21\2\u00bc\u00be\3\2\2\2\u00bd\u00b8\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be"+
		"!\3\2\2\2\u00bf\u00c0\b\22\1\2\u00c0\u00c1\7\37\2\2\u00c1\u00c6\3\2\2"+
		"\2\u00c2\u00c3\f\3\2\2\u00c3\u00c5\5(\25\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7#\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00ca\b\23\1\2\u00ca\u00cb\7 \2\2\u00cb\u00d0\3\2"+
		"\2\2\u00cc\u00cd\f\3\2\2\u00cd\u00cf\7 \2\2\u00ce\u00cc\3\2\2\2\u00cf"+
		"\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1%\3\2\2\2"+
		"\u00d2\u00d0\3\2\2\2\u00d3\u00d4\b\24\1\2\u00d4\u00d5\5(\25\2\u00d5\u00da"+
		"\3\2\2\2\u00d6\u00d7\f\3\2\2\u00d7\u00d9\5(\25\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\'\3\2\2\2"+
		"\u00dc\u00da\3\2\2\2\u00dd\u00de\t\2\2\2\u00de)\3\2\2\2\24\64CKW^hr\u008c"+
		"\u0094\u0098\u009f\u00a5\u00ac\u00b5\u00bd\u00c6\u00d0\u00da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}