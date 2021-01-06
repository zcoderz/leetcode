package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * Class to calculate number of connected components in a graph
 *
 * simple approach is to use union find.
 */
public class NumConnectedComponents {

    public static void main(String [] args) {
        //int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        //int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int [][] edges = {{0,1},{2,3},{1,2}};
        NumConnectedComponents numConnectedComponents = new NumConnectedComponents();
        int count = numConnectedComponents.countComponents(4, edges);
        System.out.println(count);
    }

    public int countComponents(int n, int[][] edgesParam) {
        Vertex [] vertices = new Vertex[n];
        for (int i =0; i < n; i++) {
            Vertex v = new Vertex(i, i, 0);
            vertices[i] = v;
        }
        Edge [] edges = new Edge[edgesParam.length];
        for (int i =0; i < edgesParam.length; i++) {
            Edge edge = new Edge(edgesParam[i][0], edgesParam[i][1]);
            edges[i] = edge;
        }
        UnionFind.connectVertices(vertices, edges);
        return UnionFind.numConnectedVertices(vertices);
    }


}
