// Generated from D:/大三下/编译原理和设计/大作业/anltrcode/src/main\txjParse.g4 by ANTLR 4.9.1
package txjParse;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class txjParseLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IntegerConstant=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IntegerConstant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IntegerConstant"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public txjParseLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "txjParse.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\3=\b\1\4\2\t\2\3"+
		"\2\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2"+
		"\3\2\3\2\3\2\7\2\27\n\2\f\2\16\2\32\13\2\5\2\34\n\2\3\2\5\2\37\n\2\3\2"+
		"\3\2\3\2\3\2\5\2%\n\2\3\2\3\2\3\2\3\2\5\2+\n\2\3\2\5\2.\n\2\3\2\5\2\61"+
		"\n\2\3\2\5\2\64\n\2\3\2\5\2\67\n\2\3\2\5\2:\n\2\5\2<\n\2\2\2\3\3\3\3\2"+
		"\t\3\2\63;\3\2\62;\3\2\629\4\2ZZzz\5\2\62;CHch\4\2WWww\4\2NNnn\2N\2\3"+
		"\3\2\2\2\3\33\3\2\2\2\5\t\t\2\2\2\6\b\t\3\2\2\7\6\3\2\2\2\b\13\3\2\2\2"+
		"\t\7\3\2\2\2\t\n\3\2\2\2\n\34\3\2\2\2\13\t\3\2\2\2\f\20\7\62\2\2\r\17"+
		"\t\4\2\2\16\r\3\2\2\2\17\22\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\34"+
		"\3\2\2\2\22\20\3\2\2\2\23\24\7\62\2\2\24\30\t\5\2\2\25\27\t\6\2\2\26\25"+
		"\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\34\3\2\2\2\32\30"+
		"\3\2\2\2\33\5\3\2\2\2\33\f\3\2\2\2\33\23\3\2\2\2\34;\3\2\2\2\35\37\t\7"+
		"\2\2\36\35\3\2\2\2\36\37\3\2\2\2\37$\3\2\2\2 !\7n\2\2!%\7n\2\2\"#\7N\2"+
		"\2#%\7N\2\2$ \3\2\2\2$\"\3\2\2\2$%\3\2\2\2%<\3\2\2\2&\'\7n\2\2\'+\7n\2"+
		"\2()\7N\2\2)+\7N\2\2*&\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\t\7\2\2"+
		"-,\3\2\2\2-.\3\2\2\2.<\3\2\2\2/\61\t\7\2\2\60/\3\2\2\2\60\61\3\2\2\2\61"+
		"\63\3\2\2\2\62\64\t\b\2\2\63\62\3\2\2\2\63\64\3\2\2\2\64<\3\2\2\2\65\67"+
		"\t\b\2\2\66\65\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28:\t\7\2\298\3\2\2\29"+
		":\3\2\2\2:<\3\2\2\2;\36\3\2\2\2;*\3\2\2\2;\60\3\2\2\2;\66\3\2\2\2<\4\3"+
		"\2\2\2\20\2\t\20\30\33\36$*-\60\63\669;\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}