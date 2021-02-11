package sort;

/**
 * 75. Sort Colors
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same
 * color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2, 0, 1};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
    }

    /**
     * interesting solution from leet code which is based off of Dijkstra's dutch flag problem
     * keep two pointers p0, and p2. p2 is at right , p2 is left, 1 items keep where they are
     * solvable in one pass....
     * @param nums
     */
    public void sortColors(int[] nums) {
        // for all idx < i : nums[idx < i] = 0
        // j is an index of element under consideration
        int p0 = 0, curr = 0;
        // for all idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;
        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // swap p0-th and curr-th elements
                // i++ and j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }
            else if (nums[curr] == 2) {
                // swap k-th and curr-th elements
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            }
            else curr++;
        }
    }
}
