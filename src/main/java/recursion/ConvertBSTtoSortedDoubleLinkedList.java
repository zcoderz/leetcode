package recursion;

import utils.TreeNode;

/**
 * do an in order traversal while keeping track of prior node and head
 * adjust the pointers for doubly linked list appropriately.
 *
 */
public class ConvertBSTtoSortedDoubleLinkedList {
    TreeNode head;
    TreeNode prior;
    public TreeNode treeToDoublyList(TreeNode root) {
        levelOrderTraversal(root);
        if (head != null && prior != null) {
            head.left = prior;
            prior.right = head;
        }
        return head;
    }

    void levelOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        levelOrderTraversal(node.left);
        if (head == null) {
            head = new TreeNode(node.val);
            prior = head;
        } else {
            TreeNode curr = new TreeNode(node.val);
            prior.right = curr;
            curr.left = prior;
            prior = curr;
        }
        levelOrderTraversal(node.right);
    }
}
