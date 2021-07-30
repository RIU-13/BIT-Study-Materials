package bit.minisys.minicc.icgen;
import java.util.*;
import bit.minisys.minicc.parser.ast.*;



// 一个简单样例，只实现了加法

class Symbol {

	String name;//名字
	String kind;//种类，包括变量、常量、函数定义、数组和标号等
	String type;//int、double类型

	int link;   //上一个

	List<Symbol> params;

	int paraId;
	int lbId;
	int arrId;

	public void print()
    {
        System.out.printf("{name:%s;kind:%s;type:%s;link:%d}\n",name,kind,type,link);
    }

}
class BodySymbol {

	int lastPar; //形参最后id
	int last;   //变量id最后
	int pSize;  //总参数变量大小
	int vSize;  //总变量大小
}//函数体body

class DisplaySymbol{
	Stack<Integer> disTab; //层次关系
	DisplaySymbol(){
		disTab = new Stack<Integer>();
	}
}
class LabelSymbol{
	int id; //唯一标识
	int ir_id;//中间代码的label值
	boolean f;//定义性出现还是使用性出现
}
class ArrayTable{
	int id;        //唯一标识
	String elType; //元素种类
	int elRef;    //若是多维数组
	int size;     //数组大小
}


public class ExampleICBuilder implements ASTVisitor{

	private Map<ASTNode, ASTNode> map;				// 使用map存储子节点的返回值，key对应子节点，value对应返回值，value目前类别包括ASTIdentifier,ASTIntegerConstant,TemportaryValue...
	private List<Quat> quats;						// 生成的四元式列表
	private Integer tmpId;							// 临时变量编号

	Stack<Integer> false_num = new Stack<>();
	Stack<Integer> label_num = new Stack<>();
	Stack<Integer> step_num = new Stack<>();

	public List<Symbol> SymbolTables = new ArrayList<>();
	public DisplaySymbol displaySymbols = new DisplaySymbol();
	public List<LabelSymbol> labelSymbols = new ArrayList<>();
	//public List<ArrayTable> arrayTables = new ArrayList<>();
	public List<BodySymbol> bodySymbols = new ArrayList<>();

	Map<ASTNode, Symbol> mapSb = new HashMap<>();
	Map<String,String> mapType = new HashMap<>();
	Map<Symbol,Integer> mapGotolb = new HashMap<>();
	//Map<ASTArrayDeclarator,Integer> mapArrRef = new HashMap<>();

	boolean flag = false;
	public int fIter = 0;//对循环语句标记，看是否在循环语句里面
	public int freturn = 0;
	public int bodyId = -1;

	void printError(String errorstring,int errorid)
	{
		if(!flag)
		{
			System.out.println("errors:");
			System.out.println("------------------------------------");
			flag = true;
		}
		System.out.println("ES0"+errorid+" >> "+errorstring);
	}
	void printErrorEnd()
	{
		if(flag)
			System.out.println("------------------------------------");
	}
	boolean checkAllLabel(BodySymbol bs){
		//bs是函数定义的那部分
		if(labelSymbols.size()!=0)
		{
			int j = 0;
			int id = bs.last;
			while(j<bs.vSize){
				Symbol sb = SymbolTables.get(id);
				//sb.print();
				if(sb.kind == "GotoStatement")
				{
					LabelSymbol lsb = labelSymbols.get(sb.lbId);
					if(!lsb.f){
						printError("Label: "+sb.name+" is not defined.",7);
						return false;
					}
					return true;
				}

				j++;
				id = sb.link;
			}
			return true;
		}

		return true;
	}
	//有无重复定义变量
	boolean checkRepeat(Symbol sb)
	{
		int btable_num = displaySymbols.disTab.peek();
		//System.out.println("--"+btable_num);
		int j =0;
		int id = bodySymbols.get(btable_num).last;
//		System.out.println(id);
//		System.out.println(bodySymbols.get(btable_num).vSize);
		while(j<bodySymbols.get(btable_num).vSize)
		{
				Symbol temp_sb = SymbolTables.get(id);
				//temp_sb.print();
				boolean cond1 = sb.name.equals(temp_sb.name)&&sb.kind.equals(temp_sb.kind);
				boolean cond2 = sb.name.equals(temp_sb.name)&&sb.kind.equals("GotoStatement");
				boolean cond3 = sb.name.equals(temp_sb.name)&&sb.kind.equals("LabeledStatement");
				if(cond1||cond2||cond3){
					if(sb.kind == "FunctionDefine")
						//如果包含，则表示重复
						printError(temp_sb.kind+" "+sb.name+" is defined.",2);
					else if(temp_sb.kind == "LabeledStatement")
					{
						LabelSymbol ls = labelSymbols.get(temp_sb.lbId);
						if(ls.f)
						{
							//定义性出现
							printError("Label: "+temp_sb.name+" has been defined",1);
						}
						else //goto、未定义
						{
							mapGotolb.put(sb,ls.ir_id);
							ls.f = true;
							return false;
						}

					}
					else if(temp_sb.kind == "GotoStatement"){
						//之前已经出现过了
						LabelSymbol ls = labelSymbols.get(temp_sb.lbId);
						if(sb.kind == "LabeledStatement")
							ls.f = true;
						mapGotolb.put(sb,ls.ir_id);
						return false;
					}else
						printError(temp_sb.kind+" "+ sb.name + " has been declarated.",2);
					return false;
				}
				j++;
				id = temp_sb.link;
		}

		return true;
	}

