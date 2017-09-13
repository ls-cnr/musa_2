// Generated from LTL.g4 by ANTLR 4.7

	package layer.awareness.LTL.target;
	import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LTLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, NOT=2, TRUE=3, FALSE=4, OP=5, CL=6, G=7, F=8, X=9, U=10, R=11, 
		AND=12, OR=13, IMP=14, BIC=15, WS=16, NEWLINE=17;
	public static final int
		RULE_start = 0, RULE_atom = 1, RULE_expr = 2, RULE_unaOper = 3, RULE_binOper = 4;
	public static final String[] ruleNames = {
		"start", "atom", "expr", "unaOper", "binOper"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'!'", "'true'", "'false'", "'('", "')'", "'G'", "'F'", "'X'", 
		"'U'", "'R'", "'&&'", "'||'", "'->'", "'<->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "NOT", "TRUE", "FALSE", "OP", "CL", "G", "F", "X", "U", "R", 
		"AND", "OR", "IMP", "BIC", "WS", "NEWLINE"
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
	public String getGrammarFileName() { return "LTL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



		private Stack<String> stack = new Stack<>();
			
		public Stack<String> getStack(){
			return stack;
		}


	public LTLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(12);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				atom();
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

	public static class AtomContext extends ParserRuleContext {
		public Token VAR;
		public TerminalNode VAR() { return getToken(LTLParser.VAR, 0); }
		public TerminalNode TRUE() { return getToken(LTLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LTLParser.FALSE, 0); }
		public TerminalNode OP() { return getToken(LTLParser.OP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CL() { return getToken(LTLParser.CL, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_atom);
		try {
			setState(24);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				((AtomContext)_localctx).VAR = match(VAR);
				 stack.push((((AtomContext)_localctx).VAR!=null?((AtomContext)_localctx).VAR.getText():null)); 
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(16);
				match(TRUE);
				 stack.push("TRUE"); 
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(18);
				match(FALSE);
				 stack.push("FALSE"); 
				}
				break;
			case OP:
				enterOuterAlt(_localctx, 4);
				{
				setState(20);
				match(OP);
				setState(21);
				expr();
				setState(22);
				match(CL);
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

	public static class ExprContext extends ParserRuleContext {
		public UnaOperContext unaOper() {
			return getRuleContext(UnaOperContext.class,0);
		}
		public BinOperContext binOper() {
			return getRuleContext(BinOperContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case G:
			case F:
			case X:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				unaOper();
				}
				break;
			case VAR:
			case TRUE:
			case FALSE:
			case OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				binOper();
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

	public static class UnaOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(LTLParser.NOT, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode F() { return getToken(LTLParser.F, 0); }
		public TerminalNode X() { return getToken(LTLParser.X, 0); }
		public TerminalNode G() { return getToken(LTLParser.G, 0); }
		public UnaOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaOper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterUnaOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitUnaOper(this);
		}
	}

	public final UnaOperContext unaOper() throws RecognitionException {
		UnaOperContext _localctx = new UnaOperContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unaOper);
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(NOT);
				setState(31);
				atom();
				 stack.push("NOT"); 
				}
				break;
			case F:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(F);
				setState(35);
				atom();
				 stack.push("F"); 
				}
				break;
			case X:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				match(X);
				setState(39);
				atom();
				 stack.push("X"); 
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				match(G);
				setState(43);
				atom();
				 stack.push("G"); 
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

	public static class BinOperContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode AND() { return getToken(LTLParser.AND, 0); }
		public TerminalNode OR() { return getToken(LTLParser.OR, 0); }
		public TerminalNode IMP() { return getToken(LTLParser.IMP, 0); }
		public TerminalNode BIC() { return getToken(LTLParser.BIC, 0); }
		public TerminalNode U() { return getToken(LTLParser.U, 0); }
		public TerminalNode R() { return getToken(LTLParser.R, 0); }
		public BinOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binOper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterBinOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitBinOper(this);
		}
	}

	public final BinOperContext binOper() throws RecognitionException {
		BinOperContext _localctx = new BinOperContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_binOper);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				atom();
				setState(49);
				match(AND);
				setState(50);
				atom();
				 stack.push("AND"); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				atom();
				setState(54);
				match(OR);
				setState(55);
				atom();
				 stack.push("OR"); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				atom();
				setState(59);
				match(IMP);
				setState(60);
				atom();
				 stack.push("IMP"); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				atom();
				setState(64);
				match(BIC);
				setState(65);
				atom();
				 stack.push("BIC"); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				atom();
				setState(69);
				match(U);
				setState(70);
				atom();
				 stack.push("U"); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				atom();
				setState(74);
				match(R);
				setState(75);
				atom();
				 stack.push("R"); 
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23S\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\5\2\17\n\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3\33\n\3\3\4\3\4\5\4\37\n\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\61\n\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Q\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2Z\2"+
		"\16\3\2\2\2\4\32\3\2\2\2\6\36\3\2\2\2\b\60\3\2\2\2\nP\3\2\2\2\f\17\5\6"+
		"\4\2\r\17\5\4\3\2\16\f\3\2\2\2\16\r\3\2\2\2\17\3\3\2\2\2\20\21\7\3\2\2"+
		"\21\33\b\3\1\2\22\23\7\5\2\2\23\33\b\3\1\2\24\25\7\6\2\2\25\33\b\3\1\2"+
		"\26\27\7\7\2\2\27\30\5\6\4\2\30\31\7\b\2\2\31\33\3\2\2\2\32\20\3\2\2\2"+
		"\32\22\3\2\2\2\32\24\3\2\2\2\32\26\3\2\2\2\33\5\3\2\2\2\34\37\5\b\5\2"+
		"\35\37\5\n\6\2\36\34\3\2\2\2\36\35\3\2\2\2\37\7\3\2\2\2 !\7\4\2\2!\"\5"+
		"\4\3\2\"#\b\5\1\2#\61\3\2\2\2$%\7\n\2\2%&\5\4\3\2&\'\b\5\1\2\'\61\3\2"+
		"\2\2()\7\13\2\2)*\5\4\3\2*+\b\5\1\2+\61\3\2\2\2,-\7\t\2\2-.\5\4\3\2./"+
		"\b\5\1\2/\61\3\2\2\2\60 \3\2\2\2\60$\3\2\2\2\60(\3\2\2\2\60,\3\2\2\2\61"+
		"\t\3\2\2\2\62\63\5\4\3\2\63\64\7\16\2\2\64\65\5\4\3\2\65\66\b\6\1\2\66"+
		"Q\3\2\2\2\678\5\4\3\289\7\17\2\29:\5\4\3\2:;\b\6\1\2;Q\3\2\2\2<=\5\4\3"+
		"\2=>\7\20\2\2>?\5\4\3\2?@\b\6\1\2@Q\3\2\2\2AB\5\4\3\2BC\7\21\2\2CD\5\4"+
		"\3\2DE\b\6\1\2EQ\3\2\2\2FG\5\4\3\2GH\7\f\2\2HI\5\4\3\2IJ\b\6\1\2JQ\3\2"+
		"\2\2KL\5\4\3\2LM\7\r\2\2MN\5\4\3\2NO\b\6\1\2OQ\3\2\2\2P\62\3\2\2\2P\67"+
		"\3\2\2\2P<\3\2\2\2PA\3\2\2\2PF\3\2\2\2PK\3\2\2\2Q\13\3\2\2\2\7\16\32\36"+
		"\60P";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}