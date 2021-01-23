package arrays;

import java.util.Arrays;

public class ThreeSumLess {

    public static void main(String[] args) {
        int[] nums = {3, 1, 0, -2};
        ThreeSumLess tSum = new ThreeSumLess();
        int sum = tSum.threeSum(nums, 4);
        System.out.println(sum);
    }


    public int threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += findSums(nums[i], i + 1, nums, target);
        }
        return sum;
    }

    int findSums(int start, int startIndex, int[] nums, int target) {
        target = target - start;
        int i = startIndex;
        int j = nums.length - 1;
        int sum = 0;
        while (j > i) {
            int currSum = nums[i] + nums[j];
            if (currSum >= target) {
                j--; //make currSum smaller
            } else {
                sum += j - i;
                i++;
            }
        }
        return sum;
    }
}

