// Generated from D:/大三下/编译原理和设计/大作业/ex5/BIT-MiniCC/src/bit/minisys/minicc\txj_parse.g4 by ANTLR 4.9.1
package bit.minisys.minicc.parser.txjparser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class txj_parseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, WS=41, BlockComment=42, LineComment=43, Unary_operator=44, 
		Assignment_operator=45, Type=46, IntegerConstant=47, CharConstant=48, 
		FloatConstant=49, StringLiteral=50, Identifier=51;
	public static final int
		RULE_program = 0, RULE_translationUnit = 1, RULE_externalDeclaration = 2, 
		RULE_expr = 3, RULE_typename = 4, RULE_specifiers = 5, RULE_functionCall = 6, 
		RULE_argList = 7, RULE_stat = 8, RULE_breakStatement = 9, RULE_continueStatement = 10, 
		RULE_gotoStatement = 11, RULE_returnStatement = 12, RULE_labeledStatement = 13, 
		RULE_compoundStatement = 14, RULE_blockitems = 15, RULE_blockitem = 16, 
		RULE_expressionStatement = 17, RULE_exprs = 18, RULE_selectionStatement = 19, 
		RULE_iterationStatement = 20, RULE_iterationDecStatement = 21, RULE_declarator = 22, 
		RULE_initList = 23, RULE_initLists = 24, RULE_declaration = 25, RULE_functionDefine = 26, 
		RULE_arguments = 27, RULE_fundeflist = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "translationUnit", "externalDeclaration", "expr", "typename", 
			"specifiers", "functionCall", "argList", "stat", "breakStatement", "continueStatement", 
			"gotoStatement", "returnStatement", "labeledStatement", "compoundStatement", 
			"blockitems", "blockitem", "expressionStatement", "exprs", "selectionStatement", 
			"iterationStatement", "iterationDecStatement", "declarator", "initList", 
			"initLists", "declaration", "functionDefine", "arguments", "fundeflist"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'++'", "'--'", "'['", "']'", "'sizeof'", "'('", "')'", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'<<'", "'>>'", "'>'", "'<'", "'<='", 
			"'>='", "'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "':'", 
			"','", "'break'", "'continue'", "'goto'", "'return'", "'{'", "'}'", "'if'", 
			"'else'", "'while'", "'do\"'", "'for'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "WS", "BlockComment", "LineComment", "Unary_operator", 
			"Assignment_operator", "Type", "IntegerConstant", "CharConstant", "FloatConstant", 
			"StringLiteral", "Identifier"
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

	@Override
	public String getGrammarFileName() { return "txj_parse.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public txj_parseParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(txj_parseParser.EOF, 0); }
		public TranslationUnitContext translationUnit() {
			return getRuleContext(TranslationUnitContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Type) {
				{
				setState(58);
				translationUnit();
				}
			}

			setState(61);
			match(EOF);
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

	public static class TranslationUnitContext extends ParserRuleContext {
		public List<ExternalDeclarationContext> externalDeclaration() {
			return getRuleContexts(ExternalDeclarationContext.class);
		}
		public ExternalDeclarationContext externalDeclaration(int i) {
			return getRuleContext(ExternalDeclarationContext.class,i);
		}
		public TranslationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterTranslationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitTranslationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitTranslationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TranslationUnitContext translationUnit() throws RecognitionException {
		TranslationUnitContext _localctx = new TranslationUnitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_translationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				externalDeclaration();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Type );
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

	public static class ExternalDeclarationContext extends ParserRuleContext {
		public FunctionDefineContext functionDefine() {
			return getRuleContext(FunctionDefineContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExternalDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterExternalDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitExternalDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitExternalDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternalDeclarationContext externalDeclaration() throws RecognitionException {
		ExternalDeclarationContext _localctx = new ExternalDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_externalDeclaration);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				functionDefine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				declaration();
				setState(70);
				match(T__0);
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
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Unary_expressionContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Unary_operator() { return getToken(txj_parseParser.Unary_operator, 0); }
		public Unary_expressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterUnary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitUnary_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitUnary_expression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatConstantContext extends ExprContext {
		public TerminalNode FloatConstant() { return getToken(txj_parseParser.FloatConstant, 0); }
		public FloatConstantContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterFloatConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitFloatConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitFloatConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharConstantContext extends ExprContext {
		public TerminalNode CharConstant() { return getToken(txj_parseParser.CharConstant, 0); }
		public CharConstantContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterCharConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitCharConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitCharConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Binary_expressionContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Assignment_operator() { return getToken(txj_parseParser.Assignment_operator, 0); }
		public Binary_expressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterBinary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitBinary_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitBinary_expression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerConstantContext extends ExprContext {
		public TerminalNode IntegerConstant() { return getToken(txj_parseParser.IntegerConstant, 0); }
		public IntegerConstantContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterIntegerConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitIntegerConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitIntegerConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MyExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterMyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitMyExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitMyExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Postfix_expressionContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Postfix_expressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterPostfix_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitPostfix_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitPostfix_expression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Array_accessContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public Array_accessContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterArray_access(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitArray_access(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitArray_access(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Cast_expressionContext extends ExprContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Cast_expressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterCast_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitCast_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitCast_expression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MyFunctionCallContext extends ExprContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public MyFunctionCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterMyFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitMyFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitMyFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierContext extends ExprContext {
		public TerminalNode Identifier() { return getToken(txj_parseParser.Identifier, 0); }
		public IdentifierContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExprContext {
		public TerminalNode StringLiteral() { return getToken(txj_parseParser.StringLiteral, 0); }
		public StringLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Unary_typenameContext extends ExprContext {
		public Token op;
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public Unary_typenameContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterUnary_typename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitUnary_typename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitUnary_typename(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Conditional_expressionContext extends ExprContext {
		public ExprsContext trueExpr;
		public ExprContext falseExpr;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public Conditional_expressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterConditional_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitConditional_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitConditional_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new Unary_expressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(75);
				((Unary_expressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << Unary_operator))) != 0)) ) {
					((Unary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(76);
				expr(22);
				}
				break;
			case 2:
				{
				_localctx = new Unary_typenameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				((Unary_typenameContext)_localctx).op = match(T__5);
				setState(78);
				match(T__6);
				setState(79);
				typename();
				setState(80);
				match(T__7);
				}
				break;
			case 3:
				{
				_localctx = new Cast_expressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(T__6);
				setState(83);
				typename();
				setState(84);
				match(T__7);
				setState(85);
				expr(20);
				}
				break;
			case 4:
				{
				_localctx = new MyFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				functionCall();
				}
				break;
			case 5:
				{
				_localctx = new IntegerConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(IntegerConstant);
				}
				break;
			case 6:
				{
				_localctx = new CharConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(89);
				match(CharConstant);
				}
				break;
			case 7:
				{
				_localctx = new FloatConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(FloatConstant);
				}
				break;
			case 8:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(91);
				match(Identifier);
				}
				break;
			case 9:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(StringLiteral);
				}
				break;
			case 10:
				{
				_localctx = new MyExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				match(T__6);
				setState(94);
				expr(0);
				setState(95);
				match(T__7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(145);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(99);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(100);
						((Binary_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
							((Binary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(101);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(102);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(103);
						((Binary_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((Binary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(104);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(105);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(106);
						((Binary_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
							((Binary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(107);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(108);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(109);
						((Binary_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((Binary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(110);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(111);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(112);
						((Binary_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((Binary_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(113);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(114);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(115);
						((Binary_expressionContext)_localctx).op = match(T__21);
						setState(116);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(117);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(118);
						((Binary_expressionContext)_localctx).op = match(T__22);
						setState(119);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(120);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(121);
						((Binary_expressionContext)_localctx).op = match(T__23);
						setState(122);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(123);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(124);
						((Binary_expressionContext)_localctx).op = match(T__24);
						setState(125);
						expr(12);
						}
						break;
					case 10:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(126);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(127);
						((Binary_expressionContext)_localctx).op = match(T__25);
						setState(128);
						expr(11);
						}
						break;
					case 11:
						{
						_localctx = new Conditional_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(129);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(130);
						match(T__26);
						setState(131);
						((Conditional_expressionContext)_localctx).trueExpr = exprs();
						setState(132);
						match(T__27);
						setState(133);
						((Conditional_expressionContext)_localctx).falseExpr = expr(10);
						}
						break;
					case 12:
						{
						_localctx = new Binary_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(135);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(136);
						((Binary_expressionContext)_localctx).op = match(Assignment_operator);
						setState(137);
						expr(9);
						}
						break;
					case 13:
						{
						_localctx = new Postfix_expressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(138);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(139);
						((Postfix_expressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__2) ) {
							((Postfix_expressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 14:
						{
						_localctx = new Array_accessContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(140);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(141);
						match(T__3);
						setState(142);
						exprs();
						setState(143);
						match(T__4);
						}
						break;
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class TypenameContext extends ParserRuleContext {
		public SpecifiersContext specifiers() {
			return getRuleContext(SpecifiersContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterTypename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitTypename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitTypename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypenameContext typename() throws RecognitionException {
		TypenameContext _localctx = new TypenameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(150);
				specifiers();
				}
				break;
			}
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Type || _la==Identifier) {
				{
				setState(153);
				declarator(0);
				}
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

	public static class SpecifiersContext extends ParserRuleContext {
		public TerminalNode Type() { return getToken(txj_parseParser.Type, 0); }
		public SpecifiersContext specifiers() {
			return getRuleContext(SpecifiersContext.class,0);
		}
		public SpecifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterSpecifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitSpecifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitSpecifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecifiersContext specifiers() throws RecognitionException {
		SpecifiersContext _localctx = new SpecifiersContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_specifiers);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(Type);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				match(Type);
				setState(158);
				specifiers();
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

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(txj_parseParser.Identifier, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(Identifier);
			setState(162);
			match(T__6);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(163);
				argList();
				}
			}

			setState(166);
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

	public static class ArgListContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_argList);
		try {
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				expr(0);
				setState(170);
				match(T__28);
				setState(171);
				argList();
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

	public static class StatContext extends ParserRuleContext {
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public GotoStatementContext gotoStatement() {
			return getRuleContext(GotoStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public LabeledStatementContext labeledStatement() {
			return getRuleContext(LabeledStatementContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public IterationDecStatementContext iterationDecStatement() {
			return getRuleContext(IterationDecStatementContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stat);
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				breakStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				continueStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(177);
				gotoStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(178);
				returnStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(179);
				labeledStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(180);
				compoundStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(181);
				selectionStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(182);
				expressionStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(183);
				iterationStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(184);
				iterationDecStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(185);
				match(T__0);
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

	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__29);
			setState(189);
			match(T__0);
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

	public static class ContinueStatementContext extends ParserRuleContext {
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(T__30);
			setState(192);
			match(T__0);
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

	public static class GotoStatementContext extends ParserRuleContext {
		public Token flag;
		public TerminalNode Identifier() { return getToken(txj_parseParser.Identifier, 0); }
		public GotoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gotoStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterGotoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitGotoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitGotoStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GotoStatementContext gotoStatement() throws RecognitionException {
		GotoStatementContext _localctx = new GotoStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_gotoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__31);
			setState(195);
			((GotoStatementContext)_localctx).flag = match(Identifier);
			setState(196);
			match(T__0);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__32);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(199);
				exprs();
				}
			}

			setState(202);
			match(T__0);
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

	public static class LabeledStatementContext extends ParserRuleContext {
		public Token label;
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(txj_parseParser.Identifier, 0); }
		public LabeledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterLabeledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitLabeledStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitLabeledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabeledStatementContext labeledStatement() throws RecognitionException {
		LabeledStatementContext _localctx = new LabeledStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_labeledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			((LabeledStatementContext)_localctx).label = match(Identifier);
			setState(205);
			match(T__27);
			setState(206);
			stat();
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

	public static class CompoundStatementContext extends ParserRuleContext {
		public BlockitemsContext blockitems() {
			return getRuleContext(BlockitemsContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitCompoundStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__33);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__35) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Unary_operator) | (1L << Type) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(209);
				blockitems();
				}
			}

			setState(212);
			match(T__34);
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

	public static class BlockitemsContext extends ParserRuleContext {
		public BlockitemContext blockitem() {
			return getRuleContext(BlockitemContext.class,0);
		}
		public BlockitemsContext blockitems() {
			return getRuleContext(BlockitemsContext.class,0);
		}
		public BlockitemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockitems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterBlockitems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitBlockitems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitBlockitems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockitemsContext blockitems() throws RecognitionException {
		BlockitemsContext _localctx = new BlockitemsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_blockitems);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				blockitem();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				blockitem();
				setState(216);
				blockitems();
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

	public static class BlockitemContext extends ParserRuleContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public BlockitemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockitem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterBlockitem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitBlockitem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitBlockitem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockitemContext blockitem() throws RecognitionException {
		BlockitemContext _localctx = new BlockitemContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_blockitem);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__5:
			case T__6:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__35:
			case T__37:
			case T__38:
			case T__39:
			case Unary_operator:
			case IntegerConstant:
			case CharConstant:
			case FloatConstant:
			case StringLiteral:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				stat();
				}
				break;
			case Type:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				declaration();
				setState(222);
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

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			exprs();
			setState(227);
			match(T__0);
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

	public static class ExprsContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitExprs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprs);
		try {
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				expr(0);
				setState(231);
				match(T__28);
				setState(232);
				exprs();
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

	public static class SelectionStatementContext extends ParserRuleContext {
		public StatContext then;
		public StatContext otherwise;
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitSelectionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitSelectionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_selectionStatement);
		try {
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(T__35);
				setState(237);
				match(T__6);
				setState(238);
				exprs();
				setState(239);
				match(T__7);
				setState(240);
				((SelectionStatementContext)_localctx).then = stat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(T__35);
				setState(243);
				match(T__6);
				setState(244);
				exprs();
				setState(245);
				match(T__7);
				setState(246);
				((SelectionStatementContext)_localctx).then = stat();
				setState(247);
				match(T__36);
				setState(248);
				((SelectionStatementContext)_localctx).otherwise = stat();
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

	public static class IterationStatementContext extends ParserRuleContext {
		public ExprsContext init;
		public ExprsContext cond;
		public ExprsContext step;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public List<ExprsContext> exprs() {
			return getRuleContexts(ExprsContext.class);
		}
		public ExprsContext exprs(int i) {
			return getRuleContext(ExprsContext.class,i);
		}
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterIterationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitIterationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitIterationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_iterationStatement);
		int _la;
		try {
			setState(281);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				match(T__37);
				setState(253);
				match(T__6);
				setState(254);
				expr(0);
				setState(255);
				match(T__7);
				setState(256);
				stat();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				match(T__38);
				setState(259);
				stat();
				setState(260);
				match(T__37);
				setState(261);
				match(T__6);
				setState(262);
				expr(0);
				setState(263);
				match(T__7);
				setState(264);
				match(T__0);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3);
				{
				setState(266);
				match(T__39);
				setState(267);
				match(T__6);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(268);
					((IterationStatementContext)_localctx).init = exprs();
					}
				}

				setState(271);
				match(T__0);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(272);
					((IterationStatementContext)_localctx).cond = exprs();
					}
				}

				setState(275);
				match(T__0);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
					{
					setState(276);
					((IterationStatementContext)_localctx).step = exprs();
					}
				}

				setState(279);
				match(T__7);
				setState(280);
				stat();
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

	public static class IterationDecStatementContext extends ParserRuleContext {
		public DeclarationContext init2;
		public ExprsContext cond;
		public ExprsContext step;
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public List<ExprsContext> exprs() {
			return getRuleContexts(ExprsContext.class);
		}
		public ExprsContext exprs(int i) {
			return getRuleContext(ExprsContext.class,i);
		}
		public IterationDecStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationDecStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterIterationDecStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitIterationDecStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitIterationDecStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationDecStatementContext iterationDecStatement() throws RecognitionException {
		IterationDecStatementContext _localctx = new IterationDecStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_iterationDecStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__39);
			setState(284);
			match(T__6);
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Type) {
				{
				setState(285);
				((IterationDecStatementContext)_localctx).init2 = declaration();
				}
			}

			setState(288);
			match(T__0);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(289);
				((IterationDecStatementContext)_localctx).cond = exprs();
				}
			}

			setState(292);
			match(T__0);
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << Unary_operator) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << FloatConstant) | (1L << StringLiteral) | (1L << Identifier))) != 0)) {
				{
				setState(293);
				((IterationDecStatementContext)_localctx).step = exprs();
				}
			}

			setState(296);
			match(T__7);
			setState(297);
			stat();
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

	public static class DeclaratorContext extends ParserRuleContext {
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
	 
		public DeclaratorContext() { }
		public void copyFrom(DeclaratorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParamsDeclaratorContext extends DeclaratorContext {
		public TerminalNode Type() { return getToken(txj_parseParser.Type, 0); }
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public ParamsDeclaratorContext(DeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterParamsDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitParamsDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitParamsDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayDeclaratorContext extends DeclaratorContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayDeclaratorContext(DeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterArrayDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitArrayDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitArrayDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunDeclaratorContext extends DeclaratorContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunDeclaratorContext(DeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterFunDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitFunDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitFunDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableDeclaratorContext extends DeclaratorContext {
		public TerminalNode Identifier() { return getToken(txj_parseParser.Identifier, 0); }
		public VariableDeclaratorContext(DeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitVariableDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitVariableDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		return declarator(0);
	}

	private DeclaratorContext declarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, _parentState);
		DeclaratorContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_declarator, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				_localctx = new VariableDeclaratorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(300);
				match(Identifier);
				}
				break;
			case Type:
				{
				_localctx = new ParamsDeclaratorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(301);
				match(Type);
				setState(302);
				declarator(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(316);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new ArrayDeclaratorContext(new DeclaratorContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_declarator);
						setState(305);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(306);
						match(T__3);
						setState(307);
						expr(0);
						setState(308);
						match(T__4);
						}
						break;
					case 2:
						{
						_localctx = new FunDeclaratorContext(new DeclaratorContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_declarator);
						setState(310);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(311);
						match(T__6);
						setState(313);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
						case 1:
							{
							setState(312);
							arguments();
							}
							break;
						}
						setState(315);
						match(T__7);
						}
						break;
					}
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	public static class InitListContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TerminalNode Assignment_operator() { return getToken(txj_parseParser.Assignment_operator, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public InitListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterInitList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitInitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitInitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitListContext initList() throws RecognitionException {
		InitListContext _localctx = new InitListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_initList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			declarator(0);
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(322);
				match(Assignment_operator);
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(323);
					match(T__33);
					}
				}

				setState(326);
				exprs();
				setState(328);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(327);
					match(T__34);
					}
					break;
				}
				}
				break;
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

	public static class InitListsContext extends ParserRuleContext {
		public InitListContext initList() {
			return getRuleContext(InitListContext.class,0);
		}
		public InitListsContext initLists() {
			return getRuleContext(InitListsContext.class,0);
		}
		public InitListsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initLists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterInitLists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitInitLists(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitInitLists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitListsContext initLists() throws RecognitionException {
		return initLists(0);
	}

	private InitListsContext initLists(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InitListsContext _localctx = new InitListsContext(_ctx, _parentState);
		InitListsContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_initLists, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(333);
			initList();
			}
			_ctx.stop = _input.LT(-1);
			setState(340);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InitListsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_initLists);
					setState(335);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(336);
					match(T__28);
					setState(337);
					initList();
					}
					} 
				}
				setState(342);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
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

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode Type() { return getToken(txj_parseParser.Type, 0); }
		public InitListsContext initLists() {
			return getRuleContext(InitListsContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(Type);
			setState(344);
			initLists(0);
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

	public static class FunctionDefineContext extends ParserRuleContext {
		public CompoundStatementContext body;
		public TerminalNode Type() { return getToken(txj_parseParser.Type, 0); }
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunctionDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterFunctionDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitFunctionDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitFunctionDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefineContext functionDefine() throws RecognitionException {
		FunctionDefineContext _localctx = new FunctionDefineContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_functionDefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(Type);
			setState(347);
			declarator(0);
			setState(348);
			match(T__6);
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(349);
				arguments();
				}
				break;
			}
			setState(352);
			match(T__7);
			setState(353);
			((FunctionDefineContext)_localctx).body = compoundStatement();
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

	public static class ArgumentsContext extends ParserRuleContext {
		public FundeflistContext fundeflist() {
			return getRuleContext(FundeflistContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Type || _la==Identifier) {
				{
				setState(355);
				fundeflist();
				}
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

	public static class FundeflistContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public FundeflistContext fundeflist() {
			return getRuleContext(FundeflistContext.class,0);
		}
		public FundeflistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fundeflist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).enterFundeflist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof txj_parseListener ) ((txj_parseListener)listener).exitFundeflist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof txj_parseVisitor ) return ((txj_parseVisitor<? extends T>)visitor).visitFundeflist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FundeflistContext fundeflist() throws RecognitionException {
		FundeflistContext _localctx = new FundeflistContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_fundeflist);
		try {
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				declarator(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				declarator(0);
				setState(360);
				match(T__28);
				setState(361);
				fundeflist();
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
		case 3:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 22:
			return declarator_sempred((DeclaratorContext)_localctx, predIndex);
		case 24:
			return initLists_sempred((InitListsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 10);
		case 10:
			return precpred(_ctx, 9);
		case 11:
			return precpred(_ctx, 8);
		case 12:
			return precpred(_ctx, 24);
		case 13:
			return precpred(_ctx, 23);
		}
		return true;
	}
	private boolean declarator_sempred(DeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 3);
		case 15:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean initLists_sempred(InitListsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u0170\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\5\2>\n\2\3\2\3"+
		"\2\3\3\6\3C\n\3\r\3\16\3D\3\4\3\4\3\4\3\4\5\4K\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5d\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0094\n\5"+
		"\f\5\16\5\u0097\13\5\3\6\5\6\u009a\n\6\3\6\5\6\u009d\n\6\3\7\3\7\3\7\5"+
		"\7\u00a2\n\7\3\b\3\b\3\b\5\b\u00a7\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t"+
		"\u00b0\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00bd\n\n\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\5\16\u00cb\n\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\5\20\u00d5\n\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\5\21\u00dd\n\21\3\22\3\22\3\22\3\22\5\22\u00e3\n\22\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u00ed\n\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00fd\n\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\5\26\u0110\n\26\3\26\3\26\5\26\u0114\n\26\3\26\3\26\5"+
		"\26\u0118\n\26\3\26\3\26\5\26\u011c\n\26\3\27\3\27\3\27\5\27\u0121\n\27"+
		"\3\27\3\27\5\27\u0125\n\27\3\27\3\27\5\27\u0129\n\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\5\30\u0132\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u013c\n\30\3\30\7\30\u013f\n\30\f\30\16\30\u0142\13\30\3\31"+
		"\3\31\3\31\5\31\u0147\n\31\3\31\3\31\5\31\u014b\n\31\5\31\u014d\n\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\7\32\u0155\n\32\f\32\16\32\u0158\13\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u0161\n\34\3\34\3\34\3\34\3\35"+
		"\5\35\u0167\n\35\3\36\3\36\3\36\3\36\3\36\5\36\u016e\n\36\3\36\2\5\b."+
		"\62\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2"+
		"\t\5\2\4\5\b\b..\3\2\13\r\3\2\16\17\3\2\20\21\3\2\22\25\3\2\26\27\3\2"+
		"\4\5\2\u0194\2=\3\2\2\2\4B\3\2\2\2\6J\3\2\2\2\bc\3\2\2\2\n\u0099\3\2\2"+
		"\2\f\u00a1\3\2\2\2\16\u00a3\3\2\2\2\20\u00af\3\2\2\2\22\u00bc\3\2\2\2"+
		"\24\u00be\3\2\2\2\26\u00c1\3\2\2\2\30\u00c4\3\2\2\2\32\u00c8\3\2\2\2\34"+
		"\u00ce\3\2\2\2\36\u00d2\3\2\2\2 \u00dc\3\2\2\2\"\u00e2\3\2\2\2$\u00e4"+
		"\3\2\2\2&\u00ec\3\2\2\2(\u00fc\3\2\2\2*\u011b\3\2\2\2,\u011d\3\2\2\2."+
		"\u0131\3\2\2\2\60\u0143\3\2\2\2\62\u014e\3\2\2\2\64\u0159\3\2\2\2\66\u015c"+
		"\3\2\2\28\u0166\3\2\2\2:\u016d\3\2\2\2<>\5\4\3\2=<\3\2\2\2=>\3\2\2\2>"+
		"?\3\2\2\2?@\7\2\2\3@\3\3\2\2\2AC\5\6\4\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2"+
		"DE\3\2\2\2E\5\3\2\2\2FK\5\66\34\2GH\5\64\33\2HI\7\3\2\2IK\3\2\2\2JF\3"+
		"\2\2\2JG\3\2\2\2K\7\3\2\2\2LM\b\5\1\2MN\t\2\2\2Nd\5\b\5\30OP\7\b\2\2P"+
		"Q\7\t\2\2QR\5\n\6\2RS\7\n\2\2Sd\3\2\2\2TU\7\t\2\2UV\5\n\6\2VW\7\n\2\2"+
		"WX\5\b\5\26Xd\3\2\2\2Yd\5\16\b\2Zd\7\61\2\2[d\7\62\2\2\\d\7\63\2\2]d\7"+
		"\65\2\2^d\7\64\2\2_`\7\t\2\2`a\5\b\5\2ab\7\n\2\2bd\3\2\2\2cL\3\2\2\2c"+
		"O\3\2\2\2cT\3\2\2\2cY\3\2\2\2cZ\3\2\2\2c[\3\2\2\2c\\\3\2\2\2c]\3\2\2\2"+
		"c^\3\2\2\2c_\3\2\2\2d\u0095\3\2\2\2ef\f\25\2\2fg\t\3\2\2g\u0094\5\b\5"+
		"\26hi\f\24\2\2ij\t\4\2\2j\u0094\5\b\5\25kl\f\23\2\2lm\t\5\2\2m\u0094\5"+
		"\b\5\24no\f\22\2\2op\t\6\2\2p\u0094\5\b\5\23qr\f\21\2\2rs\t\7\2\2s\u0094"+
		"\5\b\5\22tu\f\20\2\2uv\7\30\2\2v\u0094\5\b\5\21wx\f\17\2\2xy\7\31\2\2"+
		"y\u0094\5\b\5\20z{\f\16\2\2{|\7\32\2\2|\u0094\5\b\5\17}~\f\r\2\2~\177"+
		"\7\33\2\2\177\u0094\5\b\5\16\u0080\u0081\f\f\2\2\u0081\u0082\7\34\2\2"+
		"\u0082\u0094\5\b\5\r\u0083\u0084\f\13\2\2\u0084\u0085\7\35\2\2\u0085\u0086"+
		"\5&\24\2\u0086\u0087\7\36\2\2\u0087\u0088\5\b\5\f\u0088\u0094\3\2\2\2"+
		"\u0089\u008a\f\n\2\2\u008a\u008b\7/\2\2\u008b\u0094\5\b\5\13\u008c\u008d"+
		"\f\32\2\2\u008d\u0094\t\b\2\2\u008e\u008f\f\31\2\2\u008f\u0090\7\6\2\2"+
		"\u0090\u0091\5&\24\2\u0091\u0092\7\7\2\2\u0092\u0094\3\2\2\2\u0093e\3"+
		"\2\2\2\u0093h\3\2\2\2\u0093k\3\2\2\2\u0093n\3\2\2\2\u0093q\3\2\2\2\u0093"+
		"t\3\2\2\2\u0093w\3\2\2\2\u0093z\3\2\2\2\u0093}\3\2\2\2\u0093\u0080\3\2"+
		"\2\2\u0093\u0083\3\2\2\2\u0093\u0089\3\2\2\2\u0093\u008c\3\2\2\2\u0093"+
		"\u008e\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\t\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\5\f\7\2\u0099\u0098"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\5.\30\2\u009c"+
		"\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\13\3\2\2\2\u009e\u00a2\7\60\2"+
		"\2\u009f\u00a0\7\60\2\2\u00a0\u00a2\5\f\7\2\u00a1\u009e\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\r\3\2\2\2\u00a3\u00a4\7\65\2\2\u00a4\u00a6\7\t\2"+
		"\2\u00a5\u00a7\5\20\t\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\7\n\2\2\u00a9\17\3\2\2\2\u00aa\u00b0\5\b\5"+
		"\2\u00ab\u00ac\5\b\5\2\u00ac\u00ad\7\37\2\2\u00ad\u00ae\5\20\t\2\u00ae"+
		"\u00b0\3\2\2\2\u00af\u00aa\3\2\2\2\u00af\u00ab\3\2\2\2\u00b0\21\3\2\2"+
		"\2\u00b1\u00bd\5\24\13\2\u00b2\u00bd\5\26\f\2\u00b3\u00bd\5\30\r\2\u00b4"+
		"\u00bd\5\32\16\2\u00b5\u00bd\5\34\17\2\u00b6\u00bd\5\36\20\2\u00b7\u00bd"+
		"\5(\25\2\u00b8\u00bd\5$\23\2\u00b9\u00bd\5*\26\2\u00ba\u00bd\5,\27\2\u00bb"+
		"\u00bd\7\3\2\2\u00bc\u00b1\3\2\2\2\u00bc\u00b2\3\2\2\2\u00bc\u00b3\3\2"+
		"\2\2\u00bc\u00b4\3\2\2\2\u00bc\u00b5\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc"+
		"\u00b7\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bc\u00b9\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bc\u00bb\3\2\2\2\u00bd\23\3\2\2\2\u00be\u00bf\7 \2\2\u00bf\u00c0"+
		"\7\3\2\2\u00c0\25\3\2\2\2\u00c1\u00c2\7!\2\2\u00c2\u00c3\7\3\2\2\u00c3"+
		"\27\3\2\2\2\u00c4\u00c5\7\"\2\2\u00c5\u00c6\7\65\2\2\u00c6\u00c7\7\3\2"+
		"\2\u00c7\31\3\2\2\2\u00c8\u00ca\7#\2\2\u00c9\u00cb\5&\24\2\u00ca\u00c9"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\7\3\2\2\u00cd"+
		"\33\3\2\2\2\u00ce\u00cf\7\65\2\2\u00cf\u00d0\7\36\2\2\u00d0\u00d1\5\22"+
		"\n\2\u00d1\35\3\2\2\2\u00d2\u00d4\7$\2\2\u00d3\u00d5\5 \21\2\u00d4\u00d3"+
		"\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\7%\2\2\u00d7"+
		"\37\3\2\2\2\u00d8\u00dd\5\"\22\2\u00d9\u00da\5\"\22\2\u00da\u00db\5 \21"+
		"\2\u00db\u00dd\3\2\2\2\u00dc\u00d8\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dd!"+
		"\3\2\2\2\u00de\u00e3\5\22\n\2\u00df\u00e0\5\64\33\2\u00e0\u00e1\7\3\2"+
		"\2\u00e1\u00e3\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2\u00df\3\2\2\2\u00e3#"+
		"\3\2\2\2\u00e4\u00e5\5&\24\2\u00e5\u00e6\7\3\2\2\u00e6%\3\2\2\2\u00e7"+
		"\u00ed\5\b\5\2\u00e8\u00e9\5\b\5\2\u00e9\u00ea\7\37\2\2\u00ea\u00eb\5"+
		"&\24\2\u00eb\u00ed\3\2\2\2\u00ec\u00e7\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed"+
		"\'\3\2\2\2\u00ee\u00ef\7&\2\2\u00ef\u00f0\7\t\2\2\u00f0\u00f1\5&\24\2"+
		"\u00f1\u00f2\7\n\2\2\u00f2\u00f3\5\22\n\2\u00f3\u00fd\3\2\2\2\u00f4\u00f5"+
		"\7&\2\2\u00f5\u00f6\7\t\2\2\u00f6\u00f7\5&\24\2\u00f7\u00f8\7\n\2\2\u00f8"+
		"\u00f9\5\22\n\2\u00f9\u00fa\7\'\2\2\u00fa\u00fb\5\22\n\2\u00fb\u00fd\3"+
		"\2\2\2\u00fc\u00ee\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fd)\3\2\2\2\u00fe\u00ff"+
		"\7(\2\2\u00ff\u0100\7\t\2\2\u0100\u0101\5\b\5\2\u0101\u0102\7\n\2\2\u0102"+
		"\u0103\5\22\n\2\u0103\u011c\3\2\2\2\u0104\u0105\7)\2\2\u0105\u0106\5\22"+
		"\n\2\u0106\u0107\7(\2\2\u0107\u0108\7\t\2\2\u0108\u0109\5\b\5\2\u0109"+
		"\u010a\7\n\2\2\u010a\u010b\7\3\2\2\u010b\u011c\3\2\2\2\u010c\u010d\7*"+
		"\2\2\u010d\u010f\7\t\2\2\u010e\u0110\5&\24\2\u010f\u010e\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\7\3\2\2\u0112\u0114\5&"+
		"\24\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0117\7\3\2\2\u0116\u0118\5&\24\2\u0117\u0116\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\n\2\2\u011a\u011c\5\22\n\2\u011b"+
		"\u00fe\3\2\2\2\u011b\u0104\3\2\2\2\u011b\u010c\3\2\2\2\u011c+\3\2\2\2"+
		"\u011d\u011e\7*\2\2\u011e\u0120\7\t\2\2\u011f\u0121\5\64\33\2\u0120\u011f"+
		"\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\7\3\2\2\u0123"+
		"\u0125\5&\24\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\u0128\7\3\2\2\u0127\u0129\5&\24\2\u0128\u0127\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\7\n\2\2\u012b\u012c\5\22"+
		"\n\2\u012c-\3\2\2\2\u012d\u012e\b\30\1\2\u012e\u0132\7\65\2\2\u012f\u0130"+
		"\7\60\2\2\u0130\u0132\5.\30\3\u0131\u012d\3\2\2\2\u0131\u012f\3\2\2\2"+
		"\u0132\u0140\3\2\2\2\u0133\u0134\f\5\2\2\u0134\u0135\7\6\2\2\u0135\u0136"+
		"\5\b\5\2\u0136\u0137\7\7\2\2\u0137\u013f\3\2\2\2\u0138\u0139\f\4\2\2\u0139"+
		"\u013b\7\t\2\2\u013a\u013c\58\35\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\u013d\3\2\2\2\u013d\u013f\7\n\2\2\u013e\u0133\3\2\2\2\u013e"+
		"\u0138\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2"+
		"\2\2\u0141/\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u014c\5.\30\2\u0144\u0146"+
		"\7/\2\2\u0145\u0147\7$\2\2\u0146\u0145\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u014a\5&\24\2\u0149\u014b\7%\2\2\u014a\u0149\3\2"+
		"\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u0144\3\2\2\2\u014c"+
		"\u014d\3\2\2\2\u014d\61\3\2\2\2\u014e\u014f\b\32\1\2\u014f\u0150\5\60"+
		"\31\2\u0150\u0156\3\2\2\2\u0151\u0152\f\3\2\2\u0152\u0153\7\37\2\2\u0153"+
		"\u0155\5\60\31\2\u0154\u0151\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3"+
		"\2\2\2\u0156\u0157\3\2\2\2\u0157\63\3\2\2\2\u0158\u0156\3\2\2\2\u0159"+
		"\u015a\7\60\2\2\u015a\u015b\5\62\32\2\u015b\65\3\2\2\2\u015c\u015d\7\60"+
		"\2\2\u015d\u015e\5.\30\2\u015e\u0160\7\t\2\2\u015f\u0161\58\35\2\u0160"+
		"\u015f\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\7\n"+
		"\2\2\u0163\u0164\5\36\20\2\u0164\67\3\2\2\2\u0165\u0167\5:\36\2\u0166"+
		"\u0165\3\2\2\2\u0166\u0167\3\2\2\2\u01679\3\2\2\2\u0168\u016e\5.\30\2"+
		"\u0169\u016a\5.\30\2\u016a\u016b\7\37\2\2\u016b\u016c\5:\36\2\u016c\u016e"+
		"\3\2\2\2\u016d\u0168\3\2\2\2\u016d\u0169\3\2\2\2\u016e;\3\2\2\2&=DJc\u0093"+
		"\u0095\u0099\u009c\u00a1\u00a6\u00af\u00bc\u00ca\u00d4\u00dc\u00e2\u00ec"+
		"\u00fc\u010f\u0113\u0117\u011b\u0120\u0124\u0128\u0131\u013b\u013e\u0140"+
		"\u0146\u014a\u014c\u0156\u0160\u0166\u016d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}