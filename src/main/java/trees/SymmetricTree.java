package trees;

import utils.TreeNode;

/**
 * 101. Symmetric Tree
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 */

class SymmetricTree {

    public static void main(String[] args) {


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


    public boolean isSymmetric(TreeNode root) {
        return symCompare(root.left, root.right);
    }

    /**
     * simple code alternate traversal between left and right nodes
     * @param nodeA
     * @param nodeB
     * @return
     */
    boolean symCompare(TreeNode nodeA, TreeNode nodeB) {
        if (nodeA == null && nodeB == null) {
            return true;
        }
        if (nodeA == null || nodeB == null) {
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