package binary_tree;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

/**
 * /**
 *  * 236. Lowest Common Ancestor of a Binary Tree
 *  * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *  *
 *  * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 *  * two nodes p and q as the lowest node in T that has both p and q as descendants
 *  * (where we allow a node to be a descendant of itself).”
 *  *
 *  *
 *  *
 *  * Example 1:
 *  *
 *  *
 *  * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *  * Output: 3
 *  * Explanation: The LCA of nodes 5 and 1 is 3.
 *  *
 *  * IMP-1 : Common question (also check iterative solution LeastCommonAncestorRecursive)
 *
 */
public class LeastCommonAncestorIterativeStack {

    /**
     * this is a tricky problem. idea is that a stack can be used to track path and maintain concept for back tracking
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int BOTH_DONE = 0;
        int LEFT_DONE = 1;
        int BOTH_PENDING = 2;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        TreeNode LCA = null;
        stack.push(new Pair<>(root, BOTH_PENDING));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> topPair = stack.peek();
            if (topPair.second != BOTH_DONE) {
                //mark LCA on first entry, on back track , if reprocess then we'd send LCA incorrectly because
                //same p or q will be repeated when back tracking for example from left path
                if ((topPair.second == BOTH_PENDING) && (topPair.first == p || topPair.first == q)) {
                    //node matched
                    if (LCA != null) {
                        return LCA;
                    } else {
                        LCA = topPair.first;
                    }
                }

                //move left or right based on the state of the node
                TreeNode child;
                if (topPair.second == LEFT_DONE) {
                    child = topPair.first.right;
                } else {
                    child = topPair.first.left;
                }
                stack.pop();
                stack.push(new Pair<>(topPair.first, topPair.second - 1));
                if (child != null)
                    stack.push(new Pair<>(child, BOTH_PENDING));

            } else {
                //if both childs visited, pop the element and move the LCA to the prior node which
                //was parent of the current node with LCA
                if (LCA == topPair.first) {
                    stack.pop();
                    LCA = stack.peek().first;
                } else {
                    stack.pop();
                }
            }
        }
        return null;
    }
}
