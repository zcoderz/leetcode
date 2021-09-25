package face_book.medium;

import utils.Pair;
import utils.TreeNode;

import java.util.*;


/**
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 *
 */
public class VerticalOrderTraversal {

    //using a tree so that the data is sorted in increased column order
    TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node.left = node9;
        node.right = node20;
        node20.left = node15;
        node20.right = node7;
        VerticalOrderTraversal vertical = new VerticalOrderTraversal();
        List<List<Integer>> vert = vertical.verticalOrder(node);
        for (List<Integer> l : vert) {
            System.out.println(l);
        }
    }

    /**
     * process the tree nodes in vertical order
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        processTree(root);
        List<List<Integer>> retList = new ArrayList<>(treeMap.values());
        return retList;
    }

    /**
     * go down the tree one row at a time while tracking the column index for each node.
     *
     * @param treeNode
     */
    void processTree(TreeNode treeNode) {
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, treeNode));
        while (!queue.isEmpty()) {
            int sz = queue.size();
            while (sz > 0) {
                Pair<Integer, TreeNode> pair = queue.poll();
                List<Integer> list = treeMap.getOrDefault(pair.first, new ArrayList<>());
                list.add(pair.second.val);
                treeMap.put(pair.first, list);
                if (pair.second.left != null) {
                    queue.add(new Pair<>(pair.first - 1, pair.second.left));
                }
                if (pair.second.right != null) {
                    queue.add(new Pair<>(pair.first + 1, pair.second.right));
                }
                sz--;
            }
        }
    }
}
