package graph;


/**
 * 785. Is Graph Bipartite?
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B,
 * such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 * Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i,
 * and it doesn't contain any element twice.
 *
 */
public class IsGraphBipartite {

    public static void main(String [] args) {
        IsGraphBipartite graph = new IsGraphBipartite();
        int [][] graphVertexesEdges = {{1,3},{0,2},{1,3},{0,2}};
        boolean bipartite = graph.isBipartite(graphVertexesEdges);
        System.out.println(bipartite);
        int [][] graphVertexes2 = {{1,2,3},{0,2},{0,1,3},{0,2}};
        bipartite = graph.isBipartite(graphVertexes2);
        System.out.println(bipartite);
    }

    int [] vertixColor ;
    int blue = 1;

    public boolean isBipartite(int[][] graph) {
        vertixColor = new int[graph.length];
        for (int i =0; i < graph.length; i++) {
            boolean processG = processGraph(graph, i, blue);
            if (!processG) {
                return false;
            }
        }
        return true;
    }

    /**
     * for each vertex in graph do a dfs search to color the child nodes appropriately
     * and thus validate that you can partition the vertexes into 2 groups of colors
     * where a vertex cannot belong to both groups.
     *
     * this is simple : you start from parent vertex, do a dfs down to children, alternating colors at each
     * level of the dfs. if a vertex at a child level is colored differently than expected (meaning you reached that
     * vertex via a different path that caused it to belong to the alternative color) than the graph must not be bipartite.
     *
     * @param graph
     * @param i
     * @param color
     * @return
     */
    boolean processGraph(int [][] graph, int i, int color) {
        if (vertixColor[i] == 0) { //if the vertex is already colored than skip it
            //if vertex not already visited
            vertixColor[i] = color;
            int [] childNodes = graph[i];
            for (int j : childNodes) {
                //if a child vertex has same color as parent than the graph isnt bipartite
                if (vertixColor[j] == color) {
                    return false;
                }
                int nextColor = color == 1 ? 2: 1; //alternate colors as you traverse down
                boolean validColors = processGraph(graph, j , nextColor);
                if (!validColors) {
                    return false;
                }
            }
        }
        return true;
    }
}
