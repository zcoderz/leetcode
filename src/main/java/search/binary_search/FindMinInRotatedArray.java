package search.binary_search;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 */
public class FindMinInRotatedArray {
    //this problem is similar to rotated array, essentially we are finding the rotation index
    //which will tell us the min point.

    public static void main(String [] args) {
        FindMinInRotatedArray minR = new FindMinInRotatedArray();
        int []nums = {2,0,1};
        int i = minR.findMin(nums);
        System.out.println(i);
    }

    public int findMin(int[] nums) {
        if (nums[nums.length-1] > nums[0]) { //no rotation
            return nums[0];
        }
        return nums[findRotation(nums)];
    }

    int findRotation(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        while (high > low) {
            int mid = low + (high-low)/2;
            if (nums[mid]>nums[high]) {
                low = mid+1; //in case transition to low didnt occur before the mid, it must happen after the mid
            } else if (nums[mid] < nums[high]) {
                //in this case you are covering until the mid assuming transition from high to low
                //happened at or before mid. this is fine given the array is sorted
                high=mid;
            }

        }
        return low;
    }

}
