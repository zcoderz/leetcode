package stack;

import utils.Node;

import java.util.*;

/**
 * 133. Clone Graph
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity sake, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val = 1, the second node with val = 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 *
 * Adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
 *
 *
 * Example 1:
 *
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 */
public class CloneGraph {

    private HashMap<Node, Node> visited = new HashMap<>();

    public static void main(String[] args) {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4);
        Node nodeE = new Node(5);

        nodeA.children.add(nodeB);
        nodeA.children.add(nodeC);

        nodeB.children.add(nodeD);
        nodeC.children.add(nodeD);
        nodeD.children.add(nodeE);

        CloneGraph graph = new CloneGraph();
        Node cloned = graph.cloneGraph(nodeA);

        int j = 2;
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Stack<Node[]> stack = new Stack<>();
        Map<Node, Node> visited = new HashMap<>();
        Node[] arr = new Node[]{node, cloneNode(node)};
        //took me a long time to realize that i had missed adding the root to visited...
        //a small miss can lead to confusing affects. in this case, need to debug the output and figure out
        visited.put(arr[0], arr[1]);
        stack.add(arr);
        while (!stack.isEmpty()) {
            Node[] n = stack.pop();
            Node orig = n[0];
            Node clone = n[1];
            for (Node child : orig.children) {
                Node nChild = visited.get(child);
                if (nChild == null) {
                    nChild = cloneNode(child);
                }
                clone.children.add(nChild);
                if (visited.containsKey(child)) {
                    continue;
                }
                stack.add(new Node[]{child, nChild});

                visited.put(child, nChild);
            }
        }
        return arr[1];
    }


    /**
     * does a copy of the node val. doesnt copy node list
     *
     * @param node
     * @return
     */
    private Node cloneNode(Node node) {
        Node nNode = new Node(node.val);
        return nNode;
    }

    public Node cloneGraphRecursive(Node node) {
        if (node == null) {
            return node;
        }
        System.out.println("processing node " + node.val);
        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned children as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the children to generate their clones
        // and prepare a list of cloned children to be added to the cloned node.
        for (Node neighbor : node.children) {
            cloneNode.children.add(cloneGraphRecursive(neighbor));
        }
        return cloneNode;
    }
}
