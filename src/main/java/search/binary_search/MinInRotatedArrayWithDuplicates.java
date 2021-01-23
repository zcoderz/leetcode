package search.binary_search;

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
