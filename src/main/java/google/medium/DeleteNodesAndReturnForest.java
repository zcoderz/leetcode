package google.medium;


import utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5] Output: [[1,2,null,4],[6],[7]]
 *
 *
 * IMP-2: Good practice on tree traversal for deletes
 */
public class DeleteNodesAndReturnForest {

    List<TreeNode> roots = new ArrayList<>();

    /**
     * track the numbers to be deleted in a set this is simple. traverse tree before recursion stack returns delete the
     * left and or right child if they are in the set to be deleted. to ensure that the set of items that are to be
     * deleted get trimmed after every delete, remove the deleted item from the set
     *
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int v : to_delete) {
            set.add(v);
        }

        //to do figure out how to handle delete of root
        traverseTree(root, set);
        if (set.contains(root.val)) {
            if (root.left != null) {
                roots.add(root.left);
            }
            if (root.right != null) {
                roots.add(root.right);
            }
        } else {
            roots.add(root);
        }
        return roots;
    }

    void traverseTree(TreeNode node, Set<Integer> toDelete) {
        if (node == null) {
            return;
        }

        traverseTree(node.left, toDelete);
        traverseTree(node.right, toDelete);

        if ((node.left != null) && (toDelete.contains(node.left.val))) {
            toDelete.remove(node.left.val);
            if (node.left.left != null) {
                roots.add(node.left.left);
            }
            if (node.left.right != null) {
                roots.add(node.left.right);
            }
            node.left = null;
        }

        if ((node.right != null) && (toDelete.contains(node.right.val))) {
            toDelete.remove(node.right.val);
            if (node.right.left != null) {
                roots.add(node.right.left);
            }
            if (node.right.right != null) {
                roots.add(node.right.right);
            }
            node.right = null;
        }
    }

}
