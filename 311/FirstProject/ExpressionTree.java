import java.util.*;
public class ExpressionTree implements Node {
    //Beginning of the search tree
    private Node<T> root;
    //Whatever message is passed in
    private String infixExpr;

    enum ORDERTYPE{
        preorder,
        inorder,
        postorder
    }
    
    //default constructor
    public ExpressionTree(){

    }
    public ExpressionTree(Node<T> root, T infix){
        this.infix = infix;
        //a new tree automatically converts to postFix
        this.root = toPostFix(infix);
    }

    /** 
     * @return Node<T> type
     */
    public Node<T> getRoot(){
        return this.root;
    }
    /** 
     * @return generic type T
     */
    public T getInfix(){
        return this.infix;
    }
    

    private void preOrder(Node<T> root){
        if(root != null){
        }
    }
    @Override
    public String toString(){
        return this.root.getClass().getSimpleName() + " " + 
        this.infixExpr.getClass().getSimpleName();
    }

    /** 
     * @return boolean
     * @param infix string
     * helper function to parse infix
    */
    private boolean isNum(String infix){
        switch(infix){
            case "+":
                return false;
            case "-":
                return false;
            case "*":
                return false;
            case "/":
                return false;
            default:
                return true;
        }
    }

    /** 
	 * Helper function for all expression elements
	 * @param infix
	 * @return integer 
    */
	private int opPrecedence(T infix){
        infix = infix.trim();
		switch(infix){
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                return 0;
        }
	}

    public String toPostfix(String infix){
        //Holds final result
        ArrayList <String> postFixList = new ArrayList<>();
        //splitting the string into tokens
        StringTokenizer token = new StringTokenizer(infix,"()+*-/",true);
        Stack<Character>tokenStack = new Stack<Character>();
        while(token.hasMoreTokens()){
                String infixToken = token.nextToken();
                if(isNum(infixToken) == true){
                    //add straight to resulting arrayList
                    postFixList.add(infixToken);    
                }
                else if(infixToken.equals("(")){
                    tokenStack.push(infixToken);
                }
                else if(infixToken.equals(")")){
                    //while newewst stack element is not open paren
                    while(!tokenStack.peek().equals("(")){
                        //pop from the stack, add to the final arraylist
                        postFixList.add(tokenStack.pop());
                    }
                    //pop off the stack
                    tokenStack.pop();
                }
                else if (infixToken.equals("+")||infixToken.equals("-")||
                                infixToken.equals("*")||infixToken.equals("/"))
                {

                    if(tokenStack.isEmpty()){
                            //add next token to the stack
                            tokenStack.push(infixToken);
                        }
                    else{   
                            Char infChar = tokenStack.peek();
                            if(infChar.equals("(")){
                                tokenStack.push(infixToken);
                                continue;
                            } 
                            //latest stack char precedence compared to remaining token  
                            if(opPrecedence(infChar) >= opPrecedence(infixToken)){
                                while(!tokenStack.isEmpty()){
                                    postFixList.add(tokenStack.pop());
                                    if(tokenStack.isEmpty()){
                                            tokenStack.push(infixToken);
                                            break;
                                        }
                                    else if(tokenStack.peek().equals("(")){
                                            tokenStack.push(infixToken);
                                            break;
                                        }
                                    else if(opPrecedence(tokenStack.peek()) >= opPrecedence(infixToken)){
                                            continue;
                                        }   

                                    else{
                                        tokenStack.push(infixToken);
                                        break;
                                    }   
                                }
                            }   
                            else{
                                tokenStack.push(infixToken);
                            }                       
                        }               
            }
        }

        while(!tokenStack.isEmpty())
        {
            postFixList.add(tokenStack.pop());
        }
        return postFixList;
    }            
    
    public Node<T> postFixtoTree(ArrayList<String> postFix){
        
    }
}
