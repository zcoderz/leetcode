package arrays;

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
