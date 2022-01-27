/**
 * @author Gregory Vincent
 * @date 1/26/22
 * Contains a generic element, reference to left and right childw
 */
public class Node<T> {
    
    public T element; //create a generic element
    private Node<T> left; //left child
    private Node<T> right; //right child

    //default constructor
    public Node(){

    }

    @overload
    public Node(T element, Node<T>left, Node right){
        this.element = element;
        this.left = left;
        this.right = right;
    }
    //getter and setter for node elements
    public T getElement(){
        return this.element;
    }

    public void setElement(T element){
        this.element = element;
    }

    //getters and setters for nodes
    public Node<T> getLeft(){
        return this.left;
    }

    public Node<T> getRight(){
        return this.right;
    }

    public void setLeft(Node<T> left){
        this.left = left;
    }

    public void setRight(Node<T> right){
        this.right = right;
    }

    @Override
    public String toString(){
        return this.element.toString() + " " + this.left.toString() + " " + this.right.toString();
    }

    @Override
    public void equals(){
        return;
    }

}
