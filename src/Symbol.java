public class Symbol {

	private String type;
	private String name;
	private String value;

	public Symbol(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public Symbol(String type, String name, String value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public String getType() { return type; }

	public String getName() { return name; }

	public String getValue() { return value; }

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String print = "name " + name + " type " + type;
		if (value != null) {
			print += " value " + value;
		}
		return print;
	}

}
