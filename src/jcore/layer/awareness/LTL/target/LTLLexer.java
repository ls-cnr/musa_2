// Generated from LTL.g4 by ANTLR 4.7

	package layer.awareness.LTL.target;
	import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LTLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, NOT=2, TRUE=3, FALSE=4, OP=5, CL=6, G=7, F=8, X=9, U=10, R=11, 
		AND=12, OR=13, IMP=14, BIC=15, WS=16, NEWLINE=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VAR", "NOT", "TRUE", "FALSE", "OP", "CL", "G", "F", "X", "U", "R", "AND", 
		"OR", "IMP", "BIC", "WS", "NEWLINE"
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


	public LTLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LTL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23`\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\7\2(\n\2\f\2\16\2+\13\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21"+
		"\6\21V\n\21\r\21\16\21W\3\21\3\21\3\22\5\22]\n\22\3\22\3\22\2\2\23\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23\3\2\5\3\2c|\4\2C\\c|\5\2\13\13\17\17\"\"\2b\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5,\3"+
		"\2\2\2\7.\3\2\2\2\t\63\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?"+
		"\3\2\2\2\23A\3\2\2\2\25C\3\2\2\2\27E\3\2\2\2\31G\3\2\2\2\33J\3\2\2\2\35"+
		"M\3\2\2\2\37P\3\2\2\2!U\3\2\2\2#\\\3\2\2\2%)\t\2\2\2&(\t\3\2\2\'&\3\2"+
		"\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\4\3\2\2\2+)\3\2\2\2,-\7#\2\2-\6\3"+
		"\2\2\2./\7v\2\2/\60\7t\2\2\60\61\7w\2\2\61\62\7g\2\2\62\b\3\2\2\2\63\64"+
		"\7h\2\2\64\65\7c\2\2\65\66\7n\2\2\66\67\7u\2\2\678\7g\2\28\n\3\2\2\29"+
		":\7*\2\2:\f\3\2\2\2;<\7+\2\2<\16\3\2\2\2=>\7I\2\2>\20\3\2\2\2?@\7H\2\2"+
		"@\22\3\2\2\2AB\7Z\2\2B\24\3\2\2\2CD\7W\2\2D\26\3\2\2\2EF\7T\2\2F\30\3"+
		"\2\2\2GH\7(\2\2HI\7(\2\2I\32\3\2\2\2JK\7~\2\2KL\7~\2\2L\34\3\2\2\2MN\7"+
		"/\2\2NO\7@\2\2O\36\3\2\2\2PQ\7>\2\2QR\7/\2\2RS\7@\2\2S \3\2\2\2TV\t\4"+
		"\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\b\21\2\2Z\"\3"+
		"\2\2\2[]\7\17\2\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\7\f\2\2_$\3\2\2\2"+
		"\6\2)W\\\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}