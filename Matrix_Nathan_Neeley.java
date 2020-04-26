import java.util.*;

public class Matrix_Nathan_Neeley<V> {
   
   private int[][] adjacencyMatrix; // adjacency matrix declaration
   private Integer[][] reachabilityMatrix; //Integer reachability matrix declaration
   private int[][][] booleanMatrix; //boolean reachability matrix
   
   //non-argument constructor
   public Matrix_Nathan_Neeley() {
   }
   
   //set length of all arrays
   public void setMatrixLength (int numberOfNodes) {
      adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
      reachabilityMatrix = new Integer[numberOfNodes][numberOfNodes];
      booleanMatrix = new int[numberOfNodes][numberOfNodes][numberOfNodes];
   }
   
   //construct adjacency matrix and first dimension of boolean matrix
   public void setAdjacencyMatrix (int[][] adjacencyMatrix) {
      for (int i = 0; i < adjacencyMatrix.length; i++) {
         for (int k = 0; k < adjacencyMatrix[0].length; k++) {
            this.adjacencyMatrix[i][k] = adjacencyMatrix[i][k];
            booleanMatrix[0][i][k] = adjacencyMatrix[i][k];
            reachabilityMatrix[i][k] = 0;
         }
      }
   }
    
    //getter method
   public int[][] getAdjacencyMatrix () {
      return adjacencyMatrix;
   }
    
    //in-degree for all nodes
   public void inDegreeNode() {
      int sum = 0;
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
         for (int k = 0; k < adjacencyMatrix[0].length; k++) {
            if (adjacencyMatrix[k][i] == 1)
               sum = sum + 1;
         }
         System.out.println("Node " + i + " in-degree is " + sum);
         sum = 0;
      }
   }
   
   //out-degree for all nodes
   public void outDegreeNode() {
      int sum = 0;
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
         for (int k = 0; k < adjacencyMatrix[0].length; k++) {
            if (adjacencyMatrix[i][k] == 1) 
               sum = sum + 1;
         }
         System.out.println("Node " + i + " out-degree is " + sum);
         sum = 0;
      }
   }
   
   //determine how many self-loops
   public void numberOfLoops() {
      int loops = 0;
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
         for (int k = 0; k < adjacencyMatrix[0].length; k++) {
            if (adjacencyMatrix[i][k] == 1 && i == k)
               loops++;
         }
      }
      
      System.out.println("Total number of self-loops is " + loops);
   }
   
   //determine how many paths of 1-edge length
   public void oneEdgeLength() {
   
      int numberOfOnes = 0;
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
         for (int k = 0; k < adjacencyMatrix[0].length; k++)
            numberOfOnes += adjacencyMatrix[i][k];
      }
      
      System.out.println("Total number of paths of length 1-edge is " + numberOfOnes);
   }
    
    //deterine how many paths of numberOfNodes - 1 length
   public void numberOfPaths(int numberOfNodes) {
      
      int counter = 0;
      for (int i = 0; i < numberOfNodes; i++) {
         for (int j = 0; j < numberOfNodes; j++) {
            if (booleanMatrix[numberOfNodes-1][i][j] >= 1)
               counter = booleanMatrix[numberOfNodes-1][i][j] + counter;
         }
      }
      
      System.out.println("Total number of paths of length " + numberOfNodes + " edges is " + counter);
   }
   
   //determine how many total paths
   public void totalNumberOfPaths(int numberOfNodes) {
   
      int counter = 0;
      for (int i = 0; i < numberOfNodes; i++) {
         for (int j = 0; j < numberOfNodes; j++)
            counter = counter + reachabilityMatrix[i][j];
      }
      
      System.out.println("Total number of paths of length " + numberOfNodes + "-edge is " + counter);
   }
    
    //setter method for Integer reachability matrix
   public void setReachabilityMatrix(int numberOfNodes) {
      
      resetMatrices(numberOfNodes);
      setAdjacencyMatrix(adjacencyMatrix);
      int counter = 0;
      for (int k = 1; k < numberOfNodes; k++) {
         for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
               while (counter < numberOfNodes) {
                  booleanMatrix[k][i][j] = booleanMatrix[k][i][j] + (booleanMatrix[k-1][i][counter] * adjacencyMatrix[counter][j]);
                  counter++;
               }
               counter = 0;
            }
         }
      }
      
      for (int k = 0; k < numberOfNodes; k++) {
         for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) 
               reachabilityMatrix[i][j] = reachabilityMatrix[i][j] + booleanMatrix[k][i][j];
         }
      }
      
      //print out with formatting
      System.out.print("Integer Reachability Matrix: \n[");
      for (int i = 0; i < reachabilityMatrix.length; i++) {
         if (i == 0)
            System.out.print("[");
         else
            System.out.print(" [");
         for (int j = 0; j < reachabilityMatrix[0].length; j++) {
            if (j == reachabilityMatrix[0].length - 1)
               System.out.print(reachabilityMatrix[i][j]);
            else
               System.out.print(reachabilityMatrix[i][j] + ", ");
         }
         if (i < reachabilityMatrix.length - 1)
            System.out.println("]");
         else
            System.out.print("]");
      }
      System.out.println("]");
   }
   
   public void resetMatrices(int numberOfNodes) {
   
      for (int k = 0; k < numberOfNodes; k++) {
         for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++)
               booleanMatrix[k][i][j] = reachabilityMatrix[i][j] = 0;
         }
      }
   }
   
   //calculate total number of N cycles
   public void calculateNCycles() {
      int counter = 0;               
               
      int cycles = 0;
      for(int i = 0; i < adjacencyMatrix.length; i++){
         counter = 0;
         for(int j = 0; j < adjacencyMatrix[0].length; j++){
            if(i == j && booleanMatrix[adjacencyMatrix.length - 1][i][j] > 0)  
               counter = booleanMatrix[adjacencyMatrix.length - 1][i][j] + counter;
         }
         cycles = cycles + counter;
      }
      System.out.println("Total number of cycles of length " + adjacencyMatrix.length + " edges is " + cycles);
   }
   
   //calculate total number of cycles
   public void calculateAllCycles() {
      int counter = 0;              
               
      int cycles = 0;
      for (int k = 0; k < booleanMatrix.length; k++) {
         for(int i = 0; i < booleanMatrix.length; i++){
            counter = 0;
            for(int j = 0; j < booleanMatrix.length; j++){
               if(i == j && booleanMatrix[k][i][j] > 0)
                  counter = booleanMatrix[k][i][j] + counter;
            }
            cycles = cycles + counter;
         }
      }
      System.out.println("Total number of cycles of length " + adjacencyMatrix.length + "-edge is " + cycles);
   }
               
   public void output() {
      
      setReachabilityMatrix(booleanMatrix.length);
      inDegreeNode();
      outDegreeNode();
      numberOfLoops();
      calculateNCycles();
      oneEdgeLength();
      numberOfPaths(booleanMatrix.length);
      totalNumberOfPaths(booleanMatrix.length);
      calculateAllCycles();
   }
}
     
