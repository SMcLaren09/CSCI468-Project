import java.util.*;

public class SymbolTable {

	private String name;
	private HashMap<String, Symbol> symbols;
    private ArrayList<Symbol> inOrder;
	private HashMap<String, Symbol> ancestorSymbols;
	private SymbolTable parent;
	private ArrayList<SymbolTable> children;

	public SymbolTable(SymbolTable parent, HashMap<String, Symbol> ancestorSymbols){
		this.parent = parent;
		this.ancestorSymbols = (ancestorSymbols == null) ? new HashMap<>() : ancestorSymbols;
		symbols = new HashMap<>();
		inOrder = new ArrayList<>();
		children = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSymbol(Symbol symbol) {
		System.out.println("Adding Symbol..." + symbols.toString());
		if (!exists(symbol))
			symbols.put(symbol.getName(), symbol);
			inOrder.add(symbol);
		else
			System.out.println("Symbol already exists!");
	}

	public void addChild(SymbolTable table) {
		children.add(table);
	}

	public SymbolTable createChild() {
		SymbolTable child = new SymbolTable(this, packageSymbols());
		addChild(child);
		return child;
	}

	public SymbolTable getParent() {
		return parent;
	}

	public void printTable() {
		System.out.println("Symbol table " + name);
		for (Symbol symbol : inOrder) {
			symbol.print();
		}
	}

	public void printAll() {
		printTable();
		System.out.println(); //line space
		for (SymbolTable table : children) {
			table.printAll();
		}
	}

	private boolean exists(Symbol symbol) {
		if (symbols.containsKey(symbol.getName()) || ancestorSymbols.containsKey(symbol.getName()))
			return true;
		else 
			return false;
	}

	private HashMap<String, Symbol> packageSymbols() {
		HashMap<String, Symbol> temp = new HashMap<>();
		temp.putAll(symbols);
		temp.putAll(ancestorSymbols);
		return temp;
	}

}
