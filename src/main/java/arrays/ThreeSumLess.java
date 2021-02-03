package arrays;

import java.util.Arrays;

/**
 * 259. 3Sum Smaller
 *
 * Given an array of n integers nums and an integer target, find the number of index
 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Follow up: Could you solve it in O(n2) runtime?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 *
 */
public class ThreeSumLess {

    public static void main(String[] args) {
        int[] nums = {3, 1, 0, -2};
        ThreeSumLess tSum = new ThreeSumLess();
        int sum = tSum.threeSum(nums, 4);
        System.out.println(sum);
    }


    public int threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += findSums(nums[i], i + 1, nums, target);
        }
        return sum;
    }

    int findSums(int start, int startIndex, int[] nums, int target) {
        target = target - start;
        int i = startIndex;
        int j = nums.length - 1;
        int sum = 0;
        while (j > i) {
            int currSum = nums[i] + nums[j];
            if (currSum >= target) {
                j--; //make currSum smaller
            } else {
                sum += j - i;
                i++;
            }
        }
        return sum;
    }
}

