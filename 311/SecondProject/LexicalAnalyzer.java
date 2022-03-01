import java.util.*;
import java.io.*;
/**
 * @author Greg Vincent
 * Lexical analyzer for arithmetic expressions
 * 2/22/22
 */
public class LexicalAnalyzer{

    int lexLen;
    char [] lexeme;
    char nextChar; //current char being analyzed

    public LexicalAnalyzer(int lexLen, char [] lexeme) {
        this.lexLen = lexLen;
        this.lexeme = lexeme;
    }
    /**
     * All the different identifiers for tokens and character
     */
    enum TokenType {
        INT_LIT,
        IDENT,
        ASSIGN_OP,
        ADD_OP,
        SUB_OP,
        MULT_OP,
        DIV_OP,
        LEFT_PAREN,
        RIGHT_PAREN,
        //All different character types
        LETTER,
        DIGIT,
        UNKNOWN,
        END_OF_STRING
    }
    TokenType tokenCode = null; 
    
    /**
     * @param C - given character
     * @return - wether this character is a space or not
     */
    private boolean isSpace(char C){
        if(C == ' '){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * adds the next character in the list to the lexeme
     * @param lexLen - length of the lexical analyzer
     */
    public void addChar(){
        //lexeme length must be less than 98
        if(lexLen <= 98){
            //if its less than the limit, add the character to the lexeme
            lexeme[lexLen++] = nextChar;
        }
        else{
            System.out.println("Error, lexeme is too long.");
        }
    }
    //gets character from lexeme, identifies it
    public void getChar(){
        //for the characters in the lexeme
        if(nextChar == lexeme.length - 1){
            //if its a letter
            if(Character.isLetter(nextChar)){
                tokenCode = TokenType.LETTER;
            }
            //is a number
            else if(Character.isDigit(nextChar)){
                tokenCode = TokenType.DIGIT;
            }
            else{
                tokenCode = TokenType.UNKNOWN;
            }
        }
        tokenCode = TokenType.END_OF_STRING;
    }

    /**
     * Checks for spaces, if so, keep going
     */
    public void noSpaces(){
        while(isSpace(nextChar)){
            getChar();
        }
    }

    /**
     * @param lookupChar
     * @return integer id for the parameter
     */
    public int lookup(char lookupChar){
        lookupChar = nextChar;
        switch(lookupChar){
            case '(':
                addChar(); //take this character, add it to the lexeme
                tokenCode = LEFT_PAREN; //set the token for the lexeme element
                break;
            case ')':
                addChar();
                tokenCode = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                tokenCode = ADD_OP;
                break;
            case '-':
                addChar();
                tokenCode = SUB_OP;
                break;
            case '*':
                addChar();
                tokenCode = MOD_OP;
                break;
            case '/':
                addChar();
                tokenCode = DIV_OP;
                break;
            default:
                addChar();
                tokenCode = END_OF_STRING;
                break;

        }
    }

    /**function performs actual lexical analysis */
    public void lex(){
        lexLen = 0; //set the initial length of the array
        noSpaces(); //check for spaces in the array
        switch(tokenCode){
            case tokenCode == TokenType.LETTER:
                addChar(); //add the letter to the lexeme
                getChar(); //look at this character's tokenCode
                while(tokenCode == LETTER || tokenCode == DIGIT){
                    addChar();
                    getChar();
                }
                tokenCode = IDENT; //lexeme must have ident token if it's a digit or letter
                break;
            case tokenCode == TokenType.DIGIT:
                addChar();
                getChar();
                while(tokenCode == DIGIT){
                    addChar();
                    getChar();
                }
                tokenCode = INT_LIT;
                break;
            case tokenCode == TokenType.UNKNWOWN:
                lookup(nextChar); //search for the character
                getChar(); //get its type
                break;
            case tokenCode == TokenType.END_OF_STRING:
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'S';
                lexeme[3] = '0';
                break;
            System.out.println("Next token is " + nextChar + "." + 
                                "Next lexeme is " + lexeme.toString() + ".");
        }
    }

    public static void main(String[] args){
        //testing the analyzer
        File readFile = new File("expressions.txt");
        final int size = 98;
        String [] lexeme = new String[size];
        String currentLine;
        BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
        for (int index = 0; index < lexeme.length; index++){
            while ((currentLine = fileReader.readLine()) != null) {
                //fill the array
                lexeme[index] = currentLine;
                index++;
            }
            System.out.println(lexeme[index]);
        }
        fileReader.close();

    }
    // for (index = 0; index < numbers.length; index++)
    // {
    //    // Read the file contents into the array
    //    while (inputFile.hasNext() && index < numbers.length)
    //    {
    //       numbers[index] = inputFile.nextInt();
    //       index++;
    //    }
    // System.out.println(numbers[index]);
    // }
}
