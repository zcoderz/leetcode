package trees;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. Binary Tree Inorder Traversal
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 */
public class InOrderTraversalIterative {

    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {


        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node7.left = node5;
        node5.right = node3;
        node3.left = node2;

        InOrderTraversalIterative iterative = new InOrderTraversalIterative();
        iterative.inorderTraversal(node7);

        //5, 2, 3 , 7
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        stackLeft.push(root);
        inOrderStack(stackLeft, stackRight);
        return list;
    }

    /**
     * use two stacks to mimic the left and right traversal.
     * 1. first process the left stack while keep adding items to it until left nodes exist
     *      a. when popping from left stack add popped node to the right stack
     * 2. when left stack is empty process from the right stack
     *      b. existence of an item in the right stack means that its left children have been processed
     *      c. add the item to the output list
     *      d. add the item's right child to left stack
     * @param stackLeft
     * @param stackRight
     */
    public void inOrderStack(Stack<TreeNode> stackLeft, Stack<TreeNode> stackRight) {
        while (!stackLeft.isEmpty() || !stackRight.isEmpty()) {
            if (!stackLeft.isEmpty()) {
                TreeNode node = stackLeft.pop();
                if (node.left != null) {
                    stackLeft.push(node.left);
                }
                stackRight.push(node);
            } else {
                TreeNode node = stackRight.pop();
                list.add(node.val);
                if (node.right != null) {
                    stackLeft.push(node.right);
                }
            }
        }
    }

}
