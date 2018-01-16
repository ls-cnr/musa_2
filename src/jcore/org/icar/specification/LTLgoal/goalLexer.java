// Generated from goal.g4 by ANTLR 4.7.1

package org.icar.specification.LTLgoal;
//package org.icar.specification.linear_temporal_logic.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class goalLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "GOALMODEL", "GOAL", "BEGINGOALMODEL", "ENDGOALMODEL", "BEGINGOAL", 
		"ENDGOAL", "FOREACH", "EXISTS", "SUCH", "TRUE", "FALSE", "GLOBALLY", "NEXT", 
		"FINALLY", "UNTIL", "RELEASE", "NOT", "AND", "OR", "IF", "IFF", "LB", 
		"RB", "COMMA", "LSB", "RSB", "LCLETTER", "UCLETTER", "DIGIT", "WS"
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


	public goalLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "goal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00a8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3 \6 \u00a3\n \r \16 \u00a4\3 \3 \2\2!\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!\3\2\6\4\2aac|\3\2C\\"+
		"\3\2\62;\5\2\13\f\17\17\"\"\2\u00a8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2"+
		"\2\2\5C\3\2\2\2\7M\3\2\2\2\tR\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2"+
		"\2\21Z\3\2\2\2\23b\3\2\2\2\25h\3\2\2\2\27j\3\2\2\2\31o\3\2\2\2\33u\3\2"+
		"\2\2\35w\3\2\2\2\37y\3\2\2\2!{\3\2\2\2#}\3\2\2\2%\177\3\2\2\2\'\u0083"+
		"\3\2\2\2)\u0087\3\2\2\2+\u008a\3\2\2\2-\u008d\3\2\2\2/\u0091\3\2\2\2\61"+
		"\u0093\3\2\2\2\63\u0095\3\2\2\2\65\u0097\3\2\2\2\67\u0099\3\2\2\29\u009b"+
		"\3\2\2\2;\u009d\3\2\2\2=\u009f\3\2\2\2?\u00a2\3\2\2\2AB\7)\2\2B\4\3\2"+
		"\2\2CD\7i\2\2DE\7q\2\2EF\7c\2\2FG\7n\2\2GH\7o\2\2HI\7q\2\2IJ\7f\2\2JK"+
		"\7g\2\2KL\7n\2\2L\6\3\2\2\2MN\7i\2\2NO\7q\2\2OP\7c\2\2PQ\7n\2\2Q\b\3\2"+
		"\2\2RS\7}\2\2S\n\3\2\2\2TU\7\177\2\2U\f\3\2\2\2VW\7?\2\2W\16\3\2\2\2X"+
		"Y\7\60\2\2Y\20\3\2\2\2Z[\7h\2\2[\\\7q\2\2\\]\7t\2\2]^\7g\2\2^_\7c\2\2"+
		"_`\7e\2\2`a\7j\2\2a\22\3\2\2\2bc\7g\2\2cd\7z\2\2de\7k\2\2ef\7u\2\2fg\7"+
		"v\2\2g\24\3\2\2\2hi\7<\2\2i\26\3\2\2\2jk\7v\2\2kl\7t\2\2lm\7w\2\2mn\7"+
		"g\2\2n\30\3\2\2\2op\7h\2\2pq\7c\2\2qr\7n\2\2rs\7u\2\2st\7g\2\2t\32\3\2"+
		"\2\2uv\7I\2\2v\34\3\2\2\2wx\7Z\2\2x\36\3\2\2\2yz\7H\2\2z \3\2\2\2{|\7"+
		"W\2\2|\"\3\2\2\2}~\7T\2\2~$\3\2\2\2\177\u0080\7p\2\2\u0080\u0081\7q\2"+
		"\2\u0081\u0082\7v\2\2\u0082&\3\2\2\2\u0083\u0084\7c\2\2\u0084\u0085\7"+
		"p\2\2\u0085\u0086\7f\2\2\u0086(\3\2\2\2\u0087\u0088\7q\2\2\u0088\u0089"+
		"\7t\2\2\u0089*\3\2\2\2\u008a\u008b\7/\2\2\u008b\u008c\7@\2\2\u008c,\3"+
		"\2\2\2\u008d\u008e\7>\2\2\u008e\u008f\7/\2\2\u008f\u0090\7@\2\2\u0090"+
		".\3\2\2\2\u0091\u0092\7*\2\2\u0092\60\3\2\2\2\u0093\u0094\7+\2\2\u0094"+
		"\62\3\2\2\2\u0095\u0096\7.\2\2\u0096\64\3\2\2\2\u0097\u0098\7]\2\2\u0098"+
		"\66\3\2\2\2\u0099\u009a\7_\2\2\u009a8\3\2\2\2\u009b\u009c\t\2\2\2\u009c"+
		":\3\2\2\2\u009d\u009e\t\3\2\2\u009e<\3\2\2\2\u009f\u00a0\t\4\2\2\u00a0"+
		">\3\2\2\2\u00a1\u00a3\t\5\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2"+
		"\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7"+
		"\b \2\2\u00a7@\3\2\2\2\4\2\u00a4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}