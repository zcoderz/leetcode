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
public class UniValSubTreeSimple {

    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        isUnival(root, 0);
        return count;
    }

    /**
     * this is a simpler but tricky solution
     * @param node
     * @param val
     * @return
     */
    boolean isUnival(TreeNode node, int val) {
        if (node == null) return true;

        boolean left = isUnival(node.left, node.val);
        boolean right = isUnival(node.right, node.val);

        //if either left or right isnt unival then this isnt a unival and therefore any parents arent univals
        //we must return false
        if (!(left && right)) {
            return false;
        }

        //increment if left and right are unival
        count++;

        //return if node has same value as parent
        return node.val == val;
    }

}

