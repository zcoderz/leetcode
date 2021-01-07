package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * A graph is a valid tree if the edges dont have a cycle and all vertices are connected in same path
 */
public class IsGraphValidTree {

    public static void main(String [] args) {
        int [][] edges = {{0,1}, {0,2}, {0,3}, {1,4}};
        IsGraphValidTree valid = new IsGraphValidTree();
        boolean res = valid.validTree(5, edges);
        System.out.println(res);

        int [][] edges1 = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        res = valid.validTree(5, edges1);
        System.out.println(res);
    }

    public boolean validTree(int n, int[][] edges) {
        Vertex [] vertices = UnionFind.constructVertices(n);
        Edge[] edges1 = UnionFind.constructEdges(edges);
        boolean cycle = UnionFind.isCycle(vertices, edges1) == 1;
        if (cycle) return false;
        return UnionFind.numConnectedVertices(vertices) == 1;

    }
}
