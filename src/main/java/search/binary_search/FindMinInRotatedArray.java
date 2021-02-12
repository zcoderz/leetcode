package search.binary_search;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums, return the minimum element of this array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * This problem is similar to MinInRotatedArrayWithDuplicates and to SearchRotated
 * Essentially we are finding the rotation index which will tell us the min point.
 *
 * IMP-1: Search rotated array and search with duplicates is a common set of problems that needs to be practiced
 */
public class FindMinInRotatedArray {


    public static void main(String[] args) {
        FindMinInRotatedArray minR = new FindMinInRotatedArray();
        int[] nums = {2, 0, 1};
        int i = minR.findMin(nums);
        System.out.println(i);
    }

    public int findMin(int[] nums) {
        if (nums[nums.length - 1] > nums[0]) { //no rotation
            return nums[0];
        }
        return nums[findRotation(nums)];
    }

    int findRotation(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (high > low) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1; //in case transition to low didnt occur before the mid, it must happen after the mid
            } else if (nums[mid] < nums[high]) {
                //in this case you are covering until the mid assuming transition
                //happened at or before mid. this is fine given the array is sorted
                high = mid;
            }
        }
        return low;
    }

}
