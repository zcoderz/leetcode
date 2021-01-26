package google.medium;


import java.util.*;

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 *
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that
 * the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 *
 * IMP-1 this algo is a very clever approach to the problem and the approach is very different from other common
 * approaches and hence imp to practice this one carefully
 */
public class LongestContinuousDiffLess {


    public static void main(String [] args) {

        int [] nums = {1,5,6,7,8,10,6,5,6};
        int limit = 4;

        LongestContinuousDiffLess longest = new LongestContinuousDiffLess();
        int out = longest.longestSubarray(nums, limit);
        System.out.println(out);

    }

    /**
     * Very clever algo , got this from someone's discussion on leetcode.
     *
     * essentially creating two queues (increasing and decreasing)
     * compare the first in the queue (largest - smallest)
     * if greater than limit start removing items from the queue head while moving the left index right (i variable)
     *
     * using array queue here is more efficient than using a min or max heap as per the below algo the queue items
     * are trimmed by the algo itself to ensure increase and decrease in queues
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        ArrayDeque<Integer> increasing = new ArrayDeque<> ();
        ArrayDeque<Integer> decreasing = new ArrayDeque<> ();
        increasing.offer(nums[0]); decreasing.offer(nums[0]);
        int len = nums.length;
        int res = 1;
        int i = 0;
        for (int j = 1; j < len; j++) {
            //make sure that num[i] gets added into the correct slot in the increasing array
            while (!increasing.isEmpty() && increasing.getLast() > nums[j]) {
                increasing.pollLast();
            }
            //make sure that num[i] gets added into the correct slot in the decreasing array
            while (!decreasing.isEmpty() && decreasing.getLast() < nums[j]) {
                decreasing.pollLast();
            }
            //add the new element into the increasing / decreasing queues
            increasing.offer(nums[j]);
            decreasing.offer(nums[j]);
            //if the difference between highest and lowest element in the two queues is
            //greater than the limit then start dropping the elements from the largest/smallest elements from
            //the queues as you iterate left index right (i is the left index)
            while ((decreasing.peekFirst() - increasing.peekFirst()) > limit) {
                if (increasing.peekFirst() == nums[i]) {
                    increasing.pollFirst();
                }
                if (decreasing.peekFirst() == nums[i]) {
                    decreasing.pollFirst();
                }
                i++;
            }
            //size of longest contiguous array is difference between right and left indices
            res = Math.max(res, j-i+1);
        }
        return res;
    }

}
