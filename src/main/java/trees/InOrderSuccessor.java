package trees;

public class InOrderSuccessor {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        TreeNode curr = root;

        TreeNode right = p.right;
        while (right != null && (right.left != null) ) {
            right = right.left;
        }
        if (right != null) {
            return right;
        }

        while (curr != null) {
            if (p.val < curr.val) {
                successor = curr;
                curr = curr.left;
            } else {
                //p.val >= curr.val
                curr = curr.right;
            }

        }
        return successor;
    }


}

