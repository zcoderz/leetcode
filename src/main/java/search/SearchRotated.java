package search;

public class SearchRotated {

    public static void main(String [] args) {

        int [] arr = {9, 10, 11, 13, 15, 17, 2, 3, 5, 6, 8};
        SearchRotated searchRotated = new SearchRotated();
        int val = searchRotated.search(arr, 1);
        System.out.println(val);
    }
    public int search(int[] nums, int target) {
        if (nums.length ==0 ) {
            return -1;
        }
        if (nums.length ==1 && nums[0] != target) {
            return -1;
        }

        int startVal = nums[0];
        int turningPoint = findTurning(startVal, 1, nums.length-1, nums);

        if (nums[turningPoint] == target) {
            return turningPoint;
        }
        else if (startVal > target) {
            return java.util.Arrays.binarySearch(nums, turningPoint, nums.length, target);
        } else {
            return java.util.Arrays.binarySearch(nums, 0, turningPoint, target);
        }

    }

    int findTurning(int startVal, int startIndex, int lastIndex, int [] nums) {
        if (startIndex >= lastIndex) {
            return lastIndex;
        }
        int mid = (startIndex + lastIndex) / 2;
        int midVal = nums[mid];
        if (midVal > startVal) {
            return findTurning(startVal, mid+1, lastIndex, nums);
        } else {
            return findTurning(startVal, startIndex, mid, nums);
        }

    }
}
