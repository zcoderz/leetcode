package frequent.medium;


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
        while (hi>low) {
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
