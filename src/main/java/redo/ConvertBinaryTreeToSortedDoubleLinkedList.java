package redo;


import java.util.Stack;

public class ConvertBinaryTreeToSortedDoubleLinkedList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    public static void main(String [] args) {
        Node n4 = new Node(4);
        Node n2 = new Node(2);
        Node n1 = new Node(1);
        Node n3 = new Node (3);
        Node n5 = new Node (5);

        n4.left = n2;
        n2.left = n1;
        n2.right = n3;
        n4.right = n5;

        ConvertBinaryTreeToSortedDoubleLinkedList con = new ConvertBinaryTreeToSortedDoubleLinkedList();
        Node ret = con.treeToDoublyList(n4);
        System.out.println(1);
    }

    Node head;
    Node curr;
    Node prior;
    public Node treeToDoublyList(Node root) {
        //processRecurse(root);
        //head.left = prior; prior.right = head;
        curr = root;
        process();
        return head;
    }

    void processRecurse(Node n) {
        if(n == null) return;
        processRecurse(n.left);
        if (head == null) {
            head = n;
        }
        if (prior != null) {
            prior.right = n;
            n.left = prior;
        }

        prior = n;
        processRecurse(n.right);
    }

    void process() {
        Stack<Node> stack = new Stack<>();
        Node prior = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (head == null) {
                head = curr;
            }
            if (prior != null) {
                prior.right = curr;
                curr.left = prior;
            }
            prior = curr;
            curr = curr.right;
        }

        head.left = prior; prior.right = head;
    }
}
