package microsoft;

import com.sun.source.tree.Tree;
import utils.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BoundaryOfBinaryTree {

    public static void main(String [] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.right = node4;
        node4.left = node3;
        node3.left = node2;
        node2.left = node5;

        BoundaryOfBinaryTree boundary = new BoundaryOfBinaryTree();
        boundary.boundaryOfBinaryTree(node1);
        System.out.println(boundary.boundary);
    }

    List<Integer> boundary = new LinkedList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return boundary;
        }
        boundary.add(root.val);
        calculateLeftBoundary(root.left);
        calculateLeaves(root.left);
        calculateLeaves(root.right);
        List<Integer> rightBoundary = new LinkedList<>();
        calculateRightBoundary(root.right, rightBoundary);
        Collections.reverse(rightBoundary);
        boundary.addAll(rightBoundary);
        return boundary;
    }

    void calculateLeaves(TreeNode node) {
        if (node == null) {
            return;
        }
        if (isLeafNode(node)) {
            boundary.add(node.val);
        }
        calculateLeaves(node.left);
        calculateLeaves(node.right);
    }

    void calculateLeftBoundary(TreeNode node) {
        if (node == null) {
            return;
        }
        if (!isLeafNode(node)) {
            boundary.add(node.val);
        }
        if (node.left != null) {
            calculateLeftBoundary(node.left);
        } else {
            calculateLeftBoundary(node.right);
        }
    }

    void calculateRightBoundary(TreeNode node, List<Integer> rightBoundary) {
        if (node == null) {
            return;
        }
        if (!isLeafNode(node)) {
            rightBoundary.add(node.val);
        }
        if (node.right != null) {
            calculateRightBoundary(node.right, rightBoundary);
        } else {
            calculateRightBoundary(node.left, rightBoundary);
        }
    }

    boolean isLeafNode(TreeNode node) {
        return node.right == null && node.left == null;
    }
}
