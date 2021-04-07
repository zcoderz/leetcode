package search.binary_search;

/**
 * 33. Search in Rotated Sorted Array
 * You are given an integer array nums sorted in ascending order (with distinct values), and an integer target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * If target is found in the array return its index, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * IMP-1: Common interview question : logic is that you find the turning point of the numbers in the array
 * once you find the turning point, look at the left or right of the turning point based on whether the value is
 * greater than the start value or smaller than it
 */
public class SearchRotated {

    public static void main(String[] args) {

        int[] arr = {9, 10, 11, 13, 15, 17, 2, 3, 5, 6, 8};
        SearchRotated searchRotated = new SearchRotated();
        int val = searchRotated.search(arr, 1);
        System.out.println(val);
    }

    /**
     * first find the turning point and then search left or right of it
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 && nums[0] != target) {
            return -1;
        }

        int startVal = nums[0];
        int turningPoint = findTurning(startVal, 1, nums.length - 1, nums);

        if (nums[turningPoint] == target) {
            return turningPoint;
        } else if (startVal > target) {
            return java.util.Arrays.binarySearch(nums, turningPoint, nums.length, target);
        } else {
            return java.util.Arrays.binarySearch(nums, 0, turningPoint, target);
        }

    }

    /**
     * start from the first index in the array compare mid value against start value
     * if mid is greater then turning point is to the right
     * if mid is smaller then turning point is to the left (inclusive of the mid index)
     */
    int findTurning(int startVal, int startIndex, int lastIndex, int[] nums) {
        if (startIndex >= lastIndex) {
            return lastIndex;
        }
        int mid = (startIndex + lastIndex) / 2;
        int midVal = nums[mid];
        if (midVal > startVal) {
            return findTurning(startVal, mid + 1, lastIndex, nums);
        } else {
            return findTurning(startVal, startIndex, mid, nums);
        }
    }

    /**
     * this is a single pass solution to the problem
     * the checks are based on :
     * 1. whether rotation is to the left or right of mid
     * 2. a decision to go left or right of the mid based on target, value at mid and the value at the starting index
     * @param nums
     * @param low
     * @param high
     * @param target
     * @return
     */
    int binary_search(int[] nums, int low, int high, int target) {
        while (high >= low) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if ((nums[low] > nums[mid])) {
                //rotation is on left
                if ((nums[mid] > target) || (target >= nums[low])) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //rotation is not on left
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; //target is between mid and low
                } else {
                    low = mid + 1; //target is on right
                }
            }
        }
        return -1;
    }
}
