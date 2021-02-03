package binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * IMP-1 : Common question
 * also check InOrderPostOrderBuildTree
 */
public class InOrderPreOrderBuildTree {

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

    Map<Integer, Integer> map = new HashMap<>();
    int rootIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i] ,i);
        }
        rootIdx = 0;
        return constructPreOrder(0, preorder.length-1, preorder);
    }

    /**
     * this is very similar to the post order one, the only main difference is that you are moving from left to right
     * than from right to left
     * @param left
     * @param right
     * @param preOrder
     * @return
     */
    TreeNode constructPreOrder(int left, int right, int [] preOrder) {
        if (left > right) {
            return null;
        }

        int val = preOrder[rootIdx++];
        TreeNode node = new TreeNode(val);

        int idx = map.get(val);

        node.left = constructPreOrder(left, idx-1, preOrder);
        node.right = constructPreOrder(idx+1, right, preOrder);

        return node;
    }

}
