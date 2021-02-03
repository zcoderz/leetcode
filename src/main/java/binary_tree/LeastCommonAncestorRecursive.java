package binary_tree;

import utils.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * IMP-1 : Common question (also check iterative solution LeastCommonAncestorIterativeStack)
 */
public class LeastCommonAncestorRecursive {


    TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        processLCA(root, p, q);
        return lca;
    }


    /**
     * Simple recursive solution to find LCA.
     * @param root
     * @param p
     * @param q
     * @return
     */
    boolean processLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean thisFound = false;
        if ((root == p ) || (root == q)) {
            thisFound = true;
        }

        boolean foundL = processLCA(root.left, p, q);
        boolean foundR = processLCA(root.right, p, q);

        //mark LCA if found in both left and right path or this one matches and one of the child path matches
        if ((foundL && foundR) || (thisFound && (foundL || foundR))) {
            lca = root;
        }

        //return true if match is found in left, right or current
        return (foundL || foundR || thisFound);
    }

}
