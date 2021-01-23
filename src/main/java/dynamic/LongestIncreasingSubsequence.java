package dynamic;

import java.util.Arrays;

//a much simpler O(N^2) method exists
//store max sequences to curr in an array
//compare curr index against prior and increase size appropriately
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
