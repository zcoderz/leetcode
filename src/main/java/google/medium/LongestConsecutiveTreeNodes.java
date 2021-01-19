package google.medium;

import utils.TreeNode;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */
public class LongestConsecutiveTreeNodes {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        recurse(root.left, root.val, 1);
        recurse(root.right, root.val, 1);
        return max;
    }

    void recurse(TreeNode node, Integer priorVal, int count) {
        max = Math.max(max, count);
        if (node == null) {
            return;
        }

        if (node.val == (priorVal + 1)) {
            recurse(node.left, node.val, count+1);
            recurse(node.right, node.val, count+1);
        } else {
            recurse(node.left, node.val, 1);
            recurse(node.right, node.val, 1);
        }
    }
}
