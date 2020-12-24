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
        //add the nodes to a set so we can identify when these nodes occur
        nodesToFind.add(p);
        nodesToFind.add(q);
        //start with root added to the stack
        nodeStack.add(new NodeVisitor(root, ChildVisited.NONE));
        TreeNode lca = null;
        while (!nodeStack.isEmpty()) {
            //start with looking at the stack to see whats on it
            NodeVisitor visitor = nodeStack.peek();
            if (nodesToFind.contains(visitor.treeNode)) {
                //if we find the node we are searching for
                if ((lca != null) && (nodesToFind.size() == 1)) {
                    return lca; //if the second node is found then return the lca
                } else {
                    lca = visitor.treeNode; //this is the first node that we found. set lca to it
                }
                nodesToFind.remove(visitor.treeNode); //remove the node we just found from the set of nodes
            }
            if (visitor.visited == ChildVisited.NONE) {
                visitor.visited = ChildVisited.LEFT;//first move to left and add to stack
                if (visitor.treeNode.left != null) {
                    nodeStack.add(new NodeVisitor(visitor.treeNode.left, ChildVisited.NONE));
                }
            } else if (visitor.visited == ChildVisited.LEFT) {
                visitor.visited = ChildVisited.RIGHT;//if left has been visited then move to the right
                if (visitor.treeNode.right != null) {
                    nodeStack.add(new NodeVisitor(visitor.treeNode.right, ChildVisited.NONE));
                }
            } else { //if both left and right have been visited
                nodeStack.pop();
                if (lca == visitor.treeNode && nodesToFind.size()==1) {
                    //if we have found one of the items already then adjust lca to its parent as move
                    //down in stack to the parent
                    lca = nodeStack.peek().treeNode;
                }
            }
        }
        return null;
    }
}
