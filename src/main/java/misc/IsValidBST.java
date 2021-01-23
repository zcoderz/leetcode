package misc;

public class IsValidBST {
    public static enum CompareType {
        LESS, GREATER
    }

    /**
     * Definition for a binary tree node.
     **/

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    ;

    class Solution {

        public boolean isValidBST(TreeNode root) {

            isValidBST(root.left);
            isValidBST(root.right);
            boolean comp = compareBST(root.left, CompareType.LESS, root.val);
            if (!comp) return false;
            comp = compareBST(root.right, CompareType.GREATER, root.val);
            if (!comp) return false;
            return true;
        }

        boolean compareBST(TreeNode node, CompareType compareType, Integer val) {
            if (node == null) {
                return true;
            }
            boolean compVal = compare(node.val, val, compareType);
            if (!compVal) {
                return false;
            }
            compVal = compareBST(node.left, compareType, val);
            if (!compVal) {
                return false;
            }
            compVal = compareBST(node.right, compareType, val);
            if (!compVal) {
                return false;
            }

            return true;
        }

        boolean compare(Integer nodeVal, Integer val, CompareType compareType) {
            if (compareType == CompareType.LESS) {
                return val > nodeVal;
            } else {
                return val < nodeVal;
            }
        }


    }
}
