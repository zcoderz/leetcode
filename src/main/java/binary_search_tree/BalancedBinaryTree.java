package binary_search_tree;

import utils.TreeNode;

public class BalancedBinaryTree {

    /**
     * this is a tricky problem but can be simplified via recursion
     * @param root
     * @param key
     * @return
     */
    TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null; //edge case
        if(root.val == key) { //here is the case where values match

            //case where left or right or both are null
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            //in case where both left and right are not null recurse down to the in order successor
            //delete that node and swap its values with the node to be deleted.
            TreeNode next = root.right;
            while (next.left != null) {
                next = next.left;
            }
            deleteNode(root, next.val);
            root.val = next.val;
            return root;

        } else if (root.val > key && root.left != null) {
            //set the return value to left node so as to set tree structure correctly.
            root.left = deleteNode(root.left, key);
        } else if (root.val < key && root.right != null) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

}