	//判断参数类型是否一致
	boolean checkMatch(Symbol A, Symbol B)
	{
		if(!A.type.equals(B.type))
			return false;
		else
			return true;
	}
	boolean checkIn(Symbol sb) {
//		sb.print();
		Symbol sb_res = new Symbol();
		Stack<Integer> temp_s = new Stack<>();
		BodySymbol btable_res = new BodySymbol();
		boolean res = true;
		boolean f = false;
		int ksize = displaySymbols.disTab.size();
		for (int i = 0; i < ksize; i++) {
			int btable_num = displaySymbols.disTab.pop();
			//System.out.println(btable_num);
			temp_s.push(btable_num);
			int j = 0;
			int id = bodySymbols.get(btable_num).last;
			while (j < bodySymbols.get(btable_num).vSize) {
				Symbol sb_ed = SymbolTables.get(id);
				//sb_ed.print();
				//如果变量名相等
				if (sb.name.equals(sb_ed.name)) {
					f = true;
					sb_res = sb_ed;
					break;
				}
				id = sb_ed.link;
				j++;
			}

			if (f) {
				btable_res = bodySymbols.get(btable_num);
				break;
			}

		}
		if (f) {
			//sb.print();
			//找到了，看匹配
			if (sb.kind == "FunctionCall") {
				btable_res = bodySymbols.get(sb_res.paraId);
				//先判断参数长度
				//sb_res.print();
				if (btable_res.pSize != sb.params.size()) {
					printError(sb.kind + ":" + sb.name + "'s" + " param num is not matched.", 4);
					res =  false;
				}
				else{
					//System.out.println(temp.param.size());
					//再判断参数类型是否匹配

					int j = 0;
					int id = btable_res.lastPar;

					while (j < btable_res.pSize) {
						Symbol temp_sb = SymbolTables.get(id);
						if (!checkMatch(temp_sb, sb.params.get(j))) {
							printError(sb.kind + ":" + sb.name + "'s" + " param type is not matched.", 4);
							res = false;
							break;
						}
						j++;
						id = temp_sb.link;
					}
				}

			}else if(sb.kind == "GotoStatement"){
				mapGotolb.put(sb,labelSymbols.get(sb_res.lbId).ir_id);
			}
			res = true;


		}else if(sb.kind == "GotoStatement"){
			//没有检查到
			res = false;

		}
		else {
			if (sb.kind == "FunctionCall")
				printError(sb.kind + " " + sb.name + " is not declarated.", 1);
			else
				printError(sb.kind + " " + sb.name + " is not defined.", 1);
			res =  false;
		}

		//再恢复displaytable
		ksize = temp_s.size();
		for(int i=0;i<ksize;i++)
		{
			displaySymbols.disTab.push(temp_s.pop());
		}
		mapType.put(sb.name,sb_res.type);
		return res;
	}

	public ExampleICBuilder() {
		map = new HashMap<ASTNode, ASTNode>();
		quats = new LinkedList<Quat>();
		tmpId = 0;
	}
	public List<Quat> getQuats() {
		return quats;
	}

	@Override
	public void visit(ASTCompilationUnit program) throws Exception {

		displaySymbols.disTab.push(0);
		BodySymbol bodySymbol = new BodySymbol();
		bodySymbol.last = bodySymbol.lastPar = -1;
	    bodySymbol.pSize = bodySymbol.vSize = 0;
		bodySymbols.add(bodySymbol);

		for (ASTNode node : program.items) {
			if(node instanceof ASTFunctionDefine)
				visit((ASTFunctionDefine)node);
			else if(node instanceof ASTDeclaration)
				visit((ASTDeclaration) node);
		}
		printErrorEnd();
	}

	@Override
	public void visit(ASTDeclaration declaration) throws Exception {
		// TODO Auto-generated method stub

		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());
		//System.out.println(displaySymbols.disTab.peek());
		Symbol sb = new Symbol();
		sb.type = "";
		for(int i=0;i<declaration.specifiers.size();i++)
		{
			visit(declaration.specifiers.get(i));
			sb.type += mapSb.get(declaration.specifiers.get(i)).type+" ";
		}

