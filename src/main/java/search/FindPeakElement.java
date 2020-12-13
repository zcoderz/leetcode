package search;

public class FindPeakElement {

    public static void main (String [] args) {

        int []nums = {1,2,3,1};
        FindPeakElement peakElement = new FindPeakElement();
        peakElement.findPeakElement(nums);

    }

    public int findPeakElement(int[] nums) {
        return findPeek(0, nums.length-1, nums);
    }

    public int findPeek(int left, int right, int [] nums) {
        if (left == right) return nums[left];

        int mid = (left + right)/ 2;
        int val = nums[mid];
        int nextVal = nums[mid+1];

        if (val > nextVal) {
            return findPeek(left, mid, nums);
        } else {
            return findPeek(mid+1, right, nums);
        }
    }
}
