package search;

public class FindPeakElement {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1};
        FindPeakElement peakElement = new FindPeakElement();
        peakElement.findPeakElement(nums);

    }

    public int findPeakElement(int[] nums) {
        return findPeek(0, nums.length - 1, nums);
    }

    /**
     * this is a clever solution.
     *
     * the code is based around the below idea :
     * 1. items left of the peek index will have the item following the mid index be greater than the mid index
     * 2. items right of the peek index will have the item following the mid index be less than the mid index
     *
     * therefore, based on 1 & 2 you can determine whether to move left or right in the array.
     * when you reach a point where left and right index are same than that must be the peek index.
     * @param left
     * @param right
     * @param nums
     * @return
     */
    public int findPeek(int left, int right, int[] nums) {
        if (left == right) return nums[left];

        int mid = (left + right) / 2;
        int val = nums[mid];
        int nextVal = nums[mid + 1];

        if (val > nextVal) {
            return findPeek(left, mid, nums);
        } else {
            return findPeek(mid + 1, right, nums);
        }
    }
}
