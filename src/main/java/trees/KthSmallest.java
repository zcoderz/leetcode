package trees;

import java.util.concurrent.atomic.AtomicInteger;

public class KthSmallest {

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

     public static void main (String [] args) {
         TreeNode theTree = new TreeNode(3);
         TreeNode theTreeA = new TreeNode(1);
         TreeNode theTreeB = new TreeNode (4);
         TreeNode theTreeC = new TreeNode(2);
         theTreeA.right = theTreeC;
         theTree.left = theTreeA;
         theTree.right = theTreeB;

         KthSmallest smallest = new KthSmallest();
         int theI = smallest.kthSmallest(theTree, 1);
         System.out.println(theI);

     }

    public int kthSmallest(TreeNode root, int k) {
         return recurse(root, k, new AtomicInteger(0));
    }

    public int recurse(TreeNode root, int k, AtomicInteger count) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int valL = recurse(root.left, k, count);
         if(valL != Integer.MIN_VALUE) {
             return valL;
         }



         int theNumber = count.addAndGet(1);
         if (theNumber == k) {
             return root.val;
         }

        int valR = recurse(root.right, k , count);
        if (valR != Integer.MIN_VALUE) {
            return valR;
        } else {
            return Integer.MIN_VALUE;
        }
    }

}
