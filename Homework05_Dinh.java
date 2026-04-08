import java.io.*;

public class Homework05_Dinh {

    // global variables
    static int charClass;
    // change char lexeme[100]
    static StringBuilder lexeme = new StringBuilder(100);
    static int nextChar;
    static int token;
    static int nextToken;
    static BufferedReader reader;

    // character class
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;

    // token codes
    static final int INT_Lit = 10;
    static final int IDENT = 11;
    static final int ASSIGN_OP = 20;
    static final int ADD_OP = 21;
    static final int SUB_OP = 22;
    static final int MULT_OP = 23;
    static final int DIV_OP = 24;
    static final int LEFT_PAREN = 25;
    static final int RIGHT_PAREN = 26;

    static final int EOF = -1;

    public static void main(String[] args) {
        // function declarations
        addChar();
        getChar();
        getNonBlank();
        lex();
    }

    private static void lex() {
        
    }

    private static void getChar() {
        
    }

    private static void getNonBlank() {
       
    }

    private static void addChar() {
        
    }
}
