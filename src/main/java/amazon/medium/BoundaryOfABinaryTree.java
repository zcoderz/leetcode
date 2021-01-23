package amazon.medium;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
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
