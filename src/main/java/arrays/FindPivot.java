package arrays;

/**
 * 724. Find Pivot Index
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to
 * the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
 * This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 */
public class FindPivot {

    public static void main(String[] args) {
        FindPivot findPivot = new FindPivot();
        int[] nums = {-1, -1, 0, 1, 1, 0};
        int index = findPivot.pivotIndex(nums);
        System.out.println(index);
    }

    /**
     * Find pivot where sum of items on left is same as that on right of pivot easiest to think of this as total sum and
     * then collect left, see where it balances
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int lSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (lSum * 2 == sum - nums[i]) {
                return i;
            }
            lSum += nums[i];
        }
        return -1;
    }
}
