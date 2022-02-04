import java.util.*;
public class ExpressionTree implements Node {
    //Beginning of the search tree
    private Node<T> root;
    //Whatever message is passed in
    private String infixExpr;
    
    //default constructor
    public ExpressionTree(){

    }
    public ExpressionTree(Node<T> root, T infix){
        this.infix = infix;
        //a new tree automatically converts to postFix
        this.root = toTree(toPostFix(infix));
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

    /** 
     * @return boolean
     * @param infix generic type
     * helper function to parse infix
    */
    private boolean isNum(T infix){
        
    }

    private boolean isOp(T infix){
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
        return postFixList.toString();
    }        

    /**
     * @param postFix
     * @return Node<T> reference to the newly formed tree
     * StringTokenizer token = new StringTokenizer(infix,"()+*-/",true);
     */
    public Node<T> toTree(String postFix){
        //helper variable for the for lopp
        //temp data structure to hold the nodes
        Stack nodeStack = new Stack<Node<T>>();
        //split the postfix expression by spaces
        String [] postData = postFix.split("\\s+");
        //iterate through the data
        for(String token : postData){
            //if token is an operator
            if(isOp(token)){
                //Create a new node
                Node newNode = new Node<T>();
            }
        }
    }    


    /**
     * Inorder Tree traversal
     * @param root
     */
    public void inOrder(Node<T> root){
        inOrder(root.left);
        System.out.println(root.left.toString());
        inOrder(root.right);
    }

    /**
     * Preorder tree traversal
     * @param root
     */
    public void preOrder(Node<T> root){
        System.out.println(root.toString());
        preOrder(root.left);
        preOrder(root.right);

    }

    /**
     * Postorder tree traversal
     * @param root
     */
    public void postOrder(Node<T> root){
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.toString());
    }

    /**
     * @param tree - a passed in expression tree
     * @return a string representation of the tree
     * Overridden toString function
     */
     @Override
     public String toString(ExpressionTree tree){
        StringBuilder result = new StringBuilder(); //stringBuilder is a mutable string holding all the data
        result.append(getClass().getSimpleName());
        System.out.println("This tree's type is " + this.getClass().getSimpleName());
        //Add the tree to the result stringbuilder
        result.append("Infix Expression: " + infixExpr);
        //add postFix expression
        postfixExpr = preOrder(tree);
        result.append("Postfix Expression: " + postfixTree);
        prefixExpr = preOrder(tree);
        //add the preFix expression
        result.append("Prefix Expression: " + prefixExpr);
        return result.toString();
     }
}