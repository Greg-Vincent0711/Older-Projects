/**
 * @author Gregory Vincent
 * @date 1/28/2022
 */
import java.util.*;
public class ExpressionTree{
    //Beginning of the search tree
    private Node<String> root;
    //Whatever message is passed in
    private String infixExpr;
    
    //default constructor
    public ExpressionTree(){

    }
    public ExpressionTree(String infixExpr){
        this.infixExpr = infixExpr;
        //a new tree automatically converts to postFix
        root = toTree(toPostFix(infixExpr));
    }

    /** 
     * @return Node<T> type
     */
    public Node<String> getRoot(){
        return root;
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
        if(infix.equals("+") || infix.equals("-") || 
            infix.equals("/") || infix.equals("*") ||
            infix.equals("(") ||infix.equals(")")) {
            return false;
        }
        else{
            return true;
        }
            
    }

    /** 
	 * Helper function for all expression elements
	 * @param infix
	 * @return integer 
    */
	private int opPrecedence(String infix){
		if(infix.equals("+") || infix.equals("-")){
            return 1;
        }
        else if(infix.equals("*") || infix.equals("/")){
            return 2;
        }
        return 0;
    }
	
    /**
     * 
     * @param infix -  String
     * @return an arraylist as postfix list
     */
    public ArrayList<String> toPostFix(String infix){
        //Holds final result
        ArrayList<String> postFixList = new ArrayList<String>();
        //splitting the string into tokens
        StringTokenizer token = new StringTokenizer(infix,"*/+-()",true);
        //infix to postfix stack
        Stack<String>tokenStack = new Stack<String>();
        //while there are more tokens in the tokenizer
        while(token.hasMoreTokens()){
                //store each token in the infix string
                String infixToken = token.nextToken();
                //if the token is an operand
                if(isOperand(infixToken)){
                    //add it to the result
                    postFixList.add(infixToken);    
                }
                //if newest token is an open parenthesis
                else if(infixToken.equals(")")){
                    //while the newest stack token is not an open parenthesis
                    while(!tokenStack.peek().equals("(")){
                        postFixList.add(tokenStack.pop());
                    }
                    tokenStack.pop();
                }
                //whiile the next token is an open parenthesis
                else if(infixToken.equals("(")){
                    //push the token onto the stack
                    tokenStack.push(infixToken);
                }
                else{
                    //while stack is not empty and the next stack token is not an open paren
                    while(!tokenStack.isEmpty() && !tokenStack.peek().equals("(")){
                        //if the precendece of the incoming token is less than or equal to the stack peek precedence
                        if(opPrecedence(infixToken) <= opPrecedence(tokenStack.peek())){
                            //pop the stack's operator and add it to the result
                            postFixList.add(tokenStack.pop());
                        }
                    }
                    //push the infixtoken to the stack
                    tokenStack.push(infixToken);
                }
            }
            //while the stack is empty
            while(!tokenStack.isEmpty()){
                //add what remains
                postFixList.add(tokenStack.pop());
            }
            //return the result as a string
            return postFixList;
        }        


  /**
     * @param postFix
     * @return Node<T> reference to the newly formed tree
     * StringTokenizer token = new StringTokenizer(infix,"()+*-/",true);
     */
    public Node<String> toTree(ArrayList<String> postFix){
        //helper variable for the for lopp
        //temp data structure to hold the nodes
        Stack<Node<String>> nodeStack = new Stack<Node<String>>();
        String token;
        //split the postfix expression by spaces
        for(int i = 0; i < postFix.size(); i++){
            token = postFix.get(i);
            if(isOperand(token)){
                //Create a new node and push onto stack
                nodeStack.push(new Node<String>(token));
            }
            else{
                Node<String> root = new Node<String>(token);
                //pop two nodes from the stack
                Node<String> first = nodeStack.pop();
                Node<String> second = nodeStack.pop();
                //set it's right and left
                root.setRight(second);
                root.setLeft(first);
                nodeStack.push(root);
            }
        }
        root = nodeStack.pop();
        return root;
    } 

