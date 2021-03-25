package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 15. 3Sum
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * I have this solution coded under frequent/medium as well.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {1, -1, -1, 0};
        ThreeSum tSum = new ThreeSum();
        List<List<Integer>> sums = tSum.threeSum(nums);
        for (List<Integer> l : sums) {
            System.out.println(l);
        }
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> listSums = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) { //avoid duplicate checks
                findSums(nums[i], i + 1, nums, listSums);
            }
        }
        return listSums;
    }

    /**
     * Use a two pointer approach, start from smallest on left and largest on right then advance the right or left
     * pointer based on whether the sum from left and right indexes is less or greater than the target
     *
     * @param start
     * @param startIndex
     * @param nums
     * @param lSums
     */
    void findSums(int start, int startIndex, int[] nums,
                  List<List<Integer>> lSums) {
        int target = start * -1;
        int i = startIndex;
        int j = nums.length - 1;

        while (j > i) {
            int currSum = nums[i] + nums[j];
            if (currSum > target) {
                j--; //make currSum smaller
            } else if (currSum < target) {
                i++; //make currSum larger
            } else {
                lSums.add(Arrays.asList(start, nums[i], nums[j]));
                i++;
                j--;
                //advance pointers if repeated items
                //so that the next data set has a unique collection
                while (i < nums.length && nums[i] == nums[i - 1]) i++;
                while (nums[j] == nums[j + 1] && j > 0) j--;
            }
        }
    }
}
