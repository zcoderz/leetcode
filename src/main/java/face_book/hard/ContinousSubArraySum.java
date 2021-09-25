package face_book.hard;

import java.util.HashMap;
import java.util.Map;


/**
 * 523. Continuous Subarray Sum
 * Medium
 *
 * 726
 *
 * 124
 *
 * Add to List
 *
 * Share
 * Given an integer array nums and an integer k, return true if nums has a
 * continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * IMP-1
 *
 * ToDO: Add to website. this is a tricky one, redo...
 */
public class ContinousSubArraySum {

    public static void main (String [] args) {
        ContinousSubArraySum continous = new ContinousSubArraySum();
        int [] nums = {23,2,4,6,6};
        boolean res = continous.checkSubarraySum(nums, 7);
        System.out.println(res);
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            int mod = total % k;
            if (map.containsKey(mod) && (i - map.get(mod) > 1)) {
                return true;
            }
            map.put(mod, map.getOrDefault(mod, i));
        }
        return false;
    }
}
