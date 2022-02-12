
/**
 * @author Gregory Vincent
 * @date 1/26/22
 * Contains a generic element, reference to left and right childw
 */
public class Node<T>{
    
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
        return this.element;
    }

    public void setElement(T element){
        this.element = element;
    }

    /**
     * @return left node reference 
     */
    public Node<T> getLeft(){
        return this.left;
    }

    /**
     * 
     * @return right node reference 
     */
    public Node<T> getRight(){
        return this.right;
    }

    /**
     * Setter function
     * @param left node
     */
    public void setLeft(Node<T> left){
        this.left = left;
    }

    
    /**
     * Setter function
     * @param right node
     */
    public void setRight(Node<T> right){
        this.right = right;
    }

    /**
     * returns string representation of tree data
     */
    @Override
    public String toString(){
        String result;
        if(this.element != null && this.left != null && this.right != null){
            result = this.element.toString() + this.left.toString() + this.right.toString();
            System.out.println(result);
            return result;
        }
        else if(this.element == null){
            return null;
        }
        else if(this.element != null && this.right == null && this.left != null){
            result = this.element.toString() + this.left.toString();
            System.out.println(result);
            return result;
        } 
        return " ";
    }
    
    /**
     * @return boolean
     * @param this, check2 - whatever nodes are being passed into to compare
     * Checks equality of two different objects
     */
    public boolean equals(Node check2) {
        //neither can be null
        if(this == null || this == null){
            return false;
        } 
        // check the equality of the right and left nodes of each
        else if(getElement().equals(check2.getElement())){
            return true;
        }
        return false;
            
    }
}
