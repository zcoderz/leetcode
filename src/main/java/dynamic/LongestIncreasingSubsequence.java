package dynamic;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without
 * changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * IMP-1
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        int lon = l.lengthOfLIS(arr);
        System.out.println(lon);
    }

    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(dp, 0, len, num);

            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = num;
            if (len == index) {
                len++;
            }
        }
        return len;

    }

}
