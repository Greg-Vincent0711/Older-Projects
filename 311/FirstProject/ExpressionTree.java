public class ExpressionTree implements Node {
    private Node<T> root;
    private T infix;
    
    //empty constructor
    public ExpressionTree(){

    }

    //Overloaded constructor
    public ExpressionTree(Node<T> root, T infix){
        this.root = root;
        this.infix = infix;
    }

    //getters and setters for root and infix expression
    public Node<T> getRoot(){
        return this.root;
    }

    public void setRoot(Node<T> root){
        this.root = root;
    }

    public T getInfix(){
        return this.infix;
    }
    
    public void setInfix(T infix){
        this.infix = infix;
    }

    private void preOrder(Node<T> root){
        if(root != null){
        }
    }
}
