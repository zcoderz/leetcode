package misc;

import java.util.*;

public class EvaluateDivision {

    public static void main (String [] args) {
        List<List<String>> equations = new ArrayList<>();
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        List<String> d1 = new ArrayList<>();
        d1.add("a"); d1.add("b");
        List<String> d2 = new ArrayList<>();
        d2.add("b"); d2.add("c");
        equations.add(d1); equations.add(d2);
        List<String> q1 = new ArrayList<>();
        q1.add("a"); q1.add("c");
        queries.add(q1);
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        double d[] = evaluateDivision.calcEquation(equations, values, queries);

        for (int i =0; i < d.length; i++) {
            System.out.println(d);
        }
    }

    public class Edge {
        public Node parent;
        public Node child;
        public double weight;
        public Edge(Node parent, Node child, double weight) {
            this.parent = parent;
            this.child = child;
            this.weight = weight;
        }
    }

    public class Node {
        String val;
        List<Edge> edges = new ArrayList<>();
        public Node(String val) {
            this.val = val;
        }
    }

    Map<String, Node> nodeMap = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int i = 0;
        for (List<String> equation: equations) {
            String varA = equation.get(0);
            String varB = equation.get(1);
            Node nodeA = nodeMap.getOrDefault(varA, new Node(varA));
            Node nodeB = nodeMap.getOrDefault(varB, new Node(varB));
            nodeMap.put(varA, nodeA);
            nodeMap.put(varB, nodeB);
            double dValue =  values[i];
            Edge edgeA = new Edge(nodeA, nodeB, dValue);
            Edge edgeB = new Edge(nodeB, nodeA, 1/dValue);
            nodeA.edges.add(edgeA);
            nodeB.edges.add(edgeB);
            i++;
        }
        double [] dVals = new double[queries.size()];
        i = 0;
        Set<Node> visiting = new HashSet<>();
        for (List<String> strQuery : queries) {
            String valA = strQuery.get(0);
            String valB = strQuery.get(1);
            visiting.clear();
            double dVal = calcValue(nodeMap.get(valA), valB,1.0D, visiting);
            dVals[i] = dVal;
            i++;
        }
        return dVals;
    }

    double calcValue(Node node, String strTarget, double dAggWeight, Set<Node> visiting) {
        if ((node == null)) {
            return -1;
        }
        visiting.add(node);
        for (Edge edge : node.edges) {
            if (!visiting.contains(edge.child)) {
                if (edge.child.val.equals(strTarget)) {
                    return dAggWeight * edge.weight;
                }
                double dVal = calcValue(edge.child, strTarget, dAggWeight * edge.weight, visiting);
                if (dVal != -1) {
                    return dVal;
                }
            }
        }
        return -1;
    }

}
