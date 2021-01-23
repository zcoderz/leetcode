package google.medium;

/**
 * 280. Wiggle Sort Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <=
 * nums[3]....
 * <p>
 * Example:
 * <p>
 * Input: nums = [3,5,2,1,6,4] Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 1, 6, 4};
        WiggleSort sort = new WiggleSort();
        sort.wiggleSort(nums);
        for (int num : nums) {
            System.out.print(num);
        }
    }

    /**
     * this is an extremely simple problem but have to simplify what the question is asking as it seems at first to be
     * quite complex the question really is only asking for that : 1. iterate left to right 2. check alternatively :
     * index <= index + 1 & index >= index + 1
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1]) ||
                    ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

    void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}
