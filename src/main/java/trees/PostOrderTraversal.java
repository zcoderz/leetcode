package trees;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 */
public class PostOrderTraversal {

    LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {


        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node7.left = node5;
        node5.right = node3;
        node3.left = node2;

        PostOrderTraversal iterative = new PostOrderTraversal();
        iterative.postOrderTraversal(node7);

        //5, 2, 3 , 7
    }

    /**
     * Use two stacks to calculate post order traversal
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        stackLeft.push(root);
        inOrderStack(stackLeft, stackRight);
        while (!stackRight.isEmpty()) {
            list.add(stackRight.pop().val);
        }
        return list;
    }

    /**
     * A clever way to run post order traversal iteratively
     * right stack basically gets items added into it in reverse order of post order traversal
     * but since its a stack the pop would return the items in post order traversal
     * @param stackLeft
     * @param stackRight
     */
    public void inOrderStack(Stack<TreeNode> stackLeft, Stack<TreeNode> stackRight) {
        while (!stackLeft.isEmpty()) {
            TreeNode node = stackLeft.pop();
            stackRight.push(node);
            if (node.left != null) {
                stackLeft.push(node.left);
            }
            if (node.right != null) {
                stackLeft.push(node.right);
            }
        }
    }
}
