package trees;

import utils.Node;
import utils.Pair;
import utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a binary tree where every node has a unique value, and a target key k,
 * find the value of the nearest leaf node to target k in the tree.
 *
 * Here, nearest to a leaf means the least number of edges travelled on the binary
 * tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 * In the following examples, the input tree is represented in flattened form row by row.
 * The actual root tree given will be a TreeNode object.
 */
public class ClosestLeafInABinaryTree {

    public static void main(String [] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);


//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//        node4.left = node6;
//        node6.left = node8;
//        node6.right = node9;
//        node5.right = node7;
//        node7.left = node10;

        ClosestLeafInABinaryTree closestLeafInABinaryTree = new ClosestLeafInABinaryTree();
        int val = closestLeafInABinaryTree.findClosestLeaf(node1, 1);
        System.out.println(val);
    }

    Node searchNode;
    public int findClosestLeaf(TreeNode root, int k) {
        //handle root separately
        if (root.val == k && root.left == null && root.right == null) return k;
        recurseTree(root, k, null);
        return bfs(searchNode, root.val);
    }

    /**
     * do a BFS of the tree starting from the target node. as soon as you find the first leaf node stop.
     * @param startNode
     * @param rootVal
     * @return
     */
    int bfs(Node startNode, int rootVal) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int iQueueSize = queue.size();
            for (int i = 0; i < iQueueSize; i++) {
                Node node = queue.poll();
                //dont process root as a leaf node as root can have a single child and thus be treated
                //as a leaf node
                if (node.children.size() == 1 && node.val != rootVal ) {
                    return node.val;
                }
                visited.add(node);
                for (Node child : node.children) {
                    if (!visited.contains(child)) {
                        queue.add(child);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * traverse the tree and transform it into a graph
     * @param treeNode
     * @param k
     * @param parent
     */
    void recurseTree(TreeNode treeNode, int k, Node parent) {
        Node theNode = new Node(treeNode.val);
        if (treeNode.val == k) {
            searchNode = theNode;
        }
        if (parent != null) {
            parent.children.add(theNode);
            theNode.children.add(parent);
        }
        if (treeNode.left != null) {
            recurseTree(treeNode.left, k, theNode);
        }
        if (treeNode.right != null) {
            recurseTree(treeNode.right, k, theNode);
        }
    }

}