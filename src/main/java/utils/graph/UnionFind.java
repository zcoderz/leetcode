package utils.graph;

import graph.DisjointSetsCycleOptimized;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class UnionFind {

    /**
     * the method does union of the vertex x & y
     * from wikipedia :
     * For union by rank, a node stores its rank, which is an upper bound for its height.
     * When a node is initialized, its rank is set to zero. To merge trees with roots x and y,
     * first compare their ranks. If the ranks are different, then the larger rank tree becomes the parent,
     * and the ranks of x and y do not change. If the ranks are the same, then either one can become the parent,
     * but the new parent's rank is incremented by one. While the rank of a node is clearly related to its height,
     * storing ranks is more efficient than storing heights. The height of a node can change during a Find operation,
     * so storing ranks avoids the extra effort of keeping the height correct.
     * @param vertices
     * @param x
     * @param y
     */
    public static void union(Vertex[] vertices, int x, int y)
    {
        int xRank = vertices[find(vertices, x)].getRank();
        int yRank = vertices[find(vertices, y)].getRank();

        if (xRank > yRank) {
            vertices[y].setParent(x);
        } else if (yRank > xRank) {
            vertices[x].setParent(y);
        } else {
            vertices[x].setParent(y);
            //rank increases only when two vertices of same rank are merged together.
            vertices[y].setRank(vertices[y].getRank() + 1);
        }
    }

    /**
     * The method recurses through parent of the vertex until it finds the root
     * @param vertices
     * @param i
     * @return
     */
    public static int find(Vertex[] vertices, int i)
    {
        if (vertices[i].getParent() != i) {
            //compact the parent path so that next call to parent doesnt have to recuse as deep
            vertices[i].setParent(find(vertices, vertices[i].getParent()));
        }
        return vertices[i].getParent();
    }

    // The main function to check whether a given graph
    // contains cycle or not
    public static int isCycle(Vertex[] vertices, Edge[] edges)
    {
        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < edges.length; ++i) {
            int x = find(vertices, edges[i].getSrc());
            int y = find(vertices, edges[i].getDest());
            //if two vertices that arent yet combined roll up to the same parent then the graph
            //by definition must have a cycle.
            if (x == y)
                return 1;
            union(vertices, x, y);
        }
        return 0;
    }

    public static int constructMinSpanningTree(Vertex[] vertices, Edge[] edges)
    {
        int cost = 0;
        //sort the edges by the cost
        Arrays.sort(edges, Comparator.comparingInt(Edge::getCost));
        int numVertices = vertices.length;
        int seenVertices = 0;
        //break when either all edges are exhausted or when all vertices have been joined
        for (int i =0; i < edges.length && seenVertices < (numVertices-1); i++) {
            int x = find(vertices, edges[i].getSrc());
            int y = find(vertices, edges[i].getDest());
            //if the two vertices belong to diff parents then union them together
            if (x != y) {
                union(vertices, x, y);
                cost += edges[i].getCost();
                seenVertices ++;
            }
        }
        if (seenVertices == numVertices -1) {
            return cost;
        } else {
            return -1;
        }
    }

    public static void connectVertices(Vertex[] vertices, Edge[] edges)
    {
        int numVertices = vertices.length;
        int seenVertices = 0;
        //break when either all edges are exhausted or when all vertices have been joined
        for (int i =0; i < edges.length && seenVertices < numVertices-1; i++) {
            int x = find(vertices, edges[i].getSrc());
            int y = find(vertices, edges[i].getDest());
            //if the two vertices belong to diff parents then union them together
            if (x != y) {
                union(vertices, x, y);
                seenVertices++;
            }
        }
    }

    public static int numConnectedVertices(Vertex[] vertices)
    {
        Set<Integer> connectedComponents = new HashSet<>();
        for (int i =0; i < vertices.length; i++) {
            int parent = find(vertices, i);
            connectedComponents.add(parent);
        }
        return connectedComponents.size();
    }

    public static Vertex [] constructVertices(int n) {
        Vertex [] vertices = new Vertex[n];
        for (int i =0; i < n; i++) {
            Vertex v = new Vertex(i, i, 0);
            vertices[i] = v;
        }
        return vertices;
    }

    public static Edge [] constructEdges(int [] [] edgesParam) {
        Edge [] edges = new Edge[edgesParam.length];
        for (int i =0; i < edgesParam.length; i++) {
            Edge edge = new Edge(edgesParam[i][0], edgesParam[i][1]);
            edges[i] = edge;
        }
        return edges;
    }

}