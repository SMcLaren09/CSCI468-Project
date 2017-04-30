import java.util.*;

public class TinyBuilder {

	private ArrayList<String> ir;
	private ArrayList<String> tiny;
	//private ArrayList<String> vars;


	public TinyCodeBuilder(ArrayList<String> ir) {
		this.ir = ir;
		tiny = new ArrayList<>();
		//vars = new ArrayList<>();
	}


	public void parseIrList() {
		//let the parsing games begin...

	}

	private void foundVariable(String var) {
		tiny.add(String.format("var %s",var));
	}

}
