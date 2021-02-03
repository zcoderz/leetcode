package arrays;


/**
 * 209. Minimum Size Subarray Sum
 *
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 *
 */
public class MinSubArray {

    public static void main(String[] args) {
        MinSubArray minSubArray = new MinSubArray();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int sum = 7;
        int len = minSubArray.minSubArrayLen(sum, nums);
        System.out.println(len);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int left = 0;
        int sum = 0;
        int minSubArrayLen = Integer.MAX_VALUE;
        for (; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                minSubArrayLen = Math.min(minSubArrayLen, i - left);
                sum -= nums[left++];
            }
        }
        return (minSubArrayLen == Integer.MAX_VALUE) ? 0 : minSubArrayLen + 1;
    }
}
