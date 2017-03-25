
//package antlrproject;

import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 *
 * @author Ryan Darnell
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	
        try {
	    FileReader file = new FileReader(args[0]);
            CharStream stream = new ANTLRInputStream(file);
            junkLexer lexer = new junkLexer(stream);
            Vocabulary vocab = lexer.getVocabulary();
            Token tok = null;
	    //System.out.println("Attempting to grab tokens");
	    //System.out.println(lexer.nextToken());

	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    junkParser parser = new junkParser(tokens);
	    Listener listener = new Listener();
	    new ParseTreeWalker().walk(listener, parser.program());
	    //System.out.printf("Found %d errors\n", parser.getNumberOfSyntaxErrors());
	    //System.out.println("Finished parsing.");
	    //TokenStream tokens1 = parser.getTokenStream();

	   // System.out.println(tokens1.get(0).getText());

	    if (parser.getNumberOfSyntaxErrors() == 0) {
  		System.out.println("Accepted");
	    } else {
		System.out.println("Not accepted");
	    }
        } catch (Exception ex) {
            System.out.println("Failed to do stuff");
        }
    }

}
