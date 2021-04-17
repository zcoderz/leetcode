package frequent.medium;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 *
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor,
 * divide all the array by it, and sum the division's result.
 * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than or equal to that element.
 * (For example: 7/3 = 3 and 10/2 = 5).
 *
 * It is guaranteed that there will be an answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 *
 * IMP-3
 */
public class SmallestDivisor {

    public static void main(String [] args) {
        //int [] nums = {2,3,5,7,11};
        //int [] nums = {1,2,5,9};
        //int [] nums = {1,2,3};
        int [] nums = {962551,933661,905225,923035,990560};
        SmallestDivisor sd = new SmallestDivisor();
        int div = sd.smallestDivisor(nums, 10);
        System.out.println(div);

    }

    public int smallestDivisor(int[] nums, int threshold) {
        int hi = 0;
        int len = nums.length;
        //set the hi number at start to be the highest in the array
        for (int j =0; j < len; j++) {
            hi = Math.max(nums[j] , hi);
        }
        int count ;
        int low = 1;
        int divisor = low + (hi-low)/2;
        //binary search to converge lo and hi to be same
        while (hi > low) {
            count = 0;
            int i = 0;
            for (; i < len; i++) {
                count += Math.ceil(nums[i] / (double) divisor);
            }
            //increase divisor if count is greater than threshold
            if (count > threshold) {
                //structure to move low up than high down as rounding from hi-lo/2
                // due to integer division pushes the divisor down
                low = divisor+1;
            } else {
                hi = divisor;
            }
            //updating divisor here so as to update it for when hi and lo are equal because the above
            //loop breaks when hi=low
            divisor = low + (hi-low)/2;
        }
        return divisor;
    }
}
