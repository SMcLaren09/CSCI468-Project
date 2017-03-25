public class Listener extends junkBaseListener {

	SymbolTable s;

	@Override
	public void enterFunc_decl(junkParser.Func_declContext ctx) {
		System.out.println("enterFunc...");
		System.out.println(ctx.getText());

	}

	@Override
	public void exitFunc_decl(junkParser.Func_declContext ctx) {
		System.out.println("exitFunc...");
		System.out.println(ctx.getText());


	}

	public SymbolTable getSymbolTable() {
		return s;
	}
}
