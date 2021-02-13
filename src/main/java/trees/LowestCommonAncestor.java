package trees;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node
 * in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * IMP-1 :
 *   -- Finding this iteratively via stack is a neat solution, practice it.
 *   -- Recursive solution as written is extremely simple. Have a look.
 */
public class LowestCommonAncestor {

    private TreeNode leastCommonAncestor;

    /**
     * Clever way to simplify this problem.
     * Each node , check whether each of left and right paths get to p and q. the node where this is true
     * is the LCA node.
     * @param currentNode
     * @param p
     * @param q
     * @return
     */
    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }
        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
        //If the current node is one of p or q. Important to make this check if the node itself is one of p or q
        //and one of its children points to the other node, then this node must be the LCA
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;
        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.leastCommonAncestor = currentNode;
        }
        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    // Three static flags to keep track of post-order traversal (i,e if you have visited the left, right or both
    // children)

    // Both left and right traversal pending for a node.
    // Indicates the nodes children are yet to be traversed.
    private static int BOTH_PENDING = 2;

    // Left traversal done.
    private static int LEFT_DONE = 1;

    // Both left and right traversal done for a node.
    // Indicates the node can be popped off the stack.
    private static int BOTH_DONE = 0;


    /**
     * for reach node track via a pair whether both left and right are visited or left is visited or none
     * we can use whether left, right or both visited to figure out which direction to traverse next
     *
     *
     * logic :
     * -- if both right and left arent visited :
     *    if node matches p or q
     *       if one match already existed then return lca pointer previously established as lca
     *       else if this is first match set lca as pointing to this node
     *    if left visited add right to stack
     *    if right visited add left to stack
     *    decrement count of directions visited for this node in stack
     * -- else if both left and right have been visited.
     *   -- pop this node from stack
     *   -- if this node was the lca, set lca to current top of stack
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        // Initialize the stack with the root node.
        stack.push(new Pair<>(root, BOTH_PENDING));
        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;
        // This is used to keep track of the least common ancestor.
        TreeNode leastCommonAncestor = null;
        // Child node
        TreeNode child_node;
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
                            return leastCommonAncestor;
                        } else {
                            // Otherwise, set one_node_found to True,
                            // to mark one of p and q is found.
                            one_node_found = true;
                            // Save the current top element of stack as the leastCommonAncestor.
                            leastCommonAncestor = stack.peek().first;
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
                stack.push(new Pair<>(parent_node, parent_state - 1));
                // Add the child node to the stack for traversal.
                if (child_node != null) {
                    stack.push(new Pair<>(child_node, BOTH_PENDING));
                }
            } else {
                // If the parent_state of the node is both done,
                // the top node could be popped off the stack.
                // Update the leastCommonAncestor node to be the next top node.
                if (leastCommonAncestor == stack.pop().first && one_node_found) {
                    leastCommonAncestor = stack.peek().first;
                }
            }
        }
        return null;
    }
}
