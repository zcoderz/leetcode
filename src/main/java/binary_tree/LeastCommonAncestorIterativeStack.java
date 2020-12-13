package binary_tree;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

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
                //if both childs visited, pop the element and move the LCA to the prior ndoe which
                // was parent of the current node with LCA
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
