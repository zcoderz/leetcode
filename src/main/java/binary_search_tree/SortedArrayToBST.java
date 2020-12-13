package binary_search_tree;

import utils.TreeNode;

import java.util.Random;


public class SortedArrayToBST {

    Random random = new Random();

    public static void main(String [] args) {
        int [] nums = {-10,-3,0,5,9};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return processBST(nums, 0, nums.length-1);
    }

    /**
     * divide the array
     * @param nums
     * @param left
     * @param right
     * @return
     */
    TreeNode processBST(int [] nums, int left , int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        if ((left + right ) % 2 == 1) {
            //if you are landing on an uneven number, randomly choose to pick left or right of it
            int adjustment = random.nextInt(2);
            mid += adjustment;
        }

        TreeNode node = new TreeNode(nums[mid]);
        node.left = processBST(nums, left , mid-1);
        node.right = processBST(nums, mid+1, right);

        return node;
    }

}
