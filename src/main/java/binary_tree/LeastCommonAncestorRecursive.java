package binary_tree;

import utils.TreeNode;

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
