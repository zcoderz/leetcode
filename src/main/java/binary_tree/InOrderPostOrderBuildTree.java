package binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * IMP-2: Fun question to practice
 */
public class InOrderPostOrderBuildTree {


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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i] ,i);
        }
        rootIdx = postorder.length-1;
        constructPostOrder(0, postorder.length-1, postorder);
        return null;
    }

    /**
     * need to focus on this one !
     * use the in order traversal to create a map of key to index. this is needed because items left to the
     * key index constitute the left tree and those at the right constitute the right tree
     *
     * use the post order array to leverage that the item on its right index is the next root for respective tree
     *
     *
     * @param left
     * @param right
     * @param postorder
     * @return
     */
    TreeNode constructPostOrder(int left, int right, int[] postorder) {
        if (left > right) {
            return null;
        }
        //post order right to left, right is the next root for the given sub tree
        int iVal  = postorder[rootIdx--];

        TreeNode root = new TreeNode(iVal);
        int index = map.get(iVal);

        //draw out on a white board that the next post order index corresponds first to the right tree and then
        //to the left tree this is because we are moving post order from right to left
        TreeNode rTree = constructPostOrder(index+1, right, postorder);
        TreeNode lTree = constructPostOrder(left, index-1, postorder);


        root.left = lTree;
        root.right = rTree;

        return root;

    }


}
