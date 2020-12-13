package trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static void main (String [] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;

        ZigZagTraversal traversal = new ZigZagTraversal();
        traversal.zigzagLevelOrder(node3);
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean traverse_left = false;
        List<List<Integer>> arr = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); queue.add(null);
        LinkedList<Integer> currentLevel = new LinkedList<>();

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (traverse_left) {
                    currentLevel.addFirst(node.val);
                } else {
                    currentLevel.addLast(node.val);
                }
            } else {
                arr.add(currentLevel);
                currentLevel = new LinkedList<>();
                traverse_left = !traverse_left;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }

            if (null != node && node.left != null) {
                queue.add(node.left);
            }
            if(null != node && node.right != null) {
                queue.add(node.right);
            }


        }

        return arr;

    }

}
