package redo;

public class FindKthLargest {

    public static void main(String [] args) {
        FindKthLargest find = new FindKthLargest();
        int [] nums = {3,2,1,5,6,4};

        int v = find.findKthLargest(nums, nums.length-1);
        System.out.println(v);
    }

    public int findKthLargest(int[] nums, int k) {
        return findKthRange(nums, 0, nums.length-1, k);
    }


    int findKthRange(int [] nums, int left, int right, int k) {
        int v = left + (right-left)/2;


        int loc = partition(nums, left, right, v);

        if (loc == k) {
            return loc;
        } else if ( loc > k) {
            return findKthRange(nums, left, loc, k);
        } else  {
            return  findKthRange(nums, loc, right, k);
        }

    }

    int partition(int [] nums, int left, int right, int v) {
        int val = nums[v];
        int oRight = right;
        swap(nums, v, right--);

        while (left < right) {
            while (nums[left] < val && left < right) left++;
            while (nums[right] > val && left < right) right--;
            if (right > left) {
                swap(nums, left++, right--);
            }
            if (right <= left) {
                break;
            }
        }

        swap(nums, right, oRight);
        return right;
    }

    void swap(int [] nums, int left, int right) {
        int tmp = nums[right];
        nums[right] = nums[left];
        nums[left] = tmp;
    }
}
