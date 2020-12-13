package trees;

public class DisjointSetsCycleOptimized {
    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // /collection of all edges

    class Edge
    {
        int src, dest;
    };

    class Subset {
        int parent;
        int rank;
    }

    // Creates a graph with V vertices and E edges
    DisjointSetsCycleOptimized(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i < e; ++i) {
            edge[i] = new Edge();
        }
    }

    // A utility function to find the subset of an element i
    int find(Subset subsets[], int i)
    {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return i;
    }

    // A utility function to do union of two subsets
    void Union(Subset subset[], int x, int y)
    {
        int xRank = find(subset, x);
        int yRank = find(subset, y);

        if (xRank > yRank) {
            subset[y].parent = x;
        } else if (yRank > xRank) {
            subset[x].parent = y;
        } else {
            subset[x].parent = y;
            subset[x].rank++;
        }

    }

    // The main function to check whether a given graph
    // contains cycle or not
    int isCycle( DisjointSetsCycleOptimized graph)
    {
        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[graph.V];

        // Initialize all subsets as single element sets
        for (int i=0; i<graph.V; ++i) {
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.E; ++i)
        {
            int x = graph.find(subsets, graph.edge[i].src);
            int y = graph.find(subsets, graph.edge[i].dest);
            if (x == y)
                return 1;
            graph.Union(subsets, x, y);
        }
        return 0;
    }

    // Driver Method
    public static void main (String[] args)
    {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2*/
        int V = 3, E = 3;
        DisjointSetsCycleOptimized graph = new DisjointSetsCycleOptimized(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph)==1)
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }
}
