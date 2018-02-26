// Generated from capability.g4 by ANTLR 4.7.1

package org.icar.specification.ACLanguage;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class capabilityParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, FLOAT=24, 
		VARIABLEID=25, ATOMID=26, STRING=27, ESC=28, LCLETTER=29, UCLETTER=30, 
		DIGIT=31, COMMENT=32, WS=33;
	public static final int
		RULE_capability = 0, RULE_cap_body = 1, RULE_precondition = 2, RULE_postcondition = 3, 
		RULE_evolution = 4, RULE_evo_action_list = 5, RULE_action = 6, RULE_condition = 7, 
		RULE_quantified_predicate_formula = 8, RULE_predicate_formula = 9, RULE_neg_predicate = 10, 
		RULE_bracketed_predicate = 11, RULE_binary_pred_operator = 12, RULE_predicate = 13, 
		RULE_termlist = 14, RULE_term = 15, RULE_atom = 16, RULE_var_list = 17, 
		RULE_variable = 18, RULE_numeral = 19;
	public static final String[] ruleNames = {
		"capability", "cap_body", "precondition", "postcondition", "evolution", 
		"evo_action_list", "action", "condition", "quantified_predicate_formula", 
		"predicate_formula", "neg_predicate", "bracketed_predicate", "binary_pred_operator", 
		"predicate", "termlist", "term", "atom", "var_list", "variable", "numeral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'capability'", "'{'", "'}'", "'pre:'", "'post:'", "'scenario'", 
		"'('", "')'", "','", "'add'", "'remove'", "'clear'", "'foreach'", "':'", 
		"'exists'", "'true'", "'false'", "'not'", "'!'", "'and'", "'or'", "'->'", 
		"'<->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"FLOAT", "VARIABLEID", "ATOMID", "STRING", "ESC", "LCLETTER", "UCLETTER", 
		"DIGIT", "COMMENT", "WS"
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
	public String getGrammarFileName() { return "capability.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public capabilityParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CapabilityContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Cap_bodyContext cap_body() {
			return getRuleContext(Cap_bodyContext.class,0);
		}
		public CapabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capability; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitCapability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CapabilityContext capability() throws RecognitionException {
		CapabilityContext _localctx = new CapabilityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_capability);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__0);
			setState(41);
			atom();
			setState(42);
			match(T__1);
			setState(43);
			cap_body();
			setState(44);
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

	public static class Cap_bodyContext extends ParserRuleContext {
		public PreconditionContext precondition() {
			return getRuleContext(PreconditionContext.class,0);
		}
		public PostconditionContext postcondition() {
			return getRuleContext(PostconditionContext.class,0);
		}
		public List<EvolutionContext> evolution() {
			return getRuleContexts(EvolutionContext.class);
		}
		public EvolutionContext evolution(int i) {
			return getRuleContext(EvolutionContext.class,i);
		}
		public Cap_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cap_body; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitCap_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cap_bodyContext cap_body() throws RecognitionException {
		Cap_bodyContext _localctx = new Cap_bodyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cap_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(46);
				precondition();
				}
			}

			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(49);
				postcondition();
				}
			}

			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(52);
				evolution();
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class PreconditionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public PreconditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitPrecondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreconditionContext precondition() throws RecognitionException {
		PreconditionContext _localctx = new PreconditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_precondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__3);
			setState(59);
			condition();
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

	public static class PostconditionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public PostconditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postcondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitPostcondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostconditionContext postcondition() throws RecognitionException {
		PostconditionContext _localctx = new PostconditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_postcondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__4);
			setState(62);
			condition();
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

	public static class EvolutionContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Evo_action_listContext evo_action_list() {
			return getRuleContext(Evo_action_listContext.class,0);
		}
		public EvolutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evolution; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitEvolution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvolutionContext evolution() throws RecognitionException {
		EvolutionContext _localctx = new EvolutionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_evolution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__5);
			setState(65);
			atom();
			setState(66);
			match(T__6);
			setState(67);
			evo_action_list();
			setState(68);
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

	public static class Evo_action_listContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public Evo_action_listContext evo_action_list() {
			return getRuleContext(Evo_action_listContext.class,0);
		}
		public Evo_action_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evo_action_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitEvo_action_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Evo_action_listContext evo_action_list() throws RecognitionException {
		Evo_action_listContext _localctx = new Evo_action_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_evo_action_list);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				action();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				action();
				setState(72);
				match(T__8);
				setState(73);
				evo_action_list();
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

	public static class ActionContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_action);
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(T__9);
				setState(78);
				predicate();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(T__10);
				setState(80);
				predicate();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(T__11);
				setState(82);
				atom();
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

	public static class ConditionContext extends ParserRuleContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public Quantified_predicate_formulaContext quantified_predicate_formula() {
			return getRuleContext(Quantified_predicate_formulaContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_condition);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case ATOMID:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				predicate_formula();
				}
				break;
			case T__12:
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				quantified_predicate_formula();
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

	public static class Quantified_predicate_formulaContext extends ParserRuleContext {
		public Quantified_predicate_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantified_predicate_formula; }
	 
		public Quantified_predicate_formulaContext() { }
		public void copyFrom(Quantified_predicate_formulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExistentialPredQuantifierContext extends Quantified_predicate_formulaContext {
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public ExistentialPredQuantifierContext(Quantified_predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitExistentialPredQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UniversalPredQuantifierContext extends Quantified_predicate_formulaContext {
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public UniversalPredQuantifierContext(Quantified_predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitUniversalPredQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantified_predicate_formulaContext quantified_predicate_formula() throws RecognitionException {
		Quantified_predicate_formulaContext _localctx = new Quantified_predicate_formulaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_quantified_predicate_formula);
		try {
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				_localctx = new UniversalPredQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				match(T__12);
				setState(90);
				var_list();
				setState(91);
				match(T__13);
				setState(92);
				predicate_formula();
				}
				break;
			case T__14:
				_localctx = new ExistentialPredQuantifierContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(T__14);
				setState(95);
				var_list();
				setState(96);
				match(T__13);
				setState(97);
				predicate_formula();
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

	public static class Predicate_formulaContext extends ParserRuleContext {
		public Predicate_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_formula; }
	 
		public Predicate_formulaContext() { }
		public void copyFrom(Predicate_formulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AlwaysFalseContext extends Predicate_formulaContext {
		public AlwaysFalseContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAlwaysFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegPredOperatorContext extends Predicate_formulaContext {
		public Neg_predicateContext neg_predicate() {
			return getRuleContext(Neg_predicateContext.class,0);
		}
		public NegPredOperatorContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitNegPredOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class APredicateContext extends Predicate_formulaContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public APredicateContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlwaysTrueContext extends Predicate_formulaContext {
		public AlwaysTrueContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAlwaysTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FormulaPredWithBracketContext extends Predicate_formulaContext {
		public Bracketed_predicateContext bracketed_predicate() {
			return getRuleContext(Bracketed_predicateContext.class,0);
		}
		public FormulaPredWithBracketContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitFormulaPredWithBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_formulaContext predicate_formula() throws RecognitionException {
		Predicate_formulaContext _localctx = new Predicate_formulaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_predicate_formula);
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				_localctx = new AlwaysTrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(T__15);
				}
				break;
			case T__16:
				_localctx = new AlwaysFalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(T__16);
				}
				break;
			case ATOMID:
				_localctx = new APredicateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				predicate();
				}
				break;
			case T__17:
			case T__18:
				_localctx = new NegPredOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(104);
				neg_predicate();
				}
				break;
			case T__6:
				_localctx = new FormulaPredWithBracketContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(105);
				bracketed_predicate();
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

	public static class Neg_predicateContext extends ParserRuleContext {
		public Neg_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neg_predicate; }
	 
		public Neg_predicateContext() { }
		public void copyFrom(Neg_predicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotExclOperatorContext extends Neg_predicateContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public NotExclOperatorContext(Neg_predicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitNotExclOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotOperatorContext extends Neg_predicateContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public NotOperatorContext(Neg_predicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitNotOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Neg_predicateContext neg_predicate() throws RecognitionException {
		Neg_predicateContext _localctx = new Neg_predicateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_neg_predicate);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				_localctx = new NotOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(T__17);
				setState(109);
				predicate_formula();
				}
				break;
			case T__18:
				_localctx = new NotExclOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(T__18);
				setState(111);
				predicate_formula();
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

	public static class Bracketed_predicateContext extends ParserRuleContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public Binary_pred_operatorContext binary_pred_operator() {
			return getRuleContext(Binary_pred_operatorContext.class,0);
		}
		public Bracketed_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracketed_predicate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitBracketed_predicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bracketed_predicateContext bracketed_predicate() throws RecognitionException {
		Bracketed_predicateContext _localctx = new Bracketed_predicateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bracketed_predicate);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(T__6);
				setState(115);
				predicate_formula();
				setState(116);
				match(T__7);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(T__6);
				setState(119);
				binary_pred_operator();
				setState(120);
				match(T__7);
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

	public static class Binary_pred_operatorContext extends ParserRuleContext {
		public Binary_pred_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_pred_operator; }
	 
		public Binary_pred_operatorContext() { }
		public void copyFrom(Binary_pred_operatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OrPredBinaryOperatorContext extends Binary_pred_operatorContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public OrPredBinaryOperatorContext(Binary_pred_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitOrPredBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfPredBinaryOperatorContext extends Binary_pred_operatorContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public IfPredBinaryOperatorContext(Binary_pred_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitIfPredBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndPredBinaryOperatorContext extends Binary_pred_operatorContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public AndPredBinaryOperatorContext(Binary_pred_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAndPredBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IffPredBinaryOperatorContext extends Binary_pred_operatorContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public IffPredBinaryOperatorContext(Binary_pred_operatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitIffPredBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_pred_operatorContext binary_pred_operator() throws RecognitionException {
		Binary_pred_operatorContext _localctx = new Binary_pred_operatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_binary_pred_operator);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new AndPredBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				predicate_formula();
				setState(125);
				match(T__19);
				setState(126);
				predicate_formula();
				}
				break;
			case 2:
				_localctx = new OrPredBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				predicate_formula();
				setState(129);
				match(T__20);
				setState(130);
				predicate_formula();
				}
				break;
			case 3:
				_localctx = new IfPredBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				predicate_formula();
				setState(133);
				match(T__21);
				setState(134);
				predicate_formula();
				}
				break;
			case 4:
				_localctx = new IffPredBinaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				predicate_formula();
				setState(137);
				match(T__22);
				setState(138);
				predicate_formula();
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
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_predicate);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				atom();
				setState(144);
				match(T__6);
				setState(145);
				termlist(0);
				setState(146);
				match(T__7);
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
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitTermlist(this);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_termlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				}
				break;
			case 2:
				{
				setState(151);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermlistContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_termlist);
					setState(154);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(155);
					match(T__8);
					setState(156);
					term();
					}
					} 
				}
				setState(161);
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
		public TerminalNode STRING() { return getToken(capabilityParser.STRING, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_term);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				numeral();
				}
				break;
			case VARIABLEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				variable();
				}
				break;
			case ATOMID:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				predicate();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
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
		public TerminalNode ATOMID() { return getToken(capabilityParser.ATOMID, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitVar_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_listContext var_list() throws RecognitionException {
		Var_listContext _localctx = new Var_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_var_list);
		try {
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				variable();
				setState(172);
				match(T__8);
				setState(173);
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
		public TerminalNode VARIABLEID() { return getToken(capabilityParser.VARIABLEID, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
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
		public TerminalNode FLOAT() { return getToken(capabilityParser.FLOAT, 0); }
		public NumeralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitNumeral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumeralContext numeral() throws RecognitionException {
		NumeralContext _localctx = new NumeralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_numeral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
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
		case 14:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00b8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\3\5\3\62\n\3"+
		"\3\3\5\3\65\n\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7N\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\bV\n\b\3\t\3\t\5\tZ\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\nf\n\n\3\13\3\13\3\13\3\13\3\13\5\13m\n\13\3\f\3\f\3\f\3\f\5\fs\n"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r}\n\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008f\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0097\n\17\3\20\3\20\5\20\u009b\n"+
		"\20\3\20\3\20\3\20\7\20\u00a0\n\20\f\20\16\20\u00a3\13\20\3\21\3\21\3"+
		"\21\3\21\5\21\u00a9\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\5\23\u00b2"+
		"\n\23\3\24\3\24\3\25\3\25\3\25\2\3\36\26\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(\2\2\2\u00bb\2*\3\2\2\2\4\61\3\2\2\2\6<\3\2\2\2\b?\3\2"+
		"\2\2\nB\3\2\2\2\fM\3\2\2\2\16U\3\2\2\2\20Y\3\2\2\2\22e\3\2\2\2\24l\3\2"+
		"\2\2\26r\3\2\2\2\30|\3\2\2\2\32\u008e\3\2\2\2\34\u0096\3\2\2\2\36\u009a"+
		"\3\2\2\2 \u00a8\3\2\2\2\"\u00aa\3\2\2\2$\u00b1\3\2\2\2&\u00b3\3\2\2\2"+
		"(\u00b5\3\2\2\2*+\7\3\2\2+,\5\"\22\2,-\7\4\2\2-.\5\4\3\2./\7\5\2\2/\3"+
		"\3\2\2\2\60\62\5\6\4\2\61\60\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\65"+
		"\5\b\5\2\64\63\3\2\2\2\64\65\3\2\2\2\659\3\2\2\2\668\5\n\6\2\67\66\3\2"+
		"\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;9\3\2\2\2<=\7\6\2\2=>"+
		"\5\20\t\2>\7\3\2\2\2?@\7\7\2\2@A\5\20\t\2A\t\3\2\2\2BC\7\b\2\2CD\5\"\22"+
		"\2DE\7\t\2\2EF\5\f\7\2FG\7\n\2\2G\13\3\2\2\2HN\5\16\b\2IJ\5\16\b\2JK\7"+
		"\13\2\2KL\5\f\7\2LN\3\2\2\2MH\3\2\2\2MI\3\2\2\2N\r\3\2\2\2OP\7\f\2\2P"+
		"V\5\34\17\2QR\7\r\2\2RV\5\34\17\2ST\7\16\2\2TV\5\"\22\2UO\3\2\2\2UQ\3"+
		"\2\2\2US\3\2\2\2V\17\3\2\2\2WZ\5\24\13\2XZ\5\22\n\2YW\3\2\2\2YX\3\2\2"+
		"\2Z\21\3\2\2\2[\\\7\17\2\2\\]\5$\23\2]^\7\20\2\2^_\5\24\13\2_f\3\2\2\2"+
		"`a\7\21\2\2ab\5$\23\2bc\7\20\2\2cd\5\24\13\2df\3\2\2\2e[\3\2\2\2e`\3\2"+
		"\2\2f\23\3\2\2\2gm\7\22\2\2hm\7\23\2\2im\5\34\17\2jm\5\26\f\2km\5\30\r"+
		"\2lg\3\2\2\2lh\3\2\2\2li\3\2\2\2lj\3\2\2\2lk\3\2\2\2m\25\3\2\2\2no\7\24"+
		"\2\2os\5\24\13\2pq\7\25\2\2qs\5\24\13\2rn\3\2\2\2rp\3\2\2\2s\27\3\2\2"+
		"\2tu\7\t\2\2uv\5\24\13\2vw\7\n\2\2w}\3\2\2\2xy\7\t\2\2yz\5\32\16\2z{\7"+
		"\n\2\2{}\3\2\2\2|t\3\2\2\2|x\3\2\2\2}\31\3\2\2\2~\177\5\24\13\2\177\u0080"+
		"\7\26\2\2\u0080\u0081\5\24\13\2\u0081\u008f\3\2\2\2\u0082\u0083\5\24\13"+
		"\2\u0083\u0084\7\27\2\2\u0084\u0085\5\24\13\2\u0085\u008f\3\2\2\2\u0086"+
		"\u0087\5\24\13\2\u0087\u0088\7\30\2\2\u0088\u0089\5\24\13\2\u0089\u008f"+
		"\3\2\2\2\u008a\u008b\5\24\13\2\u008b\u008c\7\31\2\2\u008c\u008d\5\24\13"+
		"\2\u008d\u008f\3\2\2\2\u008e~\3\2\2\2\u008e\u0082\3\2\2\2\u008e\u0086"+
		"\3\2\2\2\u008e\u008a\3\2\2\2\u008f\33\3\2\2\2\u0090\u0097\5\"\22\2\u0091"+
		"\u0092\5\"\22\2\u0092\u0093\7\t\2\2\u0093\u0094\5\36\20\2\u0094\u0095"+
		"\7\n\2\2\u0095\u0097\3\2\2\2\u0096\u0090\3\2\2\2\u0096\u0091\3\2\2\2\u0097"+
		"\35\3\2\2\2\u0098\u009b\b\20\1\2\u0099\u009b\5 \21\2\u009a\u0098\3\2\2"+
		"\2\u009a\u0099\3\2\2\2\u009b\u00a1\3\2\2\2\u009c\u009d\f\3\2\2\u009d\u009e"+
		"\7\13\2\2\u009e\u00a0\5 \21\2\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\37\3\2\2\2\u00a3\u00a1"+
		"\3\2\2\2\u00a4\u00a9\5(\25\2\u00a5\u00a9\5&\24\2\u00a6\u00a9\5\34\17\2"+
		"\u00a7\u00a9\7\35\2\2\u00a8\u00a4\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a8\u00a6"+
		"\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9!\3\2\2\2\u00aa\u00ab\7\34\2\2\u00ab"+
		"#\3\2\2\2\u00ac\u00b2\5&\24\2\u00ad\u00ae\5&\24\2\u00ae\u00af\7\13\2\2"+
		"\u00af\u00b0\5$\23\2\u00b0\u00b2\3\2\2\2\u00b1\u00ac\3\2\2\2\u00b1\u00ad"+
		"\3\2\2\2\u00b2%\3\2\2\2\u00b3\u00b4\7\33\2\2\u00b4\'\3\2\2\2\u00b5\u00b6"+
		"\7\32\2\2\u00b6)\3\2\2\2\22\61\649MUYelr|\u008e\u0096\u009a\u00a1\u00a8"+
		"\u00b1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}