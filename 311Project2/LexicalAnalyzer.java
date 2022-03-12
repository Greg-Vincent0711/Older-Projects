package test;
import java.util.*;

public class LexicalAnalyzer {
	private String lexemeStr, lexeme = "";
    private int count = 0;
    private char [] tempLex; // Temporary lexeme array
    private char nextChar;
    private int token;
    private int charClass;   

    /*
     * Constructor for the lexeme
     */
    public LexicalAnalyzer(String lexemeStr) {
    	this.lexemeStr = lexemeStr;
        tempLex = lexemeStr.toCharArray();
    }
   
    //Character classes
    private final int LETTER = 0;
    private final int DIGIT = 1;
    private final int UNKNOWN = 99;
    
    //Different token types
    private final int INT_LIT = 10;
    private final int IDENT = 11;
    private final int ADD_OP = 21;
    private final int SUB_OP = 22;
    private final int MULT_OP = 23;
    private final int DIV_OP = 24;
    private final int LEFT_PAREN = 25;
    private final int RIGHT_PAREN = 26;
    private final int EOF = -1;
	private int lexLen = 0;
    
    //Different lexeme codes for string idents
    private final int FOR = 30;
    private final int ELSE = 32;
    private final int WHILE = 33;
    private final int DO = 34;
    private final int INT = 35;
    private final int FLOAT = 36;
    private final int SWITCH = 37;

    /**
     * @return int 
     * @param char lookupChar 
     * Function handles all unknown charclass cases
     * Anything to do with operators
     */
    public int lookup(char lookupChar){
        switch(lookupChar){
            case '(':
                addChar(); 
                token = LEFT_PAREN; 
                break;
            case ')':
                addChar();
                token = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                token = ADD_OP;
                break;
            case '-':
                addChar();
                token = SUB_OP;
                break;
            case '*':
                addChar();
                token = MULT_OP;
                break;
            case '/':
                addChar();
                token = DIV_OP;
                break;
            default:
                addChar();
                token = EOF;
                break;
        }
        return token;
    }
    
    /*
     * @param lexemeStr - lexeme to identify
     * @return int 
     * */
    public int codeLookup(String lexemeStr){
    	if(lexemeStr.equalsIgnoreCase("FOR")){
    		token = FOR;
    		System.out.println("                              ");
    		System.out.println("Caught FOR code " + token + ".");
    	}
    	else if(lexemeStr.equalsIgnoreCase("ELSE")){
    		token = ELSE;
    		System.out.println("                               ");
    		System.out.println("Caught ELSE code " + token + ".");
    	}
    	else if(lexemeStr.equalsIgnoreCase("WHILE")){
    		token = WHILE;
    		System.out.println("                                ");
    		System.out.println("Caught WHILE code " + token + ".");
    	}
    	else if(lexemeStr.equalsIgnoreCase("DO")){
    		token = DO;
    		System.out.println("                             ");	
    		System.out.println("Caught DO code " + token + "."); 
    	}
    	else if(lexemeStr.equalsIgnoreCase("INT")){
    		token = INT;
    		System.out.println("                              ");
    		System.out.println("Caught INT code " + token + ".");
    	}
    	else if(lexemeStr.equalsIgnoreCase("FLOAT")){
    		token = FLOAT;
    		System.out.println("                                ");
    		System.out.println("Caught FLOAT code " + token + ".");
    	}
    	else if(lexemeStr.equalsIgnoreCase("SWITCH")){
    		token = SWITCH;
    		System.out.println("                                 ");
    		System.out.println("Caught SWITCH code " + token + ".");
    	}
    	return 1;
    }
    
    /*
     * Adding to the lexeme
     * */
    public void addChar(){
        lexeme += nextChar;
    }
    
    /*
     * giving a character class to each token in the lexeme
     * */
    public void getChar(){
    	if(lexLen >= lexemeStr.length()) {
            charClass = EOF;
    		return;
        }
    	/*increment the length everytime the character class is set*/
    	nextChar = tempLex[lexLen];
        if(Character.isLetter(nextChar)){
            charClass = LETTER;
            lexLen++;
        }
        else if(Character.isDigit(nextChar)){
            charClass = DIGIT;
            lexLen++;
        }
        else{
            charClass = UNKNOWN;
            lexLen++;
        }
   }

    /*
     * Checking for spaces
     * */
    public void getNonBlank(){
         while(Character.isWhitespace(nextChar)){
            getChar();
            count++;
        }    
    }
      
    /* analyzing a lexeme */
    public void analyze(){
        getNonBlank(); 
        /*After checking if their's a space, switch the character class*/
        switch(charClass){
            case LETTER:
                addChar(); 
                getChar(); 
                while(charClass == LETTER || charClass == DIGIT){
                    addChar();
                    getChar();
                    count++;
                }
                codeLookup(lexeme); //Check for the string lexeme's type
                token = IDENT;
                break;
            case DIGIT:
                addChar();
                getChar();
                while(charClass == DIGIT){
                    addChar();
                    getChar();
                    count++;
                }
                token = INT_LIT;
                break;
            case UNKNOWN:
                lookup(nextChar); 
                getChar();
                break;
            case EOF:
                token = EOF;
                lexeme = "EOF";
                break;
        }
        System.out.println("                                                                    ");
        System.out.println("Next token is " + token + "." + " The next lexeme is " + lexeme + ".");
        System.out.println("____________________________________________________________________");
        lexeme = "";
    }
    
    /*Running the lexical analyzer*/
    public void lexAnalyze() {
        for (int count = 0; count < lexemeStr.length(); count++) {
            if (count == 0) {
                getChar();
            }
            while(token != EOF) {
            	/*Analyze each character of a lexeme*/
            	analyze();
            }
        }
    }
    
    /*Running syntax analyzer*/
    public void synAnalyze() {
        for (count = 0; count < lexemeStr.length(); count++) {
            if (count == 0) {
                getChar();
            }
           while(token != EOF) {
        	   analyze();
        	   /*Parse an expression*/
               expr();
           }    
        }
    }
    
    /*All the Syntax analyzer functions*/
    public void expr() {
    	System.out.println("____________________________________________________________________");
    	System.out.println("ENTER <EXPR>");
    		/*Parse the first term*/
    		term();
    		while(token == ADD_OP || token == SUB_OP) {
    			analyze();
    			term();
    		}
    	System.out.println("LEAVING <EXPR>");
    	System.out.println("____________________________________________________________________");
    }
    
    public void term() {
    	System.out.println("____________________________________________________________________");
    	System.out.println("ENTER <TERM> ");
    		factor();
    		while(token == MULT_OP || token == DIV_OP) {
    			analyze();
    			/*Parse the first factor*/
    			factor();
    		}
    	System.out.println("EXIT <TERM> ");
    	System.out.println("____________________________________________________________________");
    	
    }
    
    public void factor(){
    	System.out.println("____________________________________________________________________");
    	System.out.println("ENTER <FACTOR>");
    		if(token == IDENT || token == INT_LIT) {
    			analyze();
    		}
    		else {
    			if(token == LEFT_PAREN) {
    				analyze();
    				expr();
    				if(token == RIGHT_PAREN) {
    					analyze();
    				}
    				else{
    					error();
    				}
    			}
    			else {
    				error();
    			}
    		}
    	System.out.println("EXIT <FACTOR>");
    	System.out.println("____________________________________________________________________");
    }
    
    /*
     * Simple error checker
     * */
    public int error(){
    	System.out.println("Error.");
    	return -1;
    }
    
}
