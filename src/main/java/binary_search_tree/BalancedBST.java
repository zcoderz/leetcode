package binary_search_tree;

import utils.TreeNode;

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
