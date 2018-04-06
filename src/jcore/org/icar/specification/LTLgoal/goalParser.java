// Generated from goal.g4 by ANTLR 4.7.1

package org.icar.specification.LTLgoal;

import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class goalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, FLOAT=27, VARIABLEID=28, ATOMID=29, STRING=30, ESC=31, 
		LCLETTER=32, UCLETTER=33, DIGIT=34, COMMENT=35, WS=36;
	public static final int
		RULE_goal_model = 0, RULE_ltl_extended_list = 1, RULE_single_goal = 2, 
		RULE_goalpriority = 3, RULE_ltl_extended = 4, RULE_quantified_formula = 5, 
		RULE_formula = 6, RULE_bracketed = 7, RULE_unary_operator = 8, RULE_binary_operator = 9, 
		RULE_predicate = 10, RULE_termlist = 11, RULE_term = 12, RULE_atom = 13, 
		RULE_var_list = 14, RULE_variable = 15, RULE_numeral = 16;
	public static final String[] ruleNames = {
		"goal_model", "ltl_extended_list", "single_goal", "goalpriority", "ltl_extended", 
		"quantified_formula", "formula", "bracketed", "unary_operator", "binary_operator", 
		"predicate", "termlist", "term", "atom", "var_list", "variable", "numeral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'goalmodel'", "'{'", "'}'", "'goal'", "'='", "'.'", "'['", "']'", 
		"'foreach'", "':'", "'exists'", "'true'", "'false'", "'('", "')'", "'not'", 
		"'F'", "'X'", "'G'", "'and'", "'or'", "'->'", "'<->'", "'U'", "'R'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "FLOAT", "VARIABLEID", "ATOMID", "STRING", "ESC", "LCLETTER", 
		"UCLETTER", "DIGIT", "COMMENT", "WS"
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
		public Ltl_extended_listContext ltl_extended_list() {
			return getRuleContext(Ltl_extended_listContext.class,0);
		}
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
			setState(34);
			match(T__0);
			setState(35);
			match(T__1);
			setState(36);
			ltl_extended_list();
			setState(37);
			match(T__2);
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
			setState(44);
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
				setState(40);
				single_goal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				single_goal();
				setState(42);
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
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Ltl_extendedContext ltl_extended() {
			return getRuleContext(Ltl_extendedContext.class,0);
		}
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
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(T__3);
				setState(47);
				atom();
				setState(48);
				match(T__4);
				setState(49);
				ltl_extended();
				setState(50);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__3);
				setState(53);
				atom();
				setState(54);
				match(T__4);
				setState(55);
				ltl_extended();
				setState(56);
				goalpriority();
				setState(57);
				match(T__5);
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
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
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
			setState(61);
			match(T__6);
			setState(62);
			numeral();
			setState(63);
			match(T__7);
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
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
			case T__12:
			case T__13:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case ATOMID:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				formula();
				}
				break;
			case T__8:
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
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
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
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
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
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
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				_localctx = new UniversalQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(T__8);
				setState(70);
				var_list();
				setState(71);
				match(T__9);
				setState(72);
				formula();
				}
				break;
			case T__10:
				_localctx = new ExistentialQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__10);
				setState(75);
				var_list();
				setState(76);
				match(T__9);
				setState(77);
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
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				_localctx = new AlwaysTrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(T__11);
				}
				break;
			case T__12:
				_localctx = new AlwaysFalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				match(T__12);
				}
				break;
			case ATOMID:
				_localctx = new APredicateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				predicate();
				}
				break;
			case T__15:
			case T__16:
			case T__17:
			case T__18:
				_localctx = new UnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(84);
				unary_operator();
				}
				break;
			case T__13:
				_localctx = new FormulaWithBracketContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(85);
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
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
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
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(T__13);
				setState(89);
				formula();
				setState(90);
				match(T__14);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				match(T__13);
				setState(93);
				binary_operator();
				setState(94);
				match(T__14);
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
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
	 
		public Unary_operatorContext() { }
		public void copyFrom(Unary_operatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotUnaryOperatorContext extends Unary_operatorContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public NotUnaryOperatorContext(Unary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitNotUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GloballyUnaryOperatorContext extends Unary_operatorContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public GloballyUnaryOperatorContext(Unary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitGloballyUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NextUnaryOperatorContext extends Unary_operatorContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public NextUnaryOperatorContext(Unary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitNextUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FinallyUnaryOperatorContext extends Unary_operatorContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public FinallyUnaryOperatorContext(Unary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitFinallyUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unary_operator);
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				_localctx = new NotUnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__15);
				setState(99);
				formula();
				}
				break;
			case T__16:
				_localctx = new FinallyUnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__16);
				setState(101);
				formula();
				}
				break;
			case T__17:
				_localctx = new NextUnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__17);
				setState(103);
				formula();
				}
				break;
			case T__18:
				_localctx = new GloballyUnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(104);
				match(T__18);
				setState(105);
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
		public Binary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operator; }
	 
		public Binary_operatorContext() { }
		public void copyFrom(Binary_operatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AndBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public AndBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitAndBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public OrBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitOrBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IffBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public IffBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitIffBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReleaseBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public ReleaseBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitReleaseBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UntilBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public UntilBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitUntilBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfBinaryOperatorContext extends Binary_operatorContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public IfBinaryOperatorContext(Binary_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof goalVisitor ) return ((goalVisitor<? extends T>)visitor).visitIfBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operatorContext binary_operator() throws RecognitionException {
		Binary_operatorContext _localctx = new Binary_operatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_binary_operator);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new AndBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				formula();
				setState(109);
				match(T__19);
				setState(110);
				formula();
				}
				break;
			case 2:
				_localctx = new OrBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				formula();
				setState(113);
				match(T__20);
				setState(114);
				formula();
				}
				break;
			case 3:
				_localctx = new IfBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				formula();
				setState(117);
				match(T__21);
				setState(118);
				formula();
				}
				break;
			case 4:
				_localctx = new IffBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				formula();
				setState(121);
				match(T__22);
				setState(122);
				formula();
				}
				break;
			case 5:
				_localctx = new UntilBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(124);
				formula();
				setState(125);
				match(T__23);
				setState(126);
				formula();
				}
				break;
			case 6:
				_localctx = new ReleaseBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(128);
				formula();
				setState(129);
				match(T__24);
				setState(130);
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
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				atom();
				setState(136);
				match(T__13);
				setState(137);
				termlist(0);
				setState(138);
				match(T__14);
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
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				}
				break;
			case 2:
				{
				setState(143);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(151);
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
					setState(146);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(147);
					match(T__25);
					setState(148);
					term();
					}
					} 
				}
				setState(153);
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
		public TerminalNode STRING() { return getToken(goalParser.STRING, 0); }
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
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				numeral();
				}
				break;
			case VARIABLEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				variable();
				}
				break;
			case ATOMID:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				predicate();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(157);
				match(STRING);
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
		public TerminalNode ATOMID() { return getToken(goalParser.ATOMID, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(ATOMID);
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

	public static class Var_listContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
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
		enterRule(_localctx, 28, RULE_var_list);
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				variable();
				setState(164);
				match(T__25);
				setState(165);
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
		public TerminalNode VARIABLEID() { return getToken(goalParser.VARIABLEID, 0); }
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
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(VARIABLEID);
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

	public static class NumeralContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(goalParser.FLOAT, 0); }
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
		NumeralContext _localctx = new NumeralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_numeral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(FLOAT);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00b0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4>\n\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6"+
		"F\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7R\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\5\bY\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tc\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\nm\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u0087\n\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u008f\n\f\3\r\3\r\5"+
		"\r\u0093\n\r\3\r\3\r\3\r\7\r\u0098\n\r\f\r\16\r\u009b\13\r\3\16\3\16\3"+
		"\16\3\16\5\16\u00a1\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\5\20\u00aa"+
		"\n\20\3\21\3\21\3\22\3\22\3\22\2\3\30\23\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"\2\2\2\u00b7\2$\3\2\2\2\4.\3\2\2\2\6=\3\2\2\2\b?\3\2\2\2\n"+
		"E\3\2\2\2\fQ\3\2\2\2\16X\3\2\2\2\20b\3\2\2\2\22l\3\2\2\2\24\u0086\3\2"+
		"\2\2\26\u008e\3\2\2\2\30\u0092\3\2\2\2\32\u00a0\3\2\2\2\34\u00a2\3\2\2"+
		"\2\36\u00a9\3\2\2\2 \u00ab\3\2\2\2\"\u00ad\3\2\2\2$%\7\3\2\2%&\7\4\2\2"+
		"&\'\5\4\3\2\'(\7\5\2\2(\3\3\2\2\2)/\3\2\2\2*/\5\6\4\2+,\5\6\4\2,-\5\4"+
		"\3\2-/\3\2\2\2.)\3\2\2\2.*\3\2\2\2.+\3\2\2\2/\5\3\2\2\2\60\61\7\6\2\2"+
		"\61\62\5\34\17\2\62\63\7\7\2\2\63\64\5\n\6\2\64\65\7\b\2\2\65>\3\2\2\2"+
		"\66\67\7\6\2\2\678\5\34\17\289\7\7\2\29:\5\n\6\2:;\5\b\5\2;<\7\b\2\2<"+
		">\3\2\2\2=\60\3\2\2\2=\66\3\2\2\2>\7\3\2\2\2?@\7\t\2\2@A\5\"\22\2AB\7"+
		"\n\2\2B\t\3\2\2\2CF\5\16\b\2DF\5\f\7\2EC\3\2\2\2ED\3\2\2\2F\13\3\2\2\2"+
		"GH\7\13\2\2HI\5\36\20\2IJ\7\f\2\2JK\5\16\b\2KR\3\2\2\2LM\7\r\2\2MN\5\36"+
		"\20\2NO\7\f\2\2OP\5\16\b\2PR\3\2\2\2QG\3\2\2\2QL\3\2\2\2R\r\3\2\2\2SY"+
		"\7\16\2\2TY\7\17\2\2UY\5\26\f\2VY\5\22\n\2WY\5\20\t\2XS\3\2\2\2XT\3\2"+
		"\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\17\3\2\2\2Z[\7\20\2\2[\\\5\16\b\2"+
		"\\]\7\21\2\2]c\3\2\2\2^_\7\20\2\2_`\5\24\13\2`a\7\21\2\2ac\3\2\2\2bZ\3"+
		"\2\2\2b^\3\2\2\2c\21\3\2\2\2de\7\22\2\2em\5\16\b\2fg\7\23\2\2gm\5\16\b"+
		"\2hi\7\24\2\2im\5\16\b\2jk\7\25\2\2km\5\16\b\2ld\3\2\2\2lf\3\2\2\2lh\3"+
		"\2\2\2lj\3\2\2\2m\23\3\2\2\2no\5\16\b\2op\7\26\2\2pq\5\16\b\2q\u0087\3"+
		"\2\2\2rs\5\16\b\2st\7\27\2\2tu\5\16\b\2u\u0087\3\2\2\2vw\5\16\b\2wx\7"+
		"\30\2\2xy\5\16\b\2y\u0087\3\2\2\2z{\5\16\b\2{|\7\31\2\2|}\5\16\b\2}\u0087"+
		"\3\2\2\2~\177\5\16\b\2\177\u0080\7\32\2\2\u0080\u0081\5\16\b\2\u0081\u0087"+
		"\3\2\2\2\u0082\u0083\5\16\b\2\u0083\u0084\7\33\2\2\u0084\u0085\5\16\b"+
		"\2\u0085\u0087\3\2\2\2\u0086n\3\2\2\2\u0086r\3\2\2\2\u0086v\3\2\2\2\u0086"+
		"z\3\2\2\2\u0086~\3\2\2\2\u0086\u0082\3\2\2\2\u0087\25\3\2\2\2\u0088\u008f"+
		"\5\34\17\2\u0089\u008a\5\34\17\2\u008a\u008b\7\20\2\2\u008b\u008c\5\30"+
		"\r\2\u008c\u008d\7\21\2\2\u008d\u008f\3\2\2\2\u008e\u0088\3\2\2\2\u008e"+
		"\u0089\3\2\2\2\u008f\27\3\2\2\2\u0090\u0093\b\r\1\2\u0091\u0093\5\32\16"+
		"\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0099\3\2\2\2\u0094\u0095"+
		"\f\3\2\2\u0095\u0096\7\34\2\2\u0096\u0098\5\32\16\2\u0097\u0094\3\2\2"+
		"\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\31"+
		"\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u00a1\5\"\22\2\u009d\u00a1\5 \21\2"+
		"\u009e\u00a1\5\26\f\2\u009f\u00a1\7 \2\2\u00a0\u009c\3\2\2\2\u00a0\u009d"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\33\3\2\2\2\u00a2"+
		"\u00a3\7\37\2\2\u00a3\35\3\2\2\2\u00a4\u00aa\5 \21\2\u00a5\u00a6\5 \21"+
		"\2\u00a6\u00a7\7\34\2\2\u00a7\u00a8\5\36\20\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a4\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa\37\3\2\2\2\u00ab\u00ac\7\36\2"+
		"\2\u00ac!\3\2\2\2\u00ad\u00ae\7\35\2\2\u00ae#\3\2\2\2\17.=EQXbl\u0086"+
		"\u008e\u0092\u0099\u00a0\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}