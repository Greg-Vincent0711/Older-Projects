import java.util.*;
public class ExpressionTree{
    //Beginning of the search tree
    private Node root = new Node<>();
    //Whatever message is passed in
    private String infixExpr;
    
    //default constructor
    public ExpressionTree(){

    }
    public ExpressionTree(String infixExpr){
        this.infixExpr = infixExpr;
        //a new tree automatically converts to postFix
        this.root = toTree(toPostfix(infixExpr));
    }

    /** 
     * @return Node<T> type
     */
    public Node getRoot(){
        return this.root;
    }
    /** 
     * @return generic type T
     */
    public String getInfix(){
        return this.infixExpr;
    }

    /** 
     * @return boolean
     * @param infix String 
     * Helps to see if a character is a number
    */
    private boolean isOperand(String infix){
        if(infix.contains("+") || infix.contains("-") || 
            infix.contains("/") ||infix.contains("*") ||
            infix.contains("(") ||infix.contains(")")){
            return false;
        }
        else 
        {
            return true;
        }
            
    }

    private boolean isOperator(String infix){
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
	private int opPrecedence(String infix){
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
        Stack<String>tokenStack = new Stack<String>();
        while(token.hasMoreTokens()){
                String infixToken = token.nextToken();
                if(isOperand(infixToken)){
                    //add straight to resulting arrayList
                    postFixList.add(infixToken);    
                }
                else if(infixToken == "("){
                    tokenStack.push(infixToken);
                }
                else if(infixToken == ")"){
                    //while newewst stack element is not open paren
                    while(tokenStack.peek() != "("){
                        //pop from the stack, add to the final arraylist
                        postFixList.add(tokenStack.pop());
                    }
                    //pop off the stack
                    tokenStack.pop();
                }
                else if (infixToken == ("+")||infixToken == ("-")||
                        infixToken == ("*")||infixToken == ("/"))
                {
                    if(tokenStack.isEmpty()){
                            //add next token to the stack
                            tokenStack.push(infixToken);
                    }
                    else{   
                            String tokenPeek = tokenStack.peek();
                            if(tokenPeek == "("){
                                tokenStack.push(infixToken);
                                continue;
                            } 
                            //latest stack char precedence compared to remaining token  
                            if(opPrecedence(tokenPeek) >= opPrecedence(infixToken)){
                                while(!tokenStack.isEmpty()){
                                    postFixList.add(tokenStack.pop());
                                    if(tokenStack.isEmpty()){
                                            tokenStack.push(infixToken);
                                            break;
                                        }
                                    else if(tokenStack.peek() == "("){
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
    public Node toTree(String postFix){
        //helper variable for the for lopp
        //temp data structure to hold the nodes
        Stack nodeStack = new Stack<Node>();
        //split the postfix expression by spaces
        String [] postData = postFix.split("\\s+");
        //iterate through the data
        for(String token : postData){
            if(isOperand(token)){
                //Create a new node
                Node newNode = new Node<>();
                //push onto stack
                nodeStack.push(newNode);
            }
            else if(isOperator(token)){
                //pop two nodes from the stack
                Node first = (Node)nodeStack.pop();
                Node second = (Node)nodeStack.pop();
                Node root = new Node<>();
                //set it's right and left
                root.setRight(first);
                root.setLeft(second);
                nodeStack.push(root);
            }
        }
        return (Node)nodeStack.pop();
    }    

    /**
     * Inorder Tree traversal
     * @param inNode - node
     */
    public Node inOrder(Node inNode){
        if(inNode == null){
            System.out.println("Parameter node is null.");
        }
        inOrder(root.getLeft());
        System.out.println(inNode.getElement());
        inOrder(root.getRight());
        return inNode;
    }

    /**
     * Preorder tree traversal
     * @param preNode - node
     */
    public Node preOrder(Node preNode){
        if(preNode == null){
            System.out.println("Parameter node is null.");
        }
        System.out.println(preNode.getElement());
        preOrder(preNode.getLeft());
        preOrder(preNode.getRight());
        return preNode;
    }

    /**
     * Postorder tree traversal
     * @param postNode - node
     */
    public Node postOrder(Node postNode){
        if(postNode == null){
            System.out.println("Parameter node is null.");
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.println(root.getElement());
        return postNode;
    }

    /**
     * 
     * @param conversionNode
     * @return string representation of a node
     * Helper function for saving tree traversal data
     */
    private String nodeToString(Node conversionNode){
        if(conversionNode == null){
            return null;
        }
        else{
            return conversionNode.getElement().toString();
        }
    }

    /**
     * @param tree - a passed in expression tree
     * @return a string representation of the tree and all other attributes needed
     * Overridden toString function
     */
    @Override
     public String toString(){
     
        //stringBuilder is a mutable string holding all the data
        StringBuilder result = new StringBuilder(); 
        //Temp stringbuilder for different traversals
        // StringBuilder traversal = new StringBuilder();
        result.append("Type: " + getClass().getSimpleName());

        //Add each tree to traversal stringbuilder
        result.append("Infix Expression: " + infixExpr);
        String preResult = nodeToString(preOrder(root));
        result.append("Prefix Expression: " + preResult);
        String postResult = nodeToString(postOrder(root));
        result.append("Postfix Expression: " + postResult);
        // result.append(traversal);
        //reset the length each time after
        // traversal.setLength(0);

        // traversal.append("Postfix Expression: " + postOrder(root));
        // result.append(traversal);
        // traversal.setLength(0);

        // traversal.append("Prefix Expression: " + preOrder(root));
        // result.append(traversal);
        // traversal.setLength(0);
        return result.toString();
    

     }
}
