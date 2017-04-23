import java.util.*;

public class Listener extends junkBaseListener {

	SymbolTable s;
	IRBuilder ir;
	Symbol symbol;
	boolean newTable, newTableHeader, programHeader = false;
	String variableType,
	       variableValue;
	ArrayList<String> variableName = new ArrayList<>();
	int block = 1;

	@Override
	public void enterProgram(junkParser.ProgramContext ctx) {
//		System.out.println("enterProgram...");
		s = new SymbolTable(null, null);
		ir = new IRBuilder(s);
		s.setName("GLOBAL");
		programHeader = true;
        }

	@Override
	public void exitProgram(junkParser.ProgramContext ctx) {
//		System.out.println("Exiting Program...");
		ir.endProgram();
		//callTinyCodeConverter
		//s.printAll();
        }

	@Override
	public void enterFunc_decl(junkParser.Func_declContext ctx) {
//		System.out.println("enterFunc...");
		pushSymbolTable();
		newTable = true;
       		newTableHeader = true;
		//todo: allow for multiple function declarations
		ir.enterMain();
	}

	@Override
	public void enterFunc_body(junkParser.Func_bodyContext ctx) {
//		System.out.println("Enter Body");
		newTableHeader = false;
	}

	@Override
	public void exitFunc_decl(junkParser.Func_declContext ctx) {
//		System.out.println("exitFunc...");
		popSymbolTable();
	}

	@Override
	public void enterVar_decl(junkParser.Var_declContext ctx) {
//		System.out.println("Enter Variable Declaration");
		variableName.clear();
	}

	@Override
	public void exitVar_decl(junkParser.Var_declContext ctx) {
//		System.out.println("Exit Var Decl");
		for (String name : variableName) {
	            	s.addSymbol(new Symbol(variableType, name));
			//s.printTable();
		}
        	variableType = null;
        	variableName.clear();
    	}

	@Override public void enterVar_type(junkParser.Var_typeContext ctx) {
//		System.out.println("Enter Variable Type");
		variableType = ctx.getText();
	}

	@Override
	public void enterId(junkParser.IdContext ctx) {
//		System.out.println("Enter variable name");
		if (newTable) {
//            		System.out.println("New Table");
             		s.setName(ctx.getText());
             		variableName.clear();
             		newTable = false;
        	} else if (newTableHeader) {
//            		System.out.println("Table Header");
            		s.addSymbol(new Symbol(variableType, ctx.getText()));
            		variableType = null;
		} else if (programHeader) {
			programHeader = false;
        	} else {
            		variableName.add(ctx.getText());
        	}
	}

	@Override
	public void exitId(junkParser.IdContext ctx) {
//		System.out.println("Exit variable name");
	}

	@Override
	public void enterString_decl(junkParser.String_declContext ctx) {
//		System.out.println("Enter String decl");
		variableType = "STRING";
	}

	@Override
	public void exitString_decl(junkParser.String_declContext ctx) {
//		System.out.println("Exit String decl");
		s.addSymbol(new Symbol(variableType, variableName.get(variableName.size() - 1), variableValue));
       	 	variableName.clear();
        	variableType = null;
	        variableValue = null;
	}

	@Override
	public void enterStr(junkParser.StrContext ctx) {
//		System.out.println("Enter String value");
		variableValue = ctx.getText();
	}

	@Override
	public void enterAssign_expr(junkParser.Assign_exprContext ctx) {
		System.out.println("Assignment Expression:");
		int count = ctx.getChildCount();
		for (int i = 0; i < count; i++) {
			System.out.println("Child " + i + ": " + ctx.getChild(i).getText());
		}
	}
	@Override
	public void enterExpr(junkParser.ExprContext ctx) {
		System.out.println("Expression Part Uno:");
		System.out.println("Child factor: " + ctx.getChild(1).getText());
	}
	@Override
	public void enterFactor(junkParser.FactorContext ctx) {
		System.out.println("Factor Part Uno:");
		int count = ctx.getChildCount();
		for (int i = 0; i < count; i++) {
			System.out.println("Child " + i + ": " + ctx.getChild(i).getText());
		}
	}
	@Override
	public void enterAddop(junkParser.AddopContext ctx) {
		System.out.println("Addop Part Uno:");
		int count = ctx.getChildCount();
		for (int i = 0; i < count; i++) {
			System.out.println("Child " + i + ": " + ctx.getChild(i).getText());
		}
	}

