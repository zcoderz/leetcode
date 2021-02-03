package binary_tree;

/**
 * 112. Path Sum
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 *
 */
public class PathSum {

      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
               this.right = right;
          }
     }

     public boolean hasPathSum(TreeNode root, int sum) {
        return checkForSum(root, 0, sum);
     }

     boolean checkForSum(TreeNode node, int currSum, int target ) {
          if (node == null) return false;
          currSum += node.val;

          boolean leaf = false;
          if (node.left == null && node.right==null) {
              leaf = true;
          }

          if (leaf && (currSum == target)) {return true;}
          boolean match = checkForSum(node.left, currSum, target);
          if (match) return true;
         match = checkForSum(node.right, currSum, target);
         if (match) return true;

         return false;
     }
}
