package redo;

import utils.TreeNode;

import java.util.*;

public class NodesAtDistanceK {

    static class GraphNode {
        TreeNode node;
        List<GraphNode> children = new LinkedList<>();
    }

    Map<TreeNode, GraphNode> mapNodes = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        runDFS(root, null);

        return runBFS(target, K);
    }

    List<Integer> runBFS(TreeNode target, int dist) {
        GraphNode node = mapNodes.get(target);
        Set<GraphNode> set = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);
        set.add(node);
        int currDist = 0;

        while (!queue.isEmpty()) {
            GraphNode v = queue.poll();
            if (v == null) {
                currDist++;
                if (dist == currDist) {
                    List<Integer> ret = new LinkedList<>();
                    for (GraphNode n : queue) {
                        ret.add(n.node.val);
                    }
                    return ret;
                }
                queue.add(null);
            } else {
                for (GraphNode n: v.children) {
                    if (!set.contains(n)) {
                        set.add(n);
                        queue.add(n);
                    }
                }
            }

        }
        return new LinkedList<>();
    }

    void runDFS(TreeNode node, GraphNode parent) {
        if (node == null) return;

        GraphNode gNode = new GraphNode();
        gNode.node = node;
        mapNodes.put(node, gNode);

        if (parent != null) {
            gNode.children.add(parent);
            parent.children.add(gNode);
        }

        runDFS(node.left, gNode);
        runDFS(node.right, gNode);
    }
}
