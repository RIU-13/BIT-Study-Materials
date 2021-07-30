package bit.minisys.minicc.semantic;

import bit.minisys.minicc.parser.ast.ASTCompilationUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import bit.minisys.minicc.semantic.txjVistor;
import java.io.File;
import java.io.IOException;

public class txjSemanticMain {
    public void run(String File) throws Exception {
        ObjectMapper mapper =new ObjectMapper();
        //System.out.println(iFile);
        ASTCompilationUnit program=(ASTCompilationUnit) mapper.readValue(new File(File), ASTCompilationUnit.class);
        System.out.println("Semantic doing");
        txjVistor vistor = new txjVistor();
        vistor.visit(program);
        System.out.println("TXJ's Semantic finished");
    }
}
