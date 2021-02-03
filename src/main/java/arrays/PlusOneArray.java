package arrays;

/**
 * 66. Plus One
 * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contains a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 */
public class PlusOneArray {
    public int[] plusOne(int[] digits) {
        int j = digits.length - 1;
        int carry = 0;
        for (; j >= 0; j--) {
            if (digits[j] == 9) {
                digits[j] = 0;
                carry = 1;
            } else {
                digits[j] = digits[j] + 1;
                carry = 0;
                break;
            }
        }
        if (carry == 1) {
            //here because all digits are 9
            int[] nums = new int[digits.length + 1];
            nums[0] = 1;
            return nums;
        }
        return digits;
    }
}
