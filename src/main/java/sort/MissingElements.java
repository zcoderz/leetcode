package sort;

/**
 * find the Kth missing element in the array.
 */
public class MissingElements {

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 10};

        MissingElements mE = new MissingElements();
        int num = mE.missingElement(arr, 1);
        System.out.println(num);

        num = mE.missingElement(arr, 3);
        System.out.println(num);

        num = mE.missingElement(arr, 6);
        System.out.println(num);

        int[] arr2 = {1, 2, 4};
        num = mE.missingElement(arr2, 3);
        System.out.println(num);
    }


    public int missingElement(int[] nums, int k) {
        if (nums.length == 0) return k;
        int gaps = (nums[nums.length - 1] - nums[0]) - (nums.length) + 1;
        if (k > gaps) {
            //if more gaps requested then occur in array , get the num via below equation
            return nums[nums.length - 1] + (k - gaps);
        }

        //do a binary search
        return search(nums, 0, nums.length - 1, k);
    }

    /**
     * do a binary search
     *
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
    int search(int[] nums, int left, int right, int k) {
        if ((right - left) == 1) {
            //the gap between left and right has narrowed to a single offset
            return nums[left] + k;
        }

        int mid = (left + right) / 2;
        if ((nums[mid] - nums[left]) > k) {
            //left off of mid has enough missing, so focus on left
            return search(nums, left, mid, k);
        } else {
            //left off of mid didnt have enough missing , so find the remaining missing on the right
            int gaps = k - ((nums[mid] - nums[left]) - (mid - left));
            return search(nums, mid, right, gaps);
        }
    }
}
