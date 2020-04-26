// Name:       Nathan Neeley
// Class:      CS 5040
// Term:       Spring 2020
// Instructor: Dr. Haddad
// Assignment: 7
// IDE:        jGrasp

import java.util.*;

public interface Graph_Nathan_Neeley<V> //interface of all abstract method that will be implemented in AbstractGraph
{
   public int getSize();
   
   public void clear();
   
   public boolean addVertex(V vertex);
   
   public AbstractGraph_Nathan_Neeley<V>.Tree dfs(int v);
}