	@Override
	public void enterCond(junkParser.CondContext ctx) {
		//System.out.println("Condition: " + ctx.getText());
		int count = ctx.getChildCount();
		String[] set = new String[count];
		for (int i = 0; i < count; i++) {
			set[i] = ctx.getChild(i).getText();
		}
		ir.parseComparison(set);
	}

	@Override
	public void enterIf_stmt(junkParser.If_stmtContext ctx) {
                //System.out.println("Enter If stmt");
		pushSymbolTable();
	        s.setName("BLOCK " + block);
        	block++;
		
		//flag IRBuilder
		ir.setCondition("if");
	}

	@Override
	public void exitIf_stmt(junkParser.If_stmtContext ctx) {
                //System.out.println("Exit If stmt");
		popSymbolTable();
		ir.exitIf();
		//flag IRBuilder
		ir.setCondition(null);
	}

	@Override
	public void enterElse_part(junkParser.Else_partContext ctx) {
                //System.out.println("Enter Else stmt");
		//get out of if  block and enter else block
        	if (ctx.getChildCount() > 0) {
			popSymbolTable();
        		pushSymbolTable();
        		s.setName("BLOCK " + block);
	        	block++;
			ir.enterElsePart();
        		//use exitIf() to pop back to parent symbolTable
		}
	}

	@Override
	public void exitElse_part(junkParser.Else_partContext ctx) {
//                System.out.println("Exit Else stmt");
	}

	@Override
	public void enterWhile_stmt(junkParser.While_stmtContext ctx) {
                //System.out.println("WHILE | " + ctx.getText());
		pushSymbolTable();
		s.setName("BLOCK " + block);
		block++;
		
		//flag IRBuilder
		ir.setCondition("while");
        }

	@Override
	public void exitWhile_stmt(junkParser.While_stmtContext ctx) {
//		System.out.println("Exit While stmt");
		popSymbolTable();
		ir.endWhile();
		//flag IRBuilder
		ir.setCondition(null);
	}

	@Override
	public void enterWrite_stmt(junkParser.Write_stmtContext ctx) {
		//Attempting to expand the context parser
		//System.out.println("Expanding Write statement!");
		//System.out.println(ctx.getText());
		//int count = ctx.getChildCount();
		//list: | WRITE | ( | param1,param2,... | ) | ; |
		String[] params = ctx.getChild(2).getText().split(",");
		ir.buildWrite(params);
	}

	@Override
	public void enterRead_stmt(junkParser.Read_stmtContext ctx) {
		//Attempting to expand the context parser
		//System.out.println("Expanding Read statement!");
		//System.out.println(ctx.getText());
		//int count = ctx.getChildCount();
		//list: | READ | ( | param1,param2,... | ) | ; |
		String[] params = ctx.getChild(2).getText().split(",");
		ir.buildRead(params);
	}



	public SymbolTable getSymbolTable() {
		return s;
	}

	public void popSymbolTable() {
		s = s.getParent();
		ir.updateTable(s);
	}

	public void pushSymbolTable() {
		//SymbolTable newTable = new SymbolTable(s, s.getScopedVariables());
		//s = newTable;
		s = s.createChild();
		ir.updateTable(s);
	}
}


//
//		int count = ctx.getChildCount();
//		for (int i = 0; i < count; i++) {
//			System.out.println(ctx.getChild(i).getText());
//		}
