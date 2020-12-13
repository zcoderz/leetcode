package trees;

import java.util.LinkedList;

public class NextRightPointers {

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public static void main(String [] args) {
        Node node1 = new Node (1);
        Node node2 = new Node (2);
        Node node3 = new Node (3);
        Node node4 = new Node (4);
        Node node5 = new Node (5);
        Node node6 = new Node (6);
        Node node7 = new Node (7);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        NextRightPointers nextRightPointers = new NextRightPointers();
        nextRightPointers.connect(node1);
        int j = 1;
    }

    public Node connect(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {

            Node prev = null;
            for (Node node: queue) {
                if (node == null) break;
                if (prev != null) prev.next = node; prev = node;
            }
            if (prev != null) {
                prev.next = null;
            }


            while (queue.peek() != null )   {
                Node node = queue.pop();

                if (node.left!= null)
                    queue.add(node.left);

                if (node.right!= null)
                    queue.add(node.right);
            }


            if (queue.size() > 1) {
                queue.add(null);
                break;
            }
            queue.pop();

        }
        return root;
    }

}
