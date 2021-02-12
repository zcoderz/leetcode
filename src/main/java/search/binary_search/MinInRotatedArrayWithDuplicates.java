package search.binary_search;


/**
 * 154. Find Minimum in Rotated Sorted Array II
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 *
 * Leetcode has an excellent explanation for this problem.
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/
 *
 * MP-1: Search rotated array and search with duplicates is a common set of problems that needs to be practiced
 */
public class MinInRotatedArrayWithDuplicates {

    public static void main(String[] args) {
        MinInRotatedArrayWithDuplicates minR = new MinInRotatedArrayWithDuplicates();
        int[] nums = {2, 2, 2, 0, 1};
        int i = minR.findMin(nums);
        System.out.println(i);
    }

    public int findMin(int[] nums) {
        if (nums[nums.length - 1] > nums[0]) { //no rotation
            return nums[0];
        }
        return nums[findRotation(nums)];
    }

    /**
     * Case 1). nums[mid] < nums[high]  :  turning point is to left of mid
     * Case 2). nums[mid] > nums[high] : turning point is to right of mid
     * Case 3). nums[mid] == nums[high] :  turning could be left or right of mid. safe logic is to reduce high by 1
     * @param nums
     * @return
     */
    int findRotation(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (high > low) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1; //in case transition to low didnt occur before the mid, it must happen after the mid
            } else if (nums[mid] < nums[high]) {
                //in this case you are covering until the mid assuming transition from high to low
                //happened at or before mid. this is fine given the array is sorted
                high = mid;
            } else {
                high--; // this is a special case where if mid and high are same, move high one back
            }

        }
        return low;
    }
}
