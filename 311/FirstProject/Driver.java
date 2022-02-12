// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.io.File;
import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) {
        //Testing equals functionality
        String test1 = "(5*(((1+3)*9)/(1+4)))";
        String test2 = "(5*(((1+3)*9)/(1+4)))";
        String test3 = "(1+(2*3))+(((4*55)+66)*77)";
        ExpressionTree firstTree = new ExpressionTree(test1);
        //First tree and Second tree should be equal
        ExpressionTree secondTree = new ExpressionTree(test2);
        ExpressionTree thirdTree = new ExpressionTree(test3);
        System.out.println(firstTree.equals(secondTree));
        System.out.println(firstTree.equals(thirdTree));
        int count = 1;
        File readFile = new File("expressions.txt");
        //creating trees for each expression
        try {
            String currentLine;
            BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
            while ((currentLine = fileReader.readLine()) != null) {
                System.out.println("New Tree Created... " + count);
                System.out.println("_________________________________");
                System.out.println("                                 ");
                ExpressionTree newTree = new ExpressionTree(currentLine);
                // Print out the the traversals of each new tree
                System.out.println(newTree.toString());
                count++;
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

