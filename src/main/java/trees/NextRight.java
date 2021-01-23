package trees;

public class NextRight {


    public Node connect(Node root) {
        if (null == root) return null;
        root.next = null;
        inOrderTraverse(root);
        return root;
    }

    ;

    void inOrderTraverse(Node node) {
        updateNode(node);
        if (node.left != null) {
            inOrderTraverse(node.left);
        }
        if (node.right != null) {
            inOrderTraverse(node.right);
        }
    }

    void updateNode(Node node) {
        if (node.left != null) {
            node.left.next = node.right;
        }
        if (node.next != null) {
            if (node.right != null) {
                node.right.next = node.next.left;
            }
        }
    }

    // Definition for a Node.
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
