// Generated from capability.g4 by ANTLR 4.7.1

package org.icar.specification.ACLanguage;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class capabilityLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, FLOAT=22, VARIABLEID=23, ATOMID=24, 
		STRING=25, ESC=26, LCLETTER=27, UCLETTER=28, DIGIT=29, COMMENT=30, WS=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "FLOAT", "VARIABLEID", "ATOMID", "STRING", 
		"ESC", "LCLETTER", "UCLETTER", "DIGIT", "COMMENT", "WS"
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


	public capabilityLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "capability.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\6\27\u00a6\n\27\r\27\16\27\u00a7\3\27\3\27\7\27"+
		"\u00ac\n\27\f\27\16\27\u00af\13\27\3\27\3\27\6\27\u00b3\n\27\r\27\16\27"+
		"\u00b4\5\27\u00b7\n\27\3\30\3\30\3\30\3\30\7\30\u00bd\n\30\f\30\16\30"+
		"\u00c0\13\30\3\31\3\31\3\31\3\31\7\31\u00c6\n\31\f\31\16\31\u00c9\13\31"+
		"\3\32\3\32\3\32\7\32\u00ce\n\32\f\32\16\32\u00d1\13\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\5\33\u00d9\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\7\37\u00e5\n\37\f\37\16\37\u00e8\13\37\3\37\3\37\3\37\3\37"+
		"\3\37\3 \6 \u00f0\n \r \16 \u00f1\3 \3 \4\u00cf\u00e6\2!\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!\3\2\6\4"+
		"\2aac|\3\2C\\\3\2\62;\5\2\13\f\17\17\"\"\2\u0103\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\3A\3\2\2\2\5L\3\2\2\2\7N\3\2\2\2\tP\3\2\2\2\13U\3\2\2\2\r[\3\2"+
		"\2\2\17d\3\2\2\2\21f\3\2\2\2\23h\3\2\2\2\25j\3\2\2\2\27n\3\2\2\2\31u\3"+
		"\2\2\2\33{\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2\2\2!\u008c\3\2\2\2#\u0090"+
		"\3\2\2\2%\u0093\3\2\2\2\'\u0097\3\2\2\2)\u0099\3\2\2\2+\u009e\3\2\2\2"+
		"-\u00b6\3\2\2\2/\u00b8\3\2\2\2\61\u00c1\3\2\2\2\63\u00ca\3\2\2\2\65\u00d8"+
		"\3\2\2\2\67\u00da\3\2\2\29\u00dc\3\2\2\2;\u00de\3\2\2\2=\u00e0\3\2\2\2"+
		"?\u00ef\3\2\2\2AB\7e\2\2BC\7c\2\2CD\7r\2\2DE\7c\2\2EF\7d\2\2FG\7k\2\2"+
		"GH\7n\2\2HI\7k\2\2IJ\7v\2\2JK\7{\2\2K\4\3\2\2\2LM\7}\2\2M\6\3\2\2\2NO"+
		"\7\177\2\2O\b\3\2\2\2PQ\7r\2\2QR\7t\2\2RS\7g\2\2ST\7<\2\2T\n\3\2\2\2U"+
		"V\7r\2\2VW\7q\2\2WX\7u\2\2XY\7v\2\2YZ\7<\2\2Z\f\3\2\2\2[\\\7u\2\2\\]\7"+
		"e\2\2]^\7g\2\2^_\7p\2\2_`\7c\2\2`a\7t\2\2ab\7k\2\2bc\7q\2\2c\16\3\2\2"+
		"\2de\7*\2\2e\20\3\2\2\2fg\7+\2\2g\22\3\2\2\2hi\7.\2\2i\24\3\2\2\2jk\7"+
		"c\2\2kl\7f\2\2lm\7f\2\2m\26\3\2\2\2no\7t\2\2op\7g\2\2pq\7o\2\2qr\7q\2"+
		"\2rs\7x\2\2st\7g\2\2t\30\3\2\2\2uv\7e\2\2vw\7n\2\2wx\7g\2\2xy\7c\2\2y"+
		"z\7t\2\2z\32\3\2\2\2{|\7h\2\2|}\7q\2\2}~\7t\2\2~\177\7g\2\2\177\u0080"+
		"\7c\2\2\u0080\u0081\7e\2\2\u0081\u0082\7j\2\2\u0082\34\3\2\2\2\u0083\u0084"+
		"\7<\2\2\u0084\36\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7z\2\2\u0087\u0088"+
		"\7k\2\2\u0088\u0089\7u\2\2\u0089\u008a\7v\2\2\u008a\u008b\7u\2\2\u008b"+
		" \3\2\2\2\u008c\u008d\7c\2\2\u008d\u008e\7p\2\2\u008e\u008f\7f\2\2\u008f"+
		"\"\3\2\2\2\u0090\u0091\7q\2\2\u0091\u0092\7t\2\2\u0092$\3\2\2\2\u0093"+
		"\u0094\7p\2\2\u0094\u0095\7q\2\2\u0095\u0096\7v\2\2\u0096&\3\2\2\2\u0097"+
		"\u0098\7#\2\2\u0098(\3\2\2\2\u0099\u009a\7v\2\2\u009a\u009b\7t\2\2\u009b"+
		"\u009c\7w\2\2\u009c\u009d\7g\2\2\u009d*\3\2\2\2\u009e\u009f\7h\2\2\u009f"+
		"\u00a0\7c\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2\7u\2\2\u00a2\u00a3\7g\2\2"+
		"\u00a3,\3\2\2\2\u00a4\u00a6\5;\36\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ad\7\60\2\2\u00aa\u00ac\5;\36\2\u00ab\u00aa\3\2\2\2\u00ac\u00af\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b7\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b2\7\60\2\2\u00b1\u00b3\5;\36\2\u00b2\u00b1\3"+
		"\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00a5\3\2\2\2\u00b6\u00b0\3\2\2\2\u00b7.\3\2\2\2"+
		"\u00b8\u00be\59\35\2\u00b9\u00bd\5\67\34\2\u00ba\u00bd\59\35\2\u00bb\u00bd"+
		"\5;\36\2\u00bc\u00b9\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd"+
		"\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\60\3\2\2"+
		"\2\u00c0\u00be\3\2\2\2\u00c1\u00c7\5\67\34\2\u00c2\u00c6\5\67\34\2\u00c3"+
		"\u00c6\59\35\2\u00c4\u00c6\5;\36\2\u00c5\u00c2\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\62\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cf\7$\2\2"+
		"\u00cb\u00ce\5\65\33\2\u00cc\u00ce\13\2\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00d0\3\2\2\2\u00cf\u00cd\3\2"+
		"\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\7$\2\2\u00d3"+
		"\64\3\2\2\2\u00d4\u00d5\7^\2\2\u00d5\u00d9\7$\2\2\u00d6\u00d7\7^\2\2\u00d7"+
		"\u00d9\7^\2\2\u00d8\u00d4\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\66\3\2\2\2"+
		"\u00da\u00db\t\2\2\2\u00db8\3\2\2\2\u00dc\u00dd\t\3\2\2\u00dd:\3\2\2\2"+
		"\u00de\u00df\t\4\2\2\u00df<\3\2\2\2\u00e0\u00e1\7\61\2\2\u00e1\u00e2\7"+
		",\2\2\u00e2\u00e6\3\2\2\2\u00e3\u00e5\13\2\2\2\u00e4\u00e3\3\2\2\2\u00e5"+
		"\u00e8\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e9\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7,\2\2\u00ea\u00eb\7\61\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\b\37\2\2\u00ed>\3\2\2\2\u00ee\u00f0\t\5\2\2"+
		"\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2"+
		"\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\b \2\2\u00f4@\3\2\2\2\20\2\u00a7"+
		"\u00ad\u00b4\u00b6\u00bc\u00be\u00c5\u00c7\u00cd\u00cf\u00d8\u00e6\u00f1"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}