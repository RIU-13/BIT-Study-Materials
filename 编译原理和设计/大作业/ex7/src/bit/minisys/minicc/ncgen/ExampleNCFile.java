package bit.minisys.minicc.ncgen;

import bit.minisys.minicc.icgen.Quat;
import org.python.antlr.ast.Str;

import java.io.*;
import java.util.List;

public class ExampleNCFile {
    public List<String> toWriteCodes;
    //public List<String> readCodes;
    public void write(String filename){
        // write
        try {
            FileWriter fileWriter = new FileWriter(new File(filename));
            fileWriter.write(toWriteCodes.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
