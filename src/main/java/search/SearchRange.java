package search;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * IMP-1: Great question! should practice this again. the solution is amazingly simple.
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = {0,0, 2, 2, 3,4,5,6,7};
        SearchRange range = new SearchRange();
        int [] index = range.searchRange(nums, 2);
        System.out.println(index[0] + " " + index[1]);
    }

    /**
     * very neat method. note that for the right index, it will return 1 past the index
     * @param nums
     * @param target
     * @param left
     * @return
     */
    public static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target // mid is greater than target
                    //mid is same as target but you are searching for the left most index of the target
                    || (left && target == nums[mid]))
            {
                hi = mid;
            }
            else {
                //target is to the right of the mid
                //or you are searching for the right most index
                lo = mid+1;
            }
        }
        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIdx = extremeInsertionIndex(nums, target, true);
        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }
        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;
        return targetRange;
    }
}
