package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. Populating Next Right Pointers in Each Node
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer
 * to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 */
public class NextRightPointers {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

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


    /**
     * this is a simple solution, you traverse each level of the tree one level at a time left to right
     * @param root
     * @return
     */
    public Node connectQueueBased(Node root) {

        if (root == null) {
            return root;
        }

        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Outer while loop which iterates over
        // each level
        while (!queue.isEmpty()) {

            // Note the size of the queue
            int size = queue.size();

            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {

                // Pop a node from the front of the queue
                Node node = queue.poll();

                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // Since the tree has now been modified, return the root node
        return root;
    }

    Node prev, leftmost;

    /**
     * this is a clever idea that doesn't use a queue. At each level you have already established the next pointers
     * therefore you can use the next pointers at current level to process the next level. you will need to keep track
     * of the first node at the prior level and for that the code uses a variable 'leftmost'
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // The root node is the only node on the first level
        // and hence its the leftmost node for that level
        this.leftmost = root;
        // Variable to keep track of leading node on the "current" level
        Node curr;
        // We have no idea about the structure of the tree,
        // so, we keep going until we do find the last level.
        // the nodes on the last level won't have any children
        while (this.leftmost != null) {
            // "prev" tracks the latest node on the "next" level
            // while "curr" tracks the latest node on the current
            // level.
            this.prev = null;
            curr = this.leftmost;
            // We reset this so that we can re-assign it to the leftmost
            // node of the next level. Also, if there isn't one, this
            // would help break us out of the outermost loop.
            this.leftmost = null;
            // Iterate on the nodes in the current level using
            // the next pointers already established.
            while (curr != null) {
                // Process both the children and update the prev
                // and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);
                // Move onto the next node.
                curr = curr.next;
            }
        }
        return root;
    }

    /**
     * adjust the node pointers appropriately
     * @param childNode
     */
    public void processChild(Node childNode) {
        if (childNode != null) {
            // If the "prev" pointer is already set i.e. if we
            // already found at least one node on the next level,
            // setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;
            } else {
                // Else it means this child node is the first node
                // we have encountered on the next level, so, we
                // set the leftmost pointer
                this.leftmost = childNode;
            }
            this.prev = childNode;
        }
    }

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
