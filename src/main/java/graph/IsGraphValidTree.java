package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * 261. Graph Valid Tree
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * A graph is a valid tree if the edges dont have a cycle and all vertices are connected in same path
 *
 * IMP-1: Good practice question on graphs
 */
public class IsGraphValidTree {

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        IsGraphValidTree valid = new IsGraphValidTree();
        boolean res = valid.validTree(5, edges);
        System.out.println(res);

        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        res = valid.validTree(5, edges1);
        System.out.println(res);
    }

    public boolean validTree(int n, int[][] edges) {
        Vertex[] vertices = UnionFind.constructVertices(n);
        Edge[] edges1 = UnionFind.constructEdges(edges);
        boolean cycle = UnionFind.isCycle(vertices, edges1) == 1;
        if (cycle) return false;
        return UnionFind.numConnectedVertices(vertices) == 1;

    }
}
