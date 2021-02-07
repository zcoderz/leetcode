package graph;

import utils.graph.Edge;


public class DisjointCycleDetection {
    int numVertices, numEdges;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // /collection of all edges
    // Creates a graph with V vertices and E edges
    DisjointCycleDetection(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        edge = new Edge[this.numEdges];
        for (int i = 0; i < numEdges; ++i)
            edge[i] = new Edge();
    }

    // Driver Method
    public static void main(String[] args) {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */
        int V = 3, E = 3;
        DisjointCycleDetection graph = new DisjointCycleDetection(V, E);

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

    // A utility function to find the subset of an element i
    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    // A utility function to do union of two subsets
    void Union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }


    // The main function to check whether a given graph
    // contains cycle or not
    int isCycle(DisjointCycleDetection graph) {
        // Allocate memory for creating V subsets
        int parent[] = new int[graph.numVertices];

        // Initialize all subsets as single element sets
        for (int i = 0; i < graph.numVertices; ++i)
            parent[i] = -1;

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.numEdges; ++i) {
            int x = graph.find(parent, graph.edge[i].getSrc());
            int y = graph.find(parent, graph.edge[i].getDest());

            if (x == y)
                return 1;

            graph.Union(parent, x, y);
        }
        return 0;
    }

}

