package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversalIterative {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String [] args) {


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

    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        stackLeft.push(root);
        inOrderStack(stackLeft, stackRight);
        return list;
    }

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
