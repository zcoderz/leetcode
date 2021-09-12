package redo;

import utils.TreeNode;

public class BinaryTreeMaxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        calcSum(root);
        return max;
    }

    int calcSum(TreeNode node) {
        if (node == null) return 0;
        int left = calcSum(node.left);
        int right = calcSum(node.right);
        int curr = node.val;
        int maxSoFar =  Math.max(curr, Math.max((curr + left), Math.max((curr + right), (curr+left+right))));
        max = Math.max(maxSoFar, max);
        int retVal =  Math.max(curr, Math.max((curr + left), (curr + right)));
        return retVal;
    }
}
