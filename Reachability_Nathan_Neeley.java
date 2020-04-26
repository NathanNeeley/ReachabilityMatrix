import java.util.*;
import java.io.*;

public class Reachability_Nathan_Neeley
{
   public static void main (String[] args)
   {
      Scanner input = new Scanner(System.in);
   
      int option = 0; 
      int numberOfNodes = 0;
      List<AbstractGraph_Nathan_Neeley.Edge> edgeList = new ArrayList<>(); //edgeList List of .Edge type
      Matrix_Nathan_Neeley<Integer> matrix = new Matrix_Nathan_Neeley(); //matrix object
      
   
      do {
         //interactive menu
         System.out.println("\n-----MAIN MENU-----");
         System.out.println("0 - Exit Program");
         System.out.println("1 - Enter Graph Data");
         System.out.println("2 - Print Outputs");
         System.out.print("Enter menu option: ");
         option = input.nextInt();
         System.out.println();
         
         //user input options
         switch (option) {
         
            case 0: //program is terminated
               System.out.println("Program is terminated.");
               break;
            case 1: //enter in graph data
               edgeList.clear();
               System.out.print("Enter number of nodes: "); //ask for number of nodes
               numberOfNodes = input.nextInt();
               if (numberOfNodes > 5 || numberOfNodes < 0) { //rerun if too large
                  System.out.println("Number of nodes should be positive and 5 or less.");
                  continue;
               }
               int[][] adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
               matrix.setMatrixLength(numberOfNodes); //set matrix length
               System.out.println("\nAdjacency Matrix");
               for (int i = 0; i < adjacencyMatrix.length; i++) { //ask for user input for adjacency matrix
                  for (int k = 0; k < adjacencyMatrix[0].length; k++) {
                     System.out.print("Enter A[" + i + "][" + k + "]: ");
                     adjacencyMatrix[i][k] = input.nextInt();
                     switch (adjacencyMatrix[i][k]) {
                     
                        case 0:
                           break;
                        case 1:
                           edgeList.add(new AbstractGraph_Nathan_Neeley.Edge(i, k));
                           break;
                     }
                  }
               }
               
               System.out.println();
               matrix.setAdjacencyMatrix(adjacencyMatrix); //set adjacency matrix
               break;
            case 2:
               if (edgeList.isEmpty()) //error message if no data in graph
                  System.out.println("No data in graph. Re-enter menu option.");
               else {
                  Graph_Nathan_Neeley<Integer> graph = new UnweightedGraph_Nathan_Neeley<>(edgeList, matrix.getAdjacencyMatrix().length); //construct graph
                  matrix.output(); //print out all information about the graph from the matrix or matrices
               }
               break;
            default: //print error message and rerun if option not in list
               System.out.println("Option not in list. Re-enter menu option.\n");
         
         }
      } while (option != 0); //rerun until 0 is entered
   }
}

         
         
         
