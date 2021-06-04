package face_book.medium;

import utils.TreeNode;

import java.util.*;

public class NodesAtDistanceK {

    public static void main(String [] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node3.left = node5;
        node3.right = node1;
        node5.left= node6;
        node5.right = node2;
        node2.left=node7;
        node2.right=node4;
        node1.left=node0;
        node1.right = node8;

        NodesAtDistanceK test = new NodesAtDistanceK();
        List<Integer> res = test.distanceK(node3, node5, 2);
        System.out.println(res);
    }

    static class Node {
        List<Node> children = new LinkedList<>();
        int val;
    }

    Map<TreeNode, Node> nodeMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        runDfs(root, null);
        return  runBFS(target, K);
    }

    List<Integer> runBFS(TreeNode target, int K) {
        List<Integer> integers = new ArrayList<>();
        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        Node n = nodeMap.get(target);
        if (n==null) {
            return integers;
        }
        if (K==0) {
            integers.add(n.val);
            return integers;
        }
        queue.add(n);
        queue.add(null);
        Set<Node> seen = new HashSet<>();
        seen.add(n);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                depth++;
                if (depth == K) {
                    for (Node p : queue) {
                        integers.add(p.val);
                    }
                    return integers;
                }
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            } else {
                for (Node c : node.children) {
                    if (!seen.contains(c)) {
                        seen.add(c);
                        queue.add(c);
                    }
                }
            }
        }
        return integers;
    }

    void runDfs(TreeNode node, TreeNode parent) {
        if (node == null) return;
        Node n = new Node(); n.val = node.val;
        nodeMap.put(node, n);
        if (parent != null) {
            Node p = nodeMap.get(parent);
            n.children.add(p);
            p.children.add(n);
        }
        runDfs(node.left, node);
        runDfs(node.right, node);
    }
}
