package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * IMP-1 : cycle detection in graphs via disjoint sets is good practice
 */
public class DisjointSetsCycleOptimized {

    private int numVertices, numEdges;    // V-> no. of vertices & E->no.of edges
    private Edge[] edge; // /collection of all edges
    // Creates a graph with V vertices and E edges
    public DisjointSetsCycleOptimized(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        edge = new Edge[this.numEdges];
        for (int i = 0; i < numEdges; ++i) {
            edge[i] = new Edge();
        }
    }

    // Driver Method
    public static void main(String[] args) {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2*/
        int V = 3, E = 3;
        DisjointSetsCycleOptimized graph = new DisjointSetsCycleOptimized(V, E);

        // add edge 0-1
        graph.edge[0].setSrc(0);
        graph.edge[0].setDest(1);

        // add edge 1-2
        graph.edge[1].setSrc(1);
        graph.edge[1].setDest(2);

        // add edge 0-2
        graph.edge[2].setSrc(0);
        graph.edge[2].setDest(2);

        if (graph.isCycle(graph) == 1)
            System.out.println("graph contains cycle");
        else
            System.out.println("graph doesn't contain cycle");
    }

    // The main function to check whether a given graph
    // contains cycle or not
    int isCycle(DisjointSetsCycleOptimized graph) {
        // Allocate memory for creating V subsets
        Vertex[] vertices = new Vertex[graph.numVertices];
        // Initialize all subsets as single element sets
        for (int i = 0; i < graph.numVertices; ++i) {
            vertices[i].setParent(i);
            vertices[i].setRank(0);
        }
        return UnionFind.isCycle(vertices, edge);
    }
}
