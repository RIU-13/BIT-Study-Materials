package bit.minisys.minicc.parser;
import bit.minisys.minicc.parser.ast.ASTCompilationUnit;
import bit.minisys.minicc.parser.ast.ASTNode;
import bit.minisys.minicc.parser.txjparser.txj_parseBaseVisitor;
import bit.minisys.minicc.parser.txjparser.txj_parseParser;
import bit.minisys.minicc.parser.ast.*;
import bit.minisys.minicc.pp.internal.P;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.python.constantine.platform.Fcntl;

import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OutputJson extends txj_parseBaseVisitor{

    @Override
    public ASTNode visitProgram(txj_parseParser.ProgramContext ctx)
    {
        ASTCompilationUnit p = new ASTCompilationUnit();
        if(ctx.translationUnit()!=null)
        {
            ArrayList<ASTNode> f1 = (ArrayList<ASTNode>) visitTranslationUnit(ctx.translationUnit());
            p.items.addAll(f1);
            p.children.addAll(p.items);
        }


        return p;
    }

    @Override
    public ArrayList<ASTNode> visitTranslationUnit(txj_parseParser.TranslationUnitContext ctx) {
        ArrayList<ASTNode> trans = new ArrayList<ASTNode>();
        for(int i=0;i<ctx.getChildCount();i++)
        {
            if(ctx.children.get(i)!=null)
            {
                ASTNode t = (ASTNode) visit(ctx.children.get(i));
                if(t!=null)
                    trans.add(t);

            }

        }
        return trans;
    }

    @Override
    public ASTNode visitExternalDeclaration(txj_parseParser.ExternalDeclarationContext ctx) {
        if(ctx.declaration()!=null)
        {
            ASTDeclaration dec = visitDeclaration(ctx.declaration());
            return dec;
        }
        else if(ctx.functionDefine()!=null)
        {
            ASTFunctionDefine functionDefine = visitFunctionDefine(ctx.functionDefine());
            return functionDefine;
        }
        return null;
    }



    @Override
    public ASTFunctionDefine visitFunctionDefine(txj_parseParser.FunctionDefineContext ctx) {
        if(ctx==null)
            return null;
        ASTFunctionDefine fdef = new ASTFunctionDefine();
        Token spetoken = ctx.Type().getSymbol();
        ASTToken s = new ASTToken();
        s.tokenId = spetoken.getTokenIndex();
        s.value = spetoken.getText();
        fdef.specifiers.add(s);
        //fdef.children.add(s);

        ASTFunctionDeclarator fdec = new ASTFunctionDeclarator();
        ASTDeclarator dec = (ASTDeclarator) visit(ctx.declarator());
        fdec.declarator = dec;
        fdef.children.add(dec);
        ArrayList<ASTParamsDeclarator> pl = visitArguments(ctx.arguments());
        if(pl!=null)
        {
            fdec.params.addAll(pl);
            fdec.children.addAll(pl);
        }
        ASTCompoundStatement cs = visitCompoundStatement(ctx.compoundStatement());

        fdef.declarator = fdec;
        fdef.children.add(fdec);
        fdef.body = cs;
        fdef.children.add(cs);
        return fdef;
    }


        @Override
    public ASTVariableDeclarator visitVariableDeclarator(txj_parseParser.VariableDeclaratorContext ctx) {
        ASTVariableDeclarator v = new ASTVariableDeclarator();
        ASTIdentifier t = new ASTIdentifier();
        Token vardec = ctx.Identifier().getSymbol();
        t.tokenId = vardec.getTokenIndex();
        t.value = vardec.getText();
        v.identifier = t;
        return v;
    }

    @Override
    public ArrayList<ASTParamsDeclarator> visitArguments(txj_parseParser.ArgumentsContext ctx) {
        if(ctx==null)
        {
            return null;
        }
        ArrayList<ASTParamsDeclarator> al =visitFundeflist(ctx.fundeflist());
        return al;
        //return null;
    }

    //ARG_LIST --> ARGUMENT ',' ARGLIST | ARGUMENT
    @Override
    public ArrayList<ASTParamsDeclarator> visitFundeflist(txj_parseParser.FundeflistContext ctx) {
        if(ctx==null)
        {
            return null;
        }
        ArrayList<ASTParamsDeclarator> pdl = new ArrayList<ASTParamsDeclarator>();
        ASTParamsDeclarator pd = (ASTParamsDeclarator) visit(ctx.declarator());
        pdl.add(pd);

        //递归使用

        ArrayList<ASTParamsDeclarator> pdl2 = visitFundeflist(ctx.fundeflist());
        if(pdl2!=null)
        {
            pdl.addAll(pdl2);

        }

        return pdl;

    }

    @Override
    public ASTParamsDeclarator visitParamsDeclarator(txj_parseParser.ParamsDeclaratorContext ctx) {
        ASTParamsDeclarator t = new ASTParamsDeclarator();
        Token Typetoken = ctx.Type().getSymbol();
        ASTToken s = new ASTToken();
        s.tokenId = Typetoken.getTokenIndex();
        s.value = Typetoken.getText();
        t.specfiers.add(s);

        ASTDeclarator dec = (ASTDeclarator) visit(ctx.declarator());
        t.declarator = dec;

        return t;


    }

    @Override
    public ASTCompoundStatement visitCompoundStatement(txj_parseParser.CompoundStatementContext ctx) {
        ASTCompoundStatement cs = new ASTCompoundStatement();
        if(ctx.blockitems()!=null)
        {
            ArrayList<ASTNode> blockitems = visitBlockitems(ctx.blockitems());
            cs.blockItems = blockitems;
            cs.children.addAll(blockitems);
        }

        return cs;
    }

    @Override
    public ArrayList<ASTNode> visitBlockitems(txj_parseParser.BlockitemsContext ctx) {
        if(ctx==null)
        {
            return null;
        }
        ArrayList<ASTNode> cs = new ArrayList<ASTNode>();
        ASTNode s = visitBlockitem(ctx.blockitem());
        cs.add(s);


        ArrayList<ASTNode> cs2 = visitBlockitems(ctx.blockitems());
        if(cs2!=null)
            cs.addAll(cs2);

        return cs;
    }

    @Override
    public ASTNode visitBlockitem(txj_parseParser.BlockitemContext ctx) {
        if(ctx==null)
            return null;
        //Object temp = visit(ctx.stat());
        if(ctx.stat() != null)
        {
            ASTStatement stat = (ASTStatement) visit(ctx.stat());

            return stat;
        }

       // Object temp2 = visit(ctx.declaration());
        if(ctx.declaration()!=null)
        {
            ASTDeclaration dec = (ASTDeclaration) visit(ctx.declaration());
            return dec;
        }
        return null;

    }

    @Override
    public ASTReturnStatement visitReturnStatement(txj_parseParser.ReturnStatementContext ctx) {
        ASTReturnStatement rs = new ASTReturnStatement();
        if(ctx.exprs()!=null)
        {
            LinkedList<ASTExpression> exprs = new LinkedList<ASTExpression>();
            List<ASTExpression> myexprs = visitExprs(ctx.exprs());
            for(int i= 0;i<myexprs.size();i++)
                exprs.add(myexprs.get(i));
            rs.expr = exprs;
        }

        else
            rs.expr = null;


        return rs;
    }

    @Override
    public ASTExpression visitBinary_expression(txj_parseParser.Binary_expressionContext ctx) {
        if(ctx==null)
            return null;
        ASTBinaryExpression bexpr = new ASTBinaryExpression();
        ASTExpression term = (ASTExpression) visit(ctx.expr(0));
        //System.out.println(term);
        ASTExpression term2 = (ASTExpression) visit(ctx.expr(1));
        bexpr.expr1 = term;
        ASTToken tkn = new ASTToken();
        tkn.value = ctx.op.getText();
        tkn.tokenId = ctx.op.getTokenIndex();
        bexpr.expr2 = term2;
        bexpr.op = tkn;
        return bexpr;
    }

    @Override
    public Object visitMyExpr(txj_parseParser.MyExprContext ctx) {
        ASTExpression expr = (ASTExpression) visit(ctx.expr());
        return expr;
    }

    @Override
    public ASTIdentifier visitIdentifier(txj_parseParser.IdentifierContext ctx) {
        if(ctx==null)
            return null;
        ASTIdentifier term = new ASTIdentifier();
        Token it = ctx.Identifier().getSymbol();
        term.value = it.getText();
        term.tokenId = it.getTokenIndex();

        return term;
        }

    @Override
    public ASTDeclaration visitDeclaration(txj_parseParser.DeclarationContext ctx) {
        ASTDeclaration decr = new ASTDeclaration();
        //获得specifier
        Token typetoken = ctx.Type().getSymbol();
        ASTToken id = new ASTToken();
        id.tokenId = typetoken.getTokenIndex();
        id.value = typetoken.getText();
        ArrayList<ASTToken> spe = new ArrayList<ASTToken>();
        spe.add(id);
        decr.specifiers = spe;
        decr.children.add(id);
        //获得initlists
        if(ctx.initLists()!=null)
        {
            ArrayList<ASTInitList> init = visitInitLists(ctx.initLists());
            decr.initLists = init;
            decr.children.addAll(init);
        }

        return decr;
    }

    @Override
    public ArrayList<ASTInitList> visitInitLists(txj_parseParser.InitListsContext ctx) {
        if(ctx==null)
            return null;
        ArrayList<ASTInitList> inits = new ArrayList<ASTInitList>();
        ArrayList<ASTInitList> inits2 = visitInitLists(ctx.initLists());
        if(inits2!=null)
            inits.addAll(inits2);
        if(ctx.initList()!=null)
        {
            ASTInitList init = visitInitList(ctx.initList());
            inits.add(init);
        }

        return inits;

    }

    @Override
    public ASTInitList visitInitList(txj_parseParser.InitListContext ctx) {
        ASTInitList init = new ASTInitList();
        if(ctx.declarator()!=null)
        {
            ASTDeclarator d = (ASTDeclarator) visit(ctx.declarator());
            init.declarator = d;
        }


        List<ASTExpression> exprs = new ArrayList<ASTExpression>();
        //System.out.println(ctx.exprs());
        if(ctx.exprs()!=null)
        {
            exprs = visitExprs(ctx.exprs());

        }
        init.exprs = exprs;
        //System.out.println(init.exprs);
        return init;
    }

    @Override
    public ASTIntegerConstant visitIntegerConstant(txj_parseParser.IntegerConstantContext ctx) {
        ASTIntegerConstant ic = new ASTIntegerConstant();
        Token t = ctx.IntegerConstant().getSymbol();
        ic.tokenId = t.getTokenIndex();
        ic.value = Integer.parseInt(t.getText());
        return ic;
    }

    @Override
    public ASTFloatConstant visitFloatConstant(txj_parseParser.FloatConstantContext ctx) {
        ASTFloatConstant ic = new ASTFloatConstant();
        Token t = ctx.FloatConstant().getSymbol();
        ic.tokenId = t.getTokenIndex();
        ic.value = Double.parseDouble(t.getText());
        return ic;
    }

    @Override
    public ASTArrayAccess visitArray_access(txj_parseParser.Array_accessContext ctx) {
        ASTArrayAccess arr = new ASTArrayAccess();
        ASTExpression arrayname = (ASTExpression) visit(ctx.expr());
        if(ctx.exprs()!=null)
        {
            List<ASTExpression> elem = (List<ASTExpression>) visit(ctx.exprs());
            arr.elements = elem;
            arr.children.addAll(elem);
        }

        arr.arrayName = arrayname;
        arr.children.add(arrayname);

        return arr;
    }


    @Override
    public ASTFunctionCall visitFunctionCall(txj_parseParser.FunctionCallContext ctx) {
        //System.out.println("funcall");
        ASTFunctionCall fc = new ASTFunctionCall();
        ASTIdentifier id = new ASTIdentifier();
        Token t = ctx.Identifier().getSymbol();
        id.tokenId = t.getTokenIndex();
        id.value = t.getText();

        fc.funcname = (ASTExpression) id;
        fc.children.add(id);
        //System.out.println(fc);
        List<ASTExpression> argList = new ArrayList<ASTExpression>();
        if(ctx.argList()!=null)
        {
            argList = visitArgList(ctx.argList());

        }
        fc.argList = argList;
        fc.children.addAll(argList);

        return fc;


    }

    @Override
    public List<ASTExpression> visitArgList(txj_parseParser.ArgListContext ctx) {
        //System.out.println("arglist:"+ctx);
        List<ASTExpression> argList=new ArrayList<ASTExpression>();
        if(ctx==null)
        {
            return null;
        }
        ASTExpression exp = (ASTExpression) visit(ctx.expr());
        argList.add(exp);
        List<ASTExpression> argList2=visitArgList(ctx.argList());
        if(argList2!=null)
        {
            argList.addAll(argList2);
        }
        return argList;

    }

    @Override
    public ASTBreakStatement visitBreakStatement(txj_parseParser.BreakStatementContext ctx) {
        ASTBreakStatement br = new ASTBreakStatement();

        //System.out.println();
        return br;
    }

    @Override
    public ASTGotoStatement visitGotoStatement(txj_parseParser.GotoStatementContext ctx) {
        ASTGotoStatement gt = new ASTGotoStatement();
        ASTIdentifier flag = new ASTIdentifier();
        flag.tokenId = ctx.flag.getTokenIndex();
        flag.value = ctx.flag.getText();
        gt.label = flag;
        return gt;
    }

    @Override
    public ASTSelectionStatement visitSelectionStatement(txj_parseParser.SelectionStatementContext ctx) {
        ASTSelectionStatement st = new ASTSelectionStatement();
        LinkedList<ASTExpression> cond = new LinkedList<ASTExpression>();
        List<ASTExpression> conexpr = visitExprs(ctx.exprs());
        for(int i=0;i<conexpr.size();i++)
            cond.add(conexpr.get(i));

        st.cond = cond;
        ASTStatement then = (ASTStatement) visit(ctx.stat(0));
        st.then = then;
        if(ctx.stat(1)!=null)
        {
            //System.out.println("otherwise");
            ASTStatement otherwise = (ASTStatement) visit(ctx.stat(1));
            st.otherwise = otherwise;
        }
        return st;
    }

    @Override
    public ASTExpressionStatement visitExpressionStatement(txj_parseParser.ExpressionStatementContext ctx) {
        ASTExpressionStatement ext = new ASTExpressionStatement();
        ext.exprs = (List<ASTExpression>) visit(ctx.exprs());
        return ext;
    }

    @Override
    public List<ASTExpression> visitExprs(txj_parseParser.ExprsContext ctx) {
        if(ctx==null)
            return null;
        ASTExpression expr = (ASTExpression) visit(ctx.expr());
        List<ASTExpression> exprs = new ArrayList<ASTExpression>();
        exprs.add(expr);

        List<ASTExpression> exprs2 = visitExprs(ctx.exprs());
        if(exprs2!=null)
            exprs.addAll(exprs2);
        return exprs;
    }

    @Override
    public ASTPostfixExpression visitPostfix_expression(txj_parseParser.Postfix_expressionContext ctx) {
        ASTPostfixExpression post = new ASTPostfixExpression();
        post.expr = (ASTExpression) visit(ctx.expr());
        Token op = ctx.op;
        ASTToken opast = new ASTToken();
        opast.value = op.getText();
        opast.tokenId = op.getTokenIndex();
        post.op = opast;
        return post;
    }

    @Override
    public Object visitUnary_expression(txj_parseParser.Unary_expressionContext ctx) {
        ASTUnaryExpression unexpr = new ASTUnaryExpression();
        Token myop = ctx.op;
        ASTToken op = new ASTToken();
        op.tokenId = myop.getTokenIndex();
        op.value = myop.getText();
        unexpr.op = op;
        if(ctx.expr()!=null)
        {
            unexpr.expr = (ASTExpression) visit(ctx.expr());
        }
        return unexpr;
    }

    @Override
    public ASTUnaryTypename visitUnary_typename(txj_parseParser.Unary_typenameContext ctx) {
        ASTUnaryTypename unty = new ASTUnaryTypename();
        Token myop = ctx.op;
        ASTToken op = new ASTToken();
        op.tokenId = myop.getTokenIndex();
        op.value = myop.getText();
        unty.op = op;
        ASTTypename typename = visitTypename(ctx.typename());
        unty.children.add(typename);
        unty.typename = typename;
        return unty;


    }

    @Override
    public ASTTypename visitTypename(txj_parseParser.TypenameContext ctx) {
        ASTTypename typename = new ASTTypename();
        if(ctx.specifiers()!=null)
        {
            List<ASTToken> spec = visitSpecifiers(ctx.specifiers());
            typename.specfiers = spec;
            typename.children.addAll(spec);
        }
        if(ctx.declarator()!=null)
        {
            ASTDeclarator dec = (ASTDeclarator) visit(ctx.declarator());
            typename.declarator = dec;
            typename.children.add(dec);
        }
        return typename;
    }

    @Override
    public List<ASTToken> visitSpecifiers(txj_parseParser.SpecifiersContext ctx) {
        if(ctx == null)
            return null;

        List<ASTToken> specs = new ArrayList<ASTToken>();

        Token myspec = ctx.Type().getSymbol();
        ASTToken spec = new ASTToken();
        spec.value = myspec.getText();
        spec.tokenId = myspec.getTokenIndex();
        specs.add(spec);

        List<ASTToken> specs2 = visitSpecifiers(ctx.specifiers());
        if(specs2!=null)
        {
            specs.addAll(specs2);
        }
        return specs;
    }

    @Override
    public ASTArrayDeclarator visitArrayDeclarator(txj_parseParser.ArrayDeclaratorContext ctx) {
        ASTArrayDeclarator arrdec = new ASTArrayDeclarator();

        ASTDeclarator declarator = (ASTDeclarator) visit(ctx.declarator());
        arrdec.declarator = declarator;
        arrdec.children.add(declarator);
        ASTExpression expr = (ASTExpression) visit(ctx.expr());
        arrdec.expr = expr;
        arrdec.children.add(expr);
        return arrdec;
    }

    @Override
    public ASTCastExpression visitCast_expression(txj_parseParser.Cast_expressionContext ctx) {
        ASTCastExpression castexpr = new ASTCastExpression();
        ASTTypename typename = visitTypename(ctx.typename());
        castexpr.typename = typename;
        castexpr.children.add(typename);

        ASTExpression expr = (ASTExpression) visit(ctx.expr());
        castexpr.expr = expr;
        castexpr.children.add(expr);

        return castexpr;
    }

    @Override
    public ASTCharConstant visitCharConstant(txj_parseParser.CharConstantContext ctx) {
        ASTCharConstant ic = new ASTCharConstant();
        Token t = ctx.CharConstant().getSymbol();
        ic.tokenId = t.getTokenIndex();
        ic.value = t.getText();
        return ic;
    }

    @Override
    public ASTStringConstant visitStringLiteral(txj_parseParser.StringLiteralContext ctx) {
        ASTStringConstant ic = new ASTStringConstant();
        Token t = ctx.StringLiteral().getSymbol();
        ic.tokenId = t.getTokenIndex();
        ic.value = t.getText();
        return ic;
    }

    @Override
    public Object visitContinueStatement(txj_parseParser.ContinueStatementContext ctx) {
        ASTContinueStatement cstat = new ASTContinueStatement();
        return cstat;
    }

    @Override
    public ASTLabeledStatement visitLabeledStatement(txj_parseParser.LabeledStatementContext ctx) {
        ASTLabeledStatement labstat = new ASTLabeledStatement();
        ASTIdentifier id = new ASTIdentifier();
        id.tokenId = ctx.label.getTokenIndex();
        id.value = ctx.label.getText();
        labstat.label = id;
        labstat.children.add(id);
        ASTStatement statement = (ASTStatement) visit(ctx.stat());
        labstat.stat = statement;
        labstat.children.add(statement);
        return labstat;
    }

    @Override
    public ASTStatement visitIterationStatement(txj_parseParser.IterationStatementContext ctx) {
        ASTIterationStatement itstat = new ASTIterationStatement();
        if(ctx.init!=null)
        {
            LinkedList<ASTExpression> init = new LinkedList<ASTExpression>();
            List<ASTExpression> temp = visitExprs(ctx.init);
            for(int i=0;i<temp.size();i++)
                init.add(temp.get(i));
            itstat.init = init;
            itstat.children.addAll(init);
        }
        if(ctx.cond!=null)
        {
            LinkedList<ASTExpression> cond = new LinkedList<ASTExpression>();
            List<ASTExpression> temp2 = visitExprs(ctx.cond);
            for(int i=0;i<temp2.size();i++)
                cond.add(temp2.get(i));
            itstat.cond = cond;
            itstat.children.addAll(cond);
        }
        if(ctx.step!=null)
        {

            LinkedList<ASTExpression> step = new LinkedList<ASTExpression>();
            List<ASTExpression> temp3 = visitExprs(ctx.step);
            for(int i=0;i<temp3.size();i++)
                step.add(temp3.get(i));
            itstat.step = step;
            itstat.children.addAll(step);
        }

        ASTStatement statement = (ASTStatement) visitStat(ctx.stat());
        itstat.stat = statement;
        itstat.children.add(statement);
        return itstat;


    }

    @Override
    public Object visitIterationDecStatement(txj_parseParser.IterationDecStatementContext ctx) {
        ASTIterationDeclaredStatement itstat = new ASTIterationDeclaredStatement();
        if(ctx.init2!=null)
        {
            ASTDeclaration declaration = visitDeclaration(ctx.declaration());
            itstat.init = declaration;
            itstat.children.add(declaration);
        }
        if(ctx.cond!=null)
        {
            LinkedList<ASTExpression> cond = new LinkedList<ASTExpression>();
            List<ASTExpression> temp = visitExprs(ctx.cond);
            for(int i=0;i<temp.size();i++)
                cond.add(temp.get(i));
            itstat.cond = cond;
            itstat.children.addAll(cond);
        }
        if(ctx.step!=null)
        {

            LinkedList<ASTExpression> step = new LinkedList<ASTExpression>();
            List<ASTExpression> temp = visitExprs(ctx.step);
            for(int i=0;i<temp.size();i++)
                step.add(temp.get(i));
            itstat.step = step;
            itstat.children.addAll(step);
        }

        ASTStatement statement = (ASTStatement) visitStat(ctx.stat());
        itstat.stat = statement;
        itstat.children.add(statement);
        return itstat;

    }
    //    @Override
//    public LinkedList<ASTDeclaration> visitDeclarations(txj_parseParser.DeclarationsContext ctx) {
//        if(ctx==null)
//            return null;
//        LinkedList<ASTDeclaration> declarations = new LinkedList<ASTDeclaration>();
//        ASTDeclaration declaration = visitDeclaration(ctx.declaration());
//        declarations.add(declaration);
//        LinkedList<ASTDeclaration> declarations2 = visitDeclarations(ctx.declarations());
//        if(declarations2!=null)
//        {
//            declarations.addAll(declarations2);
//
//        }
//        return declarations;
//    }

    @Override
    public ASTConditionExpression visitConditional_expression(txj_parseParser.Conditional_expressionContext ctx) {
        ASTConditionExpression ctstat = new ASTConditionExpression();
        ASTExpression condExpr = (ASTExpression) visit(ctx.expr(0));
        ctstat.condExpr = condExpr;
        ctstat.children.add(condExpr);

        LinkedList<ASTExpression> trueExpr = new LinkedList<ASTExpression>();
        List<ASTExpression> temp = visitExprs(ctx.trueExpr);
        for(int i=0;i<temp.size();i++)
            trueExpr.add(temp.get(i));
        ctstat.trueExpr = trueExpr;
        ctstat.children.addAll(trueExpr);

        ASTExpression falseExpr = (ASTExpression) visit(ctx.falseExpr);
        ctstat.falseExpr = falseExpr ;
        ctstat.children.add(falseExpr);

        return ctstat;

    }

    @Override
    public Object visitFunDeclarator(txj_parseParser.FunDeclaratorContext ctx) {
        ASTFunctionDeclarator fundec = new ASTFunctionDeclarator();
        fundec.declarator = (ASTDeclarator) visit(ctx.declarator());
        List<ASTParamsDeclarator> params = new ArrayList<ASTParamsDeclarator>();
        if(ctx.arguments()!=null)
        {
            params = visitArguments(ctx.arguments());
        }
        fundec.params = params;
        return fundec;
    }


}
