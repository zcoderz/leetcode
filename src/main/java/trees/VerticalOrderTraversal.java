package trees;

import utils.Pair;
import utils.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    public static void main(String [] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node.left = node9; node.right = node20;
        node20.left = node15;
        node20.right = node7;
        VerticalOrderTraversal vertical = new VerticalOrderTraversal();
        List<List<Integer>> vert = vertical.verticalOrder(node);
        for (List<Integer> l : vert) {
            System.out.println(l);
        }
    }

    //using a tree so that the data is sorted in increased column order
    TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();

    /**
     * process the tree nodes in vertical order
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

    /**
     * the below logic doesnt work as nodes from lower tree levels can possibly get processed
     * before upper that's because the code iterates through left side entirely before moving over the the other side
     * Iterate through the tree and add nodes in to their appropriate indexes
     * @param treeNode
     * @param colIndex
     */
    void processTreeWrong(TreeNode treeNode, int colIndex) {
        List<Integer> list = treeMap.getOrDefault(colIndex, new ArrayList<>());
        list.add(treeNode.val);
        treeMap.put(colIndex, list);
        if (treeNode.left != null) {
            processTreeWrong(treeNode.left, colIndex-1);
        }
        if (treeNode.right != null) {
            processTreeWrong(treeNode.right, colIndex+1);
        }
    }

}
