import java.util.*;

public class IRBuilder {

	private ArrayList<String> ir_list;
	private String condition; //either 'while' or 'if' to state 
	private int labelNum;
	private int regNum;
	private String compIR;

	public IRBuilder() {
		//Will need to add functionality later when it is determined what that is
		ir_list = new ArrayList<>();
		labelNum = 1;
		regNum = 1;
	}

	private void clear() {
		//clear all token values to prevent spilling
		condition = null;
		compIR = null;
	}

	public void buildWrite(junkParser.Write_stmtContext ctx) {
		//Idea: grab list of children and loop through. Each time a comma is discovered, create a new instruction before continuing
		//Structure: WRITE(list1,list2,list3,etc);
	}

	public void exitCond() {
		//Determining which complex statement is executed in order to route the call
		if (condition == "while")
			buildWhileHeader();
		else
			buildIfHeader();
	}

	public void setCompop(String compop) {
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
		//possibility: compIR += typeFlag; //append the dataType flag?
	}

	private void buildWhileHeader() {
		//Assumption: buildWhile is called when exitCond() listener method is called
		// IRCode to be built:
		// LABEL label#
		// [store immediate to register] (regNum++)
		// [evaluate condition with jump to label#++]
	}

	public void exitWhile() {
		// IRCode to be built:
		//LABEL label#++
	}

	private void buildIfHeader() {
		//Assumption: buildIf is called when exitCond() listener method is called
		// IRCode to be built:
		// [store immediate to register] (regNum++)
		// [evaluate condition with jump to label#]
	}

	public void enterElse() { //weird shit happens with these if statements........
		// IRCode to be built(if needed):
		// JUMP label#
		// LABEL label#
	}
	public void exitIF() {
		// IRCode to be built:
		// LABEL label#
	}
		
		



}
