package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
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
 */
public class PopulateNextRight {

    class Node {
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

    /**
     * this is an easy problem. you get nodes at a level and link up the nodes in an array
     * use queue to enable level order traversal
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> levels = new LinkedList<>();
        levels.add(root);

        while (!levels.isEmpty()) {
            List<Node> nodesAtLevel = new ArrayList<>();
            while (!levels.isEmpty()) {
                nodesAtLevel.add(levels.poll());
            }
            for (Node node: nodesAtLevel) {
                if (node.left != null) {
                    levels.add(node.left);
                }
                if (node.right != null) {
                    levels.add(node.right);
                }
            }
            int i = 0;
            for (; i < nodesAtLevel.size()-1; i++) {
                Node curr = nodesAtLevel.get(i);
                curr.next = nodesAtLevel.get(i+1);
            }
            nodesAtLevel.get(i).next = null;
        }

        return root;
    }
}
