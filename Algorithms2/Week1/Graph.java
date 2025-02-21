package Algorithms2.Week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;



public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;


    // create an empty graph with V verticies
    public Graph(int V) {
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // create a graph from an input stream
    public Graph(In in) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add an edge v-w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // verticies adjacent to v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // number of verticies
    public int V() {
        return this.V;
    }

    // number of edges
    public int E() {

    }

    // string representation
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // compute the degree of V
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    // compute maximum degree
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    // compute average degree
    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }
    
    // countm self-loops
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count/2;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adHeyu(v)) {
                StdOut.println(v + "-" + w);
            }
        }
    }
}
