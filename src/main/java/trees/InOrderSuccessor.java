package trees;

import utils.TreeNode;

import java.util.ArrayDeque;

/**
 * 285. Inorder Successor in BST
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 *
 */
public class InOrderSuccessor {


    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        TreeNode curr = root;

        //if right child exists then successor must be the left most child of the right node
        TreeNode right = p.right;
        while (right != null && (right.left != null)) {
            right = right.left;
        }
        if (right != null) {
            return right;
        }

        //successor logically is the next biggest element after p, so every time you find a value greater than p
        //make it the successor and move towards a smaller value than p, if no smaller exist after you found an item
        //greater than p, then the last greater element must be the successor
        while (curr != null) {
            if (p.val < curr.val) {
                //successor would be the last greater element traversed while searching for p
                successor = curr;
                curr = curr.left;
            } else {
                //if the value is smaller or equal to p, traverse towards the right - bigger value
                curr = curr.right;
            }

        }
        return successor;
    }

    public TreeNode inorderSuccessorStack(TreeNode root, TreeNode p) {
        // the successor is somewhere lower in the right subtree
        // successor: one step right and then left till you can
        if (p.right != null) {
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }

        // the successor is somewhere upper in the tree
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        int inorder = Integer.MIN_VALUE;

        // inorder traversal : left -> node -> right
        while (!stack.isEmpty() || root != null) {
            // 1. go left till you can
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 2. all logic around the node
            root = stack.pop();
            // if the previous node was equal to p
            // then the current node is its successor
            if (inorder == p.val) return root;
            inorder = root.val;

            // 3. go one step right
            root = root.right;
        }

        // there is no successor
        return null;
    }
}

