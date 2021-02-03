package binary_tree;

import utils.TreeNode;

/**
 * 250. Count Univalue Subtrees
 *
 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 *
 */
public class UniValSubTrees {
    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        isUnival(root);
        return count;
    }

    boolean isUnival(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return  true;
        }

        boolean uniVal = true;

        if (node.left != null) {
            uniVal = (isUnival(node.left) && node.left.val == node.val);
        }

        if (node.right != null) {
            uniVal =  (isUnival(node.right) && node.right.val == node.val) && uniVal;
        }

        if (uniVal) {
            count++;
            return true;
        }
        return false;
    }
}
