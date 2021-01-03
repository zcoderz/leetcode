package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * the problem uses min spanning trees (union find) to connect the cities (vertices)
 * together while traversing the path using edges with minimum cost.
 */
public class ConnectCitiesWithMinCost {

    public static void main(String [] args) {
        int [][] connections =  {{1,2,5},{1,3,6},{2,3,1}};
        ConnectCitiesWithMinCost cities = new ConnectCitiesWithMinCost();
        int cost = cities.minimumCost(3, connections);
        System.out.println(cost);

        int [][] connections2 = {{1,2,3},{3,4,4}};
        cost = cities.minimumCost(4, connections2);
        System.out.println(cost);
    }

    public int minimumCost(int numVertices, int[][] connections) {
        Edge [] edges = new Edge[connections.length];
        for (int i = 0; i < connections.length; i++) {
            edges[i] = new Edge(connections[i][0]-1, connections[i][1]-1, connections[i][2]);
        }
        Vertex [] vertices = new Vertex[numVertices];
        for (int i =0; i < numVertices; i++) {
            //creates a new vertex setting parent to itself with rank 0
            vertices[i] = new Vertex(i, i, 0);
        }
        return  UnionFind.constructMinSpanningTree(vertices, edges);
    }

}
