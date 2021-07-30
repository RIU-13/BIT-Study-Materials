
import txjParse.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson GSON = new Gson();

    public static String toJson(ParseTree tree){
        return toJson(tree,true);
    }

    public static String toJson(ParseTree tree,boolean prettyPrint)
    {
        return prettyPrint?PRETTY_PRINT_GSON.toJson(toMap(tree)) : GSON.toJson(toMap(tree));
    }

    public static Map<String,Object> toMap(ParseTree tree)
    {
        Map<String,Object> map = new LinkedHashMap<>();
        traverser(tree,map);
        return map;
    }
    public static void traverser(ParseTree tree,Map<String,Object> map){
        if(tree instanceof TerminalNodeImpl)
        {
            Token token = ((TerminalNodeImpl)tree).getSymbol();
            map.put("type",token.getType());
            map.put("value",token.getText());
            map.put("tokenID",token.getStartIndex());
        }
        else
        {
            List<Map<String,Object>> children = new ArrayList<>();
            String name = tree.getClass().getSimpleName().replaceAll("Context$","");
            map.put(Character.toLowerCase(name.charAt(0)) + name.substring(1),children);

            for(int i=0;i<tree.getChildCount();i++)
            {
                Map<String,Object> nested = new LinkedHashMap<>();
                children.add(nested);
                traverser(tree.getChild(i),nested);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String expr = "1";
        System.out.println(expr);
        //ANTLRInputStream in = new ANTLRInputStream(expr);
        CharStream in = CharStreams.fromString(expr);
        txjParseLexer lexer = new txjParseLexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        txjParseParser parser = new txjParseParser(tokenStream);
        ParseTree parseTree = parser.program();
        System.out.println(toJson(parseTree));
        /*CalculatorVisitor visitor = new CalculatorVisitor();
        int result = visitor.visit(parseTree);
        System.out.println("Visitor calculate result: "+result);*/
    }
}


