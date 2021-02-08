package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 951. Flip Equivalent Binary Trees
 *
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 *
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 */

public class FlipEquiv {

    /**
     * A very clever implementation from leet code.
     * compare each node and traverse the smaller path first
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
    }

    public boolean flipEquivComplicated(TreeNode root1, TreeNode root2) {
        //root doesnt have same value
        if (root1.val != root2.val) {
            return false;
        }
        ChildNullStatus aStatus = getChildNullStatus(root1);
        ChildNullStatus bStatus = getChildNullStatus(root2);
        //equal null children
        if (aStatus == ChildNullStatus.BOTH_NULL && bStatus == ChildNullStatus.BOTH_NULL) {
            return true;
        }
        //unequal children
        if (((aStatus == ChildNullStatus.RIGHT_NULL || aStatus == ChildNullStatus.LEFT_NULL)
                && ((bStatus == ChildNullStatus.BOTH_NULL) || (bStatus == ChildNullStatus.BOTH_NOT_NULL))) ||
                ((bStatus == ChildNullStatus.RIGHT_NULL || bStatus == ChildNullStatus.LEFT_NULL)
                        && ((aStatus == ChildNullStatus.BOTH_NULL) || (aStatus == ChildNullStatus.BOTH_NOT_NULL)))) {
            return false;
        }

        if (aStatus == ChildNullStatus.BOTH_NOT_NULL && bStatus == ChildNullStatus.BOTH_NOT_NULL) {
            boolean retA = flipEquivComplicated(root1.left, root2.left) && flipEquivComplicated(root1.right, root2.right);
            boolean retB = flipEquivComplicated(root1.left, root2.right) && flipEquivComplicated(root1.right, root2.left);
            return (retA || retB);
        }

        if (aStatus == ChildNullStatus.RIGHT_NULL && bStatus == ChildNullStatus.RIGHT_NULL) {
            return flipEquivComplicated(root1.left, root2.left);
        }

        if (aStatus == ChildNullStatus.RIGHT_NULL && bStatus == ChildNullStatus.LEFT_NULL) {
            return flipEquivComplicated(root1.left, root2.right);
        }

        if (aStatus == ChildNullStatus.LEFT_NULL && bStatus == ChildNullStatus.LEFT_NULL) {
            return flipEquivComplicated(root1.right, root2.right);
        }

        if (aStatus == ChildNullStatus.LEFT_NULL && bStatus == ChildNullStatus.RIGHT_NULL) {
            return flipEquivComplicated(root1.right, root2.left);
        }

        return true;
    }

    public ChildNullStatus getChildNullStatus(final TreeNode node) {
        if ((node.left != null) && (node.right != null)) {
            return ChildNullStatus.BOTH_NOT_NULL;
        } else if ((node.left == null) && (node.right == null)) {
            return ChildNullStatus.BOTH_NULL;
        } else if ((node.left == null)) {
            return ChildNullStatus.LEFT_NULL;
        } else {
            return ChildNullStatus.RIGHT_NULL;
        }
    }

    public enum ChildNullStatus {
        BOTH_NULL,
        BOTH_NOT_NULL,
        LEFT_NULL,
        RIGHT_NULL
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
