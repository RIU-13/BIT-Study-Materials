package bit.minisys.minicc.parser;

import bit.minisys.minicc.MiniCCCfg;
import bit.minisys.minicc.internal.util.MiniCCUtil;
import bit.minisys.minicc.parser.ast.ASTNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.tree.ParseTree;
import bit.minisys.minicc.parser.txjparser.*;

import java.io.*;
import java.util.Arrays;

import bit.minisys.minicc.parser.ast.*;
public class txjParseMain {


    public void run(String file) throws IOException {
        String oFile = MiniCCUtil.removeAllExt(file)+ MiniCCCfg.MINICC_PARSER_OUTPUT_EXT;

        InputStream is = new FileInputStream(file);
        CharStream input = CharStreams.fromStream(is);
        txj_parseLexer lexer = new txj_parseLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        txj_parseParser parser = new txj_parseParser(tokenStream);
        ParseTree parseTree = parser.program();

        OutputJson visitor = new OutputJson();
        ASTNode root = (ASTNode) visitor.visit(parseTree);


        //Êä³öµ½jsonÖÐ
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(oFile), root);
        System.out.println("txjParse Finished!");
    }
}
