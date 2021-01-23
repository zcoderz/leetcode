package sort;

public class SortColors {

    //0, 1, 2

    public static void main(String[] args) {
        int[] nums = {2, 0, 1};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
    }

    public void sortColors(int[] nums) {
        int p2 = nums.length - 1;
        int curr = 0;
        int p1 = 0;

        while (p2 >= curr) {
            if (nums[curr] == 0) {
                if (nums[p1] == 0) {
                    curr++;
                    p1++;
                } else if (nums[p1] == 1) {
                    swap(nums, curr, p1);
                    curr++;
                    p1++;
                } else {
                    curr++;
                }
            } else if (nums[curr] == 1) {
                curr++;
            } else if (nums[curr] == 2) {
                swap(nums, curr, p2);
                p2--;
            }
        }

    }

    private void swap(int[] nums, int a, int b) {
        int i = nums[a];
        nums[a] = nums[b];
        nums[b] = i;
    }
}
