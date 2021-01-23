package google.medium;

import java.util.*;

/**
 * 399. Evaluate Division
 * <p>
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single
 * variable.
 * <p>
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
 * for Cj / Dj = ?.
 * <p>
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
 * that there is no contradiction.
 * <p>
 * Below solution is via DFS. A faster solution would be via union find. in union find you can check whether numerator
 * and denom are in same set to find whether division possibility exists. and then you can find the divisor weight
 * relative to root for each of the numerator and denom. you divide the two weights to get num/denom.
 */
public class EvaluateDivisions {

    Map<String, Node> nodeMap = new HashMap<>();
    Map<String, Double> memorization = new HashMap<>();

    public static void main(String[] args) {

        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        EvaluateDivisions ed = new EvaluateDivisions();
        double[] d = ed.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(d));
    }

    /**
     * main entry to for the division
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        populateNodeMap(equations, values);
        double[] arrDbl = new double[queries.size()];
        int i = 0;
        for (List<String> query : queries) {
            double val = traverseQuery(nodeMap.get(query.get(0)), query.get(1), new HashSet<>(), query.get(0), 1);
            arrDbl[i++] = val;
        }
        return arrDbl;
    }

    /**
     * do a dfs to find the division string you are searching for
     *
     * @param node
     * @param dest
     * @param visited
     * @param orig
     * @param weight
     * @return
     */
    double traverseQuery(Node node, String dest, Set<String> visited, String orig, double weight) {
        if (node == null) return -1;
        String searchPath = node.name + "," + dest;
        Double d = memorization.get(searchPath);
        if (d != null) {
            return d;
        }
        searchPath = orig + "," + node.name;
        memorization.put(searchPath, weight);
        visited.add(node.name);
        for (Edge edge : node.edges) {
            if (edge.dest.name.equals(dest)) {
                return edge.weight;
            }
            if (!visited.contains(edge.dest.name)) {
                searchPath = edge.dest.name + "," + dest;
                d = memorization.get(searchPath);
                if (d != null) {
                    return d * edge.weight;
                }
                double val = traverseQuery(edge.dest, dest, visited, orig, weight * edge.weight);
                if (val != -1) {
                    return val * edge.weight;
                }
            }
        }
        return -1;
    }

    /**
     * create the nodes
     *
     * @param equations
     * @param values
     */
    void populateNodeMap(List<List<String>> equations, double[] values) {
        int i = 0;
        for (List<String> equation : equations) {
            double val = values[i];
            String src = equation.get(0);
            String target = equation.get(1);
            Node nodeSrc = nodeMap.computeIfAbsent(src, l -> new Node(src));
            Node nodeDest = nodeMap.computeIfAbsent(target, l -> new Node(target));

            Edge edgeA = new Edge(val, nodeDest);
            nodeSrc.edges.add(edgeA);

            Edge edgeB = new Edge(1 / val, nodeSrc);
            nodeDest.edges.add(edgeB);

            i++;
        }
    }

    class Edge {
        double weight;
        Node dest;

        public Edge(double weight, Node dest) {
            this.weight = weight;
            this.dest = dest;
        }
    }

    class Node {
        String name;
        List<Edge> edges = new ArrayList<>();
        public Node(String name) {
            this.name = name;
        }
    }
}
