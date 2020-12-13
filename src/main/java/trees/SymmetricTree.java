package trees;

/**
 * Definition for a binary tree node.
*/


class SymmetricTree {

    public static void main(String [] args) {


        TreeNode nodeA = new TreeNode(3);
        TreeNode nodeB = new TreeNode(4);
        TreeNode nodeAA = new TreeNode(2);
        nodeAA.left = nodeA;
        nodeAA.right = nodeB;
        TreeNode parent = new TreeNode(1);
        parent.left = nodeAA;
        TreeNode nodeC = new TreeNode(2);
        parent.right = nodeC;
        TreeNode nodeCl = new TreeNode(4);
        nodeC.left = nodeCl;
        TreeNode nodeCr = new TreeNode(3);
        nodeC.right = nodeCr;

        SymmetricTree t = new SymmetricTree();
        boolean comp = t.isSymmetric(parent);
        System.out.println(comp);
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        return symCompare (root.left, root.right);
    }

    boolean symCompare(TreeNode nodeA, TreeNode nodeB) {
        if (nodeA==null && nodeB==null) {
            return true;
        }
        if (nodeA == null || nodeB ==null) {
            return false;
        }
        boolean comp = (nodeA.val == nodeB.val);
        if (!comp) {
            return false;
        }
        comp = symCompare(nodeA.left, nodeB.right);
        if (!comp) {
            return false;
        }
        comp = symCompare(nodeA.right, nodeB.left);
        if (!comp) {
            return false;
        }
        return true;
    }
}