package frequent.medium;

import utils.TreeNode;

/**
 * 98. Validate Binary Search Tree
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 *
 * IMP-3: Common question.
 */
public class IsValidBST {

    Integer prevVal;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prevVal != null && root.val <= prevVal) {
            return false;
        }
        prevVal = root.val;
        return isValidBST(root.right);
    }

}

