package hacker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BFSShortestReach {
    
    static class Edge {
        int u;
        int v;
        
        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    
    public static class Graph {
        
        static final int EDGE_WEIGHT = 6;
        
        private int size;
        List<Edge> edges = new ArrayList<Edge>();
                
        public Graph(int size) {
            this.size = size;
        }

        public void addEdge(int first, int second) {
            edges.add(new Edge(first, second));
        }
        
        public int[] shortestReach(int startId) { // 0 indexed
            int[] reaches = new int[this.size];
            for(int i = 0; i < reaches.length; i++) {
                reaches[i] = -1;
            }
            boolean[] traversed = new boolean[this.size];
            List<Integer> bfs = new ArrayList<Integer>();
            bfs.add(startId);
            traversed[startId] = true;
            reaches[startId] = 0;
            
            while(!bfs.isEmpty()) {
                int nodeInTraverse = bfs.get(0);
                List<Integer> neighbours = getNeighbours(nodeInTraverse);
                Iterator<Integer> it = neighbours.iterator();
                while(it.hasNext()) {
                    int curr = it.next();
                    if(!traversed[curr]) {
                        traversed[curr] = true;
                        reaches[curr] = ((reaches[nodeInTraverse] / EDGE_WEIGHT) + 1) * EDGE_WEIGHT;
                        bfs.add(curr);
                    }
                }
                bfs.remove(0);
            } 
            return reaches;
        }
        
        public List<Integer> getNeighbours(int node) {
            List<Integer> neighbours = new ArrayList<Integer>();
            Iterator<Edge> it = edges.iterator();
            while(it.hasNext()) {
                Edge curr = it.next();
                if(curr.u == node || curr.v == node) {
                    neighbours.add((curr.u == node) ? curr.v : curr.u);
                }
            }
            return neighbours;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}
