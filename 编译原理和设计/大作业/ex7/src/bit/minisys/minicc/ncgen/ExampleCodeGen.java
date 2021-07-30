package bit.minisys.minicc.ncgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import bit.minisys.minicc.MiniCCCfg;
import bit.minisys.minicc.icgen.internal.IRBuilder;
import bit.minisys.minicc.icgen.internal.MiniCCICGen;
import bit.minisys.minicc.internal.util.MiniCCUtil;
import bit.minisys.minicc.ncgen.IMiniCCCodeGen;


public class ExampleCodeGen implements IMiniCCCodeGen{


	public ExampleCodeGen() {
		
	}
	
	@Override
	public String run(String iFile, MiniCCCfg cfg) throws Exception {
		String oFile = MiniCCUtil.remove2Ext(iFile) + MiniCCCfg.MINICC_CODEGEN_OUTPUT_EXT;

		if(cfg.target.equals("mips")) {
			//TODO:
		}else if (cfg.target.equals("riscv")) {
			//TODO:
		}else if (cfg.target.equals("x86")){
			//TODO:
			//System.out.println(iFile);
			TXJCodeGen myCodeGen = new TXJCodeGen();
			myCodeGen.run(iFile,oFile);
			System.out.println(oFile);
		}

		System.out.println("7. TXJ Target code generation finished!");
		
		return oFile;
	}
}
