package linkedin;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {

    public static void main(String [] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node1.right = node3;
        FindLeavesOfBinaryTree findLeaves = new FindLeavesOfBinaryTree();
        List<List<Integer>> lists = findLeaves.findLeaves(node1);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        while(true) {
            List<Integer> leaves =  new ArrayList<>();
            boolean noChildren = processLeaves(root, leaves);
            list.add(leaves);
            if (noChildren) {
                break;
            }
        }
        return list;
    }

    boolean processLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return true;
        }
        boolean isLeaf = processLeaves(root.left, leaves);
        if (isLeaf) {
            root.left = null;
        }
        isLeaf = processLeaves(root.right, leaves);
        if (isLeaf) {
            root.right = null;
        }
        return false;
    }

}
