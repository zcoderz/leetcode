package arrays;

/**
 * 189. Rotate Array
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int rotations = 3;

        RotateArray rA = new RotateArray();
        rA.rotate(nums, rotations);

        for (int num : nums) {
            System.out.println(num + ",");
        }
    }


    /**
     * this is a pretty elegant approach. think of an array as a ring and you are rotating the elements around you need
     * to continue rotating until you have rotated all the elements, as indicated by the variable count one trick is
     * that if array length % k ==0, you will come back to the index where you started from while items are left to be
     * rotated. in this case, simply move to the next element. you can construct above on white board via creating an
     * array of length 4 with k as 2.
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int count = 0;
        int iOrig = 0;
        int i = 0;

        int tmp = nums[i];

        do {
            int j = (i + k) % nums.length;
            int tmp1 = nums[j];
            nums[j] = tmp;
            tmp = tmp1;
            i = j;
            count++;
            if (i == iOrig) {
                i++;
                tmp = nums[i];
                iOrig = i;
            }
        } while (count != nums.length);

    }

}
