package search.binary_search;

/**
 * 167. Two Sum II - Input array is sorted
 * Given an array of integers numbers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 *
 * Return the indices of the two numbers (1-indexed) as an integer array answer of size 2,
 * where 1 <= answer[0] < answer[1] <= numbers.length.
 *
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 *
 */

public class ArraySearchTargetSum {

    public static void main(String[] args) {
        ArraySearchTargetSum as = new ArraySearchTargetSum();
        int[] nums = {2, 3, 4, 7, 11, 17, 18};
        int[] ind = as.twoSum(nums, 14);
        System.out.println(ind[0] + " , " + ind[1]);
    }

    /**
     * simple two pointer approach
     * @param numbers
     * @param target
     * @return
     */
    int [] twoSum(int [] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target)
                return new int[] {low + 1, high + 1};
            else if (sum < target)
                ++low;
            else
                --high;
        }
        return new int[] {-1, -1};
    }
}
