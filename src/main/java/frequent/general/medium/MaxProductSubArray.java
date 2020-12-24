package frequent.general.medium;


/**
 * simple problem.
 * find max product in sub array.
 */
public class MaxProductSubArray {

    public static void main(String [] args) {
        MaxProductSubArray maxProductSubArray = new MaxProductSubArray();
        int [] nums = {-1,-2,-9,-6};
        maxProductSubArray.maxProduct(nums);
        System.out.println(maxProductSubArray.maxSubArr);
    }

    int maxSubArr = Integer.MIN_VALUE;

    /**
     * simple code, start from right and move to left , keeping track of current max, min
     * need to keep track of max and min because two negatives make a positive.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int priorMin = 1;
        int priorMax = 1;
        for (int j = nums.length-1; j>=0; j--) {
            int max = Math.max(nums[j] * priorMax, nums[j] * priorMin);
            int min = Math.min(nums[j] * priorMax, nums[j] * priorMin);

            priorMax = Math.max(nums[j] , max);
            priorMin = Math.min(nums[j] , min);

            this.maxSubArr = Math.max(priorMax, this.maxSubArr);
        }
        return this.maxSubArr;
    }

}
