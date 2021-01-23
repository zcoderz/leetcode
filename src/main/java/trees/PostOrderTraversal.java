package trees;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        iterative.postOrderTravesal(node7);

        //5, 2, 3 , 7
    }

    public List<Integer> postOrderTravesal(TreeNode root) {
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        stackLeft.push(root);
        inOrderStack(stackLeft, stackRight);
        while (!stackRight.isEmpty()) {
            list.add(stackRight.pop().val);
        }
        return list;
    }

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
