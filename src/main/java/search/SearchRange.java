package search;

public class SearchRange {
    public static void main (String [] args ) {
        int []nums = {2,2};
        SearchRange range = new SearchRange();
        int index = range.findLast(nums, 0, nums.length-1, 1);
        System.out.println(index);
    }

    public int[] searchRange(int[] nums, int target) {
        int index = findFirst(nums,0, nums.length-1, target);

        int []vl = {1};
        return vl;
    }

    int findFirst(int [] nums, int left, int right, int target) {
        if (left >= right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = (left + right) / 2;
        int valAtMid = nums[mid];
        if (valAtMid > target) {
            return findFirst(nums, left, mid-1, target);
        } else if (valAtMid < target) {
            return findFirst(nums, mid+1, right, target);
        } else {
            if (mid == 0 || nums [mid-1] < target) {
                return mid;
            } else {
                return findFirst(nums, left, mid-1, target);
            }
        }
    }

    int findLast(int [] nums, int left, int right, int target) {
        if (left >= right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = (left + right) / 2;
        int valAtMid = nums[mid];
        if (valAtMid > target) {
            return findLast(nums, left, mid-1, target);
        } else if (valAtMid < target) {
            return findLast(nums, mid+1, right, target);
        } else {
            if (mid == nums.length-1 || nums [mid+1] > target) {
                return mid;
            } else {
                return findLast(nums, mid+1, right, target);
            }
        }
    }
}
