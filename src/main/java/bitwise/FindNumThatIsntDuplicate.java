package bitwise;

/**
 * 136. Single Number
 *
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 *
 */
public class FindNumThatIsntDuplicate {
    public int singleNumber(int[] nums) {
        int total = 0;
        //a xor b xor a = b. based on this if we keep iterating xor through the list of numbers
        //we will find the non duplicate number.
        for (int num : nums) {
            total ^= num;
        }
        return total;
    }
}
