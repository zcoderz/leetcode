package frequent.medium;

import java.util.*;

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
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 *
 * IMP-1: Common question. I have this coded in arrays as well.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int nums[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> theList = threeSum(nums);
        return;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        Integer firstNum;
        for (int i = 0; i < nums.length; i++) {
            firstNum = nums[i];
            if (firstNum >= 0) {
                break;
            }
            int front = i + 1;
            int back = nums.length - 1;
            int target = -firstNum;
            while (front < back) {
                int sum = nums[front] + nums[back];
                if (sum > target) {
                    back--;
                } else if (sum < target) {
                    front++;
                } else {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(firstNum);
                    arr.add(nums[front]);
                    arr.add(nums[back]);
                    triplets.add(arr);
                    while (front < back && nums[front] == arr.get(1)) {
                        front++;
                    }
                    while (front < back && nums[back] == arr.get(2)) {
                        back--;
                    }
                }
            }

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return triplets;
    }

    /**
     * Here is an implementation of 3Sum using hashmap and hashset from leet code.
     * This logic doesnt require the incoming array to be sorted
     *
     * Below are some nice notes on the algo from leet code
     *
     *
     * Use another hashset dups to skip duplicates in the outer loop.
     * Without this optimization, the submission will time out for the test case with 3,000 zeroes. This case is handled naturally when the array is sorted.
     * Instead of re-populating a hashset every time in the inner loop, we can use a hashmap and populate it once. Values in the hashmap will indicate whether we have encountered that element in the current iteration. When we process nums[j] in the inner loop, we set its hashmap value to i. This indicates that we can now use nums[j] as a complement for nums[i].
     * This is more like a trick to compensate for container overheads. The effect varies by language, e.g. for C++ it cuts the runtime in half. Without this trick the submission may time out.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumHash(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    //if map contains the complement and it occurred in the current iteration of the outer loop
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }
}
