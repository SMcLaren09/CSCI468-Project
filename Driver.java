import org.antlr.v4.*;

public static void main(String[] args) {

	try {
		CharStream stream = new ANTLRInputStream(System.in);
		JunkLexer lexer = new JunkLexer(stream);
		Vacabulary vocab = lexer.getVocabulary();
		Token tok = null;

		do {
			tok = lexer.nextToken();
			System.out.println("\t" + vocab.getSymbolicName(tok.getType()) + "\t" + tok.getText());

		} while (tok.getType() != "EOF");

	} catch(Exception e) {
		System.out.println("Failed to do some stuff");
	}


}
