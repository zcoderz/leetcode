package trees;

import utils.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 230. Kth Smallest Element in a BST
 * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 */
public class KthSmallest {

    public static void main(String[] args) {
        TreeNode theTree = new TreeNode(3);
        TreeNode theTreeA = new TreeNode(1);
        TreeNode theTreeB = new TreeNode(4);
        TreeNode theTreeC = new TreeNode(2);
        theTreeA.right = theTreeC;
        theTree.left = theTreeA;
        theTree.right = theTreeB;

        KthSmallest smallest = new KthSmallest();
        int theI = smallest.kthSmallest(theTree, 1);
        System.out.println(theI);

    }

    int count;

    public int kthSmallest(TreeNode root, int k) {
        return recurse(root, k);
    }

    /**
     * recurse in the BST until the kth element is found
     * Integer.MIN_VALUE is sentinel, return of a number other than Integer.MIN_VALUE means
     * the value is found.
     * @param root
     * @param k
     * @return
     */
    public int recurse(TreeNode root, int k) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int valL = recurse(root.left, k);
        if (valL != Integer.MIN_VALUE) {
            return valL;
        }
        count++;
        if (count == k) {
            return root.val;
        }
        int valR = recurse(root.right, k);
        if (valR != Integer.MIN_VALUE) {
            return valR;
        } else {
            return Integer.MIN_VALUE;
        }
    }

}
