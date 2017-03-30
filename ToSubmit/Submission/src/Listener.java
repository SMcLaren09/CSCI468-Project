import java.util.*;

public class Listener extends junkBaseListener {

	SymbolTable s;
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
		s.setName("GLOBAL");
		programHeader = true;
        }

	@Override
	public void exitProgram(junkParser.ProgramContext ctx) {
//		System.out.println("Exiting Program...");
		s.printAll();
        }

	@Override
	public void enterFunc_decl(junkParser.Func_declContext ctx) {
//		System.out.println("enterFunc...");
		pushSymbolTable();
		newTable = true;
       		newTableHeader = true;
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
	public void enterIf_stmt(junkParser.If_stmtContext ctx) {
//                System.out.println("Enter If stmt");
		pushSymbolTable();
	        s.setName("BLOCK " + block);
        	block++;
	}

	@Override
	public void exitIf_stmt(junkParser.If_stmtContext ctx) {
//              System.out.println("Exit If stmt");
		popSymbolTable();
	}

	@Override
	public void enterElse_part(junkParser.Else_partContext ctx) {
//              System.out.println("Enter Else stmt");
		//get out of if  block and enter else block
        	if (ctx.getChildCount() > 0) {
			popSymbolTable();
        		pushSymbolTable();
        		s.setName("BLOCK " + block);
	        	block++;
        		//use exitIf() to pop back to parent symbolTable
		}
	}

	@Override
	public void exitElse_part(junkParser.Else_partContext ctx) {
//                System.out.println("Exit Else stmt");
	}

	@Override
	public void enterWhile_stmt(junkParser.While_stmtContext ctx) {
//              System.out.println("Enter While stmt");
		pushSymbolTable();
		s.setName("BLOCK " + block);
		block++;
        }

	@Override
	public void exitWhile_stmt(junkParser.While_stmtContext ctx) {
//		System.out.println("Exit While stmt");
		popSymbolTable();
	}

	public SymbolTable getSymbolTable() {
		return s;
	}

	public void popSymbolTable() {
		s = s.getParent();
	}

	public void pushSymbolTable() {
		//SymbolTable newTable = new SymbolTable(s, s.getScopedVariables());
		//s = newTable;
		s = s.createChild();
	}
}
