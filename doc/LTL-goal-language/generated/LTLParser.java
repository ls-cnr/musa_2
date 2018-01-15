// Generated from LTL.g4 by ANTLR 4.7.1

	package datalayer.awareness.LTL.target;
	import java.util.*;
	/** Class auto-generated using ANTLR */

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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, NOT=2, TRUE=3, FALSE=4, OP=5, CL=6, COM=7, G=8, F=9, X=10, U=11, 
		R=12, AND=13, OR=14, IMP=15, BIC=16, WS=17, NEWLINE=18;
	public static final int
		RULE_start = 0, RULE_folfor = 1, RULE_arg = 2, RULE_expr = 3, RULE_unaOper = 4, 
		RULE_binOper = 5;
	public static final String[] ruleNames = {
		"start", "folfor", "arg", "expr", "unaOper", "binOper"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'!'", "'true'", "'false'", "'('", "')'", "','", "'G'", "'F'", 
		"'X'", "'U'", "'R'", "'&&'", "'||'", "'->'", "'<->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "NOT", "TRUE", "FALSE", "OP", "CL", "COM", "G", "F", "X", 
		"U", "R", "AND", "OR", "IMP", "BIC", "WS", "NEWLINE"
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
		private HashMap<String, Stack<String>> dict = new HashMap<>();
		private Stack<String> tmp;	
			
		public Stack<String> getStack(){
			return stack;
		}
		
		public HashMap<String, Stack<String>> getDict() {
			return dict;
		}
		
		private void put( String s ) {
			if( tmp == null )
				tmp = new Stack<>();
			tmp.push(s);
		}


	public LTLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FolforContext folfor() {
			return getRuleContext(FolforContext.class,0);
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
			setState(14);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				folfor();
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

	public static class FolforContext extends ParserRuleContext {
		public Token VAR;
		public Token OP;
		public ArgContext arg;
		public Token CL;
		public TerminalNode VAR() { return getToken(LTLParser.VAR, 0); }
		public TerminalNode OP() { return getToken(LTLParser.OP, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode CL() { return getToken(LTLParser.CL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FolforContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_folfor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterFolfor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitFolfor(this);
		}
	}

	public final FolforContext folfor() throws RecognitionException {
		FolforContext _localctx = new FolforContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_folfor);
		try {
			setState(26);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				((FolforContext)_localctx).VAR = match(VAR);
				setState(17);
				((FolforContext)_localctx).OP = match(OP);
				setState(18);
				((FolforContext)_localctx).arg = arg();
				setState(19);
				((FolforContext)_localctx).CL = match(CL);
				 	String sTmp = (((FolforContext)_localctx).VAR!=null?((FolforContext)_localctx).VAR.getText():null) + (((FolforContext)_localctx).OP!=null?((FolforContext)_localctx).OP.getText():null) + (((FolforContext)_localctx).arg!=null?_input.getText(((FolforContext)_localctx).arg.start,((FolforContext)_localctx).arg.stop):null) + (((FolforContext)_localctx).CL!=null?((FolforContext)_localctx).CL.getText():null);
													stack.push(sTmp);
													put((((FolforContext)_localctx).VAR!=null?((FolforContext)_localctx).VAR.getText():null));
													dict.put(sTmp, tmp);
													tmp = null;
												
				}
				break;
			case OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				match(OP);
				setState(23);
				expr();
				setState(24);
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

	public static class ArgContext extends ParserRuleContext {
		public Token VAR;
		public TerminalNode VAR() { return getToken(LTLParser.VAR, 0); }
		public TerminalNode COM() { return getToken(LTLParser.COM, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LTLListener ) ((LTLListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arg);
		try {
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				((ArgContext)_localctx).VAR = match(VAR);
				setState(29);
				match(COM);
				setState(30);
				arg();
				  put((((ArgContext)_localctx).VAR!=null?((ArgContext)_localctx).VAR.getText():null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				((ArgContext)_localctx).VAR = match(VAR);
				  put((((ArgContext)_localctx).VAR!=null?((ArgContext)_localctx).VAR.getText():null)); 
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
		enterRule(_localctx, 6, RULE_expr);
		try {
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case G:
			case F:
			case X:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				unaOper();
				}
				break;
			case VAR:
			case OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
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
		public FolforContext folfor() {
			return getRuleContext(FolforContext.class,0);
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
		enterRule(_localctx, 8, RULE_unaOper);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(NOT);
				setState(42);
				folfor();
				 stack.push("NOT"); 
				}
				break;
			case F:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				match(F);
				setState(46);
				folfor();
				 stack.push("F"); 
				}
				break;
			case X:
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				match(X);
				setState(50);
				folfor();
				 stack.push("X"); 
				}
				break;
			case G:
				enterOuterAlt(_localctx, 4);
				{
				setState(53);
				match(G);
				setState(54);
				folfor();
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
		public List<FolforContext> folfor() {
			return getRuleContexts(FolforContext.class);
		}
		public FolforContext folfor(int i) {
			return getRuleContext(FolforContext.class,i);
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
		enterRule(_localctx, 10, RULE_binOper);
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				folfor();
				setState(60);
				match(AND);
				setState(61);
				folfor();
				 stack.push("AND"); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				folfor();
				setState(65);
				match(OR);
				setState(66);
				folfor();
				 stack.push("OR"); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
				folfor();
				setState(70);
				match(IMP);
				setState(71);
				folfor();
				 stack.push("IMP"); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				folfor();
				setState(75);
				match(BIC);
				setState(76);
				folfor();
				 stack.push("BIC"); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
				folfor();
				setState(80);
				match(U);
				setState(81);
				folfor();
				 stack.push("U"); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(84);
				folfor();
				setState(85);
				match(R);
				setState(86);
				folfor();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\5\2\21\n\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4&\n"+
		"\4\3\5\3\5\5\5*\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\5\6<\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7\\\n\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2c\2\20\3\2\2\2\4\34\3\2\2\2"+
		"\6%\3\2\2\2\b)\3\2\2\2\n;\3\2\2\2\f[\3\2\2\2\16\21\5\b\5\2\17\21\5\4\3"+
		"\2\20\16\3\2\2\2\20\17\3\2\2\2\21\3\3\2\2\2\22\23\7\3\2\2\23\24\7\7\2"+
		"\2\24\25\5\6\4\2\25\26\7\b\2\2\26\27\b\3\1\2\27\35\3\2\2\2\30\31\7\7\2"+
		"\2\31\32\5\b\5\2\32\33\7\b\2\2\33\35\3\2\2\2\34\22\3\2\2\2\34\30\3\2\2"+
		"\2\35\5\3\2\2\2\36\37\7\3\2\2\37 \7\t\2\2 !\5\6\4\2!\"\b\4\1\2\"&\3\2"+
		"\2\2#$\7\3\2\2$&\b\4\1\2%\36\3\2\2\2%#\3\2\2\2&\7\3\2\2\2\'*\5\n\6\2("+
		"*\5\f\7\2)\'\3\2\2\2)(\3\2\2\2*\t\3\2\2\2+,\7\4\2\2,-\5\4\3\2-.\b\6\1"+
		"\2.<\3\2\2\2/\60\7\13\2\2\60\61\5\4\3\2\61\62\b\6\1\2\62<\3\2\2\2\63\64"+
		"\7\f\2\2\64\65\5\4\3\2\65\66\b\6\1\2\66<\3\2\2\2\678\7\n\2\289\5\4\3\2"+
		"9:\b\6\1\2:<\3\2\2\2;+\3\2\2\2;/\3\2\2\2;\63\3\2\2\2;\67\3\2\2\2<\13\3"+
		"\2\2\2=>\5\4\3\2>?\7\17\2\2?@\5\4\3\2@A\b\7\1\2A\\\3\2\2\2BC\5\4\3\2C"+
		"D\7\20\2\2DE\5\4\3\2EF\b\7\1\2F\\\3\2\2\2GH\5\4\3\2HI\7\21\2\2IJ\5\4\3"+
		"\2JK\b\7\1\2K\\\3\2\2\2LM\5\4\3\2MN\7\22\2\2NO\5\4\3\2OP\b\7\1\2P\\\3"+
		"\2\2\2QR\5\4\3\2RS\7\r\2\2ST\5\4\3\2TU\b\7\1\2U\\\3\2\2\2VW\5\4\3\2WX"+
		"\7\16\2\2XY\5\4\3\2YZ\b\7\1\2Z\\\3\2\2\2[=\3\2\2\2[B\3\2\2\2[G\3\2\2\2"+
		"[L\3\2\2\2[Q\3\2\2\2[V\3\2\2\2\\\r\3\2\2\2\b\20\34%);[";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}