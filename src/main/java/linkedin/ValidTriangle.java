package linkedin;

import java.util.Arrays;

public class ValidTriangle {

    public static void main(String [] args) {
        int [] nums = {2,2,3,4};
        ValidTriangle vt = new ValidTriangle();
        int res = vt.triangleNumber(nums);
        System.out.println(res);

        int [] nums2  = {4,2,3,4};
        ValidTriangle vt2 = new ValidTriangle();
        res = vt2.triangleNumber(nums2);
        System.out.println(res);

    }

    int count = 0;
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            int index = i+2;
            for (int j = i+1; j < nums.length-1 && nums[i] !=0; j++) {
                index = binarySearch(index, nums.length-1, nums[i] + nums[j], nums) ;
                count += index - 1 - j;
            }
        }
        return count;
    }

    int binarySearch(int left, int right, int target, int [] nums) {

        while (right >= left) {
            int mid = left + (right-left)/2;
            if (nums[mid] >= target) {
                right = mid-1;
            } else {
                left = left+1;
            }
        }
        return left;
    }
}
