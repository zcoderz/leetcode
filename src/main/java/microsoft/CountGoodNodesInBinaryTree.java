package microsoft;

import utils.TreeNode;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class CountGoodNodesInBinaryTree {
    TreeMap<Integer, Integer> treeSet = new TreeMap<>();
    int numGoodNodes;

    public static  void main(String [] args) {
        TreeNode node3a = new TreeNode(3);
        TreeNode node1a = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1b = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);

        node3a.left = node1a;
        node1a.left = node3;
        node3.right = node4;
        node4.left = node1b;
        node4.right = node5;

        CountGoodNodesInBinaryTree gn = new CountGoodNodesInBinaryTree();
        int ct = gn.goodNodes(node3a);
        System.out.println(ct);
    }

    public int goodNodes(TreeNode root) {
        recurseTree(root, treeSet);
        return numGoodNodes;
    }

    void recurseTree(TreeNode root, TreeMap<Integer, Integer> treeSet) {
        if (root == null) return;
        int val = root.val;
        if (treeSet.isEmpty() ||  treeSet.lastKey() <= val) {
            numGoodNodes++;
        }
        int count = treeSet.getOrDefault(val, 0) + 1;
        treeSet.put(val, count);
        recurseTree(root.left, treeSet);
        recurseTree(root.right, treeSet);
        count--;
        if (count ==0) {
            treeSet.remove(val);
        } else {
            treeSet.put(val, count);
        }
    }
}
