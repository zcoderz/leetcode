package trees;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LeastCommonAncestor {

    public static enum ChildVisited {
        NONE, LEFT, RIGHT
    };

    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    public static class NodeVisitor {
        TreeNode treeNode;
        ChildVisited visited;
        public NodeVisitor(TreeNode node, ChildVisited visited) {
            this.treeNode = node;
            this.visited = visited;
        }
    }

    Stack<NodeVisitor> nodeStack = new Stack<>();
    Set<TreeNode> nodesToFind = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        nodesToFind.add(p);
        nodesToFind.add(q);
        nodeStack.add(new NodeVisitor(root, ChildVisited.NONE));
        TreeNode lca = null;
        while (!nodeStack.isEmpty()) {
            NodeVisitor visitor = nodeStack.peek();
            if (nodesToFind.contains(visitor.treeNode)) {
                if ((lca != null) && (nodesToFind.size() == 1)) {
                    return lca;
                } else {
                    lca = visitor.treeNode;
                }
                nodesToFind.remove(visitor.treeNode);
            }
            if (visitor.visited == ChildVisited.NONE) {
                visitor.visited = ChildVisited.LEFT;
                if (visitor.treeNode.left != null) {
                    nodeStack.add(new NodeVisitor(visitor.treeNode.left, ChildVisited.NONE));
                }
            } else if (visitor.visited == ChildVisited.LEFT) {
                visitor.visited = ChildVisited.RIGHT;
                if (visitor.treeNode.right != null) {
                    nodeStack.add(new NodeVisitor(visitor.treeNode.right, ChildVisited.NONE));
                }
            } else {
                if (lca == visitor.treeNode && nodesToFind.size()==1) {
                    nodeStack.pop();
                    lca = nodeStack.peek().treeNode;
                } else {
                    nodeStack.pop();
                }
            }
        }
        return null;
    }


}
