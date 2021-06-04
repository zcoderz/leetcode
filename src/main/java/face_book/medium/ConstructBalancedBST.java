package face_book.medium;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ConstructBalancedBST {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> sorted = new ArrayList<>();
        inOrderTraversal(root, sorted);
        return constructBalancedBstFromSortedNodes(0, sorted.size()-1, sorted);
    }

    TreeNode constructBalancedBstFromSortedNodes(int lo, int hi, List<TreeNode> sorted) {
        if (lo > hi) {
            return null;
        }
        int mid = lo+ (hi-lo)/2;
        TreeNode theNode = sorted.get(mid);
        theNode.left = constructBalancedBstFromSortedNodes(lo, mid-1, sorted);
        theNode.right = constructBalancedBstFromSortedNodes(mid+1, hi, sorted);
        return theNode;
    }

    private void inOrderTraversal(TreeNode node, List<TreeNode> sorted) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, sorted);
        sorted.add(node);
        inOrderTraversal(node.right, sorted);
    }
}
