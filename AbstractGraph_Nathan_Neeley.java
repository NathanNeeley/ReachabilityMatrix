import java.util.*;

public abstract class AbstractGraph_Nathan_Neeley<V> implements Graph_Nathan_Neeley<V> //abstract class that implements Graph interface
{
   protected List<V> vertices = new ArrayList<>();
   protected List<List<Integer>> neighbors;
   
   //constructor with list of edges and numberOfVertices
   protected AbstractGraph_Nathan_Neeley(List<Edge> edges, int numberOfVertices) { 
      for (int i = 0; i < numberOfVertices; i++)
         addVertex((V)(new Integer(i)));
      
   }
   
   //method to add vertex
   @Override
   public boolean addVertex(V vertex) {
      if(!vertices.contains(vertex)) {
         vertices.add(vertex);
         return true;
      }
      else
         return false;
   }
   
   //method to get size
   @Override
   public int getSize() {
      return vertices.size();
   }
   
   //clear both vertices and neighbors
   @Override
   public void clear() {
      vertices.clear();
      neighbors.clear();
   }
   
   //inner class for Edges
   public static class Edge {
      public int u;
      public int v;
      
      public Edge(int u, int v) {
         this.u = u;
         this.v = v;
      }
      
      @Override
      public String toString() {
         return "(" + u + ", " + v + ")";
      }
   }
  
   //inner class to perform depth-first search
   public Tree dfs(int v) {
      List<Integer> searchOrder = new ArrayList<>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++)
         parent[i] = -1;
         
      boolean[] isVisited = new boolean[vertices.size()];
      
      dfs(v, parent, searchOrder, isVisited);
      
      return new Tree(v, parent, searchOrder);
   }
   
   //helper method for dfs
   private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
   
      searchOrder.add(u);
      isVisited[u] = true;
   
      for (int e : neighbors.get(u)) {
         if (!isVisited[e]) {
            parent[e] = u;
            dfs(u, parent, searchOrder, isVisited);
         }
      }
   }
   
   //inner class to construct Tree and Tree operations
   public class Tree {
      private int root;
      private int[] parent;
      private List<Integer> searchOrder;
      
      public Tree(int root, int[] parent, List<Integer> searchOrder) {
         this.root = root;
         this.parent = parent;
         this.searchOrder = searchOrder;
      }
      
      public int getRoot() {
         return root;
      }
      
      public int getParent(int v) {
         return parent[v];
      }
      
      public List<Integer> getSearchOrder() {
         return searchOrder;
      }
      
      public int getNumberOfVerticesFound() {
         return searchOrder.size();
      }
      
      public List<V> getPath(int index) {
         ArrayList<V> path = new ArrayList<>();
         
         do {
            path.add(vertices.get(index));
            index = parent[index];
         
         } while (index != -1);
         
         return path;
      }
   }
}
