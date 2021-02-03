package amazon.medium;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 545. Boundary of Binary Tree
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.
 *
 * The left boundary is the set of nodes defined by the following:
 *
 * The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 * If a node in the left boundary and has a left child, then the left child is in the left boundary.
 * If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 * The leftmost leaf is not in the left boundary.
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 *
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 *
 * Given the root of a binary tree, return the values of its boundary.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 *   4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 *
 * the leetcode solution for this problem has a bug they indicate to calculate left side view of tree without leaves,
 * leaf nodes and reverse right side view without leaves union them together to return result. but they miss the below
 * case which has 1 missing from boundary
 * <p>
 * the code below outputs 1 which is missing from leet code's solution
 * <p>
 * Input [4,2,null,3,1,null,null,5] Output [4,2,3,5,1] Expected [4,2,3,5]
 * <p>
 * the below code walks the left and right trees to calculate boundaries. reverse the right boundary and merges it to
 * the left boundary. code has some edge cases but is fairly simple.
 */
public class BoundaryOfABinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> leftBoundary = new ArrayList<>();

        calculateLeftBoundary(root.left, leftBoundary, false, false);
        leftBoundary.add(0, root.val);

        List<Integer> rightBoundary = new ArrayList<>();
        calculateRightBoundary(root.right, rightBoundary, false, false);
        Collections.reverse(rightBoundary);
        leftBoundary.addAll(rightBoundary);

        return leftBoundary;
    }

    void calculateRightBoundary(TreeNode node, List<Integer> l, boolean isLeft, boolean otherNull) {
        if (node == null) return;
        if (!isLeft) {
            l.add(node.val);
            calculateRightBoundary(node.right, l, false, node.left == null);
            calculateRightBoundary(node.left, l, true, node.right == null);
        } else {
            if (otherNull) {
                l.add(node.val);
            }
            if (node.right != null) {
                calculateRightBoundary(node.right, l, false, node.left == null);
            }
            if (node.left != null) {
                calculateRightBoundary(node.left, l, true, node.right == null);
            } else if (!otherNull) {
                l.add(node.val);
            }
        }
    }

    void calculateLeftBoundary(TreeNode node, List<Integer> l, boolean isRight, boolean otherNull) {
        if (node == null) return;
        if (!isRight) {
            l.add(node.val);
            calculateLeftBoundary(node.left, l, false, node.right == null);
            calculateLeftBoundary(node.right, l, true, node.left == null);
        } else {
            if (otherNull) {
                l.add(node.val);
            }
            if (node.left != null) {
                calculateLeftBoundary(node.left, l, false, node.right == null);
            }
            if (node.right != null) {
                calculateLeftBoundary(node.right, l, true, node.left == null);
            } else if (!otherNull) {
                l.add(node.val);
            }
        }
    }


}
