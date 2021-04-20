package search.binary_search;

/**
 * 1060. Missing Element in Sorted Array
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 *
 *
 * Example 1:
 *
 * Input: A = [4,7,9,10], K = 1
 * Output: 5
 * Explanation:
 * The first missing number is 5.
 *
 * find the Kth missing element in the array.
 *
 * IMP-3: binary search based solution is good practice
 */
public class MissingElements {

    public static void main(String[] args) {
        //int[] arr = {4, 7, 9, 10};
        int[] arr = {1, 2};
        MissingElements mE = new MissingElements();
        int num = mE.missingElement(arr, 2);
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
            //if more gaps requested than the number that occur in array
            //get the kth missing via the below equation
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
            //the offset between left and right is 1 but there may be any k numbers left
            //hence break here and add the remaining k to nums[left]
            return nums[left] + k;
        }
        int mid = (left + right) / 2;
        //find the gap between left and mid
        int theGap = ((nums[mid] - nums[left]) - (mid - left));
        if (theGap >= k) {
            //left off of mid has enough missing, so focus on left
            return search(nums, left, mid, k);
        } else {
            //left of mid didn't have enough missing , so find the remaining missing on the right
            int gaps = k - theGap;
            return search(nums, mid, right, gaps);
        }
    }
}
