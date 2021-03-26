package trees;

import utils.TreeNode;

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
 *
 *   IMP-1 : This is a very common question and below is an extremely elegant solution to handle this problem.
 *   See InOrderPostOrderBuildTree for another flavor of same type of question.
 *
 */
public class CreateTreeFromPreAndInOrder {

    public static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        CreateTreeFromPreAndInOrder createTreeFromPreAndInOrder = new CreateTreeFromPreAndInOrder();
        TreeNode node = createTreeFromPreAndInOrder.buildTree(preOrder, inOrder);
    }

    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<>();

    /**
     * construct a map based on in order coordinates of given indices
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length-1);
    }

    /**
     *
     * Repeat below for each sub tree (Root, Left, Right), where repeat is done for left and right sub trees
     *   Preorder traversal follows Root -> Left -> Right order
     *   The first element in the preorder list is a root.
     *   Once you know the root, you can use the inorder list to split the array into left and right sub arrays
     * Use return of the repeat to stitch left and right child trees
     *
     * @param in_left
     * @param in_right
     * @return
     */
    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // move to the next pre order root
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index-1);
        // build right subtree
        root.right = helper(index + 1, in_right);
        return root;
    }
}
