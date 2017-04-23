import java.util.*;

public class IRBuilder {

	private ArrayList<String> ir_list;
	private String condition; //either 'while' or 'if' to state 
	private int labelNum;
	private ArrayList<String> labelStack;
	private int regNum;
	private String compIR;
	private char dataType;
	private SymbolTable currentTable;

	public IRBuilder(SymbolTable currentTable) {
		//Will need to add functionality later when it is determined what that is
		ir_list = new ArrayList<>();
		labelNum = 1;
		regNum = 1;
		this.currentTable = currentTable;
		labelStack = new ArrayList<>();
	}

	public void updateTable(SymbolTable s) {
		currentTable = s;
	}
	private void clean() {
		//clean all token values to prevent spilling
		condition = null;
		compIR = null;
		dataType = ' ';
	}

	public void enterMain() {
		ir_list.add("LABEL main");
		ir_list.add("LINK");
	}
	public void endProgram() {
		ir_list.add("RET");
	}

	public void buildWrite(String[] params) {
		//Structure: list1,list2,list3,etc
		char type;
		System.out.println("Building Write");
		for (String param : params) {
			type = currentTable.searchSymbol(param).getType().toUpperCase().toCharArray()[0];
			ir_list.add("WRITE" + type + " " + param);
			System.out.println("WRITE" + type + " " + param);
		}
	}

	public void buildRead(String[] params) {
		//Structure: list1,list2,list3,etc
		char type;
		System.out.println("Building Read");
		for (String param : params) {
			type = currentTable.searchSymbol(param).getType().toUpperCase().toCharArray()[0];
			ir_list.add("READ" + type + " " + param);
			System.out.println("WRITE" + type + " " + param);
		}
	}

	//set list: | op1 | compop | op2 |
	public void parseComparison(String[] set) {
		// Scanning op2 as it is more likely to be a literal and therefore faster to determine
		if (isNumber(set[2])) {
			System.out.println("NUMBER!!!");
			//check if it floats
			dataType = set[2].contains(".") ? 'F' : 'I';
		} else { //get the data type from the variable
			System.out.println("VARIABLE!!!");
			dataType = currentTable.searchSymbol(set[0]).getType().toUpperCase().toCharArray()[0];
		}
		//same steps for set[2]
		//if (isNumber(set[2])) dataType = set[2].contains(".") ? 'F' : 'I';
		//else dataType = currentTable.searchSymbol(set[2]).getType().toUpperCase().toCharArray()[0];

		//set compop after dataType is found
		setCompop(set[1]);
		routeCondition(set[0], set[2]);
	}

	private boolean isNumber(String numberMaybe) {
		return numberMaybe.matches("-?\\d*\\.?\\d+");
	}

	// Set the condition to be routed to for routeCondition
	public void setCondition(String cond) {
		this.condition = cond;
	}

	// call appropriate condition statement
	public void routeCondition(String op1, String op2) throws NullPointerException {
		//Determining which complex statement is executed in order to route the call
		if (condition == null) 
			throw new NullPointerException("Condition value is null");
		else if (condition == "if")
			buildIfHeader(op1, op2);
		else
			buildWhileHeader(op1, op2);
		clean();
	}

	private void setCompop(String compop) {
		//set the proper IR instruction type for the given comparison operator
		//Note: this does not set the int vs float call
		switch(compop) {
			case "<":
				compIR = "GE";
				break;
			case "<=":
				compIR = "GT";
				break;
			case ">":
				compIR = "LE";
				break;
			case ">=":
				compIR = "LT";
				break;
			case "=":
				compIR = "NE";
				break;
			case "!=":
				compIR = "EQ";
				break;
			default:
				//shits broke yo
				compIR = null;
		}
		compIR += dataType; //append the dataType flag?
	}

	private void buildWhileHeader(String op1, String op2) {
		//Assumption: buildWhile is called when exitCond() listener method is called
		// IRCode to be built:
		// LABEL label#
		// [store immediate to register] (regNum++)
		// [evaluate condition with jump to label#++]
		// TODO: Fix condition issue of condition op being an expression
		System.out.println("Building While...\nLABEL label" + labelNum);
		ir_list.add("LABEL label" + labelNum++);
		if (isNumber(op1)) {
			System.out.println("STORE" + dataType + " " + op1 + " " + (op1 = "$T" + regNum));
			ir_list.add("STORE" + dataType + " " + op1 + " " + (op1 = "$T" + regNum++));
		}
		if (isNumber(op2)) {
			System.out.println("STORE" + dataType + " " + op2 + " " + (op2 = "$T" + regNum));
			ir_list.add("STORE" + dataType + " " + op2 + " " + (op2 = "$T" + regNum++));
		}
		//push new labelNum onto labelStack
		labelStack.add(0,"label" + labelNum);
		labelStack.add(0,"label" + (labelNum - 1));
		System.out.println(compIR + " " + op1 + " " + op2 + " label" + (labelNum));
		ir_list.add(compIR + " " + op1 + " " + op2 + " label" + labelNum++);
		System.out.println("Next regNum: " + regNum + "\nNext labelNum: " + labelNum);

	}
	public void endWhile() {
		// IRCode to be built:
		// JUMP label#
		// LABEL label#++
		String op1 = "JUMP " + labelStack.remove(0);
		String op2 = "LABEL " + labelStack.remove(0);
		System.out.println(op1 + "\n" + op2);
		ir_list.add(op1);
		ir_list.add(op2);
	}

	private void buildIfHeader(String op1, String op2) {
		//Assumption: buildIf is called when exitCond() listener method is called
		// IRCode to be built:
		// [store immediate to register] (regNum++)
		// [evaluate condition with jump to label#]
		System.out.println("Building If Header...");
		if (isNumber(op1)) {
			System.out.println("STORE" + dataType + " " + op1 + " " + (op1 = "$T" + regNum));
			ir_list.add("STORE" + dataType + " " + op1 + " " + (op1 = "$T" + regNum++));
		}
		if (isNumber(op2)) {
			System.out.println("STORE" + dataType + " " + op2 + " " + (op2 = "$T" + regNum));
			ir_list.add("STORE" + dataType + " " + op2 + " " + (op2 = "$T" + regNum++));
		}
		// Add comparison instruction and push labelNum to stack
		labelStack.add(0,"label" + labelNum);
		System.out.println(compIR + " " + op1 + " " + op2 + " label" + (labelNum));
		ir_list.add(compIR + " " + op1 + " " + op2 + " label" + labelNum++);
		System.out.println("Next regNum: " + regNum + "\nNext labelNum: " + labelNum);
	}

	public void enterElsePart() { //weird shit happens with these if statements........
		// IRCode to be built(if needed):
		// JUMP label#
		// LABEL label#
		String elseLabel = labelStack.remove(0);
		labelStack.add(0, "label" + labelNum);
		System.out.println("JUMP label" + labelNum);
		ir_list.add("JUMP label" + labelNum++);
		System.out.println("LABEL " + elseLabel);
		ir_list.add("LABEL " + elseLabel);		
		
	}
	public void exitIf() {
		// IRCode to be built:
		// LABEL label#
		String elseLabel = labelStack.remove(0);
		System.out.println("LABEL " + elseLabel);
		ir_list.add("LABEL " + elseLabel);
	}
		
		



}
