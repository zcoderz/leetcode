package frequent.medium;


/**
 * 152. Maximum Product Subarray
*  Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * IMP-3
 */
public class MaxProductSubArray {

    public static void main(String [] args) {
        MaxProductSubArray maxProductSubArray = new MaxProductSubArray();
        int [] nums = {-1,-2,-9,-6};
        maxProductSubArray.maxProduct(nums);
        System.out.println(maxProductSubArray.maxSubArr);
    }

    int maxSubArr = Integer.MIN_VALUE;

    /**
     * keeping track of current max, min
     * need to keep track of max and min because two negatives make a positive.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int priorMin = 1;
        int priorMax = 1;
        for (int j =0; j < nums.length; j++) {
            int max = Math.max(nums[j] * priorMax, nums[j] * priorMin);
            int min = Math.min(nums[j] * priorMax, nums[j] * priorMin);
            priorMax = Math.max(nums[j] , max);
            priorMin = Math.min(nums[j] , min);
            this.maxSubArr = Math.max(priorMax, this.maxSubArr);
        }
        return this.maxSubArr;
    }

}
