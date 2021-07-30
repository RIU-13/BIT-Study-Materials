// Generated from D:/大三下/编译原理和设计/大作业/ex5/BIT-MiniCC/src/bit/minisys/minicc\txj_parse.g4 by ANTLR 4.9.1
package bit.minisys.minicc.parser.txjparser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link txj_parseParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface txj_parseVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(txj_parseParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#translationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationUnit(txj_parseParser.TranslationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#externalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalDeclaration(txj_parseParser.ExternalDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_expression(txj_parseParser.Unary_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatConstant(txj_parseParser.FloatConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharConstant(txj_parseParser.CharConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Binary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_expression(txj_parseParser.Binary_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConstant(txj_parseParser.IntegerConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyExpr}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyExpr(txj_parseParser.MyExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Postfix_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix_expression(txj_parseParser.Postfix_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array_access}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_access(txj_parseParser.Array_accessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Cast_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCast_expression(txj_parseParser.Cast_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MyFunctionCall}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyFunctionCall(txj_parseParser.MyFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(txj_parseParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(txj_parseParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary_typename}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_typename(txj_parseParser.Unary_typenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Conditional_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_expression(txj_parseParser.Conditional_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#typename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypename(txj_parseParser.TypenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#specifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifiers(txj_parseParser.SpecifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(txj_parseParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(txj_parseParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(txj_parseParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(txj_parseParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(txj_parseParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#gotoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoStatement(txj_parseParser.GotoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(txj_parseParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#labeledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledStatement(txj_parseParser.LabeledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(txj_parseParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#blockitems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockitems(txj_parseParser.BlockitemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#blockitem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockitem(txj_parseParser.BlockitemContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(txj_parseParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprs(txj_parseParser.ExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(txj_parseParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(txj_parseParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#iterationDecStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationDecStatement(txj_parseParser.IterationDecStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParamsDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamsDeclarator(txj_parseParser.ParamsDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclarator(txj_parseParser.ArrayDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclarator(txj_parseParser.FunDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(txj_parseParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#initList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitList(txj_parseParser.InitListContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#initLists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitLists(txj_parseParser.InitListsContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(txj_parseParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#functionDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefine(txj_parseParser.FunctionDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(txj_parseParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link txj_parseParser#fundeflist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundeflist(txj_parseParser.FundeflistContext ctx);
}