package arrays;

public class MinSubArray {

    public static void main(String []args) {
        MinSubArray minSubArray = new MinSubArray();
        int[] nums = {2,3,1,2,4,3};
        int sum = 7;
        int len = minSubArray.minSubArrayLen(sum, nums);
        System.out.println(len);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int i=0;
        int left = 0;
        int sum=0;
        int minSubArrayLen = Integer.MAX_VALUE;
        for (; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s ) {
                minSubArrayLen = Math.min(minSubArrayLen, i-left);
                sum -= nums[left++];
            }
        }
        return (minSubArrayLen == Integer.MAX_VALUE) ? 0: minSubArrayLen+1;
    }
}
