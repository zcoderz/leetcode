package binary_search_tree;

import utils.TreeNode;

/**
 * 450. Delete Node in a BST
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * IMP-2 : This problem is important to understand. Once logic is understood , its fairly simple
 */
public class DeleteNodeInBST {

    /**
     * this is a tricky problem but can be simplified via recursion
     * @param root
     * @param key
     * @return
     */
    TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null; //edge case
        if(root.val == key) { //here is the case where values match
            //case where left or right or both are null, return the non-null child
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
