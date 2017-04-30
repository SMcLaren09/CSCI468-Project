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

	// Expression Builder
	//private ArrayList<String> expression;
	private ArrayList<String> stack;
	private ArrayList<String> postfixOutput;
	private boolean inExpression = false;

	public IRBuilder(SymbolTable currentTable) {
		//Will need to add functionality later when it is determined what that is
		ir_list = new ArrayList<>();
		labelNum = 1;
		regNum = 1;
		this.currentTable = currentTable;
		labelStack = new ArrayList<>();
		stack = new ArrayList<>();
		postfixOutput = new ArrayList<>();
		//expression = new ArrayList<>();
	}

	public void updateTable(SymbolTable s) {
		currentTable = s;
	}
	private void clean() {
		//clean all token values to prevent spilling
		condition = null;
		compIR = null;
		dataType = ' ';
		stack.clear();
		postfixOutput.clear();
	}

	//remove '(' and ')' from stack along with variables
	private void trimStack() {
		ArrayList<String> to_trim = new ArrayList<>(Arrays.asList("(",")"));
		//for (String el : stack) {
			//if (currentTable.searchSymbol(el) != null) 
				//to_trim.add(el);
		//}
		stack.removeAll(to_trim);
	}

	//This is officially my new favorite method
	private void printList(ArrayList<String> list, boolean vertical) {
		String orient = vertical ? "\n" : " ";
		for (String el : list) {
			System.out.print(el + orient);
		}
		System.out.println();
	}

	private boolean isNumber(String numberMaybe) {
		return numberMaybe.matches("-?\\d*\\.?\\d+");
	}

	private boolean isRegister(String numberMaybe) {
		return numberMaybe.matches("r\\d+");
	}

	public void enterMain() {
		//System.out.println("LABEL main");
		//System.out.println("LINK");
		ir_list.add("LABEL main");
		ir_list.add("LINK");
	}
	public void endProgram() {
		//System.out.println("RET");
		ir_list.add("RET");
		System.out.println("Printing IR Code: <size> " + ir_list.size());
		printList(ir_list, true);		
		TinyBuilder build = new TinyBuilder(ir_list, currentTable);
		build.parseIrList();
		build.print(true);
	}

	public void buildWrite(String[] params) {
		//Structure: list1,list2,list3,etc
		char type;
		//System.out.println("Building Write");
		for (String param : params) {
			type = currentTable.searchSymbol(param).getType().toUpperCase().toCharArray()[0];
			ir_list.add("WRITE" + type + " " + param);
			//System.out.println("WRITE" + type + " " + param);
		}
	}

	public void buildRead(String[] params) {
		//Structure: list1,list2,list3,etc
		char type;
		//System.out.println("Building Read");
		for (String param : params) {
			type = currentTable.searchSymbol(param).getType().toUpperCase().toCharArray()[0];
			ir_list.add("READ" + type + " " + param);
			//System.out.println("WRITE" + type + " " + param);
		}
	}

	//set list: | op1 | compop | op2 |
	public void parseComparison(String[] set) {
		// Scanning op2 as it is more likely to be a literal and therefore faster to determine
		if (isNumber(set[2])) {
			//System.out.println("NUMBER!!!");
			//check if it floats
			dataType = set[2].contains(".") ? 'F' : 'I';
		} else { //get the data type from the variable
			dataType = currentTable.searchSymbol(set[0]).getType().toUpperCase().toCharArray()[0];
		}

		// convert ops if expression evident
		String op1 = elementToIR(set[0]);
		String op2 = elementToIR(set[2]);
		//checking if op1 or op2 was an expr ? change ops to what's left on stack : do nothing
		trimStack();
		//printList(stack);
		if (!isNumber(op1) && !isRegister(op1) && currentTable.searchSymbol(op1) == null) {
			//System.out.println("expr!!! " + stack.get(0));
			op1 = stack.get(0);
		}
		if (!isNumber(op2) && !isRegister(op2) && currentTable.searchSymbol(op2) == null) {
			//System.out.println("expr!!! " + stack.get(1 % stack.size()));
			op2 = stack.get(1 % stack.size());
		}
		//set compop after dataType is found
		setCompop(set[1]);
		routeCondition(op1, op2);
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

		//Note: may be issue with IR order if registers weren't ifninite. Address if there's time
		//System.out.println("LABEL label" + labelNum);
		ir_list.add("LABEL label" + labelNum++);
		
		//push new labelNum onto labelStack
		labelStack.add(0,"label" + labelNum);
		labelStack.add(0,"label" + (labelNum - 1));
		//System.out.println(compIR + " " + op1 + " " + op2 + " label" + labelNum);
		ir_list.add(compIR + " " + op1 + " " + op2 + " label" + labelNum++);
		//System.out.println("Next regNum: " + regNum + "\nNext labelNum: " + labelNum);

	}
	public void endWhile() {
		// IRCode to be built:
		// JUMP label#
		// LABEL label#++
		String op1 = "JUMP " + labelStack.remove(0);
		String op2 = "LABEL " + labelStack.remove(0);
		//System.out.println(op1 + "\n" + op2);
		ir_list.add(op1);
		ir_list.add(op2);
	}

	private void buildIfHeader(String op1, String op2) {
		//Assumption: buildIf is called when exitCond() listener method is called
		// IRCode to be built:
		// [store immediate to register] (regNum++)
		// [evaluate condition with jump to label#]
		//System.out.println("Building If Header...");
		// Add comparison instruction and push labelNum to stack
		labelStack.add(0,"label" + labelNum);
		//System.out.println(compIR + " " + op1 + " " + op2 + " label" + (labelNum));
		ir_list.add(compIR + " " + op1 + " " + op2 + " label" + labelNum++);
		//System.out.println("Next regNum: " + regNum + "\nNext labelNum: " + labelNum);
	}

	public void enterElsePart() { //weird shit happens with these if statements........
		// IRCode to be built(if needed):
		// JUMP label#
		// LABEL label#
		String elseLabel = labelStack.remove(0);
		labelStack.add(0, "label" + labelNum);
		//System.out.println("JUMP label" + labelNum);
		ir_list.add("JUMP label" + labelNum++);
		//System.out.println("LABEL " + elseLabel);
		ir_list.add("LABEL " + elseLabel);		
		
	}
	public void exitIf() {
		// IRCode to be built:
		// LABEL label#
		String elseLabel = labelStack.remove(0);
		//System.out.println("LABEL " + elseLabel);
		ir_list.add("LABEL " + elseLabel);
	}
		

	/* Expression Builder Funcitonality 
	 * Conversion process:
	 * expression --> postfix notation --> IR code
	 *  ie. a + b -->     a  b  +      -->  addi a b r1
	 */

	public void enterExpression() {
		//expression.add("(");
		inExpression = true;
		stack.add(0,"(");
	}

	public void exitExpression(boolean end) {
		//expression.add(")");

		stack.add(0,")");
		popPostFixStack(end);
	}

	public void addElement(String element) {
		if (!inExpression) return;
		postfixOutput.add(element);
                //printPostfix();
	}
	public void addOperator(String op) {
		if (!inExpression) return;
		String postfixOp;
		switch(op) {
			case "+":
			case "-":
				if (stack.isEmpty() || stack.get(0) == "(") stack.add(0,op);
				else {
				// nothing is lower precedence than the two pop, push, and build
				postfixOp = stack.remove(0);
				postfixOutput.add(postfixOp);
				addOperator(op); //oh boy...
				}
				break;
			case "*":
			case "/":
				//check the top of the stack if same level of heiarchy
				if (stack.get(0) == "*" || stack.get(0) == "/") {
					postfixOp = stack.remove(0);
					postfixOutput.add(postfixOp);
                                        addOperator(op); //oh boy...
				} else { // for "+","-", or "("
					stack.add(0,op);
				}
				break;
			default:
				System.out.println("Shits broke yo...");
                }
                //printPostfix();
	}
        
        public void printPostfix() {
               System.out.print("Stack = ");
               String stacky = "";
               //reverse the appearance of the stack for readability
               for (String el : stack) {
                   stacky = el + " " + stacky;
               }
               System.out.println(stacky);
               
               System.out.print("Output = ");
               for (String el : postfixOutput) {
                   System.out.print(el + " ");
               }
               System.out.println();
        }

	private void popPostFixStack(boolean completely) {
		if (completely) {
			if (postfixOutput.size() == 1) { stack.add(0,postfixOutput.get(0)); }
			else {
			inExpression = false;
			while (!stack.isEmpty()) {
				// don't push parenthesis
				if (stack.get(0) == "(" || stack.get(0) == ")") {
                                    stack.remove(0);
                                    continue;
                                }
				// pop and push
				postfixOutput.add(stack.remove(0));
                                //printPostfix();
			}
			//System.out.println("Converting to IR...");
                        exprToIR();
			}
			postfixOutput.clear();
		} else {
			stack.remove(0); //remove ")" tag
			while (stack.get(0) != "(") {
				postfixOutput.add(stack.remove(0));
			}
			stack.remove(0); //remove "(" tag
		}
	}

	//if element is an immediate, then store to register
	private String elementToIR(String el) {		
		if (isNumber(el)) {
			dataType = el.contains(".") ? 'F' : 'I';
			//System.out.printf("STORE%c %s $T%d\n",dataType,el,regNum);
			ir_list.add(String.format("STORE%c %s $T%d",dataType,el,regNum++));
			el = "$T" + (regNum-1);
		}
		return el;
	}

	// Converts postfixOutput array values into IR code
	private void exprToIR() {
		// repurposing stack to work with IR building
		stack.clear(); //superfluous action. should already be empty

		// set dataType
		if (isNumber(postfixOutput.get(0))) { //check if it floats
			dataType = postfixOutput.get(0).contains(".") ? 'F' : 'I';
		} else { //get the data type from the variable
			dataType = currentTable.searchSymbol(postfixOutput.get(0)).getType().toUpperCase().toCharArray()[0];
		}

		//Build IR code
		String a;
		String b;
		for (String el : postfixOutput) {
			if (el.equals("+") || el.equals("-") || el.equals("*") || el.equals("/")) {
				b = elementToIR(stack.remove(0));
				a = elementToIR(stack.remove(0));
				switch (el) {
					case "+":
						//System.out.printf("ADD%c %s %s $T%d\n",dataType,a,b,regNum);
						stack.add(0,"$T"+regNum);
						ir_list.add(String.format("ADD%c %s %s $T%d",dataType,a,b,regNum++));
						break;
					case "-":
						//System.out.printf("SUB%c %s %s $T%d\n",dataType,a,b,regNum);
						stack.add(0,"$T"+regNum);
						ir_list.add(String.format("SUB%c %s %s $T%d",dataType,a,b,regNum++));
						break;
					case "*":
						//System.out.printf("MUL%c %s %s $T%d\n",dataType,a,b,regNum);
						stack.add(0,"$T"+regNum);
						ir_list.add(String.format("MUL%c %s %s $T%d",dataType,a,b,regNum++));
						break;
					case "/":
						//System.out.printf("DIV%c %s %s $T%d\n",dataType,a,b,regNum);
						stack.add(0,"$T"+regNum);
						ir_list.add(String.format("DIV%c %s %s $T%d",dataType,a,b,regNum++));
						break;
					default:
						System.out.println("Shits broke yo...");
				}
			} else {
				stack.add(0, el);
			}
		}
		//System.out.println("Remaining stack size: " + stack.size());
		//System.out.println("Remaining element on stack: " + stack.get(0));
	}

	public void assignmentStatement(String variable) {
		//convert value if immediate
		dataType = currentTable.searchSymbol(variable).getType().toUpperCase().toCharArray()[0];
		String value = elementToIR(stack.get(0));
		//System.out.printf("STORE%c %s %s\n",dataType,value,variable);
		ir_list.add(String.format("STORE%c %s %s",dataType,value,variable));
		clean();
	}		
}
