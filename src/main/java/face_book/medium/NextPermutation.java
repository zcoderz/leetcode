package face_book.medium;

import java.util.Arrays;

/**
 * 31. Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * IMP-1: This is a common important interview question. Its weird to get your head around so better to practice
 * if someone asks this question. Although i do think asking a question like this in interview is just wrong!
 *
 * Here is a great explanation of the solution that someone posted on leetcode discussion
 *             1. A decreasing array is the last permutation of the array, and the
 *                next one is the reverse of it. For example, `3,2,1` is the last
 *                permutation of this array and `1,2,3` will be next.
 *             2. For a decreasing subarray, there is a number right before the
 *                subarray that "leads" the subarray, e.g., `2,4,3,1` in which `2`
 *                "leads" the subarray `4,3,1`. Similarly, the decreasing subarray
 *                is the last permutation of itself, and the next one should be
 *                mostly the reverse of the subarray, except that the "leading"
 *                number is going to change. Looking at the previous example, the
 *                next permutation is `3,1,2,4` wherein the "leading" number is
 *                `3`, which is the next greater number than `2` in the sequence.
 *                Herein, the rule for generating the next permutation for a
 *                subarray is - 1) swap the "leading" number and the next greater
 *                one in the decreasing subarray; 2) reverse the decreasing
 *                subarray.
 *         In summary, the whole process should be the following:
 *             1. Find the decreasing subarray if not the entire array
 *             2. If it is not the entire array, swap the "leading" number and its
 *                next greater number in the decreasing subarray
 *             3. Reverse the decreasing part
 */
public class NextPermutation {

    public static void main(String [] args) {
        int [] nums = {1,2};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        for (int i =0; i < nums.length; i++) {
            System.out.print(nums[i] + " , ");
        }
    }

    public void nextPermutation(int[] nums) {
        //step 1: find first index thats not in descending order from right
        int j = nums.length-1; boolean nonDesc = false;
        for (; j>0; j--) {
            if (nums[j-1] < nums[j]) {
                nonDesc = true;
                j--;
                break;
            }
        }
        if (!nonDesc) {
            Arrays.sort(nums);
            return;
        }
        //step 2: find first index from right thats higher than the index found above
        int i = nums.length-1;
        for (; i > j; i--) {
            if (nums[i] > nums[j]) {
                break;
            }
        }
        //step 3: swap i and j
        swap(nums, i, j);
        //step 4: the index right of this index are in descending order, change them to be in ascending order
        for (i =j+1, j = nums.length-1; j > i; j--,i++) {
            swap(nums, i, j);
        }
    }

    void swap(int [] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

}
