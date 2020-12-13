package binary_tree;

import utils.TreeNode;

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
