
//package antlrproject;

import java.io.*;
import org.antlr.v4.runtime.*;

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

            do {
                tok = lexer.nextToken();
		if (tok.getType() == tok.EOF)
		    break;
		String tokenType;
                System.out.println("Token Type: " + vocab.getSymbolicName(tok.getType()) +
                                   "\nValue: " + tok.getText());
            } while(tok.getType() != tok.EOF);
        } catch (Exception ex) {
            System.out.println("Failed to do stuff");
        }
    }

}