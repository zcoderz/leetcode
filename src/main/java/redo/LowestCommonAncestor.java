package redo;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

public class LowestCommonAncestor {
    boolean found = false;
    TreeNode theNode = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        found = false;
        theNode = null;
        findLca(root, p, q);
        return theNode;
    }

    TreeNode findLca(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;
        TreeNode rightPath = findLca(node.right, p, q);
        if (theNode !=null) {
            return theNode;
        }


        TreeNode leftPath = findLca(node.left, p, q);
        if (theNode !=null) {
            return theNode;
        }

        boolean thisIsOne = false;
        if (node == p || node == q) {
            thisIsOne = true;
        }

        if ((leftPath != null && rightPath != null) || (leftPath != null && thisIsOne) || (rightPath != null && thisIsOne)) {
            found = true;
            theNode = node;
            return node;
        }

        if (leftPath != null) {
            return leftPath;
        }
        if (rightPath != null) {
            return rightPath;
        }
        if (thisIsOne) {
            return node;
        }
        return null;
    }
    // Three static flags to keep track of post-order traversal.

    // Both left and right traversal pending for a node.
    // Indicates the nodes children are yet to be traversed.
    private static int BOTH_PENDING = 2;

    // Left traversal done.
    private static int LEFT_DONE = 1;

    // Both left and right traversal done for a node.
    // Indicates the node can be popped off the stack.
    private static int BOTH_DONE = 0;

    public TreeNode lowestCommonAncestorStack(TreeNode root, TreeNode p, TreeNode q) {

        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

        // Initialize the stack with the root node.
        stack.push(new Pair<TreeNode, Integer>(root, BOTH_PENDING));

        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;

        // This is used to keep track of the LCA.
        TreeNode LCA = null;

        // Child node
        TreeNode child_node = null;

        // We do a post order traversal of the binary tree using stack
        while (!stack.isEmpty()) {

            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parent_node = top.first;
            int parent_state = top.second;

            // If the parent_state is not equal to BOTH_DONE,
            // this means the parent_node can't be popped off yet.
            if (parent_state != BOTH_DONE) {

                // If both child traversals are pending
                if (parent_state == BOTH_PENDING) {

                    // Check if the current parent_node is either p or q.
                    if (parent_node == p || parent_node == q) {

                        // If one_node_found was set already, this means we have found
                        // both the nodes.
                        if (one_node_found) {
                            return LCA;
                        } else {
                            // Otherwise, set one_node_found to True,
                            // to mark one of p and q is found.
                            one_node_found = true;

                            // Save the current top element of stack as the LCA.
                            LCA = stack.peek().first;
                        }
                    }

                    // If both pending, traverse the left child first
                    child_node = parent_node.left;
                } else {
                    // traverse right child
                    child_node = parent_node.right;
                }

                // Update the node state at the top of the stack
                // Since we have visited one more child.
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

                // Add the child node to the stack for traversal.
                if (child_node != null) {
                    stack.push(new Pair<TreeNode, Integer>(child_node, BOTH_PENDING));
                }
            } else {

                // If the parent_state of the node is both done,
                // the top node could be popped off the stack.
                // Update the LCA node to be the next top node.
                if (LCA == stack.pop().first && one_node_found) {
                    LCA = stack.peek().first;
                }

            }
        }

        return null;
    }
}
