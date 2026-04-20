/**
 * Inputs for cli:
 * java Homework05_Dinh "(sum + 47) / total"
 * java Homework05_Dinh "(count * 10 + 2) / (total - sum)"
 */

import java.io.*;

public class Homework06b_Dinh {

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
    static final int INT_LIT = 10;
    static final int IDENT = 11;
    static final int ASSIGN_OP = 20;
    static final int ADD_OP = 21;
    static final int SUB_OP = 22;
    static final int MULT_OP = 23;
    static final int DIV_OP = 24;
    static final int LEFT_PAREN = 25;
    static final int RIGHT_PAREN = 26;

    static final int EOF = -1;

    public static void main(String[] args) throws IOException {

        try {
            if (args.length == 0) {
                reader = new BufferedReader(new FileReader("front.in"));
            } else if (args.length == 1) {
                reader = new BufferedReader(new StringReader(args[0]));
            } else if (args.length >= 2 && args[0].equals("-f")) {
                reader = new BufferedReader(new FileReader(args[1]));
            }

            // function declarations
            getChar();

            do {
                lex();
            } while (nextToken != EOF);

        } catch (FileNotFoundException e) {
            System.out.println("Error cannot open file");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

    }

    private static int lookup(char ch) throws IOException{
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;

            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;

            case '+':
                addChar();
                nextToken = ADD_OP;
                break;

            case '-':
                addChar();
                nextToken = SUB_OP;
                break;

            case '*':
                addChar();
                nextToken = MULT_OP;
                break;

            case '/':
                addChar();
                nextToken = DIV_OP;
                break;

            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;
    }


    private static void addChar() throws IOException {
        if (lexeme.length() <= 98) {
            lexeme.append((char) nextChar);
        } else {
            System.out.println("Error - lexeme is too long \n");
        }
    }

    private static void getChar() throws IOException {
        nextChar = reader.read();
        if (nextChar != EOF) {
            nextChar = nextChar;
            if (Character.isLetter(nextChar)) {
                charClass = LETTER;
            } else if (Character.isDigit(nextChar)) {
                charClass = DIGIT;
            } else {
                charClass = UNKNOWN;
            }
        } else {
            nextChar = EOF;
            charClass = EOF;
        }
    }

    private static void getNonBlank() throws IOException {
        while (Character.isWhitespace(nextChar)) {
            getChar();
        }
    }

    private static void lex() throws IOException {
        lexeme.setLength(0);
        getNonBlank();
        switch (charClass) {
            // parse identifiers
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;
            // parse integer literals (int_lit)
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
            // parentheses and operators
            case UNKNOWN:
                lookup((char)nextChar);
                getChar();
                break;
            // eof
            case EOF:
                nextToken = EOF;
                lexeme.append("EOF");
                break;
        }
        System.out.printf("Next token is: %d, Next lexeme is %s\n", nextToken, lexeme.toString());
    }

    // hw6
    private static void expr() throws IOException{
        System.out.println("Enter <expr>\n");

        //parse  
        term();

        while (nextToken == ADD_OP ||nextToken == SUB_OP){
            lex();
            term();
        }
        System.out.println("Exit <expr>\n");
    }

    private static void term() throws IOException{
        System.out.println("Enter <term>\n");

        // parse the first factor
        factor();

        while (nextToken == MULT_OP || nextToken == DIV_OP) {
            lex();
            factor();
        }
        System.out.println("Exit <term>\n");
    }

    private static void factor() throws IOException{
        System.out.println("Enter <factor>\n");

        // Determine which RHS
        if (nextToken == IDENT || nextToken == INT_LIT) {
            lex();
        } else {
            if (nextToken == LEFT_PAREN) {
                lex();
                expr();
                if (nextToken == RIGHT_PAREN) {
                    lex();
                } else {
                    error();
                }
            } else {
                error();
            }
        }
        System.out.println("Exit <factor>\n");
    }

}
