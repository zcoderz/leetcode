package stack;

import java.util.*;

public class CloneGraph {

    public static void main(String [] args) {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4);
        Node nodeE = new Node(5);

        nodeA.neighbors.add(nodeB);
        nodeA.neighbors.add(nodeC);

        nodeB.neighbors.add(nodeD);
        nodeC.neighbors.add(nodeD);
        nodeD.neighbors.add(nodeE);

        CloneGraph graph = new CloneGraph();
        Node cloned = graph.cloneGraph(nodeA);

        int j = 2;
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node==null) return null;
        Stack<Node[]> stack = new Stack<>();
        Map<Node, Node> visited = new HashMap<>();
        Node[] arr = new Node [] {node, cloneNode(node)};
        //took me a long time to realize that i had missed adding the root to visited...
        //a small miss can lead to confusing affects. in this case, need to debug the output and figure out
        visited.put(arr[0], arr[1]);
        stack.add(arr);
        while (!stack.isEmpty()) {
            Node[] n = stack.pop();
            Node orig = n[0];
            Node clone = n[1];
            //System.out.println("processing node " + orig.val);
            for (Node child: orig.neighbors) {
                Node nChild = visited.get(child);
                if (nChild == null) {
                    nChild = cloneNode(child);
                }
                //System.out.println("adding child " + nChild.val + " to parent " + clone.val);
                clone.neighbors.add(nChild);
                if (visited.containsKey(child)) {
                    //System.out.println("already visited child " + child.val + " parent is " + orig.val );
                    continue;
                }
                //System.out.println("adding to stack node " + child.val + " parent is " + orig.val );
                stack.add(new Node[]{child, nChild});

                visited.put(child, nChild);
            }
            //System.out.println("processed node " + orig.val);
        }
        return arr[1];
    }


    /**
     * does a copy of the node val. doesnt copy node list
     * @param node
     * @return
     */
    private Node cloneNode(Node node ) {
        Node nNode = new Node(node.val);
        return nNode;
    }

    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraphRecusive(Node node) {
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
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {
            System.out.println("recursing neighbor " + neighbor.val);
            cloneNode.neighbors.add(cloneGraphRecusive(neighbor));
        }
        return cloneNode;
    }

}
