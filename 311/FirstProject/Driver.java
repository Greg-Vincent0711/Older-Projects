import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Driver extends Node {
    public static void main(String[] args) {
        try{
            File testFile = new File("expressions.txt");
            Scanner fileReader =  new Scanner(testFile);
            //While there's an expression
            String fileInfix = fileReader.nextLine();
            while(fileReader.hasNextLine()){
                // //create a binary tree
                // ExpressionTree newTree = new ExpressionTree(fileInfix);
                // //Numbering for each element in the tree
                // System.out.printf("\n %2d:\n", i++);
                // System.out.println(" " + newTree.toString());
                System.out.println(fileInfix);
            }
            fileReader.close();
        }  catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // //Trees for equality test. first and second should be equal
        // String test1 = "(1 + (2 * 3)) + (((4 * 55) + 66) * 77)";
        // String test2 = test1;
        // String test3 = "(2 * (((420 + 63 ) * 53) / (142 + 74)))";
        // ExpressionTree firstTree = new ExpressionTree(test1);
        // ExpressionTree secondTree = new ExpressionTree(test2);
        // ExpressionTree thirdTree = new ExpressionTree(test3);

        // System.out.println(" " + firstTree.toString());
        // System.out.println(" " + secondTree);
        // System.out.println(" " + thirdTree);
        // System.out.println(firstTree.equals(secondTree));
        // System.out.println(secondTree.equals(thirdTree));
    }
}
