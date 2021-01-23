package arrays;

import java.util.Arrays;

public class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums = {7, 1, 8, 11, 2, 1, 7, 4};

        //need to sort the array first, makes finding duplicates easier.
        Arrays.sort(nums);
        int sizeAfterDuplicatesRemoved = rd.removeDuplicates(nums);
        System.out.println(sizeAfterDuplicatesRemoved);
    }

    /**
     * Return size of the array after duplicates have been removed
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 0;

        for (; j != nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
        }
        return i + 1;
    }

}
