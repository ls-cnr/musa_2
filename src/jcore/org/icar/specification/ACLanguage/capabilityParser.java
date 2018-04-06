// Generated from capability.g4 by ANTLR 4.7.1

package org.icar.specification.ACLanguage;

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
public class capabilityParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, FLOAT=22, VARIABLEID=23, ATOMID=24, 
		STRING=25, ESC=26, LCLETTER=27, UCLETTER=28, DIGIT=29, COMMENT=30, WS=31;
	public static final int
		RULE_capability = 0, RULE_cap_body = 1, RULE_precondition = 2, RULE_postcondition = 3, 
		RULE_evolution = 4, RULE_evo_action_list = 5, RULE_action = 6, RULE_condition = 7, 
		RULE_quantified_predicate_formula = 8, RULE_predicate_formula = 9, RULE_neg_predicate = 10, 
		RULE_bracketed_predicate = 11, RULE_predicate = 12, RULE_termlist = 13, 
		RULE_term = 14, RULE_true_or_false = 15, RULE_constant_term = 16, RULE_functional_term = 17, 
		RULE_variable_term = 18, RULE_numeral_term = 19, RULE_string_term = 20, 
		RULE_atom = 21, RULE_var_list = 22;
	public static final String[] ruleNames = {
		"capability", "cap_body", "precondition", "postcondition", "evolution", 
		"evo_action_list", "action", "condition", "quantified_predicate_formula", 
		"predicate_formula", "neg_predicate", "bracketed_predicate", "predicate", 
		"termlist", "term", "true_or_false", "constant_term", "functional_term", 
		"variable_term", "numeral_term", "string_term", "atom", "var_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'capability'", "'{'", "'}'", "'pre:'", "'post:'", "'scenario'", 
		"'('", "')'", "','", "'add'", "'remove'", "'clear'", "'foreach'", "':'", 
		"'exists'", "'and'", "'or'", "'not'", "'!'", "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "FLOAT", "VARIABLEID", 
		"ATOMID", "STRING", "ESC", "LCLETTER", "UCLETTER", "DIGIT", "COMMENT", 
		"WS"
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
		public CapabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capability; }
	 
		public CapabilityContext() { }
		public void copyFrom(CapabilityContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyCapabilityContext extends CapabilityContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Cap_bodyContext cap_body() {
			return getRuleContext(Cap_bodyContext.class,0);
		}
		public MyCapabilityContext(CapabilityContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyCapability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CapabilityContext capability() throws RecognitionException {
		CapabilityContext _localctx = new CapabilityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_capability);
		try {
			_localctx = new MyCapabilityContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__0);
			setState(47);
			atom();
			setState(48);
			match(T__1);
			setState(49);
			cap_body();
			setState(50);
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
		public Cap_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cap_body; }
	 
		public Cap_bodyContext() { }
		public void copyFrom(Cap_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyCapBodyContext extends Cap_bodyContext {
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
		public MyCapBodyContext(Cap_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyCapBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cap_bodyContext cap_body() throws RecognitionException {
		Cap_bodyContext _localctx = new Cap_bodyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cap_body);
		int _la;
		try {
			_localctx = new MyCapBodyContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(52);
				precondition();
				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(55);
				postcondition();
				}
			}

			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(58);
				evolution();
				}
				}
				setState(63);
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
		public PreconditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precondition; }
	 
		public PreconditionContext() { }
		public void copyFrom(PreconditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyPreconditionContext extends PreconditionContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public MyPreconditionContext(PreconditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyPrecondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreconditionContext precondition() throws RecognitionException {
		PreconditionContext _localctx = new PreconditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_precondition);
		try {
			_localctx = new MyPreconditionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__3);
			setState(65);
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
		public PostconditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postcondition; }
	 
		public PostconditionContext() { }
		public void copyFrom(PostconditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyPostconditionContext extends PostconditionContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public MyPostconditionContext(PostconditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyPostcondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostconditionContext postcondition() throws RecognitionException {
		PostconditionContext _localctx = new PostconditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_postcondition);
		try {
			_localctx = new MyPostconditionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__4);
			setState(68);
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
		public EvolutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evolution; }
	 
		public EvolutionContext() { }
		public void copyFrom(EvolutionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyEvoScenarioContext extends EvolutionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Evo_action_listContext evo_action_list() {
			return getRuleContext(Evo_action_listContext.class,0);
		}
		public MyEvoScenarioContext(EvolutionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyEvoScenario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvolutionContext evolution() throws RecognitionException {
		EvolutionContext _localctx = new EvolutionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_evolution);
		try {
			_localctx = new MyEvoScenarioContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__5);
			setState(71);
			atom();
			setState(72);
			match(T__6);
			setState(73);
			evo_action_list();
			setState(74);
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
		public Evo_action_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evo_action_list; }
	 
		public Evo_action_listContext() { }
		public void copyFrom(Evo_action_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MySingleActionContext extends Evo_action_listContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public MySingleActionContext(Evo_action_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMySingleAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyMultiActionContext extends Evo_action_listContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public Evo_action_listContext evo_action_list() {
			return getRuleContext(Evo_action_listContext.class,0);
		}
		public MyMultiActionContext(Evo_action_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyMultiAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Evo_action_listContext evo_action_list() throws RecognitionException {
		Evo_action_listContext _localctx = new Evo_action_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_evo_action_list);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new MySingleActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				action();
				}
				break;
			case 2:
				_localctx = new MyMultiActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				action();
				setState(78);
				match(T__8);
				setState(79);
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
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
	 
		public ActionContext() { }
		public void copyFrom(ActionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyAddActionContext extends ActionContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public MyAddActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyAddAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyClearActionContext extends ActionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public MyClearActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyClearAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyRemoveActionContext extends ActionContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public MyRemoveActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyRemoveAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_action);
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				_localctx = new MyAddActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(T__9);
				setState(84);
				predicate();
				}
				break;
			case T__10:
				_localctx = new MyRemoveActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(T__10);
				setState(86);
				predicate();
				}
				break;
			case T__11:
				_localctx = new MyClearActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				match(T__11);
				setState(88);
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
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyQuantPredicateFormulaContext extends ConditionContext {
		public Quantified_predicate_formulaContext quantified_predicate_formula() {
			return getRuleContext(Quantified_predicate_formulaContext.class,0);
		}
		public MyQuantPredicateFormulaContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyQuantPredicateFormula(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyPredicateFormulaContext extends ConditionContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyPredicateFormulaContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyPredicateFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_condition);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__17:
			case T__18:
			case ATOMID:
				_localctx = new MyPredicateFormulaContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				predicate_formula(0);
				}
				break;
			case T__12:
			case T__14:
				_localctx = new MyQuantPredicateFormulaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
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
	public static class MyForAllPredicateContext extends Quantified_predicate_formulaContext {
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyForAllPredicateContext(Quantified_predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyForAllPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyExistsPredicateContext extends Quantified_predicate_formulaContext {
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyExistsPredicateContext(Quantified_predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyExistsPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantified_predicate_formulaContext quantified_predicate_formula() throws RecognitionException {
		Quantified_predicate_formulaContext _localctx = new Quantified_predicate_formulaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_quantified_predicate_formula);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				_localctx = new MyForAllPredicateContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(T__12);
				setState(96);
				var_list();
				setState(97);
				match(T__13);
				setState(98);
				predicate_formula(0);
				}
				break;
			case T__14:
				_localctx = new MyExistsPredicateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__14);
				setState(101);
				var_list();
				setState(102);
				match(T__13);
				setState(103);
				predicate_formula(0);
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
	public static class MyNegPredicateContext extends Predicate_formulaContext {
		public Neg_predicateContext neg_predicate() {
			return getRuleContext(Neg_predicateContext.class,0);
		}
		public MyNegPredicateContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyNegPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyCompPredicateContext extends Predicate_formulaContext {
		public Bracketed_predicateContext bracketed_predicate() {
			return getRuleContext(Bracketed_predicateContext.class,0);
		}
		public MyCompPredicateContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyCompPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyAtomPredicateContext extends Predicate_formulaContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public MyAtomPredicateContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyAtomPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyDisjunctionPredicateFormulaContext extends Predicate_formulaContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public MyDisjunctionPredicateFormulaContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyDisjunctionPredicateFormula(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyConjunctionPredicateFormulaContext extends Predicate_formulaContext {
		public List<Predicate_formulaContext> predicate_formula() {
			return getRuleContexts(Predicate_formulaContext.class);
		}
		public Predicate_formulaContext predicate_formula(int i) {
			return getRuleContext(Predicate_formulaContext.class,i);
		}
		public MyConjunctionPredicateFormulaContext(Predicate_formulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyConjunctionPredicateFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_formulaContext predicate_formula() throws RecognitionException {
		return predicate_formula(0);
	}

	private Predicate_formulaContext predicate_formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Predicate_formulaContext _localctx = new Predicate_formulaContext(_ctx, _parentState);
		Predicate_formulaContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_predicate_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOMID:
				{
				_localctx = new MyAtomPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(108);
				predicate();
				}
				break;
			case T__17:
			case T__18:
				{
				_localctx = new MyNegPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				neg_predicate();
				}
				break;
			case T__6:
				{
				_localctx = new MyCompPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				bracketed_predicate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MyConjunctionPredicateFormulaContext(new Predicate_formulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate_formula);
						setState(113);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(114);
						match(T__15);
						setState(115);
						predicate_formula(4);
						}
						break;
					case 2:
						{
						_localctx = new MyDisjunctionPredicateFormulaContext(new Predicate_formulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate_formula);
						setState(116);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(117);
						match(T__16);
						setState(118);
						predicate_formula(3);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
	public static class MyNotPred1Context extends Neg_predicateContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyNotPred1Context(Neg_predicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyNotPred1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyNotPred2Context extends Neg_predicateContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyNotPred2Context(Neg_predicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyNotPred2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Neg_predicateContext neg_predicate() throws RecognitionException {
		Neg_predicateContext _localctx = new Neg_predicateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_neg_predicate);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				_localctx = new MyNotPred1Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(T__17);
				setState(125);
				predicate_formula(0);
				}
				break;
			case T__18:
				_localctx = new MyNotPred2Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(T__18);
				setState(127);
				predicate_formula(0);
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
		public Bracketed_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracketed_predicate; }
	 
		public Bracketed_predicateContext() { }
		public void copyFrom(Bracketed_predicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyBracketedPredicateContext extends Bracketed_predicateContext {
		public Predicate_formulaContext predicate_formula() {
			return getRuleContext(Predicate_formulaContext.class,0);
		}
		public MyBracketedPredicateContext(Bracketed_predicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyBracketedPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bracketed_predicateContext bracketed_predicate() throws RecognitionException {
		Bracketed_predicateContext _localctx = new Bracketed_predicateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bracketed_predicate);
		try {
			_localctx = new MyBracketedPredicateContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__6);
			setState(131);
			predicate_formula(0);
			setState(132);
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

	public static class PredicateContext extends ParserRuleContext {
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	 
		public PredicateContext() { }
		public void copyFrom(PredicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyAtomContext extends PredicateContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public MyAtomContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyStructureContext extends PredicateContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public MyStructureContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_predicate);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new MyAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				atom();
				}
				break;
			case 2:
				_localctx = new MyStructureContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				atom();
				setState(136);
				match(T__6);
				setState(137);
				termlist(0);
				setState(138);
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
		public TermlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termlist; }
	 
		public TermlistContext() { }
		public void copyFrom(TermlistContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyEmptyTermContext extends TermlistContext {
		public MyEmptyTermContext(TermlistContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyEmptyTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyMultiTermContext extends TermlistContext {
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public MyMultiTermContext(TermlistContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyMultiTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MySimpleTermContext extends TermlistContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public MySimpleTermContext(TermlistContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMySimpleTerm(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_termlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new MyEmptyTermContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				}
				break;
			case 2:
				{
				_localctx = new MySimpleTermContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MyMultiTermContext(new TermlistContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_termlist);
					setState(146);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(147);
					match(T__8);
					setState(148);
					term();
					}
					} 
				}
				setState(153);
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
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyConstantTermContext extends TermContext {
		public Constant_termContext constant_term() {
			return getRuleContext(Constant_termContext.class,0);
		}
		public MyConstantTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyConstantTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyVariableTermContext extends TermContext {
		public Variable_termContext variable_term() {
			return getRuleContext(Variable_termContext.class,0);
		}
		public MyVariableTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyVariableTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyFunctionTermContext extends TermContext {
		public Functional_termContext functional_term() {
			return getRuleContext(Functional_termContext.class,0);
		}
		public MyFunctionTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyFunctionTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyNumericTermContext extends TermContext {
		public Numeral_termContext numeral_term() {
			return getRuleContext(Numeral_termContext.class,0);
		}
		public MyNumericTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyNumericTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyStringTermContext extends TermContext {
		public String_termContext string_term() {
			return getRuleContext(String_termContext.class,0);
		}
		public MyStringTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyStringTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyTrueFalseTermContext extends TermContext {
		public True_or_falseContext true_or_false() {
			return getRuleContext(True_or_falseContext.class,0);
		}
		public MyTrueFalseTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyTrueFalseTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_term);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new MyTrueFalseTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				true_or_false();
				}
				break;
			case 2:
				_localctx = new MyConstantTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				constant_term();
				}
				break;
			case 3:
				_localctx = new MyNumericTermContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				numeral_term();
				}
				break;
			case 4:
				_localctx = new MyVariableTermContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(157);
				variable_term();
				}
				break;
			case 5:
				_localctx = new MyFunctionTermContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(158);
				functional_term();
				}
				break;
			case 6:
				_localctx = new MyStringTermContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(159);
				string_term();
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

	public static class True_or_falseContext extends ParserRuleContext {
		public True_or_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_or_false; }
	 
		public True_or_falseContext() { }
		public void copyFrom(True_or_falseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyTrueConstantContext extends True_or_falseContext {
		public MyTrueConstantContext(True_or_falseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyTrueConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyFalseConstantContext extends True_or_falseContext {
		public MyFalseConstantContext(True_or_falseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyFalseConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_or_falseContext true_or_false() throws RecognitionException {
		True_or_falseContext _localctx = new True_or_falseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_true_or_false);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				_localctx = new MyTrueConstantContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(T__19);
				}
				break;
			case T__20:
				_localctx = new MyFalseConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(T__20);
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

	public static class Constant_termContext extends ParserRuleContext {
		public Constant_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_term; }
	 
		public Constant_termContext() { }
		public void copyFrom(Constant_termContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyAtomConstantTermContext extends Constant_termContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public MyAtomConstantTermContext(Constant_termContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyAtomConstantTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_termContext constant_term() throws RecognitionException {
		Constant_termContext _localctx = new Constant_termContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_constant_term);
		try {
			_localctx = new MyAtomConstantTermContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			atom();
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

	public static class Functional_termContext extends ParserRuleContext {
		public Functional_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_term; }
	 
		public Functional_termContext() { }
		public void copyFrom(Functional_termContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyAtomFunctionalTermContext extends Functional_termContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public MyAtomFunctionalTermContext(Functional_termContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyAtomFunctionalTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyStructureFunctionalTermContext extends Functional_termContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TermlistContext termlist() {
			return getRuleContext(TermlistContext.class,0);
		}
		public MyStructureFunctionalTermContext(Functional_termContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyStructureFunctionalTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_termContext functional_term() throws RecognitionException {
		Functional_termContext _localctx = new Functional_termContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functional_term);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new MyAtomFunctionalTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				atom();
				}
				break;
			case 2:
				_localctx = new MyStructureFunctionalTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				atom();
				setState(170);
				match(T__6);
				setState(171);
				termlist(0);
				setState(172);
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

	public static class Variable_termContext extends ParserRuleContext {
		public TerminalNode VARIABLEID() { return getToken(capabilityParser.VARIABLEID, 0); }
		public Variable_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitVariable_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_termContext variable_term() throws RecognitionException {
		Variable_termContext _localctx = new Variable_termContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variable_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
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

	public static class Numeral_termContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(capabilityParser.FLOAT, 0); }
		public Numeral_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitNumeral_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeral_termContext numeral_term() throws RecognitionException {
		Numeral_termContext _localctx = new Numeral_termContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_numeral_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
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

	public static class String_termContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(capabilityParser.STRING, 0); }
		public String_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitString_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_termContext string_term() throws RecognitionException {
		String_termContext _localctx = new String_termContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_string_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(STRING);
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
		enterRule(_localctx, 42, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
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
		public Var_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_list; }
	 
		public Var_listContext() { }
		public void copyFrom(Var_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MyMultiVariableContext extends Var_listContext {
		public Variable_termContext variable_term() {
			return getRuleContext(Variable_termContext.class,0);
		}
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public MyMultiVariableContext(Var_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMyMultiVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MySingleVariableContext extends Var_listContext {
		public Variable_termContext variable_term() {
			return getRuleContext(Variable_termContext.class,0);
		}
		public MySingleVariableContext(Var_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof capabilityVisitor ) return ((capabilityVisitor<? extends T>)visitor).visitMySingleVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_listContext var_list() throws RecognitionException {
		Var_listContext _localctx = new Var_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_var_list);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new MySingleVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				variable_term();
				}
				break;
			case 2:
				_localctx = new MyMultiVariableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				variable_term();
				setState(186);
				match(T__8);
				setState(187);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return predicate_formula_sempred((Predicate_formulaContext)_localctx, predIndex);
		case 13:
			return termlist_sempred((TermlistContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean predicate_formula_sempred(Predicate_formulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean termlist_sempred(TermlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00c2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\5\38\n\3\3\3\5\3;\n\3\3\3\7\3>\n\3\f\3\16\3A\13\3\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7"+
		"T\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\\\n\b\3\t\3\t\5\t`\n\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nl\n\n\3\13\3\13\3\13\3\13\5\13r\n\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13z\n\13\f\13\16\13}\13\13\3\f\3\f\3\f\3\f"+
		"\5\f\u0083\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008f"+
		"\n\16\3\17\3\17\5\17\u0093\n\17\3\17\3\17\3\17\7\17\u0098\n\17\f\17\16"+
		"\17\u009b\13\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a3\n\20\3\21\3\21"+
		"\5\21\u00a7\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00b1\n"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\5"+
		"\30\u00c0\n\30\3\30\2\4\24\34\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\2\2\2\u00c2\2\60\3\2\2\2\4\67\3\2\2\2\6B\3\2\2\2\bE\3\2\2\2"+
		"\nH\3\2\2\2\fS\3\2\2\2\16[\3\2\2\2\20_\3\2\2\2\22k\3\2\2\2\24q\3\2\2\2"+
		"\26\u0082\3\2\2\2\30\u0084\3\2\2\2\32\u008e\3\2\2\2\34\u0092\3\2\2\2\36"+
		"\u00a2\3\2\2\2 \u00a6\3\2\2\2\"\u00a8\3\2\2\2$\u00b0\3\2\2\2&\u00b2\3"+
		"\2\2\2(\u00b4\3\2\2\2*\u00b6\3\2\2\2,\u00b8\3\2\2\2.\u00bf\3\2\2\2\60"+
		"\61\7\3\2\2\61\62\5,\27\2\62\63\7\4\2\2\63\64\5\4\3\2\64\65\7\5\2\2\65"+
		"\3\3\2\2\2\668\5\6\4\2\67\66\3\2\2\2\678\3\2\2\28:\3\2\2\29;\5\b\5\2:"+
		"9\3\2\2\2:;\3\2\2\2;?\3\2\2\2<>\5\n\6\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2"+
		"?@\3\2\2\2@\5\3\2\2\2A?\3\2\2\2BC\7\6\2\2CD\5\20\t\2D\7\3\2\2\2EF\7\7"+
		"\2\2FG\5\20\t\2G\t\3\2\2\2HI\7\b\2\2IJ\5,\27\2JK\7\t\2\2KL\5\f\7\2LM\7"+
		"\n\2\2M\13\3\2\2\2NT\5\16\b\2OP\5\16\b\2PQ\7\13\2\2QR\5\f\7\2RT\3\2\2"+
		"\2SN\3\2\2\2SO\3\2\2\2T\r\3\2\2\2UV\7\f\2\2V\\\5\32\16\2WX\7\r\2\2X\\"+
		"\5\32\16\2YZ\7\16\2\2Z\\\5,\27\2[U\3\2\2\2[W\3\2\2\2[Y\3\2\2\2\\\17\3"+
		"\2\2\2]`\5\24\13\2^`\5\22\n\2_]\3\2\2\2_^\3\2\2\2`\21\3\2\2\2ab\7\17\2"+
		"\2bc\5.\30\2cd\7\20\2\2de\5\24\13\2el\3\2\2\2fg\7\21\2\2gh\5.\30\2hi\7"+
		"\20\2\2ij\5\24\13\2jl\3\2\2\2ka\3\2\2\2kf\3\2\2\2l\23\3\2\2\2mn\b\13\1"+
		"\2nr\5\32\16\2or\5\26\f\2pr\5\30\r\2qm\3\2\2\2qo\3\2\2\2qp\3\2\2\2r{\3"+
		"\2\2\2st\f\5\2\2tu\7\22\2\2uz\5\24\13\6vw\f\4\2\2wx\7\23\2\2xz\5\24\13"+
		"\5ys\3\2\2\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\25\3\2\2\2}{\3\2"+
		"\2\2~\177\7\24\2\2\177\u0083\5\24\13\2\u0080\u0081\7\25\2\2\u0081\u0083"+
		"\5\24\13\2\u0082~\3\2\2\2\u0082\u0080\3\2\2\2\u0083\27\3\2\2\2\u0084\u0085"+
		"\7\t\2\2\u0085\u0086\5\24\13\2\u0086\u0087\7\n\2\2\u0087\31\3\2\2\2\u0088"+
		"\u008f\5,\27\2\u0089\u008a\5,\27\2\u008a\u008b\7\t\2\2\u008b\u008c\5\34"+
		"\17\2\u008c\u008d\7\n\2\2\u008d\u008f\3\2\2\2\u008e\u0088\3\2\2\2\u008e"+
		"\u0089\3\2\2\2\u008f\33\3\2\2\2\u0090\u0093\b\17\1\2\u0091\u0093\5\36"+
		"\20\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0099\3\2\2\2\u0094"+
		"\u0095\f\3\2\2\u0095\u0096\7\13\2\2\u0096\u0098\5\36\20\2\u0097\u0094"+
		"\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\35\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u00a3\5 \21\2\u009d\u00a3\5\"\22"+
		"\2\u009e\u00a3\5(\25\2\u009f\u00a3\5&\24\2\u00a0\u00a3\5$\23\2\u00a1\u00a3"+
		"\5*\26\2\u00a2\u009c\3\2\2\2\u00a2\u009d\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2"+
		"\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\37\3\2\2"+
		"\2\u00a4\u00a7\7\26\2\2\u00a5\u00a7\7\27\2\2\u00a6\u00a4\3\2\2\2\u00a6"+
		"\u00a5\3\2\2\2\u00a7!\3\2\2\2\u00a8\u00a9\5,\27\2\u00a9#\3\2\2\2\u00aa"+
		"\u00b1\5,\27\2\u00ab\u00ac\5,\27\2\u00ac\u00ad\7\t\2\2\u00ad\u00ae\5\34"+
		"\17\2\u00ae\u00af\7\n\2\2\u00af\u00b1\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0"+
		"\u00ab\3\2\2\2\u00b1%\3\2\2\2\u00b2\u00b3\7\31\2\2\u00b3\'\3\2\2\2\u00b4"+
		"\u00b5\7\30\2\2\u00b5)\3\2\2\2\u00b6\u00b7\7\33\2\2\u00b7+\3\2\2\2\u00b8"+
		"\u00b9\7\32\2\2\u00b9-\3\2\2\2\u00ba\u00c0\5&\24\2\u00bb\u00bc\5&\24\2"+
		"\u00bc\u00bd\7\13\2\2\u00bd\u00be\5.\30\2\u00be\u00c0\3\2\2\2\u00bf\u00ba"+
		"\3\2\2\2\u00bf\u00bb\3\2\2\2\u00c0/\3\2\2\2\24\67:?S[_kqy{\u0082\u008e"+
		"\u0092\u0099\u00a2\u00a6\u00b0\u00bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}