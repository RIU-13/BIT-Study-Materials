// Generated from D:/大三下/编译原理和设计/大作业/ex5/BIT-MiniCC/src/bit/minisys/minicc\txj_parse.g4 by ANTLR 4.9.1
package bit.minisys.minicc.parser.txjparser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link txj_parseParser}.
 */
public interface txj_parseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(txj_parseParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(txj_parseParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(txj_parseParser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(txj_parseParser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternalDeclaration(txj_parseParser.ExternalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternalDeclaration(txj_parseParser.ExternalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expression(txj_parseParser.Unary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expression(txj_parseParser.Unary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatConstant(txj_parseParser.FloatConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatConstant(txj_parseParser.FloatConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharConstant(txj_parseParser.CharConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharConstant(txj_parseParser.CharConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Binary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinary_expression(txj_parseParser.Binary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binary_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinary_expression(txj_parseParser.Binary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntegerConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntegerConstant(txj_parseParser.IntegerConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerConstant}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntegerConstant(txj_parseParser.IntegerConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MyExpr}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMyExpr(txj_parseParser.MyExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MyExpr}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMyExpr(txj_parseParser.MyExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Postfix_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPostfix_expression(txj_parseParser.Postfix_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Postfix_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPostfix_expression(txj_parseParser.Postfix_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array_access}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArray_access(txj_parseParser.Array_accessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array_access}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArray_access(txj_parseParser.Array_accessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Cast_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCast_expression(txj_parseParser.Cast_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Cast_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCast_expression(txj_parseParser.Cast_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MyFunctionCall}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMyFunctionCall(txj_parseParser.MyFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MyFunctionCall}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMyFunctionCall(txj_parseParser.MyFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(txj_parseParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(txj_parseParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(txj_parseParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(txj_parseParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary_typename}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary_typename(txj_parseParser.Unary_typenameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary_typename}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary_typename(txj_parseParser.Unary_typenameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Conditional_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConditional_expression(txj_parseParser.Conditional_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Conditional_expression}
	 * labeled alternative in {@link txj_parseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConditional_expression(txj_parseParser.Conditional_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(txj_parseParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(txj_parseParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#specifiers}.
	 * @param ctx the parse tree
	 */
	void enterSpecifiers(txj_parseParser.SpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#specifiers}.
	 * @param ctx the parse tree
	 */
	void exitSpecifiers(txj_parseParser.SpecifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(txj_parseParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(txj_parseParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(txj_parseParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(txj_parseParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(txj_parseParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(txj_parseParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(txj_parseParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(txj_parseParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(txj_parseParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(txj_parseParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void enterGotoStatement(txj_parseParser.GotoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void exitGotoStatement(txj_parseParser.GotoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(txj_parseParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(txj_parseParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(txj_parseParser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(txj_parseParser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(txj_parseParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(txj_parseParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#blockitems}.
	 * @param ctx the parse tree
	 */
	void enterBlockitems(txj_parseParser.BlockitemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#blockitems}.
	 * @param ctx the parse tree
	 */
	void exitBlockitems(txj_parseParser.BlockitemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#blockitem}.
	 * @param ctx the parse tree
	 */
	void enterBlockitem(txj_parseParser.BlockitemContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#blockitem}.
	 * @param ctx the parse tree
	 */
	void exitBlockitem(txj_parseParser.BlockitemContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(txj_parseParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(txj_parseParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#exprs}.
	 * @param ctx the parse tree
	 */
	void enterExprs(txj_parseParser.ExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#exprs}.
	 * @param ctx the parse tree
	 */
	void exitExprs(txj_parseParser.ExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(txj_parseParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(txj_parseParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(txj_parseParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(txj_parseParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#iterationDecStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationDecStatement(txj_parseParser.IterationDecStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#iterationDecStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationDecStatement(txj_parseParser.IterationDecStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParamsDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterParamsDeclarator(txj_parseParser.ParamsDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParamsDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitParamsDeclarator(txj_parseParser.ParamsDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclarator(txj_parseParser.ArrayDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclarator(txj_parseParser.ArrayDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclarator(txj_parseParser.FunDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclarator(txj_parseParser.FunDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(txj_parseParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableDeclarator}
	 * labeled alternative in {@link txj_parseParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(txj_parseParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#initList}.
	 * @param ctx the parse tree
	 */
	void enterInitList(txj_parseParser.InitListContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#initList}.
	 * @param ctx the parse tree
	 */
	void exitInitList(txj_parseParser.InitListContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#initLists}.
	 * @param ctx the parse tree
	 */
	void enterInitLists(txj_parseParser.InitListsContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#initLists}.
	 * @param ctx the parse tree
	 */
	void exitInitLists(txj_parseParser.InitListsContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(txj_parseParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(txj_parseParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefine(txj_parseParser.FunctionDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefine(txj_parseParser.FunctionDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(txj_parseParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(txj_parseParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link txj_parseParser#fundeflist}.
	 * @param ctx the parse tree
	 */
	void enterFundeflist(txj_parseParser.FundeflistContext ctx);
	/**
	 * Exit a parse tree produced by {@link txj_parseParser#fundeflist}.
	 * @param ctx the parse tree
	 */
	void exitFundeflist(txj_parseParser.FundeflistContext ctx);
}