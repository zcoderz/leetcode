package binary_search_tree;

import utils.TreeNode;

/**
 * 110. Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 */
public class BalancedBST {

    boolean balanced = true;
    public boolean isBalanced(TreeNode root) {
        checkIfBalanced(root);
        return balanced;
    }

    int checkIfBalanced(TreeNode node) {
        if (node == null || !balanced) {
            return 0;
        }

        int left = checkIfBalanced(node.left);
        int right = checkIfBalanced(node.right);

        if (Math.abs(left-right) > 1) {
            balanced = false;
            return 0;
        }

        //get max depth of left and right and add one to it for the current depth....
        return Math.max(left, right) + 1;
    }
}
