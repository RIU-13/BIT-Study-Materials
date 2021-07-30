// Generated from D:/大三下/编译原理和设计/大作业/anltrcode/src/main\txjParse.g4 by ANTLR 4.9.1
package txjParse;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link txjParseParser}.
 */
public interface txjParseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link txjParseParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(txjParseParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link txjParseParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(txjParseParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link txjParseParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(txjParseParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link txjParseParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(txjParseParser.FunctionContext ctx);
}