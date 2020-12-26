package frequent.medium;

import utils.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView {

    public static void main(String [] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        //[1,2,3,null,5,null,4]
        node.left = node2;
        node.right = node3;
        node2.right = node5;
        node3.right = node4;

        BinaryTreeRightSideView bt = new BinaryTreeRightSideView();

        List<Integer> out = bt.rightSideView(node);
        System.out.println(out);

    }

    /**
     * here is a BFS implementations for the DFS algo below
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<> ();
        List<Integer> out = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); //get the size of queue and only process that many items off of the queue
            boolean toAdd = true;
            for (int i =0; i < size; i++) {
                TreeNode node = queue.poll();
                if (toAdd) {
                    out.add(node.val);
                    toAdd = false;
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return out;
    }



    Map<Integer, Integer> rightVals = new HashMap<>();
    /**
     * calls recurse to populate the level/val map and then prints it out in to a list
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        recurseTree(root, 0);
        List<Integer> out = new ArrayList<>();
        int count = rightVals.size();
        for (int i =0; i < count; i++) {
            out.add(rightVals.get(i));
        }
        return out;
    }

    /**
     * This code goes down a tree while giving priority for level values to nodes on right
     * @param node
     * @param level
     */
    void recurseTree(TreeNode node, Integer level) {
        if (node == null) return;
        if (!rightVals.containsKey(level)) {
            rightVals.put(level, node.val);
        }

        if (node.right != null) {
            recurseTree(node.right, level + 1);
        }
        if (node.left != null) {
            recurseTree(node.left, level + 1);
        }
    }
}
