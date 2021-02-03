package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 16. 3Sum Closest
 *
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is
 * closest to target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 */
public class ThreeSumClosest {


    private int closest = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        int[] nums = {1, -3, 3, 5, 4, 1};
        ThreeSumClosest tSum = new ThreeSumClosest();
        tSum.threeSumClosest(nums, 1);
        System.out.println(tSum.closest);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> listSums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            findSums(nums[i], i + 1, nums, target);
        }
        return closest;
    }

    void findSums(int start, int startIndex, int[] nums, int origTarget) {
        int i = startIndex;
        int j = nums.length - 1;

        while (j > i) {
            int currSum = nums[i] + nums[j] + start;
            if (currSum > origTarget) {
                j--; //make currSum smaller
            } else if (currSum < origTarget) {
                i++; //make currSum larger
            } else {
                i++;
                j--;
                while (i < nums.length && nums[i] == nums[i - 1]) i++;
                while (nums[j] == nums[j + 1] && j > 0) j--;
            }
            if (Math.abs(currSum - origTarget) < Math.abs(closest - origTarget)) {
                closest = currSum;
            }
        }
    }
}
