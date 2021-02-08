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
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: true
 *
 * IMP-3: Common question. The code below does current node's comparison on stack unwind. Better to do comparison
 * while going down as you can cancel invalid scenarios quicker
 */
public class IsValidBST {

    public static enum CompareType {
        LESS, GREATER
    }

    public boolean isValidBST(TreeNode root) {

        isValidBST(root.left);
        isValidBST(root.right);
        boolean comp = compareBST(root.left, CompareType.LESS, root.val);
        if (!comp) return false;
        comp = compareBST(root.right, CompareType.GREATER, root.val);
        if (!comp) return false;
        return true;
    }

    boolean compareBST(TreeNode node, CompareType compareType, Integer val) {
        if (node == null) {
            return true;
        }
        boolean compVal = compare(node.val, val, compareType);
        if (!compVal) {
            return false;
        }
        compVal = compareBST(node.left, compareType, val);
        if (!compVal) {
            return false;
        }
        compVal = compareBST(node.right, compareType, val);
        if (!compVal) {
            return false;
        }

        return true;
    }

    boolean compare(Integer nodeVal, Integer val, CompareType compareType) {
        if (compareType == CompareType.LESS) {
            return val > nodeVal;
        } else {
            return val < nodeVal;
        }
    }
}

