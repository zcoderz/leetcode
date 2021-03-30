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

    /**
     * this is a clever idea. the dp array will always be sorted
     * therefore, we can rely on the array index where data is being inserted to determine the max sequence length
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            //note you are searching the dp array on range 0 to len
            //where len signifies the max increasing sequence length
            int index = Arrays.binarySearch(dp, 0, len, num);
            if (index < 0) {
                //binary search returns (-index -1) if not found, so adjust to correct index
                index = -(index + 1);
            }
            dp[index] = num; //add the number to its correct index
            if (len == index) { //need to increment max length when index ==len, since index starts at 0
                len++;
            }
        }
        return len;
    }

}
