
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
            CharStream stream = new ANTLRInputStream(System.in);
            junkLexer lexer = new junkLexer(stream);
            Vocabulary vocab = lexer.getVocabulary();
            Token tok = null;
            
            do {
                tok = lexer.nextToken();
                System.out.println("\t" + vocab.getSymbolicName(tok.getType()) +
                                    "\t" + tok.getText());
            } while(tok.getType() != tok.EOF);
        } catch (Exception ex) {
            System.out.println("Failed to do stuff");
        }
    }

}
