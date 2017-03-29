public class Listener extends junkBaseListener {

	SymbolTable s;
	Symbol symbol;
	boolean isFlag = false;
	String variableType;
	String variableName;
	String variableValue;
	int block = 1;

	@Override
	public void enterProgram(junkParser.ProgramContext ctx) {
		System.out.println("enterProgram...");
                System.out.println(ctx.getText());

		s = new SymbolTable(null, null);
		s.setName("GLOBAL");
        }

	@Override
	public void exitProgram(junkParser.ProgramContext ctx) {
		System.out.println("Exiting Program...");
                //System.out.println(ctx.getText());
	//	System.out.println("Printing Tables");
	//	s.printAll();
        }

	@Override
	public void enterFunc_decl(junkParser.Func_declContext ctx) {
		System.out.println("enterFunc...");
		System.out.println(ctx.getText());

		//pushSymbolTable();
		isFlag = true;
	}

	@Override
	public void exitFunc_decl(junkParser.Func_declContext ctx) {
		System.out.println("exitFunc...");
		//System.out.println(ctx.getText());
	}

	@Override
	public void enterVar_decl(junkParser.Var_declContext ctx) {
		System.out.println("Enter Variable Declaration");
		//System.out.println(ctx.getText());

		isFlag = true;
	}

	@Override
	public void enterId_list(junkParser.Id_listContext ctx) {
		System.out.println("Enter id list");
	}

	@Override
	public void exitId_list(junkParser.Id_listContext ctx) {
		System.out.println("Exit id list");
	}

	@Override
	public void exitVar_decl(junkParser.Var_declContext ctx) {
                System.out.println("Exit Decl");
                // System.out.println(ctx.getText());

		//s.addSymbol(new Symbol(variableType, VariableName));
		variableType = null;
		variableName = null;
		isFlag = false;
        }

	@Override public void enterVar_type(junkParser.Var_typeContext ctx) {
		System.out.println("Enter Variable Type");
                System.out.println(ctx.getText());

		variableType = ctx.getText();
        }

	@Override
	public void enterId(junkParser.IdContext ctx) {
		System.out.println("Enter variable name");
                System.out.println(ctx.getText());
		//System.out.println("Parent: " + ctx.getParent().toInfoString());

		if(isFlag) {
		//	if (symbol == null) { s.setName(ctx.getText()); }
		//	else { variableName = ctx.getText(); }
		}
	}

	@Override
	public void exitId(junkParser.IdContext ctx) {
		System.out.println("Exit variable name");
                //System.out.println(ctx.getText());

	//	if (isFlag && !variableType.equals("STRING")) {
		//	s.addSymbol(new Symbol(variableType, variableName));
		//	variableName = null;
	//	} else if (isFlag) { //case: Function name
		//	s.setName(ctx.getText());
	//	}
	}

	@Override
	public void enterString_decl(junkParser.String_declContext ctx) {
		System.out.println("Enter String decl");
		System.out.println(ctx.getText());

		variableType = "STRING";
	}

	@Override
	public void exitString_decl(junkParser.String_declContext ctx) {
		System.out.println("Exit String decl");

		//s.addSymbol(new Symbol(variableType, variableName, variableValue));
		variableType = null;
		variableName = null;
		variableValue = null;
	}

	@Override
	public void enterStr(junkParser.StrContext ctx) {
		System.out.println("Enter String value");
                System.out.println(ctx.getText());

		variableValue = ctx.getText();
	}

	@Override
	public void enterIf_stmt(junkParser.If_stmtContext ctx) {
                System.out.println("Enter If stmt");
                System.out.println(ctx.getText());

	//	pushSymbolTable();
	//	s.setName("BLOCK " + block);
        }

	@Override
	public void exitIf_stmt(junkParser.If_stmtContext ctx) {
                System.out.println("Exit If stmt");
               // System.out.println(ctx.getText());

		block++;
	//	popSymbolTable();
        }

	@Override
	public void enterElse_part(junkParser.Else_partContext ctx) {
                System.out.println("Enter Else stmt");
                System.out.println(ctx.getText());

	//	pushSymbolTable();
	//	s.setName("BLOCK " + block);
        }

	@Override
	public void exitElse_part(junkParser.Else_partContext ctx) {
                System.out.println("Exit Else stmt");
                //System.out.println(ctx.getText());

	//	block++;
	//	popSymbolTable();
        }

	@Override
	public void enterWhile_stmt(junkParser.While_stmtContext ctx) {
                System.out.println("Enter While stmt");
                System.out.println(ctx.getText());

	//	pushSymbolTable();
	//	s.setName("BLOCK " + block);
        }

	@Override
	public void exitWhile_stmt(junkParser.While_stmtContext ctx) {
		System.out.println("Exit While stmt");

	//	block++;
	//	popSymbolTable();
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
