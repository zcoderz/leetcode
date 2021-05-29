package recursion;

import utils.TreeNode;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers
 * in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should
 * point to its predecessor, and the right pointer should point to its successor.
 * You should return the pointer to the smallest element of the linked list.
 *
 * do an in order traversal while keeping track of prior node and head
 * adjust the pointers for doubly linked list appropriately.
 *
 * IMP-3: Good practice problem for tree traversal
 */
public class ConvertBSTtoSortedDoubleLinkedList {
    TreeNode head;
    TreeNode prior;

    public TreeNode treeToDoublyList(TreeNode root) {
        inOrderTraversal(root);
        if (head != null && prior != null) {
            head.left = prior;
            prior.right = head;
        }
        return head;
    }

    /**
     * Very neat solution. Draw it out on the board to get an understanding of pointer flow.
     *
     * @param node
     */
    void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        if (head == null) {
            head = new TreeNode(node.val);
            prior = head;
        } else {
            TreeNode curr = new TreeNode(node.val);
            prior.right = curr;
            curr.left = prior;
            prior = curr;
        }
        inOrderTraversal(node.right);
    }
}