    /**
     * @return ArrayList<String> - list of data
     */
    public ArrayList<Node<String>> printinOrder(){
        ArrayList<Node<String>> inList = new ArrayList<Node<String>>();
        inOrder(root,inList);
        return inList;
    }

    /**
     * 
     * @param inNode - root passed in
     * @param inList - array list to add data to
     */
    private void inOrder(Node<String> inNode, ArrayList<Node<String>> inList){
        if(inNode != null){
            inOrder(inNode.getLeft(),inList);
            inList.add(inNode);
            inOrder(inNode.getRight(),inList);
        }
    }

    /**
     * Publicly called function for preOrder
     * @return ArrayList<Node<String>>
     */
    public ArrayList<Node<String>> printpreOrder(){
        ArrayList<Node<String>> preList = new ArrayList<Node<String>>();
        preOrder(root, preList);
        return preList;
    }

    /**
     * PreOrder tree traversal
     * @param preNode - root node
     * @param preList - list to append data to
     */
    private void preOrder(Node<String> preNode, ArrayList<Node<String>> preList){
        if(preNode != null){
            preList.add(preNode);
            preOrder(preNode.getLeft(),preList);
            preOrder(preNode.getRight(),preList);
        }
    }

    /**
     * Publicly called postOrder method
     * @return ArrayList<String>
     */
    public ArrayList<Node<String>> printpostOrder(){
        ArrayList<Node<String>> postList = new ArrayList<Node<String>>();
        postOrder(root, postList);
        return postList;
    }

    /**
     * @param postNode - root passed in
     * @param postList - list to append data to
     */
    private void postOrder(Node<String> postNode, ArrayList<Node<String>> postList){
        if(postNode != null){
            postOrder(postNode.getLeft(),postList);
            postOrder(postNode.getRight(),postList);
            postList.add(postNode);
        } 
    }

    /**
     * @return string representation of a tree
     */
    @Override
    public String toString(){
        ArrayList<Node<String>> inList = printinOrder();
        String inOrderStr = "";
        for(int i = inList.size() - 1; i >= 0; i--){
            inOrderStr = inOrderStr + " " + inList.get(i).getElement();
        }
        // System.out.println(inOrderStr);
        ArrayList<Node<String>> preList = printpreOrder();
        String preOrderStr = "";
        for(int i = preList.size() - 1; i >= 0; i--){
            preOrderStr = preOrderStr + " " + preList.get(i).getElement();
        }
        // System.out.println(preOrderStr);
        ArrayList<Node<String>> postList = printpostOrder();
        String postOrderStr = "";
        for(int i = postList.size() - 1; i >= 0; i--){
            postOrderStr = postOrderStr + " " + postList.get(i).getElement();
        }
        // System.out.println(postOrderStr);

        String result = "Tree type: " + this.getClass().getSimpleName() + "\n" +
                        "Infix Expression:" + infixExpr + "\n" +
                        "Inorder Traversal:" + inOrderStr + "\n" + 
                        "Preorder Traversal:" + preOrderStr + "\n" +
                        "Postorder Traversal:" + postOrderStr + "\n";
        return result;
     }

    public boolean equals(ExpressionTree Test){
        ArrayList<Node<String>> thisList = this.printpostOrder();
        ArrayList<Node<String>> testList = Test.printpostOrder();
        //compare list sizes
        if(thisList.size() != testList.size()){
            boolean answer = false;
            System.out.println("Lists are of unequal size, so " + answer + ".");
            return answer;
        }
        else{
            for(int i = 0; i < testList.size(); i++){
                //compare each element
                if(thisList.get(i).getElement() == testList.get(i).getElement()){
                    boolean answer2 = false;
                    System.out.println("Elements aren't the same in each list " + answer2 + ".");
                    return answer2;
                }
            }
            boolean answer3 = true;
            System.out.println("Lists are of the same size and have the same elements, so " + answer3 + ".");
            return answer3;
        }
    }
}