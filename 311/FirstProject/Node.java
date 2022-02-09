
/**
 * @author Gregory Vincent
 * @date 1/26/22
 * Contains a generic element, reference to left and right childw
 */
public class Node<T extends Comparable>{
    
    private T element; //create a generic element
    private Node<T> left; //left child
    private Node<T> right; //right child

    //default constructor
    public Node(){

    }

    public Node(T element){
        this.element = element;
    }
    //getter and setter for node elements
    public T getElement(){
        return element;
    }

    public void setElement(T element){
        this.element = element;
    }

    //getters and setters for nodes
    public Node<T> getLeft(){
        return left;
    }

    public Node<T> getRight(){
        return right;
    }

    public void setLeft(Node<T> left){
        this.left = left;
    }


    public void setRight(Node<T> right){
        this.right = right;
    }

    @Override
    public String toString(){
        return this.element.getClass().getSimpleName() + " " + 
        this.left.getClass().getSimpleName() + " " + this.right.getClass().getSimpleName();
    }

    /**
     * @param firstRef 
     * @param secondRef - both parameters represent any data type
     * @return boolean
     */
    private boolean helperEquals(Node<T> firstRef, Node<T> secondRef){
        if(firstRef == secondRef){
            System.out.println("Nodes are equal.");
            return true;
        }
        if(firstRef == null || secondRef == null){
            System.out.println("Nodes cannot be equal if null.");
            return false;
        }
        //Return an equality check for each pair of nodes in each tree
        return firstRef.getElement().equals(secondRef.getElement()) &&
            firstRef.getLeft().getElement().equals(secondRef.getLeft().getElement()) &&
            firstRef.getRight().getElement().equals(secondRef.getRight().getElement());
    }

    /**
     * @return boolean
     * @param testNode
     * Checks equality of two different objects
     */
    public boolean equals(Node<T> testNode){
        if(testNode.getClass() != this.getClass()){
            System.out.println("Object isn't a node.");
            return false;
        }
        return helperEquals(testNode, this);
    }
}

