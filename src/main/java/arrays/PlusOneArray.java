package arrays;

public class PlusOneArray {
    public int[] plusOne(int[] digits) {
        int j = digits.length-1;
        int carry = 0;
        for (; j>=0; j--) {
            if (digits[j]==9) {
                digits[j]=0;
                carry = 1;
            } else {
                digits[j] = digits[j] + 1;
                carry = 0;
                break;
            }
        }
        if (carry == 1) {
            int [] nums = new int[digits.length+1];
            nums[0]=1;
            System.arraycopy(digits, 0, nums, 1, nums.length - 1);
            return nums;
        }
        return digits;
    }
}