		for(int i=0;i<declaration.initLists.size();i++)
		{
			visit(declaration.initLists.get(i));
			Symbol sb_res = mapSb.get(declaration.initLists.get(i));
			sb_res.type = sb.type;
			sb_res.link = bs.last;
			//判断有无重复
			if(checkRepeat(sb_res)){
				//sb_res.print();
				SymbolTables.add(sb_res);
				bs.vSize++;
				bs.last = SymbolTables.size()-1;
				//System.out.println(bs.last);
			}
		}

	}

	@Override
	public void visit(ASTArrayDeclarator arrayDeclarator) throws Exception {
		// TODO Auto-generated method stub
		visit(arrayDeclarator.declarator);
		Symbol sb = mapSb.get(arrayDeclarator.declarator);
		sb.kind = arrayDeclarator.getType();
		mapSb.put(arrayDeclarator,sb);
//		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());
//		Symbol sb = new Symbol();
//		sb.type = arrayDeclarator.getType();
//		visit(arrayDeclarator.declarator);
//		if(arrayDeclarator.declarator instanceof ASTArrayDeclarator){
//			sb.arrId = mapArrRef.get(arrayDeclarator);
//		}
//		else if(arrayDeclarator.declarator instanceof ASTVariableDeclarator){
//			sb.name = mapSb.get(arrayDeclarator.declarator).name;
//			if(checkRepeat(sb)){
//				sb.arrId = -1;
//				//没有重复
//				//添加到符号表中
//				SymbolTables.add(sb);
//				bs.last = SymbolTables.size()-1;
//				bs.vSize++;
//
//				ArrayTable arrayTable = new ArrayTable();
//				arrayTable.elRef = -1;
//				visit(arrayDeclarator.expr);
//				arrayTable.size =
//				mapArrRef.put(arrayDeclarator,sb.arrId);
//			}
//
//
//		}
//


	}

	@Override
	public void visit(ASTVariableDeclarator variableDeclarator) throws Exception {
		// TODO Auto-generated method stub

		map.put(variableDeclarator,variableDeclarator.identifier);

		//获得符号
		visit(variableDeclarator.identifier);
		Symbol symbol = mapSb.get(variableDeclarator.identifier);
		mapSb.put(variableDeclarator,symbol);
	}

	@Override
	public void visit(ASTFunctionDeclarator functionDeclarator) throws Exception {
		// TODO Auto-generated method stub
		//map.put(functionDeclarator,functionDeclarator.declarator);
		visit(functionDeclarator.declarator);
		Symbol sb = mapSb.get(functionDeclarator.declarator);
		sb.kind = functionDeclarator.getType();
		mapSb.put(functionDeclarator,sb);
		if(functionDeclarator.params!=null)
		{
			for(int i=functionDeclarator.params.size()-1;i>=0;i--)
			{
				visit(functionDeclarator.params.get(i));
			}
		}

	}

	@Override
	public void visit(ASTParamsDeclarator paramsDeclarator) throws Exception {
		// TODO Auto-generated method stub
		//fill the symbol ??
		//形式参数
		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());

		Symbol sb = new Symbol();
		sb.type = "";
		for(int i=0;i<paramsDeclarator.specfiers.size();i++)
		{
			visit(paramsDeclarator.specfiers.get(i));
			sb.type += mapSb.get(paramsDeclarator.specfiers.get(i)).type+" ";
		}
		visit(paramsDeclarator.declarator);
		sb.name = mapSb.get(paramsDeclarator.declarator).name;
		sb.kind = paramsDeclarator.declarator.getType();
		sb.link = bs.lastPar;

		if(checkRepeat(sb))
		{
			//sb.print();
			SymbolTables.add(sb);
			bs.lastPar = SymbolTables.size()-1;
			bs.pSize++;
			bs.last = SymbolTables.size()-1;
			bs.vSize++;
		}

		//ir 生成
		String op = "pop";
		visit(paramsDeclarator.declarator);
		ASTNode res = map.get(paramsDeclarator.declarator);
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);


	}

	@Override
	public void visit(ASTArrayAccess arrayAccess) throws Exception {
		// TODO Auto-generated method stub
		String op = "[]";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;

		res = new TemporaryValue(++tmpId);
		visit(arrayAccess.arrayName);
		opnd1 = map.get(arrayAccess.arrayName);
		for(int i=0;i<arrayAccess.elements.size();i++)
		{
			visit(arrayAccess.elements.get(i));
			opnd2 = map.get(arrayAccess.elements.get(i));
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);
		}
		map.put(arrayAccess,res);
	}

	public void visitSb(ASTExpression expression) throws Exception{
		if(expression instanceof ASTIdentifier){
			Symbol sb = new Symbol();
			visit((ASTIdentifier) expression);
			sb.name = mapSb.get(expression).name;
			sb.kind = expression.getType();
			checkIn(sb);
			sb.type = mapType.get(sb.name);
			mapSb.put(expression,sb);
			//sb.print();
		}
		else if(expression instanceof ASTBinaryExpression) {
			visitSb(((ASTBinaryExpression) expression).expr1);
			visitSb(((ASTBinaryExpression) expression).expr2);
		}
		else if(expression instanceof ASTCastExpression){
			visitSb(((ASTCastExpression) expression).expr);
		}
		else if(expression instanceof ASTUnaryExpression){
			visitSb(((ASTUnaryExpression) expression).expr );
		}

		else if(expression instanceof ASTPostfixExpression){
			visitSb(((ASTPostfixExpression) expression).expr);
		}


	}

	@Override
	public void visit(ASTBinaryExpression binaryExpression) throws Exception {
		visitSb(binaryExpression);
		Symbol sb = mapSb.get(binaryExpression);
		//sb.print();
		mapSb.put(binaryExpression,sb);
		String op = binaryExpression.op.value;
		String numType1="";
		if(binaryExpression.expr1 instanceof ASTIdentifier)
		{
			//取出表达式左值类型
			ASTIdentifier identifier = (ASTIdentifier) binaryExpression.expr1;
			numType1 = mapType.get(identifier.value);
			if(numType1=="")
				return ;
		}
		else if(binaryExpression.expr1 instanceof ASTIntegerConstant)
		{
			numType1 = "int";
		}
		else if(binaryExpression.expr1 instanceof ASTFloatConstant)
		{
			numType1 = "float";
		}
		String numType2="" ;
		if(binaryExpression.expr2 instanceof ASTIdentifier)
		{
			//取出表达式左值类型
			ASTIdentifier identifier = (ASTIdentifier) binaryExpression.expr2;
			numType2 = mapType.get(identifier.value);
			if(numType2=="")
				return ;
		}
		else if(binaryExpression.expr2 instanceof ASTIntegerConstant)
		{
			numType2 = "int";
		}
		else if(binaryExpression.expr2 instanceof ASTFloatConstant)
		{

			numType2 = "float";
		}
		switch (op)
		{
			case "|":
			case "&":
			case "^":
			case "<<":
			case ">>":
				if(numType1.equals("float ")||numType2.equals("float ")||numType1.equals("double ")||numType2.equals("double "))
				{
					printError("BinaryExpression:(<< >> & | ^)expression's should be int.",5);
				}
				break;
			default:
		}

		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		String[] bin_ops = {"+","-","*","/","%","<<",">>","<",">","<=",">=","&&","||","=="};
		String[] un_ops = {"++","--","sizeof","~","!","-",};
		String[] ass_ops = {"=","*=","/=","%=","+=","-=","<<=",">>=","&=","^=","|="};

		if (Arrays.asList(ass_ops).contains(op)) {
			// 赋值操作
			// 获取被赋值的对象res
			visit(binaryExpression.expr1);

			res = map.get(binaryExpression.expr1);
			// 判断源操作数类型, 为了避免出现a = b + c; 生成两个四元式：tmp1 = b + c; a = tmp1;的情况。也可以用别的方法解决
			if (binaryExpression.expr2 instanceof ASTIdentifier) {
				opnd1 = binaryExpression.expr2;
			}else if(binaryExpression.expr2 instanceof ASTIntegerConstant) {
				opnd1 = binaryExpression.expr2;
			}else if(binaryExpression.expr2 instanceof ASTBinaryExpression) {
				ASTBinaryExpression value = (ASTBinaryExpression)binaryExpression.expr2;
				op = value.op.value;
				visit(value.expr1);
				opnd1 = map.get(value.expr1);
				visit(value.expr2);
				if(value.expr2 instanceof ASTIdentifier){

				}

				//sb.print();
				opnd2 = map.get(value.expr2);
			}else if(binaryExpression.expr2 instanceof ASTUnaryExpression){
				ASTUnaryExpression value = (ASTUnaryExpression) binaryExpression.expr2;
				op = value.op.value;
				opnd1 = null;
				visit(value.expr);

				opnd2 = map.get(value.expr);
			}else if(binaryExpression.expr2 instanceof ASTPostfixExpression){
				ASTPostfixExpression value = (ASTPostfixExpression) binaryExpression.expr2;
				op = value.op.value;
				visit(value.expr);

				opnd1 = map.get(value.expr);
				opnd2 = null;
			}else if(binaryExpression.expr2 instanceof ASTFunctionCall){
				visit(binaryExpression.expr2);
				opnd1 = map.get(binaryExpression.expr2);
			}else if(binaryExpression.expr2 instanceof ASTArrayAccess){
				visit(binaryExpression.expr2);
				opnd1 = map.get(binaryExpression.expr2);
			}
			
		}else if (Arrays.asList(bin_ops).contains(op)) {
			// 加法操作，结果存储到中间变量
			res = new TemporaryValue(++tmpId);
			visit(binaryExpression.expr1);

			opnd1 = map.get(binaryExpression.expr1);
			visit(binaryExpression.expr2);

			opnd2 = map.get(binaryExpression.expr2);
		}else if(Arrays.asList(un_ops).contains(op)) {
			// 一元运算
			res = new TemporaryValue(++tmpId);
			visit(binaryExpression.expr1);

			opnd1 = map.get(binaryExpression.expr1);
			visit(binaryExpression.expr2);

			opnd2 = map.get(binaryExpression.expr2);
		}
//		}else if(Arrays.asList(ass_ops).contains(op)){
//			//分成两步骤来算
//			res = new TemporaryValue(++tmpId);
//			visit(binaryExpression.expr2);
//		}
		
		// build quat
		Quat quat = new Quat(op, res, opnd1, opnd2);
		quats.add(quat);
		map.put(binaryExpression, res);
	}

	@Override
	public void visit(ASTBreakStatement breakStat) throws Exception {
		// TODO Auto-generated method stub
		if(fIter<=0)
		{
			//说明break没有在循环体里面
			printError("BreakStatement:must be in a LoopStatement.",3);
		}


		if(!false_num.empty())
		{
			String op = "jmp";
			int num = false_num.pop();
			ASTNode res = new TemporaryValue(num);
			false_num.push(num);
			ASTNode opnd1 = null;
			ASTNode opnd2 = null;
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);
		}

	}

	@Override
	public void visit(ASTContinueStatement continueStatement) throws Exception {
		// TODO Auto-generated method stub
		if(fIter<=0)
		{
			//说明break没有在循环体里面
			printError("BreakStatement:must be in a LoopStatement.",3);
		}
		else{
			String op = "jmp";

			ASTNode res = new TemporaryValue(++tmpId);
			step_num.push(tmpId);
			ASTNode opnd1 = null;
			ASTNode opnd2 = null;
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);
		}

	}

	@Override
	public void visit(ASTCastExpression castExpression) throws Exception {
		// TODO Auto-generated method stub
		visit(castExpression.typename);
	}

	@Override
	public void visit(ASTCharConstant charConst) throws Exception {
		// TODO Auto-generated method stub
		map.put(charConst,charConst);

		Symbol sb = new Symbol();
		sb.type = "char ";
		mapSb.put(charConst,sb);
	}

	@Override
	public void visit(ASTCompoundStatement compoundStat) throws Exception {

		for (ASTNode node : compoundStat.blockItems) {
			if(node instanceof ASTDeclaration) {
				visit((ASTDeclaration)node);
			}else if (node instanceof ASTStatement) {
				visit((ASTStatement)node);
			}
		}
		
	}
	public void visitCom(ASTCompoundStatement compoundStat) throws Exception {
		displaySymbols.disTab.push(bodySymbols.size());
		BodySymbol bs = new BodySymbol();
		bs.lastPar = bs.last = -1;
		bs.pSize = bs.vSize = 0;
		bodySymbols.add(bs);

		for (ASTNode node : compoundStat.blockItems) {
			if(node instanceof ASTDeclaration) {
				visit((ASTDeclaration)node);
			}else if (node instanceof ASTStatement) {
				visit((ASTStatement)node);
			}
		}

		displaySymbols.disTab.pop();
	}
	@Override
	public void visit(ASTConditionExpression conditionExpression) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTExpression expression) throws Exception {

		if(expression instanceof ASTArrayAccess) {
			//已完成
			visit((ASTArrayAccess)expression);
		}else if(expression instanceof ASTBinaryExpression) {
			//已完成
			visit((ASTBinaryExpression)expression);

		}else if(expression instanceof ASTCastExpression) {

			visit((ASTCastExpression)expression);
		}else if(expression instanceof ASTCharConstant) {
			//已完成
			visit((ASTCharConstant)expression);
		}else if(expression instanceof ASTConditionExpression) {
			visit((ASTConditionExpression)expression);
		}else if(expression instanceof ASTFloatConstant) {
			//已完成
			visit((ASTFloatConstant)expression);
		}else if(expression instanceof ASTFunctionCall) {
			//已完成
			visit((ASTFunctionCall)expression);
		}else if(expression instanceof ASTIdentifier) {
			//已完成
			visit((ASTIdentifier)expression);
		}else if(expression instanceof ASTIntegerConstant) {
			//已完成
			visit((ASTIntegerConstant)expression);
		}else if(expression instanceof ASTMemberAccess) {

			visit((ASTMemberAccess)expression);
		}else if(expression instanceof ASTPostfixExpression) {
			//已完成
			visit((ASTPostfixExpression)expression);
		}else if(expression instanceof ASTStringConstant) {
			//已完成
			visit((ASTStringConstant)expression);
		}else if(expression instanceof ASTUnaryExpression) {
			//已完成
			visit((ASTUnaryExpression)expression);
		}else if(expression instanceof ASTUnaryTypename){
			visit((ASTUnaryTypename)expression);
		}
	}

	@Override
	public void visit(ASTExpressionStatement expressionStat) throws Exception {
		for (ASTExpression node : expressionStat.exprs) {
			visit((ASTExpression)node);
		}
	}

	@Override
	public void visit(ASTFloatConstant floatConst) throws Exception {
		// TODO Auto-generated method stub
		map.put(floatConst,floatConst);
		Symbol sb = new Symbol();
		sb.type = "float ";
		mapSb.put(floatConst,sb);
	}

	@Override
	public void visit(ASTFunctionCall funcCall) throws Exception {
		// TODO Auto-generated method stub
		//函数调用先压栈
		String op = "push";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;

		Symbol sb = new Symbol();
		sb.params = new ArrayList<>();

		//System.out.println("functioncall");
		for(int i=0;i<funcCall.argList.size();i++)
		{
			Symbol sb_type = new Symbol();
			//System.out.println(funcCall.argList.get(i).getType());
			visit(funcCall.argList.get(i));
			if(mapSb.get(funcCall.argList.get(i))!=null)
			{

				sb_type.name = mapSb.get(funcCall.argList.get(i)).name;
				sb_type.kind = mapSb.get(funcCall.argList.get(i)).kind;
				sb_type.type = mapSb.get(funcCall.argList.get(i)).type;
				if(sb_type.name != null)
					checkIn(sb_type);
				sb_type.type = mapType.get(sb_type.name);
				sb.params.add(sb_type);

			}
			res = map.get(funcCall.argList.get(i));
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);

		}
		op = "call";

		visit(funcCall.funcname);

		sb.name = mapSb.get(funcCall.funcname).name;
		sb.kind = funcCall.getType();
		//sb.params.get(0).print();
		checkIn(sb);

		opnd1 = map.get(funcCall.funcname);
		res = new TemporaryValue(++tmpId);
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);

		map.put(funcCall,res);

	}

	@Override
	public void visit(ASTGotoStatement gotoStat) throws Exception {
		// TODO Auto-generated method stub
		String op = "jmp";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;


		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());
		Symbol sb = new Symbol();
		sb.name = gotoStat.label.value;
		sb.kind = gotoStat.getType();
		sb.link = bs.last;


		if(checkIn(sb)){

			//存在，则是定义性出现
			res = new TemporaryValue(mapGotolb.get(sb));

		}else{
			res = new TemporaryValue(++tmpId);

			sb.lbId = labelSymbols.size();

			SymbolTables.add(sb);
			bs.last = SymbolTables.size()-1;
			bs.vSize++;

			LabelSymbol ls = new LabelSymbol();
			ls.id = bs.last;
			ls.ir_id = tmpId;
			ls.f = false;//使用性出现，没有定义
			labelSymbols.add(ls);
		}

		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
	}

	@Override
	public void visit(ASTIdentifier identifier) throws Exception {

		map.put(identifier, identifier);

		//放符号表
		Symbol symbol = new Symbol();
		symbol.name = identifier.value;
		mapSb.put(identifier,symbol);
	}

	@Override
	public void visit(ASTInitList initList) throws Exception {
		// TODO Auto-generated method stub
		String op = "=";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		visit(initList.declarator);
		res = map.get(initList.declarator);
		if(initList.exprs.size()!=0)
		{
			for(int i=0;i<initList.exprs.size();i++)
			{
				visit(initList.exprs.get(i));
				opnd1 = map.get(initList.exprs.get(i));
			}
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);
		}

		//获得符号名
		Symbol sb = new Symbol();
		visit(initList.declarator);
		sb.name = mapSb.get(initList.declarator).name;
		sb.kind = initList.declarator.getType();
		mapSb.put(initList,sb);

	}

	@Override
	public void visit(ASTIntegerConstant intConst) throws Exception {
		map.put(intConst, intConst);
		Symbol sb = new Symbol();
		sb.type = "int ";
		mapSb.put(intConst,sb);
	}

	@Override
	public void visit(ASTIterationDeclaredStatement iterationDeclaredStat) throws Exception {
		// TODO Auto-generated method stub

		//fill the table

		displaySymbols.disTab.push(bodySymbols.size());
		BodySymbol bs = new BodySymbol();
		bs.lastPar = bs.last = -1;
		bs.pSize = bs.vSize = 0;
		bodySymbols.add(bs);

		visit(iterationDeclaredStat.init);


		String op = "label";
		ASTNode res = new TemporaryValue(++tmpId);
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		label_num.push(tmpId);
		//循环用的标号
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		false_num.push(++tmpId);
		if(iterationDeclaredStat.cond!=null){
			for(int i=0;i<iterationDeclaredStat.cond.size();i++)
			{
				visit(iterationDeclaredStat.cond.get(i));
				//如果是否定，跳出循环
				op = "jf";
				int fnum = false_num.pop();
				res = new TemporaryValue(fnum);
				false_num.push(fnum);
				opnd1 = map.get(iterationDeclaredStat.cond.get(i));
				Quat quat2 = new Quat(op,res,opnd1,opnd2);
				quats.add(quat2);
			}
		}

		fIter ++;
		//访问循环体内部
		if(iterationDeclaredStat.stat instanceof ASTCompoundStatement){
			visit((ASTCompoundStatement) iterationDeclaredStat.stat);
		}else{
			visit(iterationDeclaredStat.stat);
		}
		fIter--;

		//step
		if(iterationDeclaredStat.step!=null)
		{
			if(!step_num.empty())
			{
				op = "label";
				int num = step_num.peek();
				res = new TemporaryValue(num);

				opnd1 = opnd2 = null;
				Quat quat2 = new Quat(op,res,opnd1,opnd2);
				quats.add(quat2);

			}

			for(int i=0;i<iterationDeclaredStat.step.size();i++)
			{
				visit(iterationDeclaredStat.step.get(i));
			}
		}

		op = "jmp";
		int lnum = label_num.pop();
		res = new TemporaryValue(lnum);
		opnd1 = opnd2 = null;
		Quat quat1 = new Quat(op,res,opnd1,opnd2);
		quats.add(quat1);

		op = "label";
		int fnum = false_num.pop();
		res = new TemporaryValue(fnum);
		opnd1 = opnd2 = null;
		Quat quat3 = new Quat(op,res,opnd1,opnd2);
		quats.add(quat3);

		displaySymbols.disTab.pop();

	}

	@Override
	public void visit(ASTIterationStatement iterationStat) throws Exception {
		// TODO Auto-generated method stub

		String op = "";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		if(iterationStat.init!=null)
		{
			for(int i=0;i<iterationStat.init.size();i++)
			{
				visit(iterationStat.init.get(i));
			}

		}
		op = "label";
		res = new TemporaryValue(++tmpId);
		opnd1 = null;
		opnd2 = null;
		label_num.push(tmpId);
		//循环用的标号
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		false_num.push(++tmpId);

		if(iterationStat.cond!=null)
		{
			for(int i=0;i<iterationStat.cond.size();i++)
			{
				visit(iterationStat.cond.get(i));
				//如果是否定，跳出循环
				op = "jf";
				int fnum = false_num.pop();
				res = new TemporaryValue(fnum);
				false_num.push(fnum);
				opnd1 = map.get(iterationStat.cond.get(i));
				Quat quat2 = new Quat(op,res,opnd1,opnd2);
				quats.add(quat2);
			}

		}

		//访问循环体内部
		fIter++;
		//访问循环体内部

			visit(iterationStat.stat);

		fIter--;

		if(iterationStat.step!=null)
		{
			//step
			if(!step_num.empty())
			{
				op = "label";
				int num = step_num.peek();
				res = new TemporaryValue(num);

				opnd1 = opnd2 = null;
				Quat quat2 = new Quat(op,res,opnd1,opnd2);
				quats.add(quat2);

			}

			for(int i=0;i<iterationStat.step.size();i++)
			{
				visit(iterationStat.step.get(i));
			}

		}
		op = "jmp";
		int lnum = label_num.pop();
		res = new TemporaryValue(lnum);
		opnd1 = opnd2 = null;
		Quat quat1 = new Quat(op,res,opnd1,opnd2);
		quats.add(quat1);

		op = "label";
		int fnum = false_num.pop();
		res = new TemporaryValue(fnum);
		opnd1 = opnd2 = null;
		Quat quat3 = new Quat(op,res,opnd1,opnd2);
		quats.add(quat3);
		//displaySymbols.disTab.pop();

	}

	@Override
	public void visit(ASTLabeledStatement labeledStat) throws Exception {
		// TODO Auto-generated method stub


		String op = "label";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;



		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());
		Symbol sb = new Symbol();
		sb.name = labeledStat.label.value;
		sb.kind = labeledStat.getType();
		sb.link = bs.last;

		if(checkRepeat(sb))
		{
			res = new TemporaryValue(++tmpId);

			sb.lbId = labelSymbols.size();
			SymbolTables.add(sb);
			bs.last = SymbolTables.size()-1;
			bs.vSize++;

			LabelSymbol ls = new LabelSymbol();
			ls.id = bs.last;
			ls.ir_id = tmpId;
			ls.f = true;//定义性出现
			labelSymbols.add(ls);

		}else {
			res = new TemporaryValue(mapGotolb.get(sb));
//			LabelSymbol ls = labelSymbols.get(sb.lbId);
//			ls.f = true;
		}
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		map.put(labeledStat,opnd1);
		visit(labeledStat.stat);

	}

	@Override
	public void visit(ASTMemberAccess memberAccess) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTPostfixExpression postfixExpression) throws Exception {
		// TODO Auto-generated method stub
		visitSb(postfixExpression);
		String op = postfixExpression.op.value;
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;

		visit(postfixExpression.expr);
		Symbol sb = new Symbol();
		sb.name = mapSb.get(postfixExpression.expr).name;
		sb.kind = postfixExpression.expr.getType();


		res = new TemporaryValue(++tmpId);

		if(postfixExpression.expr instanceof ASTIdentifier){
			opnd1 = postfixExpression.expr;
		}
		else if(postfixExpression.expr instanceof ASTIntegerConstant){
			opnd1 = postfixExpression.expr;
		}
		else if(postfixExpression.expr instanceof ASTFloatConstant){
			opnd1 = postfixExpression.expr;
		}


		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		map.put(postfixExpression,res);


	}

	@Override
	public void visit(ASTReturnStatement returnStat) throws Exception {
		// TODO Auto-generated method stub
		freturn ++;

		String op = "ret";
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		if(returnStat.expr!=null)
		{
			for(int i=0;i<returnStat.expr.size();i++){
				visit(returnStat.expr.get(i));
				opnd1 = map.get(returnStat.expr.get(i));
			}
		}

		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
	}

	@Override
	public void visit(ASTSelectionStatement selectionStat) throws Exception {
		// TODO Auto-generated method stub
		//选择语句
		int num = 0;
		Quat quat2 = new Quat(null,null,null,null);
		for(int i=0;i<selectionStat.cond.size();i++)
		{
			visit(selectionStat.cond.get(i));
			//增加条件语句
			String op = "jf";
			ASTNode opnd1 = map.get(selectionStat.cond.get(i));
			ASTNode opnd2 = null;
			ASTNode res = new TemporaryValue(++tmpId);
			Quat quat = new Quat(op,res,opnd1,opnd2);
			quats.add(quat);

			//否定的跳转label
			op = "label";
			res = new TemporaryValue(tmpId);
			num = tmpId;
			opnd1 = null;
			opnd2 = null;
			quat2 = new Quat(op,res,opnd1,opnd2);

		}

		visit(selectionStat.then);

		if(selectionStat.otherwise!=null) {
			String op = "jmp";
			ASTNode opnd1 = null;
			ASTNode opnd2 = null;
			ASTNode res = new TemporaryValue(++tmpId);
			num = tmpId;
			Quat quat = new Quat(op, res, opnd1, opnd2);
			quats.add(quat);

		}
		quats.add(quat2);
		if(selectionStat.otherwise!=null) {
			visit(selectionStat.otherwise);
			String op = "label";
			ASTNode res = new TemporaryValue(num);
			ASTNode opnd1 = null;
			ASTNode opnd2 = null;
			Quat quat3 = new Quat(op, res, opnd1, opnd2);
			quats.add(quat3);
		}

	}

	@Override
	public void visit(ASTStringConstant stringConst) throws Exception {
		// TODO Auto-generated method stub
		map.put(stringConst,stringConst);
//		System.out.println(stringConst.value);

	}

	@Override
	public void visit(ASTTypename typename) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTUnaryExpression unaryExpression) throws Exception {
		// TODO Auto-generated method stub
		visitSb(unaryExpression);

		String op = unaryExpression.op.value;
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		visit(unaryExpression.expr);
		Symbol sb = new Symbol();
		sb.name = mapSb.get(unaryExpression.expr).name;
		sb.kind = unaryExpression.expr.getType();
		checkIn(sb);
		res = new TemporaryValue(++tmpId);

		if(unaryExpression.expr instanceof ASTIdentifier){
			opnd2 = unaryExpression.expr;
		}
		else if(unaryExpression.expr instanceof ASTIntegerConstant){
			opnd2 = unaryExpression.expr;
		}
		else if(unaryExpression.expr instanceof ASTFloatConstant){
			opnd2 = unaryExpression.expr;
		}


		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		map.put(unaryExpression,res);

	}

	@Override
	public void visit(ASTUnaryTypename unaryTypename) throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void visit(ASTFunctionDefine functionDefine) throws Exception {
		//fill the symbol table?
		BodySymbol bs = bodySymbols.get(displaySymbols.disTab.peek());
		Symbol symbol = new Symbol();
		symbol.link = bs.last;
		symbol.kind = functionDefine.getType();
		symbol.paraId = bodySymbols.size();
		for(int i=0;i<functionDefine.specifiers.size();i++)
		{
			visit(functionDefine.specifiers.get(i));
			symbol.type = mapSb.get(functionDefine.specifiers.get(i)).type+" ";
		}

		//函数定义
		String op = "proc";
		ASTNode res = null;
		ASTFunctionDeclarator fundec = ((ASTFunctionDeclarator)functionDefine.declarator);

		visit((ASTVariableDeclarator)fundec.declarator);

		symbol.name = mapSb.get((ASTVariableDeclarator)fundec.declarator).name;

		res = map.get((ASTVariableDeclarator)fundec.declarator);
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		Quat quat = new Quat(op,res,opnd1,opnd2);
		quats.add(quat);
		//check
		if(checkRepeat(symbol))
		{
			SymbolTables.add(symbol);
			//修改bodytable
			bs.last = SymbolTables.size()-1;
			bs.vSize++;
		}

		//symbol.print();

		//主要是得到形式参数

		//这里开始第二层
		displaySymbols.disTab.push(bodySymbols.size());

		BodySymbol bs2 = new BodySymbol();
		bs2.lastPar = bs2.last = -1;
		bs2.pSize = bs2.vSize = 0;
		bodySymbols.add(bs2);

		visit(functionDefine.declarator);

		visit((ASTCompoundStatement) functionDefine.body);
		checkAllLabel(bs2);
		op = "endp";
		opnd1 = null;
		opnd2 = null;

		Quat quat1 = new Quat(op,res,opnd1,opnd2);
		quats.add(quat1);

		displaySymbols.disTab.pop();
		if(freturn<=0)
		{
			printError("Function: "+symbol.name+" must have a return in the end.",8);
		}
	}

	@Override
	public void visit(ASTDeclarator declarator) throws Exception {
		// TODO Auto-generated method stub
		if(declarator instanceof ASTVariableDeclarator){
			//已完成
			visit((ASTVariableDeclarator) declarator);
		}else if(declarator instanceof ASTArrayDeclarator){
			//已完成
			visit((ASTArrayDeclarator) declarator);
		}else if(declarator instanceof ASTFunctionDeclarator){
			//已完成
			visit((ASTFunctionDeclarator) declarator);
		}
	}

	@Override
	public void visit(ASTStatement statement) throws Exception {
		if(statement instanceof ASTIterationDeclaredStatement) {
			//已完成
			visit((ASTIterationDeclaredStatement)statement);
		}else if(statement instanceof ASTIterationStatement) {
			//已完成
			visit((ASTIterationStatement)statement);
		}else if(statement instanceof ASTCompoundStatement) {
			//已完成
			visitCom((ASTCompoundStatement)statement);
		}else if(statement instanceof ASTSelectionStatement) {
			//已完成
			visit((ASTSelectionStatement)statement);
		}else if(statement instanceof ASTExpressionStatement) {
			//已完成
			visit((ASTExpressionStatement)statement);
		}else if(statement instanceof ASTBreakStatement) {
			//已完成
			visit((ASTBreakStatement)statement);
		}else if(statement instanceof ASTContinueStatement) {
			//已完成
			visit((ASTContinueStatement)statement);
		}else if(statement instanceof ASTReturnStatement) {
			//已完成
			visit((ASTReturnStatement)statement);
		}else if(statement instanceof ASTGotoStatement) {

			visit((ASTGotoStatement)statement);
		}else if(statement instanceof ASTLabeledStatement) {

			visit((ASTLabeledStatement)statement);
		}
	}

	@Override
	public void visit(ASTToken token) throws Exception {
		// TODO Auto-generated method stub
		Symbol sb = new Symbol();
		sb.type = token.value;
		mapSb.put(token,sb);
	}

}
