package graph;

import utils.graph.Edge;
import utils.graph.UnionFind;
import utils.graph.Vertex;

/**
 * 1135. Connecting Cities With Minimum Cost
 *
 * There are N cities numbered from 1 to N.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect
 * city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is
 * the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1)
 * that connects those two cities together.  The cost is the sum of the connection costs used.
 * If the task is impossible, return -1.
 *
 * Example 1:
 *
 *
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * the problem uses min spanning trees (union find) to connect the cities (vertices) together while traversing the path
 * using edges with minimum cost.
 *
 * IMP-2 : Excellent question to practice union find
 */
public class    ConnectCitiesWithMinCost {

    public static void main(String[] args) {
        int[][] connections = {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        ConnectCitiesWithMinCost cities = new ConnectCitiesWithMinCost();
        int cost = cities.minimumCost(3, connections);
        System.out.println(cost);

        int[][] connections2 = {{1, 2, 3}, {3, 4, 4}};
        cost = cities.minimumCost(4, connections2);
        System.out.println(cost);
    }

    public int minimumCost(int numVertices, int[][] connections) {
        Edge[] edges = new Edge[connections.length];
        for (int i = 0; i < connections.length; i++) {
            edges[i] = new Edge(connections[i][0] - 1, connections[i][1] - 1, connections[i][2]);
        }
        Vertex[] vertices = new Vertex[numVertices];
        for (int i = 0; i < numVertices; i++) {
            //creates a new vertex setting parent to itself with rank 0
            vertices[i] = new Vertex(i, i, 0);
        }
        return UnionFind.constructMinSpanningTree(vertices, edges);
    }

}
