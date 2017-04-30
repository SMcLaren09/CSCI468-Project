import java.util.*;

public class TinyBuilder {

	private ArrayList<String> ir_list;
	private SymbolTable table;
	private ArrayList<String> tiny;
	private ArrayList<String> vars;
	private int regNum;
	private char dataType;
	//TODO: Symbol table needs to be root Table with the ability to check its children or something... issue with string variables.

	public TinyBuilder(ArrayList<String> ir_list, SymbolTable table) {
		this.ir_list = ir_list;
		this.table = table;
		tiny = new ArrayList<>();
		vars = new ArrayList<>();
		regNum = 0; //for storing the temps
	}

	public void print(boolean vertical) {
		printList(tiny,vertical);
	}

	private void printList(ArrayList<String> list, boolean vertical) {
		String orient = vertical ? "\n" : " ";
		for (String el : list) {
			System.out.print(el + orient);
		}
		System.out.println();
	}

	public void parseIrList() {
		//let the parsing games begin...
		for (String opcode : ir_list) {
			checkForVariable(opcode);
			String[] ops = opcode.split(" ");
			dataType = ops[0].toLowerCase().charAt(ops[0].length() - 1);
			dataType = dataType == 'f' ? 'r' : dataType;

			//route instruction
			if (opcode.contains("LABEL")) parseLabel(opcode);
			else if (opcode.contains("STORE")) parseStore(opcode);
			else if (opcode.contains("ADD")) parseAdd(opcode);
			else if (opcode.contains("SUB")) parseSub(opcode);
			else if (opcode.contains("MUL")) parseMul(opcode);
			else if (opcode.contains("DIV")) parseDiv(opcode);
			else if (opcode.contains("JUMP")) parseJump(opcode);
			else if (opcode.contains("LINK")) {}
			else if (opcode.contains("READ")) parseRead(opcode);
			else if (opcode.contains("WRITE")) parseWrite(opcode);
			else if (opcode.contains("RET")) parseRet(opcode);
			else parseComp(opcode); // I think that's all...
		}
	}

	private void checkForVariable(String opcode) {
		String[] ops = opcode.split(" ");
		for (String potential : ops) {
			Symbol sym = table.searchSymbol(potential);
			if (sym != null && !vars.contains(potential)) {
				foundVariable(sym);
			}
		}
	}
	private void foundVariable(Symbol var) {
		String variable = var.getName();
		vars.add(variable);
		if (var.getType().equals("STRING")) {
			variable += String.format(" %s",var.getValue());
		}
		tiny.add(0,String.format("var %s",variable));
	}

	// LABEL [label]
	private void parseLabel(String code) {
		String[] ops = code.split(" ");
		tiny.add(String.format("label %s",ops[1]));
	}

	// STORE[T] op1 op2
	private void parseStore(String code) {
		String[] ops = code.split(" ");
		//we'll see if we need to deal with variable checking...
		tiny.add(String.format("move %s %s",ops[1],ops[2]));
	}

	// COMP[T] op1 op2 label#
	private void parseComp(String code) {
		String[] ops = code.split(" ");
		String compop = "j" + ((String)ops[0].subSequence(0,ops[0].length() - 1)).toLowerCase();
		tiny.add(String.format("cmp%c %s %s",dataType,ops[1],ops[2]));
		tiny.add(String.format("%s %s",compop,ops[3]));
	}

	// ADD[T] r1 r2 r3
	private void parseAdd(String code) {
		code = code.replace("$T","r");
		String[] ops = code.split(" ");
		tiny.add(String.format("move %s %s",ops[1],ops[3]));
		tiny.add(String.format("add%c %s %s",dataType,ops[2],ops[3]));
	}

	// SUB[T] r1 r2 r3
	private void parseSub(String code) {
		code = code.replace("$T","r");
		String[] ops = code.split(" ");
		tiny.add(String.format("move %s %s",ops[1],ops[3]));
		tiny.add(String.format("sub%c %s %s",dataType,ops[2],ops[3]));
	}

	// MULT[T] r1 r2 r3
	private void parseMul(String code) {
		code = code.replace("$T","r");
		String[] ops = code.split(" ");
		tiny.add(String.format("move %s %s",ops[1],ops[3]));
		tiny.add(String.format("mul%c %s %s",dataType,ops[2],ops[3]));
	}

	// DIV[T] r1 r2 r3
	private void parseDiv(String code) {
		code = code.replace("$T","r");
		String[] ops = code.split(" ");
		tiny.add(String.format("move %s %s",ops[1],ops[3]));
		tiny.add(String.format("div%c %s %s",dataType,ops[2],ops[3]));
	}

	// JUMP label#
	private void parseJump(String code) {
		String[] ops = code.split(" ");
		tiny.add(String.format("jmp %s",ops[1]));
	}

	// READ[T] op1
	private void parseRead(String code) {
		String[] ops = code.split(" ");
		tiny.add(String.format("sys read%c %s",dataType,ops[1]));
	}

	// WRITE[T] op1
	private void parseWrite(String code) {
		String[] ops = code.split(" ");
		tiny.add(String.format("sys write%c %s",dataType,ops[1]));
	}

	// RET
	private void parseRet(String code) {
		String[] ops = code.split(" ");
		tiny.add("sys halt");
	}



}
